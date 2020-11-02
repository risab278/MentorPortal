package com.search.mentors.model;

import javax.persistence.*;

@Entity
@Table(name="MentorTable")
public class MentorTable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column(name = "username")
    private String userName;

    @Column(name = "linkedin_Url")
    private String linkedinUrl;

    @Column(name = "reg_dateTime")
    private String regdateTime;

    @Column(name = "reg_code")
    private String regCode;

    @Column(name = "year_of_Exp")
    private int yearofExp;

    @Column(name = "active")
    private Boolean active = false;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLinkedinUrl() {
        return linkedinUrl;
    }

    public void setLinkedinUrl(String linkedinUrl) {
        this.linkedinUrl = linkedinUrl;
    }

    public String getRegdateTime() {
        return regdateTime;
    }

    public void setRegdateTime(String regdateTime) {
        this.regdateTime = regdateTime;
    }

    public String getRegCode() {
        return regCode;
    }

    public void setRegCode(String regCode) {
        this.regCode = regCode;
    }

    public int getYearofExp() {
        return yearofExp;
    }

    public void setYearofExp(int yearofExp) {
        this.yearofExp = yearofExp;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
