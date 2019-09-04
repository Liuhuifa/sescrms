package com.sesc.rms.controller;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.sesc.rms.po.SysRolePo;
import com.sesc.rms.service.inter.SysRoleService;
import com.sesc.rms.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("role")
public class RoleController {

    @Autowired
    private SysRoleService service;

    @PostMapping("listRoles")
    @ResponseBody
    public Object listRoles(@RequestParam(name = "uid",required = false,defaultValue = "-1")Integer uid,
                            @RequestParam(name = "flag",required = false,defaultValue = "-1")Integer flag){
        return service.listRoles(uid,flag);
    }

    /**
     * 跳转角色列表
     * @return
     */
    @GetMapping("listRoles1")
    public ModelAndView listRoles1(@RequestParam(name = "pageindex",required = false,defaultValue = "1")Integer pageindex,
                                  @RequestParam(name = "pagesize",required = false,defaultValue = "15")Integer pagesize){
        PageInfo<SysRolePo> info = service.listRoles(pageindex, pagesize, null);
        List<SysRolePo> list = info.getList();
        Map<String,Object> map = new HashMap<>();
        map.put("datas",list);
        map.put("info",info);
        ModelAndView mv = new ModelAndView("role/role_list",map);
        return mv;
    }

    /**
     * 添加角色
     * @RequestParam("rname")String rname,
     *                           @RequestParam("pers")List<Integer>pids
     * @return
     */
    @PostMapping("/addRole")
    @ResponseBody
    public Object addRole(HttpServletRequest request){
        String pers = request.getParameter("pers");
        String rname = request.getParameter("rname");

        List<Integer> list = JSONArray.parseArray(pers, Integer.class);
        List<Integer> newList = new ArrayList<>();
        for (int i=0;i<list.size();i++){
            Integer item = list.get(i);
            if (item!=null){
                newList.add(item);
            }
        }
        Integer pids[] = new Integer[newList.size()];
        Integer[] objects = newList.toArray(pids);
        for (int i=0;i<objects.length;i++){
            System.out.print(objects[i]+",");
        }
        SysRolePo po =new SysRolePo();
        po.setRname(rname);
        return service.addOne(po,objects);
    }
    @GetMapping("/selectOne")
    @ResponseBody
    public Result selectByName(SysRolePo po){
        return service.selectOne(po);
    }
    @PostMapping("/delOne")
    @ResponseBody
    public Result delOne(Integer rid){
        return service.del(rid);
    }
}
