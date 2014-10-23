package players;

import java.util.LinkedList;

import dominoes.S;
import dominoes.Bone;
import dominoes.CantPlayException;
import dominoes.DominoUI;
import dominoes.TextUI;
import dominoes.GameUI;
import dominoes.Play;
import dominoes.Table;

public class NormalPlayer extends GeneralPlayer {

	// The automaton will choose the moves/plays 
	// for the Machine Player
	//
	PlayAutomaton automaton;
	DominoUI userInterface;
	
	public NormalPlayer(DominoUI ui)
	{
		this("Anonymous", ui);
	}
	public NormalPlayer(String name, DominoUI ui)
	{ 
		super(name); 
		automaton = new checkerAutomaton();
		setUserInterface(ui);
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
		LinkedList<Play> playList = null;
		LinkedList<Play> workList = new LinkedList<Play>();
		LinkedList<Play> leftPlayList;
		LinkedList<Play> rightPlayList;
		if(this.numInHand() != 0)
		{
			leftPlayList  = getPlayables(this.boneList, table, "LEFT");
			rightPlayList = getPlayables(this.boneList, table, "RIGHT");

			if(leftPlayList != null){ workList.addAll(leftPlayList); }
			if(rightPlayList != null){ workList.addAll(rightPlayList); }
			if(!workList.isEmpty()){ playList = workList; }
			
			if(playList != null)
			{ 
				Play play = getUserPlayChoise(playList);
				this.boneList.remove(play.bone());
				return play;
			}
			else{ 
				throw new CantPlayException(); 
			}
		}
		else{
			throw new CantPlayException();
		}
	}
	
	private Play getUserPlayChoise(LinkedList<Play> playList)
	{
		if(getUserInterface() != null){
			return ((TextUI)getUserInterface()).getUserPlayChoice(playList, this.getName(), this.numInHand());
		}else{
			return null;
		}	
	}
	
	public void setUserInterface(DominoUI ui)
	{
		this.userInterface = ui;
	}
	
	private GameUI getUserInterface()
	{
		return (GameUI) this.userInterface;
	}
	
	
	/**
	 * Overloads EasyAutomaton choosePlay()
	 * This method receives the playerHand and the game table
	 * from a client. It then extracts the left and right tile 
	 * numbers and from the table and then searches for the 
	 * first matching bone in the player hand. Once found 
	 * it will return a Play object to the client.
	 * 
	 * @param playerHand the bones
	 * @param table the game layout
	 * @param for which game end is the play
	 * 
	 */
	public LinkedList<Play> getPlayables(LinkedList<Bone> playerHand, Table table, String tail) 
	{
		LinkedList<Play> workList = new LinkedList<Play>();
		
		if(playerHand == null){ return null; }
		else
		{
			Bone bone = null;
			tail = tail.toUpperCase();
			
			if(tail.equals("LEFT")){ 
				
				int tableLeft = table.left();
				for(int i = 0; i < playerHand.size(); i++)
				{
					bone = playerHand.get(i);
					
					if(bone.left() == tableLeft)
					{
						workList.add(new Play(bone, 0));
					}	  
				} 
			}
			else if(tail.equals("RIGHT")){

				int tableRight = table.right();
				for(int i = 0; i < playerHand.size(); i++)
				{
					bone = playerHand.get(i);
					if(bone.left() == tableRight)
					{
						workList.add(new Play(bone, 1));
					}	  
				} 
				
			}
			else{ return null; }
				
			if(workList.size() == 0){ return null; }
			else{
				return workList;
			}

		}	// if else{}
	} // choosePlay(...) 
	


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
