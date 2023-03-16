# Factory Design Pattern for Sudoku

## What is the Factory Design Pattern?
The factory design pattern is a creational design pattern used to define an interface for creating objects from a superclass, but allows for the subclasses to determine the objects they would like to instantiate.
Benefits to the Factory Design Pattern:
- Promotes loose coupling between the creator and concrete products
- Single Responsibility Principle
 - Object creation code can be placed into a single place
- Open Closed
 - New Objects can be introduced to the factory without needing to update the rest of the code

## How is it used in the Sudoku Program?
In the Sudoku Program the Factory Design Pattern is used to create Sudoku Boards, specifically by determining which difficulty of board is to be created.
The SudokuBoard interface defines a common interface for all SudokuBoards to use, and is used for the Easy, Medium, and Hard boards.
The SudokuBoardFactory then creates a Sudoku Board based on what the difficulty user specifies in the GUI.

## Why have we used it?
We have made use of the Factory pattern as we have 3 different difficulties in the game, this could be expanded in the future into many different difficulties or types of sudoku puzzles.
The factory method supports modularity because of the Open-Closed principle, allowing for new difficulties and puzzle types to be easily introduced in the future.

# UML
![Factory UML](../data/Factory-UML.png)
