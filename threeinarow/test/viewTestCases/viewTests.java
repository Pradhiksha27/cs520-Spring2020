package viewTestCases;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import controller.RowGameController;
import static org.junit.Assert.*;
import controller.*;
import view.*;

public class viewTests 
{
    private RowGameController gameController;
    private RowGameBoardView gameView;

    @Before
    public void setUp() {
        gameController = null;
        gameController = new RowGameController(3, 3, 2);
        gameView = new RowGameBoardView(gameController, 3, 3);
    }

    @After
    public void tearDown() {
        gameController = null;
    }

    @Test
    public void testUpdateBlock() {
        gameController.move(0, 2);
        assertEquals(gameView.blocks[0][2].getText(), "X");
        assertEquals(gameView.blocks[0][2].isEnabled(), false);
    }

    @Test
    public void checkWinner() {
        gameController.gameStrategy.resetGame(gameController.gameModel, 3, 3);
        gameController.move(2, 0);
        gameController.move(2, 1);
        gameController.move(1, 0);
        gameController.move(1, 1);
        gameController.move(0, 0);

        assertEquals(gameController.gameModel.getFinalResult(), "Player 1 is the winner!");
    }

}