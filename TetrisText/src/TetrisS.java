/**
* Creates Tetris S shape
* @author browning
*/
public class TetrisS extends TetrisPiece {

	/**
	 * Constructor: creates piece S
	 */
	TetrisS() {
		// rotation 1
		filledSquares[0] = new boolean[][]{
			{false, true, true, false},
			{true, true, false, false},
			{false, false, false, false},
			{false, false, false, false}
			};
			
		// rotation 2
		filledSquares[1] = new boolean[][]{
			{true, false, false, false},
			{true, true, false, false},
			{false, true, false, false},
			{false, false, false, false}
			};

		// rotation 3
		filledSquares[2] = new boolean[][]{
			{false, true, true, false},
			{true, true, false, false},
			{false, false, false, false},
			{false, false, false, false}
			};

		// rotation 4
		filledSquares[3] = new boolean[][]{
			{true, false, false, false},
			{true, true, false, false},
			{false, true, false, false},
			{false, false, false, false}
			};			
	}
	
}