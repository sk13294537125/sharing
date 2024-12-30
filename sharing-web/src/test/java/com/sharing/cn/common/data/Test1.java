package com.sharing.cn.common.data;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.jasypt.util.text.AES256TextEncryptor;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;

/**
 * @author wangwei
 * @version 2024/1/12
 */
public class Test1 {

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
     * 报文加密
     * @throws UnsupportedEncodingException
     * @throws ParseException
     */
    @Test
    public void body() throws UnsupportedEncodingException, ParseException {
        AesEncryptHandler handler = new AesEncryptHandler();
        // 密钥（配置文件中）
        handler.setSecret("efe86adbbcbcd7e0114ae7ff59fed750");

        String orderList = "{\n" +
                "  \"orderId\":\"17321533350086\",\n" +
                "  \"subOrderId\":[\"173215333502667628\"]\n" +
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
//        AES256TextEncryptor textEncryptor = new AES256TextEncryptor();
//        //加解密使用的秘钥，项目部署时需要配置到启动脚本或者环境变量
//        textEncryptor.setPassword("PKHf6VjaX2ln8TG14EIBuy59");
////        textEncryptor.setPassword("KS$T3Eon9uaC@P4SErwS7F5f");
//        //要加密的数据（数据库的用户名或密码）
//        String username = textEncryptor.encrypt("vXLEyUR2$BwSt&gawpeA%7AY");
//        String password = textEncryptor.encrypt("bmacapi");
//        String password1 = textEncryptor.encrypt("jH|9D-RxUBAaU+rXjqnFw");
//        System.out.println("加密后的username:"+username);
//        System.out.println("加密后的password:"+password);
//        System.out.println("加密后的password:"+password1);
//        String s = "5fjQ/ZpcIuVlSOdFLNmxcXNUh6iMDs9MNiK1jqJG/xN/uMksvsNTyKc9x9iL+NI2";
////        String s1 = "knpKV6fQgsVFKYmE2g4y6s89P/H/zp5QGzcy3P5wwgSWSNZuw6HXXYhbJYt5XuVf";
//        String decrypt = textEncryptor.decrypt(s);
////        String decrypt1 = textEncryptor.decrypt(s1);
//        System.out.println(decrypt);
////        System.out.println(decrypt1);


        AES256TextEncryptor textEncryptor = new AES256TextEncryptor();
        //加解密使用的秘钥，项目部署时需要配置到启动脚本或者环境变量
        textEncryptor.setPassword("bmac1234");
        //要加密的数据（数据库的用户名或密码）
        String username = textEncryptor.encrypt("pay");
        String password = textEncryptor.encrypt("12345678");
        System.out.println("加密后的username:"+username);
        System.out.println("加密后的password:"+password);
        String s = "knpKV6fQgsVFKYmE2g4y6s89P/H/zp5QGzcy3P5wwgSWSNZuw6HXXYhbJYt5XuVf";
        String decrypt = textEncryptor.decrypt(s);
        System.out.println(decrypt);
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


    public static void main(String[] args) {
        String begin = "12:00";
        String substring = begin.substring(0, 2);
        System.out.println(substring);

    }


}
