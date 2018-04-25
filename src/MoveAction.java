
/**
 * This class is for moveing action.
 * @author Nitith Chayakul
 *
 */
public class MoveAction extends BoardAction {
	
	/** Step that this move take */
	private int step;

	/**
	 * Initialize Move action.
	 * @param step - step that this move take
	 * @see BoardAction#BoardAction(Player)
	 */
	public MoveAction(Player player, int step) {
		super(player);
		this.step = step;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void action(Board board) {
		board.movePiece(player, step);
	}

}
