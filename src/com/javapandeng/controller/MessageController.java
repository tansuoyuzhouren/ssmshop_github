package com.javapandeng.controller;

import com.javapandeng.base.BaseController;
import com.javapandeng.po.Message;
import com.javapandeng.service.MessageService;
import com.javapandeng.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/message")
public class MessageController extends BaseController {
    @Autowired
    MessageService messageService;

    @RequestMapping("findBySql")
    public String message(Model model, Message message){
        if (isEmpty(message.getName())){
            String sql = "select * from message order by id";
            Pager<Message> pagers = messageService.findBySqlReturnEntity(sql);

            model.addAttribute("pagers",pagers);
            model.addAttribute("obj",message);
        } else {
            String sql = "select * from message where name like '%"+message.getName()+"%'order by id";
            Pager<Message> pagers = messageService.findBySqlReturnEntity(sql);

            model.addAttribute("pagers",pagers);
            model.addAttribute("obj",message);
        }

        return "message/message";
    }

    /**
     * 新增删除功能
     */

    @RequestMapping("delete")
    private String delete(Integer id){
        messageService.deleteById(id);

        return "redirect:/message/findBySql.action";
    }

    /**
     * 转向 增加留言页面
     */

    @RequestMapping("add")
    public String add(){

        return "message/add";
    }


}