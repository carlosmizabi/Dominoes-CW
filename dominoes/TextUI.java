package dominoes;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import dominoes.Bone;
import dominoes.BoneYard;
import dominoes.DominoUI;
import dominoes.Play;
import dominoes.Table;
import dominoes.players.DominoPlayer;


public class TextUI extends GameUI {
	
	private DominoPlayer[] players;
	private Table table;
	private DominoesExtended game;
	private int displayCount;
	private static final String HR = 
			"%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n";

	public TextUI(){  super(); }
	
	public static void welcomeScreen()
	{
		S.printl(StringTools.centerStringInSpace("\n"+ HR + " WELCOME TO TEXT DOMINOES", 72));
		S.printl(TUIPanel.message("SWEET!!") + "\n" + HR);
	}
	
	public void start(DominoPlayer[] players, int points)
	{
		S.print(TUIPanel.scoreBoard(players[0].getName(), "0", players[1].getName(), "0"));
		S.printl(TUIPanel.message("PLAYING FOR " + points + "pts"));
		S.print("   LOADING  ");
		freeze(9);
		S.print("\n");
	}
	
	public void freeze(long waits)
	{
		long startTime = System.currentTimeMillis();
		long wait = 5000;
		Timer timer = new Timer();  
        TimerTask loadBar = new TimerTask(){ public void run() {   S.print("=");  } };
        timer.schedule(loadBar, 1000, 1000);
		while(startTime + wait > System.currentTimeMillis()){}
		timer.cancel();
	}
	
	public boolean requestConfirmation()
	{
		Scanner scan = new Scanner(System.in);
		String confirm = "";
		boolean confirmation = false;
		do{
			S.print(" ARE YOU SURE, YES - NO ?");
			S.print("\n Enter Here >>> ");
			confirm = scan.nextLine();
			confirm = confirm.toUpperCase();
			
			if(confirm.equals("Y") || confirm.equals("YES")
					|| confirm.equals("N") || confirm.equals("NO")) { confirmation = true; }
			else
			{ S.printl(TUIPanel.message(confirm + "? YES OR NO PLEASE!")); }
			
		}while(confirmation == false);
		if(confirm.equals("N") || confirm.equals("NO")) { confirmation = false; }
		return confirmation;
	}
	
	public int requestPoints()
	{
		Scanner scanner = new Scanner(System.in); 
		String choice = "", confirm = "";
		int playChoice;
		boolean confirmation = false;
		do{	
			playChoice = 0;
			String message = " For How Many Points Would you like to play \n " +
					" (for example: 10, 100, 1000). \n Has to be more than 0 and less than 1.000.000!";
			S.printl(HLine.PERCENTS + "\n" + message + "\n" + HLine.PERCENTS);
			S.print("\n YOUR CHOICE >>> " );
			choice = scanner.nextLine();
			choice = choice.toUpperCase();
			
			try{
				playChoice = Integer.parseInt(choice);
				
			}catch(StringIndexOutOfBoundsException sioobe)
			{
				S.print(TUIPanel.message(choice + " - WOW!! Very Crazy Dude, I like It... but NO! Go for less!")); 
			}
			catch(NumberFormatException nfe)
			{ 
				S.print(TUIPanel.message("Only integer numbers are accepted!")); 
			}

			if(playChoice > 0 && playChoice < 1000000)
			{
				do{
					S.print(HLine.PERCENTS + "\n You'll be playing for :    " + choice + "\n");
					S.print( "\n" + " ARE YOU SURE, YES - NO ? \n\n" + HLine.PERCENTS);
					S.print("\n Enter Here >>> ");
					confirm = scanner.nextLine();
					confirm = confirm.toUpperCase();
					
					if(confirm.equals("Y") || confirm.equals("YES")
							|| confirm.equals("N") || confirm.equals("NO")) { confirmation = true; }
					else
					{ S.printl(TUIPanel.message(confirm + "? YES OR NO PLEASE!")); }
					
				}while(confirmation == false);
				if(confirm.equals("N") || confirm.equals("NO")) { confirmation = false; }
			}
			
		}while((playChoice < 1 && playChoice > 1000000) || confirmation == false);
		return playChoice;
		
	}
	
	public String requestString(String message)
	{
		Scanner scanner; 
		String choice = "", confirm = "";
		int playChoice = 0;
		boolean confirmation = false;
		message = "Please write " + message;
		do{	
			S.printl(HLine.PERCENTS + "\n" + message + "\n" + HLine.PERCENTS);
			S.print("\n Write here >>> " );
			scanner = new Scanner(System.in);
			choice = scanner.nextLine();
			choice = choice.toUpperCase();
			do{
				S.printl( TUIPanel.message(choice)); 
				S.print(" ARE YOU SURE, YES - NO ?");
				S.printl(" Enter Here >>> ");
				confirm = scanner.nextLine();
				confirm = confirm.toUpperCase();
				
				if(confirm.equals("Y") || confirm.equals("YES")
						|| confirm.equals("N") || confirm.equals("NO")) { confirmation = true; }
				else
				{ S.printl(TUIPanel.message(confirm + "? YES OR NO PLEASE!")); }
				
			}while(confirmation == false);
			if(confirm.equals("N") || confirm.equals("NO")) { confirmation = false; }
						
		}while(confirmation == false);
		return choice;
	}
	
	public int playMode(int rangeStart, int rangeEnd)
	{	
		Scanner scanner = new Scanner(System.in); 
		String choice = "", confirm = "";
		int playChoice = 0;
		boolean confirmation = false;
		do{	
			String message = "\n Choose the player mode: \n 1 - You VS Friend \n 2 - You VS Machine  \n 3 - Machine VS Machine \n";
			S.printl(HLine.PERCENTS + message + "\n" + HLine.PERCENTS);
			S.print("\n YOUR CHOICE >>> " );
			choice = scanner.nextLine();
			choice = choice.toUpperCase();
			
			try{
				playChoice = Integer.parseInt(choice);
				
			}catch(NumberFormatException ex)
			{ 
				S.print(TUIPanel.message("Only integer numbers 1, 2, or 3 accepted!")); 
				}
			if(playChoice >= 1 && playChoice <= 3 )
			{
				if(playChoice == 1)      { S.printl("\n 1 - You VS Friend \n");  }
				else if(playChoice == 2) { S.printl("\n 2 - You VS Machine  \n"); }
				else                     { S.printl("\n 3 - Machine VS Machine \n"); }
				
				confirmation = requestConfirmation();
			}			
		}while(playChoice < 1 || playChoice > 3 || confirmation == false);
		return playChoice;
	}
	
	public void setGame(DominoesExtended game)
	{
		this.game = game;
		this.displayCount = -1;
	}
	
	private DominoPlayer[] getDominoPlayers(){ return this.players; }
	
	private void setDominoPlayers(DominoPlayer[] players){ this.players = players; }

	/**
	 * This methods display the current state of 
	 * the players, the table, and the boneyard 
	 * as appropriate for the UI.
	 * 
	 * @param players 
	 * @param table
	 * @param boneyard
	 */
	@Override
	public void display(DominoPlayer[] players, Table table, BoneYard boneyard) 
	{
		if(getDominoPlayers() == null){ setDominoPlayers(players); }
		
		this.displayCount++;
		this.table = table;
		int left = 0;
		int right = 0;
		
		try{
			left = table.left();
			right = table.right();
		}catch(Exception e){
			S.printl("Error extracting tiles in UI");
		}
		
		S.print(HR + HR  + "Move: " + this.displayCount + "\n" + HR + HR + "\n");
	    S.printl(TUIPanel.table(table.layout(), boneyardSize()));
	       	   
	}
	
