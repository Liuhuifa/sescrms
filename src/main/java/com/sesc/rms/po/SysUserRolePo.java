package com.sesc.rms.po;

import lombok.Data;
import lombok.ToString;
import java.io.Serializable;
import java.util.*;
import java.lang.*;

@ToString
@Data
public class SysUserRolePo implements Serializable {
	private static final long serialVersionUID = 1L;

	public SysUserRolePo() {
	}

	public SysUserRolePo(Integer sysUserId, Integer sysRoleId) {
		this.sysUserId = sysUserId;
		this.sysRoleId = sysRoleId;
	}

	private Integer sysUserId;
	private Integer sysRoleId;
}