package com.sesc.rms.po;

import lombok.Data;
import lombok.ToString;
import java.io.Serializable;
import java.util.*;
import java.lang.*;

@ToString
@Data
public class SysPerPo implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer pid;
	private String pname;
	private String permission;
	private Date createDate;
	private Date updateDate;
	private Integer isDel;
}