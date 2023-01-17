package com.sharing.cn.common;

import java.io.Serializable;

/**
 * @author ext.shikai1
 */
public class BaseResponse  implements Serializable {

    private static final long serialVersionUID = -5829681510858415484L;

    private Boolean success;

    private String msg;

    private String msgCode;
}
