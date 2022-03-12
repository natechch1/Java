package lab2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomAgent extends Agent {

    public RandomAgent(Board board) {
        super(board);
    }

    /**
     * Gets a valid random move the RandomAgent can do.
     * @return a valid Move that the RandomAgent can perform on the Board
     */
    @Override
    public Move getMove() { // TODO
    	Random random = new Random();
    	int randomNum;
    	List<Cell> storePossibleCells = new ArrayList<Cell>();
    	List<Cell> storePossibleDes = new ArrayList<Cell>();
    	Cell fromCell;
    	Cell toCell;
    	Move move;
    	do {
    		
    		//get random cell
        	storePossibleCells = board.getPossibleCells();
        	randomNum = random.nextInt(storePossibleCells.size());
        	fromCell = storePossibleCells.get(randomNum);
        	
        	//get random move
        	storePossibleDes = board.getPossibleDestinations(fromCell);
        	randomNum = random.nextInt(storePossibleDes.size());
        	toCell = storePossibleDes.get(randomNum);
        	
        	// assign it to move
        	move = new Move(fromCell, toCell);
        	
        	
		} while (!board.isValidMove(move));
    	
    	System.out.println("[" + board.getTurn().toString() + " (Random Agent)] Moving piece " + fromCell.getCoordinate().toString() + " to " + toCell.getCoordinate().toString() + ".");
        return move;
    }
}
