package com.javapandeng.po;

import java.io.Serializable;

public class CarDto implements Serializable {
    private Integer id;

    private Integer num;

    public CarDto() {
    }

    public CarDto(Integer id, Integer num) {
        this.id = id;
        this.num = num;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "CarDao{" +
                "id=" + id +
                ", num=" + num +
                '}';
    }
}
