package com.TrainingMicroservice.Trainings.repository;

import com.TrainingMicroservice.Trainings.model.Training;
import com.sun.istack.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TrainingRepository extends JpaRepository< Training, Long> {

    List<Training> findByStatusAndUserId(String status, long Id);
    List<Training> findByStatusAndMentorId(String status, long Id);
    Training findByUserIdAndMentorIdAndSkillId(long userId,long mentorId, long skillId);

}
