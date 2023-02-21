package main.java.exerciseone;

/**
 * @author David
 * 
 *         Write an efficient algorithm to check if a string is a palindrome. A
 *         string is a palindrome if the string matches the reverse of string.
 *
 */
public class ExerciseOne {

	/**
	 * @param args Contains a list of strings that will be checked for palindromes.
	 */
	public static void main(String[] args) {
		if (args.length > 1) {
			for (String s : args) {
				boolean result = isPalindrome(s);
				System.out.println("The word " + s + " is a palindrome: " + result);

			}
		} else {
			System.out.println("No param in args, please introduce some.");

		}

	}

	/**
	 * This method check if the word given is palindrome.
	 * 
	 * In the worst case (palindrome except the central characters) loops once over
	 * the String, scaling linearly with the input therefore: O(n).
	 * 
	 * @param String s
	 * @return boolean
	 */
	public static boolean isPalindrome(String s) {
		int inc = 0; // first letter of the string
		int des = s.length() - 1; // last letter
		boolean out = false;

		// if there is no more letters or it detects is NOT a palindrome it goes out
		while ((inc < des) && (!out)) {
			if (s.toLowerCase().charAt(inc) != s.toLowerCase().charAt(des)) {
				out = true;

			} else {
				inc++;
				des--;

			}
		}

		return !out;
	}

}
