package com.oym.oa.biz;

import com.oym.oa.entity.Employee;

import java.util.List;

public interface IEmployeeBiz {

    /**
     * 添加员工信息
     * @param employee
     */
    void add(Employee employee);

    /**
     * 更新员工信息
     * @param employee
     */
    void edit(Employee employee);

    /**
     * 删除员工信息
     * @param sn
     */
    void remove(String sn);

    /**
     * 查询指定员工信息
     * @param sn
     * @return
     */
    Employee get(String sn);

    /**
     * 获取所有员工信息
     * @return
     */
    List<Employee> getAll();

}
