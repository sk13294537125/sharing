package com.sharing.cn.common.encrypt;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.jasypt.util.text.AES256TextEncryptor;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

/**
 * @author wangwei
 * @version 2024/1/12
 */
public class EncEncrypt {

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


        AES256TextEncryptor textEncryptor = new AES256TextEncryptor();
        //加解密使用的秘钥，项目部署时需要配置到启动脚本或者环境变量
        // 启动脚本配置 -Djasypt.encryptor.password=Bt%XJ^n1j8mz
//        String jasyptKey = "Bt%XJ^n1j8mz"; // wolf-生产
//        String jasyptKey = "Bt%XJ^n1j8mz"; // wolf-测试环境
//        String jasyptKey = "Bt%XJ^81ngjmz"; // 商户管理平台测试环境
//        String jasyptKey = "BJX&t^18ngjmz"; // 商户管理平台-生产
//        String jasyptKey = "KS$T3Eon9uaC@P4SErwS7F5f"; // 天机-测试环境
//        String jasyptKey = "KS$T3Eon9uaC@P4SErwS7F5f"; // 天机-生产
        String jasyptKey = "KS$T3Eon9uaC@P4SErwS7F5f";// 三重一大生产
//        String jasyptKey = "KS$T3Eon9uaC@P4SErwS7F5f";// 三重一大测试环境
        textEncryptor.setPassword(jasyptKey);
        //要加密的数据（数据库的用户名或密码）
//        String key = textEncryptor.encrypt("Bfhg@2018@2025");
        String key = textEncryptor.encrypt("68bbf2f6bf72019c069bffc369afb0366d9a67e38a47f2db0a2c7b63d3fb5509");
        System.out.println("加密后的:" + key);
        String s = "5AT27c5NYzUXcCUrYNYU+nQ6GQM+bH/2PmIagSr4BIUT2zzWwKPubyp9t7a/p3bsdYrA0ejxAWksXCgK3QSTlmUp5KBz0PJ0D9FFjU3H+V8=";
        String decrypt = textEncryptor.decrypt(s);
        System.out.println("解密后的:" + decrypt);

        // 三重一大 encrypt.sm4Key：E99567ACC5364D69BA0B0BC83066955F
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
