package com.markus.pojo.vo;

import java.util.Date;

/**
 * @author: markus
 * @date: 2022/10/15 9:48 PM
 * @Description: 商品评价内容
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class ItemCommentContentVO {

    private String userFace;
    private String nickname;
    private Date createdTime;
    private String content;
    private String specName;

    public String getUserFace() {
        return userFace;
    }

    public void setUserFace(String userFace) {
        this.userFace = userFace;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }
}
