package com.sesc.rms.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.util.IOUtils;
import com.github.pagehelper.PageInfo;
import com.sesc.rms.po.CustomerPo;
import com.sesc.rms.po.SysUserPo;
import com.sesc.rms.service.inter.CustomerService;
import com.sesc.rms.util.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("customer")
public class CustomerController{
    @Resource
    private CustomerService service;

    /**
     * 客户列表
     * @param group
     * @param belongs
     * @param name
     * @param tel
     * @param cfrom
     * @param address
     * @param pageindex
     * @param request
     * @param flag //标识哪个页面的请求 1:客户列表,2:休眠公海,3:合作中
     * @return
     */
    @GetMapping("list")
    public ModelAndView listCustomers(@RequestParam(required = false,defaultValue = "-2") Integer group,
                                      @RequestParam(required = false,defaultValue = "-3") Integer belongs,
                                      @RequestParam(required = false) String name,
                                      @RequestParam(required = false) String tel,
                                      @RequestParam(required = false) String cfrom,
                                      @RequestParam(required = false) String address,
                                      @RequestParam(required = false,defaultValue = "1") Integer pageindex,
                                      @RequestParam(required = false,defaultValue = "1") Integer flag,
                                      HttpServletRequest request,
                                      HttpServletResponse response) {
        CustomerPo po=null;
        ModelAndView mv=null;
        Subject subject = SecurityUtils.getSubject();
        boolean sub = subject.hasRole("超级管理员");
//        把当前页码保存到cookie中,以便后续操作需要回到当前页
        Cookie cookie = new Cookie("pageindex",pageindex.toString());
        cookie.setMaxAge(30000);
        response.addCookie(cookie);

//        先从session取出保存的信息
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
//            如果有权限
             po = new CustomerPo(name,address,tel,belongs,cfrom,pageindex,group);
        }else{
//            没有权限只能看到自己的
            SysUserPo user = (SysUserPo)request.getSession().getAttribute("user");
            po = new CustomerPo(name,address,tel,user.getUid(),cfrom,pageindex,1);
        }
//        保存搜索信息
        request.getSession().setAttribute("solr",po);
        PageInfo<CustomerPo> infos = null;
        if (flag==1){
//            客户列表
            mv= new ModelAndView("/client/client-list");
        }else if (flag==2){
//            休眠公海
            po.setGroup(-1);
            po.setBelongs(-1);
            mv= new ModelAndView("/client/client-sleep");
        }else if (flag==3){
//            合作中
            po.setRate(2);
            mv= new ModelAndView("/client/client-partner");
        }else if (flag==4){
//            暂停下线
            po.setRate(3);
            mv= new ModelAndView("/client/client-pause");
        }
        infos = service.listCustomers(po);
        mv.addObject("datas",infos);
        mv.addObject("solr",(CustomerPo)request.getSession().getAttribute("solr"));
        return mv;
    }

    /**
     * 添加客户
     * @param name
     * @param address
     * @param tel
     * @param qq
     * @param email
     * @param cfrom
     * @return
     */
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

    /**
     * 异步验证客户是否存在
     * @param po
     * @return
     */
    @PostMapping("/findOneByCustomer")
    @ResponseBody
    public Result findOneByCustomer(CustomerPo po){
        return service.findOneCustomer(po);
    }

    /**
     * 查询客户信息，跟踪记录，并且跳转添加跟踪记录页面
     * @param customer_id
     * @return
     */
    @GetMapping("/tail/{customer_id}")
    public ModelAndView tail(@PathVariable Long customer_id){
        CustomerPo po = new CustomerPo();
        po.setId(customer_id);
        ModelAndView mv= new ModelAndView("/client/client-tail-add");
        CustomerPo customer = service.findCustomerById(customer_id);
        mv.addObject("customer",customer);
        mv.addObject("cumtomerid",customer_id);
        return mv;
    }

    /**
     * 给市场专员分配客户
     * @param uid
     * @param
     * @return
     */
    @PostMapping("/update")
    @ResponseBody
    public Result updateByUidAndIds(@RequestParam("uid")Integer uid,Long[] ids,HttpServletRequest request){
        try {
            if (uid==null || uid==0){
//                其阿奴单没有传uid的时候，用session中获取
                SysUserPo user = (SysUserPo) request.getSession().getAttribute("user");
                uid = user.getUid();
            }
            return service.updateByUidAndIds(uid, ids);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("分配失败");
        }
    }

    /**
     * 跳转公海页面的接口
     */
    @GetMapping("sleep/{pageindex}")
    public ModelAndView listSleepCustomer(@PathVariable Integer pageindex){
        CustomerPo po =new CustomerPo();
        po.setGroup(-1);
        po.setPageindex(pageindex);
        PageInfo<CustomerPo> customerPoPageInfo = service.listCustomers(po);
        ModelAndView mv = new ModelAndView("client/client-sleep");
        mv.addObject("datas",customerPoPageInfo);
        return mv;
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @PostMapping("delAny")
    @ResponseBody
    public Result delAnyCustomers(@RequestParam("ids")Long[]ids){
        Result result = service.delAnyCustomers(ids);
        return result;
    }

    /**
     * 删除一个
     * @param id
     * @return
     */
    @PostMapping("del")
    @ResponseBody
    public Result delCustomer(@RequestParam("id")Long id){
        return service.delCustomer(id);
    }

    @PostMapping("move")
    @ResponseBody
    public Result move(@RequestParam("id")Long id){
        CustomerPo po= new CustomerPo();
        po.setId(id);
        po.setBelongs(0);
        return service.updateByCustomer(po);
    }

//    /**
//     * 跳转公海页面的接口
//     */
//    @GetMapping("partner/{pageindex}")
//    public ModelAndView partner(@PathVariable Integer pageindex){
//        CustomerPo po =new CustomerPo();
//        po.setGroup(-1);
//        po.setPageindex(pageindex);
//        PageInfo<CustomerPo> customerPoPageInfo = service.listCustomers(po);
//        ModelAndView mv = new ModelAndView("client/client-sleep");
//        mv.addObject("datas",customerPoPageInfo);
//        return mv;
//    }

    @GetMapping("countLook")
    @ResponseBody
    public Result selectLookCount(HttpServletRequest request){
        SysUserPo user = (SysUserPo)request.getSession().getAttribute("user");
        return service.selectLookCount(user.getUid());
    }

}
