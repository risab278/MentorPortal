package com.search.mentors.controller;

public class MentorDetails {
    private int rating;
    private int yearofExp;
    private long trainingDelivered;
    private String facilitiesOffered;
    private String name;
    private long skillId;

    public MentorDetails( long skillId,String name,int rating, int yearofExp, long trainingDelivered, String facilitiesOffered)
    {
        this.skillId = skillId;
        this.name = name;
        this.facilitiesOffered = facilitiesOffered;
        this.rating = rating;
        this.trainingDelivered = trainingDelivered;
        this.yearofExp = yearofExp;

    }

    public long getSkillId() {
        return skillId;
    }

    public void setSkillId(long skillId) {
        this.skillId = skillId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
