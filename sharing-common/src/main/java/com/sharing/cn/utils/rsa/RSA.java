package com.sharing.cn.utils.rsa;


import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * modify by colingzwang on 2018-06-20 18:20.
 * Email: 287712709@qq.com
 */

public class RSA {
    /**
     * 密钥算法
     */
    private static final String KEY_ALGORITHM = "RSA";

    /**
     * 签名算法
     */
    private static final String SIGNATURE_ALGORITHM = "SHA1withRSA";


    /**
     * 通过公钥byte[](publicKey.getEncoded())将公钥还原，适用于RSA算法
     *
     * @param keyBytes
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws NoSuchProviderException
     */
    /*package*/ static PublicKey getPublicKey(byte[] keyBytes) throws NoSuchAlgorithmException,
            InvalidKeySpecException, NoSuchProviderException
    {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM, "BC");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    /**
     * 通过私钥byte[]将私钥还原，适用于RSA算法
     *
     * @param keyBytes
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws NoSuchProviderException
     */
    /*package*/ static PrivateKey getPrivateKey(byte[] keyBytes) throws NoSuchAlgorithmException,
            InvalidKeySpecException, NoSuchProviderException
    {
        Security.addProvider(new BouncyCastleProvider());
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM, "BC");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    /**
     * <p>
     * 用用户私钥对信息生成数字签名
     * </p>
     *
     * @param data 已加密数据
     *
     * @return
     * @throws Exception
     */
    /*package*/ static byte[] sign(byte[] data, byte[] key_bytes) {
        try {
            if (key_bytes != null) {
                PrivateKey privateCrtKey = getPrivateKey(key_bytes);
                Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
                signature.initSign(privateCrtKey);
                signature.update(data);
                return signature.sign();
            }

            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * <p>
     * 用服务器公钥校验数字签名
     * </p>
     *
     * @param data 已加密数据
     * @param keyByte 公钥
     * @param sign 数字签名
     *
     * @return
     * @throws Exception
     *
     */
    public static boolean verify(byte[] data, byte[] keyByte, byte[] sign) {
        try {
            RSAPublicKey publicKey = (RSAPublicKey) getPublicKey(keyByte);
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initVerify(publicKey);
            signature.update(data);
            return signature.verify(sign);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 默认加密填充方式
     */
    private static final String DEFAULT_PADDING_ALGORITHM = "RSA/ECB/PKCS1Padding";

    /**
     * 分块长度
     */
    private static final int DEFAULT_BLOCK_SIZE = 128;

    static byte[] decryptData(byte[] encryptedData, byte[]pri_key)
    {
        try
        {
            int enc_len = encryptedData.length;
            byte[] tmp = new byte[DEFAULT_BLOCK_SIZE];
            byte[] result = new byte[enc_len];
            int nLen = 0;
            int nPos = 0;
            Cipher cipher = Cipher.getInstance(DEFAULT_PADDING_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, getPrivateKey(pri_key));
            while (enc_len > DEFAULT_BLOCK_SIZE) {
                System.arraycopy(encryptedData, nPos, tmp, 0, DEFAULT_BLOCK_SIZE);
                byte[] ret = cipher.doFinal(tmp);
                System.arraycopy(ret, 0, result, nLen, ret.length);
                enc_len -= DEFAULT_BLOCK_SIZE;
                nLen += ret.length;
                nPos += DEFAULT_BLOCK_SIZE;
            }
            System.arraycopy(encryptedData, nPos, tmp, 0, DEFAULT_BLOCK_SIZE);
            byte[] ret = cipher.doFinal(tmp);
            System.arraycopy(ret, 0, result, nLen, ret.length);
            nLen += ret.length;
            if (nLen < encryptedData.length) {
                byte[] src = new byte[nLen];
                System.arraycopy(result, 0, src, 0, nLen);
                return src;
            }
            return result;
        } catch (Exception e)
        {
            return null;
        }
    }
}
