package com.sharing.cn.dao.mapper;

import com.sharing.cn.domain.pojo.BaseData;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author shikai
 * @since 2022-01-13
 */
@Mapper
@Repository
public interface BaseDataMapper extends BaseMapper<BaseData> {

}
