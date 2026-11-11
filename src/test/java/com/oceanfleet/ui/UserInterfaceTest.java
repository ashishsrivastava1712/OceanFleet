
package com.oceanfleet.ui;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class UserInterfaceTest {

    private String runApplication(String input) {

        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;

        ByteArrayInputStream testInput =
                new ByteArrayInputStream(
                        input.getBytes(StandardCharsets.UTF_8)
                );

        ByteArrayOutputStream testOutput =
                new ByteArrayOutputStream();

        try {

            System.setIn(testInput);

            System.setOut(
                    new PrintStream(testOutput, true, StandardCharsets.UTF_8)
            );

            UserInterface.main(new String[]{});

            return testOutput.toString(StandardCharsets.UTF_8);

        } finally {

            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }

    @Test
    void testApplicationWithExistingVesselId() {

        String input = """
                2
                V001:Sea King:25.5:Cargo
                V002:Ocean Star:18.0:Tanker
                V001
                """;

        String output = runApplication(input);

        assertTrue(output.contains(
                "V001 | Sea King | Cargo | 25.5 knots"
        ));

        assertTrue(output.contains(
                "High performance vessels are"
        ));
    }

    @Test
    void testApplicationWithNonExistingVesselId() {

        String input = """
                2
                V001:Sea King:25.5:Cargo
                V002:Ocean Star:18.0:Tanker
                V003
                """;

        String output = runApplication(input);

        assertTrue(output.contains(
                "Vessel Id V003 not found."
        ));
    }

    @Test
    void testApplicationDisplaysTiedHighPerformanceVessels() {

        String input = """
                3
                V001:Sea King:25.5:Cargo
                V002:Ocean Star:18.0:Tanker
                V003:Wave Rider:25.5:Cruise
                V002
                """;

        String output = runApplication(input);

        assertTrue(output.contains(
                "V001 | Sea King | Cargo | 25.5 knots"
        ));

        assertTrue(output.contains(
                "V003 | Wave Rider | Cruise | 25.5 knots"
        ));
    }

    @Test
    void testApplicationDisplaysCorrectVesselTypeAndSpeed() {

        String input = """
                1
                V001:Sea King:30.0:Cargo
                V001
                """;

        String output = runApplication(input);

        assertTrue(output.contains(
                "V001 | Sea King | Cargo | 30.0 knots"
        ));
    }
}
