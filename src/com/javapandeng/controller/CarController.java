package com.javapandeng.controller;

import com.alibaba.fastjson.JSONObject;
import com.javapandeng.po.Car;
import com.javapandeng.po.Item;
import com.javapandeng.service.CarService;
import com.javapandeng.service.ItemService;
import com.javapandeng.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * 购物车
 */
@Controller
@RequestMapping("/car")
public class CarController {
    @Autowired
    CarService carService;

    @Autowired
    ItemService itemService;

    @RequestMapping("exAdd")
    @ResponseBody
    public String exAdd(Car car, HttpServletRequest request){
        JSONObject js = new JSONObject();
        Object attribute = request.getSession().getAttribute(Consts.USERID);

        if (attribute == null){
            js.put(Consts.RES, 0);

            return js.toJSONString();
        }

        Item item = itemService.load(car.getItemId());
        car.setItem(item);

        Integer userId = Integer.valueOf(attribute.toString());
        car.setUserId(userId);

        Double price = Double.valueOf(item.getPrice());


        if (item.getZk() != null){
            price = price * item.getZk() / 10;
            BigDecimal bg = new BigDecimal(price).setScale(2, RoundingMode.UP);
            car.setPrice(bg.doubleValue());
            price = bg.doubleValue();
        }

        Integer num = car.getNum();
        Double t = price*num;

        BigDecimal bg = new BigDecimal(t).setScale(2, RoundingMode.UP);
        double doubleValue = bg.doubleValue();
        car.setTotal(doubleValue+"");
        carService.insert(car);

        js.put(Consts.RES,1);

        return js.toJSONString();
    }

    /**
     * 转向购物车 页面
     */
    @RequestMapping("findBySql")
    public String findBySql(HttpServletRequest request, Model model){
        Object attribute = request.getSession().getAttribute(Consts.USERID);
        if (attribute == null){
            return "redirect:/login/res";
        }
        Integer user_id = Integer.valueOf(attribute.toString());

        String sql = "select * from car where user_id = "+ user_id;
        List<Car> cars = carService.listBySqlReturnEntity(sql);

        model.addAttribute("list",cars);
        return "car/car";
    }

    /**
     * 删除购物车
     */
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(Integer id){
        carService.deleteById(id);
        return "success";
    }

}