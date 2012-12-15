package com.heren.his.register.domain;

import javax.persistence.*;

@Entity
public class ClinicRegisterType {
    public static enum Type {
        EXPERT, CONSULTING_ROOM
    }

    @Id
    @GeneratedValue
    private long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Type type;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private Department department;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private ClinicRegisterCategory clinicRegisterCategory;

    private String digitCode;

    private String pingyinCode;

    private ClinicRegisterType() {
    }

    public ClinicRegisterType(String name, Type type, Department department) {
        this.name = name;
        this.type = type;
        this.department = department;
        department.addClinicRegisterType(this);
    }

    public ClinicRegisterType(String name, Type type, Department department, String digitCode, String pingyinCode) {
        this.name = name;
        this.type = type;
        this.department = department;
        this.digitCode = digitCode;
        this.pingyinCode = pingyinCode;
    }
}
