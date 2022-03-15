package com.clintenhopkins.scouting.view;

import com.clintenhopkins.scouting.GUIManager;
import com.clintenhopkins.scouting.controller.MatchManager;
import com.clintenhopkins.scouting.model.Match;
import com.clintenhopkins.scouting.utils.*;
import com.codename1.components.SpanLabel;
import com.codename1.components.Switch;
import com.codename1.io.Log;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.table.TableLayout;

import static com.clintenhopkins.scouting.utils.PrettyErrors.showError;
import static com.clintenhopkins.scouting.utils.PrettyErrors.throwError;

public class RecordMatch {

    private final GUIManager guiManager;
    private final Form mainPage;

    TextComponent notes = new TextComponent().label("Notes").multiline(true);

    public RecordMatch(GUIManager guiManager) {
        this.mainPage = new Form("Match Data - 0000 playing match 000", BoxLayout.y());
        this.guiManager = guiManager;
    }

    private Container generateAutoContainer(Match toRecord) {
        Label autoLabel = new Label("Auto Data:");

        SpanLabel taxiedLabel = new SpanLabel("Has Taxied (Moved out of the starting box)");

        Switch taxiedSwitch = new Switch();
        taxiedSwitch.setValue(toRecord.isTaxied());
        taxiedSwitch.addActionListener(e->toRecord.setTaxied(taxiedSwitch.isOn()));

        SpanLabel highAutoLabel = new SpanLabel("High Cargo");
        SpanLabel lowAutoLabel = new SpanLabel("Low Cargo");

        Button subHighAutoButton = new Button("-");
        subHighAutoButton.addActionListener(e-> {
            try {
                toRecord.subHighAutoCargo();
            } catch (InvalidValueException ignored) {
                throwError("The value of the High Cargo cannot be negative", guiManager.images.get(WarningTypes.WARNING));
            }
            recordMatch(toRecord);
        });
        Label highAutoText = new Label(Integer.toString(toRecord.getHighAutoCargo()));
        Button addHighAutoButton = new Button("+");
        addHighAutoButton.addActionListener(e-> {
            toRecord.addHighAutoCargo();
            recordMatch(toRecord);
        });

        Button subLowAutoButton = new Button("-");
        subLowAutoButton.addActionListener(e-> {
            try {
                toRecord.subLowAutoCargo();
            } catch (InvalidValueException ignored) {
                throwError("The value of the Low Cargo cannot be negative", guiManager.images.get(WarningTypes.WARNING));

            }
            recordMatch(toRecord);
        });
        Label lowAutoText = new Label(Integer.toString(toRecord.getLowAutoCargo()));
        Button addLowAutoButton = new Button("+");
        addLowAutoButton.addActionListener(e-> {
            toRecord.addLowAutoCargo();
            recordMatch(toRecord);
        });

        TableLayout autoDataTable = new TableLayout(3, 2);
        Container autoDataButtons = new Container(autoDataTable);
        autoDataButtons.setScrollableY(false);
        autoDataButtons.setScrollableX(false);
        autoDataButtons.add(autoDataTable.cc().wp(66), taxiedLabel)
                .add(autoDataTable.cc().wp(33), taxiedSwitch)
                .add(autoDataTable.cc().wp(66), highAutoLabel)
                .add(autoDataTable.cc().wp(33), createIncDecField(subHighAutoButton, highAutoText, addHighAutoButton))
                .add(autoDataTable.cc().wp(66), lowAutoLabel)
                .add(autoDataTable.cc().wp(33), createIncDecField(subLowAutoButton, lowAutoText, addLowAutoButton));

        Container allButtons = new Container(BoxLayout.y()).addAll(autoLabel, autoDataButtons);
        allButtons.setScrollableY(false);
        allButtons.setScrollableX(false);
        return allButtons;
    }

    private Container createIncDecField(Button subButton, Label text, Button addButton) {
        TableLayout table = new TableLayout(1, 3);
        Container container = new Container(table);
        container.setScrollableY(false);
        container.setScrollableX(false);
        container.add(table.cc().wp(33), subButton)
                .add(table.cc().wp(33), text)
                .add(table.cc().wp(33), addButton);
        return container;
    }

