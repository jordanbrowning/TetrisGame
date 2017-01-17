/**
* Creates Tetris I shape
* @author browning
*/
public class TetrisI extends TetrisPiece {

	/**
	 * Constructor: creates piece I
	 */
	TetrisI() {
		//rotation 1
		filledSquares[0] = new boolean[][]{
							{true, true, true, true},
							{false, false, false, false},
							{false, false, false, false},
							{false, false, false, false}
							};
		
		// rotation 2
		filledSquares[1] = new boolean[][]{
							{true, false, false, false},
							{true, false, false, false},
							{true, false, false, false},
							{true, false, false, false}
							};

		// rotation 3
		filledSquares[2] = new boolean[][]{
								{true, true, true, true},
								{false, false, false, false},
								{false, false, false, false},
								{false, false, false, false}
								};
		
		// rotation 4
		filledSquares[3] = new boolean[][]{
								{true, false, false, false},
								{true, false, false, false},
								{true, false, false, false},
								{true, false, false, false}
								};
		}
			
			
			
	
}
