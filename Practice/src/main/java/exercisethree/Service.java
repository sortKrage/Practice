/**
 * 
 */
package main.java.exercisethree;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @author David
 *
 */
public class Service extends Thread {

	final Path path;
	List<List<String>> directoryData;
	final String term;
	List<Document> documents;
	int n;
	int p;

	public Service(Path d, String term, int n, int p) {
		this.path = d;
		this.directoryData = new ArrayList<>();
		this.term = term;
		this.documents = new ArrayList<>();
		this.n = n;
		this.p = p;
		mappingDirectory(d);

	}

	public void run() {
		try {
			WatchService service = FileSystems.getDefault().newWatchService();
			WatchKey key = path.register(service, ENTRY_CREATE);

			while (true) {
				key = service.take(); // waits until new file is added

				for (WatchEvent<?> event : key.pollEvents()) {
					WatchEvent<Path> ev = (WatchEvent<Path>) event; // The filename is in the context of the event.
					Path p = this.path.resolve(ev.context()); // Resolves the entire path taking the filename in context

					List<String> data = mappingData(p);

					if (!data.isEmpty()) {
						Document doc = new Document(p.toFile().getName(), calculateTfIdf(data, directoryData, term));
						documents.add(doc); // We create an object containing the name and the TF-IDF value and we added
											// to a list.
						printResult();

					}

				}

				boolean valid = key.reset();
				if (!valid)
					break;

			}

		} catch (IOException | InterruptedException e) {
		}

	}

	/**
	 * Initialize the List<List<String>> directotyData containing the words for each
	 * file in the directory given by path.
	 * 
	 * @param Path p
	 */
	private void mappingDirectory(Path p) {
		File f = p.toFile();
		if (!f.exists()) {
			System.out.println(f.getName() + " not exists!");
			System.exit(1);

		} else if (f.exists() && !f.isDirectory()) {
			System.out.println(f.getName() + " is not a directory!");
			System.exit(1);

		} else if (f.exists() && f.isDirectory()) {
			File[] list = p.toFile().listFiles();
			for (File file : list) {
				mappingData(file.toPath());
			}
			for (int i = 0; i < list.length; i++) {
				File dataFile = list[i];
				Document doc = new Document(dataFile.getName(),
						calculateTfIdf(directoryData.get(i), directoryData, term));
				documents.add(doc);
			}

			printResult();
		}

	}

	/**
	 * Return a list of each word in the file given by path.
	 * 
	 * @param Path p
	 * @return List<String> data
	 */
	private List<String> mappingData(Path p) {
		List<String> data = new ArrayList<>();
		File f = p.toFile();
		try {
			if (f.isFile()) {
				Scanner s = new Scanner(f);
				while (s.hasNext()) {
					String string = (String) s.next();
					data.add(string);

				}
				s.close();
			}
		} catch (FileNotFoundException e) {
		}
		if (!data.isEmpty())
			directoryData.add(data);

		return data;

	}

	/**
	 * @param doc  list of strings
	 * @param term String represents a term
	 * @return term frequency of term in document
	 */
	public static double tf(List<String> doc, String term) {
		double result = 0;
		for (String word : doc) {
			if (term.equalsIgnoreCase(word))
				result++;
		}
		return result / doc.size();
	}

	/**
	 * @param docs list of list of strings represents the dataset
	 * @param term String represents a term
	 * @return the inverse term frequency of term in documents
	 */
	public static double idf(List<List<String>> docs, String term) {
		double n = 0;
		for (List<String> doc : docs) {
			for (String word : doc) {
				if (term.equalsIgnoreCase(word)) {
					n++;
					break;
				}
			}
		}
		return Math.log(docs.size() / n);
	}

	/**
	 * Calculates the TF-IDF index
	 * 
	 * This algorithm will scale according to the amount of files and the amount of
	 * words in the file. The worst case scenario the an full iteration in both list
	 * will be performed therefore: O(n3).
	 * 
	 * @param doc  a text document
	 * @param docs all documents
	 * @param term term
	 * @return the TF-IDF of term
	 */
	public static double calculateTfIdf(List<String> doc, List<List<String>> docs, String term) {

		Double tfIdf = tf(doc, term) * idf(docs, term);
		if (Double.isNaN(tfIdf))
			return 0;

		return tfIdf;
	}

	/**
	 * This method will print the top N documents sorted by the TF-IDF value
	 */
	private void printResult() {
		Collections.sort(documents, Collections.reverseOrder());

		for (int i = 0; (i < n) && (i < documents.size()); i++) {
			System.out.println(documents.get(i).toString());

		}
	}
}
