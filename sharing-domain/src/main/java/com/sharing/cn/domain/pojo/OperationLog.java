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
 * @since 2022-08-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OperationLog extends Model<OperationLog> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 操作类型
     */
    private String bizType;

    /**
     * 请求数据
     */
    private String requestData;

    /**
     * 返回数据
     */
    private String responseData;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;


    public static final String ID = "id";

    public static final String BIZ_TYPE = "biz_type";

    public static final String REQUEST_DATA = "request_data";

    public static final String RESPONSE_DATA = "response_data";

    public static final String CREATE_BY = "create_by";

    public static final String CREATE_TIME = "create_time";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
