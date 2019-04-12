package com.wangkaiyi.service;

import com.wangkaiyi.pojo.Item;

import java.util.List;

public interface ItemService {
    List<Item> getItemList();

    /**
     * 根据Id查询商品信息
     * @param id
     * @return
     */
    Item getItemById(Integer id);

    void updateItem(Item item);
}
