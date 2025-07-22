package com.sharing.cn.common.encrypt;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.Security;
import java.util.Arrays;

/**
 * <p> Copyright 2018 BMAC, All right reserved.  </p >
 *  <dependency>
 *             <groupId>org.bouncycastle</groupId>
 *             <artifactId>bcprov-jdk15on</artifactId>
 *             <version>1.56</version>
 *         </dependency>
 *         <dependency>
 *             <groupId>com.github.ulisesbocchio</groupId>
 *             <artifactId>jasypt-spring-boot-starter</artifactId>
 *             <version>3.0.3</version>
 *         </dependency>
 *
 * @author baviya
 * @version 1.0.0
 * @Description
 * @create 2019-07-01 14:07
 **/
public class Sm4Util {
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    private static final String ENCODING = "UTF-8";
    public static final String ALGORITHM_NAME = "SM4";
    // 加密算法/分组加密模式/分组填充方式
    // PKCS5Padding-以8个字节为一组进行分组加密
    // 定义分组加密模式使用：PKCS5Padding
    public static final String ALGORITHM_NAME_ECB_PADDING = "SM4/ECB/PKCS5Padding";
    // 128-32位16进制；256-64位16进制
    public static final int DEFAULT_KEY_SIZE = 128;

    /**
     * 生成ECB暗号
     * @explain ECB模式（电子密码本模式：Electronic codebook）
     * @param algorithmName
     *            算法名称
     * @param mode
     *            模式
     * @param key
     * @return
     * @throws Exception
     */
    private static Cipher generateEcbCipher(String algorithmName, int mode, byte[] key) throws Exception {
        Cipher cipher = Cipher.getInstance(algorithmName, BouncyCastleProvider.PROVIDER_NAME);
        Key sm4Key = new SecretKeySpec(key, ALGORITHM_NAME);
        cipher.init(mode, sm4Key);
        return cipher;
    }

    public static String encryptEcb(String hexKey, String paramStr) throws Exception {
        String cipherText = "";
        // 16进制字符串--&gt;byte[]
        byte[] keyData = ByteUtils.fromHexString(hexKey);
        // String--&gt;byte[]
        byte[] srcData = paramStr.getBytes(ENCODING);
        // 加密后的数组
        byte[] cipherArray = encrypt_Ecb_Padding(keyData, srcData);
        // byte[]--&gt;hexString
        cipherText = ByteUtils.toHexString(cipherArray);
        return cipherText;
    }

    private static byte[] encrypt_Ecb_Padding(byte[] key, byte[] data) throws Exception {
        Cipher cipher = generateEcbCipher(ALGORITHM_NAME_ECB_PADDING, Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(data);
    }
    public static String decryptEcb(String hexKey, String cipherText) throws Exception {
        // 用于接收解密后的字符串
        String decryptStr = "";
        // hexString--&gt;byte[]
        byte[] keyData = ByteUtils.fromHexString(hexKey);
        // hexString--&gt;byte[]
        byte[] cipherData = ByteUtils.fromHexString(cipherText);
        // 解密
        byte[] srcData = decrypt_Ecb_Padding(keyData, cipherData);
        // byte[]--&gt;String
        decryptStr = new String(srcData, ENCODING);
        return decryptStr;
    }
    private static byte[] decrypt_Ecb_Padding(byte[] key, byte[] cipherText) throws Exception {
        Cipher cipher = generateEcbCipher(ALGORITHM_NAME_ECB_PADDING, Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(cipherText);
    }
    public static boolean verifyEcb(String hexKey, String cipherText, String paramStr) throws Exception {
        // 用于接收校验结果
        boolean flag = false;
        // hexString--&gt;byte[]
        byte[] keyData = ByteUtils.fromHexString(hexKey);
        // 将16进制字符串转换成数组
        byte[] cipherData = ByteUtils.fromHexString(cipherText);
        // 解密
        byte[] decryptData = decrypt_Ecb_Padding(keyData, cipherData);
        // 将原字符串转换成byte[]
        byte[] srcData = paramStr.getBytes(ENCODING);
        // 判断2个数组是否一致
        flag = Arrays.equals(decryptData, srcData);
        return flag;
    }

    public static void main(String[] args) {
        try {
            // 自定义的32位16进制密钥
            String key = "86C63180C2806ED1F47B859DE501215B";
            String s = Sm4Util.encryptEcb(key, "13801200833");
            System.out.println("en:" + s);
            s = Sm4Util.decryptEcb(key, "2d96c392289801ac2092ec4f587e920c");
            System.out.println("de:" + s);
//            String name = Sm4Util.decryptEcb(key, "b0bfd77c6af28b1214486b8c0ce7183c");
//            System.out.println(name);
//            String cardId = Sm4Util.decryptEcb(key, "cdf762c1ebd7e9cb098d14962d8d30583542f46b44c9b70f1d50527602564e8e");
//            String unionId = SHAUtil.Encrypt(name + "0" + cardId, "SHA1");
//            System.out.println(unionId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 脱敏关键信息
     * @param sm4Key
     * @param msg
     * @param type 0 姓名 1身份证 2 手机号
     * @return
     */
    public static String tuomin(String sm4Key, String msg,String type){
        StringBuilder stringBuilder = new StringBuilder();
        if("0".equals(type)){
            //姓名
            try {
                msg = Sm4Util.decryptEcb(sm4Key, msg);
            } catch (Exception e) {
                e.printStackTrace();
                //log.info("sm4解密姓名信息失败：{}",msg);
            }
            for(int i=0;i<msg.length()-1;i++){
                stringBuilder.append("*");
            }
            msg = msg.substring(0,1)+stringBuilder.toString();
        }
        if("1".equals(type)){
            //身份证
            if(msg.length()>18){
                try {
                    msg = Sm4Util.decryptEcb(sm4Key, msg);
                } catch (Exception e) {
                    e.printStackTrace();
                    //log.info("sm4解密身份证信息失败：{}",msg);
                }
            }
            for(int i=0;i<msg.length()-7;i++){
                stringBuilder.append("*");
            }
            msg = msg.substring(0,3)+stringBuilder.toString()+msg.substring(msg.length()-4);
        }
        if("2".equals(type)){
            //手机号
            msg =msg.substring(0,3)+"****"+msg.substring(msg.length()-4);
        }
        return msg;
    }

    /**
     * 原始信息脱敏
     * @param msg
     * @param type
     * @return
     */
    public static String tuominWithoutDecrypt(String msg,String type){
        StringBuilder stringBuilder = new StringBuilder();
        if("0".equals(type)){
            //姓名
            for(int i=0;i<msg.length()-1;i++){
                stringBuilder.append("*");
            }
            msg = msg.substring(0,1)+stringBuilder.toString();
        }
        if("1".equals(type)){
            //身份证
            for(int i=0;i<msg.length()-7;i++){
                stringBuilder.append("*");
            }
            msg = msg.substring(0,3)+stringBuilder.toString()+msg.substring(msg.length()-4);
        }
        if("2".equals(type)){
            //手机号
            msg =msg.substring(0,3)+"****"+msg.substring(msg.length()-4);
        }
        return msg;
    }

}
