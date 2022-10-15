package com.markus.enums;

import org.springframework.lang.Nullable;

/**
 * @author: markus
 * @date: 2022/10/11 11:45 PM
 * @Description: 是否 枚举
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public enum CommentLevel {
    GOOD(1, "好评"),
    NORMAL(2, "中评"),
    BAD(3, "差评");

    public final Integer type;
    public final String description;

    CommentLevel(Integer type, String description) {
        this.type = type;
        this.description = description;
    }

    @Nullable
    public static CommentLevel getByType(Integer type) {
        for (CommentLevel value : values()) {
            if (value.type == type) {
                return value;
            }
        }
        return null;
    }
}
