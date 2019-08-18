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

	private Integer sysUserId;
	private Integer sysRoleId;
}