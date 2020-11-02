package com.Skill.Technologies.Controller;

import com.Skill.Technologies.Model.SkillTable;
import com.Skill.Technologies.repository.SkillRepository;
import com.Skill.Technologies.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/tech")
public class SkillController {

    @Autowired
    SkillService skillService;

    @Autowired
    SkillRepository skillRepository;

    @GetMapping(value = "/getall")
    public List<SkillTable> getAllTech(){
        return skillRepository.findAll();
    }

    @GetMapping(value = "id/{Id}")
    public SkillTable getTechsById(@PathVariable long Id){
        return skillService.findSkillsById(Id);
    }

    @GetMapping(value = "name/{cname}")
    public SkillTable getSearchTechByName(@PathVariable String cname){
        return skillService.findSkillsByName(cname);
    }

    @PostMapping(value = "/create")
    public SkillTable CreateSkill(@RequestBody SkillTable skillTable){
        return skillRepository.save(skillTable);
    }

    @PutMapping(value = "/update/{Id}")
    public ResponseEntity<String> updateSkill(@PathVariable long Id,@RequestBody SkillTable skillTable){
        Optional<SkillTable> skt = skillRepository.findById(Id);
        if(!skt.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        skillRepository.save(skillTable);
        return new ResponseEntity<>("Updated",HttpStatus.OK);
    }


   @DeleteMapping(value = "/delete/{Id}")
    public ResponseEntity<String> deleteId(@PathVariable long Id){
       Optional<SkillTable> skt = skillRepository.findById(Id);
       //System.out.println(skt.isPresent());
       if(!skt.isPresent()){
          // System.out.println("hpllaaaa");
           return new ResponseEntity<>("Not found",HttpStatus.NOT_FOUND);
       }
        skillRepository.deleteById(Id);
        return new ResponseEntity<>("Deleted",HttpStatus.OK);
   }


}
