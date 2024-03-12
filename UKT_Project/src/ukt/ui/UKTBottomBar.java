package ukt.ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToolBar;

public class UKTBottomBar extends JToolBar {
	
	private JFrame parent;
	
	public UKTBottomBar(JFrame p) {
		parent = p;
		
		this.setFloatable(false);
	
		add(new JButton("Generate CWL"));
	}
}
