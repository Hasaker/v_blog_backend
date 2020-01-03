package com.hasaker.vblog.service;

import com.hasaker.vblog.base.BaseService;
import com.hasaker.vblog.entity.User;

/**
 * @package com.hasaker.vblog.service
 * @author 余天堂
 * @create 2020/1/2 17:19
 * @description UserService
 */
public interface UserService extends BaseService<User> {

    User loadUserByUsername(String username);
}