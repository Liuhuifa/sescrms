package com.sesc.rms.service.inter;

import com.sesc.rms.po.CustomerLogPo;
import com.sesc.rms.po.CustomerPo;
import com.sesc.rms.util.Result;

public interface CustomerLogService {
    /**
     * 插入一条新纪录
     * @param logPo
     * @return
     */
    Result insertOne(CustomerLogPo logPo, CustomerPo customerPo);
}
