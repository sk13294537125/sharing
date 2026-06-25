package com.sharing.cn.common.encrypt;

import com.alibaba.fastjson.JSONObject;
import com.sharing.cn.utils.rsa.BmacRsaUtil;
import com.sharing.cn.utils.rsa.MapHashtableUtil;
import com.sharing.cn.utils.rsa.PackRsaSignSrc;
import com.sharing.cn.utils.rsa.RsaUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class RSAUtils {
     // 生成 RSA 密钥对
     @Test
     public void generateRSAKeyPair() throws Exception {
          KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
          keyPairGenerator.initialize(2048); // 推荐 2048 或 4096
          KeyPair keyPair = keyPairGenerator.generateKeyPair();

          PublicKey publicKey = keyPair.getPublic();
          PrivateKey privateKey = keyPair.getPrivate();

          // 将密钥转为 Base64 字符串存储
          String publicKeyStr = Base64.getEncoder().encodeToString(publicKey.getEncoded());
          String privateKeyStr = Base64.getEncoder().encodeToString(privateKey.getEncoded());
          System.out.println("公钥: " + publicKeyStr);
          System.out.println("私钥: " + privateKeyStr);
     }

     @Test
     public void packageMapMsg() {
          String exponent = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwrwTsIrtNb5IaaS9g5fqPIBNNRcaY8mVGkOFWPALEIMPRr8q26YAoxLQQTcqE8gXWksrpzXDATioTkEefv4kfuLW1eq1bHMJGDB/fQRklC34HSGS4CRIvdI9ImcPK9inCt5i2cgJaXhtyGHszpV2Vpo7nGMkN+FLGOGj+uUUn+1aUeiRZHmUTqMfwZEc8jjmsMPUTlRWZpQsA0W0xlTx+fgWyZo0fTLzkf38UNQuDSNm0SfqIyhdsOHaqzRZC8Fij2HoX6iUPOkqMcoefpfidK/66/JQI9FABbPCnJB9AH0itn3r+et2uhp82guHgEGDrPYsXipmiP8q+LvdFG3L8wIDAQAB";
          Map req = new HashMap<>(20);
          req.put("carImage", "");
          req.put("charset", "UTF-8");
          req.put("inRegionNo", "11022830001101");
          req.put("ingateNo", "1102283000110101");
          req.put("inTime", "20260518180000");
          req.put("parkCode","110228300011");
          req.put("plateColor","1");
          req.put("plateNo","沪YKT001");
          req.put("seqNumber","623bb3a7d5ed4b789dffeb8c81ceee69");
          req.put("serialNo","1110228300011260518180000001");
          req.put("source","jtjt");
          req.put("timestamp","2026-05-18 18:00:00");
          req.put("version","1");
          System.out.println("---------进入加签工具类，请求的参数排序前map--------" + req);
          Map<Object, Object> returnMsg = new HashMap<>(20);
          Map map1 = MapHashtableUtil.signForInspiry(req);
          System.out.println("--------------------------请求参数排序后：" + map1);
          String map = PackRsaSignSrc.packRsaSignSrc(map1);
          System.out.println("---------------------------请求参数变成&连接:" + map);
          String signValue = "";
          try {
               System.out.println("--------------------------加密参数之前的参数：" + map);
               signValue = RsaUtil.getSHA1SignatureBase64(map, exponent);
               System.out.println("---------------：" + signValue);
          } catch (Exception e) {
               log.error("签名失败：", e.getMessage());
          }
          returnMsg.putAll(req);
          returnMsg.put("sign", signValue);
          System.out.println("---------------加签结果为" + JSONObject.toJSONString(returnMsg));

          String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDMPGIXdzYDjS9Ur1Z+JFSabKpCsCwvO4jgBZo3hnQUxGoJZGva8KG8da/sKBZZBB+DAnlah7aLUc2osJN7eNG7XVkBTRrna8k//YZbo/eCot/G3eJEi5DevB0T4vdhe5RB0cEuHOzwazfeos7fDEkesxJrR/TleVZalKHSUzMSaQIDAQAB";
          String param = JSONObject.toJSONString(returnMsg);
          Boolean b = BmacRsaUtil.signVerify(param, publicKey);
          System.out.println("---------------验签结果为" + b);
     }


}
