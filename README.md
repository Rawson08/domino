# Domino Game
##### This game has 2 different version, first is the one which runs with the Standard Input/Output command, while the other one is the JavaFX implementation of the game with interactive UI.

##### Currently, the game has wildcard method only for the player. The computer can not use wild card as it is supposed to work, instead it takes 0 as any other numbers and only places it next to another zero. It still has work to be done.

##### You will be able to find the design diagram for the Console version of the game. 

### Some changes possible for the game
 Currently, the shuffle method only shuffles the 28 dominos into different indexes but in the order they are created, i.e. [x, y] where x is always less than y. It can be further improved by shuffling around the dominos as well as rotating them randomly so that they are better shuffled and x can no longer always be less than y. But, that is something to be improved later on.

### End Game situation
When user is close to end game, and user only has a tile which cannot be used in the board, user still has to start playing, and then choose the tile. Only after that, the game will check who won the game and display the final screen with the winner and the score for computer and player.


### Bugs Found
~~1. When the player has a tile with both zero, and it asks if the user wants to rotate it, then user selects 'No', it throws back error and forces the user to select yes to move ahead and place it in the board.~~ Bug fixed.


## Domino Game JavaFX update
### First off, please disregard the GUI folder because the JavaFX codes are just the updates to the orginal Console version, and note that the game is incomplete.

#### I sincerely apologize for not being able to complete the game even with the extension provided. I had to out from Albuquerque and I left without my laptop, which caused me trouble with the assignment. I am having to use my iPad, leaving my laptop turned on at home and using it with the help of Google Remote Desktop software. This is a silly excuse and I am not hoping to get any kind of excuse for this, I just wwanted to keep it out there.

### Game information
The game is still incomplete. I was not able to complete the GUI part, but currently the game can only be controlled from the GUI. I am simply using a method to take user input for the commands and using those commands in the console version by creating two different threads. User can not see any update to the game board, or any graphics at all. The user can only control the gameplay, which is only visible in the console, standard input/output. 


The code is still broken and it has all of the 'in-progress' codes which are not usable as of now. 
The game is only using a TextInputDialog to take the user input in the GUI format, and take it and give it into the console version to get the game working. In other words, the game is working, but the graphics part is still incomplete.