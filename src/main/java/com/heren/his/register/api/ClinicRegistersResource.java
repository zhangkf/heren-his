package com.heren.his.register.api;

import com.heren.his.register.domain1.ClinicRegister1;
import com.heren.his.register.domain1.PeriodOfValidity;
import com.heren.his.register.domain1.Department;
import com.heren.his.register.domain1.Service;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;

import static com.heren.his.register.domain1.Service.Type.CONSULTING_ROOM;

@Singleton
@Path("/clinic_registers")
@Produces(MediaType.APPLICATION_JSON)
public class ClinicRegistersResource {
    private EntityManager entityManager;

    @Inject
    public ClinicRegistersResource(EntityManager entityManager) {
        this.entityManager = entityManager;
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        Department department = new Department("呼吸科");
        entityManager.persist(department);
        Service service = new Service("普通门诊诊室1", CONSULTING_ROOM, department);
        entityManager.persist(service);

        PeriodOfValidity periodOfValidity = new PeriodOfValidity(new Date(), PeriodOfValidity.Period.MORNING);
        entityManager.persist(new ClinicRegister1(service, periodOfValidity, 5));
        entityManager.persist(new ClinicRegister1(service, periodOfValidity,10));
        entityManager.persist(new ClinicRegister1(service, periodOfValidity,15));
        tx.commit();
    }

    @GET
    @Path("all")
    public List<ClinicRegister1> all() {
        TypedQuery<ClinicRegister1> query = entityManager.createQuery("SELECT r from ClinicRegister1 r", ClinicRegister1.class);
        return query.getResultList();
    }

    @GET
    @Path("today")
    public List<ClinicRegister1> today() {
        return available(new Date());
    }

    @GET
    @Path("{date}")
    public List<ClinicRegister1> available(@PathParam("date") Date date) {
        TypedQuery<ClinicRegister1> query = entityManager.createQuery("SELECT r from ClinicRegister1 r WHERE r.periodOfValidity.validOn = :date", ClinicRegister1.class);
        query.setParameter("date", date);
        return query.getResultList();
    }
}
