
/**
 * This class is for freeze action.
 * @author Nitith Chayakul
 *
 */
public class FreezeAction extends BoardAction {
	
	/**
	 * Initialize freeze action.
	 * @see BoardAction#BoardAction(Player)
	 */
	public FreezeAction(Player player) {
		super(player);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void action(Board board) {
		player.freeze();
	}

}
