package com.markus.pojo.vo;

import com.markus.pojo.Items;
import com.markus.pojo.ItemsImg;
import com.markus.pojo.ItemsParam;
import com.markus.pojo.ItemsSpec;

import java.util.List;

/**
 * @author: markus
 * @date: 2022/10/15 2:13 PM
 * @Description: 商品详情信息VO
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class ItemInfoVO {
    private Items item;
    private ItemsParam itemParams;
    private List<ItemsImg> itemImgList;
    private List<ItemsSpec> itemSpecList;

    public Items getItem() {
        return item;
    }

    public void setItem(Items item) {
        this.item = item;
    }

    public ItemsParam getItemParams() {
        return itemParams;
    }

    public void setItemParams(ItemsParam itemParams) {
        this.itemParams = itemParams;
    }

    public List<ItemsImg> getItemImgList() {
        return itemImgList;
    }

    public void setItemImgList(List<ItemsImg> itemImgList) {
        this.itemImgList = itemImgList;
    }

    public List<ItemsSpec> getItemSpecList() {
        return itemSpecList;
    }

    public void setItemSpecList(List<ItemsSpec> itemSpecList) {
        this.itemSpecList = itemSpecList;
    }
}
