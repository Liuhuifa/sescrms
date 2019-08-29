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
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CustomerLogServiceImpl implements CustomerLogService {

    @Resource
    private CustomerLogMapper mapper;
    @Resource
    private CustomerMapper customerMapper;


    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class)
    public Result insertOne(CustomerLogPo logPo, CustomerPo customerPo) {
        Result res = new Result();
        int logResult = mapper.insertOne(logPo);
        customerPo.setCount(1);
        int customerResult = customerMapper.updateByCustomer(customerPo);
        if (logResult>0 && customerResult>0){
            //大于0添加成功
            res.setCode(1);
        }else{
            //否则添加失败
            res.setCode(0);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return res;
    }

    @Override
    public List<CustomerLogPo> listById(Long id) {
        return mapper.listById(id);
    }
}
