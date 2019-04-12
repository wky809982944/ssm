package com.wangkaiyi.controller;

import com.wangkaiyi.pojo.Item;
import com.wangkaiyi.pojo.QueryVo;
import com.wangkaiyi.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

@Controller
//@RequestMapping("item")
public class ItemController {
    @Autowired
    private ItemService itemService;
    @RequestMapping(value = {"itemList","itemList2"})
    public ModelAndView itemList() {
        //模拟商品查询
        ModelAndView mav = new ModelAndView();
        List<Item> itemList = itemService.getItemList();
        mav.addObject("itemList", itemList);
        mav.setViewName("itemList");
        System.out.println("ItemController.itemList");
        return mav;
    }

    /*@RequestMapping("itemEdit")
    public ModelAndView itemEdit(HttpServletRequest request, HttpResponse response, HttpSession session) {
        ModelAndView mav = new ModelAndView();
        String id = request.getParameter("id");
        System.out.println("response:"+response);
        System.out.println("response = " + response);
        System.out.println("session = " + session);
        //查询商品信息
        Item item = itemService.getItemById(new Integer(id));
        mav.addObject("item", item);
        mav.setViewName("itemEdit");
        return mav;
    }*/
    @RequestMapping("itemEdit")
    public ModelAndView itemEdit(Integer id) {
        ModelAndView mav = new ModelAndView();
        //查询商品信息
        Item item = itemService.getItemById(id);
        mav.addObject("item", item);
        mav.setViewName("itemEdit");
        return mav;
    }

    /**
     * 演示pojo参数绑定
     *
     * @param item
     * @return
     */
    @RequestMapping("updateItem")
    public String updateItem(Item item, MultipartFile pictureFile, Model model) throws Exception{

        //图片新名字
        String newName = UUID.randomUUID().toString();
        //图片原来的名字
        String oldName = pictureFile.getOriginalFilename();
        //后缀
        String sux = oldName.substring(oldName.lastIndexOf("."));
        //新建本地文件流
        File file = new File("/home/wky/pic/"+newName+sux);
        //写入本地磁盘
        pictureFile.transferTo(file);
        //保存图片到数据哭
        item.setPic(newName+sux);
        itemService.updateItem(item);
        model.addAttribute("item", item);
        model.addAttribute("msg", "商品信息修改成功");
        return "itemEdit";
        /*return "forward:itemList.action";*/
    }
   @RequestMapping("queryItem")
    public String queryItem(QueryVo vo,Model model,Integer[] ids) {

       if (vo.getItem() != null) {
           System.out.println("vo = " + vo.getItem());
       }
       if (ids != null&&ids.length>0) {
           for (Integer id : ids) {
               System.out.println("id = " + id);
           }
       }
       if (vo.getItem() != null && vo.getItems().size() > 0) {
           for (Item item : vo.getItems()) {
               System.out.println("item = " + item);
           }
       }
       //模拟搜索商品
       List<Item> itemList = itemService.getItemList();
       model.addAttribute("itemList", itemList);

       return "itemList";
    }

    @RequestMapping("queryVoid")
    public void queryVoid(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //request响应用户请求
       /* request.setAttribute("msg", "这个是request响应的消息");
        request.getRequestDispatcher("WEB-INF/jsp/msg.jsp").forward(request, response);
        */
       //response响应用户请求
      /* response.sendRedirect("/itemList.action");*/
        response.setCharacterEncoding("utf-8");
        PrintWriter printWriter = response.getWriter();
        //设置响应的字符编码
        //response.setContentType("text/html;charset=utf-8");

        printWriter.println("这是response打印的消息");
    }
    @RequestMapping("getItem")
    @ResponseBody
    public Item getItem(@RequestBody Item itemIn) {
        System.out.println("itemIn = " + itemIn);
        //Item item = itemService.getItemById(1);
        itemIn.setName("手机");
        return itemIn;
    }

    /**
     * Restful风格
     * @param ids
     * @return
     */
    @RequestMapping("item/{id}")
    public String itemQuery(@PathVariable("id") Integer ids,Model model) {
        Item item = itemService.getItemById(ids);
        model.addAttribute("item", item);
        return "itemEdit";
    }
}
