package com.hasaker.common.consts;

import cn.hutool.core.codec.Base64;

/**
 * @author 余天堂
 * @since 2019/10/31 17:40
 */
public final class Consts {

    // 数据库字段名
    public static final String ID = "id";
    public static final String CREATE_TIME = "create_time";
    public static final String CREATE_USER = "create_user";
    public static final String UPDATE_TIME = "update_time";
    public static final String UPDATE_USER = "update_user";
    public static final String IS_ENABLED = "is_enabled";
    public static final String IS_DELETED = "is_deleted";
    public static final String VERSION = "version";

    // 布尔类型
    public static final String SUCCESS = "1";
    public static final String FAILURE = "0";
    public static final Integer TRUE = 1;
    public static final Integer FALSE = 0;
    public static final Integer YES = 1;
    public static final Integer NO = 0;

    // 朋友圈可见性
    public static final Integer SECRET = 1;
    public static final Integer FRIEND = 2;
    public static final Integer OPEN = 3;

    // JWT-account:
    public static final String ACCOUNT = "account";
    // JWT-currentTimeMillis:
    public static final String CURRENT_TIME_MILLIS = "currentTimeMillis";

    // Password max & min length
    public static final Integer PASSWORD_MIN_LEN = 8;
    public static final Integer PASSWORD_MAX_LEN = 16;

    // JWT Assign Key
    public static final String JWT_ASSIGN_KEY = Base64.encode("djshfsdjfhsdjafjsdhfjsdahfjsdfjsdjnbvd" +
            "jsjfsdmsdjhjsdhfjsdfhjsdbvjvdsbvsdbjfhsdjfhsdjfhsdjfsdjfsdjfhsdjfhsdjfssdhfsdjfgsdhfghdsgfsdhfs" +
            "sdhfsdjhfjsdfhsdhfjsdfhsdjfhsdhjfjhsdhjhfjsdhjfhjsdhjfhjsdhjfhsdfhjdshjjfhd");
}
