
public class SnakeSquare extends Square {

	private int moveTo;
	
	public SnakeSquare(int number, int moveTo) {
		super(number);
		this.moveTo = moveTo;
	}

	public int moveTo() {
		return moveTo;
	}
}
