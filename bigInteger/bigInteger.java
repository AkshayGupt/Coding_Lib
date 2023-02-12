package bigInteger;

import java.math.BigInteger;
import java.util.Scanner;

public class bigInteger {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);

		BigInteger bg1 = new BigInteger("123");
		BigInteger bg2 = new BigInteger("345");

		// Basic calc
		bg1 = bg1.add(bg2);
		bg1 = bg2.multiply(bg2);

		// Count bits
		int setBits = bg1.bitCount();
		int totalBits = bg1.bitLength();

		// GCD
		BigInteger gcd = bg1.gcd(bg2);

		// Base Conversion
		BigInteger b5 = new BigInteger("10011", 2);

		// Power
		BigInteger power = bg1.pow(200);

	}
}
