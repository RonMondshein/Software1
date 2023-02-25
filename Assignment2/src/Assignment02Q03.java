
public class Assignment02Q03 {

	public static void main(String[] args) {
		int numOfOdd = 0;
		int n = -1;
		// *** your code goes here below ***
		
		if (args.length > 0){
			n = Integer.parseInt(args[0]);
		}
		
		
		System.out.println("The first "+ n +" Fibonacci numbers are:");
		// *** your code goes here below ***
		
		int current = 2;
		int prev = 1;
		int prevprev = 1;
		numOfOdd = 2;
		System.out.print(prevprev + " " + prev + " " + current);
		
		if (n > 3) {
			for (int i = 3; i < n; i++) {
				prevprev = prev;
				prev = current;
				current = prevprev + prev;
				if (current % 2 != 0) {
					numOfOdd++;
				}
				System.out.print(" " + current);
				
			}
		}
		
		System.out.println("");
		System.out.println("The number of odd numbers is: "+numOfOdd);

	}

}
