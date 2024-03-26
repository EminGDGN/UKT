package ukt.view.components.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

import ukt.controller.Command;
import ukt.controller.UKTController;

public class UKTKenningPanel extends JPanel {

	private UKTController controller;


	// North panel components
	private JPanel northPanel;
	private JButton convertButton;
	private JButton addSpecificationButton;
	private JButton addGraphButton;
	private JLabel specFileName;
	private JLabel graphFileName;

	
	private JSplitPane splitPane;

	// Left panel components
	private JPanel leftPanel;
	private JTextArea cwlGraph;
	private JToolBar leftToolBar;
	private JButton runButton;


	// Right panel components
	private JPanel rightPanel;
	private JTextArea cwlResult;

	public UKTKenningPanel(UKTController controller) {

		this.controller = controller;

		setLayout(new BorderLayout());

		// North panel
		northPanel = new JPanel();
		northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));

		JPanel firstPanel = new JPanel(new BorderLayout());
		JPanel specificationPanel = new JPanel(new FlowLayout());
		addSpecificationButton = new JButton("Add specifications");
		addSpecificationButton.addActionListener((ActionEvent e) -> {
            controller.handleCommand(Command.ADD_SPECIFICATION_FILE);
        });
		specificationPanel.add(new JLabel("Specifications file:"));
		specificationPanel.add(addSpecificationButton);
		specFileName = new JLabel("");
		specFileName.setFont(new Font(Font.SANS_SERIF, Font.PLAIN,  13));
		specFileName.setVisible(false);
		specificationPanel.add(specFileName);
		firstPanel.add(specificationPanel, BorderLayout.WEST);
		firstPanel.add(Box.createHorizontalGlue(), BorderLayout.CENTER);
		northPanel.add(firstPanel);

		JPanel secondPanel = new JPanel(new BorderLayout());
		JPanel graphPanel = new JPanel(new FlowLayout());
		addGraphButton = new JButton("Add graph");
		addGraphButton.setEnabled(false);
		addGraphButton.addActionListener((ActionEvent e) -> {
            controller.handleCommand(Command.ADD_GRAPH_FILE);
        });
		graphPanel.add(new JLabel("Graph file:"));
		graphPanel.add(addGraphButton);
		graphFileName = new JLabel("");
		graphFileName.setFont(new Font(Font.SANS_SERIF, Font.PLAIN,  13));
		graphFileName.setVisible(false);
		graphPanel.add(graphFileName);
		secondPanel.add(graphPanel, BorderLayout.WEST);
		secondPanel.add(Box.createHorizontalGlue(), BorderLayout.CENTER);
		northPanel.add(secondPanel);

		JPanel errorPanel = new JPanel(new BorderLayout());
		errorPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		convertButton = new JButton("Convert");
		convertButton.setEnabled(false);
		convertButton.addActionListener((ActionEvent e) -> {
            controller.handleCommand(Command.CONVERT_KENNING_TO_CWL);
        });
		errorPanel.add(Box.createHorizontalGlue(), BorderLayout.CENTER); // Pour étirer l'espace horizontal
		errorPanel.add(convertButton, BorderLayout.EAST);
		northPanel.add(errorPanel);
		
		// Left panel
		leftPanel = new JPanel(new BorderLayout());
		cwlGraph = new JTextArea("Follow these steps to convert the graph:\n\n1. Add a specifications file\n2. Add a graph file complying with the previous specification\n3. Click on Convert button");
		cwlGraph.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		cwlGraph.setEditable(false);
		leftToolBar = new JToolBar();
		leftToolBar.setLayout(new BorderLayout());
		leftToolBar.setFloatable(false);
		runButton = new JButton("Run");
		runButton.setEnabled(false);
		runButton.addActionListener((ActionEvent e) -> {
            controller.handleCommand(Command.RUN_CWL);
        });
		leftToolBar.add(runButton, BorderLayout.EAST);
		
		leftPanel.add(cwlGraph, BorderLayout.CENTER);
		leftPanel.add(leftToolBar, BorderLayout.SOUTH);
		
		// Right panel
		rightPanel = new JPanel(new BorderLayout());
		cwlResult = new JTextArea();
		cwlResult.setEnabled(false);
		
		rightPanel.add(cwlResult, BorderLayout.CENTER);

		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);

		add(northPanel, BorderLayout.NORTH);
		add(splitPane, BorderLayout.CENTER);
	}
	
	public void setGraphButtonEnable(boolean b) {
		addGraphButton.setEnabled(b);
	}
	
	public void setConvertButtonEnable(boolean b) {
		convertButton.setEnabled(b);
	}
	
	public void activateSpecFileName(String s) {
		specFileName.setText(s);
		addSpecificationButton.setVisible(false);
		specFileName.setVisible(true);
	}
	
	public void activateGraphFileName(String s) {
		graphFileName.setText(s);
		addGraphButton.setVisible(false);
		graphFileName.setVisible(true);
	}
	
	public void initSpecFileName() {
		addSpecificationButton.setVisible(true);
		specFileName.setVisible(false);
	}
	
	public void initGraphFileName() {
		addGraphButton.setVisible(true);
		graphFileName.setVisible(false);
	}
	
	public void initCwlGraphTextArea() {
		cwlGraph.setText("Follow these steps to convert the graph:\n\n1. Add a specifications file\n2. Add a graph file complying with the previous specification\n3. Click on Convert button");
	}
	
	public void setCwlGraphText(String s) {
		cwlGraph.setText(s);
	}
}
