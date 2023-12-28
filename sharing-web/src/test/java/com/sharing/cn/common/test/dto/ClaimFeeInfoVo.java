package com.sharing.cn.common.test.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author ext.shikai1
 */
@Data
public class ClaimFeeInfoVo {

    /**
     * 运费
     */
    public BigDecimal freightAmount;

    /**
     * 原始金额
     */
    public BigDecimal originalAmount;
}
