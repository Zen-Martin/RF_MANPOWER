package com.manpower.steps.test;

import com.manpower.context.ScenarioContext;
import com.manpower.pageObjects.CandidatPage;
import io.cucumber.java8.En;
import org.testng.Assert;

public class CandidatStep implements En {

    public CandidatStep(
            CandidatPage candidatPage,
            ScenarioContext scenario
    ){

        When("User click on **plan de site**", () -> {
            candidatPage.goToCandidatPage();
        });

        And("User make a lot of click", () -> {
            candidatPage.goToDebCarePage();
        });

        Then("User shouldn't important empty space", () -> {
            candidatPage.verifyEmptyContent();
            candidatPage.saveScreenShotPNG();
            Assert.assertEquals(candidatPage.verifyEmptyContent(),true);
        });

    }

}
