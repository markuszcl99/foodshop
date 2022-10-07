package com.markus.enums;

/**
 * @author: markus
 * @date: 2022/10/7 4:59 PM
 * @Description: 性别 枚举
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public enum Sex {
    woman(0, "女"),
    man(1, "男"),
    secret(2, "保密");

    public final Integer type;
    public final String description;

    Sex(Integer type, String description) {
        this.type = type;
        this.description = description;
    }
}
