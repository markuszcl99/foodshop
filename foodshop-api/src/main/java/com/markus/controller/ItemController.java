package com.markus.controller;

import com.markus.enums.YesOrNo;
import com.markus.pojo.*;
import com.markus.pojo.vo.CommentLevelCount;
import com.markus.pojo.vo.ItemInfoVO;
import com.markus.service.ItemService;
import com.markus.utils.CommonReturnResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: markus
 * @date: 2022/10/15 2:06 PM
 * @Description: 商品详情页api接口
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@Api(value = "商品详情页接口", tags = {"用于商品详情页展示的相关接口"})
@RestController
@RequestMapping("items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @ApiOperation(value = "商品详情", notes = "商品详情", httpMethod = "GET")
    @GetMapping(value = "/info/{itemId}")
    public CommonReturnResult info(
            @ApiParam(name = "itemId", value = "商品ID", required = true)
            @PathVariable String itemId) {
        if (StringUtils.isBlank(itemId)) {
            return CommonReturnResult.errorMsg("商品ID不能为空！");
        }
        Items items = itemService.queryItemById(itemId);
        ItemsParam itemsParam = itemService.queryItemParamById(itemId);
        List<ItemsImg> itemsImgList = itemService.queryItemImgList(itemId);
        List<ItemsSpec> itemsSpecList = itemService.queryItemSpecList(itemId);

        ItemInfoVO itemInfoVO = new ItemInfoVO();
        itemInfoVO.setItem(items);
        itemInfoVO.setItemImgList(itemsImgList);
        itemInfoVO.setItemParams(itemsParam);
        itemInfoVO.setItemSpecList(itemsSpecList);

        return CommonReturnResult.ok(itemInfoVO);
    }

    @ApiOperation(value = "商品评价", notes = "商品评价", httpMethod = "GET")
    @GetMapping(value = "/commentLevel")
    public CommonReturnResult commentLevel(
            @ApiParam(name = "itemId", value = "商品ID", required = true)
            @RequestParam String itemId) {
        CommentLevelCount commentLevelCount = itemService.queryCommentLevelCount(itemId);
        return CommonReturnResult.ok(commentLevelCount);
    }
}
