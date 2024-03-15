package ukt.view.components;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

public class UKTSplitPane extends JSplitPane {
	
	private JFrame parent;
	
	public UKTSplitPane(JFrame p) {
		
		parent = p;
		
		// Orientation
		orientation = HORIZONTAL_SPLIT;
		
		// Left component
		JTextArea area1 = new JTextArea();
		area1.setEnabled(false);
		setLeftComponent(area1);
		
		// Right component
		JTextArea area2 = new JTextArea();
		area2.setEnabled(false);
		setRightComponent(area2);
		
	}
}
