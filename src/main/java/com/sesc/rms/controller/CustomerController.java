package com.sesc.rms.controller;

import com.github.pagehelper.PageInfo;
import com.sesc.rms.dao.CustomerMapper;
import com.sesc.rms.po.CustomerPo;
import com.sesc.rms.po.SysUserPo;
import com.sesc.rms.service.inter.CustomerService;
import com.sesc.rms.util.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("customer")
public class CustomerController{
    @Resource
    private CustomerService service;

    @GetMapping("list")
    public ModelAndView listCustomers(@RequestParam(required = false,defaultValue = "-2") Integer group,
                                      @RequestParam(required = false,defaultValue = "-3") Integer belongs,
                                      @RequestParam(required = false) String name,
                                      @RequestParam(required = false) String tel,
                                      @RequestParam(required = false) String cfrom,
                                      @RequestParam(required = false) String address,
                                      @RequestParam(required = false,defaultValue = "1") Integer pageindex,
                                      HttpServletRequest request) {
        CustomerPo po=null;
        Subject subject = SecurityUtils.getSubject();
        boolean sub = subject.hasRole("超级管理员");

        if (name !=null && !name.equals("")){
            name=name.trim();
        }
        if (tel !=null && !tel.equals("")){
            tel=tel.trim();
        }
        if (cfrom !=null && !cfrom.equals("")){
            cfrom=cfrom.trim();
        }
        if (address !=null && !address.equals("")){
            address=address.trim();
        }
        if (sub){
             po = new CustomerPo(name,address,tel,belongs,cfrom,pageindex,group);
        }else{
            SysUserPo user = (SysUserPo)request.getSession().getAttribute("user");
            po = new CustomerPo(name,address,tel,user.getUid(),cfrom,pageindex,1);
        }
        request.getSession().setAttribute("solr",po);
        System.out.println(po);
        PageInfo<CustomerPo> infos = service.listCustomers(po);
        ModelAndView mv= new ModelAndView("/client/client-list");
        mv.addObject("datas",infos);
        mv.addObject("solr",(CustomerPo)request.getSession().getAttribute("solr"));
        return mv;
    }
    @PostMapping("addCustomer")
    @ResponseBody
    public Result addCustomer(@RequestParam String name,
                              @RequestParam String address,
                              @RequestParam String tel,
                              @RequestParam(required = false) String qq,
                              @RequestParam(required = false) String email,
                              @RequestParam String cfrom){
        if (qq!=null &&  !qq.equals("")){
            qq=qq.trim();
        }
        if (email!=null && !email.equals("")){
            email=email.trim();
        }
        CustomerPo po = new CustomerPo();
        po.setName(name);
        po.setAddress(address);
        po.setTel(tel);
        po.setQq(qq);
        po.setEmail(email);
        po.setCfrom(cfrom);
        po.setBelongs(0);
        return service.addCustomer(po);
    }
    @PostMapping("/findOneByCustomer")
    @ResponseBody
    public Result findOneByCustomer(CustomerPo po){
        return service.findOneCustomer(po);
    }
}
