package homeworks.hw1;
/**
 *
 * @author william orem
 */

public class ComplexNumber {
	public static final ComplexNumber NaN = new ComplexNumber(Double.NaN, Double.NaN);
	/**
	 * This field holds the real part of a complex number
	 */
	private double real;
	/**
	 * This field holds the imaginary part of a complex number
	 */
	private double imag;
	/**
	 * Constructs a complex number with both parts set to 0
	 */
	public ComplexNumber(){
		real = 0;
		imag = 0;
	}
	/**
	 * Constructs a complex number from the given real and imaginary
	 * @param real the real part of the complex number
	 * @param imag the imaginary part of the complex number
	 */
	public ComplexNumber(double real, double imag){
		this.real = real;
		this.imag = imag;
	}
	/**
	 * Constructs a complex number based on the passed complex number's real and imaginary
	 * @param other the original complex number to be copied
	 */
	public ComplexNumber(ComplexNumber other){
		this(other.real, other.imag);
	}
	/**
	 * @return the real
	 */
	public double getReal() {
		return real;
	}
	/**
	 * @param real the real to set
	 */
	public void setReal(double real) {
		this.real = real;
	}
	/**
	 * @return the imag
	 */
	public double getImag() {
		return imag;
	}
	/**
	 * @param imag the imag to set
	 */
	public void setImag(double imag) {
		this.imag = imag;
	}

        
	/**
	 * Compares if two ComplexNumbers are have the same values
	 * @param other the other complex number to compare to
	 * @return returns true if the two numbers have the same value
	 */
	public boolean equals(Object other){
		if(other instanceof ComplexNumber){
			ComplexNumber cother = (ComplexNumber)other;
			return this.real == cother.real
					&& this.imag == cother.imag;
		}
		return false;
	}
	/**
	 * Returns a string representation of the complex number
	 */
	public String toString(){
		String text = "";
		if(real == 0 && imag == 0){
			text = "0";
		}
		else if(real == 0){
			
		}
		else{
			text += real;
			if(imag != 0){
				text += imag > 0 ? " + i" + imag : " - i" + Math.abs(imag); 
			}
		}
		return text;
	}
	/**
	 * Method for finding the conjugate of a complex number
	 * @return the conjugate of the complex number 
	 */
	public ComplexNumber conjugate(){
		return new ComplexNumber(real, -1 * imag);
	}
	/**
	 * Method for obtaining the magnitude of of a complex number
	 * @return the magnitude of the complex number defined as sqrt(real^2 + imag^2)
	 */
	public double magnitude(){
		return Math.hypot(real, imag);
	}
	/**
	 * Method for obtaining the squared magnitude of of a complex number
	 * @return the magnitude of the complex number defined as real^2 + imag^2
	 */
	public double magnitude2(){
		double mag = magnitude(); 
		return mag * mag;
	}
	/**
	 * Returns the argument of the complex number
	 * @return the argument of this
	 */
	public double arg(){
		return Math.atan2(imag, real);
	}
	/**
	 * Multiplies a complex number by a scalar number
	 * @param scalar the number to multiply the complex number
	 * @return this * scalar
	 */
	public ComplexNumber times(double scalar){
		return new ComplexNumber(scalar * real, scalar * imag);
	}
	/**
	 * Returns the complex number raised to a certain power
	 * @param power the exponent to raise the complex number
	 * @return return the complex number raised to the exponent this ^ power
	 */
	public ComplexNumber pow(double power){
		double mag = magnitude();
		double arg = arg();
		double real = Math.cos(power * arg);
		double imag = Math.sin(power * arg);
		double raisedMag = Math.pow(mag, power);
		ComplexNumber raisedNumber = new ComplexNumber(real, imag);
		return raisedNumber.times(raisedMag);
	}
	/**
	 * Method for adding two complex numbers 
	 * @param c1 the first complex number to add
	 * @param c2 the second complex number to add
	 * @return returns the addition of c1 + c2
	 */
	public static ComplexNumber add(ComplexNumber c1, ComplexNumber c2){
		double real = c1.real + c2.real;
		double imag = c1.imag + c2.imag;
		return new ComplexNumber(real, imag);
	}
	/**
	 * Method for adding two complex numbers based on their individual parts
	 * @param real1 the real part of the first complex number to add
	 * @param imag1 the imaginary part of the first complex number to add
	 * @param real2 the real part of the second complex number to add
	 * @param imag2 the imaginary part of the second complex number to add
	 * @return returns the addition of (real1 + iimag1) + (real2 + iimag2)
	 */
	public static ComplexNumber add(double real1, double imag1, double real2, double imag2){
		ComplexNumber c1 = new ComplexNumber(real1, imag1);
		ComplexNumber c2 = new ComplexNumber(real2, imag2);
		return ComplexNumber.add(c1, c2);
	}
	/**
	 * Method for subtracting two complex numbers 
	 * @param c1 the first complex number to subtract
	 * @param c2 the second complex number to subtract
	 * @return returns the subtraction of c1 - c2
	 */
	public static ComplexNumber subtract(ComplexNumber c1, ComplexNumber c2){
		double real = c1.real - c2.real;
		double imag = c1.imag - c2.imag;
		return new ComplexNumber(real, imag);
	}
	/**
	 * Method for subtracting two complex numbers based on their individual parts
	 * @param real1 the real part of the first complex number to subtract
	 * @param imag1 the imaginary part of the first complex number to subtract
	 * @param real2 the real part of the second complex number to subtract
	 * @param imag2 the imaginary part of the second complex number to subtract
	 * @return returns the subtraction of (real1 + iimag1) - (real2 + iimag2)
	 */
	public static ComplexNumber subtract(double real1, double imag1, double real2, double imag2){
		ComplexNumber c1 = new ComplexNumber(real1, imag1);
		ComplexNumber c2 = new ComplexNumber(real2, imag2);
		return ComplexNumber.subtract(c1, c2);
	}
	/**
	 * Method for multiplying two complex numbers 
	 * @param c1 the first complex number to multiply
	 * @param c2 the second complex number to multiply
	 * @return returns the product of c1 * c2
	 */
	public static ComplexNumber multiply(ComplexNumber c1, ComplexNumber c2){
		double real = (c1.real * c2.real) - (c1.imag * c2.imag);
		double imag = (c1.real * c2.imag) + (c1.imag * c2.real);
		return new ComplexNumber(real, imag);
	}
	/**
	 * Method for multiplying two complex numbers based on their individual parts
	 * @param real1 the real part of the first complex number to multiply
	 * @param imag1 the imaginary part of the first complex number to multiply
	 * @param real2 the real part of the second complex number to multiply
	 * @param imag2 the imaginary part of the second complex number to multiply
	 * @return returns the product of (real1 + iimag1) * (real2 + iimag2)
	 */
	public static ComplexNumber multiply(double real1, double imag1, double real2, double imag2){
		ComplexNumber c1 = new ComplexNumber(real1, imag1);
		ComplexNumber c2 = new ComplexNumber(real2, imag2);
		return ComplexNumber.multiply(c1, c2);
	}
	/**
	 * Method for dividing two complex numbers 
	 * @param c1 the first complex number to divide
	 * @param c2 the second complex number to divide
	 * @return returns the product of c1 / c2
	 */
	public static ComplexNumber divide(ComplexNumber c1, ComplexNumber c2) {
		ComplexNumber numerator = multiply(c1, c2.conjugate());
		double denominator = c2.magnitude2();
		if(denominator == 0){
			return ComplexNumber.NaN;
		}
		return numerator.times(1/denominator);
	}
	/**
	 * Method for dividing two complex numbers based on their individual parts
	 * @param real1 the real part of the first complex number to divide 
	 * @param imag1 the imaginary part of the first complex number to divide
	 * @param real2 the real part of the second complex number to divide
	 * @param imag2 the imaginary part of the second complex number to divide
	 * @return returns the division of (real1 + iimag1) / (real2 + iimag2)
	 */
	public static ComplexNumber divide(double real1, double imag1, double real2, double imag2) {
		ComplexNumber c1 = new ComplexNumber(real1, imag1);
		ComplexNumber c2 = new ComplexNumber(real2, imag2);
		return ComplexNumber.divide(c1, c2);
	}
}
