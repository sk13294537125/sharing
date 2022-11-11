package com.sharing.cn.utils;

import org.apache.commons.lang3.StringUtils;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;


/**
 * 功能描述: 廉洁黑名单加解密工具
 * @author wangyonggang3
 * @version 1.0
 * @date 2022年10月14日 16:20
 */
public class AesUtil {


    public static String encrypt(String sSrc,String key) throws Exception {

        if (key == null) {
            System.out.print("加解密KEY不能为null");
            return null;
        }
        if(StringUtils.isEmpty(sSrc)){
            System.out.print("待加密字段不能为null");
            return "";
        }

        byte[] raw = key.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes(StandardCharsets.UTF_8));
        return byte2hex(encrypted).toLowerCase();
    }

    public static byte[] hex2byte(String strhex) {

        if (strhex == null) {
            return null;
        }
        int l = strhex.length();
        if (l % 2 == 1) {
            return null;
        }
        byte[] b = new byte[l / 2];
        for (int i = 0; i != l / 2; i++) {
            b[i] = (byte) Integer.parseInt(strhex.substring(i * 2, i * 2 + 2),
                    16);
        }
        return b;
    }

    public static String byte2hex(byte[] b) {

        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs.toUpperCase();
    }

    public static String decrypt(String sSrc,String key) throws Exception {

        try {
            if (key == null) {
                System.out.print("加解密KEY不能为null");
                return null;
            }
            byte[] raw = key.getBytes(StandardCharsets.UTF_8);
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] encrypted1 = hex2byte(sSrc);
            try {
                byte[] original = cipher.doFinal(encrypted1);
                return new String(original, StandardCharsets.UTF_8);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


    public static void main(String[] args) throws Exception {
        String key32 = "2e53ef6fe3fd445ab3cf471ea1210de4";
        String data = "ext.shikai1";
        String jiaMi = encrypt(data, key32);
        System.out.println("encrypt:" + jiaMi);
        //String data = "a12e412854a22181a8f0b2821c49bc83080268de22e3cbfcbcf62a0750f88c24add8bd86a75be5227d8d8421811f5e201285e66dc857e55fcbe5d4bb12355aba2a59f54bef975373562a232c849f58c8121a74ce11f72a37bc5acc7db4a91281";
        //System.out.println("decrypt" + decrypt(data, key32));

    }
}