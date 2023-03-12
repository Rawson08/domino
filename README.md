# DominoConsoleGame.Domino Game
##### This game has 2 different version, first is the one which runs with the Standard Input/Output command, while the other one is the JavaFX implementation of the game with interactive UI.

##### Currently, the game has wildcard method only for the player. The computer can not use wild card as it is supposed to work, instead it takes 0 as any other numbers and only places it next to another zero. It still has work to be done.

##### You will be able to find the design diagram for the Console version of the game. 

### Some changes possible for the game
 Currently, the shuffle method only shuffles the 28 dominos into different indexes but in the order they are created, i.e. [x, y] where x is always less than y. It can be further improved by shuffling around the dominos as well as rotating them randomly so that they are better shuffled and x can no longer always be less than y. But, that is something to be improved later on.

### End Game situation
When user is close to end game, and user only has a tile which cannot be used in the board, user still has to start playing, and then choose the tile. Only after that, the game will check who won the game and display the final screen with the winner and the score for computer and player.


### Bugs Found
1. When the player has a tile with both zero, and it asks if the user wants to rotate it, then user selects 'No', it throws back error and forces the user to select yes to move ahead and place it in the board.