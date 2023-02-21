/**
 * 
 */
package main.test.exercisethree;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import main.java.exercisethree.Service;

/**
 * @author David
 *
 */
public class ServiceTest {

	private static final double DELTA = 1e-2;

	@Test
	public void calculateTfIdf_Test() {

		List<String> doc1 = Arrays.asList("Lorem", "ipsum", "dolor", "ipsum", "sit", "ipsum");
		List<String> doc2 = Arrays.asList("Vituperata", "incorrupte", "at", "ipsum", "pro", "quo");
		List<String> doc3 = Arrays.asList("Has", "persius", "disputationi", "id", "simul");
		List<List<String>> documents = Arrays.asList(doc1, doc2, doc3);

		double tfIdf = Service.calculateTfIdf(doc1, documents, "ipsum");

		assertEquals(0.2, tfIdf, DELTA);
	}

	@Test
	public void calculateTfIdf_NaN_Test() {

		List<String> doc1 = Arrays.asList("Lorem", "ipsum", "dolor", "ipsum", "sit", "ipsum");
		List<String> doc2 = Arrays.asList("Vituperata", "incorrupte", "at", "ipsum", "pro", "quo");
		List<String> doc3 = Arrays.asList("Has", "persius", "disputationi", "id", "simul");
		List<List<String>> documents = Arrays.asList(doc1, doc2, doc3);

		double tfIdf = Service.calculateTfIdf(doc1, documents, "test");

		assertEquals(0, Double.compare(0, tfIdf));
	}
}
