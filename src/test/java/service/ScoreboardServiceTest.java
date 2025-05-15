package service;

import com.example.scoreboard.Match;
import com.example.scoreboard.ScoreboardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreboardServiceTest {
    private ScoreboardService service;

    @BeforeEach
    void setup() {
        service = new ScoreboardService();
    }

    @Test
    void testStartAndFinishGame() {
        service.startGame("A", "B");
        assertEquals(1, service.getSummary().size());
        service.finishGame("A", "B");
        assertTrue(service.getSummary().isEmpty());
    }

    @Test
    void testDuplicateStart() {
        service.startGame("Team1", "Team2");
        assertThrows(IllegalStateException.class, () -> service.startGame("Team1", "Team2"));
    }

    @Test
    void testInvalidTeamNames() {
        assertThrows(IllegalArgumentException.class, () -> service.startGame("A", "A"));
        assertThrows(IllegalArgumentException.class, () -> service.startGame("", "B"));
    }

    @Test
    void testUpdateScore() {
        service.startGame("A", "B");
        service.updateScore("A", "B", 1, 2);
        Match m = service.getSummary().getFirst();
        assertEquals(1, m.getHomeScore());
        assertEquals(2, m.getAwayScore());
    }

    @Test
    void testFinishNonexistent() {
        assertThrows(NoSuchElementException.class, () -> service.finishGame("X", "Y"));
    }

    @Test
    void testExampleInPDF() throws InterruptedException {
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

        List<Match> summ = service.getSummary();
        assertEquals("Uruguay", summ.get(0).getHomeTeam());
        assertEquals("Spain", summ.get(1).getHomeTeam());
        assertEquals("Mexico", summ.get(2).getHomeTeam());
        assertEquals("Argentina", summ.get(3).getHomeTeam());
        assertEquals("Germany", summ.get(4).getHomeTeam());
    }

    @Test
    void testTieBreakerOrder() throws InterruptedException {
        service.startGame("TeamA", "TeamB");
        service.updateScore("TeamA", "TeamB", 1, 3);
        Thread.sleep(5);
        service.startGame("TeamC", "TeamD");
        service.updateScore("TeamC", "TeamD", 2, 2);

        List<Match> list = service.getSummary();
        assertEquals("TeamC", list.get(0).getHomeTeam());
        assertEquals("TeamA", list.get(1).getHomeTeam());
    }
}
