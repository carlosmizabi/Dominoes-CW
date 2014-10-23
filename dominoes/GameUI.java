package dominoes;
import java.util.LinkedList;

import dominoes.BoneYard;
import dominoes.DominoUI;
import dominoes.Table;
import dominoes.players.DominoPlayer;

/** The user interface, must provide the following 
 * functionality:
 * 	- A way to specify the number of points necessary to win a game
 *  - A way to select that a player is interactive or a computer player
 *  - Therefore, a player can play against the computer, 
 *  	the computer against itself, or two players can play.
 *	- A way to start a new game at any time.
 *	- Show the current round of the game.
 *  - Show the number of bones a computer player has
 *  - Show the bones that each interactive player has
 *  - Show the number of bones left in the boneyard
 *  - A view of the bones played on the table.
 *  - A way for each interactive player to draw a tile from the boneyard
 *  - A way for each interactive player to pass their turn
 *  - Show the number of points each player currently has
 *  - Show who wins a round
 *  - Show who wins the game
 *	- Display a message when a user attempts to make an invalid move
 *  
 *  The table and bone displays could be graphical (it is up to you).
 */

public abstract class GameUI implements DominoUI {
	
	public GameUI(){ super(); }
	
	/**
	 * This methods display the current state of 
	 * the players, the table, and the boneyard 
	 * as appropriate for the UI.
	 * 
	 * @param players 
	 * @param table
	 * @param boneyard
	 */
	@Override
	public abstract void display(DominoPlayer[] players, Table table, BoneYard boneyard);

	/**
	 * This method displays that the specified player 
	 * made an illegal play.
	 * 
	 * @param player who made the move.
	 */
	@Override
	public abstract void displayInvalidPlay(DominoPlayer player);

	/**
	 * This methods display the winner of the round.
	 * 
	 * @param player who wins the round.
	 */
	@Override
	public abstract void displayRoundWinner(DominoPlayer player);
	
	
	public abstract Play getUserPlayChoice(LinkedList<Play> playList);

}
