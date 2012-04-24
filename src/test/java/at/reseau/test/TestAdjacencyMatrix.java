package at.reseau.test;

import org.junit.*;
import at.reseau.graph.model.*;
import at.reseau.graph.util.UtilMatrix;

public class TestAdjacencyMatrix {

	private int size = 10;
	private AdjacencyMatrix adjacency;
	private DistanceMatrix distance;
	private WegeMatrix weg;
	
	@Before
	public void setup() {
		adjacency = new AdjacencyMatrix(size);
		distance = new DistanceMatrix(size);
		weg = new WegeMatrix(size);
		adjacency.addEdge(0, 1);
		adjacency.addEdge(1, 2);
		adjacency.addEdge(2, 3);
		adjacency.addEdge(4, 5);
		adjacency.addEdge(6, 7);
		distance.populate(adjacency);
		weg.populate(adjacency);
	}
	
	@Test
	public void test() {
		System.out.println();
		System.out.println("--------- adjacency --------");
		adjacency.print();
		
		System.out.println();
		System.out.println("--------- distance --------");
		distance.print();
		
		System.out.println();
		System.out.println("--------- weg --------");
		weg.print();
		
		UtilMatrix util = new UtilMatrix();
		
		weg.populate(util.power(weg, size));
		System.out.println();
		System.out.println("--------- weg --------");
		weg.print();
		
	}
	
}
