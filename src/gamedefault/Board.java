package gamedefault;

import square.BackwardSquare;
import square.FreezeSquare;
import square.LadderSquare;
import square.SnakeSquare;
import square.Square;
import gamedefault.Piece;

public class Board {
	public static final int SIZE = 100;
	private Square[] squares;

	public Board() {
		this.squares = new Square[SIZE];
		for (int i = 0; i < squares.length; i++)
			squares[i] = new Square(i);

		initSpecialSquare();
		squares[squares.length - 1].setGoal(true);
	}

	public void initSpecialSquare() {
		squares[1] = new LadderSquare(squares[1].getNumber(), 22);
		squares[3] = new LadderSquare(squares[3].getNumber(), 67);
		squares[5] = new LadderSquare(squares[5].getNumber(), 44);
		squares[19] = new LadderSquare(squares[19].getNumber(), 58);
		squares[29] = new LadderSquare(squares[29].getNumber(), 94);
		squares[51] = new LadderSquare(squares[51].getNumber(), 71);
		squares[56] = new LadderSquare(squares[56].getNumber(), 95);
		squares[70] = new LadderSquare(squares[70].getNumber(), 91);

		squares[42] = new SnakeSquare(squares[42].getNumber(), 16);
		squares[49] = new SnakeSquare(squares[49].getNumber(), 4);
		squares[55] = new SnakeSquare(squares[55].getNumber(), 7);
		squares[72] = new SnakeSquare(squares[72].getNumber(), 14);
		squares[83] = new SnakeSquare(squares[83].getNumber(), 57);
		squares[86] = new SnakeSquare(squares[86].getNumber(), 48);
		squares[97] = new SnakeSquare(squares[97].getNumber(), 39);

		squares[17] = new FreezeSquare(squares[17].getNumber());
		squares[46] = new FreezeSquare(squares[46].getNumber());
		squares[57] = new FreezeSquare(squares[57].getNumber());
		squares[60] = new FreezeSquare(squares[60].getNumber());
		squares[75] = new FreezeSquare(squares[75].getNumber());

		squares[45] = new BackwardSquare(squares[45].getNumber());
		squares[77] = new BackwardSquare(squares[77].getNumber());
		squares[96] = new BackwardSquare(squares[96].getNumber());
	}

	public void addPiece(Piece piece, int pos) {
		squares[pos].addPiece(piece);
	}

	public void movePiece(Piece piece, int steps) {
		int pos = getPiecePos(piece);
		squares[pos].removePiece(piece);
		if (squares[pos] instanceof BackwardSquare)
			steps *= -1;
		int newPos = pos + steps;
		if (newPos >= squares.length)
			newPos = 2 * (squares.length - 1) - newPos;
		addPiece(piece, newPos);
	}

	public boolean pieceIsAtGoal(Piece piece) {
		return squares[getPiecePos(piece)].isGoal();
	}

	public int getPiecePos(Piece piece) {
		for (Square s : squares)
			if (s.hasPiece(piece))
				return s.getNumber();
		return -1;
	}

	public Square getSquare(int pos) {
		return squares[pos];
	}

	public int getBoardSize() {
		return SIZE;
	}

}
