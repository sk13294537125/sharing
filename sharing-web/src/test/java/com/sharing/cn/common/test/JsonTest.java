package com.sharing.cn.common.test;

import com.alibaba.fastjson.JSON;
import com.sharing.cn.common.test.dto.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ext.shikai1
 */
public class JsonTest {

    @Test
    public void parse() {
        List<SkuInfoDto> skuInfoDtos = new ArrayList<>();
        SkuLineDto skuLineDto = new SkuLineDto();
        skuLineDto.setSkuId("100030377517");
        SkuInfoDto skuInfoDto = new SkuInfoDto();
        skuInfoDto.setSkuLineDto(skuLineDto);
        skuInfoDto.setSkuNum(1);
        skuInfoDtos.add(skuInfoDto);
        System.out.println(JSON.toJSONString(skuInfoDtos));
    }
}
