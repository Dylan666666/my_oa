package com.oym.oa.biz.impl;

import com.oym.oa.biz.IDepartmentBiz;
import com.oym.oa.dao.IDepartmentDAO;
import com.oym.oa.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("departmentBiz")
public class DepartmentBizImpl implements IDepartmentBiz {

    @Autowired
    private IDepartmentDAO departmentDAO;

    public void add(Department department) {
        departmentDAO.insert(department);
    }

    public void edit(Department department) {
        departmentDAO.update(department);
    }

    public void remove(String sn) {
        departmentDAO.delete(sn);
    }

    public Department get(String sn) {
        return departmentDAO.select(sn);
    }

    public List<Department> getAll() {
        return departmentDAO.selectAll();
    }
}
