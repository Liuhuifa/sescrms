package com.sesc.rms.controller;

import com.github.pagehelper.PageInfo;
import com.sesc.rms.po.SysUserPo;
import com.sesc.rms.service.inter.SysRoleService;
import com.sesc.rms.service.inter.UserService;
import com.sesc.rms.util.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping("login")
    @ResponseBody
    public Object login(HttpServletRequest request,String username,String password){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username.trim(),password.trim());
        try {
            subject.login(token);
            SysUserPo user = service.login(username);
            request.getSession().setAttribute("user",user);
            return "200";
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            return "-1";
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            return "0";
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return "1";
        } catch (Exception e){
            e.printStackTrace();
            return "500";
        }
    }

    @GetMapping("listUser")
    @RequiresPermissions("system-manage")
    public ModelAndView listUser(@RequestParam(name = "pageNum",required = false,defaultValue = "1") Integer pageindex,
                           @RequestParam(name = "pageSize",required = false,defaultValue = "10") Integer pagesize){
        PageInfo<SysUserPo> sysUserPoPageInfo = service.listUser(pageindex, pagesize);
        ModelAndView mv = new ModelAndView("user_list");
        mv.addObject("datas",sysUserPoPageInfo);
        return mv;
    }

    /**
     * 添加用户
     * @param po
     * @param rids
     * @return
     */
    @PostMapping("addUser")
    @ResponseBody
    @RequiresPermissions("user-create")
    public Object addUser(SysUserPo po,Integer [] rids){
        return service.addUser(po,rids);
    }

    /**
     * 查询所有坐席(用户)
     * @return
     */
    @PostMapping("allUser")
    @ResponseBody
    public Object allUser(){
        return service.listUser(null, null);
    }

    /**
     * 查询指定角色的用户
     * @param rid
     * @param pageindex
     * @return
     */
    @GetMapping("listUserByRole/{rid}/{pageindex}/{uname}")
    @ResponseBody
    public Result listUserByRoleId(@PathVariable("rid") Integer rid,
                                   @PathVariable("pageindex") Integer pageindex,
                                   @PathVariable("uname")String uname){
        try {
            if (uname.equals("null")){
                uname=null;
            }
            Result result = service.listUserByRoleId(rid,uname,pageindex);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("服务器GG了");
        }
    }

    @PostMapping("del")
    @ResponseBody
    public Result del(@RequestParam Integer uid){
        return service.del(uid);
    }

    /**
     * 查询个人信息
     * @return
     */
    @GetMapping("info")
    public ModelAndView info(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("user/info");
        SysUserPo user = (SysUserPo) request.getSession().getAttribute("user");
        SysUserPo userPo = service.findByUid(user.getUid());
        mv.addObject("data",userPo);
        return mv;
    }

    /**
     * 修改个人信息
     * @param po
     * @return
     */
    @PostMapping("modify")
    @ResponseBody
    public Result modify(SysUserPo po,HttpServletRequest request){
        SysUserPo user = (SysUserPo) request.getSession().getAttribute("user");
        po.setUid(user.getUid());
        return service.modifyByUser(po);
    }

    /**
     * 修改密码
     * @param
     * @param request
     * @return
     */
    @PostMapping("modifyp")
    @ResponseBody
    public Result modifyp(String oldpassword,String newpassword,HttpServletRequest request){
        SysUserPo po = new SysUserPo();
        SysUserPo user = (SysUserPo) request.getSession().getAttribute("user");
        po.setUid(user.getUid());
        SimpleHash oldmd5 = new SimpleHash("md5",oldpassword, user.getSalt(),99);
        if (!oldmd5.toString().equals(user.getPassword())){
            Result r= new Result();
            r.setCode(-1);
            return r;
        }
        Long time = new Date().getTime();
        SimpleHash md5 = new SimpleHash("md5",newpassword, ByteSource.Util.bytes(time.toString()),99);
        po.setSalt(time.toString());
        po.setUpdateTime(new Date());
        po.setPassword(md5.toString());
        return service.modifyByUser(po);
    }


    /**
     * 登出
     * @return
     */
    @GetMapping("/logout")
    public String logout(){
        SecurityUtils.getSubject().logout();
        return "login";
    }
}
