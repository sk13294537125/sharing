package com.sharing.cn.common.data;

import org.apache.commons.lang3.StringUtils;
import org.jasypt.util.text.AES256TextEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * *********************************************
 * @class: Jasypt
 * @description:
 * @date: 2022/6/21 16:51
 * @author: zhaoyang yan
 * @version: 1.0
 * *********************************************
 */
public class Jasypt {

	public static void main(String[] args) throws IOException, InterruptedException {
		System.out.println("开始进行Jasypt加解密");
		System.out.println("请输入操作类型，0：加密；1：解密");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String operatorType = br.readLine();
		if (StringUtils.isBlank(operatorType)) {
			System.out.println("操作类型不合法");
			return;
		}
		if ("0".equals(operatorType) && "1".equals(operatorType)) {
			System.out.println("操作类型不合法");
			return;
		}

		System.out.println("请输入加解密算法，0：PBEWithMD5AndDES；1：PBEWithHMACSHA512AndAES_256");
		String arithmeticType = br.readLine();
		if (StringUtils.isBlank(arithmeticType)) {
			System.out.println("加解密算法不合法");
			return;
		}
		if ("0".equals(arithmeticType) && "1".equals(arithmeticType)) {
			System.out.println("加解密算法不合法");
			return;
		}

		System.out.println("请输入密钥");
		String jasyptKey = "Bt%XJ^n1j8mz";

		System.out.println("请输入待处理信息");
		String operatorInfo = br.readLine();

		if ("0".equals(arithmeticType)){
			// PBEWithMD5AndDES
			BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
			basicTextEncryptor.setPassword(jasyptKey);
			if ("0".equals(operatorType)){
				// 加密
				String encrypt = basicTextEncryptor.encrypt(operatorInfo);
				System.out.println("加密结果：" + encrypt);
			}
			if ("1".equals(operatorType)){
				// 解密
				String decrypt = basicTextEncryptor.decrypt(operatorInfo);
				System.out.println("解密结果：" + decrypt);
			}
		}
		if ("1".equals(arithmeticType)){
			// PBEWithHMACSHA512AndAES_256
			AES256TextEncryptor aes256TextEncryptor = new AES256TextEncryptor();
			aes256TextEncryptor.setPassword(jasyptKey);
			if ("0".equals(operatorType)){
				// 加密
				String encrypt = aes256TextEncryptor.encrypt(operatorInfo);
				System.out.println("加密结果：" + encrypt);
			}
			if ("1".equals(operatorType)){
				// 解密
				String decrypt = aes256TextEncryptor.decrypt(operatorInfo);
				System.out.println("解密结果：" + decrypt);
			}
		}
		Thread.sleep(60000L);
	}
}
