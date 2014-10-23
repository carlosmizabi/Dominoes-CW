package dominoes;

import java.util.ArrayList;
import java.util.LinkedList;

public class StringTools {
	public static StringBuffer stringBuffer;

	private StringTools() { super(); }
	
	/**
	 * Add Strings to one long string
	 * @param strings
	 * @return
	 */
	public static String addStrings(LinkedList<String> strings)
	{
		StringBuffer sb = getStringBuffer();
		for(String s : strings){ sb.append(s); } return sb.toString();
	}
	
	/**
	 * It will initialize a StringBuffer, if not already done.
	 * If already initialized then it will clear it for new use. 
	 * @return StringBuffer
	 */
	private static StringBuffer getStringBuffer()
	{
		if(stringBuffer == null){ stringBuffer = new StringBuffer(); } // initialize it
		else{ stringBuffer.setLength(0); } // clear it
		return stringBuffer;
	}
	
	/**
	 *  Reverses a string
	 * @param string
	 * @return reversed string
	 */
	public static String reverseString(String string)
	{
		StringBuffer sb = getStringBuffer();
		for(int i = string.length(); i > 0; i--){ sb.append(string.charAt(i-1)); }
		return sb.toString();
	}
	
	/**
	 *   Takes a string and chops it into lines of a given length
	 *   
	 * @param text : String
	 * @param lineLength : int
	 * @return lineList : LinkedList<String>
	 */
	public static LinkedList<String> cutToLines(String text, int lineLength)
	{
		int lineChewer = lineLength;		// counts the lines (line+Line+...)
		int charCount = 1;					// charCount
		int textSize = text.length();
		String wrapped = "";
		LinkedList<String> lineList = new LinkedList<String>();

		if(textSize < lineLength){ 
			lineList.add(centerStringInSpace(text, lineLength)); 
			return lineList; 
		}
		
		while(charCount < textSize)
		{   
			if(lineChewer < textSize)
			{ 
				wrapped = text.substring((lineChewer-lineLength), lineChewer);
				lineChewer += lineLength;
			}else{
				wrapped = text.substring((lineChewer-lineLength), textSize);
				if(wrapped.length() != lineLength)
				{ 
					wrapped += makeEmptySpace(lineLength-wrapped.length());
				}
			}
		
			lineList.add(wrapped);
			charCount += wrapped.length();
		}
		return lineList;
	}
	
	/**
	 *  It will add new lines "\n" at the passed line length
	 *  
	 * @param text
	 * @param lineLength
	 * @return
	 */
	public static String lineWrapText(String text, int lineLength)
	{
		if(text.length() < lineLength){ return text; }
		
		int lineChewer = lineLength;
		StringBuffer wrapped = getStringBuffer();
		
		while(lineChewer < text.length())
		{
			wrapped.append(text.substring((lineChewer-lineLength), lineChewer) + "\n");			
			lineChewer += lineLength;
		}
		return wrapped.append("\n").toString();
	}
	
	/**
	 * 	It centers the string according to the space
	 *  it is passed. And cuts off excess.
	 * 
	 * @param string to be centered
	 * @param space  space available
	 * @return centered string
	 */
	public static String centerStringInSpace(String string, int space)
	{
		int padding = ( space / 2 ) - (string.length() / 2); 
		String pad = makeEmptySpace(padding);
		String sb = pad + string + pad;
		return sb.substring(0,space-1);
	}
	
	/**
	 *  Generates a string of empty's of the size passed
	 * @param size
	 * @return
	 * 
	 */
	public static String makeEmptySpace(int size)
	{
		StringBuilder empty = new StringBuilder();
		for(int i = 0; i < size; i++){ empty.append(" "); }
		return empty.toString();		
	}
}