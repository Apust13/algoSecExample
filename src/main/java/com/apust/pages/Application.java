package com.apust.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;


public abstract class Application {

    protected WebDriver driver;
    public static final int timeOutGlobal = 25;
    public static final int timeOutGlobalShort = 10;


    private By initialPlanMenuLink = By.xpath("");

    public void clickOnInitialPlanLink() {
        waitAndClick(initialPlanMenuLink, 2);
    }


    public void click(By by) {
        click(findEl(by, 2));
    }

    public void click(By by, int timeout) {
        click(findEl(by, timeout));
    }

    public void waitAndClick(By by, int timeout) {
        waitUntilElementClickableBy(by, timeout);
        click(findEl(by));
    }


    public WebElement findEl(By by, int timeout) {
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
        WebElement el = findEl(by);
        driver.manage().timeouts().implicitlyWait(timeOutGlobal, TimeUnit.SECONDS);
        return el;
    }

    public WebElement findEl(By by, int timeoutBefore, int timeoutAfter) {
        driver.manage().timeouts().implicitlyWait(timeoutBefore, TimeUnit.SECONDS);
        WebElement el = findEl(by);
        driver.manage().timeouts().implicitlyWait(timeoutAfter, TimeUnit.SECONDS);
        return el;
    }

    public WebElement findEl(By by) {
        //  some logger with message (by, "Try to FIND element located: " + by);
        return driver.findElement(by);
    }

    public List<WebElement> findEls(By by) {
        //  some logger with message ("Try to FIND elements located: " + by);
        return driver.findElements(by);
    }

    public List<WebElement> findEls(By by, int timeSeconds) {
        driver.manage().timeouts().implicitlyWait(timeSeconds, TimeUnit.SECONDS);
        List<WebElement> result = driver.findElements(by);
        driver.manage().timeouts().implicitlyWait(timeOutGlobal, TimeUnit.SECONDS);
        return result;
    }

    public void click(WebElement element) {
        click(element, 4);
    }

    public void click(WebElement element, int timeOut) {
        try {
            driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
            element.click();
        } finally {
            driver.manage().timeouts().implicitlyWait(timeOutGlobal, TimeUnit.SECONDS);
        }
    }

    public void clickActions(By by) {
        //  some logger with message ( "Trying to click on locator via Actions : " + by );
        Actions actions = new Actions(driver);
        actions.click(findEl(by)).perform();
    }

