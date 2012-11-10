package at.reseau.graph.model;

import java.util.ArrayList;

public class PathMatrix extends Matrix {

	private static final long serialVersionUID = 1991078984908956353L;

	public PathMatrix(int size) {
		super(size);
		init();
	}
	
	public PathMatrix(Matrix m) {
		super(m.getSize());
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

//	@Override
//	public void populate(Matrix m) {
//		Matrix temp = m;
//		
//		// populate matrix with adjacency matrix
//		for(int i=0;i<size;i++) {
//			for(int j=0;j<size;j++) {
//				if(m.getValueAt(i, j) == 1) setValueAt(i, j, m.getValueAt(i, j));
//			}
//		}
//		
//		// calculate matrix
//		for(int i=0;i<size;i++) {
//			temp = multiply(this, temp);
//		}
//		
//		for(int i=0;i<size;i++) {
//			for(int j=0;j<size;j++) {
//				if(temp.getValueAt(i, j) >= 1) this.setValueAt(i, j, 1);
//			}
//		}
//	}
	
	public int getComponents() {
		ArrayList<Integer> components = new ArrayList<Integer>();
		
		for(int i=0;i<size;i++) {
			int checksum = 0;
			for(int j=0;j<size;j++) {
				if(getValueAt(i, j) == 1) checksum += Math.pow(2, j);
			}
			if(!components.contains(checksum)) components.add(checksum);
		}
		return components.size();
	}
	
	public ArrayList<Integer> getArticulation(AdjacencyMatrix m) {
		int current = getComponents();
		ArrayList<Integer> articulations = new ArrayList<Integer>();
		
		for(int node=0;node<size;node++) {
			Matrix temp = new Matrix();
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
	

}
