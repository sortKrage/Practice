/**
 * 
 */
package main.test.exerciseone;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.java.exerciseone.ExerciseOne;

/**
 * @author David
 *
 */
public class ExerciseOneTest {

	@Test
	public void isPalindrome_Test() {
		String s = "Ana";
		boolean b = ExerciseOne.isPalindrome(s);

		assertTrue(b);
	}

	@Test
	public void isNotPalindrome_Test() {
		String s = "Palindrome";
		boolean b = ExerciseOne.isPalindrome(s);

		assertFalse(b);
	}

}
