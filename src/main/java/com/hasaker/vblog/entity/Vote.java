package com.hasaker.vblog.entity;

import com.hasaker.vblog.base.BaseEntity;

import java.util.Date;

/**
 * @author 余天堂
 * @since 2019/10/31 22:52
 * 点赞实体类
 */
public class Vote extends BaseEntity {

    private String postId;

    private String voteUserId;

    private Date voteTime;
}