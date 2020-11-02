package com.search.mentors.service;

import com.search.mentors.model.MentorSkills;
import com.search.mentors.repository.MentorSkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class  MentorSkillService {

     @Autowired
     MentorSkillRepository mentorSkillRepository;


     public List<MentorSkills> getByMentorId(long mentorId){
          return mentorSkillRepository.findByMentorId(mentorId);
     }

     public List<String> getBySkillid(long skillId){

          List<MentorSkills> mentor = mentorSkillRepository.findBySkillId(skillId);

          //List<String> ls = mentor.stream().map(mName -> mName.getName()).collect(Collectors.toList());

          List<String> ls = new ArrayList<>();

      for(MentorSkills mm : mentor){
         ls.add("Name: "+ mm.getName()+" || "+"MentorId: "+mm.getMentorId());
      }

//      Iterator itr = mentor.iterator();
//
//        while (itr.hasNext()){
//
//          Object o = itr.hasNext();
//          MentorSkills mm = (MentorSkills) o;
//
//          ls.add(mm.getName());
//        }

          return  ls;

     }

}
