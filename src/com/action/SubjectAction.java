package com.action;

import com.dao.SubjectDAO;
import com.dao.UserWeightDAO;
import com.model.TUser;
import com.model.UserWeightEntity;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @ClassName SubjectAction
 * @Author lijian
 * @Date 2019/7/10
 * @Time 2:18 PM
 * @Version 1.0
 */
public class SubjectAction extends ActionSupport {

    private SubjectDAO subjectDAO;
    private UserWeightDAO userWeightDAO;
    private int A;
    private int B;
    private int C;
    private int D;

    public void setA(int a) {
        A = a;
    }

    public void setB(int b) {
        B = b;
    }

    public void setC(int c) {
        C = c;
    }

    public void setD(int d) {
        D = d;
    }

    public void setUserWeightDAO(UserWeightDAO userWeightDAO) {
        this.userWeightDAO = userWeightDAO;
    }
    public void setSubjectDAO(SubjectDAO subjectDAO) {
        this.subjectDAO = subjectDAO;
    }

    public String getSubject(){
        HttpServletRequest req=ServletActionContext.getRequest();
        HttpSession sess=req.getSession();
        TUser user=(TUser)sess.getAttribute("user");

        Map map = (Map) ServletActionContext.getContext().get("request");
        List userWeightList = userWeightDAO.findByUserId(user.getUserId().longValue());
        if(userWeightList.size() == 0){
            map.put("list",subjectDAO.findAll());
        }else {
            return ActionSupport.INPUT;
        }
        return ActionSupport.SUCCESS;
    }

    public String setSub(){
        HttpServletRequest req=ServletActionContext.getRequest();
        HttpSession sess=req.getSession();
        TUser user=(TUser)sess.getAttribute("user");
        UserWeightEntity userWeightEntity;
        userWeightEntity = new UserWeightEntity();
        userWeightEntity.setAweight(A);
        userWeightEntity.setBweight(B);
        userWeightEntity.setCweight(C);
        userWeightEntity.setDweight(D);
        userWeightEntity.setUserId(user.getUserId().longValue());
        userWeightDAO.saveOrUpdate(userWeightEntity);
        return ActionSupport.SUCCESS;
    }

}
