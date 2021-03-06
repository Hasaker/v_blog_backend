package com.hasaker.account.entity;

import com.hasaker.common.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @package com.hasaker.user.entity
 * @author 余天堂
 * @create 2020/2/11 11:48
 * @description Permission
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Permission extends BaseEntity {

    private String url;

    private String name;

    private String description;

    private Long pid;
}
