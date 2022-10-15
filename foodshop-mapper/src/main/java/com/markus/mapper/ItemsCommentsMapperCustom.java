package com.markus.mapper;

import com.markus.my.mapper.MyMapper;
import com.markus.pojo.ItemsComments;
import com.markus.pojo.vo.ItemCommentContentVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 自定义mapper
 */
public interface ItemsCommentsMapperCustom {
    public List<ItemCommentContentVO> getItemCommentsList(@Param("paramMap") Map<String, Object> paramMap);
}