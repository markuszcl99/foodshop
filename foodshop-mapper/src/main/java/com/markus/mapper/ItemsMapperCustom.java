package com.markus.mapper;

import com.markus.pojo.vo.ItemCommentContentVO;
import com.markus.pojo.vo.SearchItemVO;
import com.markus.pojo.vo.ShopCartVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 自定义mapper
 */
public interface ItemsMapperCustom {
    public List<ItemCommentContentVO> getItemCommentsList(@Param("paramMap") Map<String, Object> paramMap);

    public List<SearchItemVO> searchItemByKeyword(@Param("paramMap") Map<String, Object> paramMap);

    public List<SearchItemVO> searchItemByCatId(@Param("paramMap") Map<String, Object> paramMap);

    public List<ShopCartVO> getItemInfoBySpecIds(@Param("paramList") List<String> specIds);
}