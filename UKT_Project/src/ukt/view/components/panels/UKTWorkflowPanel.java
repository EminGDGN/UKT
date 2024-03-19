package ukt.view.components.panels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.JTree;

import ukt.controller.Command;
import ukt.controller.UKTController;

public class UKTWorkflowPanel extends JPanel {
	
	private UKTController controller;
	
	private JTabbedPane tabbedPane;
	
	private JSplitPane splitPane;
	
	// Left panel components
	private JPanel leftPanel;
	private JList processTree;
	private JToolBar toolBar;
	private JButton addProcessButton;
	private JButton removeProcessButton;
	
	// Right panel components
	private JPanel rightPanel;
	private JTextArea textArea;
	
	public UKTWorkflowPanel(UKTController controller) {
		
		this.controller = controller;
		
		setLayout(new BorderLayout());
		
		// Tabbed Pane
		tabbedPane = new JTabbedPane();
		
		// Left panel
		leftPanel = new JPanel(new BorderLayout());
		
		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		
		addProcessButton = new JButton(new ImageIcon("./icons/add.png"));
		addProcessButton.addActionListener((ActionEvent e) -> {controller.handleCommand(Command.ADD_PROCESS_WORKFLOW);});
		
		removeProcessButton = new JButton(new ImageIcon("./icons/remove.png"));
		removeProcessButton.addActionListener((ActionEvent e) -> {controller.handleCommand(Command.REMOVE_PROCESS_WORKFLOW);});
		
		toolBar.add(addProcessButton);
		toolBar.add(removeProcessButton);
		
		processTree = new JList();
		
		leftPanel.add(processTree, BorderLayout.CENTER);
		leftPanel.add(toolBar, BorderLayout.SOUTH);
		
		// Right panel
		rightPanel = new JPanel(new BorderLayout());
		textArea = new JTextArea();
		rightPanel.add(textArea, BorderLayout.CENTER);
	
		
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
		
		tabbedPane.add(splitPane);
		
		add(tabbedPane);
	}
}
