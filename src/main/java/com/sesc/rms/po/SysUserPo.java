package com.sesc.rms.po;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@ToString
@Data
public class SysUserPo implements Serializable {

    private static final long serialVersionUID = -3869955386984896286L;

    private Integer uid;
    private String realName;
    private String uname;
    private String password;

    private String phone;
    private String salt;
    private String email;


    private Date createTime;
    private Date updateTime;
    private int isDel;

    private List<SysRolePo> roles;
}

