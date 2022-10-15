package com.markus.service;

import com.markus.pojo.Carousel;
import com.markus.pojo.Category;
import com.markus.pojo.vo.CategoryVO;
import com.markus.pojo.vo.NewItemVO;

import java.util.List;

/**
 * @author: markus
 * @date: 2022/10/11 11:39 PM
 * @Description: 分类信息相关接口
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public interface CategoryService {
    /**
     * 查询一级分类信息
     * @return
     */
    public List<Category> queryAllRootLevelCat();

    /**
     * 查询二级品类信息
     * @param rootCatId
     * @return
     */
    public List<CategoryVO> getSubCatList(Integer rootCatId);

    /**
     * 查询分类推荐商品信息
     * @param rootCatId
     * @return
     */
    public List<NewItemVO> getSixNewItemsLazy(Integer rootCatId);
}
