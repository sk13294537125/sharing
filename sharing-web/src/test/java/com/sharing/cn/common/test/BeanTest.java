package com.sharing.cn.common.test;

import com.alibaba.fastjson.JSON;
import com.sharing.cn.domain.bo.BaseDataBo;
import com.sharing.cn.utils.bean.BeanUtils;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.sql.Array;
import java.util.*;

/**
 * @author ext.shikai1
 */
public class BeanTest {


    @Test
    public void copyTest() {
        BaseDataBo source = new BaseDataBo();
        source.setName("name");
        source.setCode("");

        BaseDataBo target = new BaseDataBo();
        target.setCode("code");

        BeanUtils.copyProperties(source, target);
        System.out.println(JSON.toJSONString(target));

        Vector<Object> objects = new Vector<>();
    }



    @Test
    public void equals() {
        BaseDataBo source = new BaseDataBo();
        source.setName("name");
        source.setCode("");

        BaseDataBo target = new BaseDataBo();
        source.setName("name");
        source.setCode("");

        boolean equals = source.equals(target);
        System.out.println(equals);

    }

    @Test
    public void test() {
        String year = "2023";
        boolean b = Integer.parseInt(year) >= 2023;
        System.out.println(b);

    }
}
