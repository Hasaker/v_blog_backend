package com.hasaker.account.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hasaker.account.entity.Friend;
import com.hasaker.account.entity.User;
import com.hasaker.account.enums.VisibilityEnums;
import com.hasaker.account.exception.enums.FriendExceptionEnums;
import com.hasaker.account.exception.enums.UserExceptionEnums;
import com.hasaker.account.mapper.FriendMapper;
import com.hasaker.account.service.FriendService;
import com.hasaker.account.service.UserService;
import com.hasaker.account.vo.request.RequestFriendAddVo;
import com.hasaker.account.vo.request.RequestFriendDeleteVo;
import com.hasaker.account.vo.request.RequestFriendRemarkVo;
import com.hasaker.account.vo.request.RequestFriendVisibilityVo;
import com.hasaker.account.vo.response.ResponseFriendVo;
import com.hasaker.common.base.impl.BaseServiceImpl;
import com.hasaker.common.consts.Consts;
import com.hasaker.common.exception.enums.CommonExceptionEnums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @package com.hasaker.account.service.impl
 * @author 余天堂
 * @create 2020/3/2 10:15
 * @description FriendServiceImpl
 */
@Service
public class FriendServiceImpl extends BaseServiceImpl<FriendMapper, Friend> implements FriendService {

    @Autowired
    private UserService userService;

    /**
     * 添加好友
     * @param friendAddVo
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addFriend(RequestFriendAddVo friendAddVo) {
        CommonExceptionEnums.NOT_NULL_ARG.assertNotEmpty(friendAddVo);
        CommonExceptionEnums.NOT_NULL_ARG.assertNotEmpty(friendAddVo.getUserId());
        CommonExceptionEnums.NOT_NULL_ARG.assertNotEmpty(friendAddVo.getFriendId());

        User friend = userService.getById(friendAddVo.getUserId());
        UserExceptionEnums.USER_NOT_EXISTS.assertNotEmpty(friend);

        Friend friendship = new Friend();
        friendship.setUserId(friendAddVo.getUserId());
        friendship.setFriendId(friendAddVo.getFriendId());
        friendship.setRemark(friend.getNickname());
        friendship.setVisibility(VisibilityEnums.VISIBLE_FOR_BOTH.getCode());

        this.save(friendship);
    }

    /**
     * 删除好友
     * @param friendDeleteVo
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteFriend(RequestFriendDeleteVo friendDeleteVo) {
        CommonExceptionEnums.NOT_NULL_ARG.assertNotEmpty(friendDeleteVo);
        CommonExceptionEnums.NOT_NULL_ARG.assertNotEmpty(friendDeleteVo.getUserId());
        CommonExceptionEnums.NOT_NULL_ARG.assertNotEmpty(friendDeleteVo.getFriendId());

        QueryWrapper<Friend> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Friend.USER_ID, friendDeleteVo.getUserId());
        queryWrapper.eq(Friend.FRIEND_ID, friendDeleteVo.getFriendId());
        Friend friend = this.getOne(queryWrapper);
        FriendExceptionEnums.FRIEND_NOT_EXISTS.assertNotEmpty(friend);

        friend.setIsDeleted(Consts.YES);

        this.updateById(friend);
    }

    /**
     * 改变好友可见性
     * @param friendVisibilityVo
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void changeVisibility(RequestFriendVisibilityVo friendVisibilityVo) {
        CommonExceptionEnums.NOT_NULL_ARG.assertNotEmpty(friendVisibilityVo);
        CommonExceptionEnums.NOT_NULL_ARG.assertNotEmpty(friendVisibilityVo.getUserId());
        CommonExceptionEnums.NOT_NULL_ARG.assertNotEmpty(friendVisibilityVo.getFriendId());
        CommonExceptionEnums.NOT_NULL_ARG.assertNotEmpty(friendVisibilityVo.getVisibility());

        QueryWrapper<Friend> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Friend.USER_ID, friendVisibilityVo.getUserId());
        queryWrapper.eq(Friend.FRIEND_ID, friendVisibilityVo.getFriendId());
        Friend friend = this.getOne(queryWrapper);
        FriendExceptionEnums.FRIEND_NOT_EXISTS.assertNotEmpty(friend);

        friend.setVisibility(friendVisibilityVo.getVisibility());

        this.updateById(friend);
    }

    /**
     * 修改好友昵称
     * @param friendRemarkVo
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void changeRemark(RequestFriendRemarkVo friendRemarkVo) {
        CommonExceptionEnums.NOT_NULL_ARG.assertNotEmpty(friendRemarkVo);
        CommonExceptionEnums.NOT_NULL_ARG.assertNotEmpty(friendRemarkVo.getUserId());
        CommonExceptionEnums.NOT_NULL_ARG.assertNotEmpty(friendRemarkVo.getFriendId());
        CommonExceptionEnums.NOT_NULL_ARG.assertNotEmpty(friendRemarkVo.getRemark());

        QueryWrapper<Friend> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Friend.USER_ID, friendRemarkVo.getUserId());
        queryWrapper.eq(Friend.FRIEND_ID, friendRemarkVo.getFriendId());
        Friend friend = this.getOne(queryWrapper);
        FriendExceptionEnums.FRIEND_NOT_EXISTS.assertNotEmpty(friend);

        friend.setRemark(friendRemarkVo.getRemark());

        this.updateById(friend);
    }

    /**
     * 获取好友列表
     * @param userId
     * @return
     */
    @Override
    public List<ResponseFriendVo> listFriends(Long userId) {
        CommonExceptionEnums.NOT_NULL_ARG.assertNotEmpty(userId);

        QueryWrapper<Friend> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Friend.USER_ID, userId);
        List<Friend> friends = this.list(queryWrapper);

        if (ObjectUtil.isNotEmpty(friends)) {
            return friends.stream()
                    .map(o -> Convert.convert(ResponseFriendVo.class, o))
                    .collect(Collectors.toList());
        }

        return new ArrayList<>();
    }

}
