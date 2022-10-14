package com.markus.mapper;

import com.markus.my.mapper.MyMapper;
import com.markus.pojo.Category;
import com.markus.pojo.vo.CategoryVO;
import com.markus.pojo.vo.NewItemVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CategoryMapperCustom {

    public List<CategoryVO> getSubCatList(int rootCatId);

    public List<NewItemVO> getSixNewItemsLazy(@Param("paramMap") Map<String,Object> param);
}