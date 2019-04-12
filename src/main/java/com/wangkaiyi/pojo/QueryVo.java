package com.wangkaiyi.pojo;

import java.util.List;

/**
 * 包装的pojo
 */
public class QueryVo {
    private Item item;
    private Integer[] ids;

    public Integer[] getIds() {
        return ids;
    }

    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }

    public QueryVo(Item item) {
        this.item = item;
    }

    public QueryVo() {
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "QueryVo{" +
                "item=" + item +
                '}';
    }
}
