package com.sharing.cn.init;

import com.sharing.cn.domain.bo.BaseDataBo;
import com.sharing.cn.service.BaseDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author ext.shikai1
 */
@Slf4j
@Component
public class InitBaseData {

    @Autowired
    private BaseDataService baseDataService;

    @PostConstruct
    public void init(){
        List<BaseDataBo> all = baseDataService.findByCode(null);

    }
}
