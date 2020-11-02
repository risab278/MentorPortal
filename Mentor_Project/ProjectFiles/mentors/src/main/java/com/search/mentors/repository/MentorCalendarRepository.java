package com.search.mentors.repository;

import com.search.mentors.model.MentorCalendar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MentorCalendarRepository extends JpaRepository<MentorCalendar,Long> {
    MentorCalendar findBymentorId(long mentorId);

}
