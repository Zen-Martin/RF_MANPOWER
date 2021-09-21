package com.manpower.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class AlternancePage extends Page{

    @FindBy(linkText = "Plan du site")
    private WebElement footer2plansite;

    @FindBy(xpath = "/html/body/div[1]/div/section/section/section/section/div[1]/div/div/div[2]/ul/li[1]/ul/li[1]/ul/li[4]/ul/li[4]/a")
    private WebElement cand_alternance;

    @FindBy(xpath = "/html/body/div[1]/div/section/section/section/section/div[1]/div/div/ol/div/li[2]/a")
    private WebElement cons_offre;


    private String link="";

    public void goToAlternancePage() {
        goToLinkpage(footer2plansite);
        shortUntil(visibilityOf(cand_alternance));
        goToLinkpage(cand_alternance);
        shortUntil(visibilityOf(cons_offre));
        goToLinkpage(cons_offre);
        link = driver.getCurrentUrl();
    }

    public boolean verifyAlternanceFilter(){

            System.out.println("\nurl de page "+link);

            if (!link.equals("https://www.manpower.fr/offre-emploi")){

                System.out.println("\n L'element renvoit vers un contenu specifique : "
                        + "\n\n\tBug Corrigé !!!");
                return true;

            } else {

                System.out.println("\n L'element renvoit vers un contenu general  : "
                        + "\n\n\tBug Non Corrigé !!!");
                return false;
            }

    }




}
