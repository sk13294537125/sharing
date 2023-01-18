package com.sharing.cn.domain.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author shikai
 * @since 2023-01-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BaseDataBackup extends Model<BaseDataBackup> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
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

    private String tss;


    public static final String ID = "id";

    public static final String IS_DELETE = "is_delete";

    public static final String CREATED_TIME = "created_time";

    public static final String MODIFIER = "modifier";

    public static final String MODIFIED_TIME = "modified_time";

    public static final String CODE = "code";

    public static final String NAME = "name";

    public static final String PARENT_CODE = "parent_code";

    public static final String TSS = "tss";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
