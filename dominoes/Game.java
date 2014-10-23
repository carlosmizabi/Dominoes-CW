package dominoes;
import players.MachinePlayer;
import players.MindlessPlayer;
import players.NormalPlayer;
import dominoes.*;
import dominoes.players.DominoPlayer;


public class Game {
	
	TextUI ui;

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		new Game();
	}
	
	public Game()
	{		
		this.ui = new TextUI();
		TextUI.welcomeScreen();
		DominoPlayer[] players = playMode();
		int points = setPoints();
		start(players, points);
		
		// the game
		//
		Dominoes game = GameFactory.buildGame(ui, players[0], players[1], points);
		DominoPlayer winner = game.play();
		this.ui.displayGameWinner(winner.getName());
	}
	
	private void start(DominoPlayer[] players, int points)
	{
		this.ui.start(players, points);
	}
	
	private int setPoints()
	{
		return this.ui.requestPoints();
	}
	
	/**
	 * Set up the play mode, it creates two players according to the user
	 * choice which it gets from the UI.
	 * @return
	 */
	private DominoPlayer[] playMode()
	{
		DominoPlayer[] players = new DominoPlayer[2];
		DominoPlayer up1 = null;
		DominoPlayer up2 = null;
		
		// Ask user for play mode
		//
		int playMode = this.ui.playMode(1, 3);
		
		if(playMode == 1)
		{
			String playerName = this.ui.requestString("Your Name");
			String friendName = this.ui.requestString("Your Friend's Name");
			up1 = new MindlessPlayer(playerName, this.ui);
			up2 = new MindlessPlayer(friendName, this.ui);
		}
		else if( playMode == 2)
		{
			String playerName = this.ui.requestString("Your Name");
			up1 = new NormalPlayer(playerName, this.ui);
			up2 = new MachinePlayer("MACHINE");
		}
		else{
			up1 = new MachinePlayer("Good MACHINE");
			up2 = new MachinePlayer("Evil MACHINE");
		}
		players[0] = up1;
		players[1] = up2;
		return players;
	}

}














