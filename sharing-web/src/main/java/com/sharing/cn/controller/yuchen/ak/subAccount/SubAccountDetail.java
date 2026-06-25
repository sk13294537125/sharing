package com.sharing.cn.controller.yuchen.ak.subAccount;

import lombok.Data;

/**
 * 子账户详情
 * {
 * "Id": 4958981,
 * "FlowNumber": "831620",
 * "MemberNo": "Ltle0902-32",
 * "IsMemberNo": "false",
 * "EP": 0,
 * "RP": 0,
 * "SP": 0,
 * "ULP": 0,
 * "AvatarImage": "https://static.ace-accel.com/StaticResources/Avatar/default.jpg",
 * "AceAmount": 11285,
 * "CreateTime": "2023/12/11 13:37:18",
 * "LevelNumber": 3
 * }
 */
@Data
public class SubAccountDetail {

     private Integer Id;
     private String FlowNumber;
     private String MemberNo;
     private String IsMemberNo;
     private Integer EP;
     private Integer RP;
     private Integer SP;
     private Integer ULP;
     private String AvatarImage;
     private Integer AceAmount;
     private String CreateTime;
     private String LevelNumber;
}
