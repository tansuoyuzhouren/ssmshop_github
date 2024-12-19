package com.javapandeng.controller;

import com.javapandeng.base.BaseController;
import com.javapandeng.po.News;
import com.javapandeng.service.NewsService;
import com.javapandeng.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;


@Controller
@RequestMapping("/news")
public class NewsController extends BaseController {
    @Autowired
    NewsService newsService;

    @RequestMapping("findBySql")
    public String findBySql(Model model, News news){
        if (isEmpty(news.getName())){
            String sql = "select * from news";
            Pager<News> pagers = newsService.findBySqlReturnEntity(sql);
            model.addAttribute("pagers",pagers);
        } else {
            String sql = "select * from news where name like '%" + news.getName() +"%'";
            Pager<News> pagers = newsService.findBySqlReturnEntity(sql);
            model.addAttribute("pagers",pagers);
            model.addAttribute("obj",news);

        }

        return "news/news";
    }

    /**
     *转向添加公告页面
     */

    @RequestMapping("add")
    public String add(){

        return "news/add";
    }

    /**
     * 新增添加功能
     */
    @RequestMapping("exAdd")
    public String exAdd(News news){
        Date date = new Date();
        news.setAddTime(date);

        newsService.insert(news);
        return "redirect:/news/findBySql.action";
    }

    /**
     * 转向修改页面
     */
    @RequestMapping("update")
    public String update(Model model,Integer id){

        News news = newsService.load(id);
        model.addAttribute("obj",news);

        return "news/update";
    }

    /**
     * 新增修改功能
     */
    @RequestMapping("exUpdate")
    public String exUpdate(News news){
        news.setAddTime(new Date());

        newsService.updateById(news);
        return "redirect:/news/findBySql.action";
    }

    /**
     * 新增假删除功能
     */
    @RequestMapping("delete")
    public String delete(Integer id){

        newsService.deleteById(id);
        return "redirect:/news/findBySql.action";
    }



}
