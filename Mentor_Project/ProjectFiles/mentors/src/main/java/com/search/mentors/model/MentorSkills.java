package com.search.mentors.model;

import javax.persistence.*;

@Entity
@Table(name="MentorSkills")
public class MentorSkills {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "mentor_id")
    private long mentorId;

    @Column(name = "skill_id")
    private long skillId;

    @Column(name = "rating")
    private int rating;

    @Column(name = "year_of_Exp")
    private int yearofExp;

    @Column(name = "no_training_delivered")
    private long trainingDelivered;

    @Column(name = "facilities_offered")
    private String facilitiesOffered;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMentorId() {
        return mentorId;
    }

    public void setMentorId(long mentorId) {
        this.mentorId = mentorId;
    }

    public long getSkillId() {
        return skillId;
    }

    public void setSkillId(long skillId) {
        this.skillId = skillId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getYearofExp() {
        return yearofExp;
    }

    public void setYearofExp(int yearofExp) {
        this.yearofExp = yearofExp;
    }

    public long getTrainingDelivered() {
        return trainingDelivered;
    }

    public void setTrainingDelivered(long trainingDelivered) {
        this.trainingDelivered = trainingDelivered;
    }

    public String getFacilitiesOffered() {
        return facilitiesOffered;
    }

    public void setFacilitiesOffered(String facilitiesOffered) {
        this.facilitiesOffered = facilitiesOffered;
    }
}
