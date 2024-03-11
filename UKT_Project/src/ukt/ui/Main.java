package ukt.ui;

import javax.swing.JFrame;

import javax.swing.SwingUtilities;

public class Main extends JFrame {

	public Main(String title) {
		super(title);
		setSize(1000, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new Main("UKT").setVisible(true);
			}

		});
	}

}
