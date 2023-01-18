package com.sharing.cn.service;

import com.sharing.cn.domain.bo.BaseDataBo;

import java.util.List;

/**
 * @author ext.shikai1
 */
public interface BaseDataService {

    /**
     * code
     * @param code
     * @return
     */
    List<BaseDataBo> findByCode(String code);

    /**
     * 通过父code 查询
     * @param code
     * @return
     */
    List<BaseDataBo> findByParentCode(String code);


}
