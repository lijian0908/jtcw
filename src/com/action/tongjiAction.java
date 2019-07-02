package com.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.model.TShouru;
import com.model.TXiaofei;
import com.util.Month;
import com.util.Util;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;

import com.dao.TShouruDAO;
import com.dao.TXiaofeiDAO;
import com.model.TUser;
import com.opensymphony.xwork2.ActionSupport;

public class tongjiAction extends ActionSupport
{
		
    private String message;
	private String path;
	private String year;

	public void setYear(String year) {
		this.year = year;
	}

	private TShouruDAO shouruDAO;
	private TXiaofeiDAO xiaofeiDAO;

	private String root;
	private JSONArray zhiList;
	private JSONArray shouList;
/**/

	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}

	public JSONArray getZhiList() {
		return zhiList;
	}

	public void setZhiList(JSONArray zhiList) {
		this.zhiList = zhiList;
	}

	public JSONArray getShouList() {
		return shouList;
	}

	public void setShouList(JSONArray shouList) {
		this.shouList = shouList;
	}

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
	
	public String shouzhi(){
		HttpServletRequest req=ServletActionContext.getRequest();
		HttpSession sess=req.getSession();
		TUser user=(TUser)sess.getAttribute("user");

		StringBuffer sql =new StringBuffer(" from TShouru where userId="+user.getUserId());
		if(year == null || "".equals(year)){
			year = Util.getSysYear();
		}
		List<Double> shou = new ArrayList<>();
		sql.append(" and substring( shijian, 1, 4 ) ='"+year+"'");
			List<TShouru> shouruList=shouruDAO.getHibernateTemplate().find(sql.toString());
			int i = 0;
			for(Month month: Month.values()){
				Double monthShouRu =
				shouruList.stream().filter(new Predicate<TShouru>() {
					@Override
					public boolean test(TShouru tShouru) {
						return month.getCode().equals(tShouru.getShijian().substring(5,7));
					}
				}).mapToDouble(TShouru::getJine).sum();
				shou.add(monthShouRu);
				i++;
			}
		shouList = JSONArray.fromObject(shou);
		StringBuffer sql1 =new StringBuffer("from TXiaofei where userId="+user.getUserId());
		if(year == null || "".equals(year)){
			year = Util.getSysYear();
		}
		sql1.append(" and substring( shijian, 1, 4 ) ='"+year+"'");
		List<TXiaofei> zhichuList=xiaofeiDAO.getHibernateTemplate().find(sql1.toString());

		List<Double> zhi = new ArrayList<>();
		int j = 0;
		for(Month month: Month.values()){
			Double monthZhiChu =
					zhichuList.stream().filter(new Predicate<TXiaofei>() {
						@Override
						public boolean test(TXiaofei tXiaofei) {
							return month.getCode().equals(tXiaofei.getShijian().substring(5,7));
						}
					}).mapToDouble(TXiaofei::getJine).sum();
			zhi.add(monthZhiChu);
			j++;
		}
		zhiList = JSONArray.fromObject(zhi);

		StringBuffer sql3 =new StringBuffer("from TShouru where userId="+user.getUserId());
		if(year == null || "".equals(year)){
			year = Util.getSysYear();
		}
		sql3.append(" and substring( shijian, 1, 4 ) ='"+year+"'");
		List<TShouru> shouRuRate =shouruDAO.getHibernateTemplate().find(sql3.toString());
		Double sum = shouRuRate.stream().mapToDouble(TShouru::getJine).sum();

		Map<String,Double> map  = shouRuRate.stream().collect(Collectors.groupingBy(TShouru::getChengyuan,Collectors.summingDouble(TShouru::getJine)));
		List<JSONObject> shouru2 = new ArrayList<>();
		for(Map.Entry entry : map.entrySet()){
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("name",entry.getKey());
			jsonObject.put("y",new BigDecimal(entry.getValue().toString())
					.divide(new BigDecimal(sum),2,BigDecimal.ROUND_DOWN).multiply(new BigDecimal(100)));
			shouru2.add(jsonObject);
		}

		StringBuffer sql4 =new StringBuffer("from TXiaofei where userId="+user.getUserId());
		if(year == null || "".equals(year)){
			year = Util.getSysYear();
		}
		sql4.append(" and substring( shijian, 1, 4 ) ='"+year+"'");
		List<TXiaofei> xiaoFeiRate =shouruDAO.getHibernateTemplate().find(sql4.toString());
		Double sum1 = xiaoFeiRate.stream().mapToDouble(TXiaofei::getJine).sum();

		Map<String,Double> map1  = xiaoFeiRate.stream().collect(Collectors.groupingBy(TXiaofei::getChengyuan,Collectors.summingDouble(TXiaofei::getJine)));
		List<JSONObject> xiaofei2 = new ArrayList<>();
		for(Map.Entry entry : map1.entrySet()){
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("name",entry.getKey());
			jsonObject.put("y",new BigDecimal(entry.getValue().toString())
					.divide(new BigDecimal(sum1),2,BigDecimal.ROUND_DOWN).multiply(new BigDecimal(100)));
			xiaofei2.add(jsonObject);
		}


		JSONObject roota = new JSONObject();
		roota.put("zhiList",zhiList);
		roota.put("shouList",shouList);
		roota.put("shouru2",shouru2);
		roota.put("xiaofei2",xiaofei2);
		root = roota.toString();
		System.out.println(root);
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
