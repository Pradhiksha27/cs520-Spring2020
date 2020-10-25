package controller;

import model.RowGameModel;

public class RGCThreeInARowRules implements RowGameRulesStrategy 
{

	//Resets the game to play again.
	@Override
	public void resetGame(RowGameModel gameModel, int numRows, int numCols) 
	{
		for (int row = 0; row < numRows; row++) 
		{
			for (int column = 0; column < numCols; column++) 
			{
				gameModel.blocksData[row][column].reset();
				gameModel.setIsLegal(row, column, row == numRows - 1);
			}
		}

		gameModel.player = "1";
		gameModel.movesLeft = numRows * numCols;
		gameModel.setFinalResult(null);

	}

	//Sets the values isLegalMove of based on the current move indices.
	@Override
	public void legalMoves(RowGameModel gameModel, int rowIndex, int colIndex) 
	{
		gameModel.setIsLegal(rowIndex, colIndex, false);

		if (rowIndex > 0) 
		{
			gameModel.setIsLegal(rowIndex - 1, colIndex, true);
		}
	}
}