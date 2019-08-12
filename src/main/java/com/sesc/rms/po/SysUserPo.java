package com.sesc.rms.po;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@ToString
@Data
public class SysUserPo implements Serializable {

    private static final long serialVersionUID = -3869955386984896286L;

    private Integer id;
    private String username;
    private String password;

    private String phone;
    private String salt;
    private Date createDate;

    private Date updateDate;
    private int isDel;
}

