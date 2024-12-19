package com.javapandeng.service.impl;

import com.javapandeng.base.BaseDao;
import com.javapandeng.base.BaseServiceImpl;
import com.javapandeng.mapper.OrderDetailMapper;
import com.javapandeng.po.OrderDetail;
import com.javapandeng.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl extends BaseServiceImpl<OrderDetail> implements OrderDetailService {
    @Autowired
    OrderDetailMapper orderDetailMapper;

    @Override
    public BaseDao getBaseDao() {
        return orderDetailMapper;
    }
}
