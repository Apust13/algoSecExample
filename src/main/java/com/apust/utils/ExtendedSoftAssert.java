package com.apust.utils;

import org.openqa.selenium.WebDriver;
import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;

/**
 * Created by APUST on 12/25/2017.
 */
public class ExtendedSoftAssert extends SoftAssert {

    private WebDriver driver;


    public ExtendedSoftAssert(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void onAssertFailure(IAssert<?> assertCommand, AssertionError ex) {
        super.onAssertFailure(assertCommand, ex);
        takeScreenshot(assertCommand.getMessage(), ex.getClass().getSimpleName());
        // Here can be placed any logger
    }


    public void takeScreenshot(String message, String clName) {
        // Here can be any implementation of taking screenshot method

    }
}

