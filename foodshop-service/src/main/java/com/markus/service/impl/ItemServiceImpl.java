package com.markus.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.markus.enums.CommentLevel;
import com.markus.mapper.*;
import com.markus.pojo.*;
import com.markus.pojo.vo.CommentLevelCountVO;
import com.markus.pojo.vo.ItemCommentContentVO;
import com.markus.service.ItemService;
import com.markus.utils.PagedGridResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private ItemsCommentsMapperCustom itemsCommentsMapperCustom;

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
    public CommentLevelCountVO queryCommentLevelCount(String itemId) {
        Example example = new Example(ItemsComments.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId", itemId);
        List<ItemsComments> itemsCommentsList = itemsCommentsMapper.selectByExample(example);
        return constructCommentLevelCount(itemsCommentsList);
    }

    @Override
    public PagedGridResult queryCommentContentVO(String itemId, Integer commentLevel, Integer page, Integer pageSize) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("itemId", itemId);
        paramMap.put("commentLevel", commentLevel);

        PageHelper.startPage(page, pageSize);
        List<ItemCommentContentVO> list = itemsCommentsMapperCustom.getItemCommentsList(paramMap);
        return PagedGridResult.getPagedGridResult(list, page);
    }

    private CommentLevelCountVO constructCommentLevelCount(List<ItemsComments> itemsCommentsList) {
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
        CommentLevelCountVO commentLevelCountVO = new CommentLevelCountVO();
        commentLevelCountVO.setTotalCounts(totalCounts);
        commentLevelCountVO.setGoodCounts(goodCounts);
        commentLevelCountVO.setNormalCounts(normalCounts);
        commentLevelCountVO.setBadCounts(badCounts);
        return commentLevelCountVO;
    }
}
