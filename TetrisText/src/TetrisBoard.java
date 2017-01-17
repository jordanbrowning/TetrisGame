/**
* Represents the model as a board and maintains the game
* @author browning
*/
public class TetrisBoard {
	
	// number of rows in the board
	public final int NUM_ROWS = 20; 
	
	// number of columns in the board
	public final int NUM_COLS = 10;
	
	//determines if there's a block in a specific row, column index. false = empty, true = filled
	private boolean[][] blockMatrix;
	
	//type of piece currently falling
	private TetrisPiece currentPiece; 
	
	// position of currentPiece in the grid at northwest index of the piece
	private int[] currentPieceGridPosition;
	
	//number of lines cleared so far
	private int numLines;
	
	//number of tetrises cleared so far
	private int numTetrises;
	
	/**
	 * Constructor, makes Tetris board
	 */
	TetrisBoard() {
		// create new board 
		blockMatrix = new boolean[NUM_ROWS][NUM_COLS];
		
		// fill in values for board
		initBoard();
		
		// add new piece to fall
		addNewPiece();
		
		// start scores at 0
		numLines = 0;
		numTetrises = 0;
	}
	
	/**
	 * initialize current piece grid position
	 */
	protected void initCurrentGP() {
		// centers the falling pieces at the top row
		currentPieceGridPosition = new int[]{0, 3};
	}
	
	/**
	 * initialize the board
	 */
	protected void initBoard() {
		// set all of blockMatrix to begin as false
		for(int i = 0; i < NUM_ROWS; i++){
			for(int j = 0; j < NUM_COLS; j++) {
				blockMatrix[i][j] = false; 
			}
		}
	}
	
	/**
	 * currentPiece lands on board and becomes part of preexisting board and/or clears lines
	 */
	public void landPiece() {
		// current rotation of piece
		int rot = currentPiece.getPieceRotation();
		
		// x coordinate of current piece's northwest corner
		int row = currentPieceGridPosition[0];
		
		// y coordinate of current piece's northwest corner
		int col = currentPieceGridPosition[1];	

		// adds currentPiece's landing positions to be true in blockMatrix
		for(int i = 0; i < 4; i++) { 
			for(int j = 0; j < 4; j++) { 
				if(currentPiece.isFilled(rot, i, j)) {
					blockMatrix[i + row][j + col] = true; 
				}
			}
		}
		
		
		//add up scores
		numLines += numberOfFormedLines(); 
		if(numberOfFormedLines() >= 4) {
			numTetrises++; 
		}
		
		//clear out full lines
		for(int i = 0; i < blockMatrix.length; i++) { 
			if(fullLine(i)) { 
				removeLine(i);
				//find rows with blocks filled above cleared line to drop those down
				for(int m = i-1; m >= 0; m--) { 
					for(int n = 0; n < NUM_COLS; n++) {
						// fill in space below, clear out current row 
						if(hasBlock(m, n)) { 
							blockMatrix[m][n] = false;
							blockMatrix[m+1][n] = true;
						}
					}
				}
			}
		}
		
		// ready for next piece
		addNewPiece();
	}
	
	/**
	 * moves currentPiece left
	 */
	public void moveLeft() {
		// x coordinate of current piece's northwest corner
		int gridRow = currentPieceGridPosition[0]; 
		
		// y coordinate of current piece's northwest corner, with change
		int gridCol = currentPieceGridPosition[1] - 1;
		
		// check to see if safe
		if(validMove(currentPiece, currentPiece.pieceRotation, gridRow, gridCol)) {
			// determined safe to change 
			currentPieceGridPosition[1]--; 
		}
	}
	
	/**
	 * moves currentPiece right
	 */
	public void moveRight() {
		// x coordinate of current piece's northwest corner
		int gridRow = currentPieceGridPosition[0]; 
		
		// y coordinate of current piece's northwest corner, with change
		int gridCol = currentPieceGridPosition[1] + 1;
		
		// check to see if safe
		if(validMove(currentPiece, currentPiece.pieceRotation, gridRow, gridCol)) {
			// determined safe to change 
			currentPieceGridPosition[1]++; 
		}
	}
	
	/**
	 * moves currentPiece down
	 */
	public void moveDown() { 
		// x coordinate of current piece's northwest corner, with change
		int gridRow = currentPieceGridPosition[0] + 1; 
		
		// y coordinate of current piece's northwest corner
		int gridCol = currentPieceGridPosition[1];
		
		// check to see if safe
		if(validMove(currentPiece, currentPiece.pieceRotation, gridRow, gridCol)) {
			// determined safe to change 
			currentPieceGridPosition[0]++;
		}
		
		// piece cannot go down any further
		else {
			landPiece();
		}
	}
	
	/**
	 * rotates currentPiece clockwise
	 */
	public void rotateCW() {
		// x coordinate of current piece's northwest corner
		int gridRow = currentPieceGridPosition[0];
		
		// y coordinate of current piece's northwest corner
		int gridCol = currentPieceGridPosition[1];
		
		// rotation of piece if changed
		int newRot;
		
		// since 4 is an invalid piece rotation 
		if(currentPiece.getPieceRotation() == 3) {
			newRot = 0; 
		}
		else {
			newRot = currentPiece.getPieceRotation() + 1;
		}
		
		// check to see if safe
		if(validMove(currentPiece, newRot, gridRow, gridCol)) {
			// determined safe to change 
			currentPiece.rotateCW(); 
		}
	}
	
