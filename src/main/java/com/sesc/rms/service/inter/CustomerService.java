package com.sesc.rms.service.inter;

import com.github.pagehelper.PageInfo;
import com.sesc.rms.po.CustomerPo;

import java.util.List;

public interface CustomerService {
    /**
     * 客户列表查询
     * @param po
     * @return
     */
    PageInfo<CustomerPo> listCustomers(CustomerPo po);
}
