

public class Assignment02Q02 {

	public static void main(String[] args) {
		// do not change this part below
		double piEstimation = 0.0;
		
		// *** your code goes here below ***
		int lengthPi = 0;
		if (args.length > 0)
		{
			lengthPi = Integer.parseInt(args[0]);
		}
		double cal = 0;
		double sign = 1;
		double div = 1;
		for(int i = 0; i < lengthPi; i++) {
			cal = cal + sign*(1 / div);
			sign = -1*sign;
			div = div + 2;
		}
		piEstimation = 4 * cal;
		
		// do not change this part below
		System.out.println(piEstimation + " " + Math.PI);

	}

}
