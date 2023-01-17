package com.sharing.cn.publicvo.juhe;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author ext.shikai1
 */
@Data
public class JuHeResult implements Serializable  {

    private static final long serialVersionUID = 2301912513489665786L;

    // 笑话参数
    public String content;
    public String hashId;
    public String updatetime;
    public Date unixtime;

    // 历史今天
    public String day;
    public String date;
    public String e_id;
    public String title;

    // 周公解梦
    public String id;
    public String fid;
    public String name;
    public String des;
    public List<String> list;


}
