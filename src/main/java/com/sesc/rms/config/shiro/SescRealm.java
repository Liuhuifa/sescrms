package com.sesc.rms.config.shiro;

import com.sesc.rms.po.SysUserPo;
import com.sesc.rms.service.inter.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class SescRealm extends AuthorizingRealm {
    @Autowired
    private UserService service;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        String username = token.getUsername();
        SysUserPo user = service.login(username);
        if (user!=null){


        }else{
            return null;
        }
        String salt = user.getSalt();
        byte[] bytes = salt.getBytes();
        ByteSource salts = ByteSource.Util.bytes(bytes);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username,user.getPassword(),salts,this.getName());
        return info;
    }
}
