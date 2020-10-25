package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;

import model.RowGameModel;
import controller.RowGameController;


public class RowGameGUI implements RowGameView
{
    public JFrame gui = new JFrame("Three in a Row");
    public RowGameBoardView gameBoardView;
    public JButton reset = new JButton("Reset");
    public RowGameStatusView gameStatusView;
    
    private RowGameController gameController;


    /**
     * Creates a new game initializing the GUI.
     */
    public RowGameGUI(RowGameController gameController, int rows, int cols) 
    {
        this.gameController = gameController;
	
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(new Dimension(1000, 750));
        gui.setResizable(true);

        gameBoardView = new RowGameBoardView(this.gameController, rows, cols);
        JPanel gamePanel = gameBoardView.gamePanel;

        JPanel options = new JPanel(new FlowLayout());
        options.add(reset);

        gameStatusView = new RowGameStatusView(this.gameController);
        JPanel messages = gameStatusView.messages;

        gui.add(gamePanel, BorderLayout.NORTH);
        gui.add(options, BorderLayout.CENTER);
        gui.add(messages, BorderLayout.SOUTH);

        gui.setVisible(true);
        reset.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                gameController.gameStrategy.resetGame(gameController.gameModel, rows, cols);
            }
        });
    }

    /**
     * Updates the game view after the game model
     * changes state.
     *
     * @param gameModel The current game model
     */
    public void update(RowGameModel gameModel) 
    {
	   gameBoardView.update(gameModel);
	   gameStatusView.update(gameModel);
    }
}
