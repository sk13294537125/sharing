package com.sharing.cn.common.encrypt;

import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author wangwei
 * @version 2024/1/12
 */
public class AesEncryptHandler {

    public void setSecret(String secret) {
        this.secret = secret;
    }
    private String secret;

    private static final String VIPARA = "0102030405060708";
    private static final String KEY_ALGORITHM = "AES";

    public byte[] encode(byte[] b) {
        try {
            IvParameterSpec zeroIv = new IvParameterSpec(VIPARA.getBytes());
            SecretKeySpec key = new SecretKeySpec(secret.getBytes(), KEY_ALGORITHM);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
            byte[] byte_AES = cipher.doFinal(b);
            return Base64Utils.encode(byte_AES);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    public byte[] decode(byte[] b) {
        try {
            IvParameterSpec zeroIv = new IvParameterSpec(VIPARA.getBytes());
            SecretKeySpec key = new SecretKeySpec(secret.getBytes(), KEY_ALGORITHM);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
            byte[] byte_content = Base64Utils.decode(b);
            byte[] byte_decode = cipher.doFinal(byte_content);
            return byte_decode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }
}
