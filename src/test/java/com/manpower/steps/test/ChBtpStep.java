package com.manpower.steps.test;

import com.manpower.context.ScenarioContext;
import com.manpower.pageObjects.ChBtpPage;
import io.cucumber.java8.En;
import org.testng.Assert;

public class ChBtpStep implements En {

    public ChBtpStep(
            ChBtpPage chBtpPage,
            ScenarioContext scenario
    ){

        When("User get on chantier btp", () -> {
            chBtpPage.goToChantierBTP();
        });

        Then("User should see chantier page without error", () -> {
            chBtpPage.verifyChantierBTPContent();
            chBtpPage.saveScreenShotPNG();
            Assert.assertEquals(chBtpPage.verifyChantierBTPContent(),true);
        });



    }

}
