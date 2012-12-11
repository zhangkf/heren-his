package com.heren.his.register.api;

import com.heren.his.register.domain.ClinicRegister;
import com.heren.his.register.domain.PeriodOfValidity;

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
        entityManager.persist(new ClinicRegister(5, new PeriodOfValidity(new Date(), PeriodOfValidity.Period.MORNING)));
        entityManager.persist(new ClinicRegister(10, new PeriodOfValidity(new Date(), PeriodOfValidity.Period.MORNING)));
        entityManager.persist(new ClinicRegister(15, new PeriodOfValidity(new Date(), PeriodOfValidity.Period.MORNING)));
        tx.commit();
    }

    @GET
    @Path("all")
    public List<ClinicRegister> all() {
        TypedQuery<ClinicRegister> query = entityManager.createQuery("SELECT r from ClinicRegister r", ClinicRegister.class);
        return query.getResultList();
    }

    @GET
    @Path("today")
    public List<ClinicRegister> today() {
        return available(new Date());
    }

    @GET
    @Path("{date}")
    public List<ClinicRegister> available(@PathParam("date") Date date) {
        TypedQuery<ClinicRegister> query = entityManager.createQuery("SELECT r from ClinicRegister r WHERE r.periodOfValidity.validOn = :date", ClinicRegister.class);
        query.setParameter("date", date);
        return query.getResultList();
    }
}
