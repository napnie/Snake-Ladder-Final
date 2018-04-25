
/**
 * This class is placeholder to use to skip an action.
 * @author Nitith Chayakul
 *
 */
public class PassiveAction extends BoardAction {
	
	/** Instance of this passive action. */
	private static PassiveAction instance;

	/**
	 * @see BoardAction#BoardAction(Player)
	 */
	private PassiveAction(Player player) {
		super(player);
	}
	
	/**
	 * Get instance of PassiveAction.
	 * @return instance of PassiveAction
	 */
	public static PassiveAction getInstance() {
		if( instance == null ) {
			instance = new PassiveAction(new Player(""));
		}
		return instance;
	}
	
	/**
	 * Doing nothing
	 * {@inheritDoc}
	 */
	@Override
	public void action(Board board) {
		// nothing
	}

}
