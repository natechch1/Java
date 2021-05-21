
/* PLEASE DO NOT MODIFY A SINGLE STATEMENT IN THE TEXT BELOW.
 READ THE FOLLOWING CAREFULLY AND FILL IN THE GAPS

I hereby declare that all the work that was required to 
solve the following problem including designing the algorithms
and writing the code below, is solely my own and that I received
no help in creating this solution and I have not discussed my solution 
with anybody. I affirm that I have read and understood
 the Senate Policy on Academic honesty at 
https://secretariat-policies.info.yorku.ca/policies/academic-honesty-senate-policy-on/
and I am well aware of the seriousness of the matter and the penalties that I will face as a 
result of committing plagiarism in this assignment.

BY FILLING THE GAPS,YOU ARE SIGNING THE ABOVE STATEMENTS.

Full Name: Hongce Chen
Student Number:216792327
Course Section: B
*/

package Assignment1;
import java.util.*;

/**
 * 
 * @author EECS2030 Team
 *
 */

public class Map {
	boolean [][] map; 
	private int row;
	private int column;
	private boolean carHasAccident;
	/**
	 * This is the constructor that constructs the city map, 
	 * which is a grid of row by column.
	 * @param row is the number of east-west streets of the city
	 * @param column is the number of north-south streets of the city
	 */
	public Map(int row, int column) {
		// Please implement the constructor
		this.row = row;
		this.column = column;
		map = new boolean [row][column];
	}
	/**
	 * This method checks the correctness of the input parameters. If the preconditions are not met 
	 * an exception is thrown, otherwise depending to the direction, it calls 
	 * one of the four recursive functions of goSouthWest, goSouthEast, goNorthWest and goNorthEast.
	 * @param startRow is the starting row of the path 
	 * @param startCol is the starting column of the path
	 * @param destRow is the destination row
	 * @param destCol is the destination column
	 * @param path is the path that is constructed while the recursive method is called. In first round,  it will be "".
	 * @return returns a string representing the path to the destination. The format of the output is (x1,y1) (x2,y2) (x3,y3)...
	 * @pre the integer parameters should be in the range of the city grid.(i.e. [0, N) if N is the number of east-west streets and [0, M) if 
	 * M is the number of north-south streets.) 
	 * @exception IllegalArgumentException if any of the precondition did not meet.
	 */
	public String getPath (int startRow, int startCol, int destRow, int destCol , String path) {
		// Please complete this method
		// you should decide on what should be returned. This return statement is here to avoid compiler error.
		if(startRow < 0 || startRow > this.row || startCol < 0 || startCol > this.column || destRow < 0 || destRow > this.row ||  destCol< 0 || destCol > this.column) {
		      throw new IllegalArgumentException("Not in the range.");   //it can determine whether the point is within the range
		  }
		   if (startRow >= destRow && startCol >= destCol) {                       //all of this is find the way that let point go
		     return goSouthWest (startRow, startCol, destRow, destCol , path);
		    }
		   if (startRow >= destRow && startCol <= destCol) {
		      return goSouthEast (startRow, startCol, destRow, destCol , path);
		     }
		   if (startRow <= destRow && startCol <= destCol) {
		      return goNorthEast (startRow, startCol, destRow, destCol , path);
		     }
		   if (startRow <= destRow && startCol >= destCol) {
		      return goNorthWest (startRow, startCol, destRow, destCol , path);
		     }
		   return "";
		 
	}
	/**
	 * This method returns a path from the source (startRow, startCol) to the destination (destRow, destCol).
	 * Please note that the returning path does not include the starting point.  
	 * @param startRow is the starting row of the path 
	 * @param startCol is the starting column of the path
	 * @param destRow is the destination row
	 * @param destCol is the destination column
	 * @param path is the path that is constructed while the recursive method is called. In first round,  it will be "".
	 * @return returns a string representing the path to the destination. The format of the output is (x1,y1) (x2,y2) (x3,y3)...
	 * @pre <code> startRow >= destRow </code> and <code> startCol >= destCol </code>
	 */
	
