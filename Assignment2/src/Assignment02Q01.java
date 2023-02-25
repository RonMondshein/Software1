
public class Assignment02Q01 {

	public static void main(String[] args) {
		// *** your code goes here below ***
		if (args.length > 0) {
		for(int i=0; i<args.length; i++)
		{
			int asciiValue = args[i].charAt(0);
			if(asciiValue % 5 == 0)
			{
				System.out.println(args[i].charAt(0));
			}	
		}
		}

	}

}
