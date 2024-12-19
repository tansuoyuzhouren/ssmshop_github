package com.javapandeng.po;

import java.io.Serializable;
import java.util.Date;

/**
 * 评论
 */
public class Comment implements Serializable {

    private Integer id;

    private Integer userId;

    private User user;

    private Integer item_id;

    /**
     * 内容
     */
    private String content;

    /**
     * 发布时间
     */
    private Date addTime;


    public Comment() {
    }

    public Comment(Integer id, Integer userId, Integer item_id, String content, Date addTime) {
        this.id = id;
        this.userId = userId;
        this.item_id = item_id;
        this.content = content;
        this.addTime = addTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getItem_id() {
        return item_id;
    }

    public void setItem_id(Integer item_id) {
        this.item_id = item_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", userId=" + userId +
                ", item_id=" + item_id +
                ", content='" + content + '\'' +
                ", addTime=" + addTime +
                '}';
    }
}
