package com.sesc.rms.service.inter;

import com.sesc.rms.po.SysUserPo;
import com.sesc.rms.util.Result;

public interface UserService {
    SysUserPo login(String uname);
}
