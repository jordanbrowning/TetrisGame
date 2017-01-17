/**
* Creates Tetris Z shape
* @author browning
*/
public class TetrisZ extends TetrisPiece {

	/**
	 * Constructor: creates piece Z
	 */
	TetrisZ() {
		// rotation 1
		filledSquares[0] = new boolean[][]{
							{true, true, false, false},
							{false, true, true, false},
							{false, false, false, false},
							{false, false, false, false}
							};
							
		// rotation 2
		filledSquares[1] = new boolean[][]{
							{false, true, false, false},
							{true, true, false, false},
							{true, false, false, false},
							{false, false, false, false}
							};
							
		// rotation 3
		filledSquares[2] = new boolean[][]{
							{true, true, false, false},
							{false, true, true, false},
							{false, false, false, false},
							{false, false, false, false}
							};
							
		// rotation 4
		filledSquares[3] = new boolean[][]{
							{false, true, false, false},
							{true, true, false, false},
							{true, false, false, false},
							{false, false, false, false}
							};			
	}
	
}