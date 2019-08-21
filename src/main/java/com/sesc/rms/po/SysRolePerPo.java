package com.sesc.rms.po;

import lombok.Data;
import lombok.ToString;
import java.io.Serializable;
import java.util.*;
import java.lang.*;

@ToString
@Data
public class SysRolePerPo implements Serializable {
	private static final long serialVersionUID = 1L;

	public SysRolePerPo() {
	}

	public SysRolePerPo(Integer perRoleId, Integer perPerId) {
		this.perRoleId = perRoleId;
		this.perPerId = perPerId;
	}

	private Integer perRoleId;
	private Integer perPerId;
}