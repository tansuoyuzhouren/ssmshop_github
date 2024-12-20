package com.javapandeng.service.impl;

import com.javapandeng.base.BaseDao;
import com.javapandeng.base.BaseServiceImpl;
import com.javapandeng.mapper.ItemCategoryMapper;
import com.javapandeng.po.ItemCategory;
import com.javapandeng.service.ItemCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 类目
 * service层
 * */
@Service
public class ItemCategoryServiceImpl extends BaseServiceImpl<ItemCategory> implements ItemCategoryService {

    @Autowired
    ItemCategoryMapper itemCategoryMapper;

    @Override
    public BaseDao getBaseDao() {
        return itemCategoryMapper;
    }

}
