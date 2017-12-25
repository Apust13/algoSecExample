package com.apust.logicItems;

import java.util.List;

public class InitialPlan {


    private List<Device> devices;
    private Boolean mixTraffic;

    public InitialPlan(List<Device> devices, Boolean mixTraffic) {
        this.devices = devices;
        this.mixTraffic = mixTraffic;
    }


    public InitialPlan() {

    }

    @Override
    public String toString() {
        return " \n mixTraffic = " + mixTraffic +
                " \n devices/policy items = " + devices;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public Device getDeviceByIndex(int index) {
        return devices.get(index);
    }

    public String getNameOfDevice(int index) {
        return devices.get(index).getName();
    }

    public String getDetailsOfDevice(int index) {
        return devices.get(index).getDetails();
    }

    public String getPolicyNameInInitialPlanOfDevice(int index) {
        return devices.get(index).getPolicyNameInInitialPlan();
    }





    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    public Boolean getMixTraffic() {
        return mixTraffic;
    }

    public void setMixTraffic(Boolean mixTraffic) {
        this.mixTraffic = mixTraffic;
    }
}
