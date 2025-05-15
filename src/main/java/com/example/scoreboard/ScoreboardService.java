package com.example.scoreboard;

import java.util.*;
import java.util.stream.Collectors;

public class ScoreboardService {
    private final Map<String, Match> matches = new HashMap<>();

    public void startGame(String home, String away) {
        validateTeamNames(home, away);
        String key = key(home, away);
        if (matches.containsKey(key)) {
            throw new IllegalStateException("Match already exists: " + home + " vs " + away);
        }
        matches.put(key, new Match(home, away));
    }

    public void finishGame(String home, String away) {
        String key = key(home, away);
        if (matches.remove(key) == null) {
            throw new NoSuchElementException("Match not found: " + home + " vs " + away);
        }
    }

    public void updateScore(String home, String away, int homeScore, int awayScore) {
        Match match = matches.get(key(home, away));
        if (match == null) {
            throw new NoSuchElementException("Match not found: " + home + " vs " + away);
        }
        match.updateScore(homeScore, awayScore);
    }

    public List<Match> getSummary() {
        return matches.values().stream()
                .sorted(
                        Comparator.comparingInt(Match::totalScore).reversed()
                                .thenComparing(
                                        Comparator.comparing(Match::getStartTime).reversed()
                                )
                ).toList();
    }

    private String key(String home, String away) {
        return home.trim().toLowerCase() + "_vs_" + away.trim().toLowerCase();
    }

    private void validateTeamNames(String home, String away) {
        if (home == null || away == null || home.isBlank() || away.isBlank()) {
            throw new IllegalArgumentException("Team names must be non-empty");
        }
        if (home.equalsIgnoreCase(away)) {
            throw new IllegalArgumentException("Home and away teams must be different");
        }
    }
}
