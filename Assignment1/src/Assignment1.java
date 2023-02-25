

public class Assignment1 {
	public static void main(String[] args){
		// uncomment the following line to print your output:
		//System.out.println("New char is " + newChar + ".");
		char oldChar = args[0].charAt(0);
		int oldCharAscii = oldChar;
		char newChar = (char)(oldCharAscii + Integer.parseInt(args[1]));
		System.out.println("New char is " + newChar + ".");


	}
}