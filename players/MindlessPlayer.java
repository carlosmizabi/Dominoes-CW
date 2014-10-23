package players;

import java.util.LinkedList;


import dominoes.CantPlayException;
import dominoes.DominoUI;
import dominoes.GameUI;
import dominoes.Play;
import dominoes.Table;
import dominoes.players.DominoPlayer;
import dominoes.TextUI;

public class MindlessPlayer extends GeneralPlayer{
	
	// The automaton will choose the moves/plays 
	// for the Machine Player
	//
	PlayAutomaton automaton;
	DominoUI userInterface;
	
	public MindlessPlayer(DominoUI ui)
	{
		this("Anonymous", ui);
	}
	public MindlessPlayer(String name, DominoUI ui)
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
		LinkedList<Play> playList;
		if(this.numInHand() != 0)
		{
			playList = ((checkerAutomaton)this.automaton).getPlayables(this.boneList, table);
			
			if(playList != null)
			{ 
				Play play = getUserPlayChoise(playList);
				this.boneList.remove(play.bone());
				return play;
			}
			else{ throw new CantPlayException(); }
		}
		else{
			throw new CantPlayException();
		}
	}
	
	private Play getUserPlayChoise(LinkedList<Play> playList)
	{
		if(getUserInterface() != null){
			return getUserInterface().getUserPlayChoice(playList);
		}else{
			return null;
		}	
	}
	
	/**
	 * Sets up the user interface so that the
	 * observing interface can be kept informed
	 * @param ui
	 */
	public void setUserInterface(DominoUI ui)
	{
		this.userInterface = ui;
	}
	
	
	private GameUI getUserInterface()
	{
		return (GameUI) this.userInterface;
	}
}




















