package at.reseau.test;

import java.util.ArrayList;

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
		System.out.println();
		System.out.println("--------- adjacency --------");
		adjacency.print();
		
		System.out.println();
		System.out.println("--------- distance --------");
		distance.print();
		
		System.out.println();
		System.out.println("--------- weg --------");
		weg.print();	
		
		System.out.println();
		System.out.println("--------- Exzentritäten --------");
		ArrayList<Integer> x = distance.getEccentricity();
		for(int i : x) {
			System.out.println(i);
		}
		
		System.out.println();
		System.out.println("--------- Radius --------");
		System.out.println(distance.getRadius());
		
		System.out.println();
		System.out.println("--------- Durchmesser --------");
		System.out.println(distance.getDiameter());
		
		System.out.println();
		System.out.println("--------- Komponenten --------");
		System.out.println(weg.getComponents());
		
		System.out.println();
		System.out.println("--------- Zentrum --------");
		ArrayList<Integer> c = distance.getCenter();
		for(int i : c) {
			System.out.println(i);
		}
	}
	
}
