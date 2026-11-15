<!-- AI Usage Log - fns33 (Repo Owner) 
Session 1: Repository Setup Planning - 
Date: 2026-10-19 - AI Tool: Gemini Notebook - Prompts Provided: Requested Phase 1 project plan breakdown, milestone delivery schedule, responsibilities, git commands, and repository setup guidance. - Code/Artifacts Generated: \`docs/PHASE1\_PLAN.md\`, PDF/DOCX project plans, and setup walkthroughs. - Modifications/Verification: Verified 4 milestones across 2 stage deadlines, customized member responsibilities, and configured strict git branch workflow rules. -->

// new log structure following lcs2 example:

//----------------------------------

<!-- # Entry 2 — Peer Code Review of Member 1's Core Classes

**Date:** October 22, 2026

**Member:** fns33

**Task:** Perform code review and validation on Member 1's (`lcx2`) Pull Request containing `Position.java`, `Color.java`, and `Piece.java`.

### AI Prompt / Assistance Requested

Requested code review analysis of Member 1's implementation of `Position.java`, `Color.java`, and `Piece.java` to identify syntax errors, package discrepancies, and missing methods needed for Milestone 1 CLI integration.

### Modifications Made

- Reviewed Member 1's diff across `src/board/Position.java`, `src/pieces/Color.java`, and `src/pieces/Piece.java`.
- Flagged missing abstract `getSymbol()` method in `Piece.java` required by `Board.display()` for rendering CLI piece representations.
- Verified coordinate conversion math in `Position.toChessNotation()` against the 8x8 matrix indexing convention in `Board.java`.
- Formatted structured peer review feedback for submission on GitHub.

### Outcome / Feedback

Conducted a thorough peer code review fulfilling Git workflow rubric requirements. This helped me understand how to do proper review of pull requests. 
**Testing status:** Identified compilation issue in `Piece.java` via static code analysis prior to build execution.
**Pull request status:** Left peer review comments on Member 1's PR requesting minor fixes prior to final merge into `dev`. -->

//----------------------------------

<!-- # Entry 3 — Board Matrix Implementation and CLI Display Rendering 

**Date:** October 22, 2026 

**Member:** fns33 (Repo Owner) 

**Task:** Develop the 8×8 `Board` matrix initialization, standard starting piece placements, and text-based CLI display rendering engine. 

### AI Prompt  Assistance Requested Requested an updated version of `Board.java` matching Member 1's package architecture (`package pieces;` for `Color`, `Position` getters), including 8×8 matrix representation, 32 starting piece placements, and CLI board rendering logic.
 
### Modifications Made - Implemented `src/board/Board.java` using a 2D `Piece[][]` grid matrix aligned with Member 1's `Piece`, `Position`, and `Color` definitions. - Developed `initializeBoard()` method to clear the matrix and populate all 32 white and black chess pieces in standard starting positions. - Added `getPiece()` and `setPiece()` helper methods with null-checking for board position management. - Developed `display()` method to render rank labels (1–8), file labels (A–H), dark square indicators (##), and piece symbols. 

### Outcome / Feedback 
Successfully implemented the core `Board` class aligned with Member 1's class and package architecture. Verified 0-indexed row mapping to ranks (row 0 = Rank 8, row 7 = Rank 1) and alternating dark square modulo calculations `(r + c) % 2`. 
**Testing status:** awaiting pr updates for pieces.java to complete matrix setup and CLI display rendering through local compilation and execution in VS Code.  -->

//----------------------------------

<!-- 
# Entry 3 — King and Queen Piece Movement Logic 

**Date:** October 24, 2026 

**Member:** fns33 (Repo Owner)  **Task:** Implement Milestone 3 concrete piece movement logic for `King` and `Queen` subclasses and update abstract `Piece` class. 

### AI Prompt Assistance Requested Requested prepare implementations for `King.java` and `Queen.java` with Javadoc documentation, two-character CLI symbols (`wK`, `bK`, `wQ`, `bQ`), and `possibleMoves(Board board)` implementations including board boundary validation (0–7) and collision rules. 

### Modifications Made - Created `src/pieces/King.java` implementing 8-direction single-step movement array. - Created `src/pieces/Queen.java` implementing 8-direction raycasting for sliding horizontal, vertical, and diagonal moves. 

### Outcome / Feedback
Learned implementation technique for individual pience direction control and began familiarizing more with javdoc comments as implemented. 
 -->

//----------------------------------

<!-- 
# Entry 4 — Integration of Member 2's Utils Package & Git Workflow

**Date:** October 25, 2026 

**Member:** fns33 (Repo Owner)  

**Task:** Refactor `Board.java` to delegate CLI rendering to Member 2's `BoardPrinter.java` (Task M1-T4) and `DisplayUtils.java` (Task M1-T5), resolve compiler errors, and standardize the AI log directory structure under `docs/AI-usage/`.

### AI Prompt Assistance Requested assistance refactoring `Board.java`'s `display()` method to delegate to `BoardPrinter.print(this)` and `DisplayUtils.getPieceSymbol(piece)`, diagnosing `javac` compilation errors (`Move.java` type capitalization, stray text in `BoardPrinter.java`), and restructuring `docs/AI-usage/`.

### Modifications Made - Refactored `src/board/Board.java` to delegate board rendering to `BoardPrinter.print(this);`. - Resolved `javac` compiler errors across `Move.java` and `BoardPrinter.java`. - Staged `Board.java`, `Main.java`, and `.gitkeep` deletions. - Relocated \`fns33.md\` from \`docs/ai-usage/` to `docs/AI-usage/` via `git mv`. 

