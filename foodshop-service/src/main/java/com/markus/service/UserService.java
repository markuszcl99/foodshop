package com.markus.service;

import com.markus.pojo.Users;
import com.markus.pojo.bo.UserBO;

/**
 * @author: markus
 * @date: 2022/10/2 10:08 PM
 * @Description: 用户Service接口
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public interface UserService {

    /**
     * 判断用户名是否存在
     */
    public boolean queryUsernameIsExist(String username);

    /**
     * 创建用户
     */
    public Users createUser(UserBO userBO);

    /**
     * 检索用户名和密码是否匹配，用于登录
     */
    public Users queryUserForLogin(String username, String password);
}
