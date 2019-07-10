package com.dao;

import com.model.SubjectEntity;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * ClassName SubjectDAO
 * Author lijian
 * Date 2019/7/10
 * Time 2:16 PM
 * Version 1.0
 */
public class SubjectDAO extends HibernateDaoSupport {
    public void saveOrUpdate(SubjectEntity subjectEntity){
        try {
            getHibernateTemplate().saveOrUpdate(subjectEntity);
        } catch (RuntimeException re) {
            throw re;
        }
    }

    public SubjectEntity findById(Long id){
        SubjectEntity subjectEntity =(SubjectEntity) getHibernateTemplate().get("com.model.SubjectEntity",id);
        return subjectEntity;
    }

    public List findAll(){
        return getHibernateTemplate().find("from SubjectEntity ");
    }

    public void delete(SubjectEntity subjectEntity){
        getHibernateTemplate().delete(subjectEntity);
    }

}
