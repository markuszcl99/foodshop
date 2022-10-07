package com.markus.controller;

import com.markus.pojo.Users;
import com.markus.pojo.bo.UserBO;
import com.markus.service.UserService;
import com.markus.utils.CommonReturnResult;
import com.markus.utils.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: markus
 * @date: 2022/10/2 10:25 PM
 * @Description: 用户通行controller
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@Api(value = "注册登录", tags = {"用于注册登录的相关接口"})
@RestController
@RequestMapping("passport")
public class PassportController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户名是否存在", notes = "用户名是否存在", httpMethod = "GET")
    @GetMapping("/usernameIsExist")
    public CommonReturnResult usernameIsExist(@RequestParam String username) {
        //  1. 用户名不能为空
        if (StringUtils.isBlank(username)) {
            return CommonReturnResult.errorMsg("用户名不能为空");
        }

        //  2. 检测用户名是否存在
        boolean isExist = userService.queryUsernameIsExist(username);
        if (isExist) {
            return CommonReturnResult.errorMsg("用户名已经存在");
        }

        return CommonReturnResult.ok();
    }

    @ApiOperation(value = "用户注册", notes = "用户注册", httpMethod = "POST")
    @PostMapping("/register")
    public CommonReturnResult register(@RequestBody UserBO userBO) {
        String username = userBO.getUsername();
        String password = userBO.getPassword();
        String confirmPwd = userBO.getConfirmPassword();
        // 1. 用户名和密码不能为空
        if (StringUtils.isBlank(username)
                || StringUtils.isBlank(password)
                || StringUtils.isBlank(confirmPwd)) {
            return CommonReturnResult.errorMsg("用户名或密码不能为空");
        }

        // 2. 查询用户名是否存在
        boolean isExist = userService.queryUsernameIsExist(username);
        if (isExist) {
            return CommonReturnResult.errorMsg("用户名已经存在");
        }

        // 3. 密码长度不能小于6
        if (password.length() < 6) {
            return CommonReturnResult.errorMsg("密码长度不能小于6");
        }

        // 4. 两次密码输入必须保持一致
        if (!password.equals(confirmPwd)) {
            return CommonReturnResult.errorMsg("两次密码输入不一致");
        }

        // 5. 实现注册
        userService.createUser(userBO);

        return CommonReturnResult.ok();
    }

    @ApiOperation(value = "用户登录", notes = "用户登录", httpMethod = "POST")
    @PostMapping("/login")
    public CommonReturnResult login(@RequestBody UserBO userBO) throws Exception {
        String username = userBO.getUsername();
        String password = userBO.getPassword();
        // 1. 用户名和密码不能为空
        if (StringUtils.isBlank(username)
                || StringUtils.isBlank(password)) {
            return CommonReturnResult.errorMsg("用户名或密码不能为空");
        }

        // 2. 实现登录
        Users userResult = userService.queryUserForLogin(username, MD5Utils.getMD5Str(password));

        if (userResult == null) {
            return CommonReturnResult.errorMsg("用户名或密码不正确");
        }
        return CommonReturnResult.ok(userResult);
    }

}
