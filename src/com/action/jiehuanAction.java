package com.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.dao.TJiehuanDAO;
import com.model.TJiehuan;
import com.model.TUser;
import com.opensymphony.xwork2.ActionSupport;

public class jiehuanAction extends ActionSupport
{
	private Integer id;
	private String leixing;
	private String shijian;
	private Integer jine;
	
	private String duifang;
	private String beizhu;
	private String shifouhuan;
	private String huanshi;
	
	private Integer userId;
	
	private TJiehuanDAO jiehuanDAO;
	
	public String jiehuanAdd()
	{
		Map session= ServletActionContext.getContext().getSession();
		TUser user=(TUser)session.get("user");
		
		TJiehuan jiehuan=new TJiehuan();
		
		//jiehuan.setId(id);
		jiehuan.setLeixing(leixing);
		jiehuan.setShijian(shijian);
		jiehuan.setJine(jine);
		
		jiehuan.setDuifang(duifang);
		jiehuan.setBeizhu(beizhu);
		jiehuan.setShifouhuan("否");
		jiehuan.setHuanshi("");
		
		jiehuan.setUserId(user.getUserId());
		
		jiehuanDAO.save(jiehuan);
		
		Map request=(Map)ServletActionContext.getContext().get("request");
		request.put("msg", "信息添加成功");
		return "msg";
	}
	
	
	public String jiehuanMana()
	{
		Map session= ServletActionContext.getContext().getSession();
		TUser user=(TUser)session.get("user");
		
		String sql="from TJiehuan where userId="+user.getUserId();
		List jiehuanList =jiehuanDAO.getHibernateTemplate().find(sql);
		Map request=(Map)ServletActionContext.getContext().get("request");
		request.put("jiehuanList", jiehuanList);
		return ActionSupport.SUCCESS;
	}
	
	public String jiehuanDel()
	{
		TJiehuan jiehuan=jiehuanDAO.findById(id);
		jiehuanDAO.delete(jiehuan);
		
		Map request=(Map)ServletActionContext.getContext().get("request");
		request.put("msg", "信息添加成功");
		return "msg";
	}

	
	public String huan()
	{
		TJiehuan jiehuan=jiehuanDAO.findById(id);
		jiehuan.setShifouhuan("是");
		jiehuan.setHuanshi(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		
		jiehuanDAO.attachDirty(jiehuan);
		
		Map request=(Map)ServletActionContext.getContext().get("request");
		request.put("msg", "信息更新成功");
		return "msg";
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getLeixing() {
		return leixing;
	}


	public void setLeixing(String leixing) {
		this.leixing = leixing;
	}


	public String getShijian() {
		return shijian;
	}


	public void setShijian(String shijian) {
		this.shijian = shijian;
	}


	public TJiehuanDAO getJiehuanDAO() {
		return jiehuanDAO;
	}


	public void setJiehuanDAO(TJiehuanDAO jiehuanDAO) {
		this.jiehuanDAO = jiehuanDAO;
	}


	public Integer getJine() {
		return jine;
	}


	public void setJine(Integer jine) {
		this.jine = jine;
	}


	public String getDuifang() {
		return duifang;
	}


	public void setDuifang(String duifang) {
		this.duifang = duifang;
	}


	public String getBeizhu() {
		return beizhu;
	}


	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}


	public String getShifouhuan() {
		return shifouhuan;
	}


	public void setShifouhuan(String shifouhuan) {
		this.shifouhuan = shifouhuan;
	}


	public String getHuanshi() {
		return huanshi;
	}


	public void setHuanshi(String huanshi) {
		this.huanshi = huanshi;
	}


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
