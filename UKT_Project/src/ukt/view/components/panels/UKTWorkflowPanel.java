package ukt.view.components.panels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

import ukt.controller.Command;
import ukt.controller.UKTController;

public class UKTWorkflowPanel extends JPanel {

	private UKTController controller;

	// Left panel components
	private JPanel northPanel;
	private JButton addFirstProcessButton;
	private JButton addSecondProcessButton;
	private JLabel bc1Label, bc2Label;
	private JButton resetButton, mergeButton;
	private JScrollPane scrollPaneCWLText, scrollPaneCWLResult;

	// Right panel components
	private JTextArea cwlText;
	private JToolBar cwlToolBar;
	private JButton runButton;
	private JTextArea cwlResult;
	private JPanel topRightPanel;
	private JSplitPane cwlSplitPane;

	public UKTWorkflowPanel(UKTController controller) {

		this.controller = controller;

		setLayout(new BorderLayout());

		// North panel
		northPanel = new JPanel();
		northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));

		JPanel firstPanel = new JPanel(new BorderLayout());
		JPanel bc1Panel = new JPanel(new FlowLayout());
		addFirstProcessButton = new JButton("Add CWL file");
		addFirstProcessButton.addActionListener((ActionEvent e) -> {
			controller.handleCommand(Command.ADD_BASECOMMAND1_WORKFLOW);
		});
		bc1Panel.add(new JLabel("CommandLineTool/ExpressionTool file:"));
		bc1Panel.add(addFirstProcessButton);
		bc1Label = new JLabel("");
		bc1Label.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 13));
		bc1Label.setVisible(false);
		bc1Panel.add(bc1Label);
		firstPanel.add(bc1Panel, BorderLayout.WEST);
		firstPanel.add(Box.createHorizontalGlue(), BorderLayout.CENTER);
		northPanel.add(firstPanel);

		JPanel secondPanel = new JPanel(new BorderLayout());
		JPanel bc2Panel = new JPanel(new FlowLayout());
		addSecondProcessButton = new JButton("Add CWL file");
		addSecondProcessButton.addActionListener((ActionEvent e) -> {
			controller.handleCommand(Command.ADD_BASECOMMAND2_WORKFLOW);
		});
		bc2Panel.add(new JLabel("CommandLineTool/ExpressionTool file:"));
		bc2Panel.add(addSecondProcessButton);
		bc2Label = new JLabel("");
		bc2Label.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 13));
		bc2Label.setVisible(false);
		bc2Panel.add(bc2Label);
		secondPanel.add(bc2Panel, BorderLayout.WEST);
		secondPanel.add(Box.createHorizontalGlue(), BorderLayout.CENTER);
		northPanel.add(secondPanel);

		JPanel errorPanel = new JPanel(new BorderLayout());
		errorPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		resetButton = new JButton("Reset all");
		resetButton.setEnabled(false);
		resetButton.addActionListener((ActionEvent e) -> {
			controller.handleCommand(Command.RESET_WORKFLOW_VIEW);
		});
		mergeButton = new JButton("Merge");
		mergeButton.setEnabled(false);
		mergeButton.addActionListener((ActionEvent e) -> {
			controller.handleCommand(Command.MERGE_BASECOMMAND);
		});
		errorPanel.add(Box.createHorizontalGlue(), BorderLayout.CENTER);
		JPanel panelButton = new JPanel(new FlowLayout());
		panelButton.add(resetButton);
		panelButton.add(mergeButton);
		errorPanel.add(panelButton, BorderLayout.EAST);
		northPanel.add(errorPanel);

		// Left panel
		topRightPanel = new JPanel(new BorderLayout());

		cwlToolBar = new JToolBar();
		cwlToolBar.setLayout(new BorderLayout());
		cwlToolBar.setFloatable(false);
		runButton = new JButton("Run");
		runButton.setEnabled(false);
		runButton.addActionListener((ActionEvent e) -> {
			controller.handleCommand(Command.RUN_WORKFLOW);
		});
		cwlToolBar.add(runButton, BorderLayout.EAST);

		cwlText = new JTextArea();
		scrollPaneCWLText = new JScrollPane(cwlText);
		initCwlText();
		cwlText.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		cwlText.setEditable(false);

		topRightPanel.add(scrollPaneCWLText, BorderLayout.CENTER);
		topRightPanel.add(cwlToolBar, BorderLayout.NORTH);

		cwlResult = new JTextArea();
		scrollPaneCWLResult = new JScrollPane(cwlResult);
		initCwlResult();
		cwlResult.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		cwlResult.setEditable(false);

		cwlSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, topRightPanel, scrollPaneCWLResult);

		add(northPanel, BorderLayout.NORTH);
		add(cwlSplitPane, BorderLayout.CENTER);
	}

	public void initCwlText() {
		cwlText.setText(
				"Follow these steps to create a workflow:\n\n1. Add no more than two CommandLineTool or one CommandLineTool with ExpressionTool.\n2. Click on Merge button\n3. Click on Run button");
	}

	public void initCwlResult() {
		cwlResult.setText("No results yet. Create a workflow before running it.");
	}
		
	public void setResetButtonEnable(boolean b) {
		resetButton.setEnabled(b);
	}
	
	public void setMergeButtonEnable(boolean b) {
		mergeButton.setEnabled(b);
	}
	
	public void setRunButtonEnable(boolean b) {
		runButton.setEnabled(b);
	}
	
	public void activateBC1FileName(String s) {
		bc1Label.setText(s);
		addFirstProcessButton.setVisible(false);
		bc1Label.setVisible(true);
	}
	
	public void activateBC2FileName(String s) {
		bc2Label.setText(s);
		addSecondProcessButton.setVisible(false);
		bc2Label.setVisible(true);
	}
	
	public void initBC1FileName() {
		addFirstProcessButton.setVisible(true);
		bc1Label.setVisible(false);
	}
	
	public void initBC2FileName() {
		addSecondProcessButton.setVisible(true);
		bc2Label.setVisible(false);
	}
		
	public void setCwlText(String s) {
		cwlText.setText(s);
	}
	
	public void setCwlResult(String s) {
		cwlResult.setText(s);
	}
}
