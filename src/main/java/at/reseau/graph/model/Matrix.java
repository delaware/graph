package at.reseau.graph.model;

public class Matrix {

	protected int[][] values;
	protected int size;
	
	// constructor create N-by-N matrix of given size 
	public Matrix(int size) {
		if(size < 2 || size > 100) {
			throw new IllegalArgumentException("Size of matrix must be between 2 and 100");
		}
		this.size = size;
		values = new int[size][size];
		init();
	}
	
	// set all values in matrix to 0
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
    
    // return C = A * B
	public Matrix multiply(Matrix A, Matrix B) {
		int sum = 0;
		Matrix C = new Matrix(A.getSize());
		
		for (int i=0;i<A.getSize();i++) {
			for (int j=0;j<A.getSize();j++) {
				for (int r=0;r<A.getSize();r++) {
					sum = C.getValueAt(i, j);
					if((sum += A.getValueAt(i,r) * B.getValueAt(r, j)) >= 1) {
						C.setValueAt(i, j, sum);
					}
				}
			}
		}
		return C;
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
}
