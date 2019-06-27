package com.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

/**
 * @ClassName FinancialEntity
 * @Author lijian
 * @Date 2019/6/27
 * @Time 2:00 PM
 * @Version 1.0
 */
@Entity
@Table(name = "financial", schema = "db_xf", catalog = "")
public class FinancialEntity {
    private long id;
    private String financialName;
    private String financialCode;
    private String financialDescribe;
    private String financialSource;
    private Integer financialPrice;
    private Integer financialMaxPrice;
    private Byte financialIfSale;
    private BigDecimal financialUnit;
    private String financialLevel;
    private Date createTime;
    private Date updateTime;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "financial_name")
    public String getFinancialName() {
        return financialName;
    }

    public void setFinancialName(String financialName) {
        this.financialName = financialName;
    }

    @Basic
    @Column(name = "financial_code")
    public String getFinancialCode() {
        return financialCode;
    }

    public void setFinancialCode(String financialCode) {
        this.financialCode = financialCode;
    }

    @Basic
    @Column(name = "financial_describe")
    public String getFinancialDescribe() {
        return financialDescribe;
    }

    public void setFinancialDescribe(String financialDescribe) {
        this.financialDescribe = financialDescribe;
    }

    @Basic
    @Column(name = "financial_source")
    public String getFinancialSource() {
        return financialSource;
    }

    public void setFinancialSource(String financialSource) {
        this.financialSource = financialSource;
    }

    @Basic
    @Column(name = "financial_price")
    public Integer getFinancialPrice() {
        return financialPrice;
    }

    public void setFinancialPrice(Integer financialPrice) {
        this.financialPrice = financialPrice;
    }

    @Basic
    @Column(name = "financial_max_price")
    public Integer getFinancialMaxPrice() {
        return financialMaxPrice;
    }

    public void setFinancialMaxPrice(Integer financialMaxPrice) {
        this.financialMaxPrice = financialMaxPrice;
    }

    @Basic
    @Column(name = "financial_if_sale")
    public Byte getFinancialIfSale() {
        return financialIfSale;
    }

    public void setFinancialIfSale(Byte financialIfSale) {
        this.financialIfSale = financialIfSale;
    }

    @Basic
    @Column(name = "financial_unit")
    public BigDecimal getFinancialUnit() {
        return financialUnit;
    }

    public void setFinancialUnit(BigDecimal financialUnit) {
        this.financialUnit = financialUnit;
    }

    @Basic
    @Column(name = "financial_level")
    public String getFinancialLevel() {
        return financialLevel;
    }

    public void setFinancialLevel(String financialLevel) {
        this.financialLevel = financialLevel;
    }

    @Basic
    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "update_time")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinancialEntity that = (FinancialEntity) o;
        return id == that.id &&
                Objects.equals(financialName, that.financialName) &&
                Objects.equals(financialCode, that.financialCode) &&
                Objects.equals(financialDescribe, that.financialDescribe) &&
                Objects.equals(financialSource, that.financialSource) &&
                Objects.equals(financialPrice, that.financialPrice) &&
                Objects.equals(financialMaxPrice, that.financialMaxPrice) &&
                Objects.equals(financialIfSale, that.financialIfSale) &&
                Objects.equals(financialUnit, that.financialUnit) &&
                Objects.equals(financialLevel, that.financialLevel) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, financialName, financialCode, financialDescribe, financialSource, financialPrice, financialMaxPrice, financialIfSale, financialUnit, financialLevel, createTime, updateTime);
    }
}
