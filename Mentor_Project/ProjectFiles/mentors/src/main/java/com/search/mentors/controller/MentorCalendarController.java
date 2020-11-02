package com.search.mentors.controller;

import com.search.mentors.model.MentorCalendar;
import com.search.mentors.repository.MentorCalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/calendar")
public class MentorCalendarController {

    @Autowired
    MentorCalendarRepository mentorCalendarRepository;

    @GetMapping(value = "/getall")
    public List<MentorCalendar> getAllmentorCalendar (){
        return mentorCalendarRepository.findAll();
    }

    @GetMapping(value = "/get/{mentorId}")
    public ResponseEntity<MentorCalendar> getmentorCalendar(@PathVariable long mentorId){
        MentorCalendar mtc = mentorCalendarRepository.findBymentorId(mentorId);
        if(mtc == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(mtc,HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public MentorCalendar CreateMentorCalendar(@RequestBody MentorCalendar mentorCalendar) {
        return mentorCalendarRepository.save(mentorCalendar);
    }

    @PutMapping(value = "/update/{mentorId}")
    public ResponseEntity<MentorCalendar> UpdateMentorCalendar(@PathVariable long mentorId, @RequestBody MentorCalendar mentorCalendar){
        MentorCalendar mtc = mentorCalendarRepository.findBymentorId(mentorId);
        if(mtc == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        mentorCalendarRepository.save(mentorCalendar);
        return new ResponseEntity<>(mentorCalendar,HttpStatus.OK);

    }


    @DeleteMapping(value = "/delete/{Id}")
    public String deleteMentor (@PathVariable long Id){
        mentorCalendarRepository.deleteById(Id);
        return "Deleted";
    }



}
