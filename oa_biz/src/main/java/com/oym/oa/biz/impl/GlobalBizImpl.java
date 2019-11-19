package com.oym.oa.biz.impl;

import com.oym.oa.biz.IGlobalBiz;
import com.oym.oa.dao.IEmployeeDAO;
import com.oym.oa.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("globalBiz")
public class GlobalBizImpl implements IGlobalBiz {

    @Autowired
    private IEmployeeDAO employeeDAO;

    @Override
    public Employee login(String sn, String password) {
        Employee employee = employeeDAO.select(sn);
        if (employee != null && employee.getPassword().equals(password)) {
            return employee;
        }
        return null;
    }

    @Override
    public void changePassword(Employee employee) {
        if (employee.getPassword() != null && employee.getPassword().trim().length() > 5
                && employee.getPassword().trim().length() <= 16) {
            employeeDAO.update(employee);
        }
    }

}
