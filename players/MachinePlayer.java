package players;

import java.util.LinkedList;

import dominoes.Bone;
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

public class MachinePlayer extends GeneralPlayer{
	
	// The automaton will choose the moves/plays 
	// for the Machine Player
	//
	PlayAutomaton automaton;
	
	public MachinePlayer()
	{
		this("Anonymous");
	}
	
	public MachinePlayer(String playerName)
	{
		super(playerName);
		automaton = new EasyAutomaton();
	}

	/** 
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
	@Override
	public Play makePlay(Table table) throws CantPlayException 
	{
		if(this.numInHand() != 0)
		{
			Play play = this.automaton.choosePlay(this.boneList, table);
			if(play != null)
			{ 
				this.boneList.remove(play.bone());
				return play; 
			}
			else{ throw new CantPlayException(); }
		}
		else{
			throw new CantPlayException();
		}
	}

} // closes class MachinePlayer 







































