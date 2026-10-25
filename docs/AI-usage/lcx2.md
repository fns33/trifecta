# AI Usaage Log - Tony McFarlan (Member 1)

## Project foundation set up
I used AI to get the project structure in the most simplest form possible to optimize collaboration while prioritizng skill growth as a programmer while being mindful of my actual time commitment 

*AI prompt:* I asked Claude to give me a guide on how to structure the project, how said practices are displayed in the corporate world, a git/linux command cheat sheet, and how I should form a AI-usage log 

*Modifications Made:* 
- git add .
- git status
- git branch --show-current
- mkdir -p docs/AI-usage
- touch docs/AI-usaage/Lcx2.md

- *Correction/verificaton:* git branch -a & git 

*Output & Feedback:*
- Initially, I messed up the branching as my first attempt at branching was actually attached to main->feature/lcx2 instead of it being main->dev->feature/lcx2 which the simple mistake was due to arrogance and being complacent to just get the work done instead of being diligent. 

## Entry 2 — Git Branch Management and Repository Collaboration

**Date:** October 21, 2026

**Member:** lcx2 (Member 1)

**Task:** Configure my Git feature branch, resolve branch naming conflicts, and prepare for collaborative development using GitHub and Visual Studio Code.

### AI Prompt / Assistance Requested

Consulted ChatGPT about establishing the required `main → dev → feature/lcx2` branching workflow. Requested assistance resolving a capitalization conflict between `Feature/Lcx2` and `feature/lcx2`, understanding Git status messages, and configuring remote branch tracking.

### Modifications Made

- Created and configured the correctly named `feature/lcx2` branch.
- Identified and addressed a remote branch conflict caused by capitalization differences on macOS.
- Created `backup/lcx2-original` to preserve the original branch history.
- Deleted the incorrectly capitalized remote branch.
- Restored Git's normal fetching configuration.
- Verified that the local feature branch tracks `origin/feature/lcx2` and that the working tree was clean.

### Outcome / Feedback

Successfully established the correct feature branch for Member 1 development. Confirmed that the repository recognizes the team's remote branches without the previous naming conflict.

Gained a better understanding of the distinction between local and remote branches, staging, committing, pushing, and Git's treatment of case-sensitive branch names.

---

## Entry 3 — Position Class Implementation and Pull Request Preparation

**Date:** October 21, 2026

**Member:** lcx2 (Member 1)

**Task:** Develop the initial `Position` class and prepare my work for peer review.

### AI Prompt / Assistance Requested

Asked ChatGPT to explain Java compilation errors in my initial `Position.java` implementation, particularly syntax error involving curly braces.

Also requested guidance on compiling and testing a supporting Java class and creating a draft pull request from `feature/lcx2` into `dev`.

### Modifications Made

- Developed the initial structure of the `Position` class.
- Defined private `row` and `col` instance variables.
- Added a constructor intended to validate board coordinates.
- Added an `isValid()` method for checking the 0–7 coordinate boundaries.
- Added getter methods and a method for converting coordinates into algebraic chess notation.
- Identified that the original implementation closed the class before the constructor and methods.
- Reviewed the corrected class structure and the difference between compiling a supporting class and executing a Java program.

### Outcome / Feedback

AI-assisted review identified the cause of the original Java syntax errors and refined how class and method boundaries work in the corporate world.

A draft pull request was planned to allow teammates to review the implementation before integration into `dev`.

**Testing status:** Compilation and runtime test results still need to be documented.

**Pull request status:** Draft PR creation and peer feedback still need to be documented.

### Reflection

This exercise improved my understanding of Java class structure, constructors, encapsulation, static methods, and the GitHub review workflow. I also learned why intermediate commits and pull requests are important when multiple developers contribute to the same codebase.