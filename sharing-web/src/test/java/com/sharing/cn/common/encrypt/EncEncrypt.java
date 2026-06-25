package com.sharing.cn.common.encrypt;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.iv.RandomIvGenerator;
import org.jasypt.util.text.AES256TextEncryptor;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 */
public class EncEncrypt {

    @Test
    public void deco() throws UnsupportedEncodingException, ParseException {
        AesEncryptHandler handler = new AesEncryptHandler();
        // 密钥（配置文件中）
        handler.setSecret("efe86adbbcbcd7e0114ae7ff59fed750");
        // 解密
        String enStr = "MfB9m6IWOKxRqb+cdWQghkqsQCvMqkg1HUCSYkHOqqBE/BRDPNAcWDJXUnWyFCisFcUsFgnYAFQrQRqNi2OZTshEJd44ewCVATMt0L4FB7QcUzzITb7omm6W0t3ws7/tkRSaXsIymJXIgNJRRdCcY3FDa294SnL0ZeHAbdglmcBKI6NJbKZM6jj4nR75RgUNEVWMl3lTlVsAP06HmHwGNw==";
        byte[] decode = handler.decode(enStr.getBytes());
        String urlDecodeStr = URLDecoder.decode(new String(decode), "UTF-8");
        System.out.println(urlDecodeStr);
    }
    /**
     * 报文加密 aes
     * @throws UnsupportedEncodingException
     * @throws ParseException
     */
    @Test
    public void body() throws UnsupportedEncodingException, ParseException {
        AesEncryptHandler handler = new AesEncryptHandler();
        // 密钥（配置文件中）
        handler.setSecret("efe86adbbcbcd7e0114ae7ff59fed750");

        String orderList = "{\n" +
                "    \"system\":\"dh\",\n" +
                "    \"order\":\"2\",\n" +
                "    \"warningContent\":\"动环告警-只发送快猫4-28 14:09\"\n" +
                "}";

        // 加密
        JSONObject jsonObject = JSON.parseObject(orderList);
        System.out.println(jsonObject.toString());
        byte[] encode = handler.encode(jsonObject.toString().getBytes(StandardCharsets.UTF_8));
        String encodeStr = new String(encode);
        System.out.println(encodeStr);


    }

    /**
     * enc配置加密
     */
    @Test
    public void enc() {
        AES256TextEncryptor textEncryptor = new AES256TextEncryptor();
        //加解密使用的秘钥，项目部署时需要配置到启动脚本或者环境变量
        // 启动脚本配置 -Djasypt.encryptor.password=Bt%XJ^n1j8mz
        String jasyptKey = "Bt%XJ^n1j8mz"; // wolf-生产
//        String jasyptKey = "Bt%XJ^n1j8mz"; // wolf-测试环境
//        String jasyptKey = "Bt%XJ^81ngjmz"; // 商户管理平台测试环境
//        String jasyptKey = "BJX&t^18ngjmz"; // 商户管理平台-生产
//        String jasyptKey = "KS$T3Eon9uaC@P4SErwS7F5f"; // 天机-测试环境
//        String jasyptKey = "KS$T3Eon9uaC@P4SErwS7F5f"; // 天机-生产
//        String jasyptKey = "KS$T3Eon9uaC@P4SErwS7F5f";// 三重一大生产
//        String jasyptKey = "KS$T3Eon9uaC@P4SErwS7F5f";// 三重一大测试环境
//        String jasyptKey = "KS$T3Eon9uaC@PESErwS7F6K";// 商户外放管理平台测试环境
//        String jasyptKey = "KS$T3Eon9uaC@PPRErwS7F6K";// 商户外放管理平台生产环境
//        String jasyptKey = "PKHf6VjaX2ln8TG14EIBuy59";// 天机 tj-infocollect-qjs、bus 测试
//        String jasyptKey = "Ax%XJ^81mklfqs";// 清算昆仑测试环境
//        String jasyptKey = "Bt%XJ^n1j8mz";// 清算昆仑生产环境
        textEncryptor.setPassword(jasyptKey);
        //要加密的数据（数据库的用户名或密码）
        String key = textEncryptor.encrypt("3f0dbac497ba54559fbf9000cbc003e3611");
        System.out.println("加密后的:" + key);
        String s = "JEOG2IqVR9hiJzVjyRCyX2iSnW5N1gpw5xdK7mcnQDW2nWEsfhBhKDYSroen/F1wcplTK5n+3lYh98x3UPRAzw==";
        String decrypt = textEncryptor.decrypt(s);
        System.out.println("解密后的:" + decrypt);

        // 三重一大生产 encrypt.sm4Key：E99567ACC5364D69BA0B0BC83066955F
        // 三重一大测试 encrypt.sm4Key：E99567ACC5364D69BA0B0BC83066955F
        // 三重一大测试金蝶同步  encrypt.kingdeeSm4Key：A81954ACC5364D69BA0ADCADC839875F
        // 三重一大生产 - 泛微同步  encrypt.weaverSm4Key：A71402B0F4A141DB59CF38AA268576CE
        // sms 测试 86C63180C2806ED1F47B859DE501215B
        // sms 生产 86C63180C2806ED1F47B859DE501215B
        // 统一管理平台加密秘钥
        // 测试：AB4CC41572CFD5F25135A55B0DBF6DD9
        // 生产：A1242474E4AB4D0D0C8F33186E7BE132
    }

