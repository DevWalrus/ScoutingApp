package com.clintenhopkins.scouting.utils;

public class UnselectedRadioException extends Exception{
    public UnselectedRadioException(String unselectedRadio) {
        super("You did not select something in " + unselectedRadio);
    }
}
