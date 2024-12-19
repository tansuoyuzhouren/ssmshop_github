package com.javapandeng.po;

import java.io.Serializable;

public class Message implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
    名字
     */
    private String name;

    /**
     * 手机号
     */
    private String phone;


    /**
     * 内容
     */
    private String content;


    public Message() {
    }

    public Message(Integer id, String name, String phone, String content) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "message{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
