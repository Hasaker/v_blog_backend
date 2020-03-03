package com.hasaker.account.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.hasaker.account.entity.User;
import com.hasaker.account.exception.enums.UserExceptionEnums;
import com.hasaker.account.mapper.UserMapper;
import com.hasaker.account.service.RoleService;
import com.hasaker.account.service.UserService;
import com.hasaker.common.base.impl.BaseServiceImpl;
import com.hasaker.common.consts.Consts;
import com.hasaker.common.exception.enums.CommonExceptionEnums;
import com.hasaker.common.vo.OAuthUserVo;
import com.hasaker.vo.account.request.RequestUserUpdateVo;
import com.hasaker.vo.account.response.ResponseUserDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @package com.hasaker.vblog.service.impl
 * @author 余天堂
 * @create 2020/1/2 17:21
 * @description UserServiceImpl
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private RoleService roleService;
    @Autowired
    private UserMapper userMapper;

    /**
     * 获取用户登录信息
     * @param username
     * @return
     */
    @Override
    public OAuthUserVo findUserByUserName(String username) {
        CommonExceptionEnums.NOT_NULL_ARG.isTrue(StringUtils.isBlank(username));

        User user = userMapper.findUserByUserName(username);
        UserExceptionEnums.USER_NOT_EXISTS.isTrue(ObjectUtil.isNull(user));

        OAuthUserVo oAuthUserVo = Convert.convert(OAuthUserVo.class, user);
        oAuthUserVo.setRoles(roleService.getRolesByUserId(user.getId()));

        return oAuthUserVo;
    }

    /**
     * 创建用户
     * @param username
     * @param password
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean createUser(String username, String password) {
        CommonExceptionEnums.NOT_NULL_ARG.assertNotEmpty(username);
        CommonExceptionEnums.NOT_NULL_ARG.assertNotEmpty(password);

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username", username);
        userQueryWrapper.eq(Consts.IS_DELETED, Consts.NO);
        User user = this.getOne(userQueryWrapper);
        UserExceptionEnums.USERNAME_ALREADY_EXISTS.assertEmpty(user);

        user = new User();
        user.setUsername(username);
        user.setPassword(password);

        return this.save(user);
    }

    /**
     * 用户修改密码
     * @param username
     * @param password
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean changePassword(String username, String password) {
        CommonExceptionEnums.NOT_NULL_ARG.assertNotEmpty(username);
        CommonExceptionEnums.NOT_NULL_ARG.assertNotEmpty(password);

        User user = userMapper.findUserByUserName(username);
        UserExceptionEnums.USER_NOT_EXISTS.assertNotEmpty(user);

        user.setPassword(password);

        return this.updateById(user);
    }

    /**
     * 用户更新资料
     * @param userUpdateVo
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateUser(RequestUserUpdateVo userUpdateVo) {
        CommonExceptionEnums.NOT_NULL_ARG.assertNotEmpty(userUpdateVo.getUsername());

        User user = userMapper.findUserByUserName(userUpdateVo.getUsername());
        UserExceptionEnums.USER_NOT_EXISTS.assertNotEmpty(user);

        User updatedUser = Convert.convert(User.class, userUpdateVo);
        updatedUser.setId(user.getId());

        return this.updateById(updatedUser);
    }

    /**
     * 根据用户名查询用户详情
     * @param username
     * @return
     */
    @Override
    public ResponseUserDetailVo userDetail(String username) {
        CommonExceptionEnums.NOT_NULL_ARG.assertNotEmpty(username);

        User user = userMapper.findUserByUserName(username);
        UserExceptionEnums.USER_NOT_EXISTS.assertNotEmpty(user);

        return Convert.convert(ResponseUserDetailVo.class, user);
    }
}