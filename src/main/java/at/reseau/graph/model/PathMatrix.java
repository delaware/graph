package at.reseau.graph.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

public class PathMatrix extends Matrix {

	private static final long serialVersionUID = 1991078984908956353L;

	public PathMatrix(int size) {
		super(size);
		init();
	}
	
	public PathMatrix(Matrix m) {
		super(m.getSize());
		
		// populate matrix with adjacency matrix
		populate(m);
	}

	@Override
	public void init() {
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				if(i != j) {
					setValueAt(i, j, 0);
				} else {
					// by default diagonal is set to 1
					setValueAt(i, i, 1);
				}
			}
		}
	}

	@Override
	public void populate(Matrix m) {
		Matrix temp = m;
		
		// populate matrix with adjacency matrix
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				if(m.getValueAt(i, j) == 1) setValueAt(i, j, m.getValueAt(i, j));
			}
		}
		
		// calculate matrix
		for(int i=0;i<size;i++) {
			temp = multiply(this, temp);
		}
		
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				if(temp.getValueAt(i, j) >= 1) this.setValueAt(i, j, 1);
			}
		}
	}
	
	public int getComponents() {
		Vector<Integer> vector = new Vector<Integer>();
		int components = 1;
		
		for(int column=0;column<size;column++) {
			vector.add(getValueAt(0, column));
		}
		
		if(Collections.min(vector) == 0) {
			for(int row=0;row<size;row++) {
				for(int column=0;column<size;column++) {
					if(vector.get(column)==0) {
						row = column;
						components++;
						for(int i=column;i<size;i++) {
							if(getValueAt(row, i)==1) {
								vector.set(i,components);
							}
						}
					}
				}
			}
		}
		
		return Collections.max(vector);
		
	}
	
	public ArrayList<Integer> getArticulations(AdjacencyMatrix m) {
		int current = getComponents();
		ArrayList<Integer> articulations = new ArrayList<Integer>();
		
		for(int node=0;node<size;node++) {
			Matrix temp = new Matrix(m.getSize());
			temp.populate(m);
			for(int i=0;i<size;i++) {
				temp.setValueAt(node, i, 0);
				temp.setValueAt(i, node, 0);
			}
			PathMatrix path = new PathMatrix(temp);
			if(path.getComponents() - 1 > current) {
				articulations.add(node+1);
			}
		}
		return articulations;
	}
	
	public ArrayList<String> getBrigdes(AdjacencyMatrix m) {
		int current = getComponents();
		ArrayList<String> bridges = new ArrayList<String>();
		
		Matrix temp = new Matrix(m.getSize());
		temp.populate(m);
		
		for(int row=0;row<size;row++) {
			for(int column=0;column<size;column++) {
				if(temp.getValueAt(row, column) == 1) {
					temp.setValueAt(row, column, 0);
					temp.setValueAt(column, row, 0);
					PathMatrix path = new PathMatrix(temp);
					if(path.getComponents() > current) {
						ArrayList<Integer> bridge = new ArrayList<Integer>();
						bridge.add(Math.min(column + 1, row + 1));
                        bridge.add(Math.max(column + 1, row + 1));
                        if(!bridges.contains(bridge.toString())) {
                            bridges.add(bridge.toString());
                        }
					}
					temp.setValueAt(row, column, 1);
					temp.setValueAt(column, row, 1);
				}
			}
		}
		return bridges;
	}

}
