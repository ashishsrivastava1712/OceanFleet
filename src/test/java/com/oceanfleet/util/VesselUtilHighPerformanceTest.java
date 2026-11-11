
package com.oceanfleet.util;

import com.oceanfleet.model.Vessel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VesselUtilHighPerformanceTest {

    private VesselUtil vesselUtil;

    @BeforeEach
    void setUp() {

        vesselUtil = new VesselUtil();

        vesselUtil.addVesselPerformance(
                new Vessel("V001", "Sea King", 25.5, "Cargo")
        );

        vesselUtil.addVesselPerformance(
                new Vessel("V002", "Ocean Star", 18.0, "Tanker")
        );

        vesselUtil.addVesselPerformance(
                new Vessel("V003", "Wave Rider", 22.3, "Cruise")
        );
    }

    @Test
    void testGetSingleHighPerformanceVessel() {

        List<Vessel> result =
                vesselUtil.getHighPerformanceVessels();

        assertEquals(1, result.size());
        assertEquals("V001", result.get(0).getVesselId());
        assertEquals(25.5, result.get(0).getAverageSpeed());
    }

    @Test
    void testGetMultipleHighPerformanceVessels() {

        vesselUtil.addVesselPerformance(
                new Vessel("V004", "Blue Horizon", 25.5, "Cruise")
        );

        List<Vessel> result =
                vesselUtil.getHighPerformanceVessels();

        assertEquals(2, result.size());

        assertEquals("V001", result.get(0).getVesselId());
        assertEquals("V004", result.get(1).getVesselId());
    }

    @Test
    void testHighestSpeedIsAtLastPosition() {

        vesselUtil.addVesselPerformance(
                new Vessel("V004", "Blue Horizon", 30.0, "Cargo")
        );

        List<Vessel> result =
                vesselUtil.getHighPerformanceVessels();

        assertEquals(1, result.size());
        assertEquals("V004", result.get(0).getVesselId());
    }

    @Test
    void testAllVesselsHaveSameSpeed() {

        VesselUtil util = new VesselUtil();

        util.addVesselPerformance(
                new Vessel("V001", "Sea King", 20.0, "Cargo")
        );

        util.addVesselPerformance(
                new Vessel("V002", "Ocean Star", 20.0, "Tanker")
        );

        util.addVesselPerformance(
                new Vessel("V003", "Wave Rider", 20.0, "Cruise")
        );

        List<Vessel> result = util.getHighPerformanceVessels();

        assertEquals(3, result.size());
    }

    @Test
    void testGetHighPerformanceVesselsFromEmptyList() {

        VesselUtil emptyUtil = new VesselUtil();

        List<Vessel> result =
                emptyUtil.getHighPerformanceVessels();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testResultContainsOriginalVesselObjects() {

        List<Vessel> result =
                vesselUtil.getHighPerformanceVessels();

        assertSame(
                vesselUtil.getVesselList().get(0),
                result.get(0)
        );
    }
}
