package com.action;

import com.dao.FinancialEntityDAO;
import com.model.FinancialEntity;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import java.sql.Date;
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
}
