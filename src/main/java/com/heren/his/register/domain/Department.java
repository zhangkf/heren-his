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

    @ManyToOne(optional = true)
    private BigDepartment bigDepartment;

    private Department() {
    }

    public Department(String name) {
        this.name = name;
        this.clinicRegisterTypes = new ArrayList<>();
    }

    public Department(String name, BigDepartment bigDepartment) {
        this.name = name;
        this.bigDepartment = bigDepartment;
    }

    public void addClinicRegisterType(ClinicRegisterType clinicRegisterType) {
        clinicRegisterTypes.add(clinicRegisterType);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDepartment getBigDepartment() {
        return bigDepartment;
    }

    public List<ClinicRegisterType> getClinicRegisterTypes() {
        return clinicRegisterTypes;
    }
}
