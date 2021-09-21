package com.manpower.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class CandidatPage extends Page{

    private final String init_size = "(720, 390)";

    private String actual_size = "";

    @FindBy(xpath = "/html/body/div[1]/div/section/section/section/section")
    private WebElement section;

    @FindBy(xpath = "/html/body/div[1]/div/section/section/section/section/div[1]/div/div/ol/div/li[2]/a")
    private WebElement cons_perso_svr_pl;

    @FindBy(xpath = "/html/body/div[1]/div/section/section/section/section/div[1]/div/div/ol/div/li[3]/a")
    private WebElement deb_care_svr_pl;

    @FindBy(linkText = "Plan du site")
    private WebElement footer2plansite;

    @FindBy(xpath = "/html/body/div[1]/div/section/section/section/section/div[1]/div/div/div[2]/ul/li[1]/ul/li[3]/a")
    private WebElement cand_conseils_epl;


    public void goToCandidatPage() {
        goToLinkpage(footer2plansite);
        shortUntil(visibilityOf(cand_conseils_epl));
        goToLinkpage(cand_conseils_epl);
        shortUntil(visibilityOf(cons_perso_svr_pl));
    }

    public void goToDebCarePage() {
        goToLinkpage(cons_perso_svr_pl);
        shortUntil(visibilityOf(deb_care_svr_pl));
        goToLinkpage(deb_care_svr_pl);
        driver.switchTo().defaultContent();
        actual_size = section.getSize().toString();
        scroll(500);
    }

    public boolean verifyEmptyContent(){

            System.out.println("\ntaille de l'élément par défaut : "+init_size
                    + "\ntaille de l'élément actuel : "+actual_size);

            if (!init_size.equals(actual_size)) {

                System.out.println("\n Il y'a eu ajout de contenus : "
                        + "\n\n\tBug Corrigé !!!");
                return true;

            } else {

                System.out.println("\n Aucun ajout de contenus : "
                        + "\n\n\tBug Non Corrigé !!!");
                return false;
            }

    }



}
