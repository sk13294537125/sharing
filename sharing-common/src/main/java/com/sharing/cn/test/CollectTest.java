package com.sharing.cn.test;

import com.alibaba.fastjson.JSON;
import com.sharing.cn.test.dto.BaseDataBo;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ext.shikai1
 */
public class CollectTest {

    @Test
    public void listFilter() {
        List<BaseDataBo> list = new ArrayList<>();
        BaseDataBo bo = new BaseDataBo();
        bo.setId(0);
        BaseDataBo bo1 = new BaseDataBo();
        bo1.setId(1);
        list.add(bo);
        BaseDataBo bo2 = new BaseDataBo();
        bo2.setId(1);
        list.add(bo);
        list.add(bo1);
        list.add(bo2);

        System.out.println(list.size());

    }

    @Test
    public void listRemove() {
        List<BaseDataBo> list = new ArrayList<>();
        BaseDataBo bo = new BaseDataBo();
        bo.setId(0);
        BaseDataBo bo1 = new BaseDataBo();
        bo1.setId(1);
        BaseDataBo bo2 = new BaseDataBo();
        bo2.setId(1);

        list.add(bo);
        list.add(bo1);
        list.add(bo2);

        BaseDataBo remove = list.remove(0);
        System.out.println(JSON.toJSONString(remove));
        BaseDataBo baseDataBo = list.get(0);
        System.out.println(JSON.toJSONString(baseDataBo));
    }

    @Test
    public void mapRemove() {
        List<BaseDataBo> list = new ArrayList<>();
        BaseDataBo bo = new BaseDataBo();
        bo.setId(0);
        bo.setIsDelete(1);
        BaseDataBo bo1 = new BaseDataBo();
        bo1.setId(1);
        bo1.setIsDelete(1);
        BaseDataBo bo2 = new BaseDataBo();
        bo2.setId(1);
        bo2.setIsDelete(1);

        list.add(bo);
        list.add(bo1);
        list.add(bo2);

        Map<Integer, List<BaseDataBo>> map = list.stream()
                .collect(Collectors.groupingBy(BaseDataBo::getId));
        System.out.println("map:" + JSON.toJSONString(map));

        Map<Integer, Map<Integer, List<BaseDataBo>>> serviceSkuMap = new HashMap<>();
        map.forEach((k, v) -> {
            List<BaseDataBo> orderDetails = map.get(k);
            Map<Integer, List<BaseDataBo>> fuwuSkuMap = orderDetails.stream()
                    .collect(Collectors.groupingBy(BaseDataBo::getIsDelete));
            serviceSkuMap.put(k, fuwuSkuMap);
        });
        System.out.println("serviceSkuMap:" + JSON.toJSONString(serviceSkuMap));


        Map<Integer, List<BaseDataBo>> fuwuSkuMap = serviceSkuMap.get(1);
        if (!CollectionUtils.isEmpty(fuwuSkuMap)) {
            List<BaseDataBo> orderDetails = fuwuSkuMap.get(1);
            if (!CollectionUtils.isEmpty(orderDetails)) {
                orderDetails.remove(0);
                fuwuSkuMap.put(1, orderDetails);
                serviceSkuMap.put(1, fuwuSkuMap);
            }
        }
        System.out.println("serviceSkuMap1:" + JSON.toJSONString(serviceSkuMap));
    }

    @Test
    public void test1() {
        List<BaseDataBo> list = new ArrayList<>();
        BaseDataBo bo = new BaseDataBo();
        bo.setId(0);
        bo.setIsDelete(1);
        BaseDataBo bo1 = new BaseDataBo();
        bo1.setId(1);
        bo1.setIsDelete(1);
        BaseDataBo bo2 = new BaseDataBo();
        bo2.setId(1);
        bo2.setIsDelete(1);

        list.add(bo);
        list.add(bo1);
        list.add(bo2);

        Optional<BaseDataBo> first = list.stream()
                .filter(l -> l.getId() == 1)
                .findFirst();

        int i = first.isPresent() ? first.get().getId() : 100;
        System.out.println(i);
    }

    @Test
    public void distinct() {
        List<BaseDataBo> list = new ArrayList<>();
        BaseDataBo bo = new BaseDataBo();
        bo.setId(0);
        bo.setIsDelete(1);
        BaseDataBo bo1 = new BaseDataBo();
        bo1.setId(1);
        bo1.setIsDelete(1);
        BaseDataBo bo2 = new BaseDataBo();
        bo2.setId(2);
        bo2.setIsDelete(2);

        list.add(bo);
        list.add(bo1);
        list.add(bo2);

        ArrayList<BaseDataBo> collect = list.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toCollection(
                                () -> new TreeSet<>(Comparator.comparing(BaseDataBo::getIsDelete))), ArrayList::new));

        System.out.println(collect.size());
    }

}
