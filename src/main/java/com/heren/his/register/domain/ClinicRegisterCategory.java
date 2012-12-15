package com.heren.his.register.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ClinicRegisterCategory {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    public ClinicRegisterCategory(String name) {
        this.name = name;
    }
}
