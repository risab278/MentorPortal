package com.TrainingMicroservice.Trainings.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class sendSMS {
    public static String sendSms() {
        try {
            // Construct data
            String apiKey = "apikey=" + "A10QG2Yc57I-tScEBgdtctKUan1vlWAJErCHuXsZop";
            String message = "&message=" + "Dear Learner Your trainings is approved";
            String sender = "&sender=" + "TXTLCL";
            String numbers = "&numbers=" + "919182507696";
            // Send data
            HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
            String data = apiKey + numbers + message + sender;
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
            conn.getOutputStream().write(data.getBytes("UTF-8"));
            final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            final StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) {
                stringBuffer.append(line);
            }
            rd.close();
            return stringBuffer.toString();
        } catch (Exception e) {
            System.out.println("Error SMS "+e);
            return "Error "+e;
        }
    }

}