package com.sharing.cn;

import cn.org.atool.generator.FileGenerator;
import cn.org.atool.generator.annotation.Table;
import cn.org.atool.generator.annotation.Tables;

public class FluentMybatisGenerator {

    // 数据源 url
    public static final String url = "jdbc:mysql://127.0.0.1:3306/sharing?useUnicode=true&characterEncoding=utf8";

    // 数据库用户名
    public static final String username = "root";

    // 数据库密码
    public static final String password = "root";

    // 项目名
    public static String projectName = "sharing";

    public static void main(String[] args) {
        // 引用配置类，build方法允许有多个配置类
        FileGenerator.build(BaseDataFm.class);
    }

    @Tables(
            // 设置数据库连接信息
            url = url,
            driver = "com.mysql.jdbc.Driver",
            username = username,
            password = password,
            // 默认前面拼接当前项目路径  D:\workspace\sharing/sharing-domain/src/main/java/
            // Entity 实体类路径
            srcDir = "sharing-domain/src/main/java",
            // dao 接口和实现默认生成路径, 当srcDir有值时有效
            daoDir = "sharing-dao/src/main/java",
            // 生成文件的base package路径, 不包含 ".entity", ".dao"部分
            basePack = "com.sharing.cn.fm",
            // 如果有定义创建时间，修改时间，逻辑删除； 加对应字段
            gmtCreated = "created_time", gmtModified = "modified_time", logicDeleted = "is_delete",
            // 设置哪些表要生成Entity文件
            tables = {@Table(value = {"base_data"})})
    static class BaseDataFm { // 类名随便取, 只是配置定义的一个载体
    }

}
