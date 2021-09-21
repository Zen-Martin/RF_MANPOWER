package com.manpower.pageObjects;

import com.manpower.config.Configuration;
import com.manpower.config.Properties;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;


public class HomePage extends Page {

    @FindBy(linkText = "Démarche RSE")
    private WebElement footerdmrse;

    @FindBy(linkText = "Le Hub Recrutement et Flexibilité")
    private WebElement footerhubrecrut;

    @FindBy(linkText = "Fondation ManpowerGroup")
    private WebElement footerfondmanpow;

    private final static Configuration PROP  = Properties.Config;

    private String link_1="";

    private String link_2="";

    private String access_message="";

    private String access_statut="";

    private String link = "";

    public HomePage() {
    }

    public void navigateToHomePage() {
        get(PROP.getEnvironment());
        manageAccess();
    }

    public void goToRecrutement() {
        link_1 = driver.getCurrentUrl();
        goToLinkpage(footerdmrse);
        shortUntil(visibilityOf(footerhubrecrut));
        goToLinkpage(footerhubrecrut);
        link_2 = driver.getCurrentUrl();
    }

    public boolean verifyLink(){

            System.out.println("\nlien de depart : "+link_1
                    + "\nlien d'arrivee : "+link_2);

            if (!link_1.equals(link_2)) {

                System.out.println("\n les liens sont différents : "
                        + "\n\n\tBug Corrigé !!!");
                return true;

            } else {

                System.out.println("\n les liens sont identiques : "
                        + "\n\n\tBug Non Corrigé !!!");
                return false;
            }

    }

    public void checkLink() {
        link = footerfondmanpow.getAttribute("href");
        checkErrorpage(footerfondmanpow, access_message, access_statut);
    }

    public boolean verifyPageSecurityAccess(){

            System.out.println("\nurl de page "+link
                    + "\naccess au contenu de la page : "+access_statut);

            if ((link.contains("https"))&&(access_statut.equals("PASS"))) {

                System.out.println("\n Probleme de securite et d'affichage de la page resolu : "
                        + "\n\n\tBug Corrigé !!!");
                return true;

            } else {
                System.out.println("\n Probleme de securite et d'affichage de la page non-resolu : "
                        + "\n\n\tBug Non Corrigé !!!");
                return false;
            }


    }

}
