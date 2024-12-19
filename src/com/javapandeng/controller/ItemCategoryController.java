package com.javapandeng.controller;

import com.javapandeng.base.BaseController;
import com.javapandeng.po.ItemCategory;
import com.javapandeng.service.ItemCategoryService;
import com.javapandeng.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/itemCategory")
public class ItemCategoryController extends BaseController {
    @Autowired
    ItemCategoryService itemCategoryService;
    /**
     * 分页查询一级类目列表
     */
    @RequestMapping("findBySql")
    public String findBySql(Model model, ItemCategory itemCategory){

        // 这里查询的是一级类目 pid为 null的
        String sql = "select * from item_category where isDelete = 0 and pid is null order by id";
        Pager<ItemCategory> pagers = itemCategoryService.findBySqlReturnEntity(sql);

        model.addAttribute("pagers",pagers);
        model.addAttribute("obj",itemCategory);

        return "itemCategory/itemCategory";
    }

    /**
     * 转向到新增一级分类页面
     */
    @RequestMapping("add")
    public String add(){
        return "itemCategory/add";
    }

    /**
     * 新增一级类目保存功能
     */

    @RequestMapping("exAdd")
    public String exAdd(ItemCategory itemCategory){
        itemCategory.setIsDelete(0);

        itemCategoryService.insert(itemCategory);
        return "redirect:/itemCategory/findBySql.action";
    }

    /**
     * 转向到修改一级类目页面
     */
    @RequestMapping("update")
    public String update(Model model,Integer id){

        ItemCategory obj = itemCategoryService.load(id);
        model.addAttribute("obj",obj);

        return "itemCategory/update";
    }

    /**
     * 新增一级类目的修改功能
     */
    @RequestMapping("exUpdate")
    public String exUpdate(ItemCategory itemCategory){
        itemCategoryService.updateById(itemCategory);

        return "redirect:/itemCategory/findBySql.action";
    }

    /**
     * 新增 假删除一级类目的功能
     */
    @RequestMapping("delete")
    public String delete(Integer id){
        //删除本身
        ItemCategory load = itemCategoryService.load(id);
        load.setIsDelete(1);
        System.out.println(load);
        itemCategoryService.updateById(load);
        //删除下级
        String sql = "update item_category set isDelete = 1 where pid = "+ id;
        itemCategoryService.updateBySql(sql);
        return "redirect:/itemCategory/findBySql.action";
    }

    /**
     * 查询二级类目列表
     */
    @RequestMapping("findBySql2")
    public String findBySql2(Model model,ItemCategory itemCategory){
        String sql = "select * from item_category where pid ="+itemCategory.getPid()+" and isDelete=0 order by id";
        Pager<ItemCategory> pagers = itemCategoryService.findBySqlReturnEntity(sql);

        model.addAttribute("obj",itemCategory);
        model.addAttribute("pagers",pagers);

        return "itemCategory/itemCategory2";
    }

    /**
     * 转向 新增二级类目页面
     */
    @RequestMapping("add2")
    public String add2(Integer pid,Model model){
        model.addAttribute("pid",pid);
        return "itemCategory/add2";
    }

    /**
     * 新增 增加二级类目
     */
    @RequestMapping("exAdd2")
    public String exAdd2(ItemCategory itemCategory){
        itemCategory.setIsDelete(0);
        itemCategoryService.insert(itemCategory);
        return "redirect:/itemCategory/findBySql2.action?pid=" + itemCategory.getPid();
    }

    /**
     * 转向 修改二级类目页面
     */
    @RequestMapping("update2")
    public String update2(Integer id,Model model){
        ItemCategory obj = itemCategoryService.load(id);
        model.addAttribute("obj",obj);

        return "itemCategory/update2";
    }

    /**
     * 新增 修改二级类目功能
     */
    @RequestMapping("exUpdate2")
    public String exUpdate2(ItemCategory itemCategory){
        itemCategoryService.updateById(itemCategory);

        return "redirect:/itemCategory/findBySql2.action?pid=" + itemCategory.getPid();
    }


    /**
     * 新增 假删除二级类目的功能
     */
    @RequestMapping("delete2")
    public String delete2(Integer id, Integer pid){
        ItemCategory load = itemCategoryService.load(id);
        load.setIsDelete(1);
        itemCategoryService.updateById(load);

        String sql = "update item_category set isDelete=1 where pid = id";
        itemCategoryService.updateBySql(sql);
        return "redirect:/itemCategory/findBySql2.action?pid="+pid;
    }
}