    private Container generateTeleContainer(Match toRecord) {
        SpanLabel highTeleLabel = new SpanLabel("High Cargo");
        SpanLabel lowTeleLabel = new SpanLabel("Low Cargo");

        Label teleLabel = new Label("Tele-Op Data:");

        Button subHighTeleButton = new Button("-");
        subHighTeleButton.addActionListener(e-> {
            try {
                toRecord.subHighTeleCargo();
            } catch (InvalidValueException ignored) {
                throwError("The value of the High Cargo cannot be negative", guiManager.images.get(WarningTypes.WARNING));

            }
            recordMatch(toRecord);
        });
        Label highTeleText = new Label(Integer.toString(toRecord.getHighTeleCargo()));
        Button addHighTeleButton = new Button("+");
        addHighTeleButton.addActionListener(e-> {
            toRecord.addHighTeleCargo();
            recordMatch(toRecord);
        });

        Button subLowTeleButton = new Button("-");
        subLowTeleButton.addActionListener(e-> {
            try {
                toRecord.subLowTeleCargo();
            } catch (InvalidValueException ignored) {
                throwError("The value of the Low Cargo cannot be negative", guiManager.images.get(WarningTypes.WARNING));

            }
            recordMatch(toRecord);
        });
        Label lowTeleText = new Label(Integer.toString(toRecord.getLowTeleCargo()));
        Button addLowTeleButton = new Button("+");
        addLowTeleButton.addActionListener(e-> {
            toRecord.addLowTeleCargo();
            recordMatch(toRecord);
        });

        SpanLabel cargoBonusLabel = new SpanLabel("Achieved Cargo Bonus (20 cargo or 18 if quintet)");

        Switch cargoBonusSwitch = new Switch();
        cargoBonusSwitch.setValue(toRecord.isCargoBonusAchieved());
        cargoBonusSwitch.addActionListener(e->toRecord.setCargoBonusAchieved(cargoBonusSwitch.isOn()));

        TableLayout teleDataTable = new TableLayout(3, 2);
        Container teleDataButtons = new Container(teleDataTable);
        teleDataButtons.setScrollableY(false);
        teleDataButtons.setScrollableX(false);
        teleDataButtons.add(teleDataTable.cc().wp(66), highTeleLabel)
                .add(teleDataTable.cc().wp(33), createIncDecField(subHighTeleButton, highTeleText, addHighTeleButton))
                .add(teleDataTable.cc().wp(66), lowTeleLabel)
                .add(teleDataTable.cc().wp(33), createIncDecField(subLowTeleButton, lowTeleText, addLowTeleButton))
                .add(teleDataTable.cc().wp(66), cargoBonusLabel)
                .add(teleDataTable.cc().wp(33), cargoBonusSwitch);

        Container allButtons = new Container(BoxLayout.y()).addAll(teleLabel, teleDataButtons);
        allButtons.setScrollableY(false);
        allButtons.setScrollableX(false);
        return allButtons;
    }

