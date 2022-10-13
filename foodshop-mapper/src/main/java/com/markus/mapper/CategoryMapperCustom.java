package com.markus.mapper;

import com.markus.my.mapper.MyMapper;
import com.markus.pojo.Category;
import com.markus.pojo.vo.CategoryVO;

import java.util.List;

public interface CategoryMapperCustom {

    public List<CategoryVO> getSubCatList(int rootCatId);
}