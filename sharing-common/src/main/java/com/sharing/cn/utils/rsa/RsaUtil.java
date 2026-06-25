package com.sharing.cn.utils.rsa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Base64.Decoder;


public class RsaUtil {
	private static final Logger LOG = LoggerFactory.getLogger(RsaUtil.class);

	private static RSAPrivateKey privateKey;

	private static RSAPublicKey publicKey;

	public static RSAPrivateKey getPrivateKey() {
		return RsaUtil.privateKey;
	}


	public static RSAPublicKey getPublicKey() {
		return RsaUtil.publicKey;
	}


	public static void loadPublicKey(InputStream in){
		try {
			BufferedReader br= new BufferedReader(new InputStreamReader(in));
			String readLine= null;
			StringBuilder sb= new StringBuilder();
			while((readLine= br.readLine())!=null){
				if(readLine.charAt(0)=='-'){
					continue;
				}else{
					sb.append(readLine);
					sb.append('\r');
				}
			}
			loadPublicKey(sb.toString());
		} catch (IOException e) {
			LOG.info(String.format("IOException: [errCode: empty], [errMsg: %s]", e.getMessage()));
		} catch (NullPointerException e) {
			LOG.info(String.format("NullPointerException: [errCode: empty], [errMsg: %s]", e.getMessage()));
		}
	}

	public static void loadPublicKey(String publicKeyStr){
		try {
			Decoder decoder = Base64.getDecoder();
			byte[] buffer= decoder.decode(publicKeyStr);
			KeyFactory keyFactory= KeyFactory.getInstance("RSA");
			X509EncodedKeySpec  keySpec= new X509EncodedKeySpec (buffer);
			publicKey= (RSAPublicKey) keyFactory.generatePublic(keySpec);
		} catch (NoSuchAlgorithmException e) {
			LOG.info(String.format("IOException: [errCode: empty], [errMsg: %s]", e.getMessage()));
		} catch (InvalidKeySpecException e) {
			LOG.info(String.format("InvalidKeySpecException: [errCode: empty], [errMsg: %s]", e.getMessage()));
		} catch (NullPointerException e) {
			LOG.info(String.format("NullPointerException: [errCode: empty], [errMsg: %s]", e.getMessage()));
		}
	}

	public static void loadPublicPem(InputStream in){
		try {
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(stream2ByteArray(in));
			publicKey = (RSAPublicKey) keyFactory.generatePublic(keySpec);
		} catch (NoSuchAlgorithmException e) {
			LOG.info(String.format("NoSuchAlgorithmException: [errCode: empty], [errMsg: %s]", e.getMessage()));
		} catch (InvalidKeySpecException e) {
			LOG.info(String.format("InvalidKeySpecException: [errCode: empty], [errMsg: %s]", e.getMessage()));
		} catch (NullPointerException e) {
			LOG.info(String.format("NullPointerException: [errCode: empty], [errMsg: %s]", e.getMessage()));
		}
	}


	public static void loadPrivateKey(InputStream in){
		try {
			BufferedReader br= new BufferedReader(new InputStreamReader(in));
			String readLine= null;
			StringBuilder sb= new StringBuilder();
			while ((readLine= br.readLine()) != null) {
				if (readLine.charAt(0)=='-') {
					continue;
				} else {
					sb.append(readLine);
					sb.append('\r');
				}
			}
			loadPrivateKeyByStr(sb.toString());
		} catch (IOException e) {
			LOG.info(String.format("IOException: [errCode: empty], [errMsg: %s]", e.getMessage()));
		} catch (NullPointerException e) {
			LOG.info(String.format("NullPointerException: [errCode: empty], [errMsg: %s]", e.getMessage()));
		}
	}

