package com.apust.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage extends Application {

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "")
    private WebElement someLoginInput;

    @FindBy(xpath = "")
    private By somePasswordInput;

    @FindBy(xpath = "")
    private By someSubmitButton;



    public void enterLogin(String login){
        enterTextInInput(someLoginInput, login);
    }

    public void enterPassword(String password){
        enterTextInInput(somePasswordInput, password);
    }

    public void clickOnSubmitButton(){
        click(someSubmitButton);
    }








}
