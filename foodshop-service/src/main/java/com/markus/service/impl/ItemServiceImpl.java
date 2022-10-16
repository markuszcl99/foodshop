package com.markus.service.impl;

import com.github.pagehelper.PageHelper;
import com.markus.enums.CommentLevel;
import com.markus.enums.SearchItemSortField;
import com.markus.mapper.*;
import com.markus.pojo.*;
import com.markus.pojo.vo.CommentLevelCountVO;
import com.markus.pojo.vo.ItemCommentContentVO;
import com.markus.pojo.vo.SearchItemVO;
import com.markus.pojo.vo.ShopCartVO;
import com.markus.service.ItemService;
import com.markus.utils.DesensitizationUtil;
import com.markus.utils.PagedGridResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

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
    private ItemsMapperCustom itemsMapperCustom;

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
        List<ItemCommentContentVO> list = itemsMapperCustom.getItemCommentsList(paramMap);
        // 脱敏处理
        list.stream().forEach(vo -> vo.setNickname(DesensitizationUtil.commonDisplay(vo.getNickname())));
        return PagedGridResult.getPagedGridResult(list, page);
    }

    @Override
    public PagedGridResult querySearchItemVO(String keywords, String sort, Integer page, Integer pageSize) {
        SearchItemSortField searchItemSortField = SearchItemSortField.getSearchItemSortField(sort);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("keywords", keywords);
        paramMap.put("sortField", searchItemSortField.backEndSortField);
        paramMap.put("isDesc", searchItemSortField.isDesc);

        PageHelper.startPage(page, pageSize);
        List<SearchItemVO> list = itemsMapperCustom.searchItemByKeyword(paramMap);
        return PagedGridResult.getPagedGridResult(list, page);
    }

    @Override
    public PagedGridResult querySearchItemVO(Integer catId, String sort, Integer page, Integer pageSize) {
        SearchItemSortField searchItemSortField = SearchItemSortField.getSearchItemSortField(sort);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("catId", catId);
        paramMap.put("sortField", searchItemSortField.backEndSortField);
        paramMap.put("isDesc", searchItemSortField.isDesc);

        PageHelper.startPage(page, pageSize);
        List<SearchItemVO> list = itemsMapperCustom.searchItemByKeyword(paramMap);
        return PagedGridResult.getPagedGridResult(list, page);
    }

    @Override
    public List<ShopCartVO> queryShopCartItemsBySpecId(String specIds) {
        String[] specIdArr = specIds.split(",");
        List<String> specIdList = new ArrayList<>();
        Collections.addAll(specIdList, specIdArr);
        return itemsMapperCustom.getItemInfoBySpecIds(specIdList);
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
