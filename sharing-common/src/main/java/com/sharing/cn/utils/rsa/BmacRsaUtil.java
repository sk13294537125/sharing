package com.sharing.cn.utils.rsa;

import com.alibaba.fastjson.JSONObject;
import com.sharing.cn.utils.Base64Util;
import com.sharing.cn.utils.md5.MD5Util;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class BmacRsaUtil {

     public static final String SIGN = "sign";


    public static Boolean signVerify(String reqMsg, String modulus)  {
        
        String msg = reqMsg;
        log.info("---------------进入验证------------请求报文："+ msg);
        Map<String,String> mapAll = JSONObject.parseObject(msg, HashMap.class);
        log.info("-----------------请求转化后的map集合:"+mapAll);
        String sign =  mapAll.get(SIGN);
        mapAll.remove(SIGN);
        log.info("---------进入加签工具类，请求的参数排序前map--------" + mapAll);
        Map map1 = MapHashtableUtil.signForInspiry(mapAll);
        log.info("--------------------------请求参数排序后：" + map1);
        String stringMap = PackRsaSignSrc.packRsaSignSrc(map1);
        log.info("---------------------------请求参数变成&连接:"+stringMap);

        boolean flag  = false;
        //验签
        try {
            flag = RsaUtil.verifySHA1SignatureBase64(stringMap.getBytes("utf-8"), sign,modulus);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static Boolean yanQian1(String reqMsg,String modulus)  {

        String msg = reqMsg;
        log.info("---------------进入验证------------请求报文："+ msg);
        Map<String,String> mapAll = JSONObject.parseObject(msg, HashMap.class);
        log.info("-----------------请求转化后的map集合:"+mapAll);
        String sign =  mapAll.get(SIGN);
        mapAll.remove(SIGN);

        log.info("---------进入加签工具类，请求的参数排序前map--------" + mapAll);
        Map map1 = MapHashtableUtil.signForInspiry(mapAll);
        log.info("--------------------------请求参数排序后：" + map1);
        String stringMap = PackRsaSignSrc.packRsaSignSrc(map1);
        log.info("---------------------------请求参数变成&连接:"+stringMap);
        //导入公钥
        RsaUtil.loadPublicKey(modulus);

        // PublicKey pubKey = RSACryptography.getPubKey(modulus);
        //  byte[] decode = stringMap.getBytes();

        boolean flag  = false;
        String md5 = MD5Util.md5(stringMap);
        //验签
        try {
            flag = RsaUtil.verifySHA1SignatureBase64(md5.getBytes("utf-8"), sign, modulus);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * host验签
     * @param retJson
     * @param hostsmodulus
     * @param sign
     * @return
     */
    /**
     }
     * @param retJson
     * @param hostsmodulus
     * @param sign
     * @return
     * @throws UnsupportedEncodingException
     */
    public static boolean yanQianHost(String retJson, String hostsmodulus, String sign) throws UnsupportedEncodingException {
        String msg = retJson;
        log.info("---------------进入验证------------请求报文："+ msg);
        //导入公钥
        byte[] decode = Base64Util.decode(hostsmodulus);
        byte[] decodeSign = Base64Util.decode(sign);
        //验签
        boolean verify = RSA.verify(retJson.getBytes(), decode, decodeSign);
        return verify;

    }
}
