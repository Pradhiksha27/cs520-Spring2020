package controllerTestCases;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import controller.RowGameController;

public class ThreeInARowTests {
    private RowGameController gameController;

    @Before
    public void setUp() {
        gameController = null;
        gameController = new RowGameController(3, 3, 1);
        gameController.move(2, 1); //Value = "X"
        gameController.move(2, 0); //Value = "O"
        gameController.move(1, 0); //Value = "X"
 
    }

    @After
    public void tearDown() {
        gameController = null;
    }

    @Test
    public void resetGame() {
        gameController.gameStrategy.resetGame(gameController.gameModel, 3, 3);

        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                assertEquals(gameController.gameModel.blocksData[row][column].getContents(), "");
                if (row != 2) 
                {
                    assertEquals(gameController.gameModel.blocksData[row][column].getIsLegalMove(), false);
                } else 
                {
                    assertEquals(gameController.gameModel.blocksData[row][column].getIsLegalMove(), true);
                }
            }
        }

        assertEquals(gameController.gameModel.player, "1");
        assertEquals(gameController.gameModel.movesLeft, 9);
        assertEquals(gameController.gameModel.getFinalResult(), null);
    }

    @Test
    public void illegalMove() 
    {
        int oldMovesLeft = gameController.gameModel.movesLeft;
        gameController.move(1, 2); //Value = "O"
        //(1,2) is a illegal move since (2,2) has not been played yet
        assertEquals(gameController.gameModel.player, "2");
        assertEquals(gameController.gameModel.blocksData[1][2].getContents(), "");
        assertEquals(gameController.gameModel.blocksData[1][2].getIsLegalMove(), false);
        assertEquals(gameController.gameModel.movesLeft, oldMovesLeft);
    }

    @Test
    public void legalMove() {
        int oldMovesLeft = gameController.gameModel.movesLeft;
        gameController.move(0, 0);
        //(0,0) is a legal move since (1,0) has been played
        assertEquals(gameController.gameModel.player, "1"); 
        assertEquals(gameController.gameModel.blocksData[0][0].getContents(), "O");
        assertEquals(gameController.gameModel.movesLeft, oldMovesLeft - 1); // since it is a legal move movesLeft should be decreased by 1
    }

    @Test
    public void tieMatch() {
        gameController.gameStrategy.resetGame(gameController.gameModel, 3, 3);
        gameController.move(2, 0); // X
        gameController.move(2, 1); // O
        gameController.move(1, 0); // X
        gameController.move(1, 1); // O
        gameController.move(0, 1); // X
        gameController.move(0, 0); // O
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
        //Player 1 wins!
        assertEquals(gameController.Winner(0, 0), 1);
        assertEquals(gameController.gameModel.getFinalResult(), "Player 1 is the winner!");
    }

}