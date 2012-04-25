package at.reseau.graph.model;

public class Matrix implements BaseMatrix{

	protected int[][] values;
	// default graph size
	protected int size = 5;

	public Matrix() {
		// initialize matrix
		values = new int[size][size];
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

	public void print() {
		String header = "x | ";
		String line = "- - ";
		String[] row = new String[size];
		
		for(int i=0;i<size;i++) {
			header += i + " ";
			line += "- ";
			row[i] = i + " | ";
			for(int j=0;j<size;j++) {
				row[i] += values[i][j] + " ";
			}
		}
		System.out.println(header);
		System.out.println(line);
		for(String s : row) {
			System.out.println(s);
		}
	}

	public void populate(Matrix m) {
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				setValueAt(i, j, 0);
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
	
}
