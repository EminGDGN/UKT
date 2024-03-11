package ukt.ui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class UKTMenuBar extends JMenuBar {

	private JMenu fileMenu, editMenu, helpMenu;
	private JMenuItem

	public UKTMenuBar() {

		// File menu
		fileMenu = new JMenu("File");
		

		// File menu
		editMenu = new JMenu("Edit");

		// File menu
		helpMenu = new JMenu("Help");

		add(fileMenu);
	}
}
