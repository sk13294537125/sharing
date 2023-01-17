package com.sharing.cn.publicvo.juhe;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ext.shikai1
 */
@Data
public class JuHe<T> implements Serializable {

    private static final long serialVersionUID = 2301912513489665785L;

    public String reason;

    public T result;

    public String error_code;
}
