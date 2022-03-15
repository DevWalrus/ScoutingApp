package com.clintenhopkins.scouting.controller;

import com.clintenhopkins.scouting.model.Team;
import com.clintenhopkins.scouting.model.Match;
import com.codename1.io.Log;

import java.util.HashMap;
import java.util.HashSet;

public class MatchManager {
    private final HashMap<Team, HashSet<Match>> matches;

    public MatchManager() {
        matches = new HashMap<>();
    }

    public Match addMatch(int teamNum, int matchNum) {
        HashSet<Match> myMatches;
        Team team = new Team(teamNum);
        if (matches.containsKey(team)) {
            myMatches = matches.get(team);
        } else {
            myMatches = new HashSet<>();
        }
        Match match = new Match(team, matchNum);
        myMatches.add(match);
        matches.put(team, myMatches);
        Log.p(matches.toString());
        return match;
    }

    public void addMatch(int teamNum, Match match) {
        HashSet<Match> myMatches;
        Team team = new Team(teamNum);
        if (matches.containsKey(team)) {
            myMatches = matches.get(team);
        } else {
            myMatches = new HashSet<>();
        }
        myMatches.add(match);
        matches.put(team, myMatches);
        Log.p(matches.toString());
    }

    public Match getMatch(Team team, int matchNum) {
        if (!matches.containsKey(team))
            return null;
        HashSet<Match> myMatches = matches.get(team);
        for (Match m: myMatches) {
            if (m.equals(new Match(team, matchNum))) {
                return m;
            }
        }
        return null;
    }

    public int matchCount(Team team) {
        return matches.get(team).size();
    }

    public int matchCount(int teamNum) {
        return matches.get(new Team(teamNum)).size();
    }

    public boolean teamContains(Team team, Match match) {
        return matches.get(team).contains(match);
    }
}