	private String goSouthWest (int startRow, int startCol, int destRow, int destCol , String path) {
		// Please complete this method
		// you should decide on what should be returned. This return statement is here to avoid compiler error.
		int nextRow;
		int nextCol;
		if(startRow == destRow) {
			if(startCol == destCol) {
				return path;                     //if all row and column is equal then return path and end this method
			}
			else {
				nextRow = startRow;              //if column is not equal,then let point go west which is column -1
				nextCol = startCol - 1;
			}
		}
		else {
			nextRow = startRow - 1;               //if row is not equal, then let point go south which is row-1
			nextCol = startCol;
		}
		if(map[nextRow][nextCol]) {
			carHasAccident = true;                 //according map[][], let us to determine if the point is a duplicate 
			return path;                           //and it is also an important basis for whether the car can find the correct path
		}
		map[nextRow][nextCol] = true;
		path += " (" + nextRow + "," + nextCol + ")";       
		return goSouthWest (nextRow, nextCol, destRow, destCol , path);
	}
	
	/**
	 * This method returns a path from the source (startRow, startCol) to the destination (destRow, destCol).
	 * Please note that the returning path does not include the starting point. 
	 * @param startRow is the starting row of the path 
	 * @param startCol is the starting column of the path
	 * @param destRow is the destination row
	 * @param destCol is the destination column
	 * @param path is the path that is constructed while the recursive method is called. In first round,  it will be "".
	 * @return returns a string representing the path to the destination. The format of the output is (x1,y1) (x2,y2) (x3,y3)...
	 * @pre <code> startRow >= destRow </code> and <code> startCol <= destCol </code>
	 */
	private String goSouthEast (int startRow, int startCol, int destRow, int destCol , String path) {
		// Please complete this method
		// you should decide on what should be returned. This return statement is here to avoid compiler error.
		int nextRow;
		int nextCol;
		if(startRow == destRow) {
			if(startCol == destCol) {
				return path;                             //if all row and column is equal then return path and end this method
			}
			else {
				nextRow = startRow;                      //if column is not equal,then let point go east which is column +1
				nextCol = startCol + 1;
			}
		}
		else {
			nextRow = startRow - 1;                      //if row is not equal, then let point go south which is row-1
			nextCol = startCol;
		}
		if(map[nextRow][nextCol]) {
			carHasAccident = true;                       //according map[][], let us to determine if the point is a duplicate 
			return path;                                 //and it is also an important basis for whether the car can find the correct path
		}
		map[nextRow][nextCol] = true;
		path += " (" + nextRow + "," + nextCol + ")";
		return goSouthEast (nextRow, nextCol, destRow, destCol , path);        
	}
	
	/**
	 * This method returns a path from the source (startRow, startCol) to the destination (destRow, destCol).
	 * Please note that the returning path does not include the starting point. 
	 * @param startRow is the starting row of the path 
	 * @param startCol is the starting column of the path
	 * @param destRow is the destination row
	 * @param destCol is the destination column
	 * @param path is the path that is constructed while the recursive method is called. In first round,  it will be "".
	 * @return returns a string representing the path to the destination. The format of the output is (x1,y1) (x2,y2) (x3,y3)...
	 * @pre <code> startRow <= destRow </code> and <code> startCol >= destCol </code>
	 */
	private String goNorthEast (int startRow, int startCol, int destRow, int destCol , String path) {
		int nextRow; 
		int nextCol;
		if(startRow == destRow) {
			if(startCol == destCol) {
				return path;                              //if all row and column is equal then return path and end this method
			}
			else {
				nextRow = startRow;
				nextCol = startCol + 1;                    //if column is not equal,then let point go east which is column +1
			}
		}
		else {
			nextRow = startRow + 1;                        //if row is not equal, then let point go north which is row + 1
			nextCol = startCol;
		}
		if(map[nextRow][nextCol]) {
			carHasAccident = true;                         //according map[][], let us to determine if the point is a duplicate 
			return path;                                   //and it is also an important basis for whether the car can find the correct path
		}
		map[nextRow][nextCol] = true;
		path += " (" + nextRow + "," + nextCol + ")"; 
		return goNorthEast (nextRow, nextCol, destRow, destCol , path);
	}