    public void clickJS(By by) {
        //  some logger with message ( "Trying to click on locator via JS");
        WebElement element = findEl(by, 10);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public void back() {
        //  some logger with message ("Navigate back");
        driver.navigate().back();
    }

    public void clickOnSomeElementsInListBy(By by, int count) {
        int i = 1;
        for (WebElement el : findEls(by)) {
            waitUntilElementClickable(el, 1);
            click(el);
            if (i >= count) {
                break;
            }
            i++;
        }
    }

    public void waitUntilElementClickableBy(By by, int timeout) {
        try {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            (new WebDriverWait(driver, timeout)).until(ExpectedConditions.elementToBeClickable(findEl(by, timeout)));
        } catch (TimeoutException e) {
            //  some logger with message (by, "Element is not clickable!");
        } finally {
            driver.manage().timeouts().implicitlyWait(timeOutGlobal, TimeUnit.SECONDS);
        }

    }

    public void waitUntilElementClickable(WebElement element, int timeout) {
        try {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            (new WebDriverWait(driver, timeout)).until(ExpectedConditions.elementToBeClickable(element));
        } catch (TimeoutException e) {
            //  some logger with message (by, "Element is not clickable!");
        } finally {
            driver.manage().timeouts().implicitlyWait(timeOutGlobal, TimeUnit.SECONDS);
        }

    }

    public boolean isAlertPresent() {
        try {
            new WebDriverWait(driver, 2).until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void acceptAlert() {
        if (isAlertPresent()) {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        }
    }

    public void cancelAlert() {
        if (isAlertPresent()) {
            Alert alert = driver.switchTo().alert();
            alert.dismiss();
            driver.switchTo().defaultContent();
        }
    }

    public void doubleClick(WebElement onElement) {
        Actions act = new Actions(driver);
        act.doubleClick(onElement);
        act.perform();
    }

    public void waitUntilPageLoaded() {
        try {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            new WebDriverWait(driver, 30).until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));

        } catch (TimeoutException e) {

        } finally {
            driver.manage().timeouts().implicitlyWait(timeOutGlobalShort, TimeUnit.SECONDS);
        }
    }

    public void enterTextInInput(By by, String str) {
        findEl(by).sendKeys(str);
    }

    public void enterTextInInput(WebElement el, String str) {
        el.sendKeys(str);
    }

    public String getTextBy(By by) {
        return findEl(by).getText();
    }

    public void waitUntilAttributeToBeNotEmptyBy(By by, String attribute) {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        try {
            new WebDriverWait(driver, 10).until(ExpectedConditions.attributeToBeNotEmpty(findEl(by), attribute));
        } catch (Exception e) {

        } finally {
            driver.manage().timeouts().implicitlyWait(timeOutGlobal, TimeUnit.SECONDS);
        }
    }

    public String getAttributeBy(By by, String att) {
        waitUntilAttributeToBeNotEmptyBy(by, att);
        return findEl(by).getAttribute(att);
    }

    public boolean isCheckboxChecked(WebElement element) {
        try {
            return element.getAttribute("checked").equals("true");
        } catch (NullPointerException e) {
            return false;
        }
    }

    public boolean isCheckboxChecked(By by) {
        try {
            return findEl(by).getAttribute("checked").equals("true");
        } catch (NullPointerException e) {
            return false;
        }
    }

    public List<String> getTextFromWebElementsByListBy(By by) {
        ArrayList<String> result = new ArrayList<>();
        for (WebElement el : findEls(by, 4)) {
            result.add(el.getText().trim());
        }
        return result;
    }


    public List<Boolean> isTextFromListContainsPattern(List<String> list, String pattern) {
        ArrayList<Boolean> result = new ArrayList<>();
        for (String str : list) {
            result.add(str.contains(pattern));
        }
        return result;
    }


    public boolean isPatternRepeatInArray(String[] array, String pattern) {
        int i = 0;
        for (String str : array) {
            if (str.contains(pattern)) {
                i++;
            }
        }
        if (i > 1) {
            return true;
        } else {
            return false;
        }
    }

    public List<Boolean> isPatternRepeatedInEachElementOfList(List<String> list, String pattern) {
        ArrayList<Boolean> result = new ArrayList<>();
        for (String str : list) {
            String[] array = str.split("/");
            result.add(isPatternRepeatInArray(array, pattern));
        }
        return result;
    }


    public List<Boolean> isCheckboxesChecked(By by) {
        ArrayList<Boolean> result = new ArrayList<>();
        for (WebElement el : findEls(by, 4)) {
            try {
                result.add(el.getAttribute("checked").equals("true"));
            } catch (NullPointerException e) {
                result.add(false);
            }
        }
        return result;
    }


// ********************* MOCK implementation ****************************


    /*
     The first item in device/policy is correct, second is wrong to data from - expectedResult.json

     The both items are correct to data from - expectedResult2.json

     */


    public List<String> getTextFromWebElementsByListByMOCK(By by) {
        return Arrays.asList("FireFlow_SG_-_15_instances/FireFlow_SG_-13_instances/acl-d631ebb2", "Test name 2");
    }

    public List<Boolean> isTextFromListContainsPatternMOCK(List<String> list, String pattern) {
        return Arrays.asList(true, false);
    }


    public List<Boolean> isPatternRepeatedInEachElementOfListMOCK(List<String> list, String pattern) {
        return Arrays.asList(true, true);
    }

    public List<Boolean> isCheckboxesCheckedMOCK(By by) {
        return Arrays.asList(true, false);
    }

    public List<String> getTextFromWebElementsByListByMOCK2(By by) {
        return Arrays.asList("Blocked", "Test data 2");
    }


}
