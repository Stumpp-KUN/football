package com.example.scoreboard;

import java.time.Instant;

public class Match {
    private final String homeTeam;
    private final String awayTeam;
    private int homeScore;
    private int awayScore;
    private final Instant startTime;

    public Match(String homeTeam, String awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = 0;
        this.awayScore = 0;
        this.startTime = Instant.now();
    }

    public String getHomeTeam() { return homeTeam; }
    public String getAwayTeam() { return awayTeam; }
    public int getHomeScore() { return homeScore; }
    public int getAwayScore() { return awayScore; }
    public Instant getStartTime() { return startTime; }

    public void updateScore(int home, int away) {
        if (home < 0 || away < 0) {
            throw new IllegalArgumentException("Score cannot be negative");
        }
        this.homeScore = home;
        this.awayScore = away;
    }

    public int totalScore() {
        return homeScore + awayScore;
    }

    @Override
    public String toString() {
        return String.format("%s %d - %s %d", homeTeam, homeScore, awayTeam, awayScore);
    }
}
