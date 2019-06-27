package com.dao;

import com.model.FinancialRaltionEntity;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * @ClassName FinancialEntityRelationDAO
 * @Author lijian
 * @Date 2019/6/25
 * @Time 5:11 PM
 * @Version 1.0
 */
public class FinancialEntityRelationDAO extends HibernateDaoSupport {

    public void saveOrUpdate(FinancialRaltionEntity financialRaltionEntity){
        try {
            getHibernateTemplate().saveOrUpdate(financialRaltionEntity);
        } catch (RuntimeException re) {
            throw re;
        }
    }

    public FinancialRaltionEntity findByUserId(Long id){
        FinancialRaltionEntity financialRaltionEntity =(FinancialRaltionEntity) getHibernateTemplate()
                .find("from FinancialRaltionEntity where userId = "+id);
        return financialRaltionEntity;
    }


    public void delete(FinancialRaltionEntity financialRaltionEntity){
        getHibernateTemplate().delete(financialRaltionEntity);
    }
}
