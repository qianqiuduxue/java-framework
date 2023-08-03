package com.zzb.dao;

import com.zzb.pojo.Department;
import com.zzb.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
@Repository
public class EmployeeDao {
    private static Map<Integer, Employee> employees = null;
    @Autowired
    private DepartmentDao departmentDao;
    static {
        employees=new HashMap<Integer, Employee>();

        employees.put(101,new Employee(1001,"AA","A234235232@qq.com",1,new Department(101,"教学部")));
        employees.put(102,new Employee(1002,"BB","B234235232@qq.com",0,new Department(102,"市场部")));
        employees.put(103,new Employee(1003,"CC","C234235232@qq.com",1,new Department(103,"教研部")));
        employees.put(104,new Employee(1004,"DD","D234235232@qq.com",0,new Department(104,"运营部")));
        employees.put(105,new Employee(1005,"EE","E234235232@qq.com",1,new Department(105,"后勤部")));

    }
    //主键自增
    private static Integer initId = 1006;
    //增加一个员工
    public void save(Employee employee){
        //主键自增
        if(employee.getId()==null){
            employee.setId(initId++);
        }
        //部门关联外键
        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));
        //其他默认
        employees.put(employee.getId(),employee);
    }
    //查询全部员工
    public Collection<Employee> getAll(){
        return employees.values();
    }

    public Employee getEmployeeById(Integer id){
        return employees.get(id);
    }
    //
    public void delete(Integer id){
        employees.remove(id);
    }
}
