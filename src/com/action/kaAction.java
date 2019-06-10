package com.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.dao.TKaDAO;
import com.model.TAdmin;
import com.model.TKa;
import com.model.TUser;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class kaAction extends ActionSupport
{
	private Integer id;
	private String kahao;
	private String yinhang;
	private String leixing;
	
	private Integer userId;
	
	private TKaDAO kaDAO;
	
	/**
	 * @return
	 */
	public String kaAdd()
	{
		Map session= ServletActionContext.getContext().getSession();
		TUser user=(TUser)session.get("user");
		
		TKa ka=new TKa();
		
		//ka.setId(id);
		ka.setKahao(kahao);
		ka.setYinhang(yinhang);
		ka.setLeixing(leixing);
		
		ka.setUserId(user.getUserId());
		
		kaDAO.save(ka);
		
		Map request=(Map)ServletActionContext.getContext().get("request");
		request.put("msg", "信息添加成功");
		return "msg";
	}
	
	
	public String kaMana()
	{
		Map session= ServletActionContext.getContext().getSession();
		TUser user=(TUser)session.get("user");
		
		String sql="from TKa where userId="+user.getUserId();
		List kaList =kaDAO.getHibernateTemplate().find(sql);
		Map request=(Map)ServletActionContext.getContext().get("request");
		request.put("kaList", kaList);
		return ActionSupport.SUCCESS;
	}
	
	public String kaDel()
	{
		TKa ka=kaDAO.findById(id);
		kaDAO.delete(ka);
		
		Map request=(Map)ServletActionContext.getContext().get("request");
		request.put("msg", "信息添加成功");
		return "msg";
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getKahao() {
		return kahao;
	}


	public void setKahao(String kahao) {
		this.kahao = kahao;
	}


	public String getYinhang() {
		return yinhang;
	}


	public void setYinhang(String yinhang) {
		this.yinhang = yinhang;
	}


	public String getLeixing() {
		return leixing;
	}


	public void setLeixing(String leixing) {
		this.leixing = leixing;
	}


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public TKaDAO getKaDAO() {
		return kaDAO;
	}


	public void setKaDAO(TKaDAO kaDAO) {
		this.kaDAO = kaDAO;
	}


}
