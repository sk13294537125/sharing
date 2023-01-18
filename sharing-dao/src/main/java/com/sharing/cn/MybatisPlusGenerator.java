package com.sharing.cn;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.sharing.cn.utils.SystemUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * MybatisPlusGenerator
 * MybatisPlus生成器
 *
 *  模板引擎
 *  <!-- 生成代码模板工具 -->
 *  <dependency>
 *      <groupId>org.apache.velocity</groupId>
 *      <artifactId>velocity-engine-core</artifactId>
 *      <version>2.3</version>
 *  </dependency>
 *
 *  mybatis-plus 三个版本要一致
 *  <!-- mybatis-plus -->
 *  <dependency>
 *      <groupId>com.baomidou</groupId>
 *      <artifactId>mybatis-plus-boot-starter</artifactId>
 *      <version>3.4.1</version>
 *  </dependency>
 *  <dependency>
 *      <groupId>com.baomidou</groupId>
 *      <artifactId>mybatis-plus-generator</artifactId>
 *      <version>3.4.1</version>
 *  </dependency>
 *  <dependency>
 *      <groupId>com.baomidou</groupId>
 *      <artifactId>mybatis-plus-extension</artifactId>
 *      <version>3.4.1</version>
 *  </dependency>
 * @author ext.shikai1
 */
@Slf4j
public class MybatisPlusGenerator {

    // 项目名
    public static String projectName = "sharing";

    // 表名
    public static String[] tableNameArray = {"base_data_backup"};

    /**
     * 执行Mybatis生成器
     */
    public static void main(String[] args) {
        String projectPath = SystemUtils.getProperty(SystemUtils.USER_DIR);
        for (ProjectModuleEnum projectModule : ProjectModuleEnum.values()) {
            StringBuilder pathBuilder = new StringBuilder(projectPath);
            pathBuilder.append("/").append(projectName).append("-").append(projectModule.getName());
            // 代码生成器
            AutoGenerator autoGenerator = new AutoGenerator();
            // 全局配置
            autoGenerator.setGlobalConfig(createGlobalConfig(pathBuilder.toString()));
            // 数据源配置
            autoGenerator.setDataSource(createDataSourceConfig());
            // 包配置
            PackageConfig packageConfig = createPackageConfig();
            autoGenerator.setPackageInfo(packageConfig);
            // 自定义配置
            autoGenerator.setCfg(createInjectionConfig(pathBuilder.toString(), projectModule, packageConfig));
            // 模版配置
            autoGenerator.setTemplate(createTemplateConfig(projectModule));
            // 策略配置
            autoGenerator.setStrategy(createStrategyConfig(tableNameArray, packageConfig.getModuleName()));
            autoGenerator.setTemplateEngine(new VelocityTemplateEngine());
            autoGenerator.execute();
        }
    }

    /**
     * 数据源配置
     *
     * @return 数据源配置
     */
    private static DataSourceConfig createDataSourceConfig() {
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl("jdbc:mysql://127.0.0.1:3306/sharing?characterEncoding=utf-8&useUnicode=true&autoReconnect=true&connectTimeout=3000&initialTimeout=1&socketTimeout=5000&useSSL=false&serverTimezone=CTT");
        dataSourceConfig.setDriverName("com.mysql.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("root");
        return dataSourceConfig;
    }

    /**
     * 包路径配置
     * @return
     */
    public static PackageConfig createPackageConfig() {
        // 包配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.sharing.cn")
                .setService("dao.service")
                .setServiceImpl("dao.service.impl")
                .setMapper("dao.mapper")
                .setEntity("domain.pojo");
        return packageConfig;
    }


    /**
     * 策略配置
     *
     * @param tableNameArray 表名
     * @param moduleName     模块名
     * @return 策略配置
     */
    private static StrategyConfig createStrategyConfig(String[] tableNameArray, String moduleName) {
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setInclude(tableNameArray);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setEntityColumnConstant(true);
        strategy.setTablePrefix(moduleName + "_");
        return strategy;
    }

    /**
     * 模版配置
     *
     * @return 模版配置
     */
    private static TemplateConfig createTemplateConfig(ProjectModuleEnum projectModuleEnum) {
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        templateConfig.setController(null);
        if (ProjectModuleEnum.DOMAIN.equals(projectModuleEnum)) {
            templateConfig.setService(null);
            templateConfig.setServiceImpl(null);
            templateConfig.setMapper(null);
            templateConfig.setXml(null);
        } else if (ProjectModuleEnum.DAO.equals(projectModuleEnum)) {
            templateConfig.setEntity(null);
        }
        return templateConfig;
    }

    /**
     * 自定义配置
     *
     * @return 自定义配置
     */
    private static InjectionConfig createInjectionConfig(String projectPath, ProjectModuleEnum projectModuleEnum, PackageConfig packageConfig) {
        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {
            }
        };
        if (ProjectModuleEnum.DOMAIN.equals(projectModuleEnum)) {
            return injectionConfig;
        }
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 如果模板引擎是 velocity
        String templatePath = "/templates/mapper.xml.vm";
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String xmlPath = packageConfig.getParent().replace(StringPool.DOT, StringPool.SLASH) + "/" + packageConfig.getMapper().replace(StringPool.DOT, StringPool.SLASH);
                // 自定义输出文件名,如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/" + xmlPath + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        injectionConfig.setFileOutConfigList(focList);
        return injectionConfig;
    }

    /**
     * 全局配置
     *
     * @return 全局配置
     */
    private static GlobalConfig createGlobalConfig(String projectPath) {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(projectPath + "/src/main/java");
        globalConfig.setAuthor("shikai");
        globalConfig.setOpen(false);
        globalConfig.setFileOverride(true);
        globalConfig.setBaseResultMap(true);
        globalConfig.setBaseColumnList(true);
        globalConfig.setDateType(DateType.ONLY_DATE);
        globalConfig.setIdType(IdType.AUTO);
        globalConfig.setActiveRecord(true);
        globalConfig.setServiceName("%sDao");
        globalConfig.setServiceImplName("%sDaoImpl");
        return globalConfig;
    }

    /**
     * 项目模块枚举
     */
    @Getter
    @AllArgsConstructor
    public enum ProjectModuleEnum {
        /**
         * Domain
         */
        DOMAIN("domain"),
        /**
         * Dao
         */
        DAO("dao");
        /**
         * 项目模块名称
         */
        private final String name;
    }
}
