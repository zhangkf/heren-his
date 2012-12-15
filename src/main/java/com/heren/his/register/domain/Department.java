package com.heren.his.register.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Department {
    @Id
    @GeneratedValue
    private long id;

    private String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<ClinicRegisterType> clinicRegisterTypes;

    @ManyToOne
    private BigDepartment bigDepartment;

    private Department() {
    }

    public Department(String name) {
        this.name = name;
        this.clinicRegisterTypes = new ArrayList<>();
    }

    public void addClinicRegisterType(ClinicRegisterType clinicRegisterType) {
        clinicRegisterTypes.add(clinicRegisterType);
    }
}
