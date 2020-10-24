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
    public RowGameGUI gameView;


    /**
     * Creates a new game initializing the GUI.
     */
    public RowGameController() {
		gameModel = new RowGameModel();
		gameView = new RowGameGUI(this);
	
		resetGame();
    }

    public RowGameModel getModel() {
		return this.gameModel;
    }

    public RowGameGUI getView() {
		return this.gameView;
    }

    public void startUp() {
		gameView.gui.setVisible(true);
    }

    /**
     * Moves the current player into the given block.
     *
     * @param block The block to be moved to by the current player
     */
    public void move(JButton block) {
	gameModel.movesLeft = gameModel.movesLeft - 1;

	gameView.update(gameModel);
    }

    /**
     * Ends the game disallowing further player turns.
     */
    public void endGame() {
		for(int row = 0;row<3;row++) {
	    	for(int column = 0;column<3;column++) {
				this.gameModel.blocksData[row][column].setIsLegalMove(false);
	    	}
		}

		gameView.update(gameModel);
    }

    /**
     * Resets the game to be able to start playing again.
     */
    public void resetGame() {
        for(int row = 0;row<3;row++) {
            for(int column = 0;column<3;column++) {
                gameModel.blocksData[row][column].reset();
		// Enable the bottom row
	        gameModel.blocksData[row][column].setIsLegalMove(row == 2);
            }
        }
		gameModel.player = "1";
		gameModel.movesLeft = 9;
		gameModel.setFinalResult(null);
		gameView.update(gameModel);
    }
}
