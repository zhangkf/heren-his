package com.heren.his.register.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class BigDepartment {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    @OneToMany(cascade=CascadeType.REFRESH)
    private List<Department> department;

    public BigDepartment(String name) {
        this.name = name;
    }
}
