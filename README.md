# Kalah Game Console Application

This is a console-based implementation of the Kalah game. Players can choose different rules (novice, normal, advanced) to play against the computer.

## Prerequisites

- Java 17 or higher
- Gradle 7.6 or higher

## Build Instructions

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/kalah-game.git
   cd kalah
2. Build the project:
   ```bash
   ./gradlew build
   ```
3. Run the application:
   ```bash
    ./gradlew run --console=plain
    ```
4. Follow the on-screen instructions to play the game.
5. Choose the game mode (novice, normal, advanced) and make your moves by entering the pit number.
6. The game will alternate turns between you and the computer until one player has no more seeds left in their pits.
7. The player with the most seeds in their store at the end of the game wins.
8. You can also run the tests to ensure everything is working correctly:
   ```bash
   ./gradlew test
   ```