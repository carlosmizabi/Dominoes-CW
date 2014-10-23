package dominoes;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import players.MachinePlayer;
import dominoes.Bone;
import dominoes.BoneYard;
import dominoes.CantPlayException;
import dominoes.CubbyHole;
import dominoes.InvalidPlayException;
import dominoes.Play;
import dominoes.Table;
import dominoes.players.DominoPlayer;


public class Test_MachinePlayer {
	
	private DominoPlayer player_1;
	private DominoPlayer player_2;
	private Table table;
	private BoneYard boneyard;
	
	@Before
	public void setUp()
	{
		boneyard = new BoneYard(6);
		player_1 = new MachinePlayer();
		player_2 = new MachinePlayer();
		table = new Table();
		Bone[] boneHand = new Bone[7];
		for(int i = 0; i < 7; i++) { player_1.draw(boneyard); }
		for(int i = 0; i < 7; i++) { player_2.draw(boneyard); }	
	}

	@Test
	public void testBonesInHand() 
	{
		boolean valid = true;
		Bone[] player_1_hand = player_1.bonesInHand();
		for(int i = 0; i < 7; i++) 
		{ 
			Bone b = player_1_hand[i];
			if(b == null){ valid = false; break; }
		}	
		assertTrue("The bone hand was not valid", valid);
	}

	@Test
	public void testDraw() 
	{
		/* This test will make a player draw 28
		 * bones. Then it will "call" each of them
		 * if it goes out of bounds it will produce
		 * an ArrayIndexOutOfBounds Error. 
		 * It is useful for quick error spotting.
		 */
		boneyard = new BoneYard(6);
		player_1 = new MachinePlayer();
		for(int i = 0; i < 28; i++){ player_1.draw(boneyard); }
		Bone[] player_1_hand = player_1.bonesInHand();
		for(int i = 0; i < 28; i++) 
		{  Bone bone = player_1_hand[i]; }
	}

	@Test
	public void testGetName() 
	{
		String playerName = this.player_1.getName();
		String expected = "Anonymous";
		assertEquals("Player name does not match", expected , playerName);
	}

	@Test
	public void testGetPoints() 
	{
		int playerPoints = this.player_1.getPoints();
		int expected = 0;
		assertEquals("Player points that does not correspond", expected, playerPoints);
	}

	/* testMakePlay() ---------------------------------
	 * 
	 * REMEMBER: the Table only cares about
	 * receiving a bone and the place to put it.
	 * Hence, after the first piece is played to
	 * the "center" (2), any bone thereafter, 
	 * according to the Table, can be put either
	 * on the left (0) or right (1) regardless if 
	 * is correct.
	 * The Player must ensure it plays correctly:
	 * previous bone's "left" matches next bone's 
	 * "right", for example:
	 * 
	 *        (0)    (2)     (1)
	 *     <--left--center--right--> 
	 * [1,5]-[5,6]--[6,6]--[6,3]-[3,2]
	 * bone3 bone2 bone1 bone4 bone5 
	 * 
	 * [1,5]-[5,6]--[6,6]--[6,3]-[3,2]
	 *    ^---^ ^----^ ^----^ ^---^
	 *    match  match match  match
	 * -------------------------------------------------
	 */
	@Test
	public void testMakePlay() 
	{
		
		table = new Table();
		boneyard = new BoneYard(6);
		player_1 = new MachinePlayer();
		
		// FIRST PLAY -------------------------------
		//
		Bone firstBone = new Bone(6,6);
		Play firstPlay = new Play(firstBone, 2);
		try {
			table.play(firstPlay);
		} catch (InvalidPlayException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// PLAYER DRAW ALL BONES AND PLAY THEM -------
		//
		for(int i = 0; i <= 28; i++) { player_1.draw(boneyard); }
		for(int i = 0; i <= 28; i++) 
		{ 
			try {
				
				Play play = player_1.makePlay(table);
				if(play != null){ table.play(play); }			
			} 
			catch (InvalidPlayException e) 
			{
				System.out.println("InvalidPlayException");
				e.printStackTrace();
			} 
			catch (CantPlayException e) 
			{
				System.out.println("CantPlayException");
				e.printStackTrace();
			} 
		}
		
		// If previous bone left doesn't match next Bone right,
		// then bones haven't been played correctly
		//
		Bone[] layout = table.layout();
		int prevBoneRight = -1;
		boolean validMatches = true;
		for(Bone b: layout)
		{
			if(!b.equals(layout[0])) // not on first bone
			{
				System.out.println(b.left());
				if(prevBoneRight != b.left()) { validMatches = false; }	
			}
			prevBoneRight = b.right();	
		}

		assertTrue(validMatches);
	}

	@Test
	public void testNewRound() 
	{
		/* After a new Round the Player should not have any bones.
		 * Two fields getters must be tested:
		 * int numHand -- how many bones
		 * Bone[] boneHand -- the bones in hand
		 * 
		 * Remember: The MachinePlayer will generate a linkedList 
		 * internally and will only generate a Bone[] on request 
		 * "bonesInHand()". If there are no bones it will return null.
		 * 
		 */
		player_1.newRound();
		int numHand = player_1.numInHand();
		int expNumHand = 0;
		String message = "The int numHand should be zero at newRound!";
		assertEquals(message, expNumHand, numHand);
		message = "bonesInHand(): should have returned null after newRound()!";
		assertNull(message, player_1.bonesInHand());
	}

	@Test
	public void testNumInHand() 
	{
		int handNum = this.player_1.numInHand();
		int expected = 7;
		assertEquals("The handNum doesn't match", expected, handNum);
	}

	@Test
	public void testSetName() 
	{
		this.player_1.setName("Jogador");
		String playerName = this.player_1.getName();
		String expected = "Jogador";
		assertEquals("Name not set properly", expected, playerName);
	}

	@Test
	public void testSetPoints() 
	{
		// First Round
		//
		this.player_1.setPoints(9);
		int playerPoints = this.player_1.getPoints();
		int expected = 9;
		assertEquals("Points not set properly", expected, playerPoints);

		// Second Round
		//
		this.player_1.setPoints(0);
		playerPoints = this.player_1.getPoints();
		expected = 9;
		assertEquals("Points not set properly", expected, playerPoints);

		// Third Round 
		//
		this.player_1.setPoints(-10);
		playerPoints = this.player_1.getPoints();
		expected = 0;
		assertEquals("Points not set properly", expected, playerPoints);
	}

	@Test
	public void testTakeBack() {
		fail("Not yet implemented");
	}

}
