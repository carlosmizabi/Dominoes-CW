package dominoes;
import dominoes.DominoUI;
import dominoes.Dominoes;
import dominoes.players.DominoPlayer;

/**
 * GameFactory builds a DominoesExtended Game
 * @author carlosmarques
 *
 */
public class GameFactory {

	private GameFactory() { super(); }

	
	public static Dominoes buildGame(DominoUI ui, DominoPlayer player1,
			DominoPlayer player2, int points)
	{
//		DominoUI ui = new TextUI();
		Dominoes newGame = new DominoesExtended(ui, player1,
				player2, points, 6);
		((TextUI)ui).setGame((DominoesExtended)newGame);
		return newGame;
	}
}
