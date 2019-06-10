package com.model;

/**
 * TJiehuan entity. @author MyEclipse Persistence Tools
 */

public class TJiehuan implements java.io.Serializable {

	// Fields

	private Integer id;
	private String leixing;
	private String shijian;
	private Integer jine;
	
	private String duifang;
	private String beizhu;
	private String shifouhuan;
	private String huanshi;
	
	private Integer userId;

	// Constructors

	/** default constructor */
	public TJiehuan() {
	}

	/** full constructor */
	public TJiehuan(String leixing, String shijian, Integer jine,
			String duifang, String beizhu, String shifouhuan, String huanshi,
			Integer userId) {
		this.leixing = leixing;
		this.shijian = shijian;
		this.jine = jine;
		this.duifang = duifang;
		this.beizhu = beizhu;
		this.shifouhuan = shifouhuan;
		this.huanshi = huanshi;
		this.userId = userId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLeixing() {
		return this.leixing;
	}

	public void setLeixing(String leixing) {
		this.leixing = leixing;
	}

	public String getShijian() {
		return this.shijian;
	}

	public void setShijian(String shijian) {
		this.shijian = shijian;
	}

	public Integer getJine() {
		return this.jine;
	}

	public void setJine(Integer jine) {
		this.jine = jine;
	}

	public String getDuifang() {
		return this.duifang;
	}

	public void setDuifang(String duifang) {
		this.duifang = duifang;
	}

	public String getBeizhu() {
		return this.beizhu;
	}

	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}

	public String getShifouhuan() {
		return this.shifouhuan;
	}

	public void setShifouhuan(String shifouhuan) {
		this.shifouhuan = shifouhuan;
	}

	public String getHuanshi() {
		return this.huanshi;
	}

	public void setHuanshi(String huanshi) {
		this.huanshi = huanshi;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}