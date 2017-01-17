/**
* Creates Tetris J shape
* @author browning
*/
public class TetrisJ extends TetrisPiece {

	/**
	 * Constructor: creates piece J
	 */
	TetrisJ() {
		// rotation 1
		filledSquares[0] = new boolean[][]{
							{true, true, true, false},
							{false, false, true, false},
							{false, false, false, false},
							{false, false, false, false}
							};
							
		// rotation 2
		filledSquares[1] = new boolean[][]{
							{false, false, true, false},
							{false, false, true, false},
							{false, true, true, false},
							{false, false, false, false}
							};
							
		// rotation 3		
		filledSquares[2] = new boolean[][]{
							{false, false, false, false},
							{true, false, false, false},
							{true, true, true, false},
							{false, false, false, false}
							};
		// rotation 4			
		filledSquares[3] = new boolean[][]{
							{true, true, false, false},
							{true, false, false, false},
							{true, false, false, false},
							{false, false, false, false}
							};
	}
	
}