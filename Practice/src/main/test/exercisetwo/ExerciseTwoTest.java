package main.test.exercisetwo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import main.java.exercisetwo.ExerciseTwo;

/**
 * @author David
 *
 */
public class ExerciseTwoTest {

	@Test
	public void findKComplementary_Test() {
		int[] A = { 1, 1 };
		int K = 2;
		int i = ExerciseTwo.findKComenplentary(A, K);

		assertEquals(1, i);
	}

	@Test
	public void notFindKComplementary_Test() {
		int[] A = { 1, 1 };
		int K = 7;
		int i = ExerciseTwo.findKComenplentary(A, K);

		assertEquals(0, i);
	}

}
