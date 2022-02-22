package com.ihave.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;
import java.util.UUID;

public class FileUtil {

    /**
     * 下载网络文件
     * @param url 网络地址
     * @return file
     */
    public static File downloadFile(String url) {
        File file = null;

        URL urlfile;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            //下载
            file = File.createTempFile("music_", ".tem");

            urlfile = new URL(url);
            inputStream = urlfile.openStream();
            outputStream = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = inputStream.read(buffer, 0, 8192)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != outputStream) {
                    outputStream.close();
                }
                if (null != inputStream) {
                    inputStream.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    /**
     * 制作zip文件并加密
     * @param file 文件
     * @param password 密码
     * @return
     */
    public static InputStream zip_encryption(MultipartFile file, String password) {
        try {
            File fileToFile = ZipUtil.multipartFileToFile(file);
            String unzipPackage = "/tmp/" + UUID.randomUUID();

            String zipPackage = unzipPackage + "/" + UUID.randomUUID() + ".zip";
            ZipUtil.unZipFile(fileToFile, unzipPackage, null);
            ZipUtil.zipFile(unzipPackage, zipPackage, password);
            File finishZip = new File(zipPackage);
            FileInputStream fileInputStream = new FileInputStream(finishZip);
            ZipUtil.delete(new File(unzipPackage));
            ZipUtil.delete(fileToFile);
            return fileInputStream;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