    /**
     * 云卡平台enc加密
     */
    @Test
    public void encSms() {
        StandardPBEStringEncryptor textEncryptor = new StandardPBEStringEncryptor();
        String jasyptKey = "Bt%XJ^n1j8mz"; // sms-测试
//        String jasyptKey = "Bt%XJ^n1j8mz"; // sms-生产
        String algorithm = "PBEWithMD5AndDES";
        textEncryptor.setPassword(jasyptKey);
        textEncryptor.setAlgorithm(algorithm);
        String key = textEncryptor.encrypt("86C63180C2806ED1F47B859DE501215B");
        System.out.println("加密后的:" + key);
        String s = "JEOG2IqVR9hiJzVjyRCyX2iSnW5N1gpw5xdK7mcnQDW2nWEsfhBhKDYSroen/F1wcplTK5n+3lYh98x3UPRAzw==";
        String decrypt = textEncryptor.decrypt(s);
        System.out.println("解密后的:" + decrypt);
    }

    /**
     * 尝试解密jasypt密文，找出正确的算法
     */
    @Test
    public void mainJasypt() {
        String password = "Bt%XJ^n1j8mz";
        String encrypted = "kxvShXt1I1somWD2/ahfVfYP0FVEW4t4idyPoPxlSV9HaIOOLCYpBA==";

        System.out.println("测试解密...");
        System.out.println("密码: " + password);
        System.out.println("密文长度: " + encrypted.length());

        // 尝试不同的算法
        String[] algorithms = {
                "PBEWithMD5AndDES",
                "PBEWithMD5AndTripleDES",
                "PBEWithSHA1AndDESede",
                "PBEWithSHA1AndRC2_40",
                "PBEWithHMACSHA1AndAES_128",
                "PBEWithHMACSHA1AndAES_256",
                "PBEWithHMACSHA224AndAES_128",
                "PBEWithHMACSHA224AndAES_256",
                "PBEWithHMACSHA256AndAES_128",
                "PBEWithHMACSHA256AndAES_256",
                "PBEWithHMACSHA384AndAES_128",
                "PBEWithHMACSHA384AndAES_256",
                "PBEWithHMACSHA512AndAES_128",
                "PBEWithHMACSHA512AndAES_256"  // 最可能
        };

        for (String algorithm : algorithms) {
            try {
                StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
                encryptor.setPassword(password);
                encryptor.setAlgorithm(algorithm);
                encryptor.setIvGenerator(new RandomIvGenerator());

                String decrypted = encryptor.decrypt(encrypted);
                System.out.println("\n✅ 成功！算法: " + algorithm);
                System.out.println("解密结果: " + decrypted);
                System.out.println("长度: " + decrypted.length());

                // 判断结果
                if (decrypted.length() == 16 || decrypted.length() == 32) {
                    System.out.println("看起来是SM4密钥");
                }
                return;
            } catch (Exception e) {
                System.out.println("失败 - 算法: " + algorithm + " - " + e.getClass().getSimpleName());
            }
        }
        System.out.println("\n所有算法都失败了！");
    }

    /**
     * 天机加密
     */
    @Test
    public void tianji() {
        String s = CommonCryptogramUtil.doSm4CbcEncrypt("13994537125");
        System.out.println(s);
//        String s2 = CommonCryptogramUtil.doSm4CbcDecrypt(s);
        String s2 = "069f2fc7dcd49bb892bc7ec18565ad98";
        System.out.println(s2);
        String s1 = CommonCryptogramUtil.doHashValue("tj123456!@#");
        System.out.println(s1);

    }



}
