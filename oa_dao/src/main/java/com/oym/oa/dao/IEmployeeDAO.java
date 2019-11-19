package com.oym.oa.dao;

import com.oym.oa.entity.Employee;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("employeeDAO")
public interface IEmployeeDAO {

    /**
     * 插入员工信息
     * @param employee
     */
    void insert(Employee employee);

    /**
     * 更新员工信息
     * @param employee
     */
    void update(Employee employee);

    /**
     * 删除该员工信息
     * @param sn
     */
    void delete(String sn);

    /**
     * 查询目的员工
     * @param sn
     * @return
     */
    Employee select(String sn);

    /**
     * 查询各员工信息
     * @return
     */
    List<Employee> selectAll();

    /**
     * 查询待处理人
     * @param dsn
     * @param post
     * @return
     */
    List<Employee> selectByDepartmentAndPost(@Param("dsn") String dsn,@Param("post") String post);

}
