# team: trifecta
# members: Nicole Leondike, Jennifer Buenteo, Tony McFarlan
# course: CS 3354.004 Fa2026

# CS 3354 Console-Based Chess Game (Phase 1 CLI Backend, Phase 2 Swing GUI)

A robust, object-oriented, console-based Java chess backend developed for CS 3354\. The application supports a 2-player interactive command-line interface, strict standard notation parsing, turn-taking, and piece movement legality checks.

---

## Prerequisites / Environment Setup

* **Java Development Kit (JDK):** Version 17 or higher (`JDK 17+` recommended)
* **Operating System:** Windows (PowerShell / Command Prompt), macOS, or Linux
* **Environment Variable:** Ensure `JAVA_HOME` is set and `javac` / `java` are available in your system `PATH`.

To verify your Java installation:

```
java -version
javac -version

```

---

## Build / Compilation Commands

All source files are organized cleanly under the `src/` directory into modular packages (`board`, `pieces`, `game`, `utils`).

### Compiling the Project

To compile all Java source files into the `bin/` directory:

#### Windows (PowerShell):

```
javac -d bin (Get-ChildItem -Path src -Recurse -Filter *.java).FullName

```

#### macOS / Linux / Bash:

```
javac -d bin $(find src -name "*.java")

```

---

## Execution Command

Once compiled, launch the interactive CLI game loop using `java`:

```
java -cp bin game.Main

```

---

## Gameplay Examples / Interactions

The game prompts the active player to enter standard chess coordinate moves using `[FROM] [TO]` notation (e.g., `E2 E4`).

### 1. Standard Move / Turn Alternation

```
   A  B  C  D  E  F  G  H
8 bR bN bB bQ bK bB bN bR
7 bp bp bp bp bp bp bp bp
6    ##    ##    ##    ##
5 ##    ##    ##    ##  
4    ##    ##    ##    ##
3 ##    ##    ##    ##  
2 wp wp wp wp wp wp wp wp
1 wR wN wB wQ wK wB wN wR

WHITE 's turn. Enter move: E2 E4
Move accepted.
From: E2
To: E4

   A  B  C  D  E  F  G  H
8 bR bN bB bQ bK bB bN bR
7 bp bp bp bp bp bp bp bp
6    ##    ##    ##    ##
5 ##    ##    ##    ##  
4          ##    ##    ##
3 ##    ##    ##    ##  
2 wp wp wp wp ## wp wp wp
1 wR wN wB wQ wK wB wN wR

BLACK 's turn. Enter move: e7 e5
Move accepted.
From: E7
To: E5

```

### 2. Error Recovery (Whitespace / Case Tolerant / Invalid Input Handling)

The game loop automatically catches syntax and coordinate errors without crashing or skipping turns:

```
WHITE 's turn. Enter move: invalid move
Invalid format. Use [FROM] [TO] (Example: E2 E4)

WHITE 's turn. Enter move: Z9 A0
Invalid square: Z9

WHITE 's turn. Enter move:   e2    e4  
Move accepted.
From: E2
To: E4

```

### 3. Exiting the Game

Type `quit` or `exit` at any move prompt:

```
WHITE 's turn. Enter move: quit
Thanks for playing!

```

---

##  Feature Summary

### Implemented Features

* **8x8 Matrix / Visual CLI:** Full chessboard representation displaying file labels (`A`–`H`), rank numbers (`1`–`8`), piece symbols (`wK`, `bp`, etc.), and alternating light/dark (`##`) square patterns.
* **Turn / Game Engine:** Turn state machine alternating active color (`WHITE` / `BLACK`) starting with `WHITE`.
* **Notation Parser (`MoveParser`):** Sanitizes inputs, handles arbitrary whitespace and case variations, converts algebraic coordinates (e.g., `E2`) to 0-indexed matrix array offsets.
* **Crash-Resilient Loop:** Exception-handled prompt loop preventing program termination on malformed inputs.
* **Polymorphic Movement Architecture:** Abstract `Piece` parent class with concrete piece implementations (`King`, `Queen`, `Knight`, `Pawn`, `Rook`, `Bishop`) providing `possibleMoves(Board)`.
* **Piece Legality / Boundary Checks:** Validates target coordinates are within grid boundaries (0–7) and prevents capturing friendly pieces.

