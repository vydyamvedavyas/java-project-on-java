package com.vityarthi.campuslock.service;

import com.vityarthi.campuslock.exception.LockerException;
import com.vityarthi.campuslock.model.Locker;
import com.vityarthi.campuslock.util.LoggerUtil;

import java.security.SecureRandom;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class LockerService {

    private static final String[] VALID_SIZES = {
            "SMALL",
            "MEDIUM",
            "LARGE"
    };

    private final Map<Integer, Locker> lockers =
            new TreeMap<>();

    private final SecureRandom random =
            new SecureRandom();

    public LockerService(int totalLockers) {

        if (totalLockers <= 0) {

            throw new IllegalArgumentException(
                    "Total lockers must be greater than zero."
            );
        }

        for (int i = 1; i <= totalLockers; i++) {

            String size =
                    (i % 3 == 0)
                            ? "LARGE"
                            : (i % 2 == 0)
                            ? "MEDIUM"
                            : "SMALL";

            lockers.put(
                    i,
                    new Locker(i, size)
            );
        }
    }

    public synchronized String allocateLocker(
            String userId,
            String size
    ) throws LockerException {

        if (userId == null || userId.isBlank()) {

            throw new LockerException(
                    "User ID cannot be empty."
            );
        }

        String requestedSize =
                normalizeSize(size);

        Locker selected =
                lockers.values()
                        .stream()
                        .filter(locker ->
                                !locker.isOccupied()
                                        && locker.getSize()
                                        .equals(requestedSize)
                        )
                        .min(
                                Comparator.comparingInt(
                                        Locker::getLockerId
                                )
                        )
                        .orElse(null);

        if (selected == null) {

            throw new LockerException(
                    "No available lockers found for size: "
                            + requestedSize
            );
        }

        String generatedPin =
                generatePin();

        selected.allocate(
                userId.trim(),
                generatedPin
        );

        LoggerUtil.log(
                "Locker #" +
                        selected.getLockerId() +
                        " allocated to user " +
                        userId.trim()
        );

        return
                "SUCCESS: Allocated Locker #" +
                        selected.getLockerId() +
                        " | Access PIN: " +
                        generatedPin;
    }

    public synchronized boolean pickupPackage(
            int lockerId,
            String pin
    ) throws LockerException {

        Locker locker =
                lockers.get(lockerId);

        if (locker == null) {

            throw new LockerException(
                    "Invalid Locker ID."
            );
        }

        if (!locker.isOccupied()) {

            throw new LockerException(
                    "Locker is currently empty."
            );
        }

        if (!isValidPin(pin)) {

            throw new LockerException(
                    "PIN must contain exactly 4 digits."
            );
        }

        if (!locker.getCurrentPin().equals(pin)) {

            LoggerUtil.log(
                    "SECURITY ALERT: Invalid PIN attempt on Locker #"
                            + lockerId
            );

            throw new LockerException(
                    "Access Denied: Incorrect PIN."
            );
        }

        locker.release();

        LoggerUtil.log(
                "Package retrieved successfully from Locker #"
                        + lockerId
        );

        return true;
    }

    public synchronized void displayLockerStatus() {

        System.out.println(
                "\n--- CURRENT LOCKER STATUS ---"
        );

        for (Locker locker : lockers.values()) {

            String status =
                    locker.isOccupied()
                            ? "OCCUPIED (User: "
                            + locker.getAssignedUserId()
                            + ")"
                            : "AVAILABLE";

            System.out.printf(
                    "Locker #%d [%s]: %s%n",
                    locker.getLockerId(),
                    locker.getSize(),
                    status
            );
        }

        System.out.println(
                "-----------------------------\n"
        );
    }

    public synchronized int getAvailableLockerCount() {

        int count = 0;

        for (Locker locker : lockers.values()) {

            if (!locker.isOccupied()) {
                count++;
            }
        }

        return count;
    }

    public synchronized int getOccupiedLockerCount() {

        return lockers.size()
                - getAvailableLockerCount();
    }

    private String normalizeSize(
            String size
    ) throws LockerException {

        if (size == null || size.isBlank()) {

            throw new LockerException(
                    "Locker size cannot be empty."
            );
        }

        String normalized =
                size.trim().toUpperCase();

        for (String validSize : VALID_SIZES) {

            if (validSize.equals(normalized)) {
                return normalized;
            }
        }

        throw new LockerException(
                "Invalid locker size. Use SMALL, MEDIUM, or LARGE."
        );
    }

    private String generatePin() {

        return String.format(
                "%04d",
                random.nextInt(10000)
        );
    }

    private boolean isValidPin(String pin) {

        return pin != null
                && pin.matches("\\d{4}");
    }
}