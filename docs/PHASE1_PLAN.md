<!-- Phase 1 Project Plan

#* 1. Executive Summary & Delivery Schedule

This project implements the backend and command-line interface (CLI) for a two- player Java chess game. Implementation is split into four distinct milestones across two delivery stages:

Stage 1 (Day 1): Milestone 1 & Milestone 2 (Board matrix, display, game loop, input parsing)
stage 2 (1 Week Later): Milestone 3 & Milestone 4 (Piece movement logic, legality rules, Javadoc, AI logs, final submission)
---

## 2. Team Structure & Branching Strategy

Repository Owner / Lead: fns33
Team Members: Member 1, Member 2
### Git Workflow Rules

1. Branching Model:

ain: Stable production code.
dev: Integration branch managed by Repo Owner.
feature/<github-username>: Isolated feature branches for each team member.
2. Pull Requests & Code Reviews:

All feature additions must be submitted via Pull Requests from feature/<github-username> into dev. Every team member must review and approve at least one teammate's PR into dev on GitHub.
Repo Owner manages final integration from dev into main.
3. Branch Retention: All remote feature branches and dev must remain active on Github until grading is finalized.

---

# 3. Pre-steps & setup Requirements

Repository Initialization: Initialize dev branch and commit •gitignore to exclude binaries (*-class), build directories (bin/, out/), and IDE configurations.
Project structure: Establish package layout under src/:
board: Board state and coordinate management.
pieces: Abstract and concrete piece definitions.
game: Game engine, turn control, and input parser.
utils: Display helpers and formatting tools.
3. Process Documentation:

Commit initial project plan to docs/PHASE1_PLAN.md.
Create individual AI usage logs in docs/ai-usage/<github-username>-md.
4. Stage 1: Milestones 1 & 2 (Due Day 1)
** Milestone 1: Core Skeleton, Board Matrix & CLI Display
Key Deliverables:
Coordinate and position abstractions.
Abstract piece base class.
8x8 Board matrix initialized with all 32 chess pieces in starting positions.
Text-based CLI display rendering rank (1-8) and file (A-H) Labels, dark square indicators (##), and piece symbols.
Responsibilities:
Repo Owner (fns33):
Repo Owner (fns33): Board matrix initialization, piece setup logic, CLI board rendering engine.
Member 1: Position class, abstract piece class structure, piece type/color enumerations.
Member 2: Display formatting utilities, string builders, rank/file coordinate translation helpers.
• Manual Test Cases:

verify 8x8 grid aligns correctly with rank numbers 1-8 and file letters A-H.
verify all 32 pieces spawn in standard chess initial positions.
verify dark and light squares alternate correctly (#* vs spaces).
• Reminders: Make frequent, small commits. Include initial Javadoc stubs as classes are created.

### Milestone 2: Input Parsing, Game Loop & Turn Management

Key Deliverables:
Game controller managing turn state (white moves first).
Move notation parser accepting [FROM] [TO] formats (e.g., E2 E4).
Case-insensitive input handling, whitespace trimming, and crash-proof input validation.
Responsibilities:
Member 1: Input parser, string tokenization, regex validation, user error feedback.
Member 2: Game loop flow, turn alternation logic, player turn state managemens-
Repoowner (fns33): Feature PR reviews into dev, error recovery integration, loop stability verification.
Manual Test Cases:
est valid move inputs (e2 e4, E2 E4).
Test invalid syntax (move pawn, 1234), out-of-bounds coords (Z9, A8), and empty strings.
Verify system re-prompts the player without crashing or skipping turns.
Reminders: Submit PRs from feature branches to dev. Document AI prompts used for input validation logic.
5, Stage 2: Milestones 3 & 4 (Due 1 Week Later)

#*# Milestone 3: Piece Movement Logic & Move Legality

Key Deliverables:
Concrete subclasses: Pawn, Rook, Knight, Bishop, Queen, King.
Move generation for each piece type.
Core legality checks: board boundaries, friendly capture prevention, turn ownership checks, and path obstruction detection for sliding pieces.
Responsibilities:
Repo Owner (fns33): King and Queen move rules, central validation pipeline, turn legality engine.
Membly 1: Rook and Bishop move rules, straight/diagonal line-of-sight ray casting.
Member 2: Knight L-shaped moves and Pawn movement (single step, initial double step, diagonal capture).
Manual Test Cases:
Test movement rules for all 6 piece types against valid and illegal targets.
Test path blocking (sliding pieces attempting to jump occupied squares).
Test capturing enemy pieces vs attempting to land on friendly pieces.
Reminders: Avoid last-minute code dumps. Maintain continuous integration into dev.
#* Milestone 4: Documentation, Javadoc, AI Logs & Final Delivery

Key Deliverables:
Javadoc annotations across all classes/methods and compiled Javadoc HTML.
Comprehensive README.md containing build instructions, execution steps, and sample gameplay walkthrough.
Feature summary detailing implemented chess rules and known scope limitations.
Audited AI logs for all team members in docs/ai-usage/.
Final dev to main merge and peer contribution evaluations (0-10 scale).
Responsibilities:
Member 1: Javadoc annotation audit, HTML documentation generation.
Member 2: README. md setup guide, execution steps, feature summary write-up.
Repo Owner (fns33): Final PR reviews, dev to main integration, repo clean-clone verification.
Manual Test Cases:
Perform aclean clone into an empty directory and verify execution using only
README. md steps.

Verify zero compiler warnings and clean Javadoc generation.
Reminders: Confirm all remote feature branches are visible on Github prior to submission.
#* 6, AI Usage Guidelines

All team members must record AI prompts, generated outputs, and manual code adjustments in docs/ai-usage/<github-username>-md, Entries must reflect active verification and understanding of all AI-p rated code. -->
