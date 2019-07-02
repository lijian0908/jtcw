package com.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @ClassName FlowEntity
 * @Author lijian
 * @Date 2019/6/30
 * @Time 2:45 PM
 * @Version 1.0
 */
@Entity
@Table(name = "flow", schema = "db_xf", catalog = "")
public class FlowEntity {
    private long id;
    private String flowNum;
    private String orderNum;
    private String productId;
    private String paidAmount;
    private Integer paidMethod;
    private Integer buyCounts;
    private Timestamp createTime;
    private OrdersEntity order;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "flow_num")
    public String getFlowNum() {
        return flowNum;
    }

    public void setFlowNum(String flowNum) {
        this.flowNum = flowNum;
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
    @Column(name = "product_id")
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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
    @Column(name = "paid_method")
    public Integer getPaidMethod() {
        return paidMethod;
    }

    public void setPaidMethod(Integer paidMethod) {
        this.paidMethod = paidMethod;
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
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlowEntity that = (FlowEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(flowNum, that.flowNum) &&
                Objects.equals(orderNum, that.orderNum) &&
                Objects.equals(productId, that.productId) &&
                Objects.equals(paidAmount, that.paidAmount) &&
                Objects.equals(paidMethod, that.paidMethod) &&
                Objects.equals(buyCounts, that.buyCounts) &&
                Objects.equals(createTime, that.createTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, flowNum, orderNum, productId, paidAmount, paidMethod, buyCounts, createTime);
    }

    @OneToOne
    @JoinColumn(name = "order_num", referencedColumnName = "order_num")
    public OrdersEntity getOrder() {
        return order;
    }

    public void setOrder(OrdersEntity order) {
        this.order = order;
    }
}
