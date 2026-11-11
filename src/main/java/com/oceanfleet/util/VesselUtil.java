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
}
