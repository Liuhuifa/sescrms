package com.sesc.rms.config.shiro;

import com.sesc.rms.po.SysPerPo;
import com.sesc.rms.po.SysRolePo;
import com.sesc.rms.po.SysUserPo;
import com.sesc.rms.service.inter.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SescRealm extends AuthorizingRealm {
    Logger LOGGER = LoggerFactory.getLogger(SescRealm.class);
    @Autowired
    @Lazy
    private UserService service;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        执行授权
        System.out.println("执行授权>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        List list = principalCollection.asList();
//        只有一个用户登录所以直接取出来
        String o = (String) list.get(0);
        SysUserPo users = service.listRoleAndPer(o);
        SimpleAuthorizationInfo infos = new SimpleAuthorizationInfo();
        List<SysRolePo> roles = users.getRoles();
        Set<String> roleNames = new HashSet<>();//角色名
        Set<String> perNames = new HashSet<>();//权限名
//        添加角色
        roles.stream().forEach(item->{
            roleNames.add(item.getRname());
//            添加权限
            List<SysPerPo> pers = item.getPers();
            pers.stream().forEach(its->{
                System.out.println(its.getPermission()+"拥有的权限");
                perNames.add(its.getPermission());
            });
        });

        infos.setRoles(roleNames);
        infos.setStringPermissions(perNames);

        return infos;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证>>>>>>>>>>>>>>>>>>");
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        String username = token.getUsername();


        DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
        DefaultWebSessionManager sessionManager = (DefaultWebSessionManager)securityManager.getSessionManager();
        Collection<Session> sessions = sessionManager.getSessionDAO().getActiveSessions();//获取当前已登录的用户session列表
        for(Session session:sessions){
            //清除该用户以前登录时保存的session
            if(username.equals(String.valueOf(session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY)))) {
                sessionManager.getSessionDAO().delete(session);
            }
        }


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
