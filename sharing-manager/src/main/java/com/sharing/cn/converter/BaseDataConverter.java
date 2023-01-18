package com.sharing.cn.converter;

import com.sharing.cn.domain.bo.BaseDataBo;
import com.sharing.cn.domain.pojo.BaseData;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {CustomConvertor.class})
public interface BaseDataConverter {

    BaseDataConverter INSTANCE = Mappers.getMapper(BaseDataConverter.class);

    /**
     * bo to pojo
     * @param bo
     * @return
     */
    BaseData converterPojo(BaseDataBo bo);

    /**
     * pojo to bo
     * @param pojo
     * @return
     */
    BaseDataBo pojoToBo(BaseData pojo);

    /**
     * pojo to bo
     * @param pojo
     * @return
     */
    List<BaseDataBo> pojoToBo(List<BaseData> pojo);



}
