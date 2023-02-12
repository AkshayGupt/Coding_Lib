package Combinatorics;

import java.util.Scanner;

public class birthdayParadox {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);

		// Probablity
		double probablityForSameBirthday = sc.nextDouble();
		double probablityForDifferentBirthday = 1;

		if (probablityForSameBirthday == 1) {
			System.out.println(366);
			return;
		}

		int people = 0;
		while ((1 - probablityForDifferentBirthday) < probablityForSameBirthday) {
			people++;
			probablityForDifferentBirthday *= (365 - people + 1) / (365d);
		}

		System.out.println(people);

	}
}