package com.heren.his.register.domain;

import javax.persistence.*;

@Entity
public class Service {
    public static enum Type {
        EXPERT, CONSULTING_ROOM
    }

    @Id
    @GeneratedValue
    private long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Type type;

    @ManyToOne(cascade = CascadeType.REFRESH, optional = false)
    private Department department;

    private Service() {
    }

    public Service(String name, Type type, Department department) {
        this.name = name;
        this.type = type;
        this.department = department;
        department.addService(this);
    }
}
