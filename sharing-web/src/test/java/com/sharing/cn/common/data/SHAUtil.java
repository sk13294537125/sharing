package com.sharing.cn.common.data;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHAUtil {
    public static String Encrypt(String strSrc, String encName) {
        MessageDigest md = null;
        String strDes = null;

        byte[] bt = strSrc.getBytes();
        try {
            if ((encName == null) || (encName.equals("")))
                encName = "SHA-256";

            md = MessageDigest.getInstance(encName);
            md.update(bt);
            strDes = bytes2Hex(md.digest());
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        return strDes;
    }

    public static String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; ++i) {
            tmp = Integer.toHexString(bts[i] & 0xFF);
            if (tmp.length() == 1)
                des = des + "0";

            des = des + tmp;
        }
        return des;
    }

    public static void main(String[] args) {
        String s = Encrypt("2222220000000000598", "");
        System.out.println(s);
    }
}