package com.example;

import com.example.scoreboard.Match;
import com.example.scoreboard.ScoreboardService;

import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ScoreboardService service = new ScoreboardService();

        service.startGame("Mexico", "Canada");
        service.updateScore("Mexico", "Canada", 0, 5);
        Thread.sleep(5);
        service.startGame("Spain", "Brazil");
        service.updateScore("Spain", "Brazil", 10, 2);
        Thread.sleep(5);
        service.startGame("Germany", "France");
        service.updateScore("Germany", "France", 2, 2);
        Thread.sleep(5);
        service.startGame("Uruguay", "Italy");
        service.updateScore("Uruguay", "Italy", 6, 6);
        Thread.sleep(5);
        service.startGame("Argentina", "Australia");
        service.updateScore("Argentina", "Australia", 3, 1);

        List<Match> summary = service.getSummary();
        System.out.println("===== Summary =====");
        for (Match m : summary) {
            System.out.println(m);
        }
    }
}