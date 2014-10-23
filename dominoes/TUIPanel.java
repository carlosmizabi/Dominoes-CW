package dominoes;                                                                                                                                                             

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.LinkedList;

/**
 *  This class holds holds all the visual (textual)
 *  elements of the TextUI.
 *  
 * @author carlosmarques
 *
 */
public class TUIPanel {
	
	public static final int LINE_LENGTH = 72;
	public static final String ROUND_WINNER = "IS THE WINNER OF THIS ROUND";

	public static String nextMove(String leftTail, String rightTail, LinkedList<Bone> boneList)
	{
		String boneRows = "", bone1 = "", bone2 = "", bone3 = "";
		String divider = "|********|    ------------------------------------------------    |********|\n";
		StringBuffer sb = new StringBuffer();
		
		// HEADER ------------------------------------------------------------------------
		// -------------------------------------------------------------------------------
		
		sb.append(HLine.EQUALS +
          "|****************************    NEXT MOVE     ****************************|\n" +
          HLine.EQUALS + HLine.STARS_SPACED +
          "|********|           ===============| |===============            |********|\n" +
          "|********|   LEFT    ||      "+ leftTail +"      | |       "+ rightTail + "     ||    RIGHT   |********|\n" +
          "|********|           ===============| |===============            |********|\n" +
          HLine.STARS_SPACED + 
          HLine.STARS_COLUMN + "       CHOOSE THE NUMBER OF YOUR NEXT BONE TO PLAY      " + HLine.STARS_COLUMN + "\n" +
          HLine.STARS_COLUMN + "    ------------------------------------------------    " + HLine.STARS_COLUMN + "\n");

		
		// BODY ------------------------------------------------------------------------
		// -------------------------------------------------------------------------------
		
        String pad = StringTools.makeEmptySpace(5); 
        int size = boneList.size(), left = -1, right = -1;
        int count = 0; 	// bones in a row
		for(int i = 0; i < size; i++)
		{	
			count++;
			left = boneList.get(i).left();
			right= boneList.get(i).right();
			
			// BEFORE ROW --------------------------------------------
			
			if(count == 1){ sb.append(HLine.STARS_COLUMN + pad); }
		 	
			if( i+1 < 10){ sb.append(" "); }
			
			// ADD BONE  ---------------------------------------------
			
			sb.append(" " + (i+1) + "..." + "[" + left + "," +right + "]" + pad); 

			// AFTER ROW ---------------------------------------------
			
			if(count == 3)
			{ 
				sb.append("   " + HLine.STARS_COLUMN + "\n"); 
				if(i > 3){ sb.append(divider); }
				count = 0; 
			}	
		}
		// Append extra space to smaller rows
		//
		if(count == 1){ sb.append(StringTools.makeEmptySpace(35) + HLine.STARS_COLUMN + "\n"); }
		if(count == 2){ sb.append(StringTools.makeEmptySpace(19) + HLine.STARS_COLUMN + "\n"); }
		
		// FOOTER ------------------------------------------------------------------------
		// -------------------------------------------------------------------------------
		
		sb.append(HLine.EQUALS + " ENTER THE NUMBER OF THE PLAY BONE (note these are only your PLAYABLE bones) \n"); 
		return sb.toString();
	}
	
	public static String hands()
	{
		String head = HLine.EQUALS +
				"|******************************    HAND    ********************************|"+ "\n" +
				HLine.EQUALS +
                "Player \"XxXxXxXx\" hand [N bones]: " + "\n" +
                HLine.CUT_LINE + 
				"[x,o] [o,x] [x,o] [o,x][x,o][o,x][x,o][o,x][x,o][o,x]" + "\n" +
				"[x,o][o,x][x,o][o,x][x,o][o,x][x,o][o,x][x,o][o,x]" + "\n" +
				"[x,o][o,x][x,o][o,x][x,o][o,x][x,o][o,x] " + "\n" +
				 
				HLine.EQUALS +  HLine.STARS +  HLine.EQUALS ;
		
		return head;
	}
	
	/**
	 * Reverses value of boolean
	 * @param bool
	 * @return
	 */
	private static boolean reverseBoolean(boolean bool)
	{
		 if(bool == true)  return false;
		 else return true;
	}
	
	public static String table(Bone[] layout, int boneyard)
	{
		LinkedList<Bone> bones = new LinkedList<Bone>();
		for(Bone b: layout){ bones.add(b); }
		return table(bones, boneyard);
	}
	
	public static String table(LinkedList<Bone> boneList, int boneyard)
	{
		StringBuffer sb = new StringBuffer();	// final string buffer

		// Line Extraction & SetUp-------------------------------------------
		
		LinkedList<String> rows = extractTableLines(boneList);
		
		// Reverse Rows and add Sidebars ------------------------------------
		
		int i = 0;
		while(i+3 < rows.size())
		{
			rows.set(i+3, reverseTableBoneLines(rows.get(i+3)));
			i += 6;
		}

		addTableLinesSideBars(rows);

		// Final String Composition ------------------------------------------
		
		sb.append(tableHeader(boneList.size(), boneyard));
		for(String s: rows){ sb.append(s); }
		sb.delete(sb.length()-1, sb.length()); // remove last new line "\n"
		return sb.append(tableFooter()).toString();
		
	}
	
	private static void addTableLinesSideBars(LinkedList<String> rows)
	{
		int lineCount = 0;
		boolean reversed = false;
		// Strings for visual composition ----------------------------------
		
		String paddingLeft = "", paddingRight = "", tempLine = "";
		String pads = StringTools.makeEmptySpace(8), span = StringTools.makeEmptySpace(35);
		String leftMargin = HLine.STARS_COLUMN + pads;
		String rightMargin = pads + HLine.STARS_COLUMN + "\n";
		
		for(int i = 0; i < rows.size(); i++)
		{
			lineCount++;
			if(lineCount == 1)
			{
				rows.set(i, leftMargin + rows.get(i) + rightMargin);	
			}
			else if( lineCount == 2 || lineCount == 3)
			{
				if(reversed == false)
				    rows.set(i, leftMargin + "  " + span + rows.get(i)  + rightMargin );
				else
					rows.set(i, leftMargin  + rows.get(i) + "  " + span + rightMargin );
				
				if( lineCount == 3){ lineCount =0; reversed = reverseBoolean(reversed); }
			}
		}
	}
	
	private static String reverseTableBoneLines(String string)
	{
		string = StringTools.reverseString(string);
		string = string.replace(']', '-');
		string = string.replace('[', ']');
		string = string.replace('-', '[');
		return string;
	}
	
	private static LinkedList<String> extractTableLines(LinkedList<Bone> boneList)
	{
		LinkedList<Bone> listCopy = new LinkedList<Bone>();
		listCopy.addAll(boneList);
		StringBuffer line = new StringBuffer(); // line buffer
		LinkedList<String> rows = new LinkedList<String>();
		int item = 0;
		Bone bone;

		while(!listCopy.isEmpty())
		{
			item++;
			
			// horizontal line -------------------------
			
			line.setLength(0); // clear
			while(item <= 8 && !listCopy.isEmpty())
			{
				bone = listCopy.pop();
				line.append("[" + bone.left()+ "," + bone.right() + "]"); // bone
				item++;
			}
			// add extra space for short lines
			if(item-1 < 8){
				for( int i = item-1; i != 8; i++) { line.append("     "); }
			}
			rows.add(line.toString());
			
			// vertical bone 9th --------------------------
			
			if(!listCopy.isEmpty())
			{
				bone = listCopy.pop();
				rows.add( "|" + bone.left()  + "|" );
				rows.add( "|" + bone.right() + "|" );
				item = 0; // reset
			}	
		}
		
//		for(String s: rows){ S.printl("*****************" + s); }
		S.printl(rows.size());
		return rows;
	}
	

	private static String tableHeader(int played, int boneyard)
	{
		DecimalFormat ft = new DecimalFormat("#00");
		return "|******************************    TABLE   ********************************|"  + "\n" +
			   "|********|  Played: " + ft.format(played) + "                           Boneyard:  " + ft.format(boneyard)  + "    |********|" + "\n" +  
				HLine.STARS_EQUALS +  HLine.STARS_SPACED ;
	}
	
	private static String tableFooter()
	{
		return "\n" + HLine.STARS_SPACED +  HLine.STARS_EQUALS + HLine.STARS + HLine.EQUALS;
	}
	
	
	public static String scoreBoard(String player_1_name, String player_1_score, 
			                      String player_2_name, String player_2_score)
	{
		player_1_name = scoreBoardFormat(player_1_name, "player", "start");
		player_2_name = scoreBoardFormat(player_2_name, "player", "end");
		player_1_score = scoreBoardFormat(player_1_score, "score", "end");
		player_2_score = scoreBoardFormat(player_2_score, "score", "start");
		
		String scoreBoard = 
				HLine.EQUALS + "|******************************    SCORE   ********************************|\n" +
						"|****|================================================================|****|" + "\n" + 
                "|****| "   + player_1_name  + "  " + player_1_score + " << VS >> " + player_2_score + "  " + player_2_name +   " |****|" + "\n" + 
				"|****|================================================================|****|" + "\n";
		return scoreBoard;
	}
	
	/**
	 *  It will return a message framed 
	 *  
	 * @param message
	 * @return
	 */
	
	public static String message(String message)
	{
		int lineWidth = 50;
		LinkedList<String> lineList = StringTools.cutToLines(message, lineWidth);
		String top, column, bottom, screen;
		top = HLine.EQUALS + HLine.STARS + HLine.STARS_SPACED;
		bottom = HLine.STARS + HLine.EQUALS;
		screen = top;
		for(String line : lineList)
		{
			screen += HLine.STARS_COLUMN + "   " + line + "    " + HLine.STARS_COLUMN + "\n"; 
		}
		return screen += HLine.STARS_SPACED + bottom;
	}
	

	/**
	 * 
	 * @param string -> to be formated
	 * @param type   ->	"SCORE","PLAYER"	
	 * @param end	 -> "START","END"
	 * @return formated string
	 */
	private static String scoreBoardFormat(String string, String type, String end)
	{
		type = type.toUpperCase();
		end  = end.toUpperCase();
		
		String atSTART = "START", atEND = "END", space = "", finString = "";
		int scoreSize = 9, nameSize  = 15;
		
		switch(type)
		{
			case "PLAYER":
				
				if(string.length() > nameSize) { finString = string.substring(0, nameSize); }
				else{
					space = StringTools.makeEmptySpace(nameSize-string.length());
					if(end.equals(atSTART)){ finString = string + space; }
					if(end.equals(atEND)){ finString = space + string; }
				}
				break;
				
			case "SCORE":
				if(string.length() > scoreSize) 
				{ 
					finString = string.substring((string.length() - scoreSize), string.length()); 
					}
				else{
					space = StringTools.makeEmptySpace(scoreSize-string.length());
					if(end.equals(atSTART)){ finString = string + space; }
					if(end.equals(atEND)){ finString = space + string; }
				}
				break;
				
			default:
				return "EMPTY";
		}
		return finString;
	}
	
	public static String roundWinner(String playerName)
	{
		String stripe = HLine.DENTED_LINE + HLine.CIRCLES_LINE + HLine.CIRCLES_LINE + HLine.DENTED_LINE;
		playerName = StringTools.centerStringInSpace(playerName, (LINE_LENGTH)-1) + "\n";
		return stripe + "\n" + StringTools.centerStringInSpace(HLine.BARS_LINE, LINE_LENGTH) + "\n" +  
			   playerName + "\n" + StringTools.centerStringInSpace(ROUND_WINNER, (LINE_LENGTH)-1) +"\n\n" +
		       StringTools.centerStringInSpace(HLine.BARS_LINE, LINE_LENGTH) + "\n" + stripe;
	}
	
	
	public static String gameWinner(String playerName)
	{
		String winner = " ()()()__||__()()() "+  StringTools.centerStringInSpace(playerName, 31)    + " ()()()__||__()()()" + "\n";
		return CASTLE + winner + CASTLE_GROUND;
	}
	
