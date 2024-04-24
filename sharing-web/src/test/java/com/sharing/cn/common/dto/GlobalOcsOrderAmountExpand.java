package com.sharing.cn.common.test.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * GlobalOcsOrderAmountExpand
 * 订单扩展信息
 *
 * @author ：luqi114
 * @date ：2022/9/14 21:22
 */
@Getter
@Setter
@ToString
public class GlobalOcsOrderAmountExpand {
    /**
     * 订单
     */
    private Long orderId;

    /**
     * 商品编号
     */
    private Long skuId;

    /**
     * 编号（商城这边本字段长度目前定义为70位）
     */
    private String code;

    /**
     * 金额类型
     */
    private Integer type;

    /**
     * 分摊到sku上的每个金额项的金额
     */
    private BigDecimal amount;
}
