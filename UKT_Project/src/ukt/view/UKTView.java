package ukt.view;

import java.awt.BorderLayout;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import ukt.controller.FileType;
import ukt.controller.Panel;
import ukt.controller.UKTController;
import ukt.view.components.UKTCenterPanel;
import ukt.view.components.UKTMenuBar;

public class UKTView extends JFrame {

	/* Controller */
	private UKTController controller;
	
	/* UI Components */
	private JFileChooser fileChooser;
	private JOptionPane optionPane;
	private UKTMenuBar menuBar;
	private UKTCenterPanel centerPanel;

	/* Constructor */
	public UKTView(UKTController controller) {
		super("UKT"); // title of the window
		this.controller = controller;
		this.initComponents();
	}

	private void initComponents() {
		
		fileChooser = new JFileChooser();
		menuBar = new UKTMenuBar(controller);
		centerPanel = new UKTCenterPanel(controller);
		optionPane = new JOptionPane();
		
		// Set menu bar
		setJMenuBar(menuBar);

		// Add center panel
		add(centerPanel, BorderLayout.CENTER);

		// Add bottom tool bar
		// add(new UKTBottomBar(controller), BorderLayout.SOUTH);
	}

	public void startInterface() {
		// Set window size
		setSize(1000, 500);

		// Make the window appear in the middle of the screen
		setLocationRelativeTo(null);

		// Exit on closing window
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// Set first panel
		setPanel(Panel.HOME_PANEL);

		// Set the window visible
		setVisible(true);
	}
	
	public void setPanel(Panel p) {
		this.centerPanel.setPanelVisible(p);
		centerPanel.repaint();
	}
	
	public File showFileChooser(String title, FileType type) {
        fileChooser.setDialogTitle(title);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileFilter(new FileNameExtensionFilter(type.getDisplayName()+" files", type.getExtension()));
        
        int userSelection = fileChooser.showOpenDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }
        
        return null;
    }
	
	public String showOptionPane(String title) {
		String userSelection = JOptionPane.showInputDialog(null, title);
        if (userSelection != null) {
            return userSelection;
        } else {
            return null;
        }
	}
	
	public void displayErrorMessage(String errorMessage) {
		JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
	}
}
