package com.javapandeng.po;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ItemOrder implements Serializable {
    private Integer id;

    //商品id
    private Integer item_id;

    //购买者id
    private Integer user_id;
    private User user;

    /**
     * 订单号
     */
    private String code;

    /**
     * 购买时间
     */
    private Date addTime;

    /**
     * 购买数量
     */
    private String total;

    /**
     * 是否被删除
     */
    private Integer isDelete;

    /**
     *0.新建代发货 1.已取消 2.已发货 3.到收货 4.已评价
     */
    private Integer status;

    private List<OrderDetail> orderDetails;

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public ItemOrder() {
    }

    public ItemOrder(Integer id, Integer item_id, Integer user_id, User user, String code, Date addTime, String total, Integer isDelete, Integer status) {
        this.id = id;
        this.item_id = item_id;
        this.user_id = user_id;
        this.user = user;
        this.code = code;
        this.addTime = addTime;
        this.total = total;
        this.isDelete = isDelete;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getItem_id() {
        return item_id;
    }

    public void setItem_id(Integer item_id) {
        this.item_id = item_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ItemOrder{" +
                "id=" + id +
                ", item_id=" + item_id +
                ", user_id=" + user_id +
                ", user=" + user +
                ", code='" + code + '\'' +
                ", addTime='" + addTime + '\'' +
                ", total='" + total + '\'' +
                ", isDelete=" + isDelete +
                ", status=" + status +
                '}';
    }
}
