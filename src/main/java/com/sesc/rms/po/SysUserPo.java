package com.sesc.rms.po;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@ToString
@Data
public class SysUserPo implements Serializable {

    private static final long serialVersionUID = -3869955386984896286L;

    private Integer uid;
    private String uname;
    private String password;

    private String phone;
    private String salt;
    private String email;


    private Date createDate;
    private Date updateDate;
    private int isDel;
}

