package at.reseau.graph;

import java.io.IOException;
import javax.swing.SwingUtilities;
import at.reseau.graph.view.MainFrame;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        
        SwingUtilities.invokeLater(new Runnable() {
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