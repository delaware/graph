package at.reseau.test;

import org.junit.Before;
import org.junit.Test;

import at.reseau.graph.model.AdjacencyMatrix;
import at.reseau.graph.model.DistanceMatrix;
import at.reseau.graph.model.PathMatrix;

public class TestAdjacencyMatrix {

	private int size = 5;
	private AdjacencyMatrix adjacency;
	private DistanceMatrix distance;
	private PathMatrix weg;
	
	@Before
	public void setup() {
		adjacency = new AdjacencyMatrix(size);
		distance = new DistanceMatrix(size);
		weg = new PathMatrix(size);
		adjacency.addEdge(0, 1);
		adjacency.addEdge(0, 2);
		adjacency.addEdge(1, 2);
		adjacency.addEdge(1, 3);
		//adjacency.addEdge(2, 4);
		adjacency.addEdge(3, 4);
		distance.populate(adjacency);
		weg.populate(adjacency);
	}
	
	@Test
	public void test() {

	}
	
}
