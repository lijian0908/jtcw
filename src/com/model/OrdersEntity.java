package com.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @ClassName OrdersEntity
 * @Author lijian
 * @Date 2019/6/30
 * @Time 2:45 PM
 * @Version 1.0
 */
@Entity
@Table(name = "orders", schema = "db_xf", catalog = "")
public class OrdersEntity {
    private long id;
    private String orderNum;
    private String orderStatus;
    private String orderAmount;
    private String paidAmount;
    private String productId;
    private Integer buyCounts;
    private Date createTime;
    private Date paidTime;
    private FinancialEntity product;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "order_num")
    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    @Basic
    @Column(name = "order_status")
    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Basic
    @Column(name = "order_amount")
    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    @Basic
    @Column(name = "paid_amount")
    public String getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(String paidAmount) {
        this.paidAmount = paidAmount;
    }

    @Basic
    @Column(name = "product_id")
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "buy_counts")
    public Integer getBuyCounts() {
        return buyCounts;
    }

    public void setBuyCounts(Integer buyCounts) {
        this.buyCounts = buyCounts;
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
    @Column(name = "paid_time")
    public Date getPaidTime() {
        return paidTime;
    }

    public void setPaidTime(Date paidTime) {
        this.paidTime = paidTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdersEntity that = (OrdersEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(orderNum, that.orderNum) &&
                Objects.equals(orderStatus, that.orderStatus) &&
                Objects.equals(orderAmount, that.orderAmount) &&
                Objects.equals(paidAmount, that.paidAmount) &&
                Objects.equals(productId, that.productId) &&
                Objects.equals(buyCounts, that.buyCounts) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(paidTime, that.paidTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, orderNum, orderStatus, orderAmount, paidAmount, productId, buyCounts, createTime, paidTime);
    }

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "financial_code")
    public FinancialEntity getProduct() {
        return product;
    }

    public void setProduct(FinancialEntity product) {
        this.product = product;
    }
}
