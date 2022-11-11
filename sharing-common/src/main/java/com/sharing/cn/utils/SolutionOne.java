package com.sharing.cn.utils;

import java.util.HashMap;
import java.util.Map;

public class SolutionOne {

    private final int MOD = (int) 1e9 + 7;

    private Map<Long, Long> cache = new HashMap<>();

    public int busRapidTransit(int target, int inc, int dec, int[] jump, int[] cost) {
        return (int) (dfs(target, inc, dec, jump, cost) % MOD);
    }

    private long dfs(long n, int inc, int dec, int[] jump, int[] cost) {
        if (n == 1) {
            return inc;
        }
        if (n == 0) {
            return 0;
        }
        if (cache.containsKey(n)) {
            return cache.get(n);
        }
        long ans = n * inc;
        for (int i = 0; i < cost.length; i++) {
            int a = jump[i], b = cost[i];
            ans = Math.min(ans, dfs(n / a, inc, dec, jump, cost) + b + (n % a) * inc);
            if (n % a != 0) {
                ans = Math.min(ans, dfs(n / a + 1, inc, dec, jump, cost) + b + (a - n % a) * dec);
            }
        }
        cache.put(n, ans);
        return ans;
    }










}
