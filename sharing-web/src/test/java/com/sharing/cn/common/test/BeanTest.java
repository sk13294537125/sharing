package com.sharing.cn.common.test;

import com.alibaba.fastjson.JSON;
import com.sharing.cn.domain.bo.BaseDataBo;
import com.sharing.cn.utils.bean.BeanUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
    }

    @Test
    public void copyList() {
        List<BaseDataBo> sourceList = new ArrayList<>();
        BaseDataBo source1 = new BaseDataBo();
        source1.setName("source1");
        source1.setCode("source1");
        sourceList.add(source1);

        BaseDataBo source2 = new BaseDataBo();
        source2.setCode("source2");
        source1.setName("source2");
        sourceList.add(source2);

        BaseDataBo source3 = new BaseDataBo();
        source3.setCode("source3");
        source3.setName("source3");
        sourceList.add(source3);

        List<BaseDataBo> targetList = new ArrayList<>();

        System.out.println(JSON.toJSONString(targetList));
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
