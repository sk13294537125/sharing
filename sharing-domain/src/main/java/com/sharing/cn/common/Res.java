package com.sharing.cn.common;

import com.sharing.cn.enums.ResultCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 公共返回类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Res implements Serializable {

    private static final long serialVersionUID = 1;

    /**
     * 是否成功
     */
    private boolean success = true;
    /**
     * 代码
     */
    private String resultCode;
    /**
     * 描述
     */
    private String resultMsg;

    /**
     * 赋值返回公共字段
     *
     * @param codeEnum 返回枚举
     */
    public void setResultCodeEnum(ResultCodeEnum codeEnum) {
        this.success = ResultCodeEnum.SUCCESS.equals(codeEnum);
        this.resultCode = codeEnum.getCode();
        this.resultMsg = codeEnum.getMsg();
    }

    /**
     * 自定义异常
     *
     * @param codeEnum 状态码
     * @param msg 返回提示
     */
    public void definedResult(ResultCodeEnum codeEnum, String msg) {
        this.success = ResultCodeEnum.SUCCESS.equals(codeEnum);
        this.resultCode = codeEnum.getCode();
        this.resultMsg = codeEnum.getMsg();
    }



}
