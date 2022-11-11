package com.sharing.cn.fm.entity;

import cn.org.atool.fluent.mybatis.annotation.FluentMybatis;
import cn.org.atool.fluent.mybatis.annotation.LogicDelete;
import cn.org.atool.fluent.mybatis.annotation.TableField;
import cn.org.atool.fluent.mybatis.annotation.TableId;
import cn.org.atool.fluent.mybatis.base.RichEntity;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * BaseDataEntity: 数据映射实体定义
 *
 * @author Powered By Fluent Mybatis
 */
@SuppressWarnings({"rawtypes", "unchecked"})
@Data
@Accessors(
    chain = true
)
@EqualsAndHashCode(
    callSuper = false
)
@FluentMybatis(
    table = "base_data",
    schema = "sharing"
)
public class BaseDataEntity extends RichEntity {
  private static final long serialVersionUID = 1L;

  /**
   * 主键
   */
  @TableId("id")
  private Integer id;

  /**
   * 创建时间
   */
  @TableField(
      value = "created_time",
      insert = "now()"
  )
  private Date createdTime;

  /**
   * 修改时间
   */
  @TableField(
      value = "modified_time",
      insert = "now()",
      update = "now()"
  )
  private Date modifiedTime;

  /**
   */
  @TableField(
      value = "is_delete",
      insert = "0"
  )
  @LogicDelete
  private Boolean isDelete;

  /**
   * 枚举码
   */
  @TableField("code")
  private String code;

  /**
   * 修改人
   */
  @TableField("modifier")
  private String modifier;

  /**
   * 枚举值
   */
  @TableField("name")
  private String name;

  /**
   * 父级枚举值
   */
  @TableField("parent_code")
  private String parentCode;

  @Override
  public final Class entityClass() {
    return BaseDataEntity.class;
  }
}
