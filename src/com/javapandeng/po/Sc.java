package com.javapandeng.po;

import java.io.Serializable;

public class Sc implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 类目id
     */
    private Integer item_id;
    private Item item;

    /**
     * 用户id
     */
    private Integer user_id;
    private User user;

    public Sc() {
    }

    public Sc(Integer id, Integer item_id, Integer user_id) {
        this.id = id;
        this.item_id = item_id;
        this.user_id = user_id;
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

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Sc{" +
                "id=" + id +
                ", item_id=" + item_id +
                ", user_id=" + user_id +
                '}';
    }
}
