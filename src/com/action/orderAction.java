package com.action;

import com.alipay.api.AlipayApiException;
import com.dao.FinancialEntityDAO;
import com.dao.OrdersEntityDAO;
import com.model.FinancialEntity;
import com.model.OrdersEntity;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

/**
 * @ClassName orderAction
 * @Author lijian
 * @Date 2019/6/30
 * @Time 1:29 PM
 * @Version 1.0
 */
public class orderAction {
    private OrdersEntity ordersEntity;

    private OrdersEntityDAO ordersEntityDAO;
    private FinancialEntityDAO financialEntityDAO;

    public FinancialEntityDAO getFinancialEntityDAO() {
        return financialEntityDAO;
    }

    public void setFinancialEntityDAO(FinancialEntityDAO financialEntityDAO) {
        this.financialEntityDAO = financialEntityDAO;
    }

    public OrdersEntityDAO getOrdersEntityDAO() {
        return ordersEntityDAO;
    }

    public void setOrdersEntityDAO(OrdersEntityDAO ordersEntityDAO) {
        this.ordersEntityDAO = ordersEntityDAO;
    }

    public OrdersEntity getOrdersEntity() {
        return ordersEntity;
    }

    public void setOrdersEntity(OrdersEntity ordersEntity) {
        this.ordersEntity = ordersEntity;
    }

    public String saveOrUpdate() throws UnsupportedEncodingException, AlipayApiException {
        FinancialEntity financialEntity1 = financialEntityDAO.findById(ordersEntity.getProduct().getId());
        ordersEntity.setProductId(String.valueOf(ordersEntity.getProduct().getId()));
        ordersEntity.setOrderStatus("10");
        long tempTime = new java.util.Date().getTime();
        ordersEntity.setCreateTime(new Date(tempTime));
        ordersEntityDAO.saveOrUpdate(ordersEntity);
        Map map = (Map) ServletActionContext.getContext().get("request");
        map.put("WIDout_trade_no",ordersEntity.getOrderNum());
        map.put("WIDtotal_amount",new BigDecimal(ordersEntity.getOrderAmount()).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP).toString());
        map.put("WIDsubject",financialEntity1.getFinancialName());
        map.put("WIDbody",financialEntity1.getFinancialDescribe());

        return ActionSupport.SUCCESS;
    }

    public String delete(){
        ordersEntityDAO.delete(ordersEntity);
        return ActionSupport.SUCCESS;
    }





}
