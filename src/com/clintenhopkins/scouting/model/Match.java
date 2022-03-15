package com.clintenhopkins.scouting.model;

import com.clintenhopkins.scouting.utils.HangerLoc;
import com.clintenhopkins.scouting.utils.InvalidValueException;
import com.clintenhopkins.scouting.utils.UnselectedRadioException;
import com.clintenhopkins.scouting.utils.WinCondition;
import com.codename1.io.Log;

import java.util.Objects;

public class Match {

    private final Team team;
    private final int matchNum;
    private boolean taxied = false;
    private boolean cargoBonusAchieved = false;
    private boolean hangerBonusAchieved = false;
    private boolean fastClimbing = false;
    private HangerLoc climbLoc;
    private WinCondition winCondition;
    private int highAutoCargo = 0;
    private static final int H_A_C_VALUE = 4;
    private int lowAutoCargo = 0;
    private static final int L_A_C_VALUE = 2;
    private int highTeleCargo = 0;
    private static final int H_T_C_VALUE = 2;
    private int lowTeleCargo = 0;
    private static final int L_T_C_VALUE = 1;
    private static final String NO_NOTES = "None";
    private String notes = NO_NOTES;

    public Match(Team team, int matchNum) {
        this.team = team;
        this.matchNum = matchNum;
    }

    public Match(int teamNum, int matchNum) {
        this.team = new Team(teamNum);
        this.matchNum = matchNum;
    }

    public int getTeamNum() {
        return team.getTeamNum();
    }

    public boolean isTaxied() {
        return taxied;
    }

    public void setTaxied(boolean taxied) {
        this.taxied = taxied;
    }

    public boolean isCargoBonusAchieved() {
        return cargoBonusAchieved;
    }

    public void setCargoBonusAchieved(boolean cargoBonusAchieved) {
        this.cargoBonusAchieved = cargoBonusAchieved;
    }

    public boolean isHangerBonusAchieved() {
        return hangerBonusAchieved;
    }

    public void setHangerBonusAchieved(boolean hangerBonusAchieved) {
        this.hangerBonusAchieved = hangerBonusAchieved;
    }

    public boolean isFastClimbing() {
        return fastClimbing;
    }

    public void setFastClimbing(boolean fastClimbing) {
        this.fastClimbing = fastClimbing;
    }

    public void addHighAutoCargo() {
        highAutoCargo++;
    }

    public void addLowAutoCargo() {
        lowAutoCargo++;
    }

    public void subHighAutoCargo() throws InvalidValueException {
        if (highAutoCargo == 0)
            throw new InvalidValueException();
        highAutoCargo--;
    }

    public void subLowAutoCargo() throws InvalidValueException{
        if (lowAutoCargo == 0)
            throw new InvalidValueException();
        lowAutoCargo--;
    }

    public int getHighAutoCargo() {
        return highAutoCargo;
    }

    public int getLowAutoCargo() {
        return lowAutoCargo;
    }

    public void addHighTeleCargo() {
        highTeleCargo++;
    }

    public void addLowTeleCargo() {
        lowTeleCargo++;
    }

    public void subHighTeleCargo() throws InvalidValueException{
        if (highTeleCargo == 0)
            throw new InvalidValueException();
        highTeleCargo--;
    }

    public void subLowTeleCargo() throws InvalidValueException{
        if (lowTeleCargo == 0)
            throw new InvalidValueException();
        lowTeleCargo--;
    }

    public HangerLoc getClimbLoc() throws UnselectedRadioException {
        if (climbLoc == null)
            throw new UnselectedRadioException("Bar Climb");
        return climbLoc;
    }

    public WinCondition getWinCondition() throws UnselectedRadioException {
        if (winCondition == null)
            throw new UnselectedRadioException("Match Results");
        return winCondition;
    }

    public int getHighTeleCargo() {
        return highTeleCargo;
    }

    public int getLowTeleCargo() {
        return lowTeleCargo;
    }

    public void setWinCondition(WinCondition winCondition) {
        this.winCondition = winCondition;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getNotes() {
        return notes;
    }

    public void setClimbLoc(HangerLoc climbLoc) {
        this.climbLoc = climbLoc;
        Log.p(Integer.toString(climbLoc.getValue()));
    }

    public int getApproxAutoScore() {
        return (taxied ? 2 : 0) +
                (highAutoCargo * H_A_C_VALUE) +
                (lowAutoCargo * L_A_C_VALUE);
    }

    public int getApproxTeleScore() {
        return (highTeleCargo * H_T_C_VALUE) +
                (lowTeleCargo * L_T_C_VALUE);
    }

    public int getApproxEndScore() throws UnselectedRadioException {
        return getClimbLoc().getValue();
    }

    public int getApproxScoreContribution() throws UnselectedRadioException {
        return getApproxAutoScore() +
                getApproxTeleScore() +
                getApproxEndScore();
    }

    public int getRP() {
        return winCondition.getValue() +
                (cargoBonusAchieved ? 1 : 0) +
                (hangerBonusAchieved ? 1 : 0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return matchNum == match.matchNum && Objects.equals(team, match.team);
    }

    @Override
    public int hashCode() {
        return Objects.hash(team, matchNum);
    }

    @Override
    public String toString() {
        return team + " playing match " + matchNum;
    }
}
