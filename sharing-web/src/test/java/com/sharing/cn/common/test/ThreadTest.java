package com.sharing.cn.common.test;

import com.alibaba.fastjson.JSON;
import com.sharing.cn.common.dto.TaskExecutors;
import com.sharing.cn.common.test.dto.RequestVo;
import com.sharing.cn.common.test.dto.SkuLineDto;
import lombok.Data;
import org.junit.Test;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author ext.shikai1
 */
public class ThreadTest {

    @Test
    public void join() {
        final Thread t1 = new Thread(() ->
                System.out.println("线程1执行"
                ));

        Thread t2 = new Thread(() -> {
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程2执行");
        });

        final Thread t3 = new Thread(() -> {
            try {
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程3执行");
        });

        t3.start();
        t2.start();
        t1.start();
    }

    @Test
    public void threadPool() {
        ExecutorService threadPool = new ThreadPoolExecutor(2, 5,
                1L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        threadPool.execute(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("threadPool线程--->" + i);
            }
        });

        ExecutorService service = Executors.newFixedThreadPool(10);
        service.execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println("service线程--->" + i);
                }
            }
        });
    }

    @Test
    public void local() {
        final int threadCount = 10;
        /**
         * 线程池
         */
        ExecutorService exe = Executors.newFixedThreadPool(threadCount);
        /**
         * 口令枪
         * 当口令枪起，比赛开始
         */
        final CountDownLatch matchGun = new CountDownLatch(1);

        /**
         * 比赛者完成比赛用来通知的枪
         */
        final CountDownLatch endGun = new CountDownLatch(100);

        for (int i = 0; i < 100; i++) {
            Worker worker = new Worker(i + 1, matchGun, endGun);
            //分配线程
            exe.execute(worker);
        }
        /**
         * 比赛枪响起
         */
        matchGun.countDown();
        try {
            /**
             * 等待，直到最后一个队员完成比赛。
             */
            endGun.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("比赛结束!");
        }
        /**
         * 关闭线程池。
         */
        exe.shutdown();
    }
    /**
     * 内部类，具体执行者
     */
    static class Worker implements Runnable {
        /**
         * 比赛队员牌号
         */
        private int id;
        /**
         * 比赛抢
         */
        private CountDownLatch begin;
        /**
         * 完成比赛通知枪
         */
        private CountDownLatch end;

        public Worker(int i, CountDownLatch begin, CountDownLatch end) {
            super();
            this.id = i;
            this.begin = begin;
            this.end = end;
        }

        @Override
        public void run() {
            try {
                /**
                 * 等待比赛枪响起
                 */
                begin.await();
                /**
                 * 如果枪响，则开始进入比赛
                 */
                Thread.sleep((long) (Math.random() * 100));    //随机分配时间，即运动员完成时间
                System.out.println("队员[" + id + "]完成比赛.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                /**
                 * 通知仲裁，完成该队员完成比赛
                 */
                end.countDown();    //使end状态减1，最终减至0
            }
        }
    }

    @Test
    public void latch1() throws InterruptedException {
        List<String> collect = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            collect.add(String.valueOf(i));
        }
        CountDownLatch latch = new CountDownLatch(collect.size());
        for(String t : collect){
            new Thread(() -> {
                try {
                    System.out.println(t);
                }catch (Exception e){
                    System.out.println("Thread Exception");
                } finally {
                    latch.countDown();

                }
            }).start();
        }
        latch.await();

    }

    @Test
    public void localRun() throws InterruptedException {
        LinkedBlockingQueue<Object> objects = new LinkedBlockingQueue<>();

        ExecutorService threadPool = new ThreadPoolExecutor(2, 5,
                1L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        CountDownLatch latch = new CountDownLatch(10);

        List<Integer> list = new ArrayList<>();
        try {
            for (int i = 0; i < 10; i++) {
                int finalI = i;
                AtomicBoolean atomicBoolean = new AtomicBoolean();
                threadPool.submit(() -> {
                    atomicBoolean.set(localTestRun(finalI));
                    if (atomicBoolean.get()) {
                        list.add(finalI);
                    }
                    latch.countDown();
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            latch.countDown();
            if (!threadPool.isShutdown()) {
                threadPool.shutdown();
            }
        }

        try {
            latch.await(20, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("结束!");
        }
        threadPool.shutdown();
        System.out.println("关闭");
        System.out.println(JSON.toJSONString(list));
    }

    public boolean localTestRun(Integer i) {
        System.out.println(i);
        //try {
        //    Thread.sleep(1000);
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //    System.out.println("异常");
        //}
        return true;
    }

    @Test
    public void threadLocal() {
        ThreadLocal sThreadLocal = new ThreadLocal();
        sThreadLocal.set("a");
        sThreadLocal.set("b");
        String a = (String)sThreadLocal.get();
        System.out.println(a);
        System.exit(1);
    }

    @Test
    public void test1() {
        int i = Thread.activeCount();
        System.out.println(i);
    }

    @Test
    public void thread3() {
        long l = System.currentTimeMillis();
        List<com.sharing.cn.common.test.dto.RequestVo> req = new ArrayList<>();
        List<SkuLineDto> list = new ArrayList<>();
        List<Future<SkuLineDto>> futures = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            RequestVo vo = new RequestVo();
            vo.setBussinessId(String.valueOf(i));
            req.add(vo);
        }

        ThreadPoolTaskExecutor threadPoolTaskExecutor = TaskExecutors.getInstance().threadPoolTaskExecutor();
        for (RequestVo requestVo : req) {
            ConfigCallable configCallable = new ConfigCallable();
            configCallable.setReq(requestVo);
            Future<SkuLineDto> submit = threadPoolTaskExecutor.submit(configCallable);
            futures.add(submit);
        }
        for (Future<SkuLineDto> future : futures) {
            try {
                list.add(future.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println(JSON.toJSONString(list));
        long l1 = System.currentTimeMillis();
        System.out.println(l1 - l);

        long l2 = System.currentTimeMillis();
        List<SkuLineDto> list1 = new ArrayList<>();
        for (RequestVo requestVo : req) {
            SkuLineDto skuLineDto = new SkuLineDto();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            skuLineDto.setSkuId(requestVo.getBussinessId());
            list1.add(skuLineDto);
        }
        System.out.println(JSON.toJSONString(list1));
        long l3 = System.currentTimeMillis();
        System.out.println(l3 - l2);
    }

    @Data
    class ConfigCallable implements Callable<SkuLineDto> {

        private com.sharing.cn.common.test.dto.RequestVo req;

        @Override
        public SkuLineDto call() throws Exception {
            //List<SkuLineDto> list = new ArrayList<>();
            //for (RequestVo requestVo : req) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                SkuLineDto skuLineDto = new SkuLineDto();
                skuLineDto.setSkuId(req.getBussinessId());
                return skuLineDto;
                //list.add(skuLineDto);
            //}
            //return list;
        }
    }


}
