package com.oceanfleet.util;

import com.oceanfleet.model.Vessel;

import java.util.ArrayList;
import java.util.List;

public class VesselUtil {

    private List<Vessel> vesselList;

    public VesselUtil() {
        this.vesselList = new ArrayList<>();
    }

    public List<Vessel> getVesselList() {
        return this.vesselList;
    }

    public void setVesselList(List<Vessel> vesselList) {
        this.vesselList = vesselList;
    }

    public void addVesselPerformance(Vessel vessel) {
        this.vesselList.add(vessel);
    }

    public Vessel getVesselById(String vesselId) {
        if (vesselId == null || this.vesselList == null) {
            return null;
        }
        for (int idx = 0; idx < this.vesselList.size(); idx++) {
            Vessel current = this.vesselList.get(idx);
            if (current != null && vesselId.equals(current.getVesselId())) {
                return current;
            }
        }
        return null;
    }

    public List<Vessel> getHighPerformanceVessels() {
        List<Vessel> topPerformers = new ArrayList<>();
        if (this.vesselList == null || this.vesselList.isEmpty()) {
            return topPerformers;
        }

        double peakSpeed = this.vesselList.get(0).getAverageSpeed();
        for (int i = 1; i < this.vesselList.size(); i++) {
            double currentSpeed = this.vesselList.get(i).getAverageSpeed();
            if (currentSpeed > peakSpeed) {
                peakSpeed = currentSpeed;
            }
        }

        for (int i = 0; i < this.vesselList.size(); i++) {
            Vessel v = this.vesselList.get(i);
            if (Double.compare(v.getAverageSpeed(), peakSpeed) == 0) {
                topPerformers.add(v);
            }
        }

        return topPerformers;
    }
}
