package com.sharing.cn.domain.bo;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author shikai
 * @since 2022-01-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BaseDataBo extends Model<BaseDataBo> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    private Integer isDelete;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 修改人
     */
    private String modifier;

    /**
     * 修改时间
     */
    private Date modifiedTime;

    /**
     * 枚举码
     */
    private String code;

    /**
     * 枚举值
     */
    private String name;

    /**
     * 父级枚举值
     */
    private String parentCode;

}
