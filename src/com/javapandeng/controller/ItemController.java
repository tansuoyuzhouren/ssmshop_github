package com.javapandeng.controller;
import com.javapandeng.base.BaseController;
import com.javapandeng.po.Item;
import com.javapandeng.po.ItemCategory;
import com.javapandeng.service.ItemCategoryService;
import com.javapandeng.service.ItemService;
import com.javapandeng.utils.Pager;
import com.javapandeng.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/item")
public class ItemController extends BaseController {
    @Autowired
    ItemService itemService;
    @Autowired
    ItemCategoryService itemCategoryService;

    @RequestMapping("findBySql")
    public String findBySql(Model model, Item item){
        if (isEmpty(item.getName())){
            String sql = "select * from item where isDelete=0 order by id desc";
            Pager<Item> pagers = itemService.findBySqlReturnEntity(sql);
            model.addAttribute("pagers",pagers);
        } else {
            String sql = "select * from item where isDelete=0 and name like '%" + item.getName() +"%'";
            Pager<Item> pagers = itemService.findBySqlReturnEntity(sql);
            model.addAttribute("pagers",pagers);

            model.addAttribute("obj",item);
        }
        return "item/item";
    }

    /**
     * 转向添加页面
     */
    @RequestMapping("add")
    public String add(Model model){
        String sql = "select * from item_category where isDelete = 0 and pid is not null order by id";
        List<ItemCategory> types = itemCategoryService.listBySqlReturnEntity(sql);

        model.addAttribute("types",types);
        return "item/add";
    }

    /**
     * 新增添加功能
     */
    @RequestMapping("exAdd")
    /**
     * @RequestParam("file") CommonsMultipartFile[] files
     * @RequestParam("file") ： 表示从Http表单中获取名为 file的参数，通常用于上传
     * CommonsMultipartFile[] files ： 获取前端表单上传的文件封装成的数组
     */
    public String exAdd(Item item, @RequestParam("file")CommonsMultipartFile[] files, HttpServletRequest request) throws IOException {
        if (files.length>0){
            for (int s = 0; s<files.length;s++){
                String n = UUIDUtils.create();
                /**
                 * File.separator : 不同的操作系统对路径的分隔符也有不同，所以可以考虑使用 File.separator 来替代硬编码的 \ 或 /。
                 */
                String path = request.getServletContext().getRealPath("/")+"resource"+File.separator+"ueditor"+File.separator+"upload"+File.separator+n+files[s].getOriginalFilename();

                File newFile = new File(path);

                //通过CommonsMultipartFile的方法直接写文件
                files[s].transferTo(newFile);

                //把文件对于的路径写到对象里面
                if (s==0){
                    item.setUrl1(path);
                }
                if (s==1){
                    item.setUrl2(path);
                }
                if (s==2){
                    item.setUrl3(path);
                }
                if (s==3){
                    item.setUrl4(path);
                }
                if (s==4){
                    item.setUrl5(path);
                }
            }
        }

        ItemCategory byId = itemCategoryService.getById(item.getCategory_id_two());

        item.setCategory_id_one(byId.getPid());

        item.setGmNum(0);
        item.setIsDelete(0);
        item.setScNum(0);
        itemService.insert(item);

        return "redirect:/item/findBySql.action";
    }

    /**
     * 转向修改 页面
     */
    @RequestMapping("update")
    public String update(Model model,Integer id){
        String sql = "select * from item_category where isDelete=0 and pid is not null";
        List<ItemCategory> types = itemCategoryService.listBySqlReturnEntity(sql);
        model.addAttribute("types",types);

        Item byId = itemService.getById(id);
        model.addAttribute("obj",byId);

        return "/item/update";
    }


    /**
     * 新增修改功能
     */
    @RequestMapping("exUpdate")
    public String exUpdate(Item item,@RequestParam("file")CommonsMultipartFile[] files,HttpServletRequest request) throws IOException {
        if (files.length > 0){
            for (int i = 0; i < files.length; i++) {
                String n = UUIDUtils.create();

                String path = request.getServletContext().getRealPath("/")+"resource"+File.separator+"ueditor"+File.separator+"upload"+File.separator+n+files[i].getOriginalFilename();
                File newFile = new File(path);

                files[i].transferTo(newFile);
                //把文件对于的路径写到对象里面

                if (i==0){
                    item.setUrl1(path);
                }
                if (i==1){
                    item.setUrl2(path);
                }
                if (i==2){
                    item.setUrl3(path);
                }
                if (i==3){
                    item.setUrl4(path);
                }
                if (i==4){
                    item.setUrl5(path);
                }
            }
        }

        ItemCategory byId = itemCategoryService.getById(item.getCategory_id_two());

        item.setCategory_id_one(byId.getPid());
        item.setIsDelete(0);
        item.setScNum(0);
        item.setGmNum(0);

        itemService.updateById(item);

        return "redirect:/item/findBySql.action";
    }

    @RequestMapping("delete")
    public String delete(Integer id){
        String sql = "update item set isDelete=1 where id = " + id;
        itemService.updateBySql(sql);
        return "redirect:/item/findBySql.action";
    }

    /**
     * 转向 搜索页面
     */
    @RequestMapping("shoplist")
    public String shoplist(Item item,String condition,Model model){


        String sql = "select  * from item where isDelete = 0 ";
        if (!isEmpty(item.getCategory_id_two())) {
            sql = sql + "and category_id_two = " +item.getCategory_id_two();
        }

        if (!isEmpty(condition)){
            sql = sql + "and name like"+"'%+"+condition+"+%'";
            model.addAttribute("condition",condition);
        }

        /**
         * 根据价格取排序并返回
         */
        if (!isEmpty(item.getPrice())){
            sql = sql + " order by price+0 desc";
        }

        /**
         * 根据销量排序并返回
         */
        if (!isEmpty(item.getGmNum())){
            sql = sql + " order by gmNum desc";
        }

        /**
         * 默认
         */
        if (!isEmpty(item.getPrice()) && !isEmpty(item.getGmNum())){
            sql = sql + "order by id desc";
        }

        Pager<Item> pagers = itemService.findBySqlReturnEntity(sql);
        model.addAttribute("pagers",pagers);
        model.addAttribute("obj",item);

        return "item/shoplist";
    }

    /**
     * 点击查看 商品详情
     */
    @RequestMapping("view")
    public String view(Integer id,Model model){

        Item item = itemService.load(id);
        model.addAttribute("obj",item);


        return "item/view";
    }
}
