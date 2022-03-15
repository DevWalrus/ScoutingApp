package com.clintenhopkins.scouting.view;

import com.clintenhopkins.scouting.GUIManager;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;

import static com.clintenhopkins.scouting.utils.PrettyErrors.showError;

public class MainScreen extends ScoutingAppPage{

    public MainScreen(GUIManager guiManager) {
        super(guiManager, "Alpha+ Scouting");
    }

    public void newSession() {
        mainPage.removeAll();

        showError();

        Button scoutButton = new Button("Begin Scouting");

        scoutButton.addActionListener(e->guiManager.scout());

        Button dataButton = new Button("Look At Data [NOT SETUP]");

        mainPage.addAll(scoutButton, dataButton);
        mainPage.show();
    }
}
