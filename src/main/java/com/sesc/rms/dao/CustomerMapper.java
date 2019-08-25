package com.sesc.rms.dao;

import com.sesc.rms.po.CustomerPo;

import java.util.List;

public interface CustomerMapper {
    /**
     * 客户列表查询
     * @param po
     * @return
     */
    List<CustomerPo> listCustomers(CustomerPo po);
}
