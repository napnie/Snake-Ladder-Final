import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
	
	private Scanner scanner = new Scanner(System.in);

	public void start() {
		System.out.println("Number of players: ");
		int player = scanner.nextInt();
		scanner.nextLine();
		
		Game game = new Game(player);
		String command = "";
		while(!command.equals("q")) {
			if(command.equals("r")) {
				replay(game);
			} else {
				play(game);
			}
			System.out.println("Press any key to continue, (r)eplay, (q)uit: ");
			command = scanner.nextLine();
		}
	}
	
	private void replay(Game game) {
		game.reset();
		List<BoardAction> replay = game.getReplay();
//		Player cur = game.currentPlayer();
		for(int i=0 ; i<replay.size() ; i++ ) {
			System.out.println("-------------------------");
			System.out.println(game.currentPlayerName() + "'s turn");
			System.out.println("Position: " + game.currentPlayerPosition());
			
			if(game.isCurrentPlayerFreezing()) {
				System.out.println(game.currentPlayerName() + " is freezing!");
				game.unfreeze();
				game.switchPlayer();
				continue;
			}
			System.out.println("Please hit enter to go to next action.");
			
			scanner.nextLine();
			
			BoardAction roll = replay.get(i++);
			if( roll instanceof RollAction ) {
				RollAction rollCast = (RollAction) roll;
				BoardAction move = replay.get(i++);
				String rollAct = game.excuteAction(rollCast);
				System.out.println( rollAct );
				System.out.println( game.excuteAction(move) );
				
				if( rollCast.getFace() == 6 ) {
					BoardAction roll2 = replay.get(i++);
					BoardAction move2 = replay.get(i++);
					System.out.println( game.excuteAction(roll2) );
					System.out.println( game.excuteAction(move2) );
				}
			}
			
			System.out.println("Land in " + game.getSquareType() + " at " + game.currentPlayerPosition() );
			BoardAction effect = replay.get(i);
			System.out.println( game.excuteAction(effect) );
			System.out.println("Position: " + game.currentPlayerPosition());
			if(game.currentPlayerWins()) {
				System.out.println(game.currentPlayerName() + " Win!");
				return;
			}
			game.switchPlayer();
		}
	}
	
	private void play(Game game) {
		game.reset();
		game.clearReplay();
		while(!game.isEnded()) {
			System.out.println("-------------------------");
			System.out.println(game.currentPlayerName() + "'s turn");
			System.out.println("Position: " + game.currentPlayerPosition());
			
			if(game.isCurrentPlayerFreezing()) {
				System.out.println(game.currentPlayerName() + " is freezing!");
				game.unfreeze();
				game.switchPlayer();
				continue;
			}
			System.out.println("Please hit enter to roll a die.");
			
			scanner.nextLine();
			
			int face = game.currentPlayerRollDice();
			System.out.println( game.currentPlayerMovePiece(face) );
			if( face == 6) {
				face = game.currentPlayerRollDice();
				System.out.println( game.currentPlayerMovePiece(face) );
			}
			
			System.out.println("Land in " + game.getSquareType() + " at " + game.currentPlayerPosition() );
			System.out.println( game.excuteAction() );
			System.out.println("Position: " + game.currentPlayerPosition());
			if(game.currentPlayerWins()) {
				System.out.println(game.currentPlayerName() + " Win!");
				game.end();
			}
			game.switchPlayer();
		}
	}
	
	public static void main(String[] args) {
		new ConsoleUI().start();
	}
}
