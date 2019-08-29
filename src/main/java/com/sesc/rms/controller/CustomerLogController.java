package com.sesc.rms.controller;

import com.sesc.rms.po.CustomerLogPo;
import com.sesc.rms.po.CustomerPo;
import com.sesc.rms.po.SysUserPo;
import com.sesc.rms.service.inter.CustomerLogService;
import com.sesc.rms.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("customer-log")
public class CustomerLogController {

    @Autowired
    private CustomerLogService service;

    @PostMapping("add")
    @ResponseBody
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
                      Long cumtomerid,
                      HttpServletRequest request
                      ){
        SysUserPo user = (SysUserPo)request.getSession().getAttribute("user");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = format.format(new Date());
        CustomerPo customerPo = new CustomerPo(cumtomerid,name,address,qq,tel,email,1,caim,cfrom,dateStr,rate,remark);
        if (rate==2){
            customerPo.setStatus(2);
        }
        CustomerLogPo logPo = new CustomerLogPo(cumtomerid,name,user.getRealName(),rate,question,remark,money,dateStr);
        return service.insertOne(logPo,customerPo);
    }

    @GetMapping("tail-info/{logid}")
    public ModelAndView toTailInfo(@PathVariable Long logid){
        List<CustomerLogPo> customerLogPos = service.listById(logid);
        ModelAndView mv =new ModelAndView("/client/tail-info");
        mv.addObject("datas",customerLogPos);
        return mv;
    }
}
