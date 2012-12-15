package com.heren.his.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class ClinicRegisterDAO<T> {

    private EntityManager entityManager;

    public ClinicRegisterDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public T load(Class<T> clas, long id) {
        return this.entityManager.find(clas, id);
    }

    public void persist(T t) {
        EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.begin();
        this.entityManager.persist(t);
        transaction.commit();
    }
}
