package view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.RowGameController;
import model.RowGameModel;


public class RowGameBoardView implements RowGameView
{
    public JButton[][] blocks;
    public JPanel gamePanel = new JPanel(new FlowLayout());

    //Addresses issue #1(Encapsulation)
    private int numRows;
    private int numCols;

    public RowGameBoardView(RowGameController gameController, int rows, int cols) 
    {
        super();

        JPanel game = new JPanel(new GridLayout(rows,cols));
        gamePanel.add(game, BorderLayout.CENTER);
	
        numRows = rows;
        numCols = cols;
        blocks = new JButton[rows][cols];

        gameController.gameModel.addPropertyChangeListener(new PropertyChangeListener() 
        {
            @Override
            public void propertyChange(PropertyChangeEvent evt) 
            {
                update(gameController.gameModel);
            }
        });

        // Initialize a JButton for each cell of the rows x cols game board.
        for(int row = 0; row<rows; row++) 
        {
            for(int column = 0; column<cols; column++) 
            {
                int chosenRow = row;
                int chosenCol = column;

                blocks[row][column] = new JButton();
                blocks[row][column].setPreferredSize(new Dimension(75,75));
                game.add(blocks[row][column]);
                
                blocks[row][column].addActionListener(new ActionListener() 
                {
                    public void actionPerformed(ActionEvent e) 
                    {
                        gameController.move(chosenRow, chosenCol);
                    }
                });
            }
        }	
    }

    /**
     * Updates the game view after the game model
     * changes state.
     *
     * @param gameModel The current game model
     */
    public void update(RowGameModel gameModel) 
    {
        for (int row = 0; row < numRows; row++) 
        {
	       for (int column = 0; column < numCols; column++) 
           {
		      this.updateBlock(gameModel, row, column);
	       } // end for col
	   } // end for row	
    }

    /**
     * Updates the block view at the given row and column 
     * after the game model changes state.
     *
     * @param gameModel The game model
     * @param row The row that contains the block
     * @param column The column that contains the block
     */
    public void updateBlock(RowGameModel gameModel, int row, int col) 
    {
        blocks[row][col].setText(gameModel.blocksData[row][col].getContents());
        blocks[row][col].setEnabled(gameModel.blocksData[row][col].getIsLegalMove());	
    }
}
