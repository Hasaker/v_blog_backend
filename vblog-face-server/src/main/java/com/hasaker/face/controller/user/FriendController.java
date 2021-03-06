package com.hasaker.face.controller.user;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.hasaker.account.feign.FriendClient;
import com.hasaker.account.vo.request.*;
import com.hasaker.common.vo.Ajax;
import com.hasaker.common.vo.PageInfo;
import com.hasaker.face.controller.base.BaseController;
import com.hasaker.face.service.user.FriendService;
import com.hasaker.face.vo.request.SearchVo;
import com.hasaker.face.vo.response.ResponseFriendInfoVo;
import com.hasaker.face.vo.response.ResponseFriendRequestVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @package com.hasaker.face.controller.user
 * @author 余天堂
 * @create 2020/3/26 14:51
 * @description FriendController
 */
@RestController
@RequestMapping(value = "/user/friend")
public class FriendController extends BaseController {

    @Autowired
    private FriendClient friendClient;
    @Autowired
    private FriendService friendService;

    @ApiOperation(value = "Send a friend request")
    @PostMapping(value = "/request/send")
    public Ajax sendFriendRequest(@RequestBody RequestFriendRequestVo requestVo) {
        requestVo.setSenderId(getUserId());
        if (ObjectUtils.isNull(requestVo.getSenderVisibility())) {
            requestVo.setSenderVisibility(1);
        }
        return friendClient.sendFriendRequest(requestVo);
    }

    @ApiOperation(value = "Accept a friend request")
    @PostMapping(value = "/request/accept")
    public Ajax acceptFriendRequest(@RequestBody RequestFriendRequestAcceptVo acceptVo) {
        if (ObjectUtils.isNull(acceptVo.getAccepterVisibility())) {
            acceptVo.setAccepterVisibility(1);
        }
        return friendClient.acceptFriendRequest(acceptVo);
    }

    @ApiOperation(value = "Deny a friend request")
    @PostMapping(value = "/request/deny/{friendRequestId}")
    public Ajax denyFriendRequest(@PathVariable Long friendRequestId) {
        return friendClient.denyFriendRequest(friendRequestId);
    }

    @ApiOperation(value = "Ignore a friend request")
    @PostMapping(value = "/request/ignore/{friendRequestId}")
    public Ajax ignoreFriendRequest(@PathVariable Long friendRequestId) {
        return friendClient.ignoreFriendRequest(friendRequestId);
    }

    @ApiOperation(value = "Delete a friend")
    @PostMapping(value = "/delete")
    public Ajax deleteFriend(@RequestBody RequestFriendDeleteVo deleteFriendVo) {
        deleteFriendVo.setUserId(getUserId());
        return friendClient.deleteFriend(deleteFriendVo);
    }

    @ApiOperation(value = "Change friend's remark")
    @PostMapping(value = "/remark")
    public Ajax changeRemark(@RequestBody RequestFriendRemarkVo changeRemarkVo) {
        changeRemarkVo.setUserId(getUserId());
        return friendClient.changeRemark(changeRemarkVo);
    }

    @ApiOperation(value = "Change friend's visibility")
    @PostMapping(value = "/visibility")
    public Ajax changeVisibility(@RequestBody RequestFriendVisibilityVo changeVisibilityVo) {
        changeVisibilityVo.setUserId(getUserId());
        return friendClient.changeVisibility(changeVisibilityVo);
    }

    @ApiOperation(value = "List friends")
    @PostMapping(value = "/list")
    public Ajax<PageInfo<ResponseFriendInfoVo>> listFriend(@RequestBody SearchVo searchVo) {
        searchVo.setUserId(getUserId());

        return Ajax.getInstance().successT(friendService.list(searchVo));
    }

    @GetMapping(value = "/list/request")
    public Ajax<List<ResponseFriendRequestVo>> listFriendRequest() {

        return Ajax.getInstance().successT(friendService.listFriendRequest(getUserId()));
    }
}
