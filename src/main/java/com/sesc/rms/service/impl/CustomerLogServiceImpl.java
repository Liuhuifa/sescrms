package com.sesc.rms.service.impl;

import com.sesc.rms.dao.CustomerLogMapper;
import com.sesc.rms.dao.CustomerMapper;
import com.sesc.rms.po.CustomerLogPo;
import com.sesc.rms.po.CustomerPo;
import com.sesc.rms.service.inter.CustomerLogService;
import com.sesc.rms.util.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class CustomerLogServiceImpl implements CustomerLogService {

    @Resource
    private CustomerLogMapper mapper;
    @Resource
    private CustomerMapper customerMapper;

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    @Override
    public Result insertOne(CustomerLogPo logPo, CustomerPo customerPo) {
        Result res = new Result();
        int result=-1;//标识作用
        result = mapper.insertOne(logPo);
        if (result>0){
            result=-1;
            result = customerMapper.updateByCustomer(customerPo);
        }
        if (result>0){
            //大于0添加成功
            res.setCode(1);
        }else{
            //否则添加失败
            res.setCode(0);
        }
        return res;
    }
}
