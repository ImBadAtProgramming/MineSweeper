package Minesweeper;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MineSweeperTest {

	public static void main(String args[]) {
		
		createFrame();
	}

	private static void createFrame() {
		// TODO Auto-generated method stub
		JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.black);

		JFrame frame = new JFrame("sweepGame");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(2,2,2,2);
		
		for (int x = 0; x < 25; x++) {
			c.gridx = x;
			
			for (int y = 0; y < 25; y++) {
				c.gridy = y;
				panel.add(new Box(10,10, Color.MAGENTA), c);
			}
		}
		frame.add(panel);
		frame.pack();
	    frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}
}


