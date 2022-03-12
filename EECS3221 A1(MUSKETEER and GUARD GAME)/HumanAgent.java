package lab2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hamcrest.core.Is;

public class HumanAgent extends Agent {

    public HumanAgent(Board board) {
        super(board);
    }
    
    /**
     * This is a helper method print possible cells and get the from Cell coordinate
     * 
     * 
     */
    public String getFromCell() {
    	Scanner fromCellScanner = new Scanner(System.in);         // create a scanner
    	List<Cell> possibleCells = new ArrayList<>();         // get possible cell
    	List<String> storeCell = new ArrayList<String>(); 	  // store possible cell in String array list
    	String storeScannerString = null;                     //create a String to store the scanner coordinate
    	
    	possibleCells = board.getPossibleCells();			  //first get the possible move cell
    	
    	for (int i = 0; i < possibleCells.size(); i++) {
			storeCell.add(possibleCells.get(i).getCoordinate().toString());    // add the cell coordinate from Cell into String array list
		}
    	
    	System.out.printf("[%s] Possible destinations are %s. Enter where you want to move: ", board.getTurn().getType(), storeCell);
    	storeScannerString = fromCellScanner.next().toUpperCase();
    	
    	while (!storeCell.contains(storeScannerString)) {
          System.out.println(storeScannerString + " is an invalid destination.");
          System.out.printf("[%s] Possible destinations are %s. Enter where you want to move: ", board.getTurn().getType(), storeCell);
          storeScannerString = fromCellScanner.next().toUpperCase();
    	}
    	
    	//fromCellScanner.close();                                 // close the scanner
    	return storeScannerString;
	}
    
    
    /**
     * This helper method is to print all possible destination and get the to Cell coordinate. 
     *
     */
    
    public String getToCell(Cell fromCell) {
    	Scanner toCellScanner = new Scanner(System.in);         	 // create a scanner
    	List<Cell> possibleDes = new ArrayList<>();   		 // get possible destination
    	List<String> storeDes = new ArrayList<String>(); 	         // store possible destination in String array list
    	String storeScannerString = null;                            //create a String to store the scanner coordinate
    	
    	possibleDes = board.getPossibleDestinations(fromCell);
    	
    	for (int i = 0; i < possibleDes.size(); i++) {
			storeDes.add(possibleDes.get(i).getCoordinate().toString());
		}
    	
    	System.out.printf("[%s] Possible destinations are %s. Enter where you want to move: ", board.getTurn().getType(), storeDes);
    	storeScannerString = toCellScanner.next().toUpperCase();
    	
    	while (!storeDes.contains(storeScannerString)) {
            System.out.println(storeScannerString + " is an invalid destination.");
            System.out.printf("[%s] Possible destinations are %s. Enter where you want to move: ", board.getTurn().getType(), storeDes);
            storeScannerString = toCellScanner.next().toUpperCase();
        }
    	
    	//toCellScanner.close();
    	return storeScannerString;
	}
    
    /**
     * Asks the human for a move with from and to coordinates and makes sure its valid.
     * Create a Move object with the chosen fromCell and toCell
     * @return the valid human inputted Move
     */
    @Override
    public Move getMove() { // TODO
    	String storeScannerString = null;                     		 //create a String to store the scanner coordinate
    	Coordinate fromCellCoordinate;                       		 // its fromCell coordinate
    	Coordinate toCellCoordinate;                         		 // its toCell coordinate
    	Cell fromCell = null;                                        // its fromCell
    	Cell toCell = null;                                          // its toCell
    	Move getMove;                                                // store the move
    		
    	storeScannerString = getFromCell();
    	
    	try {
			fromCellCoordinate = Utils.parseUserMove(storeScannerString);
			fromCell = board.getCell(fromCellCoordinate);         // its fromCell with it coordinate
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	
    	//then get the possible destination
    	storeScannerString = getToCell(fromCell);
    	try {
		toCellCoordinate = Utils.parseUserMove(storeScannerString);
        toCell = board.getCell(toCellCoordinate);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	
        
        getMove = new Move(fromCell, toCell);                         // create a move which will contain final valid move 
        return getMove;
    }
}
