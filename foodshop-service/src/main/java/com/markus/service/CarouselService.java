package com.markus.service;

import com.markus.pojo.Carousel;

import java.util.List;

/**
 * @author: markus
 * @date: 2022/10/11 11:39 PM
 * @Description: 轮播图相关接口
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public interface CarouselService {
    /**
     * 查询所有轮播图集合数据
     * @param isShow
     * @return
     */
    public List<Carousel> queryAll(int isShow);
}
