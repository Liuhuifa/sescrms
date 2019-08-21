package com.sesc.rms.controller;

import com.sesc.rms.po.SysUserRolePo;
import com.sesc.rms.service.inter.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("user-role")
public class SysUserRoleController {

    @Autowired
    private SysUserRoleService service;

    /**
     * 添加多个角色
     * @param rids
     * @param uid
     * @return
     */
    @PostMapping("addAny")
    public Object add(@RequestParam(name = "rids") Integer rids[],
                      @RequestParam(name = "uid") Integer uid){
        List<SysUserRolePo>lists = new ArrayList<>();
        for (int i=0;i<rids.length;i++){
            SysUserRolePo po = new SysUserRolePo(uid,rids[i]);
            lists.add(po);
        }
        return service.addAny(lists);
    }

    /**
     * 添加一个角色
     * @param rid
     * @param uid
     * @return
     */
    @PostMapping("addOne")
    @ResponseBody
    public Object addOne(@RequestParam(name = "rid") Integer rid,
                      @RequestParam(name = "uid") Integer uid){
        return service.addOne(new SysUserRolePo(uid,rid));
    }

    /**
     * 删除一个角色
     * @param rid
     * @param uid
     * @return
     */
    @PostMapping("del")
    @ResponseBody
    public Object del(@RequestParam(name = "rid") Integer rid,
                         @RequestParam(name = "uid") Integer uid){
        return service.del(uid, rid);
    }
}
