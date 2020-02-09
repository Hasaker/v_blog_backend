package com.hasaker.user.mapper;

import com.hasaker.common.base.BaseMapper;
import com.hasaker.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @package com.hasaker.vblog.mapper
 * @author 余天堂
 * @create 2019/12/24 18:07
 * @description UserMapper
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user where username = #{username} and is_deleted = '0'")
    User findUserByUserName(@Param("username") String username);
}
