package com.oym.oa.biz.impl;

import com.oym.oa.biz.IEmployeeBiz;
import com.oym.oa.dao.IEmployeeDAO;
import com.oym.oa.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("employeeBiz")
public class EmployeeBizImpl implements IEmployeeBiz {

    @Autowired
    IEmployeeDAO employeeDAO;

    @Override
    public void add(Employee employee) {
        employee.setPassword("123456");
        employeeDAO.insert(employee);
    }

    @Override
    public void edit(Employee employee) {
        employeeDAO.update(employee);
    }

    @Override
    public void remove(String sn) {
        employeeDAO.delete(sn);
    }

    @Override
    public Employee get(String sn) {
        return employeeDAO.select(sn);
    }

    @Override
    public List<Employee> getAll() {
        return employeeDAO.selectAll();
    }
}
