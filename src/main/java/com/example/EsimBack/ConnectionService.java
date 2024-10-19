package com.example.EsimBack;


import org.apache.hc.client5.http.classic.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

@Service
public class ConnectionService {
    private static final Logger logger = LoggerFactory.getLogger(ConnectionService.class);

    @Autowired
    private RestTemplate restTemplate;

    public void checkBmwBackHealth() {
        try {
            //ResponseEntity<String> response = restTemplate.getForEntity("https://localhost:8442/health", String.class);
            //if (response.getStatusCode().is2xxSuccessful()) {
            //    logger.info("BmwBack est connecté : {}", response.getBody());
            //} else {
            //    logger.warn("BmwBack a répondu avec un statut : {}", response.getStatusCode());
            //}
            // Define the URL to send the GET request to
            String url = "https://localhost:8442/health";

            // Make the GET request and get the response as a String
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            // Print the response body
            System.out.println("Response: " + response.getBody());


        } catch(Exception  e)  {
            System.out.println(e.getMessage());

        }
    }

}
