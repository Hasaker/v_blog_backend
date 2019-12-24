create table vblog.friendship
(
    id             bigint(40)   not null comment '好友关系ID',

    user_id         bigint(40)  not null comment '用户ID',
    friend_id       bigint(40)  not null comment '好友ID',
    remark          varchar(16) null     comment '好友备注',

    create_user    varchar(16)  null comment '创建用户',
    create_time    bigint(40)   null comment '创建时间',
    update_user    varchar(16)  null comment '更新人',
    update_time    bigint(40)   null comment '更新时间',
    is_deleted     char(1)      null comment '是否已删除(0未删除/1已删除)',
    version        int(1)       null comment '版本',

    constraint table_name_id_uindex unique (id)
);

alter table vblog.friendship add primary key (id);