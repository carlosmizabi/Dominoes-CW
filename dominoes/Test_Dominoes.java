package dominoes;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import players.MachinePlayer;


import dominoes.BoneYard;
import dominoes.DominoUI;
import dominoes.Dominoes;
import dominoes.Table;
import dominoes.players.DominoPlayer;

/**
 * The purpose of this test is to assess
 * the functionalities of the Dominoes class
 * 
 * CLAIM:
 * 
 * This class represents the dominoes game. In a 
 * dominoes game there are 2 players, each taking 
 * turns playing dominoes. The first player to empty 
 * their hand of dominoes, or bones as they are know, 
 * wins the round. The loser counts up the weight of 
 * the bones left in their hand, and the winner gets 
 * that many points. If neither player can play, then 
 * the player with lightest hand wins the round and 
 * the points. Games are won when a player can play, 
 * then the player with the lightest hand wins the round 
 * and the points. Games are won when a player has won 
 * a predetermined number of points (usually 100)
 * 
 * CLAIM PROBLEMS:
 * 
 * Round should be won when "when a player can [not] play, 
 * then the player with the lightest hand wins the round 
 * and the points" rather than the game.
 */

/** 
 * Constructor takes a game user interface, two players
 * the number of points to win the game and max number of pips.
 * 
 * class Dominoes has several methods:
 * 
 * 	Public: 
 * 			play(): DominoPlayer
 * 
 *  Private:
 *  		computeWinner() : int
 *  		newRound() : void
 *  		takeTurnDominoPlayer(DominoPlayer) : boolean
 *  		weight(DominoPlayer) : int
 * 
 *
 * Tasks:
 * 
 * 	- Need to assess how the class interacts with the 
 * 	  objects it receives as parameters. 
 */
/*
public class Test_Dominoes {
	
	private DominoPlayer up1;
	private DominoPlayer up2;
	private DominoPlayer[] players;
	private Table table;
	private BoneYard boneyard; 
	private Dominoes game;
	
	@Before
	public void setUpt()
	{
		
		// Lets create some players
		//
		up1 = new MachinePlayer();
		up2 = new MachinePlayer();
		players = new DominoPlayer[2];
		players[0] = up1;
		players[1] = up2;
		
		// - Create a new table, boneyard, interface
		// and dominoes game
		// 
		table = new Table();
		boneyard = new BoneYard(6);
		DominoUI ui = new GameUI(players, table, boneyard);
		game = new Dominoes(ui, up1, up2, 100, 6);
		
	}

	@Test
	public void test() 
	{
		// Create 28 artificial "mindless" Plays
		//
		for(int i = 0; i < 14; i++)
		{
			
		}
		

	}

}


*/














