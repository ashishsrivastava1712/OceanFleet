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
}
