package com.wlt.springboot.controller;

import com.wlt.springboot.dao.EmployeeDao;
import com.wlt.springboot.entities.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

/**
 * @Author: T56
 * @Date: 2020-03-16 22:50
 */
@Controller
@Slf4j
public class EmployeeController {
    @Autowired
    private EmployeeDao employeeDao;

    @GetMapping("/emps")
    public String list(Model model){//这里可以用model，也可以yongmap，域对象都可以
        //模板引擎的默认前缀classpath:/templates/   后缀.html
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("emps",employees);
        log.info(employees.toString());
        return "emp/list";
    }

    @GetMapping("/emp")
    public String toAddPage(){
        //跳转到员工添加页面
        return "emp/add";
    }
}
