package controller;

import model.RowGameModel;

public class RGCTicTacToeRules implements RowGameRulesStrategy 
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
				gameModel.setIsLegal(row, column, true);
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
	}
}