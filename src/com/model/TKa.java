package com.model;

/**
 * TKa entity. @author MyEclipse Persistence Tools
 */

public class TKa implements java.io.Serializable {

	// Fields

	private Integer id;
	private String kahao;
	private String yinhang;
	private String leixing;
	
	private Integer userId;

	// Constructors

	/** default constructor */
	public TKa() {
	}

	/** full constructor */
	public TKa(String kahao, String yinhang, String leixing, Integer userId) {
		this.kahao = kahao;
		this.yinhang = yinhang;
		this.leixing = leixing;
		this.userId = userId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKahao() {
		return this.kahao;
	}

	public void setKahao(String kahao) {
		this.kahao = kahao;
	}

	public String getYinhang() {
		return this.yinhang;
	}

	public void setYinhang(String yinhang) {
		this.yinhang = yinhang;
	}

	public String getLeixing() {
		return this.leixing;
	}

	public void setLeixing(String leixing) {
		this.leixing = leixing;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}