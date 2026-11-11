
package com.oceanfleet.util;

import com.oceanfleet.model.Vessel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VesselUtilTest {

    private VesselUtil vesselUtil;
    private Vessel vessel1;
    private Vessel vessel2;
    private Vessel vessel3;

    @BeforeEach
    void setUp() {

        vesselUtil = new VesselUtil();

        vessel1 = new Vessel(
                "V001",
                "Sea King",
                25.5,
                "Cargo"
        );

        vessel2 = new Vessel(
                "V002",
                "Ocean Star",
                18.0,
                "Tanker"
        );

        vessel3 = new Vessel(
                "V003",
                "Wave Rider",
                22.3,
                "Cruise"
        );

        vesselUtil.addVesselPerformance(vessel1);
        vesselUtil.addVesselPerformance(vessel2);
        vesselUtil.addVesselPerformance(vessel3);
    }

    // UC2: Tests whether the vessel list is initialized.
    @Test
    void testVesselListInitiallyEmpty() {

        VesselUtil emptyUtil = new VesselUtil();

        assertNotNull(emptyUtil.getVesselList());
        assertTrue(emptyUtil.getVesselList().isEmpty());
    }

    // UC2: Tests adding a single vessel.
    @Test
    void testAddSingleVessel() {

        VesselUtil util = new VesselUtil();

        Vessel vessel = new Vessel(
                "V001",
                "Sea King",
                25.5,
                "Cargo"
        );

        util.addVesselPerformance(vessel);

        assertEquals(1, util.getVesselList().size());
        assertSame(vessel, util.getVesselList().get(0));
    }

    // UC2: Tests adding multiple vessels.
    @Test
    void testAddMultipleVessels() {

        assertEquals(3, vesselUtil.getVesselList().size());
    }

    // UC2: Tests whether insertion order is maintained.
    @Test
    void testInsertionOrderIsMaintained() {

        List<Vessel> vessels = vesselUtil.getVesselList();

        assertEquals("V001", vessels.get(0).getVesselId());
        assertEquals("V002", vessels.get(1).getVesselId());
        assertEquals("V003", vessels.get(2).getVesselId());
    }

    // UC2 and UC6: Tests setting a vessel list using a defensive copy.
    @Test
    void testSetVesselListCreatesDefensiveCopy() {

        List<Vessel> vessels = new ArrayList<>();

        vessels.add(new Vessel(
                "V004",
                "Blue Horizon",
                30.0,
                "Cargo"
        ));

        vesselUtil.setVesselList(vessels);

        assertEquals(1, vesselUtil.getVesselList().size());
        assertNotSame(vessels, vesselUtil.getVesselList());
    }

    // UC6: Tests whether modifying the original list affects internal storage.
    @Test
    void testModifyingOriginalListDoesNotAffectVesselUtil() {

        List<Vessel> vessels = new ArrayList<>();

        vessels.add(new Vessel(
                "V004",
                "Blue Horizon",
                30.0,
                "Cargo"
        ));

        vesselUtil.setVesselList(vessels);

        vessels.clear();

        assertEquals(1, vesselUtil.getVesselList().size());
    }

    // UC6: Tests whether modifying the returned list affects internal storage.
    @Test
    void testModifyingReturnedListDoesNotAffectInternalList() {

        List<Vessel> returnedList = vesselUtil.getVesselList();

        returnedList.clear();

        assertEquals(3, vesselUtil.getVesselList().size());
    }

    // UC3: Tests retrieving a vessel using an existing ID.
    @Test
    void testGetVesselByIdWhenVesselExists() {

        Vessel result = vesselUtil.getVesselById("V002");

        assertNotNull(result);
        assertSame(vessel2, result);
        assertEquals("Ocean Star", result.getVesselName());
    }

    // UC3: Tests retrieving the first vessel.
    @Test
    void testGetFirstVesselById() {

        Vessel result = vesselUtil.getVesselById("V001");

        assertSame(vessel1, result);
    }

    // UC3: Tests retrieving the last vessel.
    @Test
    void testGetLastVesselById() {

        Vessel result = vesselUtil.getVesselById("V003");

        assertSame(vessel3, result);
    }

    // UC3: Tests searching for a non-existing vessel.
    @Test
    void testGetVesselByIdWhenVesselDoesNotExist() {

        Vessel result = vesselUtil.getVesselById("V004");

        assertNull(result);
    }

    // UC3: Tests case-sensitive vessel ID searching.
    @Test
    void testVesselIdSearchIsCaseSensitive() {

        Vessel result = vesselUtil.getVesselById("v001");

        assertNull(result);
    }

    // UC3: Tests searching in an empty vessel list.
    @Test
    void testSearchInEmptyVesselList() {

        VesselUtil emptyVesselUtil = new VesselUtil();

        Vessel result = emptyVesselUtil.getVesselById("V001");

        assertNull(result);
    }
}
