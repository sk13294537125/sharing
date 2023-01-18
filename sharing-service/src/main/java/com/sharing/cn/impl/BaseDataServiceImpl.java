package com.sharing.cn.impl;

import com.sharing.cn.domain.bo.BaseDataBo;
import com.sharing.cn.manager.BaseDataManager;
import com.sharing.cn.service.BaseDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ext.shikai1
 */
@Service
public class BaseDataServiceImpl implements BaseDataService {

    @Autowired
    private BaseDataManager baseDataManager;

    @Override
    public List<BaseDataBo> findByCode(String code) {
        return baseDataManager.findByCode(code);
    }

    @Override
    public List<BaseDataBo> findByParentCode(String code) {
        return baseDataManager.findByParentCode(code);
    }
}
