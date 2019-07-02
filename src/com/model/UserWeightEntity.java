package com.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * @ClassName UserWeightEntity
 * @Author lijian
 * @Date 2019/7/2
 * @Time 2:09 PM
 * @Version 1.0
 */
@Entity
@Table(name = "user_weight", schema = "db_xf", catalog = "")
public class UserWeightEntity {
    private long id;
    private Long userId;
    private Integer aweight;
    private Integer bweight;
    private Integer cweight;
    private Integer dweight;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "userId")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "Aweight")
    public Integer getAweight() {
        return aweight;
    }

    public void setAweight(Integer aweight) {
        this.aweight = aweight;
    }

    @Basic
    @Column(name = "Bweight")
    public Integer getBweight() {
        return bweight;
    }

    public void setBweight(Integer bweight) {
        this.bweight = bweight;
    }

    @Basic
    @Column(name = "Cweight")
    public Integer getCweight() {
        return cweight;
    }

    public void setCweight(Integer cweight) {
        this.cweight = cweight;
    }

    @Basic
    @Column(name = "Dweight")
    public Integer getDweight() {
        return dweight;
    }

    public void setDweight(Integer dweight) {
        this.dweight = dweight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserWeightEntity that = (UserWeightEntity) o;
        return id == that.id &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(aweight, that.aweight) &&
                Objects.equals(bweight, that.bweight) &&
                Objects.equals(cweight, that.cweight) &&
                Objects.equals(dweight, that.dweight);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userId, aweight, bweight, cweight, dweight);
    }
}
