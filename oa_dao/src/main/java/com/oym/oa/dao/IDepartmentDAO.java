package com.oym.oa.dao;

import com.oym.oa.entity.Department;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("departmentDAO")
public interface IDepartmentDAO {

    /**
     * 插入新部门
     * @param department
     */
    void insert(Department department);

    /**
     * 更新部门信息
     * @param department
     */
    void update(Department department);

    /**
     * 删除该部门
     * @param sn
     */
    void delete(String sn);

    /**
     * 查询目的部门
     * @param sn
     * @return
     */
    Department select(String sn);

    /**
     * 查询各部门
     * @return
     */
    List<Department> selectAll();

}
