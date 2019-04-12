package com.wangkaiyi.service.impl;


import com.wangkaiyi.dao.mapper.ItemMapper;
import com.wangkaiyi.pojo.Item;
import com.wangkaiyi.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemMapper itemMapper;

    @Override
    public List<Item> getItemList() {
        return itemMapper.selectByExample(null);
    }

    @Override
    public Item getItemById(Integer id) {
        return itemMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateItem(Item item) {
        itemMapper.updateByPrimaryKeySelective(item);
    }
}
