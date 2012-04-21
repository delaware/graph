package at.reseau.graph.util;

import at.reseau.graph.model.*;

public class UtilMatrix {

	public Matrix multiply(Matrix m, Matrix n) {
		int sum = 0;
		Matrix result = new Matrix(m.getSize());
		
		for (int i=0;i<m.getSize();i++) {
			for (int j=0;j<m.getSize();j++) {
				for (int r=0;r<m.getSize();r++) {
					sum = result.getValueAt(i, j);
					if((sum += m.getValueAt(i,r) * n.getValueAt(r, j)) >= 1) {
						result.setValueAt(i, j, 1);
					}
				}
			}
		}
		return result;
	}

	public Matrix power(Matrix m, int pow) {
		Matrix temp = m;
		
		for(int i=0;i<pow;i++) {
			temp = multiply(m, temp);
		}
		return temp;
	}

}
