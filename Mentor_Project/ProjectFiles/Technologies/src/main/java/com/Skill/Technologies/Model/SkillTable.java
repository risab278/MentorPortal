package com.Skill.Technologies.Model;

import javax.persistence.*;

@Entity
@Table(name="SkillTable")
public class SkillTable {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column(name="name")
    private String name;

    @Column(name = "toc")
    private String toc;

    @Column(name="duration")
    private String duration;

    @Column(name = "pre_requisite")
    private String pre_requisite;




    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getToc() {
        return toc;
    }

    public void setToc(String toc) {
        this.toc = toc;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPre_requisite() {
        return pre_requisite;
    }

    public void setPre_requisite(String pre_requisite) {
        this.pre_requisite = pre_requisite;
    }

}
