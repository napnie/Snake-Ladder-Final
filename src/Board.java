import java.util.Arrays;
import java.util.Random;

public class Board {
	
	private static final int SIZE = 10;
	private Square[] squares;
	
	public Board() {
		squares = new Square[SIZE];
		for(int i=0 ; i<squares.length ; i++) {
			squares[i] = new Square(i);
		}
		for(int i=0 ; i<10 ; i++) {
			int event = randomSquare();
			squares[event].setFreezeState();
			event = randomSquare();
			squares[event].setBackwardState();
			event = randomSquare();
			squares[event].setLadderState(new Random().nextInt(squares.length - event - 1 - 3) + 1);
			event = randomSquare();
			squares[event].setSnakeState((new Random().nextInt(event) + 1) * -1);
		}
		squares[squares.length - 1].setGoalState();
		
		System.out.println( Arrays.toString(squares) );
	}
	
	private int randomSquare() {
		return 1 + new Random().nextInt(squares.length - 4);
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
