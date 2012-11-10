package at.reseau.graph.model;

import java.util.ArrayList;

public class AdjacencyMatrix extends Matrix {

	private static final long serialVersionUID = 8476638063623175350L;

	public AdjacencyMatrix(int size) {
		super(size);
	}

	public AdjacencyMatrix(Matrix m) {
		super(m.getSize());
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				if(m.getValueAt(i, j) == 1) setValueAt(i, j, m.getValueAt(i, j));
			}
		}
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
