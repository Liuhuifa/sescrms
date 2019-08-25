package com.sesc.rms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sesc.rms.dao.CustomerMapper;
import com.sesc.rms.po.CustomerPo;
import com.sesc.rms.service.inter.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
}
