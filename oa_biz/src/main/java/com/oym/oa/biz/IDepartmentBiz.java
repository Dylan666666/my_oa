package com.oym.oa.biz;

import com.oym.oa.entity.Department;

import java.util.List;

public interface IDepartmentBiz {

    /**
     * 添加部门信息
     * @param department
     */
    void add(Department department);

    /**
     * 更新部门信息
     * @param department
     */
    void edit(Department department);

    /**
     * 删除部门信息
     * @param sn
     */
    void remove(String sn);

    /**
     * 查询指定部门信息
     * @param sn
     * @return
     */
    Department get(String sn);

    /**
     * 获取所有部门信息
     * @return
     */
    List<Department> getAll();

}
