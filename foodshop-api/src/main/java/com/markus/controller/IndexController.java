package com.markus.controller;

import com.markus.enums.YesOrNo;
import com.markus.pojo.Carousel;
import com.markus.pojo.Category;
import com.markus.pojo.vo.CategoryVO;
import com.markus.pojo.vo.NewItemVO;
import com.markus.service.CarouselService;
import com.markus.service.CategoryService;
import com.markus.utils.CommonReturnResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author: markus
 * @date: 2022/9/29 10:14 PM
 * @Description: demo
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@Api(value = "首页接口", tags = {"用于首页展示的相关接口"})
@RestController
@RequestMapping("index")
public class IndexController {

    @Autowired
    private CarouselService carouselService;

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "获取首页轮播图列表", notes = "获取首页轮播图列表", httpMethod = "GET")
    @GetMapping("/carousel")
    public CommonReturnResult carousel() {
        List<Carousel> result = carouselService.queryAll(YesOrNo.YES.type);
        return CommonReturnResult.ok(result);
    }

    @ApiOperation(value = "获取首页一级分类列表", notes = "获取首页一级分类列表", httpMethod = "GET")
    @GetMapping("/cats")
    public CommonReturnResult cats() {
        List<Category> result = categoryService.queryAllRootLevelCat();
        return CommonReturnResult.ok(result);
    }

    @ApiOperation(value = "获取首页二级分类列表", notes = "获取首页二级分类列表", httpMethod = "GET")
    @GetMapping("/subCat/{rootCatId}")
    public CommonReturnResult subCat(
            @ApiParam(name = "rootCatId", value = "一级分类ID", required = true)
            @PathVariable Integer rootCatId) {
        if (rootCatId == null) {
            return CommonReturnResult.errorMsg("分类不存在");
        }
        List<CategoryVO> result = categoryService.getSubCatList(rootCatId);
        return CommonReturnResult.ok(result);
    }

    @ApiOperation(value = "获取首页推荐分类栏最新的六个商品信息", notes = "获取首页推荐分类栏最新的六个商品信息", httpMethod = "GET")
    @GetMapping("/sixNewItems/{rootCatId}")
    public CommonReturnResult sixNewItems(
            @ApiParam(name = "rootCatId", value = "一级分类ID", required = true)
            @PathVariable Integer rootCatId) {
        if (rootCatId == null) {
            return CommonReturnResult.errorMsg("分类不存在");
        }
        List<NewItemVO> result = categoryService.getSixNewItemsLazy(rootCatId);
        return CommonReturnResult.ok(result);
    }
}
