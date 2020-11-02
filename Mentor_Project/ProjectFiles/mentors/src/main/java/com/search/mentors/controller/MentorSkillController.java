package com.search.mentors.controller;


import com.search.mentors.model.MentorSkills;
import com.search.mentors.repository.MentorSkillRepository;
import com.search.mentors.service.MentorSkillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/mentor")
public class MentorSkillController {

    private static final Logger logger = LoggerFactory.getLogger(MentorSkillController.class);


    @Autowired
    MentorSkillService mentorSkillService;

    @Autowired
    MentorSkillRepository mentorSkillRepository;

    @GetMapping(value = "/getSearchResults/{skillName}")
    public List<String> getMentorsName(@PathVariable String  skillName){

        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:8988/tech/name/"+ skillName;
        URI uri =  null;
        try {
            uri = new URI(baseUrl);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        ResponseEntity<SkillTable> result = restTemplate.getForEntity(uri, SkillTable.class);
        SkillTable ss = result.getBody();
        logger.info("List of Mentors");
        return mentorSkillService.getBySkillid(ss.getId());
    }

    @GetMapping(value = "/getall")
    public List<MentorSkills> getAll(@RequestParam(defaultValue = "0") Integer pageNo,
                                     @RequestParam(defaultValue = "20") Integer pageSize,
                                     @RequestParam(defaultValue = "id") String sortBy){
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<MentorSkills>pagedResult = mentorSkillRepository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }

    @GetMapping(value = "/getmentor/{mentorId}")
    public ResponseEntity<List<MentorDetails>> getMentorDetails(@PathVariable long mentorId){
        List<MentorSkills> mts = mentorSkillService.getByMentorId(mentorId);
        if(mts == null){
            logger.error("No result found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<MentorDetails> md = new ArrayList<>();
        logger.info("getting mentor details");
        for(MentorSkills ms : mts){
            md.add(new MentorDetails(ms.getSkillId(),ms.getName(),ms.getRating(),ms.getYearofExp(),ms.getTrainingDelivered(),ms.getFacilitiesOffered()));
        }

        return new ResponseEntity<>(md,HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public MentorSkills CreateMentor(@RequestBody MentorSkills mentorSkills) {
     return mentorSkillRepository.save(mentorSkills);
    }


    @PutMapping(value = "/update/{Id}")
    public  ResponseEntity<MentorSkills> UpdateMentor(@PathVariable long Id,@RequestBody MentorSkills mentorSkills){
        Optional<MentorSkills> mts = mentorSkillRepository.findById(Id);
        if(!mts.isPresent()){
            logger.error("No result found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        mentorSkillRepository.save(mentorSkills);
        logger.info("updated");
        return new ResponseEntity<>(mentorSkills,HttpStatus.OK);

    }

    @DeleteMapping(value = "/delete/{Id}")
    public String deleteMentor (@PathVariable long Id){
        mentorSkillRepository.deleteById(Id);
        return "Deleted";
    }



}
