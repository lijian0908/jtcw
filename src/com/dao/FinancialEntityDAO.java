package com.dao;

import com.model.FinancialEntity;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * @ClassName FinancialEntityDAO
 * @Author lijian
 * @Date 2019/6/25
 * @Time 4:14 PM
 * @Version 1.0
 */
public class FinancialEntityDAO extends HibernateDaoSupport {

    public void saveOrUpdate(FinancialEntity financialEntity){
        try {
            getHibernateTemplate().saveOrUpdate(financialEntity);
        } catch (RuntimeException re) {
            throw re;
        }
    }

    public FinancialEntity findById(Long id){
        FinancialEntity financialEntity =(FinancialEntity) getHibernateTemplate().get("com.model.FinancialEntity",id);
        return financialEntity;
    }

    public List findAll(){
        return getHibernateTemplate().find("from FinancialEntity");
    }

    public void delete(FinancialEntity financialEntity){
        getHibernateTemplate().delete(financialEntity);
    }
}
