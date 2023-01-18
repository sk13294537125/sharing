package com.sharing.cn.manager.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sharing.cn.converter.BaseDataConverter;
import com.sharing.cn.dao.service.BaseDataDao;
import com.sharing.cn.domain.bo.BaseDataBo;
import com.sharing.cn.domain.pojo.BaseData;
import com.sharing.cn.manager.BaseDataManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ext.shikai1
 */
@Service
public class BaseDataManagerImpl implements BaseDataManager {

    @Autowired
    private BaseDataDao baseDataDao;

    @Override
    public List<BaseDataBo> findByCode(String code) {
        QueryWrapper<BaseData> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(code), BaseData.CODE, code);
        return BaseDataConverter.INSTANCE.pojoToBo(baseDataDao.list(wrapper));
    }

    @Override
    public List<BaseDataBo> findByParentCode(String code) {
        QueryWrapper<BaseData> wrapper = new QueryWrapper<>();
        wrapper.eq(BaseData.PARENT_CODE, code);
        return BaseDataConverter.INSTANCE.pojoToBo(baseDataDao.list(wrapper));
    }


}
