package com.javapandeng.controller;

import com.alibaba.fastjson.JSONObject;
import com.javapandeng.base.BaseController;
import com.javapandeng.po.*;
import com.javapandeng.service.ItemCategoryService;
import com.javapandeng.service.ItemService;
import com.javapandeng.service.ManageService;
import com.javapandeng.service.UserService;
import com.javapandeng.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * 登录相关的控制器
 */
@Controller
@RequestMapping("/login")
public class loginController extends BaseController {

    @Autowired
    ManageService manageService;

    @Autowired
    ItemCategoryService itemCategoryService;

    @Autowired
    ItemService itemService;

    @Autowired
    UserService userService;

    @Autowired
    RedisTemplate redisTemplate;
    /**
     * 管理员登陆前
     */
    @RequestMapping("login")
    public String login(HttpServletRequest request,HttpServletResponse response){

        //验证是否以登录
        String sessionId = (String) redisTemplate.opsForValue().get("sessionId");

        /**
         * 验证是否以登录
         */
        if (isEmpty(request.getCookies())){
            //返回给视图解析器
            return "login/mLogin";
        }
        if (!isEmpty(request.getCookies())) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("sessionId")){
                    if (cookie.getValue().equals(sessionId)) {
                        return "login/mIndex";
                    }
                }
            }
        }

        return "login/mLogin";
    }

    /**
     * 管理员登录验证
     */
    @RequestMapping("toLogin")
    public String toLogin(Manage manage, HttpServletRequest request, HttpServletResponse response){

        //通过数据库去验证，用户密码是否正确
        Manage byEntity = manageService.getByEntity(manage);
        if (isEmpty(byEntity)){
            return "redirect:/login/mtuichu";
        }

        //request.getSession().setAttribute(Consts.MANAGE,byEntity);
        /**
         * 将会话存储到 Redis，设置过期时间为半个月
         */

        String sessionId = request.getSession().getId();
        System.out.println(sessionId);
        redisTemplate.opsForValue().set("sessionId",sessionId,15, TimeUnit.DAYS);
        Cookie cookie = new Cookie("sessionId", sessionId);

        // 设置cookie 保存时间
        cookie.setMaxAge(15 * 24 * 60 * 60);
        response.addCookie(cookie);

        //返回给视图解析器
        return "login/mIndex";
    }

    /**
     * 管理员退出
     */
    @RequestMapping("mtuichu")
    public String mtuichu(HttpServletRequest request){
        request.getSession(). setAttribute(Consts.MANAGE,null);
        return "login/mLogin";
    }

    /**
     * 前台页面
     */
    @RequestMapping("uIndex")
    public String uIndex(Model model){

        String sql1 = "select * from item_category where isDelete = 0 and pid is null order by name";
        List<ItemCategory> itemCategories = itemCategoryService.listBySqlReturnEntity(sql1);

        List<CategoryDto> list = new ArrayList<>();
        if (!CollectionUtils.isEmpty(itemCategories)){
            for (int i = 0; i < itemCategories.size(); i++) {
                list.add(new CategoryDto());
            }
            for (int i = 0; i < itemCategories.size(); i++) {
                ItemCategory itemCategory = itemCategories.get(i);
                list.get(i).setFather(itemCategory);
                String sql2 = "select * from item_category where isDelete = 0 and pid = " + itemCategories.get(i).getId();
                List<ItemCategory> itemCategories1 = itemCategoryService.listBySqlReturnEntity(sql2);
                list.get(i).setChildrens(itemCategories1);
            }
        }
        model.addAttribute("lbs",list);

        /**
         * 折扣商品
         */
        String zk_sql = "select * from item where isDelete = 0 and zk is not null";
        List<Item> zks = itemService.listBySqlReturnEntity(zk_sql);
        model.addAttribute("zks",zks);

        /**
         * 热门商品
         */
        String gm_sql = "select * from item where isDelete = 0 and zk is not null order by gmNum desc limit 10";
        List<Item> rxs= itemService.listBySqlReturnEntity(gm_sql);
        model.addAttribute("rxs",rxs);

        return "login/uIndex";
    }


    /**
     * 转向 注册页面
     */
    @RequestMapping("res")
    public String res(){

        return "login/res";
    }

    /**
     * 新增注册功能
     */
    @RequestMapping("toRes")
    public String toRes(User user){
        userService.insert(user);
        return "login/uIndex";
    }

    /**
     * 转向 登录页面
     */
    @RequestMapping("uLogin")
    public String uLogin(){

        return "login/uLogin";
    }

    /**
     * 新增登录页面功能
     */
    @RequestMapping("utoLogin")
    public String utoLogin(User user,HttpServletRequest request){
        User byEntity = userService.getByEntity(user);

        if (isEmpty(user)){
            /**
             * 转向注册功能
             */
            return "/login/res";
        } else {
            request.getSession().setAttribute("role",2);
            request.getSession().setAttribute(Consts.MANAGE,byEntity);
            request.getSession().setAttribute(Consts.USERNAME,byEntity.getUserName());
            request.getSession().setAttribute(Consts.USERID,byEntity.getId());

            /**
             * 如果直接返回  那这个会直接被 视图解析器解析
             * 如果 redirect 那个这会使浏览器重新发送 请求 会触发响应得逻辑
             */

            return "redirect:/login/uIndex.action";
        }
    }


    /**
     * 新增退出功能
     */
    @RequestMapping("uTui")
    public String uTui(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/login/uIndex.action";
    }

    /**
     * 转向 修改用户信息页面
     */
    @RequestMapping("pass")
    public String pass(Model model,HttpServletRequest request){
        Object attribute = request.getSession().getAttribute(Consts.USERID);
        if (isEmpty(attribute)){

            return "login/uLogin";
        }
        Integer user_id = Integer.valueOf(attribute.toString());

        User user = userService.load(user_id);

        model.addAttribute("obj",user);
        return "login/pass";
    }

    /**
     * 新增修改功能
     * @return
     */
    @RequestMapping("upass")
    @ResponseBody
    public String upass(HttpServletRequest request,String password){
        JSONObject js = new JSONObject();
        Object attribute = request.getSession().getAttribute(Consts.USERID);
        if (isEmpty(attribute)){

            js.put(Consts.RES,0);
            return js.toJSONString();
        }
        Integer user_id = Integer.valueOf(attribute.toString());
        User user = userService.load(user_id);
        user.setPassWord(password);
        userService.updateById(user);

        js.put(Consts.RES,1);
        return js.toJSONString();
    }
}
