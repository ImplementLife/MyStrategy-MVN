# My Strategy: 2D Game Project

## How to Run the Project
To run the project, follow these steps:
1. Open IntelliJ IDEA and start the 'Boot' class.
2. Alternatively, you can build the .jar file using Maven with the command:
      ```
      mvn clean package
      ```
   Then, run the .jar file using:
      ```
      java -jar ./target/1FOut/Client.jar
      ```

## Gameplay
The gameplay is currently in development, but you can use the following keys:
- 'T': Create a tank squad.
- 'Y': Create an enemy tank squad.
- 'G': Create a soldier squad.
- 'H': Create an enemy soldier squad.
- 'P': Pause the game.
- 'F2': Take a screenshot.
- 'V': Generate an explosion.
- 'Left Alt': Show HP (Hit Points).
- 'Left Ctrl': Select multiple squads by rectangle near units.
- 'RMB' (Right Mouse Button): Move selected squad.
- Soldiers can shoot at enemies.

Additional Controls:
- 'B': Enable curve creation.
    - 'LMB' (Left Mouse Button): If 'curve create' is enabled, you can add points to create a Bezier curve.

In the future, this feature will be used for creating roads and railroads.
