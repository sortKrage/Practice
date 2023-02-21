package main.java.exercisetwo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author David
 * 
 *         Write an efficient algorithm to find K-complementary pairs in a given
 *         array of integers. Given Array A, pair (i, j) is K-complementary if K
 *         = A[i] + A[j];
 *
 */
public class ExerciseTwo {

	public static void main(String[] args) {

		try {
			File f = new File("src/main/java/exercisetwo/data.txt"); // Contains an example of 20k numbers
			Scanner s = new Scanner(f);

			int K = 831444; // K-complementary number
			int[] A = mappingData(s);

			System.out.printf("\n %d pairs of %d-Complementaries where found!", findKComenplentary(A, K), K);

		} catch (FileNotFoundException e) {
			System.out.println("File not found");

		}
	}

	/**
	 * This method return the number of K-complementary pairs found.
	 * 
	 * In worst case scenario (the absolute value of all integers is less than K),
	 * complexity follows O(n log n) as one full iteration of the array is performed
	 * plus iteratively reduced passes with possible early exit.
	 * 
	 * Sorted and unsorted version were tested with minimal time difference in favor
	 * of unsorted version. However the sorted version is ahead whenever a
	 * significant portion of the values are greater than K.
	 * 
	 * @param A Array of number to be check
	 * @param K The complementary number
	 * @return int The total number of K-complementary found.
	 */
	public static int findKComenplentary(int[] A, int K) {
		int count = 0;

		if (A.length > 0) {
			int i = 0;
			Arrays.sort(A);

			while ((i < A.length - 1) && (A[i] < K)) {
				int j = i + 1;

				while (j < A.length) {
					if (K == A[i] + A[j]) {
						count++;

					}
					j++;

				}
				i++;

			}

		}

		return count;
	}

	/**
	 * This method map all the numbers in array.
	 * 
	 * @param Scanner s
	 * @return int[] A An array with all numbers.
	 */
	private static int[] mappingData(Scanner s) {
		int A[] = new int[20000];
		int i = 0;

		while (s.hasNextInt()) {
			A[i++] = s.nextInt();

		}
		s.close();

		return A;
	}

}
