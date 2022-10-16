package com.markus.service;

import com.markus.pojo.Items;
import com.markus.pojo.ItemsImg;
import com.markus.pojo.ItemsParam;
import com.markus.pojo.ItemsSpec;
import com.markus.pojo.vo.CommentLevelCountVO;
import com.markus.utils.PagedGridResult;

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
     *
     * @param itemId
     * @return
     */
    public List<ItemsSpec> queryItemSpecList(String itemId);

    /**
     * 根据id查询商品图片
     *
     * @param itemId
     * @return
     */
    public List<ItemsImg> queryItemImgList(String itemId);

    /**
     * 查询各评价等级的评价数量
     *
     * @param itemId
     * @return
     */
    public CommentLevelCountVO queryCommentLevelCount(String itemId);

    /**
     * 分页查询商品评价内容
     *
     * @param itemId
     * @param commentLevel
     * @param page
     * @param pageSize
     * @return
     */
    public PagedGridResult queryCommentContentVO(String itemId, Integer commentLevel, Integer page, Integer pageSize);

    /**
     * 关键词搜索商品接口
     *
     * @param keywords
     * @param sort
     * @param page
     * @param pageSize
     * @return
     */
    public PagedGridResult querySearchItemVO(String keywords, String sort, Integer page, Integer pageSize);

    /**
     * 通过分类ID搜索商品接口
     * @param catId
     * @param sort
     * @param page
     * @param pageSize
     * @return
     */
    public PagedGridResult querySearchItemVO(Integer catId, String sort, Integer page, Integer pageSize);
}
