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
    public void test() {
        float f = 3.4f;


    }

}
