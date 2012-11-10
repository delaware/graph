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

		AdjacencyMatrix m = new AdjacencyMatrix(matrix.getSize());
		m.populate(matrix);
		
		String text = "<font size =3 face=Fixedsys>";

		text += "<b>Beteiligte Knoten:</b><br>" + "Anzahl: "
				+ matrix.getSize() + "<br>";
		String knotenText = "{ }";
		text += "Knoten:";
		ArrayList<Integer> knoten = matrix.selektierteKnoten();
		if (matrix.selektierteKnoten().size() > 0) {
			knotenText = "{ " + knoten.get(0).toString();

			for (int i = 1; i < knoten.size(); i++) {
				knotenText += ", " + knoten.get(i);
			}
			knotenText += " }";
		}
		text += knotenText + "<br>";

		text += "<p>" + "<b>Ausgewählte Kanten:</b><br>" + "Anzahl: "
				+ m.getDegreeList() + "<br>";
		String kantenText = "{ }";
		text += "Kanten:";

		ArrayList<ArrayList<Integer>> kanten = matrix.selektierteKanten();
		if (matrix.selektierteKanten().size() > 0) {
			kantenText = "{ " + kanten.get(0).toString();

			for (int i = 1; i < kanten.size(); i++) {
				kantenText += ", " + kanten.get(i);
			}
			kantenText += " }";
		}
		text += kantenText + "<br>";

                textPane.setText(text);
		textPane.setCaretPosition(0);
	}
}