    private Container generateEndContainer(Match toRecord) {
        Label endGameLabel = new Label("End Game Data:");

        SpanLabel barSpanLabel = new SpanLabel("Bar Climb");

        RadioButton naRadio = new RadioButton("Didn't Climb");
        RadioButton lowRadio = new RadioButton("Low");
        RadioButton midRadio = new RadioButton("Mid");
        RadioButton highRadio = new RadioButton("High");
        RadioButton travRadio = new RadioButton("Traversal");
        ButtonGroup climbRadioGroup = new ButtonGroup(naRadio, lowRadio, midRadio, highRadio, travRadio);
        try {
            climbRadioGroup.setSelected(toRecord.getClimbLoc().getIndex());
        } catch (UnselectedRadioException ignored){}
        ActionListener<ActionEvent> setClimbAction = e -> {
            HangerLoc val;
            switch (climbRadioGroup.getSelectedIndex()) {
                case 4:
                    val = HangerLoc.TRAV;
                    break;
                case 3:
                    val = HangerLoc.HIGH;
                    break;
                case 2:
                    val = HangerLoc.MID;
                    break;
                case 1:
                    val = HangerLoc.LOW;
                    break;
                default:
                    val = HangerLoc.NONE;
            }
            toRecord.setClimbLoc(val);
        };
        naRadio.addActionListener(setClimbAction);
        lowRadio.addActionListener(setClimbAction);
        midRadio.addActionListener(setClimbAction);
        highRadio.addActionListener(setClimbAction);
        travRadio.addActionListener(setClimbAction);

        Container barRadioGroup = new Container(BoxLayout.y()).addAll(travRadio, highRadio, midRadio, lowRadio, naRadio);
        barRadioGroup.setScrollableY(false);
        barRadioGroup.setScrollableX(false);

        SpanLabel fastClimbLabel = new SpanLabel("Was their climb fast? (Subjective)");

        Switch fastClimbSwitch = new Switch();
        fastClimbSwitch.setValue(toRecord.isFastClimbing());
        fastClimbSwitch.addActionListener(e->toRecord.setFastClimbing(fastClimbSwitch.isOn()));

        SpanLabel hangerBonusLabel = new SpanLabel("Achieved Hanger Bonus (16 climbing points)");

        Switch hangerBonusSwitch = new Switch();
        hangerBonusSwitch.setValue(toRecord.isHangerBonusAchieved());
        hangerBonusSwitch.addActionListener(e->toRecord.setHangerBonusAchieved(hangerBonusSwitch.isOn()));

        SpanLabel resultsLabel = new SpanLabel("Match Results");

        RadioButton lossRadio = new RadioButton("Loss");
        RadioButton tieRadio = new RadioButton("Tie");
        RadioButton winRadio = new RadioButton("Win");
        ButtonGroup winCondRadioGroup = new ButtonGroup(lossRadio, tieRadio, winRadio);
        try {
            winCondRadioGroup.setSelected(toRecord.getWinCondition().getValue());
        } catch (UnselectedRadioException ignored){}
        ActionListener<ActionEvent> setWinConditionAction = e -> {
            WinCondition val;
            switch (winCondRadioGroup.getSelectedIndex()) {
                case 2:
                    val = WinCondition.WIN;
                    break;
                case 1:
                    val = WinCondition.TIE;
                    break;
                default:
                    val = WinCondition.LOSS;
            }
            toRecord.setWinCondition(val);
        };
        lossRadio.addActionListener(setWinConditionAction);
        tieRadio.addActionListener(setWinConditionAction);
        winRadio.addActionListener(setWinConditionAction);


        Container resultsRadioGroup = new Container(BoxLayout.x()).addAll(lossRadio, tieRadio, winRadio);
        resultsRadioGroup.setScrollableY(false);
        resultsRadioGroup.setScrollableX(false);
        Container resultsGroup = new Container(BoxLayout.y()).addAll(resultsLabel, resultsRadioGroup);
        resultsGroup.setScrollableY(false);
        resultsGroup.setScrollableX(false);

        TableLayout barDataTable = new TableLayout(1, 2);
        Container barButtons = new Container(barDataTable);
        barButtons.setScrollableY(false);
        barButtons.setScrollableX(false);
        barButtons.add(barDataTable.cc().wp(33), barSpanLabel)
                .add(barDataTable.cc().wp(66), barRadioGroup);
        TableLayout endDataTable = new TableLayout(2, 2);
        Container hangerSwitches = new Container(endDataTable);
        hangerSwitches.setScrollableY(false);
        hangerSwitches.setScrollableX(false);
        hangerSwitches.add(endDataTable.cc().wp(66), fastClimbLabel)
                .add(endDataTable.cc().wp(33), fastClimbSwitch)
                .add(endDataTable.cc().wp(66), hangerBonusLabel)
                .add(endDataTable.cc().wp(33), hangerBonusSwitch);
        Container allButtons = new Container(BoxLayout.y()).addAll(
                endGameLabel,
                barButtons,
                hangerSwitches,
                resultsGroup
        );
        allButtons.setScrollableY(false);
        allButtons.setScrollableX(false);
        return allButtons;
    }

    public void recordMatch(Match toRecord) {
        mainPage.removeAll();

        mainPage.setTitle("Match Data - " + toRecord);

        showError();

        Button recCancel = new Button("Cancel");
        recCancel.addActionListener((e) ->
                guiManager.scout()
        );

        Button recSubmit = new Button("Submit");
        recSubmit.addActionListener(e -> {
            toRecord.setNotes(notes.getText());
            guiManager.confirm(toRecord);
        });

        Container buttons = GridLayout.encloseIn(2, recCancel, recSubmit);

        mainPage.add(generateAutoContainer(toRecord))
                .add(generateTeleContainer(toRecord))
                .add(generateEndContainer(toRecord))
                .add(notes)
                .add(buttons);

        mainPage.show();
    }
}
