package com.heren.his.register.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class BigDepartment {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    @OneToMany(mappedBy = "bigDepartment")
    private Set<Department> departments;

    public BigDepartment(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Department> getDepartments() {
        return departments;
    }

    public void addDepartment(Department department) {
        if(this.departments == null){
            this.departments = new HashSet<Department>();
        }
        this.departments.add(department);
    }

    public BigDepartment() {
    }
}
