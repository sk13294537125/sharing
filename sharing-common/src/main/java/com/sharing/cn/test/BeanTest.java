package com.sharing.cn.test;

import com.alibaba.fastjson.JSON;
import com.sharing.cn.test.dto.BaseDataBo;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

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

}
