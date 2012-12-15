package com.heren.his.dao;

import com.heren.his.register.domain.BigDepartment;
import com.heren.his.register.domain.Department;
import org.junit.Test;

import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DepartmentTests extends DomainTests{
    private ClinicRegisterDAO<Department> departmentDAO;

    public DepartmentTests() {
        departmentDAO = new ClinicRegisterDAO<>(entityManager);
    }

    @Test
    public void should_persist_and_load_department(){
        Department department = new Department("department");
        departmentDAO.persist(department);

        clearCache();

        Department savedDepartment = departmentDAO.load(Department.class, department.getId());
        assertThat(savedDepartment.getName(), is("department"));
    }

    @Test
    public void should_persit_big_department_and_department(){

        BigDepartment bigDepartment = new BigDepartment("big_department");

        ClinicRegisterDAO<BigDepartment> bigDepartmentDAO = new ClinicRegisterDAO<>(entityManager);
        bigDepartmentDAO.persist(bigDepartment);

        Department department = new Department("department", bigDepartment);
        departmentDAO.persist(department);

        clearCache();

        Department savedDepartment = departmentDAO.load(Department.class, department.getId());
        assertThat(savedDepartment.getName(), is("department"));
        assertThat(savedDepartment.getBigDepartment().getName(), is("big_department"));

        BigDepartment savdBigDepartment = bigDepartmentDAO.load(BigDepartment.class, bigDepartment.getId());
        Set<Department> departments = savdBigDepartment.getDepartments();
        assertThat(departments.size(), is(1));
        assertThat(departments.iterator().next().getName(), is("department"));


    }
}
