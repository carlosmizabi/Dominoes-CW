package dominoes;

/**
 * SHORT DIALECT CLASS
 * It translates directly
 * to Java Library methods.
 * 
 * @author carlosmarques
 *
 */
public class S {
	
	private S(){ super(); }
	
	/** System.out.print();
	 *  
	 * @param t
	 */
	public static <T> void print(Object t){	System.out.print("" + t); }
	
	/** System.out.println();
	 * 
	 * @param t
	 */
	public static <T> void printl(Object t){	System.out.println("" + t); }

}
