/**
* Creates Tetris O shape
* @author browning
*/
public class TetrisO extends TetrisPiece {

	/**
	 * Constructor: creates piece O
	 */
	TetrisO() {
		// rotation 1
		filledSquares[0] = new boolean[][]{
							{false, true, true, false},
							{false, true, true, false},
							{false, false, false, false},
							{false, false, false, false}
							};
							
		// rotation 2
		filledSquares[1] = new boolean[][]{
							{false, true, true, false},
							{false, true, true, false},
							{false, false, false, false},
							{false, false, false, false}
							};
							
		// rotation 3
		filledSquares[2] = new boolean[][]{
							{false, true, true, false},
							{false, true, true, false},
							{false, false, false, false},
							{false, false, false, false}
							};
		// rotation 4
		filledSquares[3] = new boolean[][]{
							{false, true, true, false},
							{false, true, true, false},
							{false, false, false, false},
							{false, false, false, false}
							};	
	}
	
}