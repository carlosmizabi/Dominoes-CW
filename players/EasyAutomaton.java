package players;


import java.util.LinkedList;

import dominoes.Bone;
import dominoes.Play;
import dominoes.Table;

/**
 *  It return the first 
 *  This Behaviour relies on the table
 *  protocol for table side assignment:
 *  	0 = left side;
 *  	1 = right side;
 *  	2 = initial mode "center";
 *  
 * @author carlosmarques
 *
 */

class EasyAutomaton implements PlayAutomaton{
	
	public EasyAutomaton(){ super(); }

	/**
	 * This method receives the playerHand and the game table
	 * from a client. It then extracts the left and right tile 
	 * numbers and from the table and then searches for the 
	 * first matching bone in the player hand. Once found 
	 * it will return a Play object to the client.
	 * 
	 */
	public Play choosePlay(LinkedList<Bone> playerHand, Table table) 
	{
		/* 
		 * [1,5] [5,6] <--left [6,6] right--> [6,3] [3,2]
		 * bone3 bone2         bone1          bone4 bone5 
		 * 
		 * left: bone2 right [6] matches bone1 left [6]
		 * left: bone3 right [5] matches bone2 left [5] 
		 * 
		 * right: bone4 left [6] matches bone1 right [6]
		 * right: bone5 left [3] matches bone4 right [3] 
		 */
		if(playerHand == null){ return null; }
		else
		{
			int tableLeft = table.left();
			int tableRight = table.right();
			int leftTile  = -1;
			int rightTile = -1;
			Bone bone = null;
			for(int i = 0; i < playerHand.size(); i++)
			{
				Bone b = playerHand.get(i);
				
				/* compare(...) : int
				 * 0 = match left side of table
				 * 1 = match right side of table
				 * -1 = no match
				*/
				// bone left matches table left: flip bone
				//
				leftTile  = compare(b.left(),  tableLeft, tableRight);
				rightTile = compare(b.right(), tableLeft, tableRight);

				if(leftTile == 0) 
				{ 
					bone = playerHand.get(i);
					bone.flip();
					return new Play(bone, 0);
				}
		        if(rightTile == 0)
				{	
		        	bone = playerHand.get(i);
					return new Play(bone, 0);
				}
				if(leftTile == 1)
				{ 
					bone = playerHand.get(i);
					return new Play(bone, 1);
				}
			    if(rightTile == 1)
				{
			    	bone = playerHand.get(i);
					bone.flip();
					return new Play(bone, 1);
				}    
			} // for()
			return null; // if there was no matches there is no play to produce	
		}	// if else{}
	} // choosePlay(...) 
	
	private int compare(int value, int left, int right)
	{
		if(value == left){ return 0;}
		else if(value == right){ return 1; }
		else { return -1; }
	}

} // closes EasyDifficulty









