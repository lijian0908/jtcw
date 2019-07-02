package com.dao;

import com.model.UserWeightEntity;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * @ClassName UserWeightDAO
 * @Author lijian
 * @Date 2019/7/2
 * @Time 2:11 PM
 * @Version 1.0
 */
public class UserWeightDAO extends HibernateDaoSupport {
    public void saveOrUpdate(UserWeightEntity userWeightEntity){
        try {
            getHibernateTemplate().saveOrUpdate(userWeightEntity);
        } catch (RuntimeException re) {
            throw re;
        }
    }

    public UserWeightEntity findById(Long id){
        UserWeightEntity userWeightEntity =(UserWeightEntity) getHibernateTemplate().get("com.model.UserWeightEntity",id);
        return userWeightEntity;
    }

    public List findAll(){
        return getHibernateTemplate().find("from UserWeightEntity ");
    }

    public void delete(UserWeightEntity userWeightEntity){
        getHibernateTemplate().delete(userWeightEntity);
    }

    public List findByUserId(long userId){
        return getHibernateTemplate().find("from UserWeightEntity where userId = "+userId);
    }
}