	public static RSAPrivateKey loadPrivateKeyByStr(String privateKeyStr){

		try {
			LOG.info("privateKeyStr:"+privateKeyStr);
			Decoder decoder = Base64.getDecoder();
			byte[] buffer= decoder.decode(privateKeyStr);
			//byte[] buffer = Base64.getDecoder().decode(privateKeyStr);
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			RsaUtil.privateKey = (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
		} catch (NoSuchAlgorithmException e) {
			LOG.info(String.format("NoSuchAlgorithmException: [errCode: empty], [errMsg: %s]", e.getMessage()));
		} catch (InvalidKeySpecException e) {
			LOG.info(String.format("InvalidKeySpecException: [errCode: empty], [errMsg: %s]", e.getMessage()));
		} catch (NullPointerException e) {
			LOG.info(String.format("NullPointerException: [errCode: empty], [errMsg: %s]", e.getMessage()));
		}
		return RsaUtil.privateKey;
	}

	public static void loadPrivatePem(InputStream in){
		try {
			PKCS8EncodedKeySpec keySpec= new PKCS8EncodedKeySpec(stream2ByteArray(in));
			KeyFactory keyFactory= KeyFactory.getInstance("RSA");
			RsaUtil.privateKey = (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
		} catch (NoSuchAlgorithmException e) {
			LOG.info(String.format("NoSuchAlgorithmException: [errCode: empty], [errMsg: %s]", e.getMessage()));
		} catch (InvalidKeySpecException e) {
			LOG.info(String.format("InvalidKeySpecException: [errCode: empty], [errMsg: %s]", e.getMessage()));
		} catch (NullPointerException e) {
			LOG.info(String.format("NullPointerException: [errCode: empty], [errMsg: %s]", e.getMessage()));
		}
	}

	//加签
	public static String getSHA1SignatureBase64(byte[] data,String tcexponent){
		PrivateKey priKey = null;
		System.out.println("==================================应用的配置文件得到的私钥==================================="+tcexponent);
		String sign = null;
		try {
			priKey = RSACryptography.getPrivateKey(tcexponent);
			Signature sig = Signature.getInstance("SHA1withRSA");
			sig.initSign(priKey);
			sig.update(data);
			sign = Base64.getEncoder().encodeToString(sig.sign());
		} catch (NoSuchAlgorithmException e) {
			LOG.info(String.format("NoSuchAlgorithmException: [errCode: empty], [errMsg: %s]", e.getMessage()));
		} catch (InvalidKeyException e) {
			LOG.info(String.format("InvalidKeyException: [errCode: empty], [errMsg: %s]", e.getMessage()));
		} catch (SignatureException e) {
			LOG.info(String.format("SignatureException: [errCode: empty], [errMsg: %s]", e.getMessage()));
		} catch (Exception e) {
			LOG.info(String.format("Exception: [errCode: empty], [errMsg: %s]", e.getMessage()));
		}
		return sign;
	}

	//加签
	public static String getMd5WithRsaSignatureBase64(byte[] data){
		String sign = null;
		try {
			Signature sig = Signature.getInstance("MD5withRSA");
			sig.initSign(privateKey);
			sig.update(data);
			sign = Base64.getEncoder().encodeToString(sig.sign());
		} catch (NoSuchAlgorithmException e) {
			LOG.info(String.format("NoSuchAlgorithmException: [errMsg: %s]", e.getMessage()));
		} catch (InvalidKeyException e) {
			LOG.info(String.format("InvalidKeyException: [errMsg: %s]", e.getMessage()));
		} catch (SignatureException e) {
			LOG.info(String.format("SignatureException: [errMsg: %s]", e.getMessage()));
		} catch (Exception e) {
			LOG.info(String.format("Exception: [errMsg: %s]", e.getMessage()));
		}
		return sign;
	}

	//加签
	public static String getMd5WithRsaSignatureBase64(String signSrc){
		String sign = null;
		try {
			sign = getMd5WithRsaSignatureBase64(signSrc.getBytes("utf-8"));
		} catch (Exception e) {
			LOG.info(String.format("Exception: [errCode: empty], [errMsg: %s]", e.getMessage()));
		}
		return sign;
	}

	//加签
	public static String getSHA1SignatureBase64(String signSrc,String tcexponent){
		String sign = null;
		LOG.info(String.format("sign src: %s", signSrc));
		try {
			sign = getSHA1SignatureBase64(signSrc.getBytes("utf-8"),tcexponent);
		} catch (Exception e) {
			LOG.info(String.format("Exception: [errCode: empty], [errMsg: %s]", e.getMessage()));
		}
		return sign;
	}


	//验签
	public static boolean verifySHA1SignatureBase64(byte[] signSrc, byte[] signature,String modulus1){
		boolean result = false;
		PublicKey pubKey = RSACryptography.getPubKey(modulus1);
		try {
			Signature sig = Signature.getInstance("SHA1withRSA");
			sig.initVerify(pubKey);
			sig.update(signSrc);
			result = sig.verify(signature);
		} catch (NoSuchAlgorithmException e) {
			LOG.info(String.format("NoSuchAlgorithmException: [errCode: empty], [errMsg: %s]", e.getMessage()));
		} catch (InvalidKeyException e) {
			LOG.info(String.format("InvalidKeyException: [errCode: empty], [errMsg: %s]", e.getMessage()));
		} catch (SignatureException e) {
			LOG.info(String.format("SignatureException: [errCode: empty], [errMsg: %s]", e.getMessage()));
		} catch (Exception e) {
			LOG.info(String.format("Exception: [errCode: empty], [errMsg: %s]", e.getMessage()));
		}
		return result;
	}

	//验签
	public static boolean verifySHA1SignatureBase64(byte[] signSrc, String sign, String modulus){
		boolean result = false;
		try {
			result = verifySHA1SignatureBase64(signSrc, Base64.getDecoder().decode(sign),modulus);
		} catch (Exception e) {
			LOG.info(String.format("Exception: [errCode: empty], [errMsg: %s]", e.getMessage()));
		}
		return result;
	}

	//加签
	public static String getSHA256SignatureBase64(byte[] data){
		String sign = null;
		try {
			Signature sig = Signature.getInstance("SHA256WithRSA");
			sig.initSign(privateKey);
			sig.update(data);
			sign = Base64.getEncoder().encodeToString(sig.sign());
		} catch (NoSuchAlgorithmException e) {
			LOG.info(String.format("NoSuchAlgorithmException: [errMsg: %s]", e.getMessage()));
		} catch (InvalidKeyException e) {
			LOG.info(String.format("InvalidKeyException: [errMsg: %s]", e.getMessage()));
		} catch (SignatureException e) {
			LOG.info(String.format("SignatureException: [errMsg: %s]", e.getMessage()));
		} catch (Exception e) {
			LOG.info(String.format("Exception: [errMsg: %s]", e.getMessage()));
		}
		return sign;
	}

	//加签
	public static String getSHA256SignatureBase64(String signSrc){
		String sign = null;
		try {
			return getSHA256SignatureBase64(signSrc.getBytes("utf-8"));
		} catch (Exception e) {
			LOG.info(String.format("Exception: [errMsg: %s]", e.getMessage()));
		}
		return sign;
	}

	//加签
	public static boolean verifySHA256SignatureBase64(byte[] data, byte[] sign){
		boolean result = false;
		String modulus= "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC9XrJWcWbj0LhDBzN4uwEOLA/U\n" +
				"JKmCkkbvlVgN/qei3e/jVFpxR6D3fzshnv5QNB4+BJ/rjRWbbxCJ0djzPxsLS1dJ\n" +
				"+bDwagZWZ9hNXARTq4K0uxw6Ol5jGD9Od6w5n5uxyaEk9/edvYwMhthIxC/uADRp\n" +
				"2pNSutwyLX3bUJnHZwIDAQAB";
		PublicKey pubKey = RSACryptography.getPubKey(modulus);
		try {
			Signature sig = Signature.getInstance("SHA256WithRSA");
			sig.initVerify(pubKey);
			sig.update(data);
			result = sig.verify(sign);
		} catch (NoSuchAlgorithmException e) {
			LOG.info(String.format("NoSuchAlgorithmException: [errCode: empty], [errMsg: %s]", e.getMessage()));
		} catch (InvalidKeyException e) {
			LOG.info(String.format("InvalidKeyException: [errCode: empty], [errMsg: %s]", e.getMessage()));
		} catch (SignatureException e) {
			LOG.info(String.format("SignatureException: [errCode: empty], [errMsg: %s]", e.getMessage()));
		} catch (Exception e) {
			LOG.info(String.format("Exception: [errCode: empty], [errMsg: %s]", e.getMessage()));
		}
		return result;
	}

	//验签
	public static boolean verifySHA256SignatureBase64(byte[] signSrc, String signature){
		boolean result = false;
		try {
			result = verifySHA256SignatureBase64(signSrc, Base64.getDecoder().decode(signature));
		} catch (Exception e) {
			LOG.info(String.format("Exception: [errCode: empty], [errMsg: %s]", e.getMessage()));
		}
		return result;
	}

	private static byte[] stream2ByteArray(InputStream input){
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		byte[] buffer = new byte[4096];
		int n = 0;
		try {
			while (-1 != (n = input.read(buffer))) {
				output.write(buffer, 0, n);
			}
		} catch (IOException e) {
			LOG.info(String.format("IOException: [errCode: empty], [errMsg: %s]", e.getMessage()));
		}
		return output.toByteArray();
	}

}
