package com.javapandeng.controller;

import com.javapandeng.base.BaseController;
import com.javapandeng.po.Item;
import com.javapandeng.po.Sc;
import com.javapandeng.po.User;
import com.javapandeng.service.ItemService;
import com.javapandeng.service.ScService;
import com.javapandeng.service.UserService;
import com.javapandeng.utils.Consts;
import com.javapandeng.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Controller
@RequestMapping("/sc")
public class ScController extends BaseController {
    @Autowired
    ScService scService;

    @Autowired
    ItemService itemService;

    @Autowired
    UserService  userService;

    /**
     * 新增商品收藏功能
     * @param sc
     * @param request
     * @return
     */
    @RequestMapping("exAdd")
    public String exAdd(Sc sc, HttpServletRequest request){



        final Object attribute = request.getSession().getAttribute(Consts.USERID);
        if (isEmpty(attribute)){

            /**
             * 转到登录页面
             */
            return "login/uLogin";
        } else {
            Integer user_id = Integer.valueOf(attribute.toString());
            sc.setUser_id(user_id);
            scService.insert(sc);
            Item item = itemService.load(sc.getItem_id());
            User user = userService.load(sc.getUser_id());

            sc.setUser(user);
            sc.setItem(item);


            return "redirect:sc/findBySql?item_id="+item.getId();
        }
    }

    @RequestMapping("findBySql")
    public String findBySql(Model model, HttpServletRequest request){
        Integer user_id = (Integer) request.getSession().getAttribute(Consts.USERID);

        /**
         * 查得是当前用户得收藏
         */
        String sql = "select * from sc where user_id = "+ user_id;
        Pager<Sc> pagers = scService.findBySqlReturnEntity(sql);

        System.out.println(Arrays.toString(pagers.getDatas().toArray()));
        model.addAttribute("pagers",pagers);
        return "sc/my";
    }

    /**
     新增取消收藏
     */

    @RequestMapping("delete")
    public String delete(Integer id){
        scService.deleteById(id);

        return "redirect:/sc/findBySql.action";
    }
}