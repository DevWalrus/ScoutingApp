package com.clintenhopkins.scouting.view;

import com.clintenhopkins.scouting.GUIManager;
import com.clintenhopkins.scouting.model.Match;
import com.clintenhopkins.scouting.utils.UnselectedRadioException;
import com.clintenhopkins.scouting.utils.WarningTypes;
import com.codename1.components.SpanLabel;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.table.TableLayout;

import static com.clintenhopkins.scouting.utils.PrettyErrors.showError;
import static com.clintenhopkins.scouting.utils.PrettyErrors.throwError;

public class ConfirmMatch extends ScoutingAppPage{

    public ConfirmMatch(GUIManager guiManager) {
        super(guiManager, "Confirming 0000 playing match 000");
    }

    public void confirmMatch(Match toConfirm) {
        mainPage.removeAll();
        mainPage.setTitle("Match Data - " + toConfirm);

        showError();

        Button recCancel = new Button("Back");
        recCancel.addActionListener((e) ->
                guiManager.record(toConfirm)
        );

        Button recSubmit = new Button("Confirm");
        recSubmit.addActionListener((e) ->
                guiManager.scout()
        );

        Container buttons = GridLayout.encloseIn(2, recCancel, recSubmit);

        Container auto = generateAutoContainer(toConfirm);
        Container tele = generateTeleContainer(toConfirm);
        Container end = generateEndContainer(toConfirm);
        if (end == null)
            return;
        Container overall = generateOverallContainer(toConfirm);
        if (overall == null)
            return;

        mainPage.addAll(auto, tele, end, overall, buttons);

        mainPage.show();
    }

    private Container generateAutoContainer(Match toConfirm) {
        Label autoLabel = new Label("Auto Data:");

        Label taxiLabel = new Label("Taxied:");
        Label taxiValueLabel = new Label(toConfirm.isTaxied() ? "YES" : "NO");

        Label ahcLabel = new Label("High Cargo:");
        Label ahcValueLabel = new Label(Integer.toString(toConfirm.getHighAutoCargo()));

        Label alcLabel = new Label("Low Cargo:");
        Label alcValueLabel = new Label(Integer.toString(toConfirm.getLowAutoCargo()));

        Label scoreLabel = new Label("Approx Score:");
        Label scoreValueLabel = new Label(Integer.toString(toConfirm.getApproxAutoScore()));

        TableLayout layout = new TableLayout(4, 2);
        Container valuesContainer = new Container(layout);
        valuesContainer
                .add(layout.cc().wp(66), taxiLabel)     .add(layout.cc().wp(33), taxiValueLabel)
                .add(layout.cc().wp(66), ahcLabel)      .add(layout.cc().wp(33), ahcValueLabel)
                .add(layout.cc().wp(66), alcLabel)      .add(layout.cc().wp(33), alcValueLabel)
                .add(layout.cc().wp(66), scoreLabel)    .add(layout.cc().wp(33), scoreValueLabel);

        Container all = new Container(BoxLayout.y());
        all.addAll(autoLabel, valuesContainer);
        return all;
    }

    private Container generateTeleContainer(Match toConfirm) {
        Label teleLabel = new Label("Tele-OP Data:");

        Label thcLabel = new Label("High Cargo:");
        Label thcValueLabel = new Label(Integer.toString(toConfirm.getHighTeleCargo()));

        Label tlcLabel = new Label("Low Cargo:");
        Label tlcValueLabel = new Label(Integer.toString(toConfirm.getLowTeleCargo()));

        Label cargoRPLabel = new Label("Got Cargo RP:");
        Label cargoRPValueLabel = new Label(toConfirm.isCargoBonusAchieved() ? "YES" : "NO");

        Label scoreLabel = new Label("Approx Score:");
        Label scoreValueLabel = new Label(Integer.toString(toConfirm.getApproxTeleScore()));

        TableLayout layout = new TableLayout(4, 2);
        Container valuesContainer = new Container(layout);
        valuesContainer
                .add(layout.cc().wp(66), thcLabel)      .add(layout.cc().wp(33), thcValueLabel)
                .add(layout.cc().wp(66), tlcLabel)      .add(layout.cc().wp(33), tlcValueLabel)
                .add(layout.cc().wp(66), cargoRPLabel)  .add(layout.cc().wp(33), cargoRPValueLabel)
                .add(layout.cc().wp(66), scoreLabel)    .add(layout.cc().wp(33), scoreValueLabel);

        Container all = new Container(BoxLayout.y());
        all.addAll(teleLabel, valuesContainer);
        return all;
    }

