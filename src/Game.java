import java.util.ArrayList;
import java.util.List;

public class Game {
	
	private Player[] players;
	private Die die;
	private Board board;
	
	/** List of action in snake&ladder game for replay. */
	private List<BoardAction> replay;
	
	private int currentPlayerIndex;
	/** Status of the game. */
	private boolean ended;
	
	public Game(int playerNum) {
		players = new Player[playerNum];
		for(int i=0 ; i<playerNum ; i++) {
			players[i] = new Player("P"+i);
		}
		
		die = new Die();
		board = new Board();
		replay = new ArrayList<BoardAction>();
		reset();
	}
	
	public void reset() {
		board.reset();
		for(Player p : players) {
			p.unFreeze();
			board.addPlayer(p, 0);
		}
		currentPlayerIndex = 0;
		ended = false;
	}
	
	public void clearReplay() { replay.clear(); }
	
	public boolean isEnded() { return ended; }
	
	public void end() { ended = true; }
	
	public Player currentPlayer() { return players[currentPlayerIndex]; }
	
	public void switchPlayer() { currentPlayerIndex = (currentPlayerIndex + 1) % players.length; }
	
	public String currentPlayerName() { return currentPlayer().getName(); }
	
	public int currentPlayerPosition() { return board.getPlayerPosition(currentPlayer()); }
	
	public boolean isCurrentPlayerFreezing() {
		return currentPlayer().isFreeze();
	}
	
	public void unfreeze() { currentPlayer().unFreeze(); }
	
	public int currentPlayerRollDice() {
		return currentPlayer().roll(die);
	}
	
	public String executeAction() {
		BoardAction effect = board.getAction(currentPlayer());
		return executeAction(effect);
	}
	
	public String executeAction(BoardAction action) {
		action.action(board);
		replay.add(action);
		return currentPlayerName() + " " + action.toString();
	}
	
	public String currentPlayerMovePiece(int step ) {
		BoardAction roll = new RollAction(currentPlayer(), step);
		BoardAction move = new MoveAction(currentPlayer(), step);
		String moving = executeAction(roll);
		moving += "\n" + executeAction(move);
		return moving;
	}
	
	public boolean currentPlayerWins() { return board.playerIsAtGoal(currentPlayer()); }
	
	/**
	 * Return type of square that current player is on.
	 * @return type of square that current player is on.
	 */
	public String getSquareType() {
		return board.getSquareType(currentPlayer() );
	}
	
	public List<BoardAction> getReplay() { return replay; }
}
