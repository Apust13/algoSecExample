package com.apust.testCases;

import com.apust.pages.Application;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import com.apust.utils.ExtendedSoftAssert;


import java.util.concurrent.TimeUnit;

public class MainTest {

    protected RemoteWebDriver driver;
    protected WebDriverWait wait;
    protected ExtendedSoftAssert softAssert;

    private final static String chromedriverPathWin = (System.getProperty("user.dir") + "\\src\\test\\resources\\chromedriver.exe")
            .replaceAll("\\\\|/", "\\" + System.getProperty("file.separator"));

    private final static String geckodriverPathWin = (System.getProperty("user.dir") + "\\src\\test\\resources\\geckodriver.exe")
            .replaceAll("\\\\|/", "\\" + System.getProperty("file.separator"));


    @Parameters({"browser", "url"})
    @BeforeMethod(alwaysRun = true)
    public void setup(@Optional("firefox") String browser, @Optional("https://ya.ru") String url) {

        if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", geckodriverPathWin);
            driver = new FirefoxDriver();

        } else if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", chromedriverPathWin);
            driver = new ChromeDriver();
        }

        softAssert = new ExtendedSoftAssert(driver);

        driver.navigate().to(url);
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Application.timeOutGlobal, TimeUnit.SECONDS);
        driver.navigate().to(url);
    }


    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        driver = null;
    }
}
