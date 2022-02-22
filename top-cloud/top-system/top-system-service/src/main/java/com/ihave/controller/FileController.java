package com.ihave.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;
import com.ihave.api.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

/**
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/8/3 上午11:32
 */
@RestController
@Api(tags = "文件上传控制器")
@RequestMapping("/oss")
@Slf4j
public class FileController {

    @Autowired
    private OSS ossClient;

    @Value("${spring.cloud.alicloud.oss.endpoint}")
    private String endpoint;

    @Value("${spring.cloud.alicloud.oss.bucketName}")
    private String bucketName;

    @Value("${spring.cloud.alicloud.access-key}")
    private String accessId;

    @Value("${spring.cloud.alicloud.secret-key}")
    private String secretKey;

    @Value("${spring.cloud.alicloud.roleArn}")
    private String roleArn;

    @Value("${spring.cloud.alicloud.stsEndPoint}")
    private String stsEndPoint;

    @Value("${spring.profiles.active}")
    private String active;


    /**
     * 把文件存在阿里云oss
     * ossClient.putObject(bucketName【阿里云上创建的】,文件名，文件）
     *
     * @param file 文件
     * @param flag 标志（forever，返回的链接为22年可用）
     * @param dirName 文件夹 （top/当前环境/文件夹名称/文件）
     * @return url链接、md5值
     * @throws IOException io异常
     */
    @ApiOperation(value = "上传文件")
    @PostMapping("/aliyun")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "你要上传的文件"),
            @ApiImplicitParam(name = "flag", value = "标志（forever，返回的链接为22年可用）"),
            @ApiImplicitParam(name = "dirName", value = "文件夹 （top/当前环境/文件夹名称/文件）")
    })
    public R<Object> upload(@RequestParam(value = "file") MultipartFile file, String flag, String dirName) throws Exception {
        Map<String, Object> result = new HashMap<>();
        InputStream inputStream = file.getInputStream();
        String suffix = Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf("."));
        String fileName = "top" + "/" + active + "/" + dirName + "/" + UUID.randomUUID().toString().replaceAll("-", "") + suffix;
        PutObjectResult putObjectResult = ossClient.putObject(bucketName, fileName, inputStream);
        String eTag = putObjectResult.getETag();
        if ("forever".equalsIgnoreCase(flag)) {
            Date expiration = new Date(new Date().getTime() + 3600L * 200000000); // 22 year
            GeneratePresignedUrlRequest generatePresignedUrlRequest;
            generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, fileName);
            generatePresignedUrlRequest.setExpiration(expiration);
            URL url = ossClient.generatePresignedUrl(generatePresignedUrlRequest);
            result.put("url", url.toString());
        } else {
            result.put("url", fileName);
        }
        result.put("md5", eTag);
        return R.data(result);
    }

    /**
     *
     * @param file 文件的url
     * @return 可用的url链接
     */
    @ApiOperation(value = "下载文件")
    @GetMapping("/download")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "文件url")
    })
    public R<Object> downLoad(String file) {
        Map<String,Object> map = new HashMap<>();
        // 设置URL过期时间为1小时
        Date expiration = new Date(new Date().getTime() + 3600 * 1000);
        GeneratePresignedUrlRequest generatePresignedUrlRequest;
        generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, file);
        generatePresignedUrlRequest.setExpiration(expiration);
        URL url = ossClient.generatePresignedUrl(generatePresignedUrlRequest);
        map.put("url", url.toString());
        return R.data(map);
    }


    /**
     * 获取上传的票据
     *
     * @return sts信息
     */
    @GetMapping("/getPolicy")
    @ApiOperation(value = "获取一个上传的票据")
    public R<Object> asyncUpload() {
        // 自定义角色会话名称，用来区分不同的令牌，例如可填写为SessionTest。
        String roleSessionName = "sessionFile";
        try {
            // regionId表示RAM的地域ID。以华东1（杭州）地域为例，regionID填写为cn-hangzhou。也可以保留默认值，默认值为空字符串（""）。
            String regionId = "";
            // 添加endpoint。
            DefaultProfile.addEndpoint(regionId, "Sts", stsEndPoint);
            // 构造default profile。
            IClientProfile profile = DefaultProfile.getProfile(regionId, accessId, secretKey);
            // 构造client。
            DefaultAcsClient client = new DefaultAcsClient(profile);
            final AssumeRoleRequest request = new AssumeRoleRequest();
            request.setSysMethod(MethodType.POST);
            request.setRoleArn(roleArn);
            request.setRoleSessionName(roleSessionName);
            request.setDurationSeconds(3600L); // 设置临时访问凭证的有效时间为3600秒。
            final AssumeRoleResponse response = client.getAcsResponse(request);
            Map<String, Object> map = new HashMap<>();
            map.put("accessKey", response.getCredentials().getAccessKeyId());
            map.put("accessSecret", response.getCredentials().getAccessKeySecret());
            map.put("region", endpoint.split("\\.")[0]);
            map.put("bucket", bucketName);
            map.put("stsToken", response.getCredentials().getSecurityToken());
            map.put("active", active);
            return R.data(map);
        } catch (ClientException e) {
            log.error("Failed:");
            log.error("Error code: " + e.getErrCode());
            log.error("Error message: " + e.getErrMsg());
            log.error("RequestId: " + e.getRequestId());
            return R.data(null);
        }
    }


}
