# ThreeInARow
This is a basic Java implementation of the Three in a Row game.

### How to build and test (from Terminal):
I've done all coding, debugging, compiling, running and testing by using JDK and Apache Ant. 

1. Run `ant document` to generate the javadoc (a hypertext description) for all of the java classes. 
2. Run `ant compile` to compile all of the java classes.
3. Run `ant test` to run all unit tests. 
To run a test file individually, load the project onto Eclipse, right click on the individual test file, click 'run as' and click on 'JUnit Test'. 


### How to run (from Terminal):

1. After building the project (i.e., running `ant`), run the following command in the threeinarow folder:
   `java -cp bin RowGameApp`
2. When prompted "No. of Rows:", enter a valid number to represent number of rows in the grid.
3. When prompted "No. of Columns:", enter a valid number to represent number of columns in the grid.
4. When prompted "Choose ThreeInARow (1) or TicTacToe (Any other number):", enter '1' to play ThreeInARow or any other valid number to play TicTacToe.
5. If the entered inputs are valid and satisfy necessary conditions, the UI Grid shows up. Else if the number of rows and columns are less than 3, the text "Game ends in a draw!" shows up.

### How to clean up (from Terminal):

1. Run `ant clean` to clean the project (i.e., delete all generated files).
