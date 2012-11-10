package at.reseau.graph.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import at.reseau.graph.model.Matrix;


public class ConsolePanel extends JPanel 
{

	private static final long serialVersionUID = 8320890127120058410L;
	private static JTextPane textPane;

	public ConsolePanel() 
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

		String text = "<font size =3 face=Fixedsys>";
		
		textPane.setText(text);
		textPane.setCaretPosition(0);
	}
}
