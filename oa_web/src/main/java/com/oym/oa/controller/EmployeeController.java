package com.oym.oa.controller;

import com.oym.oa.biz.IDepartmentBiz;
import com.oym.oa.biz.IEmployeeBiz;
import com.oym.oa.entity.Employee;
import com.oym.oa.global.Contant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller("employeeController")
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private IDepartmentBiz iDepartmentBiz;

    @Autowired
    private IEmployeeBiz iEmployeeBiz;

    @RequestMapping("/list")
    public String list(Map<String,Object> map) {
        map.put("list",iEmployeeBiz.getAll());
        return "employee_list";
    }

    @RequestMapping("/to_add")
    public String toAdd(Map<String,Object> map) {
        map.put("employee",new Employee());
        map.put("dlist",iDepartmentBiz.getAll());
        map.put("plist", Contant.getPosts());
        return "employee_add";
    }

    @RequestMapping("/add")
    public String add(Employee employee) {

        if ("".equals(employee.getSn().trim()) || "".equals(employee.getName())) {
            return "redirect:list";
        }

        try {
            iEmployeeBiz.add(employee);
        }catch (Exception e) {
            return "redirect:list";
        }

        return "redirect:list";
    }

    @RequestMapping(value = "/to_update",params = "sn")
    public String toUpdate(String sn,Map<String,Object> map) {
        map.put("employee",iEmployeeBiz.get(sn));
        map.put("dlist",iDepartmentBiz.getAll());
        map.put("plist", Contant.getPosts());
        return "employee_update";
    }

    @RequestMapping("/update")
    public String update(Employee employee) {

        if ("".equals(employee.getName())) {
            return "redirect:list";
        }

        iEmployeeBiz.edit(employee);
        return "redirect:list";
    }

    @RequestMapping(value = "/remove",params = "sn")
    public String remove(String sn) {
        iEmployeeBiz.remove(sn);
        return "redirect:list";
    }

}
