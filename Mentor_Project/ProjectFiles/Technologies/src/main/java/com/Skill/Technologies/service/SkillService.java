package com.Skill.Technologies.service;

import com.Skill.Technologies.Model.SkillTable;
import com.Skill.Technologies.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillService {

    @Autowired
    SkillRepository skillRepository;

    public SkillTable findSkillsById(long Id){
        return skillRepository.findById(Id).get();
    }

    public SkillTable findSkillsByName(String cname){
        return skillRepository.findByName(cname);
    }

}
