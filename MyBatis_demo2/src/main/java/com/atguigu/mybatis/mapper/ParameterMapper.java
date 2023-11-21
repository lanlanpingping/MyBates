package com.atguigu.mybatis.mapper;

import com.atguigu.mybatis.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface ParameterMapper {

    /**
     * 验证登入
     */
    User checkLoginByParam(@Param("username") String username, @Param("password") String passwrod);

    /**
     * 添加用户信息
     */
    int insertUser(User user);


    /**
     * 验证登录（参数为map）
     */
    User chackLoginByMap(Map<String , Object> map);

    /**
     * 验证登入
     */
    User checkLogin(String  username, String password);


    /**
     * 查询所有的员工信息
     */
    List<User> getAllUser();

    /**
     * 根据用户名来查询用户信息
     */
    User getUserByUserName(String username);
}
