package players;

import java.util.LinkedList;

import dominoes.Bone;
import dominoes.Play;
import dominoes.Table;

/**
 * This Automaton extends the EasyAutomaton
 *  Using the choosePlay it returns a 
 *  "easy" Play that can be used as a suggestion
 *  to the Player. A second method, getPlayables(),
 *  is the extended functionality which will return 
 *  a List of Plays (bone + position) for the client to choose
 *  from.
 *  
 * @author carlosmarques
 *
 */
public class checkerAutomaton extends EasyAutomaton implements PlayAutomaton 
{
	
	/**
	 * This method relies on choosePlay(), however,
	 * instead of sending it a list of several bones 
	 * it sends a list of one bone in order to access 
	 * if it can be played. It stores the Plays 
	 * and return them. If empty, it return null instead
	 */
	public LinkedList<Play> getPlayables(LinkedList<Bone> playerHand, Table table)
	{
		LinkedList<Play> playList = new LinkedList<Play>();
		LinkedList<Bone> workList = new LinkedList<Bone>();
		
		if(playerHand == null || playerHand.size() == 0)
		{ return null; }

		for(Bone b: playerHand)	
		{
			workList.clear();
			workList.add(b);
			Play play = choosePlay(workList, table);
			if(play != null)
			{
				playList.add(play);
			}
		}
		if(playList.size() > 0){
			return playList;
		}else{
			return null;
		}	
	}

	
	public LinkedList<Play> getLeftRightPlayables(LinkedList<Bone> playerHand, Table table)
	{
		LinkedList<Play> playList = new LinkedList<Play>();
		LinkedList<Bone> workList = new LinkedList<Bone>();
		
		if(playerHand == null || playerHand.size() == 0)
		{ return null; }
		
		
		return null;
	}
	
	
}
















