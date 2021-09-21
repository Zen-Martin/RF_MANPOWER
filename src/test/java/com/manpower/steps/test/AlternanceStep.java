package com.manpower.steps.test;

import com.manpower.context.ScenarioContext;
import com.manpower.pageObjects.AlternancePage;
import io.cucumber.java8.En;
import org.testng.Assert;

public class AlternanceStep implements En {

    public AlternanceStep(
            AlternancePage alternancePage,
            ScenarioContext scenario
    ){


        When("User get on filter page alternance", () -> {
            alternancePage.goToAlternancePage();
        });

        Then("User should see specific alternance offer", () -> {
            alternancePage.verifyAlternanceFilter();
            alternancePage.saveScreenShotPNG();
            Assert.assertEquals(alternancePage.verifyAlternanceFilter(),true);
        });



    }


}
