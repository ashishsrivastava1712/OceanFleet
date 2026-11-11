
package com.oceanfleet.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VesselTest {

    @Test
    void testNoArgumentConstructor() {

        Vessel vessel = new Vessel();

        assertNull(vessel.getVesselId());
        assertNull(vessel.getVesselName());
        assertEquals(0.0, vessel.getAverageSpeed());
        assertNull(vessel.getVesselType());
    }

    @Test
    void testParameterizedConstructor() {

        Vessel vessel = new Vessel(
                "V001",
                "Sea King",
                25.5,
                "Cargo"
        );

        assertEquals("V001", vessel.getVesselId());
        assertEquals("Sea King", vessel.getVesselName());
        assertEquals(25.5, vessel.getAverageSpeed());
        assertEquals("Cargo", vessel.getVesselType());
    }

    @Test
    void testSettersAndGetters() {

        Vessel vessel = new Vessel();

        vessel.setVesselId("V002");
        vessel.setVesselName("Ocean Star");
        vessel.setAverageSpeed(18.0);
        vessel.setVesselType("Tanker");

        assertEquals("V002", vessel.getVesselId());
        assertEquals("Ocean Star", vessel.getVesselName());
        assertEquals(18.0, vessel.getAverageSpeed());
        assertEquals("Tanker", vessel.getVesselType());
    }

    @Test
    void testUpdateExistingVesselDetails() {

        Vessel vessel = new Vessel(
                "V001",
                "Sea King",
                25.5,
                "Cargo"
        );

        vessel.setVesselName("Sea King Updated");
        vessel.setAverageSpeed(30.0);

        assertEquals("Sea King Updated", vessel.getVesselName());
        assertEquals(30.0, vessel.getAverageSpeed());
    }
}
