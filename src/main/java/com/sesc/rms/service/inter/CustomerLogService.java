package com.sesc.rms.service.inter;

import com.sesc.rms.po.CustomerLogPo;
import com.sesc.rms.po.CustomerPo;
import com.sesc.rms.util.Result;

import java.util.List;

public interface CustomerLogService {
    /**
     * 插入一条新纪录
     * @param logPo
     * @return
     */
    Result insertOne(CustomerLogPo logPo, CustomerPo customerPo);

    /**
     * 根据客户id查询所有的跟踪记录
     * @param id
     * @return
     */
    List<CustomerLogPo> listById(Long id);
}
