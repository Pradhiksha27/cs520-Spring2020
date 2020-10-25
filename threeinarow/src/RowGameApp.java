import java.util.*;
import view.RowGameGUI;
import controller.RowGameController;

public class RowGameApp 
{
    /**                                                                             
     * Starts a new game in the GUI.
     */

    //Addressing issue #2 (Extensibility)
    public static void main(String[] args) 
    {
    	int numRows = 0;
    	int numCols = 0;

    	int choice = 1;

    	Scanner input = new Scanner(System.in);

    	System.out.print("No. of Rows: ");
    	numRows = input.nextInt();

    	System.out.print("No. of Columns: ");
    	numCols = input.nextInt();

    	System.out.print("Choose ThreeInARow (1) or TicTacToe (Any other number): ");
    	choice = input.nextInt();

    	if ((numRows < 3 && numCols < 3)) {
            System.out.print("Its always a draw!");
            return;
        }

    	if ((numRows <= 0) || (numCols <= 0)) 
    	{
            System.out.println("Error! Input valid dimensions.");
            return;
        }


		RowGameController newgame = new RowGameController(numRows, numCols, choice);
        new RowGameGUI(newgame, numRows, numCols);
        newgame.gameStrategy.resetGame(newgame.gameModel, numRows, numCols);
    }
}
