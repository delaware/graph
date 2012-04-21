package at.reseau.graph.model;

public class DistanceMatrix extends Matrix {

	public DistanceMatrix(int size) {
		super(size);
	}
	
	@Override
	public void init() {
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				setValueAt(i, j, 0);
			}
		}
	}

	@Override
	public void populate(Matrix m) {
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				if(i != j) {
					setValueAt(i, j, m.getValueAt(i, j));
				} else {
					// by default diagonal is set to 0
					setValueAt(i, i, 0);
				}
			}
		}		
	}

}
