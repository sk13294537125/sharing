package com.sharing.cn.converter;

import com.sharing.cn.domain.bo.BaseDataBo;
import com.sharing.cn.domain.pojo.BaseData;
import org.mapstruct.Mapper;

@Mapper(uses = {CustomConvertor.class})
public interface BaseDataConverter {

    /**
     *
     * @param bo
     * @return
     */
    BaseData converterPojo(BaseDataBo bo);

}
