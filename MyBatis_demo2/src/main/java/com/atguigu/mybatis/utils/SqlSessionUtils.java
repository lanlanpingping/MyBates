package com.atguigu.mybatis.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Stack;

public class SqlSessionUtils {

    public static SqlSession getSqlSession(){
        try {
            //读取mybatis的核心配置文件，获取一个字节输入流
            InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
            //获取一个SqlSessionFactoryBulider,在通过里面的build()方法，获取一个SqlSessionFactory
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
            //通过sqlSessionFactory.openSession(true)方法获取一个sqlSession，并且设置事务自动提交
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            return sqlSession;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
