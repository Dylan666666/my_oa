package com.oym.oa.biz;

import com.oym.oa.entity.Employee;

public interface IGlobalBiz {

    /**
     * 员工登录
     * @param sn
     * @param password
     * @return
     */
    Employee login(String sn,String password);

    /**
     * 更改密码
     * @param employee
     */
    void changePassword(Employee employee);

}
