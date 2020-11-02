package com.Skill.Technologies.repository;

import com.Skill.Technologies.Model.SkillTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository< SkillTable, Long>{
    SkillTable findByName(String cname);
}
