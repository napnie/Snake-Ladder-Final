
/**
 * This class remember various action in snake&ladder game.
 * @author Nitith Chayakul
 *
 */
public abstract class BoardAction {
	
	/** Player that initiate the action. */
	protected Player player;
	
	/**
	 * Initialize the action.
	 * @param player - Player that initiate the action.
	 */
	public BoardAction(Player player) {
		this.player = player;
	}
	
	/**
	 * Perform the action.
	 * @param board - board that the game use.
	 */
	public abstract void action(Board board);

	public boolean isPlayer(Player player) {
		return this.player == player;
	}
}
