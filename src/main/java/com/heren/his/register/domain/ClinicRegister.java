package com.heren.his.register.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
public class ClinicRegister {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonProperty
    private Long id;

    @JsonProperty
    private int scheduled;

    @Embedded
    @JsonProperty("period_of_validity")
    private PeriodOfValidity periodOfValidity;

    public ClinicRegister() {
    }

    @JsonCreator
    public ClinicRegister(@JsonProperty("scheduled") int scheduled,
                          @JsonProperty("period_of_validity") PeriodOfValidity periodOfValidity) {
        this.scheduled = scheduled;
        this.periodOfValidity = periodOfValidity;
    }
}
