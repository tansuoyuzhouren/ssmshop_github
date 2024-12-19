package com.javapandeng.po;


import java.io.Serializable;
import java.util.List;

public class Item implements Serializable {

    /**
     * 主键
     */
    private Integer id;

    /**
     *项目名字
     */
    private String name;

    /**
     * 价格
     */
    private String price;

    /**
     *收藏数
     */
    private Integer scNum;

    /**
     * 购买数
     */
    private Integer gmNum;

    /**
     * 图片
     */
    private String url1;

    private String url2;

    private String url3;

    private String url4;

    private String url5;

    /**
     * 描述
     */
    private String ms;


    private String pam1;

    private String pam2;

    private String pam3;

    private String val3;

    private String val2;

    private String val1;

    private Integer type;

    private Integer zk;

    /**
     * 一级类目主键
     */
    private Integer category_id_one;
    private ItemCategory yiji;

    /**
     * 二级类目主键
     */
    private Integer category_id_two;
    private ItemCategory erji;

    /**
     * 是否删除
     */
    private Integer isDelete;

    public Item() {
    }

    /**
     * 评论列表
     */
    private List<Comment> pls;

    public Item(Integer id, String name, String price, Integer scNum, Integer gmNum, String url1, String url2, String url3, String url4, String url5, String ms, String pam1, String pam2, String pam3, String val3, String val2, String val1, Integer type, Integer zk, Integer category_id_one, ItemCategory yiji, Integer category_id_two, ItemCategory erji, Integer isDelete, List<Comment> pls) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.scNum = scNum;
        this.gmNum = gmNum;
        this.url1 = url1;
        this.url2 = url2;
        this.url3 = url3;
        this.url4 = url4;
        this.url5 = url5;
        this.ms = ms;
        this.pam1 = pam1;
        this.pam2 = pam2;
        this.pam3 = pam3;
        this.val3 = val3;
        this.val2 = val2;
        this.val1 = val1;
        this.type = type;
        this.zk = zk;
        this.category_id_one = category_id_one;
        this.yiji = yiji;
        this.category_id_two = category_id_two;
        this.erji = erji;
        this.isDelete = isDelete;
        this.pls = pls;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getScNum() {
        return scNum;
    }

    public void setScNum(Integer scNum) {
        this.scNum = scNum;
    }

    public Integer getGmNum() {
        return gmNum;
    }

    public void setGmNum(Integer gmNum) {
        this.gmNum = gmNum;
    }

    public String getUrl1() {
        return url1;
    }

    public void setUrl1(String url1) {
        this.url1 = url1;
    }

    public String getUrl2() {
        return url2;
    }

    public void setUrl2(String url2) {
        this.url2 = url2;
    }

    public String getUrl3() {
        return url3;
    }

    public void setUrl3(String url3) {
        this.url3 = url3;
    }

    public String getUrl4() {
        return url4;
    }

    public void setUrl4(String url4) {
        this.url4 = url4;
    }

    public String getUrl5() {
        return url5;
    }

    public void setUrl5(String url5) {
        this.url5 = url5;
    }

    public String getMs() {
        return ms;
    }

    public void setMs(String ms) {
        this.ms = ms;
    }

    public String getPam1() {
        return pam1;
    }

    public void setPam1(String pam1) {
        this.pam1 = pam1;
    }

    public String getPam2() {
        return pam2;
    }

    public void setPam2(String pam2) {
        this.pam2 = pam2;
    }

    public String getPam3() {
        return pam3;
    }

    public void setPam3(String pam3) {
        this.pam3 = pam3;
    }

    public String getVal3() {
        return val3;
    }

    public void setVal3(String val3) {
        this.val3 = val3;
    }

    public String getVal2() {
        return val2;
    }

    public void setVal2(String val2) {
        this.val2 = val2;
    }

    public String getVal1() {
        return val1;
    }

    public void setVal1(String val1) {
        this.val1 = val1;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getZk() {
        return zk;
    }

    public void setZk(Integer zk) {
        this.zk = zk;
    }

    public Integer getCategory_id_one() {
        return category_id_one;
    }

    public void setCategory_id_one(Integer category_id_one) {
        this.category_id_one = category_id_one;
    }

    public ItemCategory getYiji() {
        return yiji;
    }

    public void setYiji(ItemCategory yiji) {
        this.yiji = yiji;
    }

    public Integer getCategory_id_two() {
        return category_id_two;
    }

    public void setCategory_id_two(Integer category_id_two) {
        this.category_id_two = category_id_two;
    }

    public ItemCategory getErji() {
        return erji;
    }

    public void setErji(ItemCategory erji) {
        this.erji = erji;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public List<Comment> getPls() {
        return pls;
    }

    public void setPls(List<Comment> pls) {
        this.pls = pls;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", scNum=" + scNum +
                ", gmNum=" + gmNum +
                ", url1='" + url1 + '\'' +
                ", url2='" + url2 + '\'' +
                ", url3='" + url3 + '\'' +
                ", url4='" + url4 + '\'' +
                ", url5='" + url5 + '\'' +
                ", ms='" + ms + '\'' +
                ", pam1='" + pam1 + '\'' +
                ", pam2='" + pam2 + '\'' +
                ", pam3='" + pam3 + '\'' +
                ", val3='" + val3 + '\'' +
                ", val2='" + val2 + '\'' +
                ", val1='" + val1 + '\'' +
                ", type=" + type +
                ", zk=" + zk +
                ", category_id_one=" + category_id_one +
                ", yiji=" + yiji +
                ", category_id_two=" + category_id_two +
                ", erji=" + erji +
                ", isDelete=" + isDelete +
                ", pls=" + pls +
                '}';
    }
}