	/**
	 * This method returns a path from the source (startRow, startCol) to the destination (destRow, destCol).
	 * Please note that the returning path does not include the starting point. 
	 * @param startRow is the starting row of the path 
	 * @param startCol is the starting column of the path
	 * @param destRow is the destination row
	 * @param destCol is the destination column
	 * @param path is the path that is constructed while the recursive method is called. In first round,  it will be "".
	 * @return returns a string representing the path to the destination. The format of the output is (x1,y1) (x2,y2) (x3,y3)...
	 * @pre <code> startRow >= destRow </code> and <code> startCol <= destCol </code>
	 */
	private String goNorthWest (int startRow, int startCol, int destRow, int destCol , String path) {
		// Please complete this method
		// you should decide on what should be returned. This return statement is here to avoid compiler error.
		int nextRow;
		int nextCol;
		if(startRow == destRow) {
			if(startCol == destCol) {
				return path;                              //if all row and column is equal then return path and end this method
			}
			else {
				nextRow = startRow;
				nextCol = startCol - 1;                   //if column is not equal,then let point go west which is column - 1
			}
		}
		else {
			nextRow = startRow + 1;                       //if row is not equal, then let point go north which is row + 1
			nextCol = startCol;
		}
		if(map[nextRow][nextCol]) {
			carHasAccident = true;                        //according map[][], let us to determine if the point is a duplicate 
			return path;                                  //and it is also an important basis for whether the car can find the correct path
		}
		map[nextRow][nextCol] = true;
		path += " (" + nextRow + "," + nextCol + ")";
		return goNorthWest (nextRow, nextCol, destRow, destCol , path);
	}
	
	/**
	 * This method find a path from (startRow, startCol) to a border point of the city. 
	 * Please note that the starting point should be included in the path.
	 * @param startRow is the starting row of the path
	 * @param startCol is the starting column of the path
	 * @return is a path from (starting row, staring col) to a border point of the city. The format of the output is (x1,y1) (x2,y2) (x3,y3)...
	 */
	
	private boolean findBorder(int currentRow, int currentCol) {
		boolean border = currentRow == 0 || currentRow == this.row -1 || currentCol == 0 || currentCol == this.column -1;
		return border;
		        //it can determine if point is in border 
	} 
	
	
	public String findPath (int startRow, int startCol) {
		// Please complete this method
		// you should decide on what should be returned. This return statement is here to avoid compiler error.
		String path = " (" + startRow + "," + startCol + ")";
		Random random = new Random();
		carHasAccident = false;
		int carStartRow = startRow;
		int carStartCol = startCol;
		int carDestRow = startRow;
		int carDestCol = startCol;
		while(!findBorder(carDestRow, carDestCol) && !carHasAccident) {       //if the car still inside the border and car has not  accident, then still doing the while loop 
			carDestRow = carStartRow + random.nextInt(2) - 1;        //it makes the car stay or go north one step or go south one step 
			carDestCol = carStartCol + random.nextInt(2) - 1;        //it makes the car stay or go east one step or go west one step
			path = getPath(carStartRow, carStartCol, carDestRow, carDestCol, path);  //go to getPath method and check if the car has accident
			carStartRow = carDestRow;   //let this destpoint be new start point
			carStartCol = carDestCol;   //let this destpoint be new start point
		}
		if(!carHasAccident) {
			return path;
		}
		else {
			clearTheMap();            //if the car has accident,then clear the map and out, re-do this findPath method
			return findPath(startRow, startCol);
		}
	}
	
	private void clearTheMap(){
		for(int n = 0; n < this.row; n++) {
			for(int m = 0; m < this.column; m++) {
				map[n][m] = false;
			}
		}
	}
		
} // end of class
