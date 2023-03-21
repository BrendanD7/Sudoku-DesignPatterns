# Singleton Design Pattern for Sudoku

## What is the Singleton Design Pattern?
The Singleton design pattern is a creational design pattern that ensures a class only has one instance active, while providing a global access point to this instance.
It is useful when there should only be one instance of a certain object at a time.

## How is it used in the Sudoku Program?
In the Sudoku Program it is used to ensure there is only one instance of the factory class created at a time.
In the factory class there is an instance, as well as a get instance method. The constructor has been made private to ensure that no more than one can be created.
As such, the factory is accessed by the getInstance method in the client program.

## Why have we used it?
We have used the singleton to create the factories as we want to ensure that object creation is consistent. If there were several factories created, the boards could be created incorrectly. Having global access to the factory also makes it easier to manage object creation within the game.

# UML
![Singleton UML](../data/Singleton-UML.png)
