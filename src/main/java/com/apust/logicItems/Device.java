package com.apust.logicItems;

import java.util.List;


public class Device {

    private String name;
    private Boolean policyBased;
    private List<String> policies;
    private String policyNameInInitialPlan;
    private Boolean hasSeveralPolicies;
    private String details;
    private Boolean checkedInInitialPlan;


    public Device() {

    }

    public Device(String name, Boolean policyBased, List<String> policies, String policyNameInInitialPlan, Boolean hasSeveralPolicies, String details, Boolean checkedInInitialPlan) {
        this.name = name;
        this.policyBased = policyBased;
        this.policies = policies;
        this.policyNameInInitialPlan = policyNameInInitialPlan;
        this.hasSeveralPolicies = hasSeveralPolicies;
        this.details = details;
        this.checkedInInitialPlan = checkedInInitialPlan;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getPolicyBased() {
        return policyBased;
    }

    public void setPolicyBased(Boolean policyBased) {
        this.policyBased = policyBased;
    }

    public List<String> getPolicies() {
        return policies;
    }

    public void setPolicies(List<String> policies) {
        this.policies = policies;
    }

    public String getPolicyNameInInitialPlan() {
        return policyNameInInitialPlan;
    }

    public void setPolicyNameInInitialPlan(String policyNameInInitialPlan) {
        this.policyNameInInitialPlan = policyNameInInitialPlan;
    }

    public Boolean getHasSeveralPolicies() {
        return hasSeveralPolicies;
    }

    public void setHasSeveralPolicies(Boolean hasSeveralPolicies) {
        this.hasSeveralPolicies = hasSeveralPolicies;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Boolean getCheckedInInitialPlan() {
        return checkedInInitialPlan;
    }

    public void setCheckedInInitialPlan(Boolean checkedInInitialPlan) {
        this.checkedInInitialPlan = checkedInInitialPlan;
    }

    @Override
    public String toString() {
        return  " \n { \n name = '" + name + '\'' +
                ", \n policyBased = " + policyBased +
                ", \n policies = " + policies +
                ", \n policyNameInInitialPlan = '" + policyNameInInitialPlan + '\'' +
                ", \n hasSeveralPolicies = " + hasSeveralPolicies +
                ", \n details = '" + details + '\'' +
                ", \n checkedInInitialPlan = " + checkedInInitialPlan + " \n } \n";
    }
}