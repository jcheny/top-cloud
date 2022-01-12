package com.ihave.utils;


import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/12/20 下午5:30
 */
public class ZipUtil {



    public static File multipartFileToFile(MultipartFile file) throws Exception {

        File toFile = null;
        if (file.equals("") || file.getSize() <= 0) {
            file = null;
        } else {
            InputStream ins = null;
            ins = file.getInputStream();
            toFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
            inputStreamToFile(ins, toFile);
            ins.close();
        }
        return toFile;
    }

    //获取流文件
    private static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 压缩指定路径的文件
     *
     * @param srcFilePath 待压缩文件路径
     * @param zipPathFileName zip文件全路径名
     * @param password        加密密码
     * @return
     */
    public static boolean zipFile(String srcFilePath, String zipPathFileName, String password) {

        try {
            // 生成的压缩文件
            ZipFile zipFile = new ZipFile(zipPathFileName);
            ZipParameters parameters = new ZipParameters();
            // 压缩级别
            parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
            parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);

            if (password != null) {
                parameters.setEncryptFiles(true);
                parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
                parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
                parameters.setPassword(password);
            }

            // 要打包的文件夹
            File currentFile = new File(srcFilePath);
            File[] fs = currentFile.listFiles();
            // 遍历test文件夹下所有的文件、文件夹
            for (File f : fs) {
                if (f.isDirectory()) {
                    zipFile.addFolder(f.getPath(), parameters);
                } else {
                    zipFile.addFile(f, parameters);
                }
            }
            return true;
        } catch (ZipException e) {
            e.printStackTrace();
            System.out.println("压缩文件【" + srcFilePath + "】到路径【" + zipPathFileName + "】失败：\n" + e.getMessage());
            return false;
        }

    }

    /**
     * 压缩指定路径的文件
     *
     * @param currentFile 要打包的文件夹
     * @param zipPathFileName zip文件全路径名
     * @param password        加密密码
     * @return
     */
    public static boolean zipFile(File currentFile, String zipPathFileName, String password) {

        try {
            // 生成的压缩文件
            ZipFile zipFile = new ZipFile(zipPathFileName);
            ZipParameters parameters = new ZipParameters();
            // 压缩级别
            parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
            parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);

            if (password != null) {
                parameters.setEncryptFiles(true);
                parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
                parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
                parameters.setPassword(password);
            }

            // 要打包的文件夹
            File[] fs = currentFile.listFiles();
            // 遍历test文件夹下所有的文件、文件夹
            for (File f : fs) {
                if (f.isDirectory()) {
                    zipFile.addFolder(f.getPath(), parameters);
                } else {
                    zipFile.addFile(f, parameters);
                }
            }
            return true;
        } catch (ZipException e) {
            e.printStackTrace();
            System.out.println("压缩文件【" + currentFile + "】到路径【" + zipPathFileName + "】失败：\n" + e.getMessage());
            return false;
        }

    }

    /**
     * @param zipFileFullName zip文件所在的路径名
     * @param filePath        解压到的路径
     * @param password        需要解压的密码
     * @return
     */
    public static boolean unZipFile(String zipFileFullName, String filePath, String password) {
        try {
            ZipFile zipFile = new ZipFile(zipFileFullName);
            // 如果解压需要密码
            if (StringUtils.isNotEmpty(password) && zipFile.isEncrypted()) {
                zipFile.setPassword(password);
            }
            zipFile.extractAll(filePath);
            return true;
        } catch (ZipException e) {
            e.printStackTrace();
            System.out.println("解压文件【" + zipFileFullName + "】到路径【" + filePath + "】失败：\n" + e.getMessage());
            return false;
        }
    }

    public static void delete(File file) {
        if(!file.exists()) return;

        if(file.isFile() || file.list()==null) {
            file.delete();
            System.out.println("删除了"+file.getName());
        }else {
            File[] files = file.listFiles();
            for(File a:files) {
                delete(a);
            }
            file.delete();
            System.out.println("删除了"+file.getName());
        }

    }


    /**
     * @param zipFileFullName zip文件所在的路径名
     * @param filePath        解压到的路径
     * @param password        需要解压的密码
     * @return
     */
    public static boolean unZipFile(File zipFileFullName, String filePath, String password) {
        try {
            ZipFile zipFile = new ZipFile(zipFileFullName);
            // 如果解压需要密码
            if (StringUtils.isNotEmpty(password) && zipFile.isEncrypted()) {
                zipFile.setPassword(password);
            }
            zipFile.extractAll(filePath);
            return true;
        } catch (ZipException e) {
            e.printStackTrace();
            System.out.println("解压文件【" + zipFileFullName + "】到路径【" + filePath + "】失败：\n" + e.getMessage());
            return false;
        }
    }



    /**
     * 添加文件到压缩文件中
     *
     * @param zipFullFileName  zip文件所在路径及全名
     * @param fullFileNameList 待添加的文件全路径集合
     * @param rootFolderInZip  在压缩文件里的文件夹名
     * @return
     */
    public static boolean addFilesToZip(String zipFullFileName, List<String> fullFileNameList, String rootFolderInZip) {

        try {
            ZipFile zipFile = new ZipFile(zipFullFileName);
            ArrayList<File> addFiles = new ArrayList<>();
            for (String fileName : fullFileNameList) {

                addFiles.add(new File(fileName));
            }
            ZipParameters parameters = new ZipParameters();
            parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
            parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
            if (StringUtils.isNotEmpty(rootFolderInZip)) {
                if (!rootFolderInZip.endsWith("/")) {
                    rootFolderInZip = rootFolderInZip + "/";
                }
                parameters.setRootFolderInZip(rootFolderInZip);
            }
            zipFile.addFiles(addFiles, parameters);
            return true;
        } catch (ZipException e) {
            e.printStackTrace();
            System.out.println("添加文件失败：\n" + e.getMessage());
            return false;
        }
    }


    /**
     * 从压缩文件中删除路径
     *
     * @param zipFullFileName
     * @param fileName
     * @return
     */
    public static boolean deleteFileInZip(String zipFullFileName, String fileName) {
        try {
            ZipFile zipFile = new ZipFile(zipFullFileName);
            zipFile.removeFile(fileName);
            return true;
        } catch (ZipException e) {
            e.printStackTrace();
            System.out.println("删除文件失败：\n" + e.getMessage());
            return false;
        }

    }

}
