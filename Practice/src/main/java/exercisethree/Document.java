/**
 * 
 */
package main.java.exercisethree;

/**
 * @author David
 *
 */
public class Document implements Comparable<Document> {
	String docName;
	Double tfIdf;

	public Document(String docName, Double tfIdf) {
		this.docName = docName;
		this.tfIdf = tfIdf;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public Double getTfIdf() {
		return tfIdf;
	}

	public void setTfIdf(Double tfIdf) {
		this.tfIdf = tfIdf;
	}

	@Override
	public String toString() {
		return String.format("Name: %-20s tfIdf: %.2g%n", docName, tfIdf);
	}

	@Override
	public int compareTo(Document o) {
		if (this.getTfIdf() > o.getTfIdf()) {
			return 1;
		} else if (this.getTfIdf() < o.getTfIdf()) {
			return -1;
		} else {
			return 0;
		}
	}

}
