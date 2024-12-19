package com.javapandeng.service.impl;

import com.javapandeng.base.BaseDao;
import com.javapandeng.base.BaseServiceImpl;
import com.javapandeng.mapper.ManageMapper;
import com.javapandeng.po.Manage;
import com.javapandeng.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Manage
 * service层
 */
@Service
public class ManageServiceImpl extends BaseServiceImpl<Manage> implements ManageService {

    //业务层调用Dao层
    @Autowired
    ManageMapper manageMapper;

    @Override
    public BaseDao<Manage> getBaseDao() {
        return manageMapper;
    }

}
