package com.heren.his.dao;

import com.heren.his.register.domain.ClinicRegisterCategory;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ClinicRegisterCategoryTests extends DomainTests{

    private final ClinicRegisterDAO<ClinicRegisterCategory> clinicRegisterCategoryDAO;

    public ClinicRegisterCategoryTests() {
        clinicRegisterCategoryDAO = new ClinicRegisterDAO<>(entityManager);
    }

    @Test
    public void should_persist_clinic_register_category(){
        ClinicRegisterCategory clinicRegisterCategory = new ClinicRegisterCategory("clinic_register_category");
        clinicRegisterCategoryDAO.persist(clinicRegisterCategory);

        ClinicRegisterCategory savedClinicRegisterCategory = clinicRegisterCategoryDAO.load(ClinicRegisterCategory.class, clinicRegisterCategory.getId());
        assertThat(savedClinicRegisterCategory.getName(), is("clinic_register_category"));

    }
}
