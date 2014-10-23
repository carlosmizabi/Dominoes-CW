package players;

import java.util.LinkedList;

import dominoes.Bone;
import dominoes.Play;
import dominoes.Table;

public interface PlayAutomaton {
	

	/**
	 * This is a behavior interface it receives
	 * a hand and a table layout and processes
	 * the next move for the machine player.
	 * 
	 * @param playerHand
	 * @param table - the game Layout
	 * @return Play - the machine player next Play
	 */
	Play choosePlay(LinkedList<Bone> boneList, Table table);

}
