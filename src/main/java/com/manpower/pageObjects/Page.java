package com.manpower.pageObjects;

import com.manpower.config.Configuration;
import com.manpower.config.Properties;
import com.manpower.utils.ExcelManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.ByteArrayInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.function.Function;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class Page {

    @FindBy(id = "onetrust-reject-all-handler")
    private WebElement cookie;

    @FindBy(xpath = "/html/body")
    private WebElement page_content;


    /***
     *
     */
    protected WebDriver driver;
    protected JavascriptExecutor js;
    protected Actions action;
    /***
     * waiter
     */
    protected WebDriverWait wait;
    protected WebDriverWait shortWait;
    protected WebDriverWait middleWait;
    protected WebDriverWait longWait;

    protected Configuration config = Properties.Config;

    private ExcelManager excel;

    Page(){
        // Init
        driver = Properties.DriverManager.getDriver();
        PageFactory.initElements(driver, this);

        js = (JavascriptExecutor) driver;
        action = new Actions(driver);
        excel = new ExcelManager();

        //Waiter
        wait        = new WebDriverWait(driver, Duration.ofSeconds(4));
        shortWait   = new WebDriverWait(driver, Duration.ofSeconds(6));
        middleWait  = new WebDriverWait(driver, Duration.ofSeconds(12));
        longWait    = new WebDriverWait(driver, Duration.ofSeconds(15));

    }

    protected <V>boolean waitUntil(Function<? super WebDriver, V> isTrue){
        try{
            wait.until(isTrue);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    protected <V>boolean shortUntil(Function<? super WebDriver, V> isTrue){
        try{
            shortWait.until(isTrue);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    /***
     *
     * @param isTrue
     * @param <V>
     * @return
     */
    protected <V>boolean middleUntil(Function<? super WebDriver, V> isTrue){
        try{
            middleWait.until(isTrue);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /***
     *
     * @param isTrue
     * @param <V>
     * @return
     */
    protected <V>boolean longUntil(Function<? super WebDriver, V> isTrue){
        try{
            longWait.until(isTrue);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    /***
     *
     */
    protected void waitForLoadingPage( ){
        if(!longUntil(driver->js.executeScript("return document.readyState").equals("complete") || js.executeScript("return document.readyState").equals("interactive") )){
            throw new RuntimeException(driver.getCurrentUrl()+" not loaded");
        }
    }
    /***
     *
     * @param url
     */
    protected void get(String url){
        driver.get(url);
        waitForLoadingPage();
    }

    protected void clickOn(WebElement element){

        if( !shortUntil(visibilityOf(element)) ){
            // Logger
            throw new RuntimeException("Element not visible after click");
        }

        if( !shortUntil(elementToBeClickable(element))){
            // Logger
            throw new RuntimeException("Element not clickable");
        }
        element.click();
    }

    public void handleCookie(){
        if(shortUntil(visibilityOf(cookie))) clickOn(cookie);
    }

    public void manageAccess(){
        handleCookie();
        scroll(10);
        }

    public void goToLinkpage(WebElement link){
        driver.navigate().to(link.getAttribute("href"));
        waitForLoadingPage();
    }

    protected void scroll(int height){
        js.executeScript("window.scrollBy(0,"+height+")");
    }

    public void checkErrorpage(WebElement link, String access_message, String access_statut) {
        scroll((link.getLocation().getY()-20));
        String linked = link.getAttribute("href");
        try {
            HttpURLConnection c = (HttpURLConnection) new URL(linked).openConnection();
            c.setRequestMethod("HEAD");
            c.connect();

            int r = c.getResponseCode();

            String answ = r + "";

            if (answ.startsWith("4")) {
                access_statut = "FAIL";
            } else {
                access_statut = "PASS";
            }

        } catch (Exception e){
            access_statut = "FAIL";
            System.out.println("url inaccessible");

        }
        if(access_statut.equals("PASS"))
        {
            goToLinkpage(link);
            shortUntil(visibilityOf(page_content));
            access_message = page_content.getText();
        }

    }

    @Attachment(value = "screenshot", type = "image/png")
    public static void saveScreenShotPNG(){
        Allure.addAttachment("screenshot", new ByteArrayInputStream(((TakesScreenshot) Properties.DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES)));
    }
}
