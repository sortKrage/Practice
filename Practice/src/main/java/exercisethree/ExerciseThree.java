/**
 * 
 */
package main.java.exercisethree;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * @author David
 * 
 *         Tf/idf (term frequency / inverse document frequency) is an statistic
 *         that reflects the importance of a term T in a document D (or the
 *         relevance of a document for a searched term) relative to a document
 *         set S.
 * 
 *         Tf/idf can be extended to a set of terms TT adding the tf/idf for
 *         each term. Assume that we have a directory D containing a document
 *         set S, with one file per document. Documents will be added to that
 *         directory by external agents, but they will never be removed or
 *         overwritten. We are given a set of terms TT, and asked to compute the
 *         tf/idf of TT for each document in D, and report the N top documents
 *         sorted by relevance. The program must run as a daemon/service that is
 *         watching for new documents, and dynamically updates the computed
 *         tf/idf for each document and the inferred ranking.
 * 
 *         The program will run with the parameters:
 * 
 *         * The directory D where the documents will be written.
 * 
 *         * The terms TT to be analyzed.
 * 
 *         * The count N of top results to show.
 * 
 *         * The period P to report the top N.
 *
 */
public class ExerciseThree {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Options options = new Options();
		options.addOption("d", true, "The directory who wants to be checked");
		options.addOption("n", true, "The number of top results to show");
		options.addOption("t", true, "The term to be analyzed");
		options.addOption("p", true, "The period to report the result");

		CommandLineParser parser = new DefaultParser();
		try {
			CommandLine cmd = parser.parse(options, args);

			if (cmd.hasOption("d") && cmd.hasOption("n") && cmd.hasOption("t") && cmd.hasOption("p")) {
				Path d = Paths.get(cmd.getOptionValue("d"));
				String term = cmd.getOptionValue("t");
				int n = Integer.valueOf(cmd.getOptionValue("n"));
				int p = Integer.valueOf(cmd.getOptionValue("p"));

				Service service = new Service(d, term, n, p);
				service.start();

			} else {
				System.out.println("Missing parameters...");

			}
		} catch (ParseException e) {
			System.out.println("Not valid parameters");
		}

	}

}
