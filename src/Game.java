import java.util.ArrayList;
import java.util.List;

public class Game {
	
	private Player[] players;
	private Die die;
	private Board board;
	
	/** List of action in snake&ladder game for replay. */
	private List<BoardAction> replay;
	
	private int currentPlayerIndex;
	private boolean ended;
	
	public Game(int playerNum) {
		players = new Player[playerNum];
		players[0] = new Player("P1");
		players[1] = new Player("P2");
		for(int i=0 ; i<playerNum ; i++) {
			players[i] = new Player("P"+i);
		}
		
		die = new Die();
		board = new Board();
		replay = new ArrayList<BoardAction>();
		ended = false;
		
		for(Player player: players) {
			board.addPlayer(player, 0);
		}
	}
	
	public boolean isEnded() { return ended; }
	
	public void end() { ended = true; }
	
	public Player currentPlayer() { return players[currentPlayerIndex]; }
	
	public void switchPlayer() { currentPlayerIndex = (currentPlayerIndex + 1) % players.length; }
	
	public String currentPlayerName() { return currentPlayer().getName(); }
	
	public int currentPlayerPosition() { return board.getPlayerPosition(currentPlayer()); }
	
	public int currentPlayerRollDice() {
		if( !currentPlayer().isFreeze() ) {
			return currentPlayer().roll(die);
		}
		return 0;
	}
	
	public void currentPlayerMovePiece(int step ) { 
//		currentPlayer().movePiece(board, step); 
		BoardAction move = new MoveAction(currentPlayer(), step);
		move.action(board);
		replay.add(move);

		BoardAction action = board.getAction(currentPlayer());
		if( action != PassiveAction.getInstance() ) {
			action.action(board);
			replay.add(action);
		}
	}
	
	public boolean currentPlayerWins() { return board.pieceIsAtGoal(currentPlayer()); }
	
	/**
	 * Return type of square that current player is on.
	 * @return type of square that current player is on.
	 */
	public String getSquareType() {
		return board.getSquareType(currentPlayer() );
	}
}
