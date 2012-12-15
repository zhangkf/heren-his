package com.heren.his.dao;

import com.heren.his.register.domain.ClinicRegister;
import com.heren.his.register.domain.ClinicRegisterType;
import com.heren.his.register.domain.Department;
import com.heren.his.register.domain.PeriodOfValidity;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ClinicRegisterTests extends DomainTests{

    private final ClinicRegisterDAO<Department> departmentDAO;
    private final ClinicRegisterDAO<ClinicRegisterType> clinicRegisterTypeDAO;
    private final ClinicRegisterDAO<ClinicRegister> clinicRegisterDAO;

    public ClinicRegisterTests() {
        departmentDAO = new ClinicRegisterDAO<>(entityManager);
        clinicRegisterTypeDAO = new ClinicRegisterDAO<>(entityManager);
        clinicRegisterDAO = new ClinicRegisterDAO<>(entityManager);
    }

    @Test
    public void should_persist_clinic_register(){
        Department department = new Department("department");
        departmentDAO.persist(department);

        ClinicRegisterType clinicRegisterType = new ClinicRegisterType("clinic_register_type", ClinicRegisterType.Type.EXPERT, department, "digitCode", "pinyinCode");
        clinicRegisterTypeDAO.persist(clinicRegisterType);

        ClinicRegister clinicRegister = new ClinicRegister(20, 20, false, new PeriodOfValidity(new Date(), PeriodOfValidity.Period.MORNING), clinicRegisterType);
        clinicRegisterDAO.persist(clinicRegister);

        clearCache();

        ClinicRegister savedClinicRegister = clinicRegisterDAO.load(ClinicRegister.class, clinicRegister.getId());
        assertThat(savedClinicRegister.getClinicRegisterType(), is(clinicRegisterType));
    }
}
