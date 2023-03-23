# SWE4403 Group 1 Project Report (Sudoku game with Design Patterns)

# Group Members
- Brendan Doucette 3694084
- Francis Radford
- Paige Corbyn
- Grace Kiely
- Aradhan Furtado

# What is our project?
This project simulates a game of Sudoku with varying difficulty and board sizes. The game is programmed using the Java programming language, and makes use of the Java Swing GUI toolkit to develop a GUI for the game. 

## How to play Sudoku
The game operates under standard Sudoku rules, the goal is to complete the grid with each cell filled with numbers from 1 to the size of the board, for example, a 4x4 board would allow inputs of 1, 2, 3, and 4. Then each row must contain all four of those numbers, without any repitition, this applies as well to all columns in the board. Then the final requirements is that each sub-grid within the game also does not contain and repeated numbers. For exmaple, each sub-grid consists of a size which is the square root of the board size (So for a 4x4 board the sub-grids are 2x2). 
The game will initially generate random values to place into the board, the amount of values remaining in the board depends on the difficulty, where higher difficulties have less values given to the board from the start.
Below is an example of a completed Sudoku board, note that all of the above requirements are met in the game, the grey squares are computer generated values, while the white squares were filled by a player.

<img src="/data/CompletedGame.png"  width="300" height="300">

## How to run the game
The game is run through java, as such you must have the java runtime environment installed to run the game. Once it is installed, download the code from this Github page and place it into a directory. Once in this directory, open the terminal and navigate to the directory where you have placed the game.
Once this is complete, run the following commands in the terminal to compile, and play the game.
```
javac *.java
java SudokuGUI
```

# Features in the game
## Board Select
The first thing users see when they start the game is a screen asking them to select a difficulty and a board size, these options are check boxes that can be clicked to set the difficulty of the game. 
Easy removes the least elements from a complete board, hard removes the most, and medium sits in the middle. 
There are three board sizes to choose from, a 4x4 board which consists of 16 cells and 4 sub-grids, a 9x9 board which consists of 81 cells and 9 sub-grids, and a 16x16 board which consists of 256 cells and 16 sub-grids. 
Once the user has selected their settings and clicks the start game button a board is created with the chosen settings.
Below is an example of the board select screen.

<img src="/data/SelectBoard.png">

## Main Game
Once the user has started the game, they are greeted with a partially completed sudoku board. They are then tasked with completing the board by filling in each of the cells with a value of their choice to try and complete the puzzle. To fill them in, the user clicks them and types the number they wish to enter. Once the player has finished the puzzle and wants to check the answer, they press the "Check Board" button, which will check if they have completed the puzzle or have errors in their solution. Players can also press the "Reset Selections" button, which will clear all of their inputs from the board and bring them back to the initial board. The final button is the "New Game" button, which brings the user back to the board select screen, allowing them to start a new game with a different board. 
Below is an example of the screen users see when they first enter the main game

<img src="/data/MainGame.png" width="300" height="300">

# What patterns have we applied?
In the game we have applied the following Design Patterns: 
- Factory 
- Singleton
- Memento
- Prototype
- Iterator
- Decorator

Each of these patterns are detailed in their own documents, as seen in the links below.

# Individual Reports
- [Factory Pattern](https://github.com/SWE4403-Project/SWE4403-Project-Repo/blob/main/doc/FactoryPattern.md).
- [Singleton Pattern](https://github.com/SWE4403-Project/SWE4403-Project-Repo/blob/main/doc/Singleton.md).
- [Memento Pattern](https://github.com/SWE4403-Project/SWE4403-Project-Repo/blob/main/doc/Memento.md)
- [Prototype Pattern](https://github.com/SWE4403-Project/SWE4403-Project-Repo/blob/main/doc/Prototype.md)
- [Iterator Pattern](https://github.com/SWE4403-Project/SWE4403-Project-Repo/blob/main/doc/Iterator.md)
- [Decorator Pattern](https://github.com/SWE4403-Project/SWE4403-Project-Repo/blob/main/doc/Decorator.md)

# UML Diagram
