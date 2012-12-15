package com.heren.his.dao;

import com.heren.his.register.domain.BigDepartment;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BigDepartmentTests extends DomainTests {

    ClinicRegisterDAO<BigDepartment> bigDepartmentDAO;

    public BigDepartmentTests() {
        super();
        bigDepartmentDAO = new ClinicRegisterDAO<>(entityManager);
    }

    @Test
    public void should_persist_and_load_big_department(){
        BigDepartment bigDepartment = new BigDepartment("big_department");
        bigDepartmentDAO.persist(bigDepartment);

        clearCache();

        BigDepartment savedBigDepartment = bigDepartmentDAO.load(BigDepartment.class, bigDepartment.getId());
        assertThat(savedBigDepartment.getName(), is("big_department"));
    }
}
