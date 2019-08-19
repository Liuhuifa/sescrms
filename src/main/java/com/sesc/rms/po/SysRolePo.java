package com.sesc.rms.po;

import lombok.Data;
import lombok.ToString;
import java.io.Serializable;
import java.util.*;
import java.lang.*;

@ToString
@Data
public class SysRolePo implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer rid;
	private String rname;
	private Date createTime;
	private Date updateTime;
	private Integer isDel;

	private List<SysPerPo> pers;
}