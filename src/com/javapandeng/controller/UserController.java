package com.javapandeng.controller;

import com.javapandeng.base.BaseController;
import com.javapandeng.po.User;
import com.javapandeng.service.UserService;
import com.javapandeng.utils.Consts;
import com.javapandeng.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    UserService userService;
    /**
     * 分页查询一级类目列表
     */

    @RequestMapping("findBySql")
    public String findBySql(Model model,User user){
        if (isEmpty(user.getUserName())){
            String sql = "select * from user order by id";
            Pager<User> pagers = userService.findBySqlReturnEntity(sql);
            model.addAttribute("pagers",pagers);
        } else {
            String sql = "select * from user where userName like '%"+ user.getUserName()+"%'";
            Pager<User> pagers = userService.findBySqlReturnEntity(sql);
            model.addAttribute("pagers",pagers);
            model.addAttribute("obj",user);
        }
        return "user/user";
    }

    /**
     * 转向个人中心页面
     */
    @RequestMapping("view")
    public String view(HttpServletRequest request,Model model){
        Object attribute = request.getSession().getAttribute(Consts.USERID);


        if (isEmpty(attribute)){
            return "login/res";
        }

        Integer user_id = Integer.valueOf(attribute.toString());
        User user = userService.load(user_id);

        model.addAttribute("obj",user);
        return "user/view";

    }


    /**
     * 新增修改功能
     */
    @RequestMapping("exUpdate")
    public String exUpdate(User user,HttpServletRequest request){
        User attribute = (User) request.getSession().getAttribute(Consts.MANAGE);

        user.setId(attribute.getId());
        user.setUserName(attribute.getUserName());
        user.setPassWord(attribute.getPassWord());
        request.getSession().setAttribute(Consts.MANAGE,user);
        request.getSession().setAttribute(Consts.USERID,user.getId());
        request.getSession().setAttribute(Consts.USERNAME,user.getUserName());

        userService.updateById(user);
        return "user/view";
    }
}
