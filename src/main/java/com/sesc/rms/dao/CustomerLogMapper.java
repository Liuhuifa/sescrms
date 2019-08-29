package com.sesc.rms.dao;


import com.sesc.rms.po.CustomerLogPo;

import java.util.List;

public interface CustomerLogMapper {
//    插入一条新增记录
   int insertOne(CustomerLogPo po);
//   根据客户id查询所有跟踪记录
   List<CustomerLogPo> listById(Long logid);
}
