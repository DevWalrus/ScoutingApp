package com.clintenhopkins.scouting.view;

import com.clintenhopkins.scouting.GUIManager;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;

public abstract class ScoutingAppPage {

    protected final GUIManager guiManager;
    protected final Form mainPage;

    public ScoutingAppPage(GUIManager guiManager, String title) {
        this.guiManager = guiManager;
        this.mainPage = new Form(title, BoxLayout.y());
    }

}