### Outcome / Feedback Gained a better understanding of decoupling rendering logic from board state management in Java OOP architecture, while maintaining clean Git commit histories. -->

//----------------------------------

//----------------------------------

<!--
# Entry 5 — GameState & GameStorage GUI Integration

**Date:** November 15, 2026

**Member:** fns33 (Repo Owner)

**Task:** Integrate the backend `GameState` and `GameStorage` classes with the existing Java Swing GUI, incorporate Member 2's `GameHistoryPanel` and `GameMenuBar`, test GUI functionality, and commit/push changes to `feature/fns33`.

### AI Prompt

Requested assistance integrating `GameState` with `ChessFrame.java`, connecting `GameStorage` serialization functionality to GUI menu actions, and incorporating the teammate-created `GameHistoryPanel` and `GameMenuBar` without modifying their implementations. Also requested troubleshooting of GUI rendering issues involving Unicode chess symbols after resetting the game.

### Modifications Made

- Updated `src/gui/ChessFrame.java` to maintain a `GameState` instance and coordinate GUI interactions with the backend.
- Integrated `GameMenuBar` and `GameHistoryPanel` into the main application window.
- Connected New Game functionality to `GameState.resetGame()`.
- Connected Save Game and Load Game menu actions to `GameStorage.saveGame()` and `GameStorage.loadGame()`.
- Connected the Undo button to `GameState.undoLastMove()`.
- Implemented `refreshGameDisplay()` to synchronize the displayed board with the current `GameState`.
- Integrated winner notification dialogs using `GameState.isGameOver()` and `GameState.getWinner()`.
- Updated `src/gui/SquareView.java` to adjust white and black piece text colors while preserving the existing Unicode symbol rendering and font configuration.
- Added serialization support to `src/board/Board.java`, `src/board/Position.java`, and `src/pieces/Piece.java` to support GameStorage functionality.
- Preserved the existing implementations of Member 2's `GameHistoryPanel.java` and `GameMenuBar.java`.
- Compiled the project through VS Code PowerShell using `javac` and launched the application with `java -cp bin gui.ChessFrame`.
- Tested New Game, Save Game, Load Game, Undo with an empty history, and Exit.

### Outcome / Feedback

Successfully connected the existing Swing GUI to the backend game state and storage functionality. Verified that the application compiles, launches, resets, saves, loads, and exits correctly. Gained a better understanding of coordinating GUI components with a centralized game state, Java object serialization, and collaborative Git workflows. 
-->

//----------------------------------