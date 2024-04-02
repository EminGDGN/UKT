package ukt.view.components.panels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
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
	
	
	private JSplitPane splitPane;
	
	// Left panel components
	private JPanel leftPanel;
	private JList processTree;
	private JToolBar toolBar;
	private JButton addProcessButton;
	private JButton removeProcessButton;
	
	// Right panel components
	private JPanel rightPanel;
	private JTextArea cwlText;
	private JToolBar cwlToolBar;
	private JButton runButton;
	private JTextArea cwlResult;
	private JPanel topRightPanel;
	private JSplitPane cwlSplitPane;
	
	public UKTWorkflowPanel(UKTController controller) {
		
		this.controller = controller;
		
		setLayout(new BorderLayout());
		
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
		topRightPanel = new JPanel(new BorderLayout());
		
		cwlToolBar = new JToolBar();
		cwlToolBar.setLayout(new BorderLayout());
		cwlToolBar.setFloatable(false);
		runButton = new JButton("Run");
		runButton.setEnabled(false);
		cwlToolBar.add(runButton, BorderLayout.EAST);
		
		cwlText = new JTextArea();
		initCwlText();
		cwlText.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		cwlText.setEditable(false);
		
		topRightPanel.add(cwlText, BorderLayout.CENTER);
		topRightPanel.add(cwlToolBar, BorderLayout.NORTH);
		
		cwlResult = new JTextArea();
		initCwlResult();
		cwlResult.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		cwlResult.setEditable(false);
		
		cwlSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,topRightPanel,cwlResult);
		rightPanel.add(cwlSplitPane, BorderLayout.CENTER);
	
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
		
		add(splitPane);
	}
	
	public void initCwlText() {
		cwlText.setText("Follow these steps to create a workflow:\n\n1. Add a process to the list (click on [+] button)\n2. Configure the order of processes and their parameters\n3. Click on Run button");
	}
	
	public void initCwlResult() {
		cwlResult.setText("No results yet. Create a workflow before running it");
	}
}
