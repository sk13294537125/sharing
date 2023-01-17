package com.sharing.cn.common.test.dto;

import java.math.BigDecimal;

/**
 * @author pangzhuang
 * 客服代下单跳转结算页业务数据对象
 */
public class TelemarketingSettleAccountVo {

	/** 合同号 */
	private String compactId;
	/** 操作者 */
	private String operatorName;
	/** 回调地址 */
	private String callback;
	/** 主商品订单号 */
	private String mainOrderId;
	/** 主商品sku */
	private String mainSkuId;
	/** 延保sku */
	private String serviceSkuId;
	/** 延保数量 */
	private String serviceNum;
	/** sn码 */
	private String serialnumber;
	/** 主商品实付价 */
	private BigDecimal mainSkuPayPrice;

	@Override
	public String toString() {
		return "TelemarketingSettleAccountVo{" +
				"compactId='" + compactId + '\'' +
				", operatorName='" + operatorName + '\'' +
				", callback='" + callback + '\'' +
				", mainOrderId='" + mainOrderId + '\'' +
				", mainSkuId='" + mainSkuId + '\'' +
				", serviceSkuId='" + serviceSkuId + '\'' +
				", serviceNum='" + serviceNum + '\'' +
				", serialnumber='" + serialnumber + '\'' +
				", mainSkuPayPrice=" + mainSkuPayPrice +
				'}';
	}


	public String getCompactId() {
		return compactId;
	}
	public void setCompactId(String compactId) {
		this.compactId = compactId;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public String getCallback() {
		return callback;
	}
	public void setCallback(String callback) {
		this.callback = callback;
	}
	public String getMainOrderId() {
		return mainOrderId;
	}
	public void setMainOrderId(String mainOrderId) {
		this.mainOrderId = mainOrderId;
	}
	public String getMainSkuId() {
		return mainSkuId;
	}
	public void setMainSkuId(String mainSkuId) {
		this.mainSkuId = mainSkuId;
	}
	public String getServiceSkuId() {
		return serviceSkuId;
	}
	public void setServiceSkuId(String serviceSkuId) {
		this.serviceSkuId = serviceSkuId;
	}
	public String getServiceNum() {
		return serviceNum;
	}
	public void setServiceNum(String serviceNum) {
		this.serviceNum = serviceNum;
	}
	public String getSerialnumber() {
		return serialnumber;
	}
	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}

	public BigDecimal getMainSkuPayPrice() {
		return mainSkuPayPrice;
	}

	public void setMainSkuPayPrice(BigDecimal mainSkuPayPrice) {
		this.mainSkuPayPrice = mainSkuPayPrice;
	}
}
