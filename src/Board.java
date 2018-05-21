import java.util.Arrays;
import java.util.Random;

public class Board {
	
	private static final int SIZE = 100;
	private Square[] squares;
	
	public Board() {
		squares = new Square[SIZE];
		for(int i=0 ; i<squares.length ; i++) {
			squares[i] = new Square(i);
		}
		
		initializeEventSquare();
		squares[squares.length - 1].setGoalState();
		
		System.out.println( Arrays.toString(squares) );
	}
	
	public void initializeEventSquare() {
		// Square for ladder
		squares[7] = new LadderSquare(squares[7].getNumber(),30);
		squares[14] = new LadderSquare(squares[14].getNumber(),96);
		squares[41] = new LadderSquare(squares[41].getNumber(),80);
		squares[65] = new LadderSquare(squares[65].getNumber(),86);
		
		squares[23] = new SnakeSquare(squares[23].getNumber(),0);
		squares[54] = new SnakeSquare(squares[54].getNumber(),12);
		squares[70] = new SnakeSquare(squares[70].getNumber(),28);
		squares[87] = new SnakeSquare(squares[87].getNumber(),66);
		squares[98] = new SnakeSquare(squares[98].getNumber(),5);
		
		squares[11] = new FreezeSquare(squares[11].getNumber());
		squares[35] = new FreezeSquare(squares[35].getNumber());
		squares[67] = new FreezeSquare(squares[67].getNumber());
		squares[83] = new FreezeSquare(squares[83].getNumber());
		
		squares[5] = new BackwardSquare(squares[5].getNumber());
		squares[27] = new BackwardSquare(squares[27].getNumber());
		squares[69] = new BackwardSquare(squares[69].getNumber());
		squares[82] = new BackwardSquare(squares[11].getNumber());
		

	}
	
	public void reset() {
		for(Square s : squares) {
			s.clearPlayer();
		}
	}
	
	public void addPlayer(Player player, int position) {
		squares[position].addPlayer(player);
	}
	
	public void movePiece(Player player, int step) {
		int pos = getPlayerPosition(player);
		squares[pos].removePlayer(player);
		if(squares[pos] instanceof BackwardSquare)
			step *= -1;
		int newPos = pos + step;
		if(newPos >= squares.length - 1) newPos = squares.length - 1;
		addPlayer(player, newPos);
	}
	
	public int getPlayerPosition(Player player) {
		for(Square s : squares) {
			if(s.hasPlayer(player)) {
				return s.getNumber();
			}
		}
		return -1;
	}
	
	public BoardAction getAction(Player player) {
		int position = getPlayerPosition(player);
		return squares[position].getAction(player);
	}
	
	public boolean playerIsAtGoal(Player player) {
		return squares[getPlayerPosition(player)].isGoal();
	}
	
	public String getSquareType(Player player) {
		int position = getPlayerPosition(player);
		return squares[position].toString();
	}

}
