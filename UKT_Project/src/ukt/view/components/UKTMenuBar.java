package ukt.view.components;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import ukt.controller.Command;
import ukt.controller.UKTController;

public class UKTMenuBar extends JMenuBar {
	
	private UKTController controller;

	public UKTMenuBar(UKTController controller) {	
		
		this.controller = controller;

		// File menu
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic('F');
		
		JMenuItem createWorkflowFileMenu = new JMenuItem("Create CWL workflow");
		createWorkflowFileMenu.setMnemonic('W');
		createWorkflowFileMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, KeyEvent.CTRL_DOWN_MASK));
		createWorkflowFileMenu.addActionListener((ActionEvent e) -> {controller.handleCommand(Command.CREATE_WORKFLOW);});
		fileMenu.add(createWorkflowFileMenu);
		
		fileMenu.addSeparator();

		JMenuItem loadGraphFileMenu = new JMenuItem("Load Kenning graph");
		loadGraphFileMenu.setMnemonic('K');
		loadGraphFileMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, KeyEvent.CTRL_DOWN_MASK));
		loadGraphFileMenu.addActionListener((ActionEvent e) -> {controller.handleCommand(Command.LOAD_GRAPH);});
		fileMenu.add(loadGraphFileMenu);

		fileMenu.addSeparator();

		JMenuItem exitFileMenu = new JMenuItem("Exit");
		exitFileMenu.setMnemonic('x');
		exitFileMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.ALT_DOWN_MASK));
		exitFileMenu.addActionListener((ActionEvent e) -> {controller.handleCommand(Command.EXIT_APPLICATION);});
		fileMenu.add(exitFileMenu);

		// Adding menu
		add(fileMenu);
	}

}
