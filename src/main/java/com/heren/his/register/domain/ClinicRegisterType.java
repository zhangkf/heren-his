package com.heren.his.register.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Set;

@Entity
public class ClinicRegisterType {

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Department getDepartment() {
        return department;
    }

    public Set<ClinicRegister> getClinicRegisters() {
        return clinicRegisters;
    }

    public static enum Type {
        EXPERT, CONSULTING_ROOM
    }

    @Id
    @GeneratedValue
    private long id;

    @JsonProperty
    private String name;

    @Enumerated(EnumType.STRING)
    private Type type;

    @JsonProperty
    @ManyToOne(cascade = CascadeType.REFRESH)
    private Department department;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private ClinicRegisterCategory clinicRegisterCategory;

    @OneToMany(mappedBy = "clinicRegisterType")
    @JsonIgnore
    private Set<ClinicRegister> clinicRegisters;

    @JsonProperty
    private String digitCode;

    @JsonProperty
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClinicRegisterType that = (ClinicRegisterType) o;

        if (id != that.id) return false;
        if (!digitCode.equals(that.digitCode)) return false;
        if (!name.equals(that.name)) return false;
        if (!pingyinCode.equals(that.pingyinCode)) return false;
        if (type != that.type) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + digitCode.hashCode();
        result = 31 * result + pingyinCode.hashCode();
        return result;
    }
}