	/**
	 * rotates currentPiece counter-clockwise
	 */
	public void rotateCCW() {
		// x coordinate of current piece's northwest corner
		int gridRow = currentPieceGridPosition[0]; 
		
		// y coordinate of current piece's northwest corner
		int gridCol = currentPieceGridPosition[1];
		
		// rotation of piece if changed
		int newRot;
		
		// since -1 is an invalid piece rotation 
		if(currentPiece.getPieceRotation() == 0) {
			newRot = 3; 
		}
		else {
			newRot = currentPiece.getPieceRotation() - 1;
		}
		
		// check to see if safe
		if(validMove(currentPiece, newRot, gridRow, gridCol)) {
			// determined safe to change 
			currentPiece.rotateCCW(); 
		}
	}
	
	/**
	 * checks if currentPiece will hit a filled space 
	 * return true if collision will occur
	 */
	protected boolean detectCollision(TetrisPiece piece, int rot, int gridRow, int gridCol) {
		// goes through rows and columns of filledSquares matrices
		for(int i = 0; i < 4; i++) { 
			for(int j = 0; j < 4; j++) { 
				if(piece.isFilled(rot, i, j)) {
					
					// conditions for collision
					if(hasBlock(i + gridRow, j + gridCol)) {
						return true; 
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * checks if currentPiece will be out of bounds
	 * return true if out of bounds 
	 */
	protected boolean detectOutOfBounds(TetrisPiece piece, int rot, int gridRow, int gridCol) {
		// goes through rows and columns of filledSquares matrices
		for(int i = 0; i < 4; i++) { 
			for(int j = 0; j < 4; j++) { 
				if(piece.isFilled(rot, i, j)) {
					
					// conditions for out of bounds 
					if(i + gridRow > NUM_ROWS - 1 || j + gridCol < 0 || j + gridCol > NUM_COLS - 1) {
						return true; 
					}
				}
			}
		}
		return false; 
	}
	
	/**
	 * checks if move is valid in currentPiece's current position
	 * return true if valid
	 */
	protected boolean validMove(TetrisPiece piece, int rot, int gridRow, int gridCol) {
		if(detectOutOfBounds(piece, rot, gridRow, gridCol)) {
			return false;
		}
		if(detectCollision(piece, rot, gridRow, gridCol)) {
			return false;
		}
		return true; 
	}
	
	/**
	 * checks to see if an index is currently filled with a block
	 */
	public boolean hasBlock(int row, int col) {
		if(blockMatrix[row][col]) {
			return true;
		}
		return false; // if block is not filled
	}
	
	/**
	 * adds a new piece to the board 
	 */
	public void addNewPiece() {
		// creates a range from 1-7 to choose from each of the pieces
		int pieceChoice = (int) (Math.random()*7) + 1; 
		if(pieceChoice == 1) {
			currentPiece = new TetrisI();
		}
		else if(pieceChoice == 2) {
			currentPiece = new TetrisJ();
		}
		else if(pieceChoice == 3) {
			currentPiece = new TetrisL();
		}
		else if(pieceChoice == 4) {
			currentPiece = new TetrisO();
		}
		else if(pieceChoice == 5) {
			currentPiece = new TetrisS();
		}
		else if(pieceChoice == 6) {
			currentPiece = new TetrisT();
		}
		else {
			currentPiece = new TetrisZ();
		}
		currentPiece.pieceRotation = 0;
		initCurrentGP();
	}
	
	/**
	 * checks for how many lines are full 
	 */
	public int numberOfFormedLines() {
		// number of formed lines
		int countFullLines = 0;
		
		// goes through blockMatrix to count full lines
		for(int i = 0; i < blockMatrix.length; i++) { 
			if(fullLine(i)) {
				countFullLines++;
			}
		}
		
		return countFullLines; 
	}
	
	/**
	 * checks if a line is full
	 * return true if full line
	 */
	protected boolean fullLine(int row) {
		// goes through columns in that row specified
		for(int j = 0; j < blockMatrix[row].length; j++) { 
			// returns false when there's an empty position
			if(!hasBlock(row, j)) {
				return false;
			}
		}
		return true; 
	}
	
	/**
	 * removes a row from the board 
	 */
	protected void removeLine(int row) {
		// clear out the true values from row/filled blocks
		for(int j = 0; j < blockMatrix[row].length; j++) { 
			blockMatrix[row][j] = false; 
		}
	}
	
	/**
	 * getter for the block matrix
	 */
	public boolean[][] getBlockMatrix() {
		return blockMatrix; 
	}
	
	/**
	 * getter for the current piece
	 */
	public TetrisPiece getCurrentPiece() {
		return currentPiece;
	}
	
	/**
	 * getter for the current piece grid position
	 */
	public int[] getCurrentPieceGridPosition() {
		return currentPieceGridPosition; 
	}
	
	/**
	 * getter for the number of columns in the board
	 */
	public int getNumCols() {
		return NUM_COLS; 
	}
	
	/**
	 * getter for the number of rows in the board
	 */
	public int getNumRows() {
		return NUM_ROWS; 
	}
	
	/**
	 * getter for number of lines cleared this round
	 */
	public int getNumLines() {
		return numLines;
	}
	
	/**
	 * getter for number of tetrises cleared this round
	 */
	public int getNumTetrises() {
		return numTetrises;
	}
}
