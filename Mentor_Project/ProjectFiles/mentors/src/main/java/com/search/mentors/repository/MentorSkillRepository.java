package com.search.mentors.repository;

import com.search.mentors.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MentorSkillRepository extends JpaRepository< MentorSkills, Long> {

//    @Query(value = "select M.name from MentorSkills M where M.skillId= :mskillid")
    List<MentorSkills> findBySkillId(long skillId);
    List<MentorSkills> findByMentorId(long mentorId);

}
