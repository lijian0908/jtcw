package com.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * @ClassName SubjectEntity
 * @Author lijian
 * @Date 2019/7/10
 * @Time 11:37 AM
 * @Version 1.0
 */
@Entity
@Table(name = "subject", schema = "db_xf", catalog = "")
public class SubjectEntity {
    private long id;
    private String subjectName;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "subjectName")
    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    @Basic
    @Column(name = "optionA")
    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    @Basic
    @Column(name = "optionB")
    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    @Basic
    @Column(name = "optionC")
    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    @Basic
    @Column(name = "optionD")
    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubjectEntity that = (SubjectEntity) o;
        return id == that.id &&
                Objects.equals(subjectName, that.subjectName) &&
                Objects.equals(optionA, that.optionA) &&
                Objects.equals(optionB, that.optionB) &&
                Objects.equals(optionC, that.optionC) &&
                Objects.equals(optionD, that.optionD);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, subjectName, optionA, optionB, optionC, optionD);
    }
}
