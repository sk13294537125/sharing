package com.sharing.cn.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sharing.cn.common.Ordering;
import com.sharing.cn.common.PageQuery;
import com.sharing.cn.common.Res;
import com.sharing.cn.common.Sort;
import com.sharing.cn.domain.bo.BaseDataBo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("excel")
public class ExcelController {

    public void export(String str) {

    }


    public void importFile() {

    }

    public static void main(String[] args) {
        Integer p = 1;
        Res query = new Res();
        PageQuery<Res> reqVo = new PageQuery<Res>();
        p = p == null ? 1 : p;
        reqVo.setPageNo(p);
        reqVo.setPageSize(20);
        reqVo.setQuery(query);
        Sort sort = new Sort();
        sort.setSortKey("id");
        sort.setOrdering(Ordering.DESC);
        List<Sort> sortList = new ArrayList<>();
        sortList.add(sort);
        reqVo.setSortList(sortList);
        System.out.println(JSON.toJSONString(reqVo));
    }
}
