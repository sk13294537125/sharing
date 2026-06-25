package com.sharing.cn.controller.yuchen.ak;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sharing.cn.controller.yuchen.ak.common.AkResp;
import com.sharing.cn.controller.yuchen.ak.common.DataDTO;
import com.sharing.cn.controller.yuchen.ak.dmnemonic.DmnemonicReq;
import com.sharing.cn.controller.yuchen.ak.dmnemonic.DmnemonicResp;
import com.sharing.cn.controller.yuchen.ak.subAccount.SubAccountDetail;
import com.sharing.cn.controller.yuchen.ak.subAccount.SubAccountReq;
import com.sharing.cn.controller.yuchen.ak.transaction.TranReq;
import com.sharing.cn.utils.http.HttpFormUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class AkTransaction {

     // 查询子账户信息url post  fromdata
     public static final String FIND_SUB_ACCOUNT_URL = "https://www.akapi1.com/RPC/My_Subaccount";
     // 获取助记词  post  fromdata
     public static final String FIND_MNEMONIC_URL = "https://www.akapi1.com/RPC/Mnemonic_Get01";
     // 卖出 post   fromdata
     public static final String SALE_URL = "https://www.akapi1.com/RPC/ACE_Sell_Son";
     // 超过 阈值  可以卖出
     public static final Integer GT_NUM = 10000;
     // 保留数量
     public static final Integer RETAIN_NUM = 2000;

     private String gCode;

     /**
      * 1.查询子账户信息
      * 2.判断大于6000的数量
      * 3.大于6000，保留2000张
      * 4.查找对应助记词
      * 5.添加谷歌验证码
      * 6.提交
      */
     public static void main(String[] args) {
          String gCode = "765329";
          tran(gCode);
     }

//     @Scheduled(cron = "0/10 * 12 * * ?")
     @Scheduled(cron = "0/10 * 15 * * ?")
     public void tranJob() {
          log.info("定时任务执行谷歌验证码:{}", gCode);
          tran(gCode);
     }

     @GetMapping("/tranCon")
     public void tranCon(@RequestParam("code") String code) {
          gCode = code;
          tran(gCode);
     }

     public static void tran(String gCode) {
          // 查询子账户信息，数量大于10000的
          List<SubAccountDetail> gtSubAccountDetailList = buildSubAccount();
          // 查询助记词
          DmnemonicResp dmnemonicResp = buildDmnemonic();
          // 卖出
          List<SubAccountDetail> subAccountDetailList = gtSubAccountDetailList.stream()
                  .limit(3)
                  .collect(Collectors.toList());
          assembleTran(subAccountDetailList, dmnemonicResp, gCode);
     }

     public static List<SubAccountDetail> buildSubAccount() {
          // 查询子账户信息
          SubAccountReq subAccountReq = new SubAccountReq();
          log.info("售卖ak-查询子账户信息req:{}", JSON.toJSONString(subAccountReq));
          String subAccountResponse = HttpFormUtil.doPost(FIND_SUB_ACCOUNT_URL, subAccountReq, null);
//          String subAccountResponse = "{\"Error\": false,\"Data\": {\"PageSize\": 100,\"Count\": 0,\"List\": [{\"Id\": 10267773,\"FlowNumber\": \"3144778\",\"MemberNo\": \"Ltle0902-60\",\"IsMemberNo\": \"false\",\"EP\": 0,\"RP\": 0,\"SP\": 0,\"ULP\": 0,\"AvatarImage\": \"https://static.ace-accel.com/StaticResources/Avatar/default.jpg\",\"AceAmount\": 3393,\"CreateTime\": \"2025/6/1 22:10:56\",\"LevelNumber\": 3},{\"Id\": 9986395,\"FlowNumber\": \"2931310\",\"MemberNo\": \"Ltle0902-59\",\"IsMemberNo\": \"false\",\"EP\": 0,\"RP\": 0,\"SP\": 0,\"ULP\": 0,\"AvatarImage\": \"https://static.ace-accel.com/StaticResources/Avatar/default.jpg\",\"AceAmount\": 4410,\"CreateTime\": \"2025/4/28 20:50:32\",\"LevelNumber\": 3},{\"Id\": 9398725,\"FlowNumber\": \"2544852\",\"MemberNo\": \"Ltle0902-58\",\"IsMemberNo\": \"false\",\"EP\": 0,\"RP\": 0,\"SP\": 0,\"ULP\": 0,\"AvatarImage\": \"https://static.ace-accel.com/StaticResources/Avatar/default.jpg\",\"AceAmount\": 4946,\"CreateTime\": \"2025/1/11 16:56:28\",\"LevelNumber\": 3},{\"Id\": 8751356,\"FlowNumber\": \"2261169\",\"MemberNo\": \"Ltle0902-57\",\"IsMemberNo\": \"false\",\"EP\": 0,\"RP\": 0,\"SP\": 0,\"ULP\": 0,\"AvatarImage\": \"https://static.ace-accel.com/StaticResources/Avatar/default.jpg\",\"AceAmount\": 5733,\"CreateTime\": \"2024/9/28 21:01:48\",\"LevelNumber\": 3},{\"Id\": 8615746,\"FlowNumber\": \"2203371\",\"MemberNo\": \"Ltle0902-56\",\"IsMemberNo\": \"false\",\"EP\": 357.5754,\"RP\": 0,\"SP\": 238.3836,\"ULP\": 0,\"AvatarImage\": \"https://static.ace-accel.com/StaticResources/Avatar/default.jpg\",\"AceAmount\": 2600,\"CreateTime\": \"2024/9/17 9:42:53\",\"LevelNumber\": 3},{\"Id\": 7838853,\"FlowNumber\": \"1886133\",\"MemberNo\": \"Ltle0902-55\",\"IsMemberNo\": \"false\",\"EP\": 0,\"RP\": 0,\"SP\": 0,\"ULP\": 0,\"AvatarImage\": \"https://static.ace-accel.com/StaticResources/Avatar/default.jpg\",\"AceAmount\": 3515,\"CreateTime\": \"2024/7/5 0:17:21\",\"LevelNumber\": 3},{\"Id\": 7838852,\"FlowNumber\": \"1886132\",\"MemberNo\": \"Ltle0902-54\",\"IsMemberNo\": \"false\",\"EP\": 0,\"RP\": 0,\"SP\": 0,\"ULP\": 0,\"AvatarImage\": \"https://static.ace-accel.com/StaticResources/Avatar/default.jpg\",\"AceAmount\": 3515,\"CreateTime\": \"2024/7/5 0:16:51\",\"LevelNumber\": 3},{\"Id\": 7838851,\"FlowNumber\": \"1886131\",\"MemberNo\": \"Ltle0902-53\",\"IsMemberNo\": \"false\",\"EP\": 0,\"RP\": 0,\"SP\": 0,\"ULP\": 0,\"AvatarImage\": \"https://static.ace-accel.com/StaticResources/Avatar/default.jpg\",\"AceAmount\": 3515,\"CreateTime\": \"2024/7/5 0:16:21\",\"LevelNumber\": 3},{\"Id\": 7659963,\"FlowNumber\": \"1812104\",\"MemberNo\": \"Ltle0902-52\",\"IsMemberNo\": \"false\",\"EP\": 0,\"RP\": 0,\"SP\": 0,\"ULP\": 0,\"AvatarImage\": \"https://static.ace-accel.com/StaticResources/Avatar/default.jpg\",\"AceAmount\": 3954,\"CreateTime\": \"2024/6/17 10:03:54\",\"LevelNumber\": 3},{\"Id\": 7659858,\"FlowNumber\": \"1812070\",\"MemberNo\": \"Ltle0902-51\",\"IsMemberNo\": \"false\",\"EP\": 0,\"RP\": 0,\"SP\": 0,\"ULP\": 0,\"AvatarImage\": \"https://static.ace-accel.com/StaticResources/Avatar/default.jpg\",\"AceAmount\": 3954,\"CreateTime\": \"2024/6/17 10:03:19\",\"LevelNumber\": 3},{\"Id\": 7659626,\"FlowNumber\": \"1811986\",\"MemberNo\": \"Ltle0902-50\",\"IsMemberNo\": \"false\",\"EP\": 0,\"RP\": 0,\"SP\": 0,\"ULP\": 0,\"AvatarImage\": \"https://static.ace-accel.com/StaticResources/Avatar/default.jpg\",\"AceAmount\": 3954,\"CreateTime\": \"2024/6/17 10:02:03\",\"LevelNumber\": 3},{\"Id\": 7659512,\"FlowNumber\": \"1811948\",\"MemberNo\": \"Ltle0902-49\",\"IsMemberNo\": \"false\",\"EP\": 0,\"RP\": 0,\"SP\": 0,\"ULP\": 0,\"AvatarImage\": \"https://static.ace-accel.com/StaticResources/Avatar/default.jpg\",\"AceAmount\": 3954,\"CreateTime\": \"2024/6/17 10:01:30\",\"LevelNumber\": 3},{\"Id\": 7659347,\"FlowNumber\": \"1811882\",\"MemberNo\": \"Ltle0902-48\",\"IsMemberNo\": \"false\",\"EP\": 0,\"RP\": 0,\"SP\": 0,\"ULP\": 0,\"AvatarImage\": \"https://static.ace-accel.com/StaticResources/Avatar/default.jpg\",\"AceAmount\": 3954,\"CreateTime\": \"2024/6/17 9:59:52\",\"LevelNumber\": 3},{\"Id\": 7083733,\"FlowNumber\": \"1587809\",\"MemberNo\": \"Ltle0902-47\",\"IsMemberNo\": \"false\",\"EP\": 0,\"RP\": 0,\"SP\": 0,\"ULP\": 0,\"AvatarImage\": \"https://static.ace-accel.com/StaticResources/Avatar/default.jpg\",\"AceAmount\": 4833,\"CreateTime\": \"2024/4/27 9:57:58\",\"LevelNumber\": 3},{\"Id\": 7083691,\"FlowNumber\": \"1587795\",\"MemberNo\": \"Ltle0902-46\",\"IsMemberNo\": \"false\",\"EP\": 0,\"RP\": 0,\"SP\": 0,\"ULP\": 0,\"AvatarImage\": \"https://static.ace-accel.com/StaticResources/Avatar/default.jpg\",\"AceAmount\": 4833,\"CreateTime\": \"2024/4/27 9:57:42\",\"LevelNumber\": 3},{\"Id\": 6686424,\"FlowNumber\": \"1441654\",\"MemberNo\": \"Ltle0902-45\",\"IsMemberNo\": \"false\",\"EP\": 0,\"RP\": 0,\"SP\": 0,\"ULP\": 0,\"AvatarImage\": \"https://static.ace-accel.com/StaticResources/Avatar/default.jpg\",\"AceAmount\": 6282,\"CreateTime\": \"2024/3/29 20:39:17\",\"LevelNumber\": 3},{\"Id\": 6596431,\"FlowNumber\": \"1408524\",\"MemberNo\": \"Ltle0902-44\",\"IsMemberNo\": \"false\",\"EP\": 0,\"RP\": 0,\"SP\": 0,\"ULP\": 0,\"AvatarImage\": \"https://static.ace-accel.com/StaticResources/Avatar/default.jpg\",\"AceAmount\": 6853,\"CreateTime\": \"2024/3/26 9:30:13\",\"LevelNumber\": 3},{\"Id\": 6596357,\"FlowNumber\": \"1408501\",\"MemberNo\": \"Ltle0902-43\",\"IsMemberNo\": \"false\",\"EP\": 0,\"RP\": 0,\"SP\": 0,\"ULP\": 0,\"AvatarImage\": \"https://static.ace-accel.com/StaticResources/Avatar/default.jpg\",\"AceAmount\": 6853,\"CreateTime\": \"2024/3/26 9:29:56\",\"LevelNumber\": 3},{\"Id\": 6596275,\"FlowNumber\": \"1408478\",\"MemberNo\": \"Ltle0902-42\",\"IsMemberNo\": \"false\",\"EP\": 0,\"RP\": 0,\"SP\": 0,\"ULP\": 0,\"AvatarImage\": \"https://static.ace-accel.com/StaticResources/Avatar/default.jpg\",\"AceAmount\": 6853,\"CreateTime\": \"2024/3/26 9:29:39\",\"LevelNumber\": 3},{\"Id\": 6596207,\"FlowNumber\": \"1408455\",\"MemberNo\": \"Ltle0902-41\",\"IsMemberNo\": \"false\",\"EP\": 0,\"RP\": 0,\"SP\": 0,\"ULP\": 0,\"AvatarImage\": \"https://static.ace-accel.com/StaticResources/Avatar/default.jpg\",\"AceAmount\": 6853,\"CreateTime\": \"2024/3/26 9:29:21\",\"LevelNumber\": 3},{\"Id\": 6596133,\"FlowNumber\": \"1408435\",\"MemberNo\": \"Ltle0902-40\",\"IsMemberNo\": \"false\",\"EP\": 0,\"RP\": 0,\"SP\": 0,\"ULP\": 0,\"AvatarImage\": \"https://static.ace-accel.com/StaticResources/Avatar/default.jpg\",\"AceAmount\": 6853,\"CreateTime\": \"2024/3/26 9:29:06\",\"LevelNumber\": 3},{\"Id\": 6526509,\"FlowNumber\": \"1381266\",\"MemberNo\": \"Ltle0902-39\",\"IsMemberNo\": \"false\",\"EP\": 0,\"RP\": 0,\"SP\": 0,\"ULP\": 0,\"AvatarImage\": \"https://static.ace-accel.com/StaticResources/Avatar/default.jpg\",\"AceAmount\": 6853,\"CreateTime\": \"2024/3/20 10:35:24\",\"LevelNumber\": 3},{\"Id\": 6526271,\"FlowNumber\": \"1381181\",\"MemberNo\": \"Ltle0902-38\",\"IsMemberNo\": \"false\",\"EP\": 0,\"RP\": 0,\"SP\": 0,\"ULP\": 0,\"AvatarImage\": \"https://static.ace-accel.com/StaticResources/Avatar/default.jpg\",\"AceAmount\": 6853,\"CreateTime\": \"2024/3/20 10:21:25\",\"LevelNumber\": 3},{\"Id\": 6526242,\"FlowNumber\": \"1381168\",\"MemberNo\": \"Ltle0902-37\",\"IsMemberNo\": \"false\",\"EP\": 0,\"RP\": 0,\"SP\": 0,\"ULP\": 0,\"AvatarImage\": \"https://static.ace-accel.com/StaticResources/Avatar/default.jpg\",\"AceAmount\": 6853,\"CreateTime\": \"2024/3/20 10:19:38\",\"LevelNumber\": 3},{\"Id\": 6526239,\"FlowNumber\": \"1381166\",\"MemberNo\": \"Ltle0902-36\",\"IsMemberNo\": \"false\",\"EP\": 0,\"RP\": 0,\"SP\": 0,\"ULP\": 0,\"AvatarImage\": \"https://static.ace-accel.com/StaticResources/Avatar/default.jpg\",\"AceAmount\": 6853,\"CreateTime\": \"2024/3/20 10:19:21\",\"LevelNumber\": 3},{\"Id\": 6526209,\"FlowNumber\": \"1381151\",\"MemberNo\": \"Ltle0902-35\",\"IsMemberNo\": \"false\",\"EP\": 0,\"RP\": 0,\"SP\": 0,\"ULP\": 0,\"AvatarImage\": \"https://static.ace-accel.com/StaticResources/Avatar/default.jpg\",\"AceAmount\": 6853,\"CreateTime\": \"2024/3/20 10:17:59\",\"LevelNumber\": 3},{\"Id\": 5972933,\"FlowNumber\": \"1176993\",\"MemberNo\": \"Ltle0902-34\",\"IsMemberNo\": \"false\",\"EP\": 0,\"RP\": 0,\"SP\": 0,\"ULP\": 0,\"AvatarImage\": \"https://static.ace-accel.com/StaticResources/Avatar/default.jpg\",\"AceAmount\": 7281,\"CreateTime\": \"2024/2/16 12:13:12\",\"LevelNumber\": 3},{\"Id\": 5972853,\"FlowNumber\": \"1176964\",\"MemberNo\": \"Ltle0902-33\",\"IsMemberNo\": \"false\",\"EP\": 0,\"RP\": 0,\"SP\": 0,\"ULP\": 0,\"AvatarImage\": \"https://static.ace-accel.com/StaticResources/Avatar/default.jpg\",\"AceAmount\": 7281,\"CreateTime\": \"2024/2/16 12:12:54\",\"LevelNumber\": 3},{\"Id\": 4958981,\"FlowNumber\": \"831620\",\"MemberNo\": \"Ltle0902-32\",\"IsMemberNo\": \"false\",\"EP\": 0,\"RP\": 0,\"SP\": 0,\"ULP\": 0,\"AvatarImage\": \"https://static.ace-accel.com/StaticResources/Avatar/default.jpg\",\"AceAmount\": 11285,\"CreateTime\": \"2023/12/11 13:37:18\",\"LevelNumber\": 3},{\"Id\": 4958977,\"FlowNumber\": \"831618\",\"MemberNo\": \"Ltle0902-31\",\"IsMemberNo\": \"false\",\"EP\": 0,\"RP\": 0,\"SP\": 0,\"ULP\": 0,\"AvatarImage\": \"https://static.ace-accel.com/StaticResources/Avatar/default.jpg\",\"AceAmount\": 11285,\"CreateTime\": \"2023/12/11 13:36:59\",\"LevelNumber\": 3},{\"Id\": 4900414,\"FlowNumber\": \"813485\",\"MemberNo\": \"Ltle0902-30\",\"IsMemberNo\": \"false\",\"EP\": 0,\"RP\": 0,\"SP\": 0,\"ULP\": 0,\"AvatarImage\": \"https://static.ace-accel.com/StaticResources/Avatar/default.jpg\",\"AceAmount\": 11285,\"CreateTime\": \"2023/12/6 12:51:55\",\"LevelNumber\": 3},{\"Id\": 4900401,\"FlowNumber\": \"813481\",\"MemberNo\": \"Ltle0902-29\",\"IsMemberNo\": \"false\",\"EP\": 0,\"RP\": 0,\"SP\": 0,\"ULP\": 0,\"AvatarImage\": \"https://static.ace-accel.com/StaticResources/Avatar/default.jpg\",\"AceAmount\": 11285,\"CreateTime\": \"2023/12/6 12:51:31\",\"LevelNumber\": 3},{\"Id\": 4900391,\"FlowNumber\": \"813479\",\"MemberNo\": \"Ltle0902-28\",\"IsMemberNo\": \"false\",\"EP\": 0,\"RP\": 0,\"SP\": 0,\"ULP\": 0,\"AvatarImage\": \"https://static.ace-accel.com/StaticResources/Avatar/default.jpg\",\"AceAmount\": 11285,\"CreateTime\": \"2023/12/6 12:51:13\",\"LevelNumber\": 3},{\"Id\": 4900375,\"FlowNumber\": \"813471\",\"MemberNo\": \"Ltle0902-27\",\"IsMemberNo\": \"false\",\"EP\": 0,\"RP\": 0,\"SP\": 0,\"ULP\": 0,\"AvatarImage\": \"https://static.ace-accel.com/StaticResources/Avatar/default.jpg\",\"AceAmount\": 11285,\"CreateTime\": \"2023/12/6 12:50:50\",\"LevelNumber\": 3},{\"Id\": 4900364,\"FlowNumber\": \"813468\",\"MemberNo\": \"Ltle0902-26\",\"IsMemberNo\": \"false\",\"EP\": 0,\"RP\": 0,\"SP\": 0,\"ULP\": 0,\"AvatarImage\": \"https://static.ace-accel.com/StaticResources/Avatar/default.jpg\",\"AceAmount\": 11285,\"CreateTime\": \"2023/12/6 12:50:27\",\"LevelNumber\": 3},{\"Id\": 4837973,\"FlowNumber\": \"793998\",\"MemberNo\": \"Ltle0902-25\",\"IsMemberNo\": \"false\",\"EP\": 0,\"RP\": 0,\"SP\": 0,\"ULP\": 0,\"AvatarImage\": \"https://static.ace-accel.com/StaticResources/Avatar/default.jpg\",\"AceAmount\": 11285,\"CreateTime\": \"2023/11/30 20:08:37\",\"LevelNumber\": 3},{\"Id\": 4837964,\"FlowNumber\": \"793995\",\"MemberNo\": \"Ltle0902-24\",\"IsMemberNo\": \"false\",\"EP\": 0,\"RP\": 0,\"SP\": 0,\"ULP\": 0,\"AvatarImage\": \"https://static.ace-accel.com/StaticResources/Avatar/default.jpg\",\"AceAmount\": 11285,\"CreateTime\": \"2023/11/30 20:08:13\",\"LevelNumber\": 3},{\"Id\": 4837960,\"FlowNumber\": \"793992\",\"MemberNo\": \"Ltle0902-23\",\"IsMemberNo\": \"false\",\"EP\": 0,\"RP\": 0,\"SP\": 0,\"ULP\": 0,\"AvatarImage\": \"https://static.ace-accel.com/StaticResources/Avatar/default.jpg\",\"AceAmount\": 11285,\"CreateTime\": \"2023/11/30 20:07:55\",\"LevelNumber\": 3},{\"Id\": 4837957,\"FlowNumber\": \"793991\",\"MemberNo\": \"Ltle0902-22\",\"IsMemberNo\": \"false\",\"EP\": 0,\"RP\": 0,\"SP\": 0,\"ULP\": 0,\"AvatarImage\": \"https://static.ace-accel.com/StaticResources/Avatar/default.jpg\",\"AceAmount\": 11285,\"CreateTime\": \"2023/11/30 20:07:35\",\"LevelNumber\": 3},{\"Id\": 4837951,\"FlowNumber\": \"793988\",\"MemberNo\": \"Ltle0902-21\",\"IsMemberNo\": \"false\",\"EP\": 0,\"RP\": 0,\"SP\": 0,\"ULP\": 0,\"AvatarImage\": \"https://static.ace-accel.com/StaticResources/Avatar/default.jpg\",\"AceAmount\": 11285,\"CreateTime\": \"2023/11/30 20:07:06\",\"LevelNumber\": 3},{\"Id\": 4788082,\"FlowNumber\": \"778647\",\"MemberNo\": \"Ltle0902-20\",\"IsMemberNo\": \"false\",\"EP\": 0,\"RP\": 0,\"SP\": 0,\"ULP\": 0,\"AvatarImage\": \"https://static.ace-accel.com/StaticResources/Avatar/default.jpg\",\"AceAmount\": 11285,\"CreateTime\": \"2023/11/26 16:07:04\",\"LevelNumber\": 3},{\"Id\": 4788074,\"FlowNumber\": \"778644\",\"MemberNo\": \"Ltle0902-19\",\"IsMemberNo\": \"false\",\"EP\": 0,\"RP\": 0,\"SP\": 0,\"ULP\": 0,\"AvatarImage\": \"https://static.ace-accel.com/StaticResources/Avatar/default.jpg\",\"AceAmount\": 11285,\"CreateTime\": \"2023/11/26 16:06:41\",\"LevelNumber\": 3},{\"Id\": 4787442,\"FlowNumber\": \"778475\",\"MemberNo\": \"Ltle0902-18\",\"IsMemberNo\": \"false\",\"EP\": 0,\"RP\": 0,\"SP\": 0,\"ULP\": 0,\"AvatarImage\": \"https://static.ace-accel.com/StaticResources/Avatar/default.jpg\",\"AceAmount\": 11285,\"CreateTime\": \"2023/11/26 15:44:40\",\"LevelNumber\": 3},{\"Id\": 4787421,\"FlowNumber\": \"778470\",\"MemberNo\": \"Ltle0902-17\",\"IsMemberNo\": \"false\",\"EP\": 0,\"RP\": 0,\"SP\": 0,\"ULP\": 0,\"AvatarImage\": \"https://static.ace-accel.com/StaticResources/Avatar/default.jpg\",\"AceAmount\": 11285,\"CreateTime\": \"2023/11/26 15:44:08\",\"LevelNumber\": 3},{\"Id\": 4787408,\"FlowNumber\": \"778465\",\"MemberNo\": \"Ltle0902-16\",\"IsMemberNo\": \"false\",\"EP\": 0,\"RP\": 0,\"SP\": 0,\"ULP\": 0,\"AvatarImage\": \"https://static.ace-accel.com/StaticResources/Avatar/default.jpg\",\"AceAmount\": 11285,\"CreateTime\": \"2023/11/26 15:43:42\",\"LevelNumber\": 3},{\"Id\": 4728674,\"FlowNumber\": \"760357\",\"MemberNo\": \"Ltle0902-15\",\"IsMemberNo\": \"false\",\"EP\": 0,\"RP\": 0,\"SP\": 0,\"ULP\": 0,\"AvatarImage\": \"https://static.ace-accel.com/StaticResources/Avatar/default.jpg\",\"AceAmount\": 11285,\"CreateTime\": \"2023/11/21 16:27:56\",\"LevelNumber\": 3},{\"Id\": 4728672,\"FlowNumber\": \"760355\",\"MemberNo\": \"Ltle0902-14\",\"IsMemberNo\": \"false\",\"EP\": 0,\"RP\": 0,\"SP\": 0,\"ULP\": 0,\"AvatarImage\": \"https://static.ace-accel.com/StaticResources/Avatar/default.jpg\",\"AceAmount\": 11285,\"CreateTime\": \"2023/11/21 16:27:32\",\"LevelNumber\": 3},{\"Id\": 4728665,\"FlowNumber\": \"760351\",\"MemberNo\": \"Ltle0902-13\",\"IsMemberNo\": \"false\",\"EP\": 0,\"RP\": 0,\"SP\": 0,\"ULP\": 0,\"AvatarImage\": \"https://static.ace-accel.com/StaticResources/Avatar/default.jpg\",\"AceAmount\": 11285,\"CreateTime\": \"2023/11/21 16:27:01\",\"LevelNumber\": 3},{\"Id\": 4728659,\"FlowNumber\": \"760348\",\"MemberNo\": \"Ltle0902-12\",\"IsMemberNo\": \"false\",\"EP\": 0,\"RP\": 0,\"SP\": 0,\"ULP\": 0,\"AvatarImage\": \"https://static.ace-accel.com/StaticResources/Avatar/default.jpg\",\"AceAmount\": 11285,\"CreateTime\": \"2023/11/21 16:26:35\",\"LevelNumber\": 3},{\"Id\": 4728654,\"FlowNumber\": \"760345\",\"MemberNo\": \"Ltle0902-11\",\"IsMemberNo\": \"false\",\"EP\": 0,\"RP\": 0,\"SP\": 0,\"ULP\": 0,\"AvatarImage\": \"https://static.ace-accel.com/StaticResources/Avatar/default.jpg\",\"AceAmount\": 11285,\"CreateTime\": \"2023/11/21 16:26:10\",\"LevelNumber\": 3},{\"Id\": 4655753,\"FlowNumber\": \"738122\",\"MemberNo\": \"Ltle0902-10\",\"IsMemberNo\": \"false\",\"EP\": 0,\"RP\": 0,\"SP\": 0,\"ULP\": 0,\"AvatarImage\": \"https://static.ace-accel.com/StaticResources/Avatar/default.jpg\",\"AceAmount\": 11285,\"CreateTime\": \"2023/11/15 14:17:17\",\"LevelNumber\": 3},{\"Id\": 4655749,\"FlowNumber\": \"738121\",\"MemberNo\": \"Ltle0902-9\",\"IsMemberNo\": \"false\",\"EP\": 0,\"RP\": 0,\"SP\": 0,\"ULP\": 0,\"AvatarImage\": \"https://static.ace-accel.com/StaticResources/Avatar/default.jpg\",\"AceAmount\": 11285,\"CreateTime\": \"2023/11/15 14:16:56\",\"LevelNumber\": 3},{\"Id\": 4655744,\"FlowNumber\": \"738117\",\"MemberNo\": \"Ltle0902-8\",\"IsMemberNo\": \"false\",\"EP\": 0,\"RP\": 0,\"SP\": 0,\"ULP\": 0,\"AvatarImage\": \"https://static.ace-accel.com/StaticResources/Avatar/default.jpg\",\"AceAmount\": 11285,\"CreateTime\": \"2023/11/15 14:16:29\",\"LevelNumber\": 3},{\"Id\": 4655740,\"FlowNumber\": \"738115\",\"MemberNo\": \"Ltle0902-7\",\"IsMemberNo\": \"false\",\"EP\": 0,\"RP\": 0,\"SP\": 0,\"ULP\": 0,\"AvatarImage\": \"https://static.ace-accel.com/StaticResources/Avatar/default.jpg\",\"AceAmount\": 11285,\"CreateTime\": \"2023/11/15 14:16:06\",\"LevelNumber\": 3},{\"Id\": 4655736,\"FlowNumber\": \"738112\",\"MemberNo\": \"Ltle0902-6\",\"IsMemberNo\": \"false\",\"EP\": 0,\"RP\": 0,\"SP\": 0,\"ULP\": 0,\"AvatarImage\": \"https://static.ace-accel.com/StaticResources/Avatar/default.jpg\",\"AceAmount\": 11285,\"CreateTime\": \"2023/11/15 14:15:33\",\"LevelNumber\": 3},{\"Id\": 4596111,\"FlowNumber\": \"719406\",\"MemberNo\": \"Ltle0902-5\",\"IsMemberNo\": \"false\",\"EP\": 0,\"RP\": 0,\"SP\": 0,\"ULP\": 0,\"AvatarImage\": \"https://static.ace-accel.com/StaticResources/Avatar/default.jpg\",\"AceAmount\": 10281,\"CreateTime\": \"2023/11/10 20:58:06\",\"LevelNumber\": 3},{\"Id\": 4596100,\"FlowNumber\": \"719401\",\"MemberNo\": \"Ltle0902-4\",\"IsMemberNo\": \"false\",\"EP\": 0,\"RP\": 0,\"SP\": 0,\"ULP\": 0,\"AvatarImage\": \"https://static.ace-accel.com/StaticResources/Avatar/default.jpg\",\"AceAmount\": 10281,\"CreateTime\": \"2023/11/10 20:57:25\",\"LevelNumber\": 3},{\"Id\": 4596090,\"FlowNumber\": \"719393\",\"MemberNo\": \"Ltle0902-3\",\"IsMemberNo\": \"false\",\"EP\": 0,\"RP\": 0,\"SP\": 0,\"ULP\": 0,\"AvatarImage\": \"https://static.ace-accel.com/StaticResources/Avatar/default.jpg\",\"AceAmount\": 10281,\"CreateTime\": \"2023/11/10 20:55:47\",\"LevelNumber\": 3},{\"Id\": 4595870,\"FlowNumber\": \"719291\",\"MemberNo\": \"Ltle0902-2\",\"IsMemberNo\": \"false\",\"EP\": 0,\"RP\": 0,\"SP\": 0,\"ULP\": 0,\"AvatarImage\": \"https://static.ace-accel.com/StaticResources/Avatar/default.jpg\",\"AceAmount\": 10281,\"CreateTime\": \"2023/11/10 20:24:24\",\"LevelNumber\": 3},{\"Id\": 4595868,\"FlowNumber\": \"719290\",\"MemberNo\": \"Ltle0902-1\",\"IsMemberNo\": \"false\",\"EP\": 38,\"RP\": 0,\"SP\": 0,\"ULP\": 0,\"AvatarImage\": \"https://static.ace-accel.com/StaticResources/Avatar/default.jpg\",\"AceAmount\": 10281,\"CreateTime\": \"2023/11/10 20:23:57\",\"LevelNumber\": 3}]}}";
          log.info("售卖ak-查询子账户信息resq:{}", subAccountResponse);
          AkResp subAccountResp = JSON.parseObject(subAccountResponse, AkResp.class);
          DataDTO<SubAccountDetail> subAccountData = subAccountResp.getData();
          List<SubAccountDetail> subAccountDetailList = ((List<?>) subAccountData.getList()).stream()
                  .map(o -> JSON.toJavaObject((JSONObject) o, SubAccountDetail.class))
                  .collect(Collectors.toList());
          log.info("售卖ak-查询子账户条数:{}", subAccountDetailList.size());
          List<SubAccountDetail> gtSubAccountDetailList = subAccountDetailList.stream()
                  .filter(s -> s.getAceAmount() > GT_NUM)
                  .collect(Collectors.toList());
          log.info("售卖ak-子账户大于{}的条数:{}", GT_NUM, gtSubAccountDetailList.size());
          return gtSubAccountDetailList;
     }

     public static DmnemonicResp buildDmnemonic() {
          DmnemonicReq dmnemonicReq = new DmnemonicReq();
          log.info("售卖ak-查询助记词req:{}", JSON.toJSONString(dmnemonicReq));
          String dmnemonicResponse = HttpFormUtil.doPost(FIND_MNEMONIC_URL, dmnemonicReq, null);
//          String dmnemonicResponse = "{\"Error\":false,\"mnemonicid1\":\"3\",\"mnemonickey\":\"46edb9251861245ac0d9131fc0f1ab5d\",\"mnemonictitle\":\"--\"}";
          log.info("售卖ak-查询助记词resp:{}", dmnemonicResponse);
          DmnemonicResp dmnemonicResp = JSON.parseObject(dmnemonicResponse, DmnemonicResp.class);
          dmnemonicResp.setMnemonicstr1(findDmnemonicValue(dmnemonicResp.getMnemonicid1()));
          log.info("售卖ak-组装助记词resp:{}", JSON.toJSONString(dmnemonicResp));
          return dmnemonicResp;
     }

     public static String findDmnemonicValue(String id) {
          Map<String, String> map = new HashMap<>();
          map.put("1", "0404");
          map.put("2", "0075");
          map.put("3", "1204");
          map.put("4", "1555");
          map.put("5", "1001");
          map.put("6", "1536");
          map.put("7", "0390");
          map.put("8", "1314");
          map.put("9", "1678");
          map.put("10", "0692");
          map.put("11", "1234");
          map.put("12", "0081");
          return map.get(id);
     }

     public static void assembleTran(List<SubAccountDetail> gtSubAccountDetailList,
                                     DmnemonicResp dmnemonicResp, String gCode) {
          // 并发处理：3个线程，从队列中取数据并调用 buildTransaction
          int threadCount = 3;
          ExecutorService executor = Executors.newFixedThreadPool(threadCount);
          ConcurrentLinkedQueue<SubAccountDetail> queue = new ConcurrentLinkedQueue<>(gtSubAccountDetailList);
          CountDownLatch latch = new CountDownLatch(threadCount);

          for (int i = 0; i < threadCount; i++) {
               executor.submit(() -> {
                    try {
                         SubAccountDetail detail;
                         while ((detail = queue.poll()) != null) {
                              String traceId = UUID.randomUUID().toString();
                              try {
                                   TranReq tranReq = new TranReq();
                                   tranReq.setSonId(detail.getId());
                                   tranReq.setMnemonicid1(dmnemonicResp.getMnemonicid1());
                                   tranReq.setMnemonickey(dmnemonicResp.getMnemonickey());
                                   tranReq.setMnemonicstr1(dmnemonicResp.getMnemonicstr1());
                                   tranReq.setGCode(gCode);
                                   tranReq.setCount(detail.getAceAmount() - RETAIN_NUM);
                                   buildTransaction(tranReq, traceId);
                              } catch (Exception e) {
                                   log.error("处理子账户失败,traceId={}", traceId, e);
                              }
                         }
                    } finally {
                         latch.countDown();
                    }
               });
          }

          try {
               latch.await();
          } catch (InterruptedException e) {
               Thread.currentThread().interrupt();
               log.warn("等待线程被中断", e);
          } finally {
               executor.shutdownNow();
          }

     }

     public static void buildTransaction(TranReq tranReq, String traceId) {
          log.info("traceId={} 售卖ak-出售req:{}", traceId, JSON.toJSONString(tranReq));
          String tranResponse = HttpFormUtil.doPost(SALE_URL, tranReq, null);
//          String tranResponse = "";
          log.info("traceId={} 售卖ak-出售resp:{}", traceId, JSON.toJSONString(tranResponse));
     }
}
