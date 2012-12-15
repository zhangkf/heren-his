package com.heren.his.dao;

import com.heren.his.register.domain.ClinicRegisterType;
import com.heren.his.register.domain.Department;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ClinicRegisterTypeTests extends DomainTests{

    private final ClinicRegisterDAO<Department> departmentDAO;
    private final ClinicRegisterDAO<ClinicRegisterType> clinicRegisterTypeDAO;

    public ClinicRegisterTypeTests() {
        departmentDAO = new ClinicRegisterDAO<>(entityManager);
        clinicRegisterTypeDAO = new ClinicRegisterDAO<>(entityManager);
    }

    @Test
    public void should_persist_clinic_register_type(){
        Department department = new Department("department");
        departmentDAO.persist(department);

        ClinicRegisterType clinicRegisterType = new ClinicRegisterType("clinic_register_type", ClinicRegisterType.Type.EXPERT, department, "digitCode", "pinyinCode");
        clinicRegisterTypeDAO.persist(clinicRegisterType);

        clearCache();

        ClinicRegisterType savedClinicRegisterType = clinicRegisterTypeDAO.load(ClinicRegisterType.class, clinicRegisterType.getId());
        assertThat(savedClinicRegisterType.getName(), is("clinic_register_type"));

        assertThat(savedClinicRegisterType.getDepartment().getName(), is("department"));

    }

    @Test
    public void should_get_clinic_register_types_by_department(){
        Department department = new Department("department");
        departmentDAO.persist(department);

        ClinicRegisterType clinicRegisterType1 = new ClinicRegisterType("clinic_register_type1", ClinicRegisterType.Type.EXPERT, department, "digitCode", "pinyinCode");
        clinicRegisterTypeDAO.persist(clinicRegisterType1);
        ClinicRegisterType clinicRegisterType2 = new ClinicRegisterType("clinic_register_type2", ClinicRegisterType.Type.CONSULTING_ROOM, department, "digitCode", "pinyinCode");
        clinicRegisterTypeDAO.persist(clinicRegisterType2);

        clearCache();

        Department savedDepartment = departmentDAO.load(Department.class, department.getId());
        assertThat(savedDepartment.getClinicRegisterTypes().size(), is(2));
        assertThat(savedDepartment.getClinicRegisterTypes().contains(clinicRegisterType1), is(true));
        assertThat(savedDepartment.getClinicRegisterTypes().contains(clinicRegisterType2), is(true));

    }
}
