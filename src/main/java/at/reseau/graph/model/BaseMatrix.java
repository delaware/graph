package at.reseau.graph.model;

public interface BaseMatrix {
	// adjacency, distance and weg are initialized in a different way
	void init();
	public void populate(Matrix m);
}
