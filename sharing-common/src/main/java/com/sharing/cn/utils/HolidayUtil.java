package com.sharing.cn.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * @author ext.shikai1
 */
public class HolidayUtil {

    public static final String WORKDAY = "0";
    public static final String WEEKEND = "1";
    public static final String HOLIDAYS = "2";

    /**
     * 判断当前是否为节假日： 0 工作日  1 周末  2 节假日
     * @param httpArg :查询日期
     * @return 返回结果
     */
    public static String queryHoliday(String httpArg) {
        String httpUrl = "http://tool.bitefu.net/jiari/";
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        httpUrl = httpUrl + "?d=" + httpArg;
        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return result;
    }

    public static void main(String[] args) {
        String jsonResult = HolidayUtil.queryHoliday("20221001");
        // 0 上班 1周末 2节假日
        if ("0".equals(jsonResult)) {
            System.out.println("0上班日");
        }

        if ("1".equals(jsonResult)) {
            System.out.println("1周末");
        }

        if ("2".equals(jsonResult)) {
            System.out.println("2节假日");
        }
    }
}
