package com.atguigu.mybatis.test;

import com.atguigu.mybatis.mapper.SelectMapper;
import com.atguigu.mybatis.pojo.User;
import com.atguigu.mybatis.utils.SqlSessionUtils;
import jdk.nashorn.api.scripting.ScriptObjectMirror;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class SelectMapperTest {
    /**
     * MyBatis的各种查询功能
     * 1、若查询出的数据只有一条，
     *  可以通过实体类对象,
     *  或者集合接收
     * 2、若查询出的数据有多条，
     *  可以通过实体类型的List集合接收,
     *  可以通过map类型的list集合接收
     *  或者使用 @Mapkey(value = " ")
     *  {4={password=[B@48aca48b, sex=女, id=4, age=22, email=123456@qq.com, username=lanlan},
     *  5={password=[B@13fd2ccd, sex=女, id=5, age=22, email=123456@qq.com, username=兰兰}}
     *      结果：{password=[B@4b0d79fc, sex=女, id=5, age=22, email=123456@qq.com, username=兰兰}
     *  一定不能通过实体类来接收，此时会抛异常
     *
     *  MyBatis中设置类默认的类型别名
     *  Java.lang.Integer --> int ,integer
     *  int --> _int , _integer
     *  Map --> map
     *  String --> string
     */

    @Test
    public void testGetUserById(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);

        List<User> user = mapper.getUserById(4);
        System.out.println(user);
    }

    @Test
    public void testGetAllUser(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);

        List<User> allUser = mapper.getAllUser();
        allUser.forEach(user -> System.out.println(user));
    }

    @Test
    public void testGetCount(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);

        Integer count = mapper.getCount();
        System.out.println(count);
    }

    @Test
    public void testGetUserByIdToMap(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);

        Map<String, Object> userByidToMap = mapper.getUserByidToMap(5);

        System.out.println(userByidToMap);
    }

    @Test
    public void testGetAllUserToMap(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        Map<String, Object> allUserToMap = mapper.getAllUserToMap();

        System.out.println(allUserToMap);

    }

}