    private Container generateEndContainer(Match toConfirm) {
        Label teleLabel = new Label("End Game Data:");

        Label climbLabel = new Label("Climb Level:");
        Label climbValueLabel;
        try {
            climbValueLabel = new Label(toConfirm.getClimbLoc().toString());
        } catch (UnselectedRadioException ure) {
            throwError(ure.getMessage(), guiManager.images.get(WarningTypes.ERROR));
            guiManager.record(toConfirm);
            return null;
        }

        Label fastLabel = new Label("Fast Climber:");
        Label fastValueLabel = new Label(toConfirm.isFastClimbing() ? "YES" : "NO");

        Label climbingRPLabel = new Label("Got Climbing RP:");
        Label climbingRPValueLabel = new Label(toConfirm.isHangerBonusAchieved() ? "YES" : "NO");

        Label wcLabel = new Label("Loss/Tie/Win:");
        Label wcValueLabel;
        try {
            wcValueLabel = new Label(toConfirm.getWinCondition().toString());
        } catch (UnselectedRadioException ure) {
            throwError(ure.getMessage(), guiManager.images.get(WarningTypes.ERROR));
            guiManager.record(toConfirm);
            return null;
        }

        Label scoreLabel = new Label("Approx Score:");
        Label scoreValueLabel;
        try {
            scoreValueLabel = new Label(Integer.toString(toConfirm.getApproxEndScore()));
        }catch (UnselectedRadioException ure) {
            throwError(ure.getMessage(), guiManager.images.get(WarningTypes.ERROR));
            guiManager.record(toConfirm);
            return null;
        }

        TableLayout layout = new TableLayout(5, 2);
        Container valuesContainer = new Container(layout);
        valuesContainer
                .add(layout.cc().wp(66), climbLabel)        .add(layout.cc().wp(33), climbValueLabel)
                .add(layout.cc().wp(66), fastLabel)         .add(layout.cc().wp(33), fastValueLabel)
                .add(layout.cc().wp(66), climbingRPLabel)   .add(layout.cc().wp(33), climbingRPValueLabel)
                .add(layout.cc().wp(66), wcLabel)           .add(layout.cc().wp(33), wcValueLabel)
                .add(layout.cc().wp(66), scoreLabel)        .add(layout.cc().wp(33), scoreValueLabel);

        Container all = new Container(BoxLayout.y());
        all.addAll(teleLabel, valuesContainer);
        return all;
    }

    private Container generateOverallContainer(Match toConfirm) {
        Label teleLabel = new Label("Overall Game Data:");

        Label scoreLabel = new Label("Approx Score Contribution:");
        Label scoreValueLabel;
        try {
            scoreValueLabel = new Label(Integer.toString(toConfirm.getApproxScoreContribution()));
        } catch (UnselectedRadioException ure) {
            throwError(ure.getMessage(), guiManager.images.get(WarningTypes.ERROR));
            guiManager.record(toConfirm);
            return null;
        }

        Label rpLabel = new Label("Total RP:");
        Label rpValueLabel = new Label(Integer.toString(toConfirm.getRP()));

        Label notesLabel = new Label("Notes:");
        SpanLabel notesValueLabel = new SpanLabel(toConfirm.getNotes());

        TableLayout layout = new TableLayout(2, 2);
        Container valuesContainer = new Container(layout);
        valuesContainer
                .add(layout.cc().wp(66), scoreLabel)    .add(layout.cc().wp(33), scoreValueLabel)
                .add(layout.cc().wp(66), rpLabel)       .add(layout.cc().wp(33), rpValueLabel)
                .add(layout.cc().horizontalSpan(2), notesLabel)
                .add(layout.cc().horizontalSpan(2), notesValueLabel);

        Container all = new Container(BoxLayout.y());
        all.addAll(teleLabel, valuesContainer);
        return all;
    }
}
