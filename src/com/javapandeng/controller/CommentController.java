package com.javapandeng.controller;

import com.javapandeng.base.BaseController;
import com.javapandeng.po.Comment;
import com.javapandeng.service.CommentService;
import com.javapandeng.service.ItemOrderService;
import com.javapandeng.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping("/comment")
public class CommentController extends BaseController {

    @Autowired
    CommentService commentService;

    @Autowired
    ItemOrderService itemOrderService;

    @RequestMapping("exAdd")
    public String exAdd(Comment comment, HttpServletRequest request){
        Object attribute = request.getSession().getAttribute(Consts.USERID);

        if (isEmpty(attribute)){
            return "redirect:/login/toLogin";
        }

        Integer user_id = Integer.valueOf(attribute.toString());
        comment.setUserId(user_id);
        comment.setAddTime(new Date());

        commentService.insert(comment);



        /**
         * 修改订单的状态
         * 这里 好像有点问题 就是  在 comment中 item_id属性 其实是 item_order 的id属性
         * 不仅要评论，还要更改 订单的状态属性值 为 4.以评论
         */
        String sql = "update item_order set status = " + 4 + " where id = " + comment.getItem_id();
        itemOrderService.updateBySql(sql);

        return "redirect:/itemOrder/my.action";
    }
}