### Known Limitations / Unimplemented Rules (Phase 1 Backend Scope)

* **Castling (`O-O`, `O-O-O`):** Not enforced in Phase 1 scope.
* **Pawn Promotion:** Standard 1-step/2-step pawn movement and diagonal captures are supported; special en-passant and promotion syntax (`E7 E8=Q`) are not active.
* **Check / Checkmate State Resolution:** State detection engine tracks board positions and move legality; full checkmate detection loop is deferred to Phase 2.

---

# Phase 2 — Java Swing Chess GUI

## Overview

Phase 2 extends the existing Phase 1 Java chess backend with an interactive graphical user interface developed using Java Swing.

The application allows two players to select and move chess pieces using mouse clicks, capture opposing pieces, and receive a winner notification when a King is captured.

Phase 2 focuses on GUI functionality and event handling. Full chess movement legality validation is not required for this phase.

## Phase 2 Source Code

The Phase 2 implementation is located primarily in the following files:

| File | Responsibility |
|---|---|
| `src/gui/ChessFrame.java` | Main application window, menu integration, GUI layout, and winner notification |
| `src/gui/BoardPanel.java` | 8×8 visual chessboard and mouse-listener registration |
| `src/gui/SquareView.java` | Individual interactive squares and piece rendering |
| `src/gui/ChessMouseController.java` | Mouse selection and movement requests |
| `src/gui/GameMenuBar.java` | New Game, Save, Load, and Exit menu controls |
| `src/gui/GameHistoryPanel.java` | Move history, captured pieces, and Undo button |
| `src/game/GameState.java` | Centralized movement, turn handling, captures, and Undo |
| `src/game/GameStorage.java` | Game saving and loading |
| `src/game/MoveRecord.java` | Stores individual moves and capture information |

The Phase 1 classes under `src/board/`, `src/pieces/`, and `src/utils/` are reused and extended where necessary.

## Building and Running Phase 2

**Requirements:** Java Development Kit (JDK) 17 or newer.

Run these commands from the repository's root directory.

### macOS / Linux

```bash
mkdir -p out
javac -d out $(find src -name "*.java")
java -cp out gui.ChessFrame
```

### Windows PowerShell

```powershell
New-Item -ItemType Directory -Force out
javac -d out (Get-ChildItem -Path src -Recurse -Filter *.java).FullName
java -cp out gui.ChessFrame
```

## Implemented GUI Features

### Core Requirements

- Interactive 8×8 Java Swing chessboard displaying all 32 starting pieces.
- Alternating light and dark squares with coordinate labels A–H and 1–8.
- Mouse-based piece selection, highlighting, movement, and captures.
- Alternating player turns.
- Prevention of capturing friendly pieces.
- Winner notification when a King is captured.
- Application termination after the winner announcement is acknowledged.

### Extra GUI Features

**1. Menu Bar and Game Controls**

- New Game resets the board and game state.
- Save Game stores the current game.
- Load Game restores a previously saved game.
- Exit closes the application.

**2. Game History and Undo**

- Move history updates after successful moves.
- Captured pieces are displayed separately for White and Black.
- Undo reverses the most recent move and restores captured pieces.
- Game history and captured-piece displays synchronize after Undo, New Game, and Load Game.

## Testing

The Phase 2 implementation was tested through manual GUI interactions and automated Java tests.

**Automated tests:**

- `GameStateTest.java` — 9 test groups passed.
- `GameStorageTest.java` — 4 test groups passed.

To run the automated tests after compiling:

```bash
java -cp out game.GameStateTest
java -cp out game.GameStorageTest
```

Manual testing verified mouse selection, movement, captures, alternating turns, Undo, Save/Load, and the King-capture winner notification.

## Phase 2 Limitations

Full chess movement legality validation is intentionally not enforced in Phase 2, consistent with the assignment instructions. Players may move pieces to destination squares without applying all standard chess movement rules.

The application declares the winner when the opposing King is captured rather than evaluating traditional checkmate conditions.

## Project Submission

- **GitHub Repository:** https://github.com/fns33/trifecta
- **Phase 1:** Console-based chess backend.
- **Phase 2:** Java Swing GUI and supporting game-state functionality.
- **Documentation:** Phase 2 GUI Screenshot Report (submitted separately as a PDF).