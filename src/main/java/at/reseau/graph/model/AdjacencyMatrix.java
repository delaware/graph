package at.reseau.graph.model;

import java.util.ArrayList;

public class AdjacencyMatrix extends Matrix {

	public AdjacencyMatrix(int size) {
		super(size);
	}

	public void addEdge(int nodeA, int nodeB) {
		setValueAt(nodeA, nodeB, 1);
		setValueAt(nodeB, nodeA, 1);
	}
	
	public void removeEdge(int nodeA, int nodeB) {
		setValueAt(nodeA, nodeB, 0);
		setValueAt(nodeB, nodeA, 0);
	}
	
	public int getDegree(int node) {
		int result = 0;
		for(int i=0;i<size;i++) {
			if(values[node][i] == 1 ) result++;
		}
		return result;
	}
	
	public ArrayList<Integer> getDegreeList() {
		ArrayList<Integer> degrees = new ArrayList<Integer>();
		
		for(int i=0;i<size;i++) {
			degrees.add(getDegree(i));
		}		
		return degrees;
	}
}
