package com.sesc.rms.controller;

import com.sesc.rms.po.CustomerLogPo;
import com.sesc.rms.po.CustomerPo;
import com.sesc.rms.po.SysUserPo;
import com.sesc.rms.service.inter.CustomerLogService;
import com.sesc.rms.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("customer-log")
public class CustomerLogController {

    @Autowired
    private CustomerLogService service;

    @PostMapping("add")
    public Result add(String name,
                      String address,
                      String tel,
                      String cfrom,
                      @RequestParam(required = false) String qq,
                      @RequestParam(required = false) String email,
                      Integer money,
                      Integer rate,
                      Integer caim,
                      String question,
                      String remark,
                      HttpServletRequest request
                      ){
        SysUserPo user = (SysUserPo)request.getSession().getAttribute("user");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CustomerPo customerPo = new CustomerPo(name,address,qq,tel,email,1,caim,cfrom,format.format(new Date()),rate,remark);
        if (rate==2){
            customerPo.setStatus(2);
        }
        CustomerLogPo logPo = new CustomerLogPo(name,user.getRealName(),rate,question,remark,money,format.format(new Date()));


        return null;
    }
}