	/**
	 *  Show the players hands - use for debugging
	 * @param players
	 */
	public void displayHands(DominoPlayer[] players)
	{
	    String playerHand = "";
	    for(int i = 0; i <= 1; i++ )
	    {
		    Bone[] hand = players[i].bonesInHand();
		    if(hand != null)
		    {
				for(Bone b : hand)
				{
					playerHand += "[" + b.left() + "," + b.right() + "] ";
				}
				S.printl("Player \"" + players[i].getName() + "\" hand [" + hand.length + " bones]: " + playerHand + "\n\n");
				playerHand = "";
		    }
	    }
	}

	/**
	 * This method displays that the specified player 
	 * made an illegal play.
	 * 
	 * @param player who made the move.
	 */
	@Override
	public void displayInvalidPlay(DominoPlayer player) 
	{
		S.printl(TUIPanel.message(player.getName() + ", you made an Ivalid Play"));
		
	}

	/**
	 * This methods display the winner of the round.
	 * 
	 * @param player who wins the round.
	 */
	@Override
	public void displayRoundWinner(DominoPlayer player) 
	{
		this.displayCount = 0;
		String message;
		if( player != null)
		{
			message = TUIPanel.scoreBoard(players[0].getName(), "" + players[0].getPoints(), 
                    					  players[1].getName(), "" + players[1].getPoints()) +
                    TUIPanel.roundWinner(player.getName().toUpperCase());
		}else{
			
			message = TUIPanel.roundWinner("NULL PLAYER");
		}
		S.printl(message);
	}
	
	public void displayGameWinner(String winnerName)
	{
		S.print(TUIPanel.scoreBoard(this.players[0].getName(), "" + this.players[0].getPoints(), 
				  players[1].getName(), "" + players[1].getPoints()));
		S.print(TUIPanel.gameWinner(winnerName));
		setDominoPlayers(null); // reset for next game
	}
	

	/**
	 *  It takes a playlist, displays it to the players and 
	 *  asks for their next move choice
	 */
	public Play getUserPlayChoice(LinkedList<Play> playList)
	{
		return getUserPlayChoice(playList, this.players[0].getName(), this.players[0].numInHand());
	}
	
	/**
	 *  It takes a playlist, displays it to the players and 
	 *  asks for their next move choice
	 */
	public Play getUserPlayChoice(LinkedList<Play> playList, String playerName, int boneInHand)
	{
		LinkedList<Bone> list = new LinkedList<Bone>();
		Scanner scanner = new Scanner(System.in);
		String choice = "";
		String leftTail  = "" + this.table.left();
		String rightTail = "" + this.table.right();
		int playChoice = 0;
		int playListSize = playList.size()+1;
		
		// DISPLAY WHOS PLAYING      --------------------------------------
		
		S.print(TUIPanel.message(playerName + "'s TURN -- YOU HAVE " + boneInHand + " bones " ));
		
		// DISPLAY NEXT MOVE OPTIONS --------------------------------------
		
		list.clear();
		for(Play p: playList){ list.add(p.bone()); }
		S.print(TUIPanel.nextMove(leftTail, rightTail, list));
		

		// GET PLAYER MOVE CHOICE -----------------------------------------
		
		do{	
			S.printl("\n Please, Enter bellow the number of the bone to be played: \n >");
			
			choice = scanner.nextLine().trim();
			choice = choice.toUpperCase();
			
			if(choice.equals("BOARD")){
				if(table != null){  S.print(TUIPanel.table(list, boneyardSize())); }
			}
			try{
				playChoice = Integer.parseInt(choice);
			}catch(NumberFormatException ex)
			{ S.printl("Only integer number are accepted (for example: 2 or 3)"); }
			
		}while(playChoice < 1 || playChoice > playListSize-1 );
		
		return playList.get(playChoice-1);
	}
	
	private int boneyardSize()
	{
		int size = 99;
		if(game != null)
		{
			size = (28 - (this.game.numPlayersHands() + this.table.layout().length)); 
		}
		return size;
	}
	
	
}















