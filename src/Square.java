import java.util.ArrayList;
import java.util.List;

public class Square {
	
	private int number;
	/** List of players reside in this square. */
	private List<Player> players ;
	
	/** Current state of this square. */
	private SquareState type;
	
	public Square(int number) {
		this.number = number;
		players = new ArrayList<Player>();
		type = new NormalState();
	}

	/**
	 * Make this square a goal.
	 */
	public void setGoalState() {
		type = new GoalState();
	}
	
	public void addPlayer(Player player) {
		players.add(player);
	}
	
	public void removePlayer(Player player) {
		players.remove(player);
	}
	
	public void clearPlayer() {
		players.clear();
	}
	
	public boolean hasPlayer(Player player) {
		return players.contains(player);
	}
	
	/**
	 * Check if this square is goal.
	 * @return true if this square is goal.
	 */
	public boolean isGoal() {
		return type.isGoal();
	}
	
	public int getNumber() {
		return number;
	}
	
	/**
	 * Get the BoardAction that this square activate.
	 * @param player - player to initiate the action in this square.
	 * @return action of this square
	 */
	public BoardAction getAction(Player player) { 
		return type.getAction(player);
	}
	
	/**
	 * Set this square to be a snake square.
	 * @param backstep - step to go backward if player land in this square.
	 */
	public void setSnakeState(int backstep) {
		type = new SnakeState(backstep);
	}
	
	/**
	 * Set this square to be a ladder square.
	 * @param skipstep - step to go forward if player land in this square.
	 */
	public void setLadderState(int skipstep) {
		type = new LadderState(skipstep);
	}
	
	/**
	 * Set this square to be a freeze square.
	 */
	public void setFreezeState() { type = new FreezeState(); }
	
	/**
	 * Set this square to be a backward square.
	 */
	public void setBackwardState() { type = new BackwardState(); }
	
	/**
	 * Return the type of this square.
	 * @return type of this square.
	 */
	public String toString() { return type.toString(); }
	
	/**
	 * Get the dialog of this square. (What happends when player land in this square) <br>
	 * EX. return "Nothing happends!" if this square is 'Normal'
	 * @return dialog of this square.
	 */
	public String getDialog() { return type.getDialog(); }
	
	/**
	 * Pattern for the state of the square
	 * @author Nitith Chayakul
	 *
	 */
	public abstract class SquareState {
		
		/**
		 * @see Square#isGoal()
		 */
		public abstract boolean isGoal();
		
		/**
		 * @see Square#getAction(Player)
		 */
		public abstract BoardAction getAction(Player player);
		
		/**
		 * @see Square#getDialog()
		 */
		public abstract String getDialog();
	}
	
	public class NormalState extends SquareState {
		public boolean isGoal() { return false; }
		public BoardAction getAction(Player player) { return PassiveAction.getInstance(); }
		public String toString() { return "Normal"; }
		public String getDialog() {
			return "Nothing happends!";
		}
	}
	
	public class GoalState extends SquareState {
		public boolean isGoal() { return true; }
		public BoardAction getAction(Player player) { return PassiveAction.getInstance(); }
		public String getDialog() {
			return "Reach the goal!";
		}
		public String toString() { return "Goal"; }
	}
	
	public class SnakeState extends SquareState {
		private int backstep;
		
		public SnakeState(int backstep) { this.backstep = backstep; }
		
		public boolean isGoal() { return false; }
		public BoardAction getAction(Player player) { return new MoveAction(player, backstep); }
		public String getDialog() {
			return "Go back to "+ (number-backstep) +" square!";
		}
		public String toString() { return "Snake"; }
	}
	
	public class LadderState extends SquareState {
		private int skipstep;
		
		public LadderState(int skipstep) { this.skipstep = skipstep; }
		
		public boolean isGoal() { return false; }
		public BoardAction getAction(Player player) { return new MoveAction(player, skipstep); }
		public String getDialog() {
			return "Go to "+ (number+skipstep) +" square!";
		}
		public String toString() { return "Ladder"; }
	}
	
	public class FreezeState extends SquareState {
		public boolean isGoal() { return false; }
		public BoardAction getAction(Player player) { return new FreezeAction(player); }
		public String getDialog() {
			return "Freeze for 1 turn!";
		}
		public String toString() { return "Freeze"; }
	}
	
	public class BackwardState extends SquareState {
		public boolean isGoal() { return false; }
		public BoardAction getAction(Player player) { return new MoveAction(player, -1); }
		public String getDialog() {
			return "Go back 1 square!";
		}
		public String toString() { return "Backward"; }
	}
}
