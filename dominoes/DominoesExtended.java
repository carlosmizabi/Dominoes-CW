package dominoes;

import dominoes.players.DominoPlayer;

public class DominoesExtended extends Dominoes {

	private DominoPlayer[] players; 
	private DominoPlayer player2; 
	private DominoUI ui;
	private int points;
	
	public DominoesExtended(DominoUI ui, DominoPlayer player1,
			DominoPlayer player2, int points, int pips) 
	{
		super(ui, player1, player2, points, pips);
		players = new DominoPlayer[2];
		this.players[0] = player1;
		this.players[1] = player2;
		this.ui = ui;
		this.points = points;
	}
	
	public int numPlayersHands()
	{
		int hand1 = 0, hand2 = 0;
		try{ hand1 = players[0].numInHand();}
		catch(NullPointerException enull){ }
		try{ hand2 = players[1].numInHand();}
		catch(NullPointerException enull){ } 
		return  hand1 + hand2;
	}
	
	/**
	 * @return DominoPlayer[] game players
	 */
	public DominoPlayer[] getPlayers(){	return this.players; }
	
	/**
	 * @return DominoUI
	 */
	public DominoUI getUI(){	return this.ui; }
	
	/**
	 * @return game points target
	 */
	public int getPoints(){	return this.points; }

}











