package at.reseau.graph.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import at.reseau.graph.model.AdjacencyMatrix;
import at.reseau.graph.model.DistanceMatrix;
import at.reseau.graph.model.Matrix;
import at.reseau.graph.model.PathMatrix;

public class CalculationPanel extends JPanel {

	private static final long serialVersionUID = 1790315753452164325L;
	private static JTextPane textPane;

	public CalculationPanel() {
		initPanels();
	}

	public void initPanels() {
		setLayout(new BorderLayout());
		textPane = new JTextPane();
		textPane.setBackground(new Color(250, 250, 250));
		textPane.setContentType("text/html");
		JScrollPane scrollPane = new JScrollPane(textPane);
		add(scrollPane, BorderLayout.CENTER);
	}

	public void clear() {
		textPane.setText("");
	}

	public void updateWith(Matrix matrix) {
		System.out.println(matrix);

		String text = "<font size =3 face=Fixedsys>";
                //text += "Noch keine Ausgabe ;)";
 
		PathMatrix path = new PathMatrix(matrix);
		DistanceMatrix distance = new DistanceMatrix(matrix);
		AdjacencyMatrix adjacency = new AdjacencyMatrix(matrix);
		
		text += "<p><b>KOMPONENTEN:</b><br>";
		text += "Anzahl: " + path.getComponents() + "<br>";
		text += "<p>";
		
		text += "<p><b>RADIUS:</b><br>";
		text += "Anzahl: " + distance.getRadius() + "<br>";
		text += "<p>";
		
		text += "<p><b>DURCHMESSER:</b><br>";
		text += "Anzahl: " + distance.getDiameter() + "<br>";
		text += "<p>";
		
		text += "<p><b>EXZENTRITÄTEN:</b><br>";
		ArrayList<Integer> eccentricity = distance.getEccentricity();
		for(int i = 0; i < eccentricity.size(); i++) {
			text += "Knoten " + (i + 1) + ": " + eccentricity.get(i) + "<br>";
		}
		text += "<p>";
		
		text += "<p><b>KNOTENGRADE:</b><br>";
		ArrayList<Integer> degrees = adjacency.getDegreeList();
		for(int i = 0; i < degrees.size(); i++) {
			text += "Knoten " + (i + 1) + ": " + degrees.get(i) + "<br>";
		}
		text += "<p>";
		
		text += "<p><b>Artikulation:</b><br>";
		ArrayList<Integer> articulations = path.getArticulations(adjacency);
		text += "Anzahl: " + articulations.size() + "<br>";
		text += "Knoten: " + articulations + "<p>";
		
		text += "<p>" + "<b>BRÜCKEN:</b><br>" + "Anzahl: "
				+ path.getBrigdes(adjacency).size() + "<br>";
		String brueckenText = "{ }";
		
		text += "Kanten:";

		ArrayList<String> bruecken = path.getBrigdes(adjacency);
		if (path.getBrigdes(adjacency).size() > 0) {
			brueckenText = "{ " + bruecken.get(0).toString();

			for (int i = 1; i < bruecken.size(); i++) {
				brueckenText += ", " + bruecken.get(i);
			}
			brueckenText += " }";
		}
		text += brueckenText;
		
		textPane.setText(text);
		textPane.setCaretPosition(0);

	}
}
