package players;

import java.util.LinkedList;

import dominoes.Bone;
import dominoes.BoneYard;
import dominoes.CantPlayException;
import dominoes.DominoUI;
import dominoes.Play;
import dominoes.Table;
import dominoes.players.DominoPlayer;

/** The interactive player implementation 
 * must allow a user to choose the bone 
 * in their hand to play, and which end 
 * of the table to play the tile on.
 */

public abstract class GeneralPlayer implements DominoPlayer {
	
	private String playerName;
	private int gameScore;
	
	// Round Variables
	//
	LinkedList<Bone> boneList; // for easier insertion and deletion of bones
	private Bone[] boneHand; // needs to be remade whenever boneList changes
	private int handNum;

	public GeneralPlayer()
	{
		this("Anonymous");
	}
	
	public GeneralPlayer(String playerName)
	{
		setName(playerName);
		this.gameScore = 0;
		this.handNum = 0;
		this.boneList = new LinkedList<Bone>();
	}
	
	/************************************************
	 *                  *****************************
	 * Abstract Methods ***************************** 
	 *                  *****************************
	 ************************************************/
	
	/** Each extending player must implement how the 
	 * game is played.
	 * 
	 * Pick a bone to play and where it should be played. 
	 * 
	 * @param  table - The current table configuration 
	 * 		   so the player can select a valid bone to play.
	 *  
	 * @return play - a bone and which end of the layout to 
	 * 		   place it wrapped up in Play object. 
	 * 
	 * @Throws CantPlayException - when the player has no 
	 * 		   bones to play.
	 */
	public abstract Play makePlay(Table table) throws CantPlayException;
	
	/************************************************
	 *           ************************************
	 * Setters   ************************************ 
	 *           ************************************
	 ************************************************/

	/**
	 * Set the player's name.
	 * 
	 * @param the name of player
	 */
	@Override
	public void setName(String name) 
	{
		this.playerName = name;
	}

	/**
	 * Set the number of points the player has.
	 * 
	 * @param points to be added to the player score
	 */
	@Override
	public void setPoints(int points) 
	{
		this.gameScore = points;	
	}
	
	/************************************************
	 *           ************************************
	 * Getters   ************************************ 
	 *           ************************************
	 ************************************************/
	
	/**
	 * Return the name of the player.
	 * 
	 * @return the player's name
	 */
	@Override
	public String getName() 
	{
		return this.playerName;
	}

	/**
	 * Get the number of points the player has.
	 * 
	 * @return the player game score so far.
	 */
	@Override
	public int getPoints() 
	{
		return this.gameScore;
	}
	
	/**
	 * The array of bones that is in the player's hand.
	 * If empty it will return null
	 * 
	 * @return the bones array in player's hand
	 */
	@Override
	public Bone[] bonesInHand() 
	{
		this.boneHand = new Bone[boneList.size()];
		int looper = 0;
		for(Bone b: boneList)
		{
			this.boneHand[looper] = b;
			looper++;
		}
		return this.boneHand;
	}
	
	/**
	 * The number of bones the player has in its hand.
	 * 
	 * @return the bones in hand
	 */
	@Override
	public int numInHand() 
	{
		this.handNum = this.boneList.size();
//		System.out.println(this.getName() + " numInHand() called, handNum = " + this.handNum);
		return this.handNum;
	}
	
	
	/************************************************
	 *                  *****************************
	 * Public Methods   *****************************
	 *                  *****************************
	 ************************************************/

	/**
	 * Draw a tile from the given boneyard.
	 * 
	 * @param the boneyard from where to draw
	 * 		  a bone
	 */
	@Override
	public void draw(BoneYard boneYard) 
	{
		if(boneYard.size() > 0)
		{
			this.boneList.add(boneYard.draw());
			this.handNum = this.boneList.size();
		}
	}
	

	/**
	 * About to start a new round. 
	 * Clear out the player's hand.
	 */
	@Override
	public void newRound() 
	{
		this.boneList.clear();
	}

	
	/**
	 * Tells the player to take back the specified 
	 * bone. This usually happens when the player 
	 * has tried to play an invalid bone.
	 * 
	 * @param invalid bone played.
	 */
	@Override
	public void takeBack(Bone bone) 
	{
		this.boneList.add(bone);
	}

} // closes class MachinePlayer 
