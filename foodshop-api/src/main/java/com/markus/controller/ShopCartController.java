package com.markus.controller;

import com.markus.pojo.bo.ShopCartBO;
import com.markus.utils.CommonReturnResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: markus
 * @date: 2022/10/16 9:06 PM
 * @Description: 购物车Controller
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@Api(value = "购物车controller", tags = {"购物车相关API"})
@RestController
@RequestMapping("shopcart")
public class ShopCartController {

    @PostMapping(value = "/add")
    public CommonReturnResult add(
            @ApiParam(name = "userId", value = "用户Id", required = true)
            @RequestParam String userId,
            @ApiParam(name = "shopcartBo", value = "购物车对象参数", required = true)
            @RequestBody ShopCartBO shopCartBO,
            @ApiParam(name = "request", value = "servlet请求", required = true)
                    HttpServletRequest request,
            @ApiParam(name = "request", value = "servlet响应", required = true)
                    HttpServletResponse response) {
        if (StringUtils.isBlank(userId)) {
            return CommonReturnResult.errorMsg("");
        }
        //
        System.out.println(shopCartBO);
        // todo 用户添加购物车 在前端添加至cookie，同时会请求后端将数据存储到redis
        return CommonReturnResult.ok();
    }

}
