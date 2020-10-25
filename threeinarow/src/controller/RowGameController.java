package controller;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;

import model.RowGameModel;
import view.RowGameGUI;


/**
 * Java implementation of the 3 in a row game, using the Swing framework.
 *
 * This quick-and-dirty implementation violates a number of software engineering
 * principles and needs a thorough overhaul to improve readability,
 * extensibility, and testability.
 */
public class RowGameController {
    public static final String GAME_END_NOWINNER = "Game ends in a draw";

    public RowGameModel gameModel;
    public RowGameRulesStrategy gameStrategy;

    //Addresses issue #1(Encapsulation)
    private int numRows;
    private int numCols;

    /**
     * Creates a new game initializing the GUI.
     */
    public RowGameController(int rows, int cols, int gameRule) {
		gameModel = new RowGameModel(rows, cols);
		numRows = rows;
		numCols = cols;

		if(gameRule == 1)
		{
			gameStrategy = new RGCThreeInARowRules();
		}
		else
		{
			gameStrategy = new RGCTicTacToeRules();
		}
	
		gameStrategy.resetGame(gameModel, rows, cols);
    }

    public RowGameModel getModel() {
		return this.gameModel;
    }

    /**
     * Moves the current player into the given block.
     *
     * @param block The block to be moved to by the current player
     */
    //Addresses issue #3 (Testability)
    public void move(int row, int col) {
    	if(row < numRows && row >=0 && col < numCols && col >= 0 && gameModel.blocksData[row][col].getIsLegalMove() == false)
    	{
    		return;
    	}

		gameModel.movesLeft = gameModel.movesLeft - 1;
		String player = gameModel.player;
		int movesLeft = gameModel.movesLeft;

		int rowIndex = row;
		int colIndex = col;
		//Check if player 1 is the winner
		if (player.equals("1")) 
		{
			gameModel.setBlocksValue(rowIndex, colIndex, "X");
			gameStrategy.legalMoves(gameModel, rowIndex, colIndex);
			gameModel.setPlayer("2");

			if (Winner(rowIndex, colIndex) == 1) 
			{
				gameModel.setFinalResult("Player 1 is the winner!");
				endGame();
			} 
			else if (movesLeft == 0) 
			{
				gameModel.setFinalResult(GAME_END_NOWINNER);
			}
		} 
		//Check if player 2 is the winner
		else 
		{
			gameModel.setBlocksValue(rowIndex, colIndex, "O");
			gameStrategy.legalMoves(gameModel, rowIndex, colIndex);
			gameModel.setPlayer("1");

			if (Winner(rowIndex, colIndex) == 2) 
			{
				gameModel.setFinalResult("Player 2 is the winner!");
				endGame();
			} 
			else if (movesLeft == 0) 
			{
				gameModel.setFinalResult(GAME_END_NOWINNER);
			}
		}
    }

    /**
     * Ends the game disallowing further player turns.
     */
    //Addresses issue #2 (Extensibility)
    public void endGame() {
		for(int row = 0;row<numRows;row++) {
	    	for(int column = 0;column<numCols;column++) {
				this.gameModel.setIsLegal(row, column, false);
	    	}
		}
    }

    //Takes as input the row & column indices and returns the value of the corresponding blocksData if valid
    public String getContentIfValid(int rowIndex, int colIndex){
    	if(rowIndex < numRows && rowIndex >=0 && colIndex < numCols && colIndex >= 0){
    		return gameModel.blocksData[rowIndex][colIndex].getContents();
    	}
    	return "Invalid indices!";
    }

    //Return the 1,2,0 corresponding to whichever player has won the game
    public int Winner(int rowIndex, int colIndex) 
    {	
    	//Checking horizontally
		for (int i = 0; i < numCols - 2; i++) 
		{
			String stringCheck = (gameModel.blocksData[rowIndex][i].getContents() + gameModel.blocksData[rowIndex][i + 1].getContents() + gameModel.blocksData[rowIndex][i + 2].getContents());
			
			if (stringCheck.equals("XXX")) 
			{
				return 1;
			} 
			else if (stringCheck.equals("OOO")) 
			{
				return 2;
			}
		}
		//Checking vertically
		for (int j = 0; j < numRows - 2; j++) 
		{
			String stringCheck = (gameModel.blocksData[j][colIndex].getContents() + gameModel.blocksData[j + 1][colIndex].getContents() + gameModel.blocksData[j + 2][colIndex].getContents());
			
			if (stringCheck.equals("XXX")) 
			{
				return 1;
			} 
			else if (stringCheck.equals("OOO")) 
			{
				return 2;
			}
		}
		
		//Checking diagonally
		String d1 = getContentIfValid(rowIndex, colIndex) + getContentIfValid(rowIndex - 1, colIndex + 1) + getContentIfValid(rowIndex - 2, colIndex + 2);
		String d2 = getContentIfValid(rowIndex, colIndex) + getContentIfValid(rowIndex - 1, colIndex - 1) + getContentIfValid(rowIndex - 2, colIndex - 2);
		String d3 = getContentIfValid(rowIndex, colIndex) + getContentIfValid(rowIndex + 1, colIndex - 1) + getContentIfValid(rowIndex + 2, colIndex - 2);
		String d4 = getContentIfValid(rowIndex, colIndex) + getContentIfValid(rowIndex + 1, colIndex + 1) + getContentIfValid(rowIndex + 2, colIndex + 2);

		String d1d3 = getContentIfValid(rowIndex, colIndex) + getContentIfValid(rowIndex + 1, colIndex + 1) + getContentIfValid(rowIndex - 1, colIndex - 1);
		String d2d4 = getContentIfValid(rowIndex, colIndex) + getContentIfValid(rowIndex + 1, colIndex - 1) + getContentIfValid(rowIndex - 1, colIndex + 1);

		if (d1.equals("XXX") || d2.equals("XXX") || d3.equals("XXX") || d4.equals("XXX") || d1d3.equals("XXX") || d2d4.equals("XXX")) 
		{
			return 1;
		} 

		else if (d1.equals("OOO") || d2.equals("OOO") || d3.equals("OOO") || d4.equals("OOO") || d1d3.equals("OOO") || d2d4.equals("OOO")) 
		{
			return 2;
		}
		return 0;
	}
}
