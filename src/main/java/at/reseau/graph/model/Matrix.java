package at.reseau.graph.model;

import java.util.ArrayList;
import java.util.Collections;

public class Matrix implements BaseMatrix{

	protected int[][] values;
	// default graph size
	public int size = DEFAULT_SIZE;
	public static int DEFAULT_SIZE = 5;
	
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
	
	public ArrayList<ArrayList<Integer>> selektierteKanten() {
		ArrayList<ArrayList<Integer>> selektierteKanten = new ArrayList<ArrayList<Integer>>();
		// Durchlaufe alle Zeilen und Spalten.
		for (int row = 0; row < getSize(); row++) {
			for (int column = 0; column < getSize(); column++) {
				// Verarbeite die aktuelle Kante nur, wenn es sich nicht um die
				// Diagonale handelt und der Wert > 0 ist (also eine Kante
				// eingetragen ist f�r diese Verbindung).
				if (values[row][column] > 0) {
					// Erstelle eine neue Liste, in welche die zwei
					// Knoten-Nummern eingetragen werden sollen.
					ArrayList<Integer> neueKante = new ArrayList<Integer>();
					// F�ge zuerst den Knoten mit der kleineren Nummer ein, dann
					// jenen mit der gr��eren.
					neueKante.add(Math.min(row + 1, column + 1));
					neueKante.add(Math.max(column + 1, row + 1));

					if (!selektierteKanten.contains(neueKante)) {
						selektierteKanten.add(neueKante);
					}
				}
			}
		}
		return selektierteKanten;
	}

	/**
         * Gibt eine Liste aller Knoten zurück. Wird für die GUI benötigt.
         * @return selektierteKnoten - Alle Knoten als ArrayList.
         */
	public ArrayList<Integer> selektierteKnoten() {
		ArrayList<Integer> selektierteKnoten = new ArrayList<Integer>();
		// Durchlaufe alle Zeilen und Spalten.
		for (int row = 0; row < getSize(); row++) {
			for (int column = 0; column < getSize(); column++) {
				// Ignoriere die Diagonale. Es werden nur Knoten gewertet, die
				// abseits der Diagonale eingetragen worden sind, deren Wert
				// also > 0 ist.
				if (values[row][column] > 0) {
					// Wurden Zeile oder Spalte noch nicht in die Liste
					// eingetragen, dann f�ge sie ein.
					if (!selektierteKnoten.contains(row + 1)) {
						selektierteKnoten.add(row + 1);
					}
					if (!selektierteKnoten.contains(column + 1)) {
						selektierteKnoten.add(column + 1);
					}
				}
			}
		}
		// Sortiere die Liste von Knoten.
		Collections.sort(selektierteKnoten);
		return selektierteKnoten;
	}
	
}
