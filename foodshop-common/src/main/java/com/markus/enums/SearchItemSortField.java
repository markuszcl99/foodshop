package com.markus.enums;

/**
 * @author: markus
 * @date: 2022/10/16 2:30 PM
 * @Description: 查询商品排序字段枚举
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public enum SearchItemSortField {
    DEFAULT_SORT("k", "i.item_name", false,"默认排序，以item.item_name为排序字段"),
    SELL_COUNT_SORT("c", "i.sell_counts", true,"销量倒排"),
    PRICE_SORT("p", "tempSpec.price_discount", false,"价格正排");
    public final String fontEndSortFlag;
    public final String backEndSortField;
    public final boolean isDesc;
    public final String description;

    SearchItemSortField(String fontEndSortFlag, String backEndSortField, boolean isDesc, String description) {
        this.fontEndSortFlag = fontEndSortFlag;
        this.backEndSortField = backEndSortField;
        this.isDesc = isDesc;
        this.description = description;
    }

    public static SearchItemSortField getSearchItemSortField(String fontEndSortFlag){
        for (SearchItemSortField value : values()) {
            if (value.fontEndSortFlag.equals(fontEndSortFlag)){
                return value;
            }
        }
        return DEFAULT_SORT;
    }
}
