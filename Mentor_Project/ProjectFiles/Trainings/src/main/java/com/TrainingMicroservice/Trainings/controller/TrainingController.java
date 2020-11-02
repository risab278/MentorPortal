package com.TrainingMicroservice.Trainings.controller;


import com.TrainingMicroservice.Trainings.model.Training;
import com.TrainingMicroservice.Trainings.model.sendSMS;
import com.TrainingMicroservice.Trainings.repository.TrainingRepository;
import com.TrainingMicroservice.Trainings.service.EmailServiceImp;
import com.TrainingMicroservice.Trainings.service.TrainingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping(value = "/training")
public class TrainingController {

    private static final Logger logger = LoggerFactory.getLogger(TrainingController.class);

    @Autowired
    TrainingService trainingService;

    @Autowired
    TrainingRepository trainingRepository;



    @GetMapping(value = "/get/{Id}")
    public ResponseEntity<Training> getTrainings(@PathVariable long Id){
        Training  training = trainingRepository.findById(Id).orElse(null);
        if(training == null){
            logger.error("There is no training with this id");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info("Training corresponding to this id is found");
        return new ResponseEntity<>(training,HttpStatus.OK);
    }


    @GetMapping(value = "/getall")
    public List<Training> getAll(){
        return trainingRepository.findAll();
    }


    @GetMapping(value = "/getuser/{status}/{UserId}")
    public ResponseEntity<List<Training>>getCompletedTrainings( @PathVariable String status,@PathVariable long UserId){
        List<Training> completedTrainings = trainingRepository.findByStatusAndUserId(status,UserId);
        if(completedTrainings == null){
            logger.error("No result found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        logger.info("List of trainings");
        return new ResponseEntity<>(completedTrainings,HttpStatus.OK);
    }


    @GetMapping(value = "/getmentor/{status}/{MentorId}")
    public ResponseEntity<List<Training>> getCompletedTrainingsByMentor( @PathVariable String status,@PathVariable long MentorId){
        List<Training> completedTrainings = trainingRepository.findByStatusAndMentorId(status, MentorId);
        if(completedTrainings == null){
            logger.error("No result found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info("List of trainings");
        return new ResponseEntity<>(completedTrainings,HttpStatus.OK);
    }


    @PostMapping(value = "/create")
    public Training createTraining(@RequestBody Training training){
        return trainingRepository.save(training);
    }


    @PutMapping(value = "/update/{Id}")
    Training updateTraining(@RequestBody Training training,@PathVariable long Id){
        return trainingRepository.save(training);
    }


    @PostMapping(value = "/propose/{userId}/{mentorId}/{skillId}")
    public Training proposeTraining( @PathVariable long userId, @PathVariable long mentorId, @PathVariable long skillId){
        return trainingService.proposeTraining(userId, mentorId, skillId);
    }


    @PutMapping(value = "/approved/{Id}")
    public String approvedTrainings(@PathVariable long Id) {
        return trainingService.approveTraining(Id);
    }


    @PutMapping(value = "/finalize/{Id}")
    public Training finalizedTrainings(@PathVariable long Id){
        return trainingService.findById(Id);
    }


    @DeleteMapping(value = "/delete/{Id}")
    public String deleteTraining(@PathVariable long Id){
        trainingRepository.deleteById(Id);
        return "Deleted";
    }

}
