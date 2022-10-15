package com.markus.controller;

import com.markus.pojo.*;
import com.markus.pojo.vo.CommentLevelCountVO;
import com.markus.pojo.vo.ItemInfoVO;
import com.markus.service.ItemService;
import com.markus.utils.CommonReturnResult;
import com.markus.utils.PagedGridResult;
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
public class ItemController extends BaseController {

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
        CommentLevelCountVO commentLevelCountVO = itemService.queryCommentLevelCount(itemId);
        return CommonReturnResult.ok(commentLevelCountVO);
    }

    @ApiOperation(value = "商品评价内容展示", notes = "商品评价内容展示", httpMethod = "GET")
    @GetMapping(value = "/comments")
    public CommonReturnResult comments(
            @ApiParam(name = "itemId", value = "商品ID", required = true)
            @RequestParam String itemId,
            @ApiParam(name = "level", value = "评价等级", required = false)
            @RequestParam Integer level,
            @ApiParam(name = "page", value = "要查询的页码", required = false)
            @RequestParam Integer page,
            @ApiParam(name = "pageSize", value = "一页显示的记录数", required = false)
            @RequestParam Integer pageSize) {
        if (StringUtils.isBlank(itemId)) {
            return CommonReturnResult.errorMsg("商品ID不能为空");
        }
        if (page == null) {
            page = 1;
        }
        if (pageSize == null) {
            pageSize = CURRENT_PAGE_RECORD_COUNTS;
        }
        PagedGridResult result = itemService.queryCommentContentVO(itemId, level, page, pageSize);
        return CommonReturnResult.ok(result);
    }
}
