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
				if(i != j) {
					if(m.getValueAt(i, j) == 1) setValueAt(i, j, m.getValueAt(i, j));
				}
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
	
	public int getNumberOfEdges() {
		int num = 0;

		for (int row = 0; row < size; row++)  {
			for (int column = 0; column < size; column++)  {
				if (values[row][column] == 1) num++;
			}
		}
		return num / 2;
	}
	
	public boolean isTree() {
		boolean coherently = false; // coherently = zusammenhängend
		PathMatrix m = new PathMatrix(this);
		int components = m.getComponents();
		
		if(components == 1) coherently = true;
		
		if(coherently && (size - getNumberOfEdges()) == 1) {
			return true;
		}
		return false;
	}

	public boolean isForrest() {
		boolean coherently = false; // coherently = zusammenhängend
		PathMatrix m = new PathMatrix(this);
		int components = m.getComponents();
		
		if(m.getComponents() == 1) coherently = true;
		
		if(!coherently && getNumberOfEdges() == (size - components)) {
			return true;
		}
		return false;
	}
}
