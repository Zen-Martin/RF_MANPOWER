package com.manpower.steps.test;

import com.manpower.context.ScenarioContext;
import com.manpower.pageObjects.CddPage;
import io.cucumber.java8.En;
import org.testng.Assert;

public class CddStep implements En {

    public CddStep(
            CddPage cddPage,
            ScenarioContext scenario
    ){

        When("User get on CDD page", () -> {
            cddPage.goToCDD();
        });

        When("User click on **more informations**", () -> {
            cddPage.checkLink();
        });

        Then("User shouldn't see error page for this", () -> {
            cddPage.verifyMoreCDDContent();
            cddPage.saveScreenShotPNG();
            Assert.assertEquals(cddPage.verifyMoreCDDContent(),true);
        });




    }

}
