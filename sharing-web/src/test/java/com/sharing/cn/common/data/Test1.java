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
        String enStr = "/K5oGk6mrkEshHoUL+O2EjIvsIw2xPvziCdnG5qowwLe7/XUNCy97bX5L2HuU1i54OqXuWQMN42IeGvzHdnVdm0ut5nQTzZFGMbmNxU/qTA=";
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

        String orderList = "{\"password\":\"JQmWIjbDS53sCBWD^hBtGhxH\",\"otpCode\":\"554137\",\"account\":\"shikai\"}\n";

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
//        String jasyptKey = "Bt%XJ^n1j8mz";
        String jasyptKey = "KS$T3Eon9uaC@P4SErwS7F5f";// 三重一大生产
        textEncryptor.setPassword(jasyptKey);
        //要加密的数据（数据库的用户名或密码）
        String key = textEncryptor.encrypt("cb1eb5b01bac6ae7f30f14a05e86598b2812486e2ea2137ea4cadda6d8fb3f0a");
        System.out.println("加密后的:" + key);
        String s = "HgSQTmOIE+VwqOxP8W75ll04NW8QMkmIYIfInP5zXk2+nzI8duZjz5aqL0qYhSbCm1pfnCjT0KgCWfe3C4EhxQ==";
        String decrypt = textEncryptor.decrypt(s);
        System.out.println("解密后的:" + decrypt);
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
