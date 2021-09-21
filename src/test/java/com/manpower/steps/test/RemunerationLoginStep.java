package com.manpower.steps.test;

import com.manpower.context.ScenarioContext;
import com.manpower.pageObjects.RemunerationLoginPage;
import io.cucumber.java8.En;
import org.testng.Assert;

public class RemunerationLoginStep implements En {

    public RemunerationLoginStep(
            RemunerationLoginPage remunerationLoginPage,
            ScenarioContext scenario
    ){

        When("User get on renumeration login", () -> {
            remunerationLoginPage.goToRemunerationLoginPage();
        });

        Then("User should see renumeration page without error", () -> {
            remunerationLoginPage.verifyRemunerationLoginContent();
            remunerationLoginPage.saveScreenShotPNG();
            Assert.assertEquals(remunerationLoginPage.verifyRemunerationLoginContent(),true);
        });


    }

}
