/**
* Abstract class that represents a piece made of 4 TetrisBlocks, 
* maintains 4 rotations (0 degrees, 90 degrees, 180 degrees and 270 degrees), 
* with each being a 4x4 grid with certain filled squares.
* @author browning
*/
public abstract class TetrisPiece {
	
	//First dim: rotation of piece (0 = 0 degrees, 1 = 90 degrees, 2 = 180 degrees, 3 = 270 degrees)
	//Second & third dim: create 4x4 grid with true values indicating filled squares
	protected boolean[][][] filledSquares = new boolean[4][4][4];
	
	//Maintains the current rotation.
	protected int pieceRotation; 
	
	/**
	 * Constructor: does nothing
	 */
	public TetrisPiece() {
	}
	
	/**
	 * Rotate the piece clockwise by 90 degrees.
	 */
	public void rotateCW() {
		// cannot add here, since 4 is invalid
		if(pieceRotation == 3) {
			pieceRotation = 0; 
		}
		else {
			pieceRotation++;
		}
	}
	
	/**
	 * Rotate the piece counter-clockwise by 90 degrees.
	 */
	public void rotateCCW() {
		// can't subtract here, since -1 is invalid
		if(pieceRotation == 0) {
			pieceRotation = 3; 
		}
		else {
			pieceRotation--;
		}
	}

	
	/**
	 * Getter for pieceRotation
	 */
	public int getPieceRotation() {
		return pieceRotation;
	}
	
	/**
	 * Checks if there is a TetrisBlock at the (row, col) position for the rotation rot,
	 * where rot is 0, 90, 180 or 270 degrees.
	 * return true if there is a block
	 */
	public boolean isFilled(int rot, int row, int col) {
		// finds if there is a block in the space
		if(filledSquares[rot][row][col]) {
			return true;
		}
		return false;
	}
}
