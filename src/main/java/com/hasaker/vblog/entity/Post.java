package com.hasaker.vblog.entity;

import com.hasaker.vblog.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 余天堂
 * @since 2019/10/31 17:00
 * 动态实体类
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post extends BaseEntity {

    private String topicId;

    private String content;

    private Integer visibility;

    private String postUserId;

    private Date postTime;
}