package com.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.dao.TShouruDAO;
import com.dao.TXiaofeiDAO;
import com.model.TUser;
import com.opensymphony.xwork2.ActionSupport;

public class tongjiAction extends ActionSupport
{
		
    private String message;
	private String path;
	
	private TShouruDAO shouruDAO;
	private TXiaofeiDAO xiaofeiDAO;
	
	
	public String tongji_meiri()
	{
		HttpServletRequest req=ServletActionContext.getRequest();
		HttpSession sess=req.getSession();
		TUser user=(TUser)sess.getAttribute("user");
		
		String shijian="";
		if(req.getParameter("shijian")==null)
		{
			shijian=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		}
		else
		{
			shijian=req.getParameter("shijian");
		}
		
		
		String sql ="from TShouru where shijian=? and userId="+user.getUserId();
		Object c[]={shijian};
		List shouruList=shouruDAO.getHibernateTemplate().find(sql,c);
		req.setAttribute("shouruList", shouruList);
		
		
		String sql1 ="from TXiaofei where shijian=? and userId="+user.getUserId();
		Object c1[]={shijian};
		List xiaofeiList=xiaofeiDAO.getHibernateTemplate().find(sql1,c1);
		req.setAttribute("xiaofeiList", xiaofeiList);
		
		req.setAttribute("shijian", shijian);
		
		return ActionSupport.SUCCESS;
	}
	
	
	public String tongjiRes()
	{
		HttpServletRequest req=ServletActionContext.getRequest();
		HttpSession sess=req.getSession();
		TUser user=(TUser)sess.getAttribute("user");
		
		String shijian1=req.getParameter("shijian1").trim();
		String shijian2=req.getParameter("shijian2").trim();
		
		
		StringBuffer sql =new StringBuffer("from TShouru where userId="+user.getUserId());
		sql.append(" and shijian >='"+shijian1+"'");
		sql.append(" and shijian <='"+shijian2+"'");
		List shouruList=shouruDAO.getHibernateTemplate().find(sql.toString());
		req.setAttribute("shouruList", shouruList);
		
		
		StringBuffer sql1 =new StringBuffer("from TXiaofei where userId="+user.getUserId());
		sql1.append(" and shijian >='"+shijian1+"'");
		sql1.append(" and shijian <='"+shijian2+"'");
		List xiaofeiList=xiaofeiDAO.getHibernateTemplate().find(sql1.toString());
		req.setAttribute("xiaofeiList", xiaofeiList);
		
		req.setAttribute("shijian1", shijian1);
		req.setAttribute("shijian2", shijian2);
		
		return ActionSupport.SUCCESS;
	}
	
	

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public String getPath()
	{
		return path;
	}

	public void setPath(String path)
	{
		this.path = path;
	}

	public TShouruDAO getShouruDAO()
	{
		return shouruDAO;
	}

	public void setShouruDAO(TShouruDAO shouruDAO)
	{
		this.shouruDAO = shouruDAO;
	}

	public TXiaofeiDAO getXiaofeiDAO()
	{
		return xiaofeiDAO;
	}



	public void setXiaofeiDAO(TXiaofeiDAO xiaofeiDAO)
	{
		this.xiaofeiDAO = xiaofeiDAO;
	}

}
