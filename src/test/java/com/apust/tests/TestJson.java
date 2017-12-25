package com.apust.tests;

import com.apust.logicItems.Device;
import com.apust.pages.InitialPlanPage;
import com.apust.pages.LoginPage;
import com.apust.logicItems.InitialPlan;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.apust.testCases.MainTest;
import com.apust.utils.JSONGson;

import org.unitils.reflectionassert.ReflectionAssert;
import org.unitils.reflectionassert.ReflectionComparatorMode;


import java.util.List;

public class TestJson extends MainTest {

    private LoginPage loginPage;
    private InitialPlanPage initialPlanPage;
    private String jsonFromFile;
    private InitialPlan initialPlan;
    private InitialPlan itemsFromWeb;

    private final String resultsRepo = "./src/test/resources/expRes/";

    @DataProvider
    public Object[][] getExpResultsData() {
        Object[][] data = new Object[][]{
                {"user1", "pass1", "expectedResult.json"},
                {"user2", "pass2", "expectedResult2.json"}
        };
        return data;
    }


    @Parameters({"login", "password", "expResult"})
    @Test(dataProvider = "getExpResultsData")
    public void testUI(@Optional("userLogin") String login, @Optional("userPass") String password, @Optional("") String expResult) {

        setupData(expResult);

        login(login, password);

        goToInitialPlanPage();

        itemsFromWeb = makeLogicItemFromWeb();

        ReflectionAssert.assertReflectionEquals("The logic items are not equal", initialPlan, itemsFromWeb, ReflectionComparatorMode.IGNORE_DEFAULTS);
    }

    private InitialPlan makeLogicItemFromWeb() {
        initialPlanPage.expandListOfDevicePolicy();
        return initialPlanPage.makeLogicMOCK();
    }

    private void setupData(String expData) {
        jsonFromFile = JSONGson.getJsonFromFile(resultsRepo + expData);
        initialPlan = (InitialPlan) JSONGson.readJsonToClass(jsonFromFile, InitialPlan.class);
    }

    private void goToInitialPlanPage() {
//        loginPage.clickOnInitialPlanLink();
        initialPlanPage = new InitialPlanPage(driver);
//        initialPlanPage.waitUntilPageLoaded();
    }

    private void login(String login, String password) {



//        loginPage = new LoginPage(driver);
//        loginPage.waitUntilPageLoaded();
//
//        loginPage.enterLogin(login);
//        loginPage.enterPassword(password);
//        loginPage.clickOnSubmitButton();
    }
}
