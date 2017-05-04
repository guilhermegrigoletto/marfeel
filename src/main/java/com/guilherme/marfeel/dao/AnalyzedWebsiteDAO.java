package com.guilherme.marfeel.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.guilherme.marfeel.model.AnalyzedWebsite;

@Repository
@Transactional
public class AnalyzedWebsiteDAO {
	
    @PersistenceContext
    private EntityManager manager;
	    
    public void save(AnalyzedWebsite entity) {
        manager.persist(entity);
    }
}
