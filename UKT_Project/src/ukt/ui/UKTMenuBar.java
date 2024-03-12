package ukt.ui;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class UKTMenuBar extends JMenuBar {
	
	private File selectedGraphFile;
	private JFrame parent;

	public UKTMenuBar(JFrame p) {	
		
		parent = p;

		// File menu
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic('F');

		JMenuItem loadGraphFileMenu = new JMenuItem("Load graph file");
		loadGraphFileMenu.setMnemonic('L');
		loadGraphFileMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.CTRL_DOWN_MASK));
		loadGraphFileMenu.addActionListener(this::loadGraphFileMenuListener);
		fileMenu.add(loadGraphFileMenu);

		fileMenu.addSeparator();

		JMenuItem exitFileMenu = new JMenuItem("Exit");
		exitFileMenu.setMnemonic('x');
		exitFileMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.ALT_DOWN_MASK));
		exitFileMenu.addActionListener(this::exitFileMenuListener);
		fileMenu.add(exitFileMenu);

		// Adding menu
		add(fileMenu);
	}
	
	public boolean isGraphFileLoaded() {
		return selectedGraphFile==null;
	}
	
	public File getLoadedGraphFile() {
		return selectedGraphFile;
	}

	private void loadGraphFileMenuListener(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		fileChooser.setDialogTitle("Select a graph in json format");
		fileChooser.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("json", "JSON");
		fileChooser.addChoosableFileFilter(filter);
		int result = fileChooser.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			selectedGraphFile = fileChooser.getSelectedFile();
			System.out.println(fileChooser.getSelectedFile().getPath());
		}
	}

	private void exitFileMenuListener(ActionEvent e) {
		System.exit(0);
	}
}
