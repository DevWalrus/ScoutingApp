package com.clintenhopkins.scouting.utils;

import com.codename1.location.GeofenceListener;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;

public class BoiseListener implements GeofenceListener {
    @Override
    public void onExit(String id) {
        if(!Display.getInstance().isMinimized()) {
            Display.getInstance().callSerially(() -> {
                Dialog.show("Goodbye", "I hope you had a great event!", "OK", null);
            });
        } else {
            LocalNotification ln = new LocalNotification();
            ln.setId("gbMessage");
            ln.setAlertTitle("Goodbye");
            ln.setAlertBody("I hope you had a great event!");
            Display.getInstance().scheduleLocalNotification(ln, System.currentTimeMillis() + 10, LocalNotification.REPEAT_NONE);
        }
    }

    @Override
    public void onEntered(String id) {
        if(!Display.getInstance().isMinimized()) {
            Display.getInstance().callSerially(() -> {
                Dialog.show("You're almost there!", "I hope you have a great event!", "OK", null);
            });
        } else {
            LocalNotification ln = new LocalNotification();
            ln.setId("wcMessage");
            ln.setAlertTitle("You're almost there!");
            ln.setAlertBody("I hope you have a great event!");
            Display.getInstance().scheduleLocalNotification(ln, System.currentTimeMillis() + 10, LocalNotification.REPEAT_NONE);
        }
    }
}
