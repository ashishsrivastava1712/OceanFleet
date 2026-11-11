package com.oceanfleet.ui;

import com.oceanfleet.model.Vessel;
import com.oceanfleet.util.VesselUtil;

import java.util.List;
import java.util.Scanner;

public class UserInterface {

    public static void main(String[] args) {
        new UserInterface().run();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        VesselUtil tracker = new VesselUtil();

        recordVessels(scanner, tracker);
        lookupVesselById(scanner, tracker);
        showTopPerformers(tracker);
    }

    private void recordVessels(Scanner scanner, VesselUtil tracker) {
        System.out.println("Enter the number of vessels to be added");
        int count = Integer.parseInt(scanner.nextLine().trim());

        for (int index = 0; index < count; index++) {
            System.out.println("Enter vessel details");
            String rawLine = scanner.nextLine();
            String[] tokens = rawLine.split(":");

            String id = tokens[0];
            String name = tokens[1];
            double speed = Double.parseDouble(tokens[2]);
            String type = tokens[3];

            Vessel ship = new Vessel(id, name, speed, type);
            tracker.addVesselPerformance(ship);
        }
    }

    private void lookupVesselById(Scanner scanner, VesselUtil tracker) {
        System.out.println("Enter the Vessel Id to check speed");
        String queryId = scanner.nextLine().trim();

        Vessel matched = tracker.getVesselById(queryId);
        if (matched != null) {
            printVesselRecord(matched);
        } else {
            System.out.println("Vessel Id " + queryId + " not found.");
        }
    }

    private void showTopPerformers(VesselUtil tracker) {
        System.out.println("High performance vessels are");
        List<Vessel> topList = tracker.getHighPerformanceVessels();
        for (int i = 0; i < topList.size(); i++) {
            printVesselRecord(topList.get(i));
        }
    }

    private void printVesselRecord(Vessel vessel) {
        System.out.println(String.format("%s | %s | %s | %s knots",
                vessel.getVesselId(),
                vessel.getVesselName(),
                vessel.getVesselType(),
                vessel.getAverageSpeed()));
    }
}
