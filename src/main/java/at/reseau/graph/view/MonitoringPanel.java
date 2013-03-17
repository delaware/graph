package at.reseau.graph.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import at.reseau.graph.model.AdjacencyMatrix;
import at.reseau.graph.model.Matrix;

public class MonitoringPanel extends JPanel {

	private static final long serialVersionUID = 8320890127120058410L;
	private static JTextPane textPane;

	public MonitoringPanel() 
        {
		initPanels();
	}

	public void initPanels() 
        {
		setLayout(new BorderLayout());
		textPane = new JTextPane();
		textPane.setBackground(new Color(250, 250, 250));
		textPane.setContentType("text/html");
		JScrollPane scrollPane = new JScrollPane(textPane);
		add(scrollPane, BorderLayout.CENTER);
	}

	public void clear() 
        {
		textPane.setText("");
	}

	public void updateWith(Matrix matrix) 
        {
		AdjacencyMatrix m = new AdjacencyMatrix(matrix);
		
		String text = "<font size =3 face=Fixedsys>";

		// selected nodes
		text += "<p><b>Beteiligte Knoten:</b><br>";
		text += "Anzahl: " + m.getSize() + "<br>";
		String knotenText = "{ }";
		text += "Knoten:";
		ArrayList<Integer> nodes = m.selectNode();
		if (m.selectNode().size() > 0) {
			knotenText = "{ " + nodes.get(0).toString();
			for (int i = 1; i < nodes.size(); i++) {
				knotenText += ", " + nodes.get(i);
			}
			knotenText += " }";
		}
		text += knotenText + "<br>";

		// selected edges
		text += "<p><b>Ausgewählte Kanten:</b><br>Anzahl: "
				+ m.getDegreeList() + "<br>";
		String kantenText = "{ }";
		text += "Kanten:";

		ArrayList<ArrayList<Integer>> edges = m.selectEdge();
		if (m.selectEdge().size() > 0) {
			kantenText = "{ " + edges.get(0).toString();

			for (int i = 1; i < edges.size(); i++) {
				kantenText += ", " + edges.get(i);
			}
			kantenText += " }";
		}
		text += kantenText + "<br>";
		
		text += "<p><b>Knotengrade:</b><br>";
		ArrayList<Integer> degrees = m.getDegreeList();
		for(int i = 0; i < degrees.size(); i++) {
			text += "Knoten " + (i + 1) + ": " + degrees.get(i) + "<br>";
		}
		text += "</p>";
		
		textPane.setText(text);
		textPane.setCaretPosition(0);
	}
}
