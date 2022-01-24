package com.example.ZipcodeAPI.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@PropertySource("classpath:application.properties")
public class ZipcodeService {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    ObjectMapper objectMapper;
//    @Value("${zipApi}")
//    String zipUrl;
    String key = "t4YaYlDQEGd71OxEuzvFbpj6K5wgjeG71jfHjQhkC7BJZ5htb2zSBk5BmW7qRwns";

    public String retrieveShortDistanceZip(String currentZip, String...targetZip) throws IOException {
        String target = Arrays.stream(targetZip).collect(Collectors.joining(","));

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(String.format("https://www.zipcodeapi.com/rest/%s/multi-distance.json/%s/%s/km",key, currentZip, target));
        ResponseEntity<String> response = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET, null, String.class);
        String body = response.getBody();
        TypeReference<HashMap<String, BigDecimal>> typeRef
                = new TypeReference<HashMap<String, BigDecimal>>() {};
        JsonNode jsonNode = objectMapper.readTree(body);
        JsonNode distances = jsonNode.get("distances");
        Map<String, BigDecimal> map = objectMapper.readerFor(typeRef).readValue(distances);
        System.out.println(map);
        return Collections.min(map.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    public String retrieveDistance(String currentZip, String targetZip) throws JsonProcessingException {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(String.format("https://www.zipcodeapi.com/rest/%s/distance.json/%s/%s/km",key, currentZip, targetZip));
        ResponseEntity<String> response = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET, null, String.class);
        String body = response.getBody();

        JsonNode jsonNode = objectMapper.readTree(body);
        BigDecimal distances = jsonNode.get("distance").decimalValue();

        return distances.toString();
    }

    public int getResponseCodeValueForMultiDistanceAPI(String currentZip, String...targetZip){
        String target = Arrays.stream(targetZip).collect(Collectors.joining(","));

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(String.format("https://www.zipcodeapi.com/rest/%s/multi-distance.json/%s/%s/km", key, currentZip, target));
        ResponseEntity<String> response = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET, null, String.class);
        return response.getStatusCodeValue();
    }
}
