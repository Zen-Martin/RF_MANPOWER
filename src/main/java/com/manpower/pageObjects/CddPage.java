package com.manpower.pageObjects;

import com.manpower.config.Configuration;
import com.manpower.config.Properties;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CddPage extends Page{

    @FindBy(xpath = "/html/body/div[1]/div/section/section/section/section/div[1]/div/div/ol/div/li[2]/div[3]/p[4]/a")
    private WebElement detailcdd_cdi;

    private String access_message="";

    private String access_statut="";

    private String link = "";

    private final static Configuration PROP  = Properties.Config;

    private final static String URI = PROP.getEnvironment()+"candidats/trouver-un-emploi/quel-contrat-pour-vous/cdi-cdd";

    public void goToCDD() {
        get(URI);
    }

    public void checkLink() {
        link = detailcdd_cdi.getAttribute("href");
        checkErrorpage(detailcdd_cdi, access_message, access_statut);
    }

    public boolean verifyMoreCDDContent(){

            System.out.println("\nurl de page "+link
                    + "\naccess au contenu de la page : "+access_statut
                    + "\ncontenu de la page : "+access_message);

            if ((link.contains("https"))&&(access_statut.equals("PASS"))&&(!access_message.contains("ERROR"))) {

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
