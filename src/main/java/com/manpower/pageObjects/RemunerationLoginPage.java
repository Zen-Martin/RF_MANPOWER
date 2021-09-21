package com.manpower.pageObjects;

import com.manpower.config.Configuration;
import com.manpower.config.Properties;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class RemunerationLoginPage extends Page{

    private String message;

    private final static Configuration PROP  = Properties.Config;

    @FindBy(linkText = "Les avantages de l'intérim")
    private WebElement footerintavantage;

    @FindBy(linkText = "Mes infos pratiques")
    private WebElement infopratq;

    @FindBy(linkText = "Droits à Pôle emploi")
    private WebElement polempl;

    @FindBy(xpath = "/html/body/div[1]/div/section/section/section/section/div[1]/div/div/ol/div/li[4]/div[2]/article/div[2]/div[2]/div/p[2]/a")
    private WebElement myrenum;

    @FindBy(xpath = "/html/body/div[1]/div/section/section/section/section/div/div[1]/div/div[1]/form/fieldset/div[1]/div[1]/div/input")
    private WebElement myrenum_email;

    @FindBy(xpath = "/html/body/div[1]/div/section/section/section/section/div/div[1]/div/div[1]/form/fieldset/div[1]/div[2]/div/input")
    private WebElement myrenum_pwd;

    @FindBy(xpath = "/html/body/div/div/section/section/section/section[2]/div/div")
    private WebElement myrenum_content;


    public void goToRemunerationLoginPage(){
        goToLinkpage(footerintavantage);
        shortUntil(visibilityOf(infopratq));
        goToLinkpage(infopratq);
        shortUntil(visibilityOf(polempl));
        goToLinkpage(polempl);
        shortUntil(visibilityOf(myrenum));
        goToLinkpage(myrenum);
        shortUntil(visibilityOf(myrenum_email));
        myrenum_email.sendKeys(PROP.getEmail());
        myrenum_pwd.sendKeys(PROP.getPwd());
        myrenum_pwd.sendKeys(Keys.ENTER);
        shortUntil(visibilityOf(myrenum_content));
        message = myrenum_content.getText();
    }

    public boolean verifyRemunerationLoginContent(){

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
