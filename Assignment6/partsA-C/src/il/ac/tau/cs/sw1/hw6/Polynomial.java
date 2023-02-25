package il.ac.tau.cs.sw1.hw6;

import java.util.ArrayList;
import java.util.Arrays;

public class Polynomial {
	
	/*
	 * Creates the zero-polynomial with p(x) = 0 for all x.
	 */
	int degree;
	double[] polynom;
	public Polynomial()
	{
		this.degree = 0;
		this.polynom = new double[degree + 1];
		this.polynom[0] = 0;
	} 
	/*
	 * Creates a new polynomial with the given coefficients.
	 */
	public Polynomial(double[] coefficients) 
	{
		this.degree = coefficients.length - 1;
		this.polynom = Arrays.copyOf(coefficients, coefficients.length);

	}

	public void getPolynomialright(){
		int newSize = this.polynom.length;
		for (int i  = this.degree; i >= 0; i --){
			if(this.polynom[i] == 0){
				newSize --;
			} else{
				break;
			}
		}
		if(newSize != this.polynom.length){
			if(newSize == 0){
				this.polynom = new double[]{0.0};
				this.degree = 0;
			}else {
				this.polynom = Arrays.copyOf(this.polynom, newSize);
				this.degree = newSize - 1;
			}
		}
	}
	/*
	 * Addes this polynomial to the given one
	 *  and retruns the sum as a new polynomial.
	 */
	public Polynomial adds(Polynomial polynomial)
	{
		int maxLen = Integer.max(this.degree, polynomial.degree) + 1;
		double[] newCoefficients = new double[maxLen];
		for (int i = 0; i < maxLen; i++)
		{
			if(i <= this.degree){
				newCoefficients[i] += this.polynom[i];
			} if(i <= polynomial.degree) {
				newCoefficients[i] += polynomial.polynom[i];
		}
		}
		Polynomial addedpoly = new Polynomial(newCoefficients);
		addedpoly.getPolynomialright();
		return addedpoly;
		
	}
	/*
	 * Multiplies a to this polynomial and returns 
	 * the result as a new polynomial.
	 */
	public Polynomial multiply(double a)
	{
		double[] newCoefficients = new double[this.degree + 1];
		for(int i = 0; i < this.polynom.length; i++){
			newCoefficients[i] = this.polynom[i] * a;
		}
		Polynomial multipoly = new Polynomial(newCoefficients);
		multipoly.getPolynomialright();
		return  multipoly;
	}
	/*
	 * Returns the degree (the largest exponent) of this polynomial.
	 */
	public int getDegree()
	{
		return this.degree;
	}
	/*
	 * Returns the coefficient of the variable x 
	 * with degree n in this polynomial.
	 */
	public double getCoefficient(int n)
	{
		if(n > this.degree || n < 0){
			return 0.0;
		}
		return this.polynom[n];
	}
	
	/*
	 * set the coefficient of the variable x 
	 * with degree n to c in this polynomial.
	 * If the degree of this polynomial < n, it means that that the coefficient of the variable x 
	 * with degree n was 0, and now it will change to c. 
	 */
	public void setCoefficient(int n, double c)
	{
		if (c ==0 ){
			if ( n == this.degree){
				if (n== 0){
					double[] newp = new double[1];
					newp[0] = 0.0;
					this.polynom = Arrays.copyOf(newp, 1);
					this.degree = 0;
				}
				else{
					int newCoe = this.degree - 1;
					while (this.polynom[newCoe] == 0){
						if(newCoe == 0){
							break;
						}
						newCoe--;

					}
					this.polynom = Arrays.copyOf(this.polynom, newCoe + 1);
					this.degree = newCoe;
				}
			}

		}

		else {
			if (n <= this.degree) {
				this.polynom[n] = c;
			} else {
				double[] newCoefficients = new double[n + 1];
				for (int i = 0; i <= n; i++) {
					if (i <= this.degree) {
						newCoefficients[i] = this.polynom[i];
					} else if (i == n) {
						newCoefficients[i] = c;
					} else {
						newCoefficients[i] = 0;
					}
				}
				this.polynom = Arrays.copyOf(newCoefficients, newCoefficients.length);
				this.degree = n;
			}
		}
	}
	
	/*
	 * Returns the first derivation of this polynomial.
	 *  The first derivation of a polynomal a0x0 + ...  + anxn is defined as 1 * a1x0 + ... + n anxn-1.
	
	 */
	public Polynomial getFirstDerivation()
	{
		if(this.degree == 0){
			double[] newCoefficients = new double[1];
			newCoefficients[0] =0.0;
			Polynomial deripolynomial = new Polynomial(newCoefficients);
			return deripolynomial;
		}
		double[] newCoefficients = new double[this.degree];
		for(int i = 0; i < this.degree; i++){
			newCoefficients[i] = (i + 1) * this.polynom[i + 1];
		}
		Polynomial deripolynomial = new Polynomial(newCoefficients);
		return deripolynomial;
	}
	
	/*
	 * given an assignment for the variable x,
	 * compute the polynomial value
	 */
	public double computePolynomial(double x)
	{

		double compute = 0.0;
		for(int i = 0; i <= this.degree; i++) {
			compute += this.polynom[i] * Math.pow(x, i);
		}
			return compute;
	}
	
	/*
	 * given an assignment for the variable x,
	 * return true iff x is an extrema point (local minimum or local maximum of this polynomial)
	 * x is an extrema point if and only if The value of first derivation of a polynomal at x is 0
	 * and the second derivation of a polynomal value at x is not 0.
	 */
	public boolean isExtrema(double x)
	{
		Polynomial firstDerivation = this.getFirstDerivation();
		Polynomial secondtDerivation = firstDerivation.getFirstDerivation();
		if(firstDerivation.computePolynomial(x) == 0){
			if(secondtDerivation.computePolynomial(x) != 0){
				return true;
			}
		}

		return false;
	}
	
	
	
	

    
    

}
