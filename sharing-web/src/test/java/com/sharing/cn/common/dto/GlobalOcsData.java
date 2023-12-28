package com.sharing.cn.common.test.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

/**
 * GlobalOcsData
 * 订单商品支付信息
 *
 */
@Getter
@Setter
@ToString
public class GlobalOcsData {
    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 商品sku id
     */
    private Long skuId;

    /**
     * 商品数量
     */
    private Integer num;

    /**
     * 商品价格
     */
    private BigDecimal price;

    /**
     * 返现金额
     */
    private BigDecimal rePrice;

    /**
     * 优惠金额
     */
    private BigDecimal discount;

    /**
     * 优惠劵总金额
     */
    private BigDecimal couponDiscount;

    /**
     * 礼品卡总优惠
     */
    private BigDecimal giftCardDiscount;

    /**
     * 手机红包
     */
    private BigDecimal mobileDiscount;

    /**
     * 节能补贴金额
     */
    private BigDecimal energySubsidy;

    /**
     * 满减金额
     */
    private BigDecimal moneyOfSuit;

    /**
     * 订单扩展信息
     */
    private List<GlobalOcsOrderAmountExpand> amountExpands ;

    /**
     * 订单的实付金额
     */
    private BigDecimal payMoney;
}
