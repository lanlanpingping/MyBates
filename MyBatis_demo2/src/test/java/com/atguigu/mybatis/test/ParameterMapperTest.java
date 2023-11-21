package com.atguigu.mybatis.test;

import com.atguigu.mybatis.mapper.ParameterMapper;
import com.atguigu.mybatis.pojo.User;
import com.atguigu.mybatis.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ParameterMapperTest {

    /**
     * mybatis获取参数值的两种方式:${}和#{}
     * ${}本质是字符串拼接 , 需要手动输入单引号，并且会出现sql注入的问题
     * #{}本质是占位符赋值 , 可以避免上面的问题
     *
     * MyBatis获取参数值的各种情况
     * 1、Mapper接口方法的参数为单个的字面量类型
     * 可以通过${}和#{}以任意的名称获取参数值，但是需要注意${}的单引号问题
     * 2、mappr接口方法的参数为多个时
     * 此时MyBatis会将这些参数放在一个map集合中，以两种方式进行存储
     * a> 以arg0，arg1 ..为键，参数值为值
     * b> 以param1，param2....为键，以参数值为值
     * 因此字需要通过#{}和${}以键的方式访问值即可，但是需要注意${}的单引号问题
     * 3、若mapper接口方法的参数为多个时，可以手动将这些参数放在一个map中存储
     * 只需要通过#{}和${}以键的方式访问值即可，但是需要注意${}的单引号问题
     * 4、ampper接口方法的参数是实体类类型的参数
     * 只需要通过#{}和${}以键的方式访问值即可，但是需要注意${}的单引号问题
     * 5、使用@parm来命名参数
     * 此时MyBatis会将这些参数放到放到一个map集合中，以两种方式进行存储
     * a> 以@ParamZ注解的值为键，参数值为值
     * b> 以param1，param2...为键，以参数值为值
     *因此只需要通过#{}和${}以键的方式访问值即可，但是需要注意${}的单引号问题
     *
     * 测试
     */



    @Test
    public void getAllUser() {

        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);

        List<User> users = mapper.getAllUser();

        users.forEach(user -> System.out.println(user));
    }

    @Test
    public void testJDBC() throws Exception{
        String username = "lanlan";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis",
                "root", "123456");

        PreparedStatement preparedStatement = connection.prepareStatement("select * from t_user where username= ?");
        preparedStatement.setString(1,username);

    }

    @Test
    public void getUserByUsername(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        User lanlan = mapper.getUserByUserName("lanlan");

        System.out.println(lanlan);
    }

    @Test
    public void checkLogin(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        User lanlan = mapper.checkLogin("宝贝1", "123456");

        System.out.println(lanlan);
    }

    @Test
    public void checkLoginByMap(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        Map<String , Object> map = new HashMap<>();
        map.put("username","lanlan");
        map.put("password","123456");
        User user = mapper.chackLoginByMap(map);
        System.out.println(user);

    }


    @Test
    public void testInsertUser(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        int i = mapper.insertUser(new User(null, "兰兰1", "123456", 29, "女", "123qq.com"));
        System.out.println(i);
    }

    @Test
    public void testGetUserByParam(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        User lanlan = mapper.checkLoginByParam("lanlan", "123456");
        System.out.println(lanlan);
    }

}


