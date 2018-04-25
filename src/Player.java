
public class Player {
	
	/** Player's name */
	private String name;
	/** Player's status */
	private boolean isFreeze;
	
	/**
	 * Initialize the player
	 * @param name - player's name
	 */
	public Player(String name) {
		this.name = name;
		isFreeze = false;
	}
	
	/**
	 * Roll the die for this player.
	 * @param die - Die object
	 * @return face of the die
	 */
	public int roll(Die die) {
		die.roll();
		return die.getFace();
	}
	
	/**
	 * Freeze the player.
	 */
	public void freeze() {
		isFreeze = true;
	}
	
	
	/**
	 * Unfreeze the player.
	 */
	public void unFreeze() {
		isFreeze = false;
	}
	
	/**
	 * Check if this player is freeze.
	 * @return is this player freeze or not.
	 */
	public boolean isFreeze() { return isFreeze; }
	
	public String getName() {
		return name;
	}
	
//	public void movePiece(Board board, int step) {
//		board.movePiece(this, step);
//	}

}
