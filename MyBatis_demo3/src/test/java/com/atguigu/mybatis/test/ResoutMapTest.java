package com.atguigu.mybatis.test;

import com.atguigu.mybatis.Utils.SqlSessionUtils;
import com.atguigu.mybatis.mapper.DeptMapper;
import com.atguigu.mybatis.mapper.EmpMapper;
import com.atguigu.mybatis.pojo.Dept;
import com.atguigu.mybatis.pojo.Emp;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class ResoutMapTest {

    /**
     * 解决字段名和属性名不一样的情况：
     * a> 为字段器别名，保持和属性名一致
     * b> 设置全局配置，将_自动映射为驼峰
     *    将字段名中下划线自动映射为驼峰格式 emp_name : empName
     * c>
     *  <resultMap id="empResultMap" type="Emp">
     *         <!-- id property设置主键的映射关系 -->
     *         <!-- property 设置属性的名称 colum 设置表中字段的名称-->
     *         <id property="eid" column="eid"></id>
     *         <result property="empName" column="emp_name"></result>
     *         <result property="age" column="age"></result>
     *         <result property="sex" column="sex"></result>
     *         <result property="email" column="email"></result>
     *     </resultMap>
     *
     *
     *    处理多对一的映射关系
     *    a>级联属性赋值
     *    b>association
     *    c>分布查询
     *
     *
     *    处理一对多的映射关系
     *    a> collection
     *    b> 分布查询
     */

    @Test
    public void testGetAllEmp(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        List<Emp> allEmp = mapper.getAllEmp();
        allEmp.forEach(emp -> System.out.println(emp));
    }

    @Test
    public void testGetEmpAndDept(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Emp empAndDept = mapper.getEmpAndDept(2);
        System.out.println(empAndDept);
    }

    @Test
    public void testGetEmpAndDeptByStep(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Emp empAndDept = mapper.getEmpAndDeptBySteOne(1);
//        System.out.println(empAndDept.getEmpName());
        System.out.println("只获取了员工信息");
        System.out.println(empAndDept.getDept().getDeptName());

    }


    @Test
    public void testGetDeptAndEmp(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);
        Dept deptAndEmp = mapper.getDeptAndEmp(1);

        System.out.println(deptAndEmp);
    }

    @Test
    public void testGetDeptAndEmpByStep(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);
        Dept deptAndEmp = mapper.getDeptAndEmpByStepOne(1);

        System.out.println(deptAndEmp.getDeptName());
    }


}
