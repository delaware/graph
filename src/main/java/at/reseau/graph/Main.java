package at.reseau.graph;

import java.io.IOException;
import javax.swing.SwingUtilities;
import at.reseau.graph.view.MainFrame;

public class Main 
{
    public static void main( String[] args )
    {
        
        SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new  MainFrame().setVisible(true);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
    }
}