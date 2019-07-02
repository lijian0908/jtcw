package com.dao;

import com.model.FlowEntity;
import com.sun.tools.javac.comp.Flow;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * @ClassName FlowEntityDAO
 * @Author lijian
 * @Date 2019/6/30
 * @Time 1:25 PM
 * @Version 1.0
 */
public class FlowEntityDAO extends HibernateDaoSupport {
    public void saveOrUpdate(FlowEntity flowEntity){
        try {
            getHibernateTemplate().saveOrUpdate(flowEntity);
        } catch (RuntimeException re) {
            throw re;
        }
    }

    public FlowEntity findById(Long id){
        FlowEntity flowEntity =(FlowEntity) getHibernateTemplate().get("com.model.FlowEntity",id);
        return flowEntity;
    }

    public List findAll(){
        return getHibernateTemplate().find("from FlowEntity ");
    }

    public void delete(FlowEntity flowEntity){
        getHibernateTemplate().delete(flowEntity);
    }
}
