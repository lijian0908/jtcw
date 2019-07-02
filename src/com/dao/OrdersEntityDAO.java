package com.dao;

import com.model.OrdersEntity;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * @ClassName OrdersEntityDAO
 * @Author lijian
 * @Date 2019/6/30
 * @Time 1:25 PM
 * @Version 1.0
 */
public class OrdersEntityDAO extends HibernateDaoSupport {
    public void saveOrUpdate(OrdersEntity ordersEntity){
        try {
            getHibernateTemplate().saveOrUpdate(ordersEntity);
        } catch (RuntimeException re) {
            throw re;
        }
    }

    public OrdersEntity findById(Long id){
        OrdersEntity flowEntity =(OrdersEntity) getHibernateTemplate().get("com.model.OrdersEntity",id);
        return flowEntity;
    }

    public List findAll(){
        return getHibernateTemplate().find("from OrdersEntity ");
    }

    public void delete(OrdersEntity ordersEntity){
        getHibernateTemplate().delete(ordersEntity);
    }
}
