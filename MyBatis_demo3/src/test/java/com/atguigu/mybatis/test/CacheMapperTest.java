package com.atguigu.mybatis.test;

import com.atguigu.mybatis.Utils.SqlSessionUtils;
import com.atguigu.mybatis.mapper.CacheMapper;
import com.atguigu.mybatis.pojo.Emp;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class CacheMapperTest {
    @Test
    public void test(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        CacheMapper mapper = sqlSession.getMapper(CacheMapper.class);
        Emp emp1 = mapper.getEmpById(4);
        System.out.println(emp1);

        CacheMapper mapper1 = sqlSession.getMapper(CacheMapper.class);

        Emp emp2 = mapper1.getEmpById(4);
        System.out.println(emp2);
    }

    @Test
    public void TestCacheInsert(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();

        CacheMapper mapper = sqlSession.getMapper(CacheMapper.class);
        Emp emp1 = mapper.getEmpById(4);
        System.out.println(emp1);
        sqlSession.clearCache();
//        mapper.insertEmp(new Emp(null,"兰兰",22,"女","1234@qq.com"));
        sqlSession.commit();
        Emp emp2 = mapper.getEmpById(4);
        System.out.println(emp2);
    }

    @Test
    public void testTwoCahche(){
        try {
            InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

            SqlSession sqlSession1 = sqlSessionFactory.openSession(true);
            SqlSession sqlSession2 = sqlSessionFactory.openSession(true);

            CacheMapper mapper = sqlSession1.getMapper(CacheMapper.class);
            Emp emp1 = mapper.getEmpById(4);
            System.out.println(emp1);
            sqlSession1.close();

            CacheMapper mapper1 = sqlSession2.getMapper(CacheMapper.class);
            Emp emp2 = mapper1.getEmpById(4);
            System.out.println(emp2);
            sqlSession2.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