	public static final String CASTLE =
                                  
"                             *  .  .  . *                             " + "\n" +
"             .      *         . .  .  . .       *       .             " + "\n" +
"     *   .   .   .      /. .  . .\\ | /. .  . /.     .   .   .   *     " + "\n" +
"     ^    .  .  .  .   ///..  .  .\\|/.  .  .///. .   .  .  .  . /.    " + "\n" +
"  * //. . . \\|/. .  . /////.. ----<+>---- ./////.. . . \\|/. .  ///. * " + "\n" +
"   ////. .--<+>-- . .///////.  . ./|\\. .  ///////.. .--<+>--. /////.  " + "\n" +
"  //////. . /|\\. . ./////////. . / | \\ . /////////. . ./|\\. .///////. " + "\n" +
" ^^^^^^^^^.   .\\.  ^^^^^^^^^^^^.  .|.  .^^^^^^^^^^^^ ./.   .^^^^^^^^^^" + "\n" +
" )()()()()  *  .\\. ()()()()()()   .|.   ()()()()()()./.  *  ()()()()()" + "\n" +
" )()()()()^^^^^^^^^()()()()()()^^^^^^^^^()()()()()()^^^^^^^^()()()()()" + "\n" +
" )()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()" + "\n" +
" ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^" + "\n" +
" ()()()()()()()()()()()()()()|||||||||||||()()()()()()()()()()()()()()" + "\n" +        
" ()()()__||__()()()()()()          |          ()()()()()()__||__()()()" + "\n" +
" ()()()__||__()()()()()        \\   |   /        ()()()()()__||__()()()" + "\n" +
" ()()()__||__()()()()      `.   \\  :  /  .'       ()()()()__||__()()()" + "\n" +
" ()()()__||__()()()          `. __/ \\__ .'          ()()()__||__()()()" + "\n" +
" ()()()__||__()()()      _______\\ _|_ /________     ()()()__||__()()()" + "\n" +
" ()()()__||__()()()             /_ | _\\             ()()()__||__()()()" + "\n" +
" ()()()__||__()()()           .'  \\ /  `.           ()()()__||__()()()" + "\n" +
" ()()()__||__()()()         .'  /  :  \\  `.         ()()()__||__()()()" + "\n" +
" ()()()__||__()()()            /   |   \\            ()()()__||__()()()" + "\n";

	public static final String CASTLE_GROUND =

" ()()()__||__()()()     |=====================|     ()()()__||__()()()" + "\n" +
" ()()()__||__()()()     |   THE GAME WINNER   |     ()()()__||__()()()" + "\n" +
" ()()()__||__()()()     |=====================|     ()()()__||__()()()" + "\n" +
" ()()()()()()()()()    ()()()()()()()()()()()()(    ()()()()()()()()()" + "\n" +
" ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^" + "\n" +
" ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^	" + "\n";
}
//////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////

