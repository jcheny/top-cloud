package com.ihave.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.UUID;

/**
 * RSA加解密工具类，实现公钥加密私钥解密和私钥解密公钥解密
 */
public class RSAUtils {

    public static final String pub_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCMEHZ+e2SAX3ubYnr0IU98KyvmZnC5fiWwVl7rLzfBooEqa4BlkSlAKISEjY4IsKgitsDZYo+Xn+P+MYuDqjrjLM5GUR2mgXD+THi53XbQoBvezJFTjoUuPT2Aqqn+dQr7F2VrgqsbMoavdUN3bq8poU5ZXbBjPVnx3ygoVbtnNQIDAQAB";

    public static final String pri_key = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIwQdn57ZIBfe5tievQhT3wrK+ZmcLl+JbBWXusvN8GigSprgGWRKUAohISNjgiwqCK2wNlij5ef4/4xi4OqOuMszkZRHaaBcP5MeLnddtCgG97MkVOOhS49PYCqqf51CvsXZWuCqxsyhq91Q3durymhTlldsGM9WfHfKChVu2c1AgMBAAECgYB54H1rc+2w8uT5Acmhkza6Akrx8mPWVNlwxsdTeMPYn5Irml2gg57UcC1XtmFIWIgFpObMgkIWmw/mCbCvwjn4UqZ3FjpEZNJOh59iwylVj9VexMCN++twzlh0HTk1CKOxjj3cXn+ej1meOcxRFd0X8at3OnIXcHAPX16UprqpoQJBAOQg3sECVhhoMrgXv+l788E8UxDhJznsbXDl56XXgqcTHmHoDZIY/Z5hDc9397bceoArfCArHQWsDbjOH+QtvekCQQCdLTp4pSK+BNWSmfx6WzRih6fOtQEu/ntcm3Y/aZqD4+llO0/C89tC5wYaVqqDCOr07h9J8xG6geT13Xgj11NtAkEAoC88eoZrcePoiT+T8ByrQipboWSXqKKNDlNA30B+a1aPZOwOa2HE5Cr+CYoRlIMoOUtPcz8St+LiF7wUxKIh0QJAWWfjzcKpe4tGbWjYPEVrC+z8YFGHxdKOG4R3A+UvNnzLQ4E0gsGx5GGDkDHiNj05YW+eLrNsTzoaIl0DPOf8FQJAQeAUmmfwqaJfJ7RTU28IcJgGRevR+YQmvp5ahyKDwQmlci5HX5rpxQUhNKlZ+igpQdT+meuy7z1Wj4fZEuzyHQ==";

    private static final String src = UUID.randomUUID().toString();

//    public static void main(String[] args) throws Exception {
//        System.out.println("\n");
//        RSAKeyPair keyPair = generateKeyPair();
//        System.out.println("公钥：" + pub_key);
//        System.out.println("私钥：" + pri_key);
//        System.out.println("\n");
//        test1(keyPair, src);
//        System.out.println("\n");
//        test2(keyPair, src);
//        System.out.println("\n");
//        test3("LVlxRNy39OfFVJBtEV+CRlt4tciyqzd8Cotu6SUEmafA2uNKypQeXPe8QTkYU88ZsgGFJ4OnSb5CcU6tIcklz0sEkEuo9TNWVLdYuijfNty9xz75HWohsPf/ReUREBuuMAgwxG3BJj5NWUWfulo3giqBRCvYZEhyAp1LFZOq644=");
//    };

    public static void test3(String source) throws Exception {
        System.out.println("***************** 私钥加密公钥解密开始 *****************");
        String text2 = decryptByPublicKey(pub_key, source);
        System.out.println("解密后：" + text2);
    }

    /**
     * 公钥加密私钥解密
     */
    private static void test1(RSAKeyPair keyPair, String source) throws Exception {
        System.out.println("***************** 公钥加密私钥解密开始 *****************");
        String text1 = encryptByPublicKey(keyPair.getPublicKey(), source);
        String text2 = decryptByPrivateKey(keyPair.getPrivateKey(), text1);
        System.out.println("加密前：" + source);
        System.out.println("加密后：" + text1);
        System.out.println("解密后：" + text2);
        if (source.equals(text2)) {
            System.out.println("解密字符串和原始字符串一致，解密成功");
        } else {
            System.out.println("解密字符串和原始字符串不一致，解密失败");
        }
        System.out.println("***************** 公钥加密私钥解密结束 *****************");
    }

    /**
     * 私钥加密公钥解密
     *
     * @throws Exception
     */
    private static void test2(RSAKeyPair keyPair, String source) throws Exception {
        System.out.println("***************** 私钥加密公钥解密开始 *****************");
        String text1 = encryptByPrivateKey(keyPair.getPrivateKey(), source);
        String text2 = decryptByPublicKey(keyPair.getPublicKey(), text1);
        System.out.println("加密前：" + source);
        System.out.println("加密后：" + text1);
        System.out.println("解密后：" + text2);
        if (source.equals(text2)) {
            System.out.println("解密字符串和原始字符串一致，解密成功");
        } else {
            System.out.println("解密字符串和原始字符串不一致，解密失败");
        }
        System.out.println("***************** 私钥加密公钥解密结束 *****************");
    }

    /**
     * 公钥解密
     *
     * @param publicKeyText
     * @param text
     * @return
     * @throws Exception
     */
    public static String decryptByPublicKey(String publicKeyText, String text) throws Exception {
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKeyText));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] result = cipher.doFinal(Base64.decodeBase64(text));
        return new String(result);
    }

    /**
     * 私钥加密
     *
     * @param privateKeyText
     * @param text
     * @return
     * @throws Exception
     */
    public static String encryptByPrivateKey(String privateKeyText, String text) throws Exception {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKeyText));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] result = cipher.doFinal(text.getBytes());
        return Base64.encodeBase64String(result);
    }

    /**
     * 私钥解密
     *
     * @param privateKeyText
     * @param text
     * @return
     * @throws Exception
     */
    public static String decryptByPrivateKey(String privateKeyText, String text) throws Exception {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec5 = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKeyText));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec5);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] result = cipher.doFinal(Base64.decodeBase64(text));
        return new String(result);
    }

    /**
     * 公钥加密
     *
     * @param publicKeyText
     * @param text
     * @return
     */
    public static String encryptByPublicKey(String publicKeyText, String text) throws Exception {
        X509EncodedKeySpec x509EncodedKeySpec2 = new X509EncodedKeySpec(Base64.decodeBase64(publicKeyText));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec2);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] result = cipher.doFinal(text.getBytes());
        return Base64.encodeBase64String(result);
    }

    /**
     * 构建RSA密钥对
     *
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static RSAKeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
        String publicKeyString = Base64.encodeBase64String(rsaPublicKey.getEncoded());
        String privateKeyString = Base64.encodeBase64String(rsaPrivateKey.getEncoded());
        RSAKeyPair rsaKeyPair = new RSAKeyPair(publicKeyString, privateKeyString);
        return rsaKeyPair;
    }


    /**
     * RSA密钥对对象
     */
    public static class RSAKeyPair {

        private String publicKey;
        private String privateKey;

        public RSAKeyPair(String publicKey, String privateKey) {
            this.publicKey = publicKey;
            this.privateKey = privateKey;
        }

        public String getPublicKey() {
            return publicKey;
        }

        public String getPrivateKey() {
            return privateKey;
        }

    }

}