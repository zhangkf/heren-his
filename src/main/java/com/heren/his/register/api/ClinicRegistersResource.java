package com.heren.his.register.api;

import com.heren.his.dao.ClinicRegisterDAO;
import com.heren.his.register.domain.*;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;

@Singleton
@Path("/clinic_register")
@Produces(MediaType.APPLICATION_JSON)
public class ClinicRegistersResource {

    private EntityManagerFactory entityManagerFactory;

    private final ClinicRegisterDAO<Department> departmentDAO;
    private final ClinicRegisterDAO<ClinicRegisterType> clinicRegisterTypeDAO;
    private final ClinicRegisterDAO<ClinicRegister> clinicRegisterDAO;
    private final ClinicRegisterDAO<BigDepartment> bigDepartmentDAO;

    @Inject
    public ClinicRegistersResource(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
        departmentDAO = new ClinicRegisterDAO<>(entityManagerFactory.createEntityManager());
        clinicRegisterTypeDAO = new ClinicRegisterDAO<>(entityManagerFactory.createEntityManager());
        clinicRegisterDAO = new ClinicRegisterDAO<>(entityManagerFactory.createEntityManager());
        bigDepartmentDAO = new ClinicRegisterDAO<>(entityManagerFactory.createEntityManager());
    }

    @GET
    @Path("bigDepartments")
    public List<BigDepartment> bigDepartments(){
        prepareData();
        TypedQuery<BigDepartment> query = entityManagerFactory.createEntityManager().createQuery("select bd from BigDepartment bd", BigDepartment.class);
        return query.getResultList();
    }

    @GET
    @Path("bigDepartment/{bigDepartmentId}/departments")
    public List<Department> allByBigDepartment(@PathParam("bigDepartmentId") long bigDepartmentId){
        TypedQuery<Department> query = entityManagerFactory.createEntityManager().createQuery("select d from Department d where d.bigDepartment.id = :bigDepartmentId", Department.class);
        query.setParameter("bigDepartmentId", bigDepartmentId);
        return query.getResultList();
    }


    @GET
    @Path("department/{departmentId}/all")
    public List<ClinicRegister> allByDepartment(@PathParam("departmentId") long departmentId){
        TypedQuery<ClinicRegister> query = entityManagerFactory.createEntityManager().createQuery("select r from ClinicRegister r where r.clinicRegisterType.department.id = :departmentId", ClinicRegister.class);
        query.setParameter("departmentId", departmentId);
        return query.getResultList();
    }


    @GET
    @Path("all")
    public List<ClinicRegister> all() {
        TypedQuery<ClinicRegister> query = entityManagerFactory.createEntityManager().createQuery("SELECT r from ClinicRegister r", ClinicRegister.class);
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
        TypedQuery<ClinicRegister> query = entityManagerFactory.createEntityManager().createQuery("SELECT r from ClinicRegister r WHERE r.periodOfValidity.validOn = :date", ClinicRegister.class);
        query.setParameter("date", date);
        return query.getResultList();
    }

    private long prepareData(){

        BigDepartment bigDepartment = new BigDepartment("大部门");
        bigDepartmentDAO.persist(bigDepartment);

        Department department = new Department("部门", bigDepartment);
        departmentDAO.persist(department);

        ClinicRegisterType clinicRegisterType1= new ClinicRegisterType("clinic_register_type1", ClinicRegisterType.Type.EXPERT, department, "digitCode1", "pinyinCode1");
        clinicRegisterTypeDAO.persist(clinicRegisterType1);

        ClinicRegisterType clinicRegisterType2 = new ClinicRegisterType("clinic_register_type2", ClinicRegisterType.Type.CONSULTING_ROOM, department, "digitCode2", "pinyinCode2");
        clinicRegisterTypeDAO.persist(clinicRegisterType2);

        ClinicRegister clinicRegister1 = new ClinicRegister(clinicRegisterType1, new PeriodOfValidity(new Date(), PeriodOfValidity.Period.MORNING), 20, 20, false);
        clinicRegisterDAO.persist(clinicRegister1);
        ClinicRegister clinicRegister2 = new ClinicRegister(clinicRegisterType1, new PeriodOfValidity(new Date(), PeriodOfValidity.Period.AFTERNOON), 10, 10, false);
        clinicRegisterDAO.persist(clinicRegister2);

        ClinicRegister clinicRegister3 = new ClinicRegister(clinicRegisterType2, new PeriodOfValidity(new Date(), PeriodOfValidity.Period.MORNING), 20, 20, false);
        clinicRegisterDAO.persist(clinicRegister3);
        ClinicRegister clinicRegister4 = new ClinicRegister(clinicRegisterType2, new PeriodOfValidity(new Date(), PeriodOfValidity.Period.AFTERNOON), 10, 10, false);
        clinicRegisterDAO.persist(clinicRegister4);

        return bigDepartment.getId();

    }
}
