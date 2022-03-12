package lab2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Board {
    public int size = 5;

    // 2D Array of Cells for representation of the game board
    public final Cell[][] board = new Cell[size][size];

    private Piece.Type turn;
    private Piece.Type winner;

    /**
     * Create a Board with the current player turn set.
     */
    public Board() {
        this.loadBoard("Boards/Starter.txt");
    }

    /**
     * Create a Board with the current player turn set and a specified board.
     * @param boardFilePath The path to the board file to import (e.g. "Boards/Starter.txt")
     */
    public Board(String boardFilePath) {
        this.loadBoard(boardFilePath);
    }

    /**
     * Creates a Board copy of the given board.
     * @param board Board to copy
     */
    public Board(Board board) {
        this.size = board.size;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                this.board[row][col] = new Cell(board.board[row][col]);
            }
        }
        this.turn = board.turn;
        this.winner = board.winner;
    }

    /**
     * @return the Piece.Type (Muskeeteer or Guard) of the current turn
     */
    public Piece.Type getTurn() {
        return turn;
    }

    /**
     * Get the cell located on the board at the given coordinate.
     * @param coordinate Coordinate to find the cell
     * @return Cell that is located at the given coordinate
     */
    public Cell getCell(Coordinate coordinate) { // TODO
       // board[coordinate.row][coordinate.col] = new Cell(board[coordinate.row][coordinate.col]);
        return board[coordinate.row][coordinate.col];
    }

    /**
     * @return the game winner Piece.Type (Muskeeteer or Guard) if there is one otherwise null
     */
    public Piece.Type getWinner() {
        return winner;
    }

    /**
     * Gets all the Musketeer cells on the board.
     * @return List of cells
     */
    public List<Cell> getMusketeerCells() { // TODO
        List<Cell> musketeerCell = new ArrayList<>();
        for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				if (board[row][col].hasPiece() && board[row][col].getPiece().getType().equals(Piece.Type.MUSKETEER)) {
					musketeerCell.add(board[row][col]);
				}
			}
		}
        return musketeerCell;
    }

    /**
     * Gets all the Guard cells on the board.
     * @return List of cells
     */
    public List<Cell> getGuardCells() { // TODO
    	List<Cell> guardCell = new ArrayList<>();
        for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				if (board[row][col].hasPiece() && board[row][col].getPiece().getType().equals(Piece.Type.GUARD)) {
					guardCell.add(board[row][col]);
				}
			}
		}
        return guardCell;
    }

    /**
     * Executes the given move on the board and changes turns at the end of the method.
     * @param move a valid move
     */
    public void move(Move move) { // TODO
    	Coordinate fromCellCoordinate = move.fromCell.getCoordinate();
    	Coordinate toCellCoordinate = move.toCell.getCoordinate();
    	
    	// set the piece into move which get from board 
    	move.fromCell.setPiece(board[fromCellCoordinate.row][fromCellCoordinate.col].getPiece());
    	move.toCell.setPiece(board[toCellCoordinate.row][toCellCoordinate.col].getPiece());
    	
    	// do the move on the piece on the board
    	board[toCellCoordinate.row][toCellCoordinate.col].setPiece(move.fromCell.getPiece());
    	board[fromCellCoordinate.row][fromCellCoordinate.col].setPiece(null);
		
		if (turn.equals(Piece.Type.MUSKETEER)) {    
			turn = Piece.Type.GUARD;
		}
		else {
			turn = Piece.Type.MUSKETEER;
		}
		
	
    }

    /**
     * Undo the move given.
     * @param move Copy of a move that was done and needs to be undone. The move copy has the correct piece info in the
     *             from and to cell fields. Changes turns at the end of the method.
     */
    public void undoMove(Move move) { // TODO
    	Coordinate fromCellCoordinate = move.fromCell.getCoordinate();
    	Coordinate toCellCoordinate = move.toCell.getCoordinate();
    	
//    	// set the piece into move which get from board 
//    	move.fromCell.setPiece(board[fromCellCoordinate.row][fromCellCoordinate.col].getPiece());
//    	move.toCell.setPiece(board[toCellCoordinate.row][toCellCoordinate.col].getPiece());
//    	
    	// do the move on the piece on the board
    	board[fromCellCoordinate.row][fromCellCoordinate.col].setPiece(move.fromCell.getPiece());
    	board[toCellCoordinate.row][toCellCoordinate.col].setPiece(move.toCell.getPiece());
		
    	
		if (turn.equals(Piece.Type.MUSKETEER)) {    
			turn = Piece.Type.GUARD;
		}
		else {
			turn = Piece.Type.MUSKETEER;
		}
				
			
    }

    
    /**
     * Checks if the given move is valid. Things to check:
     * (1) the toCell is next to the fromCell
     * (2) the fromCell piece can move onto the toCell piece.
     * @param move a move
     * @return     True, if the move is valid, false otherwise
     */
    public Boolean isValidMove(Move move) { // TODO
        if (move.fromCell.getPiece().canMoveOnto(move.toCell)) {    //here strange
        	if (move.fromCell.getCoordinate().col == move.toCell.getCoordinate().col) {
    			if (move.fromCell.getCoordinate().row == move.toCell.getCoordinate().row -1 || move.fromCell.getCoordinate().row == move.toCell.getCoordinate().row +1) {
    				return true;
    			}
    		}else if (move.fromCell.getCoordinate().row == move.toCell.getCoordinate().row) {
    			if (move.fromCell.getCoordinate().col == move.toCell.getCoordinate().col -1 || move.fromCell.getCoordinate().col == move.toCell.getCoordinate().col +1) {
    				return true;
    			}
    		}
		}
        return false;
    }
    
  
    
    
    /**
     * Get all the possible cells that have pieces that can be moved this turn.
     * @return      Cells that can be moved from the given cells
     */
    public List<Cell> getPossibleCells() { // TODO
        List<Cell> storeCells = new ArrayList<Cell>();
        
        // two for loop to find each cell
        for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				// If it exit piece
				if (board[row][col].hasPiece()) {
					// if it is Musketeer turn
					if (turn.equals(Piece.Type.MUSKETEER)) {
						// check if current cell is Musketeer
						if (board[row][col].getPiece().getType().equals(Piece.Type.MUSKETEER)) {
							// This array list is to store all possible destination of current cell
							List<Cell> possibleDestination = new ArrayList<Cell>();
							possibleDestination = getPossibleDestinations(board[row][col]);
							// If this cell has destination, it means this cell is a possible cell
							if (possibleDestination.size() != 0) storeCells.add(board[row][col]);
						}
					}
					
					// if it is Guard turn
					if (turn.equals(Piece.Type.GUARD)) {
						// check if current cell is Guard
						if (board[row][col].getPiece().getType().equals(Piece.Type.GUARD)) {
							// This array list is to store all possible destination of current cell
							List<Cell> possibleDestination = new ArrayList<Cell>();
							possibleDestination = getPossibleDestinations(board[row][col]);
							// If this cell has destination, it means this cell is a possible cell
							if (possibleDestination.size() != 0) storeCells.add(board[row][col]);
						}
					}
				}
				
			}
		}
       
        return storeCells;
    }
    
    /**
     * This one is the helper method to check all possible destination of Musketeer cell on the matrix 
     * @return   all  possible destination of Musketeer cell
     */
    public List<Cell> getMusketeerPossibleDestination(int row, int col){
    	List<Cell> storeDes = new ArrayList<Cell>();
    	
    	// situation for the board
		if (row == 0) {						// if this cell in the up border
			if (col == 0) {          	    // for board[0][0]
				if (board[row + 1][col].hasPiece() && board[row + 1][col].getPiece().getType().equals(Piece.Type.GUARD)) storeDes.add(board[row + 1][col]);
				if (board[row][col + 1].hasPiece() && board[row][col + 1].getPiece().getType().equals(Piece.Type.GUARD)) storeDes.add(board[row][col + 1]);
			}
			
			else if (col == 4) {            // for board[0][4]
				if (board[row + 1][col].hasPiece() && board[row + 1][col].getPiece().getType().equals(Piece.Type.GUARD)) storeDes.add(board[row + 1][col]);
				if (board[row][col - 1].hasPiece() && board[row][col - 1].getPiece().getType().equals(Piece.Type.GUARD)) storeDes.add(board[row][col - 1]);
				
			}
			else {							// other condition in row == 0
				if (board[row + 1][col].hasPiece() && board[row + 1][col].getPiece().getType().equals(Piece.Type.GUARD)) storeDes.add(board[row + 1][col]);
				if (board[row][col - 1].hasPiece() && board[row][col - 1].getPiece().getType().equals(Piece.Type.GUARD)) storeDes.add(board[row][col - 1]);
				if (board[row][col + 1].hasPiece() && board[row][col + 1].getPiece().getType().equals(Piece.Type.GUARD)) storeDes.add(board[row][col + 1]);
			}
		}
		else if (row == 4) {				   	    // If this cell in the down border
			if (col == 0) {                 // for board[4][0]
				if (board[row - 1][col].hasPiece() && board[row - 1][col].getPiece().getType().equals(Piece.Type.GUARD)) storeDes.add(board[row - 1][col]);
				if (board[row][col + 1].hasPiece() && board[row][col + 1].getPiece().getType().equals(Piece.Type.GUARD)) storeDes.add(board[row][col + 1]);
			}
			else if (col == 4) {            // for board[4][4]
				if (board[row - 1][col].hasPiece() && board[row - 1][col].getPiece().getType().equals(Piece.Type.GUARD)) storeDes.add(board[row - 1][col]);
				if (board[row][col - 1].hasPiece() && board[row][col - 1].getPiece().getType().equals(Piece.Type.GUARD)) storeDes.add(board[row][col - 1]);
			}
			else {							// other condition in row == 4
				if (board[row - 1][col].hasPiece() && board[row - 1][col].getPiece().getType().equals(Piece.Type.GUARD)) storeDes.add(board[row - 1][col]);
				if (board[row][col - 1].hasPiece() && board[row][col - 1].getPiece().getType().equals(Piece.Type.GUARD)) storeDes.add(board[row][col - 1]);
				if (board[row][col + 1].hasPiece() && board[row][col + 1].getPiece().getType().equals(Piece.Type.GUARD)) storeDes.add(board[row][col + 1]);
			}
		}
		else if (col == 0) {                     // If this cell in the left border
			// Here we dont't need to discuss 4 corner situation because we discuss it before
			// We just discuss other cell in this column
			if (board[row - 1][col].hasPiece() && board[row - 1][col].getPiece().getType().equals(Piece.Type.GUARD)) storeDes.add(board[row - 1][col]);
			if (board[row + 1][col].hasPiece() && board[row + 1][col].getPiece().getType().equals(Piece.Type.GUARD)) storeDes.add(board[row + 1][col]);
			if (board[row][col + 1].hasPiece() && board[row][col + 1].getPiece().getType().equals(Piece.Type.GUARD)) storeDes.add(board[row][col + 1]);
		}
		else if (col == 4) {                     // If this cell in the right border
			// Here we dont't need to discuss 4 corner situation because we discuss it before
			// We just discuss other cell in this column
			if (board[row - 1][col].hasPiece() &&board[row - 1][col].getPiece().getType().equals(Piece.Type.GUARD)) storeDes.add(board[row - 1][col]);
			if (board[row + 1][col].hasPiece() &&board[row + 1][col].getPiece().getType().equals(Piece.Type.GUARD)) storeDes.add(board[row + 1][col]);
			if (board[row][col - 1].hasPiece() &&board[row][col - 1].getPiece().getType().equals(Piece.Type.GUARD)) storeDes.add(board[row][col - 1]);
		}
    	else{                                    // If this cell is not in the any border
    		if (board[row - 1][col].hasPiece() && board[row - 1][col].getPiece().getType().equals(Piece.Type.GUARD)) storeDes.add(board[row - 1][col]);
    		if (board[row + 1][col].hasPiece() && board[row + 1][col].getPiece().getType().equals(Piece.Type.GUARD)) storeDes.add(board[row + 1][col]);	
    		if (board[row][col - 1].hasPiece() && board[row][col - 1].getPiece().getType().equals(Piece.Type.GUARD)) storeDes.add(board[row][col - 1]);
    		if (board[row][col + 1].hasPiece() && board[row][col + 1].getPiece().getType().equals(Piece.Type.GUARD)) storeDes.add(board[row][col + 1]);
		}
		
    	return storeDes;
    }
    
    
    /**
     * This one is the helper method to check all possible destination of Guard cell on the matrix 
     * @return   all  possible destination of Guard cell
     */
    public List<Cell> getGuardPossibleDestination(int row, int col){
    	List<Cell> storeDes = new ArrayList<Cell>();
    	
    	// situation for the board
		if (row == 0) {						// if this cell in the up border
			if (col == 0) {          	    // for board[0][0]
				if (board[row + 1][col].hasPiece() == false) storeDes.add(board[row + 1][col]);
				if (board[row][col + 1].hasPiece() == false) storeDes.add(board[row][col + 1]);
			}
			
			else if (col == 4) {            // for board[0][4]
				if (board[row + 1][col].hasPiece() == false) storeDes.add(board[row + 1][col]);
				if (board[row][col - 1].hasPiece() == false) storeDes.add(board[row][col - 1]);
				
			}
			else {							// other condition in row == 0
				if (board[row + 1][col].hasPiece() == false) storeDes.add(board[row + 1][col]);
				if (board[row][col - 1].hasPiece() == false) storeDes.add(board[row][col - 1]);
				if (board[row][col + 1].hasPiece() == false) storeDes.add(board[row][col + 1]);
			}
		}
		else if (row == 4) {				   	    // If this cell in the down border
			if (col == 0) {                 // for board[4][0]
				if (board[row - 1][col].hasPiece() == false) storeDes.add(board[row - 1][col]);
				if (board[row][col + 1].hasPiece() == false) storeDes.add(board[row][col + 1]);
			}
			else if (col == 4) {            // for board[4][4]
				if (board[row - 1][col].hasPiece() == false) storeDes.add(board[row - 1][col]);
				if (board[row][col - 1].hasPiece() == false) storeDes.add(board[row][col - 1]);
			}
			else {							// other condition in row == 4
				if (board[row - 1][col].hasPiece() == false) storeDes.add(board[row - 1][col]);
				if (board[row][col - 1].hasPiece() == false) storeDes.add(board[row][col - 1]);
				if (board[row][col + 1].hasPiece() == false) storeDes.add(board[row][col + 1]);
			}
		}
		else if (col == 0) {                     // If this cell in the left border
			// Here we dont't need to discuss 4 corner situation because we discuss it before
			// We just discuss other cell in this column
			if (board[row - 1][col].hasPiece() == false) storeDes.add(board[row - 1][col]);
			if (board[row + 1][col].hasPiece() == false) storeDes.add(board[row + 1][col]);
			if (board[row][col + 1].hasPiece() == false) storeDes.add(board[row][col + 1]);
		}
		else if (col == 4) {                     // If this cell in the right border
			// Here we dont't need to discuss 4 corner situation because we discuss it before
			// We just discuss other cell in this column
			if (board[row - 1][col].hasPiece() == false) storeDes.add(board[row - 1][col]);
			if (board[row + 1][col].hasPiece() == false) storeDes.add(board[row + 1][col]);
			if (board[row][col - 1].hasPiece() == false) storeDes.add(board[row][col - 1]);
		}
		else {     // If this cell is not in the any border
    		if (board[row - 1][col].hasPiece() == false) storeDes.add(board[row - 1][col]);
    		if (board[row + 1][col].hasPiece() == false) storeDes.add(board[row + 1][col]);	
    		if (board[row][col - 1].hasPiece() == false) storeDes.add(board[row][col - 1]);
    		if (board[row][col + 1].hasPiece() == false) storeDes.add(board[row][col + 1]);
		}
		
    	return storeDes;
    }

    
    /**
     * Get all the possible cell destinations that is possible to move to from the fromCell.
     * @param fromCell The cell that has the piece that is going to be moved
     * @return List of cells that are possible to get to
     */
    public List<Cell> getPossibleDestinations(Cell fromCell) { // TODO
    	List<Cell> storeDes = new ArrayList<Cell>();
    	int row = fromCell.getCoordinate().row;
    	int col = fromCell.getCoordinate().col;
    	
    	if (board[row][col].hasPiece()) {
    		// if this cell is Musketeer
        	if (board[row][col].getPiece().getType().equals(Piece.Type.MUSKETEER)) {
    			storeDes = getMusketeerPossibleDestination(row, col);
        	}
        	
        	// if this cell is Guard
        	if (board[row][col].getPiece().getType().equals(Piece.Type.GUARD)) {
    			storeDes = getGuardPossibleDestination(row, col);
        	}
		}
    	
        return storeDes;
    }

    
    /**
     * Get all the possible moves that can be made this turn.
     * @return List of moves that can be made this turn
     */
    public List<Move> getPossibleMoves() { // TODO
    	List<Move> storeMoves = new ArrayList<Move>();
    	List<Cell> storePossibleFromCells = new ArrayList<Cell>();
    	List<Cell> storePossibleToCells = new ArrayList<Cell>();
    	Cell fromCell;
    	Cell toCell;
    	Move move;
    	
    	storePossibleFromCells = getPossibleCells();
    	
    	// This for loop is to get all possible from Cell	
		for (int i = 0; i < storePossibleFromCells.size(); i++) {
			
			// if this is Musketeer turn
			fromCell = storePossibleFromCells.get(i);
			storePossibleToCells = getPossibleDestinations(fromCell);
			
			// This for loop is to get all possible to Cell and store the from Cell and to Cell into move list
			for (int j = 0; j < storePossibleToCells.size(); j++) {
				toCell = storePossibleToCells.get(j);
				move = new Move(fromCell, toCell);
				storeMoves.add(move);
			}
					
			
		}
		
        return storeMoves;
    }

    /**
     * Checks if the game is over and sets the winner if there is one.
     * @return True, if the game is over, false otherwise.
     */
    public boolean isGameOver() { // TODO
    	List<Cell> musketeerCell = new ArrayList<>();
    	List<Cell> possibleDes = new ArrayList<>();
    	
    	musketeerCell = getMusketeerCells();
    	
    	if (musketeerCell.get(0).getCoordinate().row == musketeerCell.get(1).getCoordinate().row && musketeerCell.get(1).getCoordinate().row == musketeerCell.get(2).getCoordinate().row) {
    		winner = Piece.Type.GUARD;
			return true;
		}
    	if (musketeerCell.get(0).getCoordinate().col == musketeerCell.get(1).getCoordinate().col && musketeerCell.get(1).getCoordinate().col == musketeerCell.get(2).getCoordinate().col) {
    		winner = Piece.Type.GUARD;
			return true;
		}
    	
    	
    	
    		// Then check if Musketeer win
    		for (int i = 0; i < musketeerCell.size(); i++) {
    			possibleDes = getPossibleDestinations(musketeerCell.get(i));
    			// If there is one Musketeer cell has possible destination, Musketeer not win, and the game is not over
				if (possibleDes.size() != 0) return false;
			}
			
    		// Musketeer has no cell has possible destination, Musketeer win
    		winner = Piece.Type.MUSKETEER;
    		return true;
		
    	
    }

    /**
     * Saves the current board state to the boards directory
     */
    public void saveBoard() {
        String filePath = String.format("boards/%s.txt",
                new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
        File file = new File(filePath);

        try {
            file.createNewFile();
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            writer.write(turn.getType() + "\n");
            for (Cell[] row: board) {
                StringBuilder line = new StringBuilder();
                for (Cell cell: row) {
                    if (cell.getPiece() != null) {
                        line.append(cell.getPiece().getSymbol());
                    } else {
                        line.append("_");
                    }
                    line.append(" ");
                }
                writer.write(line.toString().strip() + "\n");
            }
            writer.close();
            System.out.printf("Saved board to %s.\n", filePath);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.printf("Failed to save board to %s.\n", filePath);
        }
    }

    @Override
    public String toString() {
        StringBuilder boardStr = new StringBuilder("  | A B C D E\n");
        boardStr.append("--+----------\n");
        for (int i = 0; i < size; i++) {
            boardStr.append(i + 1).append(" | ");
            for (int j = 0; j < size; j++) {
                Cell cell = board[i][j];
                boardStr.append(cell).append(" ");
            }
            boardStr.append("\n");
        }
        return boardStr.toString();
    }

    /**
     * Loads a board file from a file path.
     * @param filePath The path to the board file to load (e.g. "Boards/Starter.txt")
     */
    private void loadBoard(String filePath) {
        File file = new File(filePath);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.err.printf("File at %s not found.", filePath);
            System.exit(1);
        }

        turn = Piece.Type.valueOf(scanner.nextLine().toUpperCase());

        int row = 0, col = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] pieces = line.trim().split(" ");
            for (String piece: pieces) {
                Cell cell = new Cell(new Coordinate(row, col));
                switch (piece) {
                    case "O" -> cell.setPiece(new Guard());
                    case "X" -> cell.setPiece(new Musketeer());
                    default -> cell.setPiece(null);
                }
                this.board[row][col] = cell;
                col += 1;
            }
            col = 0;
            row += 1;
        }
        scanner.close();
        System.out.printf("Loaded board from %s.\n", filePath);
    }
}
