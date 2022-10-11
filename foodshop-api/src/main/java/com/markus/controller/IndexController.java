package com.markus.controller;

import com.markus.enums.YesOrNo;
import com.markus.pojo.Carousel;
import com.markus.service.CarouselService;
import com.markus.utils.CommonReturnResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

    @ApiOperation(value = "获取首页轮播图列表", notes = "获取首页轮播图列表", httpMethod = "GET")
    @GetMapping("/carousel")
    public CommonReturnResult carousel() {
        List<Carousel> result = carouselService.queryAll(YesOrNo.YES.type);
        return CommonReturnResult.ok(result);
    }
}
