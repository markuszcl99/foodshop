package com.markus.pojo.vo;

/**
 * @author: markus
 * @date: 2022/10/14 11:33 PM
 * @Description: 商品简单信息，用于首页推荐展示
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class SimpleItemVO {

    private String itemId;
    private String itemName;
    private String itemUrl;

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

    public String getItemUrl() {
        return itemUrl;
    }

    public void setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl;
    }
}
