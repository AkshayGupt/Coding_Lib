package bigInteger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class bigIntegerUsingArray {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// Enter number for factorial
		int number = Integer.parseInt(br.readLine().trim());

		// Store answer in array
		int result[] = new int[10000];
		Arrays.fill(result, 0);
		result[0] = 1;

		// Calculate
		int numOfDigits = 1;
		for (int i = 1; i <= number; i++) {
			numOfDigits = multiply(result, i, numOfDigits);
		}

		// Print result
		StringBuilder ans = new StringBuilder("");
		for (int i = 0; i < numOfDigits; i++) {
			ans = ans.append(result[i]);
		}
		ans.reverse();
		System.out.println(ans);
	}

	public static int multiply(int result[], int num, int numOfDigits) {

		int carryOver = 0;
		for (int i = 0; i < numOfDigits; i++) {
			int mult = result[i] * num;
			mult += carryOver;
			result[i] = mult % 10;
			carryOver = mult /= 10;
		}

		while (carryOver > 0) {
			result[numOfDigits++] = carryOver % 10;
			carryOver /= 10;
		}

		return numOfDigits;
	}
}
