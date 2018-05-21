
public class LadderSquare extends Square {

	private int moveTo;
	
	public LadderSquare(int number, int moveTo) {
		super(number);
		this.moveTo = moveTo;
	}
	
	public int moveTo() {
		return moveTo;
	}

}
