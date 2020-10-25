package controller;

import model.RowGameModel;


public interface RowGameRulesStrategy
{
	//Resets the game to play again.
    public void resetGame(RowGameModel gameModel, int rows, int cols);

    //Sets the values isLegalMove of based on the current move indices.
    public void legalMoves(RowGameModel gameModel, int rowIndex, int colIndex);

}
