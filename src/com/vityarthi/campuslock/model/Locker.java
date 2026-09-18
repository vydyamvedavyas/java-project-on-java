package com.vityarthi.campuslock.model;

public class Locker {

    private final int lockerId;
    private final String size;

    private boolean occupied;
    private String currentPin;
    private String assignedUserId;

    public Locker(int lockerId, String size) {

        if (lockerId <= 0) {
            throw new IllegalArgumentException(
                    "Locker ID must be positive."
            );
        }

        if (size == null || size.isBlank()) {
            throw new IllegalArgumentException(
                    "Locker size cannot be empty."
            );
        }

        this.lockerId = lockerId;
        this.size = size.toUpperCase();

        this.occupied = false;
        this.currentPin = "";
        this.assignedUserId = "";
    }

    public int getLockerId() {
        return lockerId;
    }

    public String getSize() {
        return size;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public String getCurrentPin() {
        return currentPin;
    }

    public String getAssignedUserId() {
        return assignedUserId;
    }

    public void allocate(String userId, String pin) {

        if (occupied) {
            throw new IllegalStateException(
                    "Locker is already occupied."
            );
        }

        this.occupied = true;
        this.assignedUserId = userId;
        this.currentPin = pin;
    }

    public void release() {

        this.occupied = false;
        this.assignedUserId = "";
        this.currentPin = "";
    }
}