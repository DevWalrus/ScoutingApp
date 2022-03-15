package com.clintenhopkins.scouting.utils;

public enum HangerLoc {
    LOW(4),
    MID(6),
    HIGH(10),
    TRAV(15),
    NONE(0);

    private final int value;

    HangerLoc(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public int getIndex() {
        switch (value) {
            case 4:
                return 1;
            case 6:
                return 2;
            case 10:
                return 3;
            case 15:
                return 4;
            default:
                return 0;
        }
    }

    @Override
    public String toString() {
        switch (value) {
            case 4:
                return "Low";
            case 6:
                return "Mid";
            case 10:
                return "High";
            case 15:
                return "Traversal";
            default:
                return "No Climb";
        }
    }
}
