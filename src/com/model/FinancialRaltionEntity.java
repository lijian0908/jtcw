package com.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

/**
 * @ClassName FinancialRaltionEntity
 * @Author lijian
 * @Date 2019/6/27
 * @Time 2:00 PM
 * @Version 1.0
 */
@Entity
@Table(name = "financial_raltion", schema = "db_xf", catalog = "")
public class FinancialRaltionEntity {
    private int id;
    private String financialCode;
    private String userId;
    private Integer financialPrice;
    private BigDecimal financialUnit;
    private Integer financialCopies;
    private Date createTime;
    private Date updateTime;
    private FinancialEntity financial;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
    @Column(name = "financial_unit")
    public BigDecimal getFinancialUnit() {
        return financialUnit;
    }

    public void setFinancialUnit(BigDecimal financialUnit) {
        this.financialUnit = financialUnit;
    }

    @Basic
    @Column(name = "financial_ copies")
    public Integer getFinancialCopies() {
        return financialCopies;
    }

    public void setFinancialCopies(Integer financialCopies) {
        this.financialCopies = financialCopies;
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
        FinancialRaltionEntity that = (FinancialRaltionEntity) o;
        return id == that.id &&
                Objects.equals(financialCode, that.financialCode) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(financialPrice, that.financialPrice) &&
                Objects.equals(financialUnit, that.financialUnit) &&
                Objects.equals(financialCopies, that.financialCopies) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, financialCode, userId, financialPrice, financialUnit, financialCopies, createTime, updateTime);
    }

    @OneToOne
    @JoinColumn(name = "financial_code", referencedColumnName = "financial_code")
    public FinancialEntity getFinancial() {
        return financial;
    }

    public void setFinancial(FinancialEntity financial) {
        this.financial = financial;
    }
}
