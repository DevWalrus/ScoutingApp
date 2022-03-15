package com.clintenhopkins.scouting.utils;

public enum WinCondition {
    WIN(2),
    TIE(1),
    LOSS(0);

    private final int value;

    WinCondition(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        switch (value) {
            case 2:
                return "Win";
            case 1:
                return "Tie";
            default:
                return "Loss";
        }
    }
}
