package com.wangkaiyi.controller;

import com.wangkaiyi.pojo.Item;
import com.wangkaiyi.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;
    @RequestMapping("itemList")
    public ModelAndView itemList() {
        //模拟商品查询
        ModelAndView mav = new ModelAndView();
        List<Item> itemList = itemService.getItemList();
        mav.addObject("itemList", itemList);
        mav.setViewName("itemList");
        return mav;
    }
}
