package com.vityarthi.campuslock;

import com.vityarthi.campuslock.exception.LockerException;
import com.vityarthi.campuslock.service.LockerService;

import java.util.Scanner;

public class Main {

    private static final int TOTAL_LOCKERS = 6;

    public static void main(String[] args) {

        LockerService service = new LockerService(TOTAL_LOCKERS);
        Scanner scanner = new Scanner(System.in);

        System.out.println("===============================================");
        System.out.println("       WELCOME TO CAMPUSLOCK LOGISTICS        ");
        System.out.println("===============================================");

        boolean running = true;

        while (running) {

            printMenu(service);

            String choice = scanner.nextLine().trim();

            try {

                switch (choice) {

                    case "1" -> service.displayLockerStatus();

                    case "2" -> reserveLocker(service, scanner);

                    case "3" -> pickupPackage(service, scanner);

                    case "4" -> {
                        System.out.println("Exiting system. Goodbye!");
                        running = false;
                    }

                    default ->
                            System.out.println(
                                    "Invalid option. Please choose 1, 2, 3, or 4."
                            );
                }

            } catch (LockerException e) {

                System.out.println("ERROR: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private static void printMenu(LockerService service) {

        System.out.println("\n1. View Lockers Status");
        System.out.println("2. Reserve / Drop Package");
        System.out.println("3. Retrieve / Pickup Package");
        System.out.println("4. Exit");

        System.out.println(
                "Available: " + service.getAvailableLockerCount()
                        + " | Occupied: "
                        + service.getOccupiedLockerCount()
        );

        System.out.print("Choose an option: ");
    }

    private static void reserveLocker(
            LockerService service,
            Scanner scanner
    ) throws LockerException {

        System.out.print("Enter Student/User ID: ");
        String userId = scanner.nextLine().trim();

        System.out.print(
                "Enter Locker Size (SMALL/MEDIUM/LARGE): "
        );

        String size = scanner.nextLine().trim();

        System.out.println(
                service.allocateLocker(userId, size)
        );
    }

    private static void pickupPackage(
            LockerService service,
            Scanner scanner
    ) throws LockerException {

        System.out.print("Enter Locker ID: ");

        String lockerIdInput = scanner.nextLine().trim();

        int lockerId;

        try {

            lockerId = Integer.parseInt(lockerIdInput);

        } catch (NumberFormatException e) {

            throw new LockerException(
                    "Locker ID must be a number."
            );
        }

        System.out.print("Enter 4-digit Pickup PIN: ");

        String pin = scanner.nextLine().trim();

        if (service.pickupPackage(lockerId, pin)) {

            System.out.println(
                    "SUCCESS: Locker unlocked. Item retrieved!"
            );
        }
    }
}