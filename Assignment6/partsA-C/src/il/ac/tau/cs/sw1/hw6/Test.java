package il.ac.tau.cs.sw1.hw6;

public class Test {
	
public static void main(String[] args) {
		
		
		Polynomial p1 = new Polynomial(); // p1 = 0
		
		printError(p1.getDegree() == 0,
				"the degree of p1 is 0");
		
		printError(p1.computePolynomial(3) == 0.0,
				"the value of p1 with x=3 is 0.0");
		
		double[] coefficients = new double[]{1.0,2.0,3.0};
		Polynomial p2 = new Polynomial(coefficients); // p2 = 1.0+2.0*x+3.0*x^2
	    Polynomial p40 = p2.adds(p2);
		Polynomial p30 = p2.multiply(2.0);

	Polynomial p50 = p2.getFirstDerivation();

	/*
	 * p4 = 3.0+6.0*x+9.0*x^2
	 * p5 = 6.0 + 18*x
	 */
	double d = -1/3.0;
	//System.out.println(p2.isExtrema(d) + " is true");
	//System.out.println(p2.isExtrema(-d) +" is false");

	printError(p2.computePolynomial(3) == 34.0,
				"the value of p2 with x=3 is 34.0");
		
		printError(p2.getCoefficient(1) == 2.0,
				"the coefficient of x is 2.0");
		
		Polynomial p3 = p2.multiply(2.0);
		/*
		 * p2 = 1.0+2.0*x+3.0*x^2
		 * p3 = 2.0+4.0*x+6.0*x^2
		 */
		
		printError(p2.getCoefficient(1) == 2.0,
				"the coefficient of x is 2.0");
		
		printError(p3.getCoefficient(1) == 4.0,
				"the coefficient of x is 4.0");
		
		
		Polynomial p4 = p2.adds(p3);
		/*
		 * p2 = 1.0+2.0*x+3.0*x^2
		 * p3 = 2.0+4.0*x+6.0*x^2
		 * p4 = 3.0+6.0*x+9.0*x^2
		 */
		printError(p4.getDegree() == 2,
				"the degree of p4 is 2");
		
		Polynomial p5 = p4.getFirstDerivation();
		
		/*
		 * p4 = 3.0+6.0*x+9.0*x^2
		 * p5 = 6.0 + 18*x
		 */
	Polynomial p11 = new Polynomial(); // p1 = 0
	double[] coefficients1 = new double[]{-1.0,-5.0,0.0,0.0,0.0, 1.0};
	Polynomial p21 = new Polynomial(coefficients1); // p2 = 1.0+2.0*x+3.0*x^2
	double f = 1;
	//System.out.println(p21.isExtrema(f) + " is true3");
	//System.out.println(p21.isExtrema(-f) +" is false3");

	printError(p5.getDegree() == 1,
				"the degree of p5 is 1");




		
		
	}

	public static void printError(boolean condition, String message) {
		if (!condition) {
			throw new RuntimeException("[ERROR] " + message);
		}
	}
}
