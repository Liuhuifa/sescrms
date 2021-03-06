package com.sesc.rms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sesc.rms.dao.CustomerMapper;
import com.sesc.rms.po.CustomerPo;
import com.sesc.rms.service.inter.CustomerService;
import com.sesc.rms.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Resource
    private CustomerMapper mapper;

    @Override
    public PageInfo<CustomerPo> listCustomers(CustomerPo po) {
        PageHelper.startPage(po.getPageindex(),po.getPagesize());
        List<CustomerPo> customerPos = mapper.listCustomers(po);
        PageInfo<CustomerPo> infos = new PageInfo<>(customerPos);
        return infos;
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    @Override
    public Result addCustomer(CustomerPo po) {
        Result result = new Result();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        po.setTime(format.format(new Date()));
        int delResult = mapper.addCustomer(po);
        if (delResult>0){
            result.setCode(1);
        }else{
            result.setCode(0);
        }
        return result;
    }

    @Override
    public Result findOneCustomer(CustomerPo po) {
        Result result = new Result();
        int findresult = mapper.findOneCustomer(po);
        if (findresult>0){
            result.setCode(2);
            result.setMessage("存在");
        }else if (findresult==0){
            result.setCode(1);
            result.setMessage("不存在");
        }else{
            result.setCode(0);
            result.setMessage("服务有毛病");
        }
        return result;
    }

    @Override
    public CustomerPo findCustomerById(Long id) {
        return mapper.findCustomerById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public Result updateByCustomer(CustomerPo po) {
        int updateResult = mapper.updateByCustomer(po);
        if (updateResult>0){
            return Result.success();
        }else{
            return Result.fail("修改失败");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public Result updateByUidAndIds(Integer uid, Long[] ids) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int result = mapper.updateByUidAndIds(uid, ids,format.format(new Date()));
        if (result>0){
            return Result.success();
        }else {
            return Result.fail("修改失败");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public Result delAnyCustomers(Long[] ids) {
        int delResult = mapper.delAnyCustomers(ids);
        if (delResult>0){
            return Result.success();
        }else {
            return Result.fail("删除失败");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public Result delCustomer(Long id) {
        int delResult = mapper.delCustomer(id);
        if (delResult>0){
            return Result.success();
        }else {
            return Result.fail("删除失败");
        }
    }

    @Override
    public Result selectLookCount(Integer id) {
        Result res = new Result();
        int result = mapper.selectLookCount(id);
        if (result>=0){
            res.setTotal(result);
            return res;
        }else{
            return Result.fail("服务器GG了可能");
        }
    }
}
