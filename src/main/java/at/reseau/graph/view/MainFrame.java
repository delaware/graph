package at.reseau.graph.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.miginfocom.swing.MigLayout;
import at.reseau.graph.model.DistanceMatrix;
import at.reseau.graph.model.Matrix;
import at.reseau.graph.model.PathMatrix;
import at.reseau.graph.util.FileHelper;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = -8933400712100861899L;

	private final static String APPLICATION_TITLE = "GRAPHIX 5";
	private final static String appInfo = "<html><font size = 4>GRAPHIX Version 5</font><br><br><font size = 2><font size = 2>Erstellt am: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;05.12.2011<br><font size = 2><font size = 2>Letztes Update: &nbsp;25.01.2012<br><br>Autor: Gregor Messner<br>Copyleft  2011-2012<br><br></html>";

	private MatrixPanel matrixPanel;
	private MatrixPanel distancePanel;
	private MatrixPanel pathPanel;
	private CalculationPanel calculationPanel;
	private GraphPanel graphPanel;
	private MonitoringPanel monitoringPanel;
	private JSlider sizeSlider;

	public MainFrame() throws IOException 
        {
		setSize(950, 740);

		setTitle(APPLICATION_TITLE);
		setLocationByPlatform(true);
		setDefaultLookAndFeelDecorated(true);

		try 
                {
			Image icon = Toolkit.getDefaultToolkit().getImage(
					File.class.getResource("/icons/app.png"));
			setIconImage(icon);
		} 
                catch (Exception e) 
                {
		}

		setLayout(new MigLayout("filly", "[][grow]"));
		initPanels();
		addWindowListener(new WindowAdapter() 
                {
			public void windowClosing(WindowEvent evt) 
                        {
				dialogBeenden();
			}
		});

	}

	private void initPanels() 
        {
		JToolBar calculationToolbar = new JToolBar("Toolbar",
				JToolBar.HORIZONTAL);
		calculationToolbar.setLayout(new MigLayout());
		calculationToolbar.setFloatable(true);

		sizeSlider = new JSlider(SwingConstants.HORIZONTAL, 3, 15,
				Matrix.DEFAULT_SIZE);
		sizeSlider.setBorder(new TitledBorder("Größe der Matrix"));
		sizeSlider.setOpaque(false);
		sizeSlider.setMajorTickSpacing(1);
		sizeSlider.setPaintTicks(false);
		sizeSlider.setPaintLabels(true);
		sizeSlider.setSnapToTicks(true);
		sizeSlider.addChangeListener(new ChangeListener() 
                {
			public void stateChanged(ChangeEvent e) {
				
				Matrix input = new Matrix(sizeSlider.getValue()-1);
				matrixPanel.updateWith(new Matrix(sizeSlider.getValue()-1), false);
				input = matrixPanel.getCurrentMatrix();
				
				PathMatrix path = new PathMatrix(input);
				DistanceMatrix distance = new DistanceMatrix(input);
				
				
				graphPanel.updateWith(matrixPanel.getCurrentMatrix());
				pathPanel.updateWith(path, true);
				distancePanel.updateWith(distance, true);
			}
		});
		calculationToolbar.add(sizeSlider, "w 288, h 60");

		JButton calcButton = new JButton(
				"<html><center><font size = 4>Berechnen</font><br><font size = 2>aktualisieren</font></html>");
		calcButton.addActionListener(new ActionListener() 
                {
			public void actionPerformed(ActionEvent e) 
                        {
				Matrix input = new Matrix(sizeSlider.getValue()-1);
				matrixPanel.updateWith(new Matrix(sizeSlider.getValue()-1), false);
				input = matrixPanel.getCurrentMatrix();
				
				PathMatrix path = new PathMatrix(input);
				DistanceMatrix distance = new DistanceMatrix(input);
				
				distancePanel.updateWith(distance, true);
				pathPanel.updateWith(path, true);
				calculationPanel.updateWith(input);
				monitoringPanel.updateWith(input);
				graphPanel.updateWith(input);

				//Automatisches Speichern bei jeder neuen Berechnung - Berger Logging :-D
//				try {
//					FileHelper.save(new File("d:\\" + System.nanoTime()
//							+ ".matrix"), matrixPanel.getCurrentMatrix());
//				} catch (FileNotFoundException e1) {
//					e1.printStackTrace();
//				} catch (IOException e1) {
//					e1.printStackTrace();
//				}
			}
		});
		calculationToolbar.add(calcButton, "gapx 22, w 100");

		JButton refreshButton = new JButton(
				"<html><center><font size = 4>Rücksetzen<br><font size = 2>löschen</font></html>");
		refreshButton.setOpaque(false);
		refreshButton.addActionListener(new ActionListener() 
                {
			public void actionPerformed(ActionEvent e) {
				Matrix blank = new Matrix();
				
				PathMatrix path = new PathMatrix(blank.getSize());
				DistanceMatrix distance = new DistanceMatrix(blank.getSize());
				
				matrixPanel.updateWith(blank, true);
				sizeSlider.setValue(blank.getSize());
				distancePanel.updateWith(distance, true);
				pathPanel.updateWith(path, true);
				graphPanel.updateWith(blank);
				calculationPanel.clear();
			}
		});
		calculationToolbar.add(refreshButton, ", gapx 5, w 100");

		JButton exitButton = new JButton(new ImageIcon(getClass().getResource(
				"/icons/exit2.png")));
		exitButton.setOpaque(false);
		calculationToolbar.add(exitButton, "gapx 5");
		exitButton.addActionListener(new ActionListener() 
                {
			public void actionPerformed(ActionEvent arg0) 
                        {
				dialogBeenden();
			}
		});

		JLabel label = new JLabel("G=(V,E)");
		label.setOpaque(false);
		label.setFont(new Font("SansSerif", Font.BOLD, 26));
		label.setForeground(new Color(190, 190, 190));
		calculationToolbar.add(label, "gapx 35");

		JButton openButton = new JButton(new ImageIcon(getClass().getResource(
				"/icons/open.png")));
		openButton.addActionListener(new ActionListener() 
                {
			public void actionPerformed(ActionEvent arg0) 
                        {
				JFileChooser dialog = new JFileChooser(new File("."));

				if (dialog.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) 
                                {
					try 
                                        {
						Matrix loaded = FileHelper.load(dialog
								.getSelectedFile());
						sizeSlider.setValue(loaded.getSize());

						matrixPanel.updateWith(loaded, true);
						distancePanel.updateWith(new Matrix(0), true);
						pathPanel.updateWith(new Matrix(0), true);
					} 
                                        catch (FileNotFoundException e) 
                                        {
						JOptionPane
								.showMessageDialog(null,
										"Bitte wählen Sie eine existierende Datei aus.");
					} 
                                        catch (IOException e) 
                                        {
						JOptionPane.showMessageDialog(null,
								"Fehler beim Einlesen der Matrize.");
					}
                                        catch (ClassNotFoundException e) 
                                        {
						JOptionPane
								.showMessageDialog(null,
										"Unbekannter Inhalt. Datei konnte nicht verarbeitet werden.");
					}
				}
			}
		});
		calculationToolbar.add(openButton, "gapx 20");

		JButton saveButton = new JButton(new ImageIcon(getClass().getResource(
				"/icons/save.png")));
		saveButton.addActionListener(new ActionListener() 
                {
			public void actionPerformed(ActionEvent arg0) 
                        {
				JFileChooser dialog = new JFileChooser(new File("."));

				if (dialog.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) 
                                {
					try 
                                        {
						FileHelper.save(dialog.getSelectedFile(), matrixPanel
								.getCurrentMatrix());
					}
                                        catch (FileNotFoundException e) 
                                        {
						JOptionPane
								.showMessageDialog(null,
										"Bitte wählen Sie eine existierende Datei aus.");
					}
                                        catch (IOException e) 
                                        {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null,
								"Fehler beim Speichern der Matrize.");
					}
				}
			}
		});
		calculationToolbar.add(saveButton, "gapx 2");

		JButton infoButton = new JButton(new ImageIcon(getClass().getResource(
				"/icons/info2.png")));
		infoButton.setOpaque(false);
		calculationToolbar.add(infoButton, "gapx 2");
		infoButton.addActionListener(new ActionListener() 
                {
			public void actionPerformed(ActionEvent arg0) 
                        {
				getApplicationInfo();
			}
		});

		JTabbedPane tabbedPane = new JTabbedPane();
		JComponent panel1 = calculationPanel = new CalculationPanel();
		JComponent panel2 = monitoringPanel = new MonitoringPanel();
		JComponent panel3 = new DocuPanel();
		tabbedPane.addTab("Berechnungen", panel1);
		tabbedPane.addTab("Monitoring", panel2);
		tabbedPane.addTab("Dokumentation", panel3);

		add(calculationToolbar, "north");
		Matrix blank = new Matrix();
		
		PathMatrix path = new PathMatrix(blank.getSize());
		path.populate(blank);
		DistanceMatrix distance = new DistanceMatrix(blank.getSize());
		distance.populate(blank);

		JPanel outputPanels = new JPanel(new MigLayout("wrap 2"));
		outputPanels.add(matrixPanel = new MatrixPanel("AdjacencyMatrix",
				new Matrix(), true, false));
		matrixPanel.setSelectedColor(new Color(0, 255, 0));
		outputPanels.add(graphPanel = new GraphPanel("Graph"), "w 305, h 305");
		graphPanel.updateWith(new Matrix());

		outputPanels.add(pathPanel = new MatrixPanel("PathMatrix",
				path, false, true));
		pathPanel.setSelectedColor(new Color(255, 255, 51));
		outputPanels.add(distancePanel = new MatrixPanel("DistanceMatrix",
				distance, false, true));
		distancePanel.setSelectedColor(new Color(255, 255, 51));

		add(outputPanels, "aligny top");
		add(tabbedPane, "grow");
	}

	private void getApplicationInfo() 
        {
		JOptionPane.showMessageDialog(null, appInfo, "Information",
				JOptionPane.PLAIN_MESSAGE);
	}

	private void dialogBeenden() 
        {
		String msg = "Möchten Sie " + APPLICATION_TITLE + " beenden?";
		int returnVal = JOptionPane.showConfirmDialog(this, msg,
				"Bitte bestätigen", JOptionPane.YES_NO_OPTION);
		if (returnVal == JOptionPane.YES_OPTION) 
                {
			dispose();
		}
	}

}