/**
* Creates Tetris L shape
* @author browning
*/
public class TetrisL extends TetrisPiece {

	/**
	 * Constructor: creates piece L
	 */
	TetrisL() {
		// rotation 1 
		filledSquares[0] = new boolean[][]{
							{true, true, true, false},
							{true, false, false, false},
							{false, false, false, false},
							{false, false, false, false}
							};
							
		// rotation 2
		filledSquares[1] = new boolean[][]{
							{true, true, false, false},
							{false, true, false, false},
							{false, true, false, false},
							{false, false, false, false}
							};
							
		// rotation 3
		filledSquares[2] = new boolean[][]{
							{false, false, false, false},
							{false, false, true, false},
							{true, true, true, false},
							{false, false, false, false}
							};
							
		// rotation 4
		filledSquares[3] = new boolean[][]{
							{true, false, false, false},
							{true, false, false, false},
							{true, true, false, false},
							{false, false, false, false}
							};
	}
	
}