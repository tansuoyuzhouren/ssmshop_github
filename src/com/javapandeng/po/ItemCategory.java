package com.javapandeng.po;

import java.io.Serializable;

public class ItemCategory implements Serializable {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 名字
     */
    private String name;

    /**
     * 复健
     */
    private Integer pid;


    /**
     * 是否删除
     */
    private Integer isDelete;


    public ItemCategory(Integer id, String name, Integer pid, Integer isDelete) {
        this.id = id;
        this.name = name;
        this.pid = pid;
        this.isDelete = isDelete;
    }

    public ItemCategory() {
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

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "ItemCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pid=" + pid +
                ", isDelete=" + isDelete +
                '}';
    }
}
