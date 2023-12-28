package com.sharing.cn.common.test.dto;


public class RequestVo {

	/**
	 * 有apikey+timestamp+bussinessId+dataStr拼接成字符串前后加入秘钥。
	 * 通过MD5生成16位信息摘要
	 */
	private String sign;
	
	/**
	 * http接口key
	 */
	private String apiKey;
	
	/**
	 * 时间戳 格式： yyyy-MM-dd HH:mm:ss
	 */
	private String timestamp;
	
	/**
	 * 业务编码
	 */
	private String bussinessId;
	
	/**
	 * 数据内容，T的json格式
	 */
	private String dataStr;



	@Override
	public String toString() {
		return "RequestVo [sign=" + sign + ", apiKey=" + apiKey + ", timestamp=" + timestamp
				+ ", bussinessId=" + bussinessId + ", dataStr=" + dataStr + "]";
	}
	
	
	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getBussinessId() {
		return bussinessId;
	}

	public void setBussinessId(String bussinessId) {
		this.bussinessId = bussinessId;
	}

	public String getDataStr() {
		return dataStr;
	}

	public void setDataStr(String dataStr) {
		this.dataStr = dataStr;
	}
}
