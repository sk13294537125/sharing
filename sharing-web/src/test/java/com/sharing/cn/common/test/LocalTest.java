package com.sharing.cn.common.test;

import com.alibaba.fastjson.JSON;
import com.sharing.cn.common.dto.tianyan.ChildrenResult;
import com.sharing.cn.common.dto.tianyan.ParentResult;
import com.sharing.cn.common.dto.tianyan.VerifyResult;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class LocalTest {

    @Test
    public void test() {
        String data = getData();
        ParentResult parentResult = JSON.parseObject(data, ParentResult.class);
        VerifyResult result = parentResult.getResult();
        List<ChildrenResult> childrenResults = result.getData();

        // 递归 查询四级
    }

    public boolean getFour(ChildrenResult childrenResult) {
        if ("4".equals(childrenResult.getLevel())) {
            return true;
        }
        return false;
    }


    public static String getData() {
        return "{\"Status\":\"200\",\"Message\":\"【有效请求】查询成功\",\"OrderNumber\":\"ECIINVESTMENTTHROUGHLEVEL2023071914453615054681\",\n" +
                "        \"Result\":{\"VerifyResult\":1,\"Data\":\n" +
                "    [\n" +
                "                {\n" +
                "                    \"Name\":\"道达尔能源投资管理（天津）有限公司\",\n" +
                "                    \"Level\":\"1\",\n" +
                "                    \"Percent\":\"100%\",\n" +
                "                    \"ShouldCapi\":\"33549万元\",\n" +
                "                    \"ChildrenList\":\n" +
                "                [\n" +
                "                        {\n" +
                "                            \"Name\":\"道达尔能源投资（天津）有限公司\",\n" +
                "                            \"Level\":\"2\",\n" +
                "                            \"Percent\":\"100%\",\n" +
                "                            \"ShouldCapi\":\"33549万元\",\n" +
                "                            \"ChildrenList\":[\n" +
                "                                    {\n" +
                "                                        \"Name\":\"湖北凯辉智慧新能源基金合伙企业（有限合伙）\",\n" +
                "                                        \"Level\":\"3\",\n" +
                "                                        \"Percent\":\"49.0985%\",\n" +
                "                                        \"ShouldCapi\":\"33549万元\",\n" +
                "                                        \"ChildrenList\":[\n" +
                "                                                {\n" +
                "                                                    \"Name\":\"昆山协鑫光电材料有限公司\",\n" +
                "                                                    \"Level\":\"4\",\n" +
                "                                                    \"Percent\":\"10.1865%\",\n" +
                "                                                    \"ShouldCapi\":\"933.33408万元\",\n" +
                "                                                    \"ChildrenList\":[]\n" +
                "                                                },\n" +
                "                                                {\n" +
                "                                                    \"Name\":\"江苏擎天工业互联网有限公司\",\n" +
                "                                                    \"Level\":\"4\",\n" +
                "                                                    \"Percent\":\"8.7912%\",\n" +
                "                                                    \"ShouldCapi\":\"100万元\",\n" +
                "                                                    \"ChildrenList\":[]\n" +
                "                                                },\n" +
                "                                                {\n" +
                "                                                    \"Name\":\"北京如实智慧电力科技有限公司\",\n" +
                "                                                    \"Level\":\"4\",\n" +
                "                                                    \"Percent\":\"7.4999%\",\n" +
                "                                                    \"ShouldCapi\":\"81.08万元\",\n" +
                "                                                    \"ChildrenList\":[]\n" +
                "                                                },\n" +
                "                                                {\n" +
                "                                                    \"Name\":\"上海全应科技有限公司\",\n" +
                "                                                    \"Level\":\"4\",\n" +
                "                                                    \"Percent\":\"5.423%\",\n" +
                "                                                    \"ShouldCapi\":\"39.5296万元\",\n" +
                "                                                    \"ChildrenList\":[]\n" +
                "                                                },\n" +
                "                                                {\n" +
                "                                                    \"Name\":\"高频（北京）科技股份有限公司\",\n" +
                "                                                    \"Level\":\"4\",\n" +
                "                                                    \"Percent\":\"4.0204%\",\n" +
                "                                                    \"ShouldCapi\":\"144.1732万元\",\n" +
                "                                                    \"ChildrenList\":[]\n" +
                "                                                },\n" +
                "                                                {\n" +
                "                                                    \"Name\":\"湖北融通高科先进材料集团股份有限公司\",\n" +
                "                                                    \"Level\":\"4\",\n" +
                "                                                    \"Percent\":\"0.7518%\",\n" +
                "                                                    \"ShouldCapi\":\"1000万元\",\n" +
                "                                                    \"ChildrenList\":[]\n" +
                "                                                },\n" +
                "                                                {\n" +
                "                                                    \"Name\":\"湖北易能蓝域能源科技有限公司\",\n" +
                "                                                    \"Level\":\"4\",\n" +
                "                                                    \"Percent\":\"8.8073%\",\n" +
                "                                                    \"ShouldCapi\":\"1600万元\",\n" +
                "                                                    \"ChildrenList\":[]\n" +
                "                                                }\n" +
                "                                                        ]\n" +
                "                                    }\n" +
                "                                            ]\n" +
                "                        }\n" +
                "                ]\n" +
                "                },\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "                {\n" +
                "                    \"Name\":\"道达尔能源（北京）企业管理有限公司\",\n" +
                "                    \"Level\":\"1\",\n" +
                "                    \"Percent\":\"100%\",\n" +
                "                    \"ShouldCapi\":\"1000万元\",\n" +
                "                    \"ChildrenList\":[]},\n" +
                "                {\n" +
                "                    \"Name\":\"道达尔能源销售（上海）有限公司\",\n" +
                "                    \"Level\":\"1\",\n" +
                "                    \"Percent\":\"100%\",\n" +
                "                    \"ShouldCapi\":\"1000万元\",\n" +
                "                    \"ChildrenList\":[]},\n" +
                "                {\n" +
                "                    \"Name\":\"道达尔能源销售（湖北）有限公司\",\n" +
                "                    \"Level\":\"1\",\n" +
                "                    \"Percent\":\"100%\",\n" +
                "                    \"ShouldCapi\":\"983万美元\",\n" +
                "                    \"ChildrenList\":[]},\n" +
                "                {\n" +
                "                    \"Name\":\"道达尔能源石化（上海）有限责任公司\",\n" +
                "                    \"Level\":\"1\",\n" +
                "                    \"Percent\":\"100%\",\n" +
                "                    \"ShouldCapi\":\"700万元\",\n" +
                "                    \"ChildrenList\":[]},\n" +
                "                {\n" +
                "                    \"Name\":\"道达尔润滑油（中国）有限公司\",\n" +
                "                    \"Level\":\"1\",\n" +
                "                    \"Percent\":\"77%\",\n" +
                "                    \"ShouldCapi\":\"1145.0208万美元\",\n" +
                "                    \"ChildrenList\":[\n" +
                "                            {\n" +
                "                                \"Name\":\"广州埃尔夫润滑油有限公司\",\n" +
                "                                \"Level\":\"2\",\n" +
                "                                \"Percent\":\"100%\",\n" +
                "                                \"ShouldCapi\":\"10030.3737万元\",\n" +
                "                                \"ChildrenList\":[]\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"Name\":\"道达尔（天津）工业有限公司\",\n" +
                "                                \"Level\":\"2\",\n" +
                "                                \"Percent\":\"100%\",\n" +
                "                                \"ShouldCapi\":\"7593.7488万元\",\n" +
                "                                \"ChildrenList\":[]\n" +
                "                            }\n" +
                "                                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"Name\":\"长峡快道充电科技（湖北）有限公司\",\n" +
                "                    \"Level\":\"1\",\n" +
                "                    \"Percent\":\"50%\",\n" +
                "                    \"ShouldCapi\":\"35000万元\",\n" +
                "                    \"ChildrenList\":[]},\n" +
                "                {\n" +
                "                    \"Name\":\"中化道达尔油品有限公司\",\n" +
                "                    \"Level\":\"1\",\n" +
                "                    \"Percent\":\"49%\",\n" +
                "                    \"ShouldCapi\":\"23135.84万元\",\n" +
                "                    \"ChildrenList\":[\n" +
                "                            {\n" +
                "                                \"Name\":\"中化道达尔燃油有限公司\",\n" +
                "                                \"Level\":\"2\",\n" +
                "                                \"Percent\":\"100%\",\n" +
                "                                \"ShouldCapi\":\"51500万元\",\n" +
                "                                \"ChildrenList\":[\n" +
                "                                        {\n" +
                "                                            \"Name\":\"中化道达尔河北石化有限公司\",\n" +
                "                                            \"Level\":\"3\",\n" +
                "                                            \"Percent\":\"100%\",\n" +
                "                                            \"ShouldCapi\":\"3000万元\",\n" +
                "                                            \"ChildrenList\":[]\n" +
                "                                        }\n" +
                "                                                ]\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"Name\":\"中化道达尔浙江石油销售有限公司\",\n" +
                "                                \"Level\":\"2\",\n" +
                "                                \"Percent\":\"100%\",\n" +
                "                                \"ShouldCapi\":\"5000万元\",\n" +
                "                                \"ChildrenList\":[]\n" +
                "                            },\n" +
                "                            {\n" +
                "                                 \"Name\":\"太仓万丰加油站有限公司\",\n" +
                "                                 \"Level\":\"2\",\n" +
                "                                 \"Percent\":\"100%\",\n" +
                "                                 \"ShouldCapi\":\"300万元\",\n" +
                "                                 \"ChildrenList\":[]\n" +
                "                            }\n" +
                "                                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"Name\":\"道达尔石油（广州）有限公司\",\n" +
                "                    \"Level\":\"1\",\n" +
                "                    \"Percent\":\"100%\",\n" +
                "                    \"ShouldCapi\":\"86万美元\",\n" +
                "                    \"ChildrenList\":[]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"Name\":\"大连道达尔咨询有限公司\",\n" +
                "                    \"Level\":\"1\",\n" +
                "                    \"Percent\":\"100%\",\n" +
                "                    \"ShouldCapi\":\"15万美元\",\n" +
                "                    \"ChildrenList\":[]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"Name\":\"上海招商局道达尔液化气咨询有限责任公司\",\n" +
                "                    \"Level\":\"1\",\n" +
                "                    \"Percent\":\"50%\",\n" +
                "                    \"ShouldCapi\":\"20万美元\",\n" +
                "                    \"ChildrenList\":[]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"Name\":\"浙江浙石油海洋燃料有限公司\",\n" +
                "                    \"Level\":\"1\",\n" +
                "                    \"Percent\":\"49%\",\n" +
                "                    \"ShouldCapi\":\"2450万元\",\n" +
                "                    \"ChildrenList\":[]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"Name\":\"金湖招商局道达尔液化气有限公司\",\n" +
                "                    \"Level\":\"1\",\n" +
                "                    \"Percent\":\"47.25%\",\n" +
                "                    \"ShouldCapi\":\"103.95万元\",\n" +
                "                    \"ChildrenList\":[]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"Name\":\"烟台招商局-道达尔石油化工有限公司\",\n" +
                "                    \"Level\":\"1\",\n" +
                "                    \"Percent\":\"44%\",\n" +
                "                    \"ShouldCapi\":\"3146万元\",\n" +
                "                    \"ChildrenList\":[]\n" +
                "                }\n" +
                "    ]\n" +
                "}\n" +
                "}";
    }
}
