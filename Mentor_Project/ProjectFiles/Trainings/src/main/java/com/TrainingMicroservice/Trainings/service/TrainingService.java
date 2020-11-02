package com.TrainingMicroservice.Trainings.service;

import com.TrainingMicroservice.Trainings.controller.TrainingController;
import com.TrainingMicroservice.Trainings.model.Training;
import com.TrainingMicroservice.Trainings.model.sendSMS;
import com.TrainingMicroservice.Trainings.repository.TrainingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;



@Service
public class TrainingService{

    @Autowired
    TrainingRepository trainingRepository;

    @Autowired
    EmailServiceImp emailServiceImp;

    private static final Logger logger = LoggerFactory.getLogger(TrainingController.class);


    public Training proposeTraining( long userId, long mentorId, long skillId){
        Training train = trainingRepository.findByUserIdAndMentorIdAndSkillId(userId, mentorId, skillId);
        if(train == null){
            Training training = new Training();
            training.setMentorId(mentorId);
            training.setUserId(userId);
            training.setSkillId(skillId);
            training.setStatus("Proposed");
            emailServiceImp.sendproposalmessage();

            return trainingRepository.save(training);
        }
        return train;
    }


    public String approveTraining(long Id){
        Training training = trainingRepository.findById(Id).get();
        if(training.getStatus().equals("Proposed")) {
            training.setStatus("Approved");
            trainingRepository.save(training);
            sendSMS.sendSms();
            logger.info("Training is approved");

            return training.getStatus();
        }
        logger.warn("Check the training status");
        return "Error";
    }





    public Training findById(long id) {
        Training training = trainingRepository.findById(id).get();

        if (training.getStatus().equals("Approved")) {

            //get mentor calendar

            RestTemplate restTemplate = new RestTemplate();
            String baseUrl = "http://localhost:8691/calendar/get/"+training.getMentorId();
            URI ur = null;
            try{
                ur = new URI(baseUrl);
            }catch (URISyntaxException e){
                e.printStackTrace();
            }

            ResponseEntity<MentorCalendar> res = restTemplate.getForEntity(ur,MentorCalendar.class);
            MentorCalendar mcl = res.getBody();

            //updating training details

            training.setStatus("underprogress");
            training.setStartDate(mcl.getEndDate().plusDays(3));
            training.setEndDate(training.getStartDate().plusDays(30));
            training.setStartTime(mcl.getStartTime());
            training.setEndTime(mcl.getEndTime());
            training.setAmountReceived(3000);


            //updating mentor calendar

            RestTemplate restTemplate2 = new RestTemplate();
            String baseUrl1 = "http://localhost:8691/calendar/update/"+training.getMentorId();
            URI uri1 =  null;
            try {
                uri1 = new URI(baseUrl1);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

            MentorCalendar mentorCalendar = new MentorCalendar();
            mentorCalendar.setStartDate(training.getStartDate());
            mentorCalendar.setEndDate(training.getEndDate());
            mentorCalendar.setStartTime(training.getStartTime());
            mentorCalendar.setEndTime(training.getEndTime());
            mentorCalendar.setMentorId(training.getMentorId());
            mentorCalendar.setId(mcl.getId());


            restTemplate2.put(uri1,mentorCalendar);

            //creating payment details

            RestTemplate restTemplate1 = new RestTemplate();
            String url = "http://localhost:9896/createpayment";
            URI uri = null;
            try {
                uri = new URI(url);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

            Payments payments = new Payments();
            payments.setId("" + training.getId());
            payments.setMid("" + training.getMentorId());
            payments.setUid("" + training.getUserId());
            payments.setTid("" + training.getId());
            payments.setAmt("3k");
            payments.setDatetime("" + training.getStartDate());
            payments.setRemarks("Payment Done");

            restTemplate1.postForEntity(uri, payments, Payments.class);

            return trainingRepository.save(training);
        }

        return training;

    }

}
