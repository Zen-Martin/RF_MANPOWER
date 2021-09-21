package com.manpower.pageObjects;

import com.manpower.config.Configuration;
import com.manpower.config.Properties;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class ChBtpPage extends Page{

    private final static Configuration PROP  = Properties.Config;

    private final static String URI = PROP.getEnvironment()+"candidats/votre-metier/metiers-du-btp";

    @FindBy(xpath = "/html/body/div[1]/div/section/section/section/aside/div[8]/article/div[2]/ul/li[1]/a")
    private static WebElement chantierbtp;

    @FindBy(xpath = "/html/body/div[1]/div/section/section/section/section/div[1]/div/div/div")
    private WebElement chantiercontent;

    private String message="";

    public void goToChantierBTP(){
        get(URI);
        goToLinkpage(chantierbtp);
        shortUntil(visibilityOf(chantiercontent));
        message = chantiercontent.getText();
    }

    public boolean verifyChantierBTPContent(){

            System.out.println("\nmessage d'en-tete : "+message);

            if (!message.contains("Accès refusé")){
                System.out.println("\n L'element souhaité s'affiche : "
                        + "\n\n\tBug Corrigé !!!");
                return true;

            } else {
                System.out.println("\n L'element souhaité ne s'affiche pas : "
                        + "\n\n\tBug Non Corrigé !!!");
                return false;
            }

    }

}
