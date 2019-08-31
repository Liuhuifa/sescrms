package com.sesc.rms.dao;

import com.sesc.rms.po.CustomerPo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerMapper {
    /**
     * 客户列表查询
     * @param po
     * @return
     */
    List<CustomerPo> listCustomers(CustomerPo po);

    /**
     * 添加客户
     * @param po
     * @return
     */
    int addCustomer(CustomerPo po);

    /**
     * 查询客户是否存在
     * @param po
     * @return
     */
    int findOneCustomer(CustomerPo po);

    /**
     * 查询某个具体的客户
     * @param id
     * @return
     */
    CustomerPo findCustomerById(Long id);

    /**
     * 修改客户信息
     * @param po
     * @return
     */
    int updateByCustomer(CustomerPo po);

    /**
     * 给市场专员分配客户
     * @param uid
     * @param ids
     * @return
     */
    int updateByUidAndIds(@Param("uid") Integer uid,@Param("ids")Long[]ids,@Param("lasttime")String lasttime);
}
