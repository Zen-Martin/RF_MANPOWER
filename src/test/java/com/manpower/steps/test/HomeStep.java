package com.manpower.steps.test;

import com.manpower.context.ScenarioContext;
import com.manpower.pageObjects.HomePage;
import io.cucumber.java8.En;
import org.testng.Assert;

public class HomeStep implements En {

    public HomeStep(
            HomePage homePage,
            ScenarioContext scenario
    ){

        Given("User is on homepage", () -> {
            homePage.navigateToHomePage();
        });

        When("User make a bit of click", () -> {
            homePage.goToRecrutement();
        });

        Then("User should see different link", () -> {
            homePage.verifyLink();
            homePage.saveScreenShotPNG();
            Assert.assertEquals(homePage.verifyLink(),true);
        });

        When("User click on **Fondation ManpowerGroup**", () -> {
            homePage.checkLink();
        });
        Then("User should access to secure page", () -> {
            homePage.verifyPageSecurityAccess();
            homePage.saveScreenShotPNG();
            Assert.assertEquals(homePage.verifyPageSecurityAccess(),true);
        });



    }

}
