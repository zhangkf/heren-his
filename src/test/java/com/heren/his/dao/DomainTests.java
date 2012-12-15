package com.heren.his.dao;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class DomainTests {

    protected static EntityManager entityManager;

    @BeforeClass
    public static void before(){
        entityManager = Persistence.createEntityManagerFactory("domain").createEntityManager();
    }

    @AfterClass
    public static void after(){
        entityManager.close();
    }


    protected void clearCache() {
        this.entityManager.clear();
    }
}
