package com.sesc.rms.controller;

import com.github.pagehelper.PageInfo;
import com.sesc.rms.dao.CustomerMapper;
import com.sesc.rms.po.CustomerPo;
import com.sesc.rms.service.inter.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ModelAndView listCustomers(  @RequestParam(required = false,defaultValue = "-2") Integer group,
                                        @RequestParam(required = false,defaultValue = "-3") Integer belongs,
                                        @RequestParam(required = false) String name,
                                        @RequestParam(required = false) String tel,
                                        @RequestParam(required = false) String cfrom,
                                        @RequestParam(required = false) String address,
                                        @RequestParam(required = false,defaultValue = "1") Integer pageindex,
                                        HttpServletRequest request) {
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
        CustomerPo po = new CustomerPo(name,address,tel,belongs,cfrom,pageindex,group);
        request.getSession().setAttribute("solr",po);
        System.out.println(po);
        PageInfo<CustomerPo> infos = service.listCustomers(po);
        ModelAndView mv= new ModelAndView("/client/client-list");
        mv.addObject("datas",infos);
        mv.addObject("solr",(CustomerPo)request.getSession().getAttribute("solr"));
        return mv;
    }
}
