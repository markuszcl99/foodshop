package com.markus.service;

import com.markus.pojo.Items;
import com.markus.pojo.ItemsImg;
import com.markus.pojo.ItemsParam;
import com.markus.pojo.ItemsSpec;
import com.markus.pojo.vo.CommentLevelCount;

import java.util.List;

/**
 * @author: markus
 * @date: 2022/10/15 12:41 PM
 * @Description: 商品详情接口
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public interface ItemService {
    /**
     * 通过商品逐渐id查询
     *
     * @param itemId
     * @return
     */
    public Items queryItemById(String itemId);

    /**
     * 根据商品id查询商品参数
     *
     * @param itemId
     * @return
     */
    public ItemsParam queryItemParamById(String itemId);

    /**
     * 根据id查询商品规格
     * @param itemId
     * @return
     */
    public List<ItemsSpec> queryItemSpecList(String itemId);

    /**
     * 根据id查询商品图片
     * @param itemId
     * @return
     */
    public List<ItemsImg> queryItemImgList(String itemId);

    /**
     * 查询各评价等级的评价数量
     * @param itemId
     * @return
     */
    public CommentLevelCount queryCommentLevelCount(String itemId);
}
