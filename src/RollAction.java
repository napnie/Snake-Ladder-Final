
public class RollAction extends BoardAction {
	
	private int face;

	public RollAction(Player player, int face) {
		super(player);
		this.face = face;
	}
	
	@Override
	public void action(Board board) {
		// nothing
	}
	
	public String toString() {
		String act = "rolled " + face + " face";
		if(face == 6) {
			act += " and get extra roll!";
		}
		return act ;
	}
	
	public int getFace() { return face; }

}
