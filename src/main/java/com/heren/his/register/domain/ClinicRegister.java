package com.heren.his.register.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

import java.util.Date;

import static com.heren.his.register.domain.PeriodOfValidity.Period.MORNING;
import static com.heren.his.register.domain.Service.Type.CONSULTING_ROOM;
import static com.heren.his.register.domain.Service.Type.EXPERT;

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

    @ManyToOne
    private Service service;

    private ClinicRegister() {
    }

    public ClinicRegister(Service service, PeriodOfValidity periodOfValidity, int scheduled) {
        this.scheduled = scheduled;
        this.periodOfValidity = periodOfValidity;
        this.service = service;
    }

    public static void main(String... arguments) {
        Department 呼吸科 = new Department("呼吸科");
        Service 普通门诊诊室1 = new Service("普通门诊诊室1", CONSULTING_ROOM, 呼吸科);
        Service 普通门诊诊室2 = new Service("普通门诊诊室2", CONSULTING_ROOM, 呼吸科);
        Service 张大夫 = new Service("张大夫", EXPERT, 呼吸科);
        Service 李大夫 = new Service("李大夫", EXPERT, 呼吸科);

        ClinicRegister 普通门诊诊室1号池 = new ClinicRegister(普通门诊诊室1, new PeriodOfValidity(new Date(), MORNING), 40);
        ClinicRegister 普通门诊诊室2号池 = new ClinicRegister(普通门诊诊室2, new PeriodOfValidity(new Date(), MORNING), 35);
        ClinicRegister 张大夫号池 = new ClinicRegister(张大夫, new PeriodOfValidity(new Date(), MORNING), 15);
        ClinicRegister 李大夫号池 = new ClinicRegister(李大夫, new PeriodOfValidity(new Date(), MORNING), 25);
    }
}
