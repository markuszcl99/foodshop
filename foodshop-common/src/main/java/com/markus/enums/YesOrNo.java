package com.markus.enums;

/**
 * @author: markus
 * @date: 2022/10/11 11:45 PM
 * @Description: 是否 枚举
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public enum YesOrNo {
    NO(0, "否"),
    YES(1, "是");

    public final Integer type;
    public final String description;

    YesOrNo(Integer type, String description) {
        this.type = type;
        this.description = description;
    }
}