/** --- HOW THE TextUI GAME DISPLAY SHOULD LOOK LIKE ----
*
==========================================================================
|************************************************************************|
|*******|    Name                    VS                     Name |*******|
|********|=======================================================|*******|
|**************|             99 << SCORES >> 99           |**************|
|**************|==========================================|**************|
==========================================================================
|******************************    TABLE   ******************************|
|********|  Played:                               Boneyard:     |********|
|********|======================================================|********|
|********|                                                      |********|
|********|        [x,o][o,x][x,o][o,x][x,o][o,x][x,o][o,x]      |********|
|********|                                             |x|      |********|
|********|                                             |o|      |********|
|********|                                             |o|      |********|
|********|                                             |x|      |********|
|********|        [x,o][o,x][x,o][o,x][x,o][o,x][x,o][o,x]      |********|
|********|        |x|                                           |********|
|********|        |o|                                           |********|
|********|        |o|                                           |********|
|********|        |x|                                           |********|
|********|        [x,o][o,x][x,o][o,x][x,o][o,x][x,o][o,x]      |********|
|********|                                                      |********|
|********|======================================================|********|
|************************************************************************|
==========================================================================


==========================================================================
|******************************    HAND    ******************************|
==========================================================================
 Player "XxXxXxXx" hand [N bones]: 
 ------------------------------------------------------------------------
 [x,o] [o,x] [x,o] [o,x][x,o][o,x][x,o][o,x][x,o][o,x]
 [x,o][o,x][x,o][o,x][x,o][o,x][x,o][o,x][x,o][o,x]
 [x,o][o,x][x,o][o,x][x,o][o,x][x,o][o,x] 
 =========================================================================

==========================================================================
|**************************     NEXT MOVE     ***************************|
==========================================================================
|********|                                                      |********|
|********|           ===============| |===============          |********|
|********|   LEFT    ||     2       | |       0     ||  RIGHT   |********|
|********|           ===============| |===============	        |********|
|********|                                                      |********|
|********|        Choose the number of the bone to play:        |********|
|********|   ------------------------------------------------   |********| 
|********|     1 --  [1,4] |   2 --  [1,4] |   3 --   [1,4]     |********|
|********|   ------------------------------------------------   |********| 
|********|     4 --  [1,4] |   4 --  [1,4] |   6 --   [1,4]     |********|
|********|   ------------------------------------------------   |********| 
|********|     7 --  [1,4] |   8 --  [1,4] |   9 --   [1,4]     |********|
|********|   ------------------------------------------------   |********| 
|********|    10 --  [1,4] |  11 --  [1,4] |  12 --   [1,4]     |********|
|********|   ------------------------------------------------   |********|
|********|             CHOOSE YOUR NEXT BONE TO PLAY            |********|
==========================================================================
  ENTER "L" (Left) OR "R" (Right) WITH THE BONE NUMBER (ex, 2L, R3, etc) 

             >>>                           
             
==========================================================================


|=========================================================================|
|*************************************************************************|
|********|                                                      |********|
|********|                                                      |********| 
|********|                                                      |********|
|********|	     INVALID PLA                                    |********|
|********|                                                      |********|
|********|                                                      |********|
|********|                                                      |********|
|*************************************************************************|
|=========================================================================|

^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 )()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()
 )()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

                      | | | | | | | | | | | | | | 

	                         THE DOCTOR 
	                        WON THIS ROUND

                      | | | | | | | | | | | | | | 

^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 )()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()
 )()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                                   .
                             *  .  .  . *
             .      *         . .  .  . .       *       .
     *   .   .   .      /\ .  . .\ | /. .  . /\     .   .   .   *
     ^    .  .  .  .   /\\\.  .  .\|/.  .  ./\\\ .   .  .  .  . /\ 
  * /\\ . . \|/. .  . /\\\\\. ----<+>---- ./\\\\\. . . \|/. .  /\\\ *
   /\\\\ .--<+>-- . ./\\\\\\\  . ./|\. .  /\\\\\\\. .--<+>--. /\\\\\
  /\\\\\\ . /|\. . ./\\\\\\\\\ . / | \ . /\\\\\\\\\ . ./|\. ./\\\\\\\
 ^^^^^^^^^.   .\.  ^^^^^^^^^^^^.  .|.  .^^^^^^^^^^^^ ./.   .^^^^^^^^^^
 )()()()()  *  .\. ()()()()()()   .|.   ()()()()()()./.  *  ()()()()()
 )()()()()^^^^^^^^^()()()()()()^^^^^^^^^()()()()()()^^^^^^^^()()()()()
 )()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()
 ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 ()()()()()()()()()()()()()()|||||||||||||()()()()()()()()()()()()()()        
 ()()()__||__()()()()()()          |          ()()()()()()__||__()()()
 ()()()__||__()()()()()        \   |   /        ()()()()()__||__()()()
 ()()()__||__()()()()      `.   \  :  /  .'       ()()()()__||__()()()
 ()()()__||__()()()          `. __/ \__ .'          ()()()__||__()()()
 ()()()__||__()()()      _______\ _|_ /________     ()()()__||__()()()
 ()()()__||__()()()             /_ | _\             ()()()__||__()()()
 ()()()__||__()()()           .'  \ /  `.           ()()()__||__()()()
 ()()()__||__()()()         .'  /  :  \  `.         ()()()__||__()()()
 ()()()__||__()()()            /   |   \            ()()()__||__()()()
 ()()()__||__()()()             JUPITER             ()()()__||__()()()
 ()()()__||__()()()     |=====================|     ()()()__||__()()()
 ()()()__||__()()()     |    IS THE WINNER    |     ()()()__||__()()()
 ()()()__||__()()()     |=====================|     ()()()__||__()()()
 ()()()()()()()()()    ()()()()()()()()()()()()(    ()()()()()()()()()
 ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

*/