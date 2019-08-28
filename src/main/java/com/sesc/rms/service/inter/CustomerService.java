package com.sesc.rms.service.inter;

import com.github.pagehelper.PageInfo;
import com.sesc.rms.po.CustomerPo;
import com.sesc.rms.util.Result;
import sun.rmi.runtime.Log;

import java.util.List;

public interface CustomerService {
    /**
     * 客户列表查询
     * @param po
     * @return
     */
    PageInfo<CustomerPo> listCustomers(CustomerPo po);

    /**
     * 添加客户
     * @param po
     * @return
     */
    Result addCustomer(CustomerPo po);

    /**
     * 查询客户是否存在
     * @param po
     * @return
     */
    Result findOneCustomer(CustomerPo po);

    /**
     * 查询某一个客户
     * @param id
     * @return
     */
    CustomerPo findCustomerById(Long id);

    /**
     * 修改用户信息
     * @param po
     * @return
     */
    Result updateByCustomer(CustomerPo po);
}
