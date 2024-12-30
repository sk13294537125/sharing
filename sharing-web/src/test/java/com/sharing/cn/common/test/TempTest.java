package com.sharing.cn.common.test;

import com.alibaba.fastjson.JSON;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.sharing.cn.common.test.dto.ClaimFeeInfoVo;
import com.sharing.cn.common.test.dto.GlobalOcsData;
import com.sharing.cn.common.test.dto.GlobalOcsResult;
import com.sharing.cn.utils.GsonUtils;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author ext.shikai1
 */
public class TempTest {

    public static Integer claimFeeMap(String key) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("claimfee_config_1001", 28);
        map.put("claimfee_config_1001_42845", 30);
        map.put("claimfee_config_1001_42826", 30);
        map.put("claimfee_config_1001_42843", 30);
        map.put("claimfee_config_1001_1016151", 28);
        map.put("claimfee_config_1001_1030033", 30);
        map.put("claimfee_config_1002", 28);
        map.put("claimfee_config_1002_42826", 30);
        map.put("claimfee_config_1002_42843", 30);
        map.put("claimfee_config_1002_42845", 200);
        map.put("claimfee_config_1002_1016151", 200);
        map.put("claimfee_config_1002_1030033", 200);
        map.put("claimfee_config_1003", 28);
        map.put("claimfee_config_1003_42826", 30);
        map.put("claimfee_config_1003_42843", 30);
        map.put("claimfee_config_1003_42845", 200);
        map.put("claimfee_config_1003_1016151", 200);
        map.put("claimfee_config_1003_1030033", 200);
        return map.get(key);
    }

    @Test
    public void claimFee() {
        BigDecimal claimFee = new BigDecimal("25");
        BigDecimal bigDecimal = queryClaimfeeByCateType(claimFee);
        System.out.println(bigDecimal);

    }

    public BigDecimal queryClaimfeeByCateType(BigDecimal claimFee) {
        String cateType = "1001";
        String suppId = "1016151";
        BigDecimal cateTypeClaimfee = new BigDecimal(claimFeeMap("claimfee_config_" + cateType));
        if (claimFee.compareTo(cateTypeClaimfee) > 0) {
            return cateTypeClaimfee;
        }

        BigDecimal cateTypeClaimfee1 = new BigDecimal(claimFeeMap("claimfee_config_" + cateType + "_" + suppId));
        if (claimFee.compareTo(cateTypeClaimfee1) > 0) {
            return cateTypeClaimfee1;
        }
        return claimFee;
    }

    @Test
    public void test1() {
        List<String> cate3IdList = new ArrayList<String>();
        String skuCate3Id = "[107810,11960]";
        String[] skuCate3IdList = skuCate3Id.split("]\\[");

        for (String cate3 : skuCate3IdList) {
            List<String> skuCate3IdSplitList =
                    Arrays.asList(cate3.replace("[", "").replace("]", "").split(","));
            if (!CollectionUtils.isEmpty(skuCate3IdSplitList)) {
                List<String> cate3List = skuCate3IdSplitList.subList(1, skuCate3IdSplitList.size());
                cate3IdList.addAll(cate3List);
            }
        }
        System.out.println(JSON.toJSONString(cate3IdList));
    }

    @Test
    public void test2() {
        String waybillSign = "20006000010903000100000000002002000000000002003000001000000000000000000000000010000101300000100000000000000010000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";

        // 第124位=3 并且 第40位>0，京东速运，不走此流程
        if (waybillSign.length() >= 124 && waybillSign.charAt(123) == '3' && Character.isDigit(waybillSign.charAt(39)) && Integer.parseInt(String.valueOf(waybillSign.charAt(39))) > 0) {
            System.out.println(1);
        }
        System.out.println(waybillSign.charAt(16));
        System.out.println(waybillSign.charAt(20));
        if (waybillSign.charAt(16) == '5' && waybillSign.charAt(20) == '1') {
            System.out.println(2);
        }
        System.out.println(3);
    }

    @Test
    public void test3() {
        String s = "{\"code\":\"SUCCESS\",\"reason\":\"\",\"data\":\"[{\\\"orderId\\\":255965025854,\\\"skuId\\\":10026490764747,\\\"num\\\":1,\\\"price\\\":499.000000,\\\"rePrice\\\":0.000000,\\\"discount\\\":110.000000,\\\"couponDiscount\\\":125.000000,\\\"baseFee\\\":0.000000,\\\"remoteAreasFee\\\":0.000000,\\\"shopFee\\\":0.000000,\\\"giftCardDiscount\\\":0.000000,\\\"moneyBalance\\\":0.000000,\\\"mobileDiscount\\\":0.000000,\\\"payMoney\\\":224.000000,\\\"energySubsidy\\\":0.000000,\\\"moneyOfSuit\\\":40.000000,\\\"promoId\\\":129889034845,\\\"skuType\\\":4,\\\"amountPayable\\\":224.000000,\\\"amountExpands\\\":[{\\\"orderId\\\":255965025854,\\\"skuId\\\":10026490764747,\\\"code\\\":\\\"385299310173\\\",\\\"type\\\":9,\\\"amount\\\":120.000000},{\\\"orderId\\\":255965025854,\\\"skuId\\\":10026490764747,\\\"code\\\":\\\"385299770701\\\",\\\"type\\\":6,\\\"amount\\\":5.000000},{\\\"orderId\\\":255965025854,\\\"skuId\\\":10026490764747,\\\"code\\\":\\\"61819222111313461301455\\\",\\\"type\\\":112,\\\"amount\\\":224.000000},{\\\"orderId\\\":255965025854,\\\"skuId\\\":10026490764747,\\\"code\\\":\\\"129893277953\\\",\\\"type\\\":202,\\\"amount\\\":110.000000},{\\\"orderId\\\":255965025854,\\\"skuId\\\":10026490764747,\\\"code\\\":\\\"100391\\\",\\\"type\\\":504,\\\"amount\\\":0.000000}],\\\"jiFenCount\\\":0,\\\"skuUuid\\\":\\\"1012_F2I3c3Q1148538098071052288\\\",\\\"currency\\\":\\\"CNY\\\",\\\"tenantId\\\":\\\"301\\\",\\\"country\\\":\\\"CN\\\",\\\"timeZone\\\":\\\"GMT+8\\\"}]\"}";
        GlobalOcsResult globalOcsResult = GsonUtils.fromJson(s, GlobalOcsResult.class);
        Gson gson = new Gson();
        List<GlobalOcsData> globalOcsDataList = gson.fromJson(globalOcsResult.getData(), new TypeToken<List<GlobalOcsData>>() {
        }.getType());
        Map<Long, List<GlobalOcsData>> listMap = globalOcsDataList.stream()
                .collect(Collectors.groupingBy(GlobalOcsData::getSkuId));
        System.out.println("listMap:" + JSON.toJSONString(listMap));
        System.out.println(JSON.toJSONString(listMap.get(10026490764747L)));
    }

    @Test
    public void tets4() {
        String i = "6079";
        String s = "6055,6056,6057,6058,6059,6060,6061,6062,6063,6067,6070,6071,6077,6079";
        List<String> list = Arrays.asList(s.split(","));
        System.out.println(JSON.toJSONString(list));
        boolean contains = !list.contains(i);
        System.out.println(contains);

    }

    @Test
    public void test5() {
        String str1 = new String("a") + new String("bc");
        String str2 = str1.intern();
        System.out.println(str1 == str2);
        System.out.println(str1.equals(str2));
    }

    @Test
    public void test6() {
        ClaimFeeInfoVo claimFeeInfoVo = new ClaimFeeInfoVo();
        // 运费
        BigDecimal freightAmount = new BigDecimal("0.00");
        // 原始金额
        BigDecimal originalAmount = new BigDecimal("0.00");

        for (int i = 0; i < 10; i++) {
            freightAmount = freightAmount.add(new BigDecimal(i));
        }
        claimFeeInfoVo.setFreightAmount(freightAmount);

        System.out.println(JSON.toJSONString(claimFeeInfoVo));
        System.out.println(claimFeeInfoVo.getOriginalAmount());
    }

    @Test
    public void test7() {
        int[] nums1 = {1,2,3,4,5,6,7};
        rotate(nums1, 2);

        System.out.println(Arrays.toString(nums1));
    }

    public void rotate(int[] nums, int k) {
        if (0 == nums.length || 0 == k) {
            return;
        }
        int[] nums1 = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (i <= k) {
                nums1[i] = nums[i + k];
            }
            if (i > k) {
                nums1[i] = nums[i - k];
            }
        }
        nums = nums1;
    }

}
