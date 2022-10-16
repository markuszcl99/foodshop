package com.markus.pojo.vo;

/**
 * @author: markus
 * @date: 2022/10/16 2:14 PM
 * @Description: 搜索商品结果信息内容
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class SearchItemVO {
    private String itemId;
    private String itemName;
    private String imgUrl;
    private int price;
    private int sellCounts;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSellCounts() {
        return sellCounts;
    }

    public void setSellCounts(int sellCounts) {
        this.sellCounts = sellCounts;
    }
}
