package com.action;

import com.dao.FinancialEntityDAO;
import com.dao.UserWeightDAO;
import com.model.FinancialEntity;
import com.model.OrdersEntity;
import com.model.TUser;
import com.model.UserWeightEntity;
import com.opensymphony.xwork2.ActionSupport;
import com.util.Util;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName FinancialEntityAction
 * @Author lijian
 * @Date 2019/6/25
 * @Time 4:23 PM
 * @Version 1.0
 */
public class FinancialEntityAction extends ActionSupport {
    private FinancialEntity financialEntity;

    private FinancialEntityDAO financialEntityDAO;

    private UserWeightDAO userWeightDAO;

    public void setUserWeightDAO(UserWeightDAO userWeightDAO) {
        this.userWeightDAO = userWeightDAO;
    }

    public void setFinancialEntityDAO(FinancialEntityDAO financialEntityDAO) {
        this.financialEntityDAO = financialEntityDAO;
    }

    public FinancialEntity getFinancialEntity() {
        return financialEntity;
    }

    public void setFinancialEntity(FinancialEntity financialEntity) {
        this.financialEntity = financialEntity;
    }

    public String saveOrUpdate(){
        Map map = (Map) ServletActionContext.getContext().get("request");
        try {
            long tempTime = new java.util.Date().getTime();
            if(financialEntity.getId() == 0){
                financialEntity.setCreateTime(new Date(tempTime));
                financialEntity.setUpdateTime(new Date(tempTime));
            }else {
                financialEntity.setUpdateTime(new Date(tempTime));
            }
            financialEntityDAO.saveOrUpdate(financialEntity);
            if(financialEntity.getId()!=0){
                map.put("msg","修改成功");
            }else {
                map.put("msg","新增成功");
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","操作失败");
            return "erro";
        }
        return ActionSupport.SUCCESS;
    }

    public String list(){
        Map map = (Map) ServletActionContext.getContext().get("request");
        try {
            List list = financialEntityDAO.findAll();
            map.put("list",list);
            map.put("msg","操作成功");
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","查询失败");
            return ActionSupport.ERROR;
        }
        return ActionSupport.SUCCESS;
    }

    public String getById(){
        Map map = (Map) ServletActionContext.getContext().get("request");
        try {
            FinancialEntity financialEntity1 = financialEntityDAO.findById(financialEntity.getId());
            map.put("financialEntity",financialEntity1);
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","操作失败");
            return ActionSupport.ERROR;
        }
        return ActionSupport.SUCCESS;
    }

    public String delete(){
        Map map = (Map) ServletActionContext.getContext().get("request");
        try {
            financialEntityDAO.delete(financialEntity);
            map.put("msg","删除成功");
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","操作失败");
            return ActionSupport.ERROR;
        }
        return ActionSupport.SUCCESS;
    }

    public String recommend(){
        HttpServletRequest req=ServletActionContext.getRequest();
        HttpSession sess=req.getSession();
        TUser user=(TUser)sess.getAttribute("user");

        Map map = (Map) ServletActionContext.getContext().get("request");
        List userWeightList = userWeightDAO.findByUserId(user.getUserId().longValue());
        //因为只有一条
        UserWeightEntity userWeightEntity;
        if(userWeightList.size()>0){
            userWeightEntity = (UserWeightEntity) userWeightList.get(0);
        }else {
            userWeightEntity = new UserWeightEntity();
            userWeightEntity.setAweight(100);
            userWeightEntity.setBweight(100);
            userWeightEntity.setCweight(100);
            userWeightEntity.setDweight(100);
            userWeightEntity.setUserId(user.getUserId().longValue());
            userWeightDAO.saveOrUpdate(userWeightEntity);
        }
        int sum = userWeightEntity.getAweight()+userWeightEntity.getBweight()+userWeightEntity.getCweight()+userWeightEntity.getDweight();
        int a = userWeightEntity.getAweight()*20/ sum;
        int b = userWeightEntity.getBweight()*20/ sum;
        int c = userWeightEntity.getCweight()*20/ sum;
        int d = userWeightEntity.getDweight()*20/ sum;

        List lista = a == 0?new ArrayList():financialEntityDAO.findByWeight(a,"A");
        List listb = b == 0?new ArrayList():financialEntityDAO.findByWeight(b,"B");
        List listc = c == 0?new ArrayList():financialEntityDAO.findByWeight(c,"C");
        List listd = d == 0?new ArrayList():financialEntityDAO.findByWeight(d,"D");

        List result = new ArrayList();
        result.addAll(lista);
        result.addAll(listb);
        result.addAll(listc);
        result.addAll(listd);
        map.put("list",result);
        return ActionSupport.SUCCESS;
    }

    public String getOrder(){
        Map map = (Map) ServletActionContext.getContext().get("request");
        OrdersEntity ordersEntity = new OrdersEntity();
        FinancialEntity financialEntity1 = financialEntityDAO.findById(financialEntity.getId());
        ordersEntity.setProduct(financialEntity1);
        ordersEntity.setOrderNum(Util.getOrderIdByTime());
        map.put("order",ordersEntity);
        return ActionSupport.SUCCESS;
}

    public String deleteWeight(){
        HttpServletRequest req=ServletActionContext.getRequest();
        HttpSession sess=req.getSession();
        TUser user=(TUser)sess.getAttribute("user");
        UserWeightEntity userWeightEntity = (UserWeightEntity)userWeightDAO.findByUserId(user.getUserId()).get(0);
        userWeightDAO.delete(userWeightEntity);
        return ActionSupport.SUCCESS;
    }
}
