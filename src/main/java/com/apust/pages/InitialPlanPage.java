package com.apust.pages;

import com.apust.logicItems.Device;
import com.apust.logicItems.InitialPlan;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class InitialPlanPage extends Application {


    public InitialPlanPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private By policyDeviceCheckBox = By.xpath("");

    private By arrowOfDevicePolicyListItems = By.xpath("");

    private By deviceCheckBoxes = By.xpath("");

    private By deviceNames = By.xpath(/*get all rows with devices/policy items , depends on implementation */"");

    private By detailsValues  = By.xpath("");

    // ??
    private WebElement mixTraffic;


    public void expandListOfDevicePolicy(){
        /*The values are depends on implementation on Web Page*/
//        if(!getAttributeBy(arrowOfDevicePolicyListItems, "state").equals("open")){
//            click(arrowOfDevicePolicyListItems);
//        }
    }

    public int getCountOfDevicePolicyItems(){
        return findEls(deviceNames).size();
    }

    public List<Device> makeLogicItemFromUI(){
        List<Device> result = new ArrayList<>();
        String pattern = "FireFlow_SG_-_";

        List<String> names = getTextFromWebElementsByListBy(deviceNames);
        List<Boolean> policyBased = isTextFromListContainsPattern(names, pattern);

//        need to clarify HTML code structure for implementation this method
        List<List<String>> policies = Arrays.asList(Arrays.asList("", ""));

//        need to clarify HTML code structure for implementation this method
        List <String> policyNameInInitialPlan = Arrays.asList("", "");

        List<Boolean> hasSeveralPolicies = isPatternRepeatedInEachElementOfList(names, pattern);
        List<String> details = getTextFromWebElementsByListBy(detailsValues);
        List<Boolean> checkedInInitialPlan = isCheckboxesChecked(deviceCheckBoxes);

        for(int i = 0; i < names.size(); i++){
            result.add(new Device(
                    names.get(i),
                    policyBased.get(i),
                    policies.get(i),
                    policyNameInInitialPlan.get(i),
                    hasSeveralPolicies.get(i),
                    details.get(i),
                    checkedInInitialPlan.get(i)

            ));
        }

        return result;
    }

    public InitialPlan makeLogicMOCK(){
        List<Device> result = new ArrayList<>();

        List<String> names = getTextFromWebElementsByListByMOCK(deviceNames);
        List<Boolean> policyBased = isTextFromListContainsPatternMOCK(names, "");

        List<List<String>> policies = Arrays.asList(Arrays.asList("FireFlow SG - 15 instances"), Arrays.asList("Test data 2"));
        List <String> policyNameInInitialPlan = Arrays.asList("AWS_automation | sa-east-1 | vpc-c7c74aa3 | FireFlow SG - 15 instances", "test data 2");

        List<Boolean> hasSeveralPolicies = isPatternRepeatedInEachElementOfListMOCK(names, "");
        List<String> details = getTextFromWebElementsByListByMOCK2(detailsValues);

        List<Boolean> checkedInInitialPlan = isCheckboxesCheckedMOCK(deviceCheckBoxes);

        for(int i = 0; i < names.size(); i++){
            result.add(new Device(
                    names.get(i),
                    policyBased.get(i),
                    policies.get(i),
                    policyNameInInitialPlan.get(i),
                    hasSeveralPolicies.get(i),
                    details.get(i),
                    checkedInInitialPlan.get(i)

            ));
        }
         return new InitialPlan(result, false);
    }

}
