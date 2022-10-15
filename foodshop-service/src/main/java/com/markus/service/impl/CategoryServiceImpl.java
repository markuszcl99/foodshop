package com.markus.service.impl;

import com.markus.mapper.CategoryMapper;
import com.markus.mapper.CategoryMapperCustom;
import com.markus.pojo.Category;
import com.markus.pojo.vo.CategoryVO;
import com.markus.pojo.vo.NewItemVO;
import com.markus.service.CategoryService;
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
 * @date: 2022/10/13 10:16 PM
 * @Description: 分类信息接口实现类
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryMapperCustom categoryMapperCustom;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Category> queryAllRootLevelCat() {
        Example example = new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("type", 1);

        return categoryMapper.selectByExample(example);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<CategoryVO> getSubCatList(Integer rootCatId) {
        return categoryMapperCustom.getSubCatList(rootCatId);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<NewItemVO> getSixNewItemsLazy(Integer rootCatId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("rootCatId", rootCatId);
        return categoryMapperCustom.getSixNewItemsLazy(paramMap);
    }
}
