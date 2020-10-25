package controllerTestCases;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import controller.RowGameController;

public class TicTacToeTests {
    private RowGameController gameController;

    @Before
    public void setUp() {
        gameController = null;
        gameController = new RowGameController(3, 3, 2);
        gameController.move(1, 1); //Value = "X"
        gameController.move(0, 1); //Value = "O"
        gameController.move(2, 2); //Value = "X"
        gameController.move(1, 2); //Value = "O"
    }

    @After
    public void tearDown() {
        gameController = null;
    }

    @Test
    public void resetGame() {
        gameController.gameStrategy.resetGame(gameController.gameModel, 3, 3);

        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) 
            {
                assertEquals(gameController.gameModel.blocksData[row][column].getContents(), "");
                assertEquals(gameController.gameModel.blocksData[row][column].getIsLegalMove(), true);
            }
        }
        assertEquals(gameController.gameModel.player, "1");
        assertEquals(gameController.gameModel.movesLeft, 9);
        assertEquals(gameController.gameModel.getFinalResult(), null);
    }

    @Test
    public void illegalMove() {
        gameController.move(1, 2); //Attempting to overwrite O with X
        int oldMovesLeft = gameController.gameModel.movesLeft;
        assertEquals(gameController.gameModel.movesLeft, oldMovesLeft);
        assertEquals(gameController.gameModel.player, "1");
        assertEquals(gameController.gameModel.blocksData[1][2].getContents(), "O");

    }

    @Test
    public void legalMove() {
        gameController.move(0, 2); //Currently unset, so leagal move
        assertEquals(gameController.gameModel.blocksData[0][2].getContents(), "X");
    }

     @Test
    public void tieMatch() {
        gameController.gameStrategy.resetGame(gameController.gameModel, 3, 3);
        gameController.move(2, 0); // X
        gameController.move(2, 1); // O
        gameController.move(0, 1); // X
        gameController.move(0, 0); // O
        gameController.move(1, 0); // X
        gameController.move(1, 1); // O
        gameController.move(2, 2); // X
        gameController.move(1, 2); // O
        gameController.move(0, 2); // X
        assertEquals(gameController.Winner(0, 0), 0);
        assertEquals(gameController.gameModel.getFinalResult(), "Game ends in a draw");
    }

    @Test
    public void playerWin() {
        gameController.gameStrategy.resetGame(gameController.gameModel, 3, 3);
        gameController.move(2, 0); // X
        gameController.move(2, 1); // O
        gameController.move(1, 0); // X
        gameController.move(1, 1); // O
        gameController.move(0, 0); // X
        assertEquals(gameController.Winner(0, 0), 1);
        assertEquals(gameController.gameModel.getFinalResult(), "Player 1 is the winner!");
    }

}