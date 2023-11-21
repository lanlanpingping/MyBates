package com.atguigu.mybatis.test;

import com.atguigu.mybatis.Utils.SqlSessionUtils;
import com.atguigu.mybatis.mapper.DynamicSQLMapper;
import com.atguigu.mybatis.pojo.Emp;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.sql.Array;
import java.util.Arrays;
import java.util.List;


public class DynamicSQLMapperTest {
    /**
     * 动态sql
     * 1、if：根据标签中test属性所对应的表达式决定标签中的内容是否需要拼接到sql中
     * 2、where: 当where标签中有内容时，会自动生成where关键字，并且将内容前多余的and、or去掉
     *           当where标签中没有内容时，此时where标签没有任何效果
     *    注意：where标签不能将其中内容后面多余的and、or去掉，age = #{age}  and age = #{age}
     *          只能够去掉条件前面的 and age = #{age}
     *
     * 3、trim:将trim标签中内容前面或者后面添加指定内容
     * 若标签中有内容时：
     * prefix|suffix  将trim标签中内容前面或者后面去掉指定内容
     * suffixOverrides
     * prefixOverides
     *若标签中没有内容时，trim标签也没有任何效果
     *4、choose、when、otherwise ,相当于if .. else .. if .. else
     * when 至少要有一个，otherwise最多只有一个
     *5、foreach
     * collection:设置需要循环的数组或集合
     * item：表示数组或集合中的每一个数据
     * separator:循环体之间的分隔符
     * open:foreach 语句的以什么开头
     * close:foreach 语句以什么结尾
     *
     * 6、sql标签：sql片段
     *定义sql片段 ：<sql id="empColumns">eid,emp-name,age,sex,email</sql>
     * 应用sql片段：<include refid="empColumns"></include>
     */
    @Test
    public void testGetEmpByCondition(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);

        List<Emp> empByCondition = mapper.getEmpByCondition(new Emp(null, "lan", 22, "女", "11@qq.com"));

        System.out.println(empByCondition);
    }

    @Test
    public void testGetEmpByChoose(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
        List<Emp> lanlan = mapper.getEmpByChoose(new Emp(null, "", null, "", ""));
        System.out.println(lanlan);
    }

    @Test
    public void testDeleteMoreByArray(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
        Integer[] integers = {1,2,3};
        mapper.deleteMoreByArray(integers);
    }

    @Test
    public void testInsertMoreByList(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
        Emp emp1 = new Emp(null,"lan",22,"女","11@qq.com");
        Emp emp2 = new Emp(null,"兰兰1",22,"女","11@qq.com");
        Emp emp3 = new Emp(null,"兰兰2",22,"女","11@qq.com");
        Emp emp4 = new Emp(null,"兰兰3",22,"女","11@qq.com");

        List<Emp> emps = Arrays.asList(emp1, emp2, emp3, emp4);

        int i = mapper.insertMoreByList(emps);

        System.out.println(i);
    }
}
