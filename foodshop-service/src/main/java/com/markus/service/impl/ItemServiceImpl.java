package com.markus.service.impl;

import com.markus.enums.CommentLevel;
import com.markus.mapper.*;
import com.markus.pojo.*;
import com.markus.pojo.vo.CommentLevelCount;
import com.markus.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author: markus
 * @date: 2022/10/15 2:01 PM
 * @Description: 商品相关接口实现类
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemsMapper itemsMapper;
    @Autowired
    private ItemsImgMapper itemsImgMapper;
    @Autowired
    private ItemsParamMapper itemsParamMapper;
    @Autowired
    private ItemsSpecMapper itemsSpecMapper;
    @Autowired
    private ItemsCommentsMapper itemsCommentsMapper;

    @Override
    public Items queryItemById(String itemId) {
        return itemsMapper.selectByPrimaryKey(itemId);
    }

    @Override
    public ItemsParam queryItemParamById(String itemId) {
        Example example = new Example(ItemsParam.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId", itemId);
        return itemsParamMapper.selectOneByExample(example);
    }

    @Override
    public List<ItemsSpec> queryItemSpecList(String itemId) {
        Example example = new Example(ItemsSpec.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId", itemId);
        return itemsSpecMapper.selectByExample(example);
    }

    @Override
    public List<ItemsImg> queryItemImgList(String itemId) {
        Example example = new Example(ItemsImg.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId", itemId);
        return itemsImgMapper.selectByExample(example);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public CommentLevelCount queryCommentLevelCount(String itemId) {
        Example example = new Example(ItemsComments.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId", itemId);
        List<ItemsComments> itemsCommentsList = itemsCommentsMapper.selectByExample(example);
        return constructCommentLevelCount(itemsCommentsList);
    }

    private CommentLevelCount constructCommentLevelCount(List<ItemsComments> itemsCommentsList) {
        int totalCounts = 0, goodCounts = 0, normalCounts = 0, badCounts = 0;
        for (ItemsComments itemsComments : itemsCommentsList) {
            int level = itemsComments.getCommentLevel();
            switch (CommentLevel.getByType(level)) {
                case GOOD:
                    goodCounts++;
                    break;
                case NORMAL:
                    normalCounts++;
                    break;
                case BAD:
                    badCounts++;
                    break;
            }
        }
        totalCounts = goodCounts + normalCounts + badCounts;
        CommentLevelCount commentLevelCount = new CommentLevelCount();
        commentLevelCount.setTotalCounts(totalCounts);
        commentLevelCount.setGoodCounts(goodCounts);
        commentLevelCount.setNormalCounts(normalCounts);
        commentLevelCount.setBadCounts(badCounts);
        return commentLevelCount;
    }
}
