package com.sharing.cn.common.test.test;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.Data;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 1、题目：编写一个Java函数，实现批量获取数据的功能（BService.get(List<Integer> ids)）。具体要求如下：
 */
public class BatchEntrezTest {

    @Test
    public void test() throws Exception {

        List<Integer> ids = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            ids.add(i);
        }

        List<Integer> integers = get(ids);

        System.out.println(JSON.toJSONString(integers));
    }

    /**
     * 1)提供一个函数BService.get(List<Integer> ids)，支持最多传入100个id；
     * 2)在BService.get((List<Integer> ids)函数内部，需要将传入的id列表分批（每批10个id）进行调用AService.get(List<Integer> ids)函数获取数据；
     * 3)BService.get((List<Integer> ids)函数需要返回所有批次获取的数据的合并结果，即一个包含所有数据的List<Integer>；
     * @param ids
     * @return
     * @throws Exception
     */
    public List<Integer> get(List<Integer> ids) throws Exception {
        List<Integer> returnIds = new ArrayList<>();
        if (CollectionUtils.isEmpty(ids)) {
            return returnIds;
        }
        // 支持最多传入100个id；
        if (ids.size() > 100) {
            return returnIds;
        }

        // 需要将传入的id列表分批（每批10个id）
        List<List<Integer>> partition = Lists.partition(ids, 10);

        List<Future<List<Integer>>> futures = new ArrayList<>();

        // 进行调用AService.get
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (List<Integer> integers : partition) {
            Dispose dispose = new Dispose();
            dispose.setIds(integers);
            Future<List<Integer>> submit = executorService.submit(dispose);
            futures.add(submit);
        }

        for (Future<List<Integer>> future : futures) {
            List<Integer> integers = future.get();
            returnIds.addAll(integers);
        }

        return returnIds;
    }

    @Data
    class Dispose implements Callable<List<Integer>> {

        private List<Integer> ids;

        @Override
        public List<Integer> call() {
            // 进行调用AService.get
            return get1(ids);
        }
    }

    public List<Integer> get1(List<Integer> ids) {
        List<Integer> returnIds = new ArrayList<>();
        if (CollectionUtils.isEmpty(ids)) {
            return returnIds;
        }
        return ids;
    }


}
