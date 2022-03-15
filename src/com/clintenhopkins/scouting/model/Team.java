package com.clintenhopkins.scouting.model;

import java.util.Objects;

public class Team {

    private int teamNum;

    public Team (int teamNum) {
        this.teamNum = teamNum;
    }

    public int getTeamNum() {
        return teamNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return teamNum == team.teamNum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamNum);
    }

    @Override
    public String toString() {
        return Integer.toString(teamNum);
    }
}
