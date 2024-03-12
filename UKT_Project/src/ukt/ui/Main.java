package ukt.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main extends JFrame {

	public Main() {

		super("UKT");

		// Set window size
		setSize(1000, 500);

		// Make the window appear in the middle of the screen
		setLocationRelativeTo(null);

		// Exit on closing window
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// Set layout
		this.getContentPane().setLayout(new BorderLayout(10,10));

		// Set menu bar
		setJMenuBar(new UKTMenuBar(this));

		// Add center split pane
		//add(new UKTSplitPane(this), BorderLayout.CENTER);

		// Add bottom tool bar
		//add(new UKTBottomBar(this), BorderLayout.SOUTH);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new Main().setVisible(true);
			}

		});
	}

}
