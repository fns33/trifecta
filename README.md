# team: trifecta
# members: Nicole Leondike, Jennifer Buenteo, Tony McFarlan
# course: CS 3354.004 Fa2026

# CS 3354 Console-Based Chess Game (Phase 1 CLI Backend)

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