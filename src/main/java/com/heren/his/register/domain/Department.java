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
    private List<Service> services;

    private Department() {
    }

    public Department(String name) {
        this.name = name;
        this.services = new ArrayList<>();
    }

    public void addService(Service service) {
        services.add(service);
    }
}
