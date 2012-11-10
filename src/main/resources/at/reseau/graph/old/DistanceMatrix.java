package at.reseau.graph.old;

import java.util.ArrayList;

public class DistanceMatrix extends Matrix {

	public DistanceMatrix(int size) {
		super(size);
		init();
	}
	
	@Override
	public void init() {
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				if(i != j) {
					setValueAt(i, j, -1);
				} else {
					setValueAt(i, i, 0);
				}
			}
		}
	}

	@Override
	public void populate(Matrix m) {
		Matrix power = m,temp = this;
		
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				if(m.getValueAt(i, j) == 1) setValueAt(i, j, m.getValueAt(i, j));
			}
		}
		
		// calculate distance matrix
		for(int p=2;p<(size+2);p++) {
			power = multiply(m, power);
			for(int i=0;i<size;i++) {
				for(int j=0;j<size;j++) {
					if(power.getValueAt(i, j) > 0 && temp.getValueAt(i, j) == -1) {
						temp.setValueAt(i, j, p);
					}
				}
			}
		}
		this.values = temp.values;
	}
	
	public ArrayList<Integer> getEccentricity() {
		ArrayList<Integer> eccentricity = new ArrayList<Integer>();
		
		for(int i=0;i<size;i++) {
			int temp = 0;
			for(int j=0;j<size;j++) {
				temp = Math.max(temp,getValueAt(i, j));
			}
			eccentricity.add(temp);
		}		
		return eccentricity;
	}
	
	public int getDiameter() {
		ArrayList<Integer> eccentricity = getEccentricity();
		int diameter = 0;
		
		for(int i : eccentricity) {
			diameter = Math.max(i,diameter);
		}
		return diameter;
	}
	
	public int getRadius() {
		ArrayList<Integer> eccentricity = getEccentricity();
		int radius = size;
		
		for(int i : eccentricity) {
			radius = Math.min(i,radius);
		}
		return radius;
	}
	
	public ArrayList<Integer> getCenter() {
		ArrayList<Integer> eccentricity = getEccentricity();
		int radius = getRadius();
		
		ArrayList<Integer> center = new ArrayList<Integer>();
			for(int c=0;c<size;c++) {
				if(eccentricity.get(c) == radius) center.add(c);
			}
		return center;
	}

}
