package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class RowGameModel 
{
    public static final String GAME_END_NOWINNER = "Game ends in a draw";

    public RowBlockModel[][] blocksData;

    /**
     * The current player taking their turn
     */
    public String player = "1";
    public int movesLeft = 9;
    private String finalResult = null;

    private final PropertyChangeSupport change = new PropertyChangeSupport(this);

    //Addresses issue #2 (Extensibility)
    public RowGameModel(int numRows, int numCols) 
    {
	    super();
        blocksData = new RowBlockModel[numRows][numCols];
        movesLeft = numRows * numCols;


	    for (int row = 0; row < numRows; row++) {
	        for (int col = 0; col < numCols; col++) {
	           blocksData[row][col] = new RowBlockModel(this);
	        } // end for col
	    } // end for row
    }

    public String getFinalResult() {
	   return this.finalResult;
    }

    //Assigns the value of this.finalResult to the "finalResult" parameter and fires an event.
    public void setFinalResult(String finalResult) {
        String oldResult = this.finalResult;
	    this.finalResult = finalResult;
        this.change.firePropertyChange("finalResult", oldResult, this.finalResult)
    }

    //Assigns this.player to the "player" parameter and fires an event.
      public void setPlayer(String player) {
        String oldPlayer = this.player;
        this.player = player;
        this.change.firePropertyChange("player", oldPlayer, this.player);
    }

    //Assigns the corresponding blocksdata value to the "value" parameter and fires an event.
    public void setBlocksValue(int rowIndex, int colIndex, String value) {
        this.blocksData[rowIndex][colIndex].setContents(value);
        this.change.firePropertyChange("value", "", value);
    }

    //Assigns the correspding blocksdata value to the "isLegal" parameter and fires an event.
    public void setIsLegal(int rowIndex, int colIndex, boolean isLegal){
        boolean oldValue = this.blocksData[rowIndex][colIndex].getIsLegalMove();
        this.blocksData[rowIndex][colIndex].setIsLegalMove(isLegal);
        this.change.firePropertyChange("isLegal", oldValue, isLegal);
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.change.addPropertyChangeListener(listener);
    }
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.change.removePropertyChangeListener(listener);
    }
}
