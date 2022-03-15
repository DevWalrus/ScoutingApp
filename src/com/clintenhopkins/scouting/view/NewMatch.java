package com.clintenhopkins.scouting.view;

import com.clintenhopkins.scouting.GUIManager;
import com.clintenhopkins.scouting.controller.MatchManager;
import com.clintenhopkins.scouting.model.Match;
import com.clintenhopkins.scouting.utils.WarningTypes;
import com.codename1.components.SpanLabel;
import com.codename1.components.Switch;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.TextModeLayout;
import com.codename1.ui.validation.NumericConstraint;
import com.codename1.ui.validation.Validator;

import java.util.ArrayList;

import static com.clintenhopkins.scouting.utils.PrettyErrors.showError;
import static com.clintenhopkins.scouting.utils.PrettyErrors.throwError;

public class NewMatch {

    private final GUIManager guiManager;
    private final Form mainPage;

    public NewMatch(GUIManager guiManager) {
        this.mainPage = new Form("New Match", new BorderLayout());
        this.guiManager = guiManager;
    }

    public void createNewMatch() {
        mainPage.removeAll();

        showError();

        TextModeLayout tl = new TextModeLayout(2, 2);
        Container teamInfo = new Container(tl);

        TextComponent teamNum = new TextComponent().label("Team Number").hint("2130");
        TextComponent matchNum = new TextComponent().label("Match Number").hint("10");

        Validator val = new Validator();
        val.addConstraint(teamNum, new NumericConstraint(false, 0, 99999, "Team number must be 1-5 numbers long"));
        val.addConstraint(matchNum, new NumericConstraint(false, 0, 999, "Match number must be 1-3 numbers long"));

        Label isPracticeLabel = new Label("Practice Match?");
        Switch isPracticeSwitch = new Switch();

        Container isPracticeContainer = new Container(BoxLayout.y());

        isPracticeContainer.add(isPracticeLabel)
                .add(isPracticeSwitch);

        teamInfo.add(tl.cc().hs(2), teamNum)
                .add(tl.cc().wp(50), matchNum)
                .add(isPracticeContainer);

        Button numSubmit = new Button("Submit");

        numSubmit.addActionListener((e) -> {
            Match toRecord;
            try {
                toRecord = new Match(Integer.parseInt(teamNum.getText()), Integer.parseInt(matchNum.getText()));
            } catch (NumberFormatException ignored) {
                throwError("Invalid Input Provided", guiManager.images.get(WarningTypes.ERROR));
                createNewMatch();
                return;
            }
            guiManager.newRecord(toRecord, isPracticeSwitch.isOn());
        });

        Button backButton = new Button("Back");
        backButton.addActionListener(e->guiManager.begin());

        mainPage.add(BorderLayout.CENTER, teamInfo);
        mainPage.add(BorderLayout.SOUTH, GridLayout.encloseIn(2, backButton, numSubmit));
        mainPage.show();
    }


}
