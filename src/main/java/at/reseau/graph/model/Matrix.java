package at.reseau.graph.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Matrix implements BaseMatrix, Serializable{

	static final long serialVersionUID = 1L;
	protected int[][] values;
	// default graph size
	public int size = DEFAULT_SIZE;
	public static int DEFAULT_SIZE = 3;
	
	public Matrix() {
		// initialize matrix
		values = new int[DEFAULT_SIZE][DEFAULT_SIZE];
	}
	
	public Matrix(int size) {
		if(size < 2 || size > 100) {
			throw new IllegalArgumentException();
		}
		// initialize matrix
		this.size = size;
		values = new int[size][size];
		init();
	}
	
	public void init() {
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				setValueAt(i, j, 0);
			}
		}
	}
	
	public int getSize() {
		return size;
	}
	
	public int getValueAt(int row, int column) {
		if (row < 0 || row >= values.length) {
			throw new IllegalArgumentException();
		}
		if (column < 0 || column >= values[row].length) {
			throw new IllegalArgumentException();
		}
		return values[row][column];
	}
	
	public void setValueAt(int row, int column, int value) {
		if (row < 0 || row >= values.length) {
			throw new IllegalArgumentException();
		}
		if (column < 0 || column >= values[row].length) {
			throw new IllegalArgumentException();
		}
		values[row][column] = value;
	}

	public void populate(Matrix m) {
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				setValueAt(i, j, m.getValueAt(i, j));
			}
		}		
	}

	public Matrix multiply(Matrix m, Matrix n) {
		int sum = 0;
		Matrix result = new Matrix(m.getSize());
		
		for (int i=0;i<m.getSize();i++) {
			for (int j=0;j<m.getSize();j++) {
				for (int r=0;r<m.getSize();r++) {
					sum = result.getValueAt(i, j);
					if((sum += m.getValueAt(i,r) * n.getValueAt(r, j)) >= 1) {
						result.setValueAt(i, j, sum);
					}
				}
			}
		}
		return result;
	}
	
	public ArrayList<ArrayList<Integer>> selectEdge() {
		ArrayList<ArrayList<Integer>> edges = new ArrayList<ArrayList<Integer>>();
		for (int row = 0; row < getSize(); row++) {
			for (int column = 0; column < getSize(); column++) {
				if (values[row][column] > 0) {
					ArrayList<Integer> neueKante = new ArrayList<Integer>();
					neueKante.add(Math.min(row + 1, column + 1));
					neueKante.add(Math.max(column + 1, row + 1));
					if (!edges.contains(neueKante)) {
						edges.add(neueKante);
					}
				}
			}
		}
		return edges;
	}

	public ArrayList<Integer> selectNode() {
		ArrayList<Integer> nodes = new ArrayList<Integer>();
		for (int row = 0; row < getSize(); row++) {
			for (int column = 0; column < getSize(); column++) {
				if (values[row][column] > 0) {
					if (!nodes.contains(row + 1)) {
						nodes.add(row + 1);
					}
					if (!nodes.contains(column + 1)) {
						nodes.add(column + 1);
					}
				}
			}
		}
		Collections.sort(nodes);
		return nodes;
	}
	
}
