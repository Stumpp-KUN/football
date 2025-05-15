# Football World Cup Scoreboard

**A lightweight Java library to manage live Football World Cup matches and scores.**

## 🎯 Features

- **Start a Match**: initialize a game between two teams (score begins at 0–0).
- **Update Score**: set the latest home and away scores for an ongoing match.
- **Finish Match**: remove a completed game from the scoreboard.
- **Summary**: retrieve all ongoing matches sorted by total score (desc), breaking ties by most recently started.

## 🛠 Prerequisites

- Java 17+
- Maven 3.6+

## 🚀 Setup

1. **Clone the repo**
   ```bash
   git clone https://github.com/yourusername/scoreboard.git
   cd scoreboard
   ```
2. **Build the project**
   ```bash
   mvn clean install
   ```

##  Assumptions

- Matches are uniquely identified by the pair of home and away team names (case‑insensitive).
- Team names must be non‑empty and different.
- Scores cannot be negative.
- In‑memory storage (HashMap) is sufficient; no persistence layer.

## ✅ Testing

Unit and integration tests cover all core behaviors. To run:

```bash
mvn test
```
