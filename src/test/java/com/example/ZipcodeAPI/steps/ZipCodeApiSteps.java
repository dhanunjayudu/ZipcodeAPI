package com.example.ZipcodeAPI.steps;

import com.example.ZipcodeAPI.service.ZipcodeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.jbehave.core.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

@Component
public class ZipCodeApiSteps {

    @Autowired
    private ZipcodeService zipcodeService;
    String closestZip, distance, currentZip, targetZipCodes;

    @Given("current zip code value")
    public void getCurrentZipValue(@Named("currentZip") final String currentZip) {
        this.currentZip = currentZip;
    }

    @Given("target zip code values")
    public void getTargetZipValue(@Named("targetZipCodes") final String targetZipCodes) {
        this.targetZipCodes = targetZipCodes;
    }

    @When("retrieving closest zip from the targetZip codes")
    public void retrieveShortDistanceZip() throws IOException {
        closestZip = zipcodeService.retrieveShortDistanceZip(currentZip, targetZipCodes);
    }

    @When("retrieving distance between closest zip to current zip")
    public void retrieveDistance() throws JsonProcessingException {
        distance = zipcodeService.retrieveDistance(currentZip, closestZip);
    }

    @Then("distance between current zip and target zip")
    public void testShortDistance(@Named("expectedDistance") final String expectedDistance) {
        assertEquals(expectedDistance, distance);
    }

    @When("checking valid response status")
    public void checkValidResponse(@Named("responseStatus") final int responseStatus){
        int status = zipcodeService.getResponseCodeValueForMultiDistanceAPI(currentZip, targetZipCodes);
        assertEquals(responseStatus, status);
    }

}
