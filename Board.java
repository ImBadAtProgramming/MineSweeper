package Minesweeper;

	import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;

	import javax.swing.JFrame;
import javax.swing.JPanel;

	public class Board {

		public static void main(String args[]) throws IOException {
			
			createFrame();
		}

		private static void createFrame() throws IOException {
			// TODO Auto-generated method stub
			JPanel panel = new JPanel(new GridBagLayout());
	        panel.setBackground(Color.black);

			JFrame frame = new JFrame("cellTester");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			GridBagConstraints c = new GridBagConstraints();
			c.insets = new Insets(1,1,1,1);
			
			for (int x = 0; x < 16; x++) {
				c.gridx = x;
				
				for (int y = 0; y < 16; y++) {
					c.gridy = y;
					panel.add(new Cell(false), c);
				}
			}
			frame.add(panel);
			frame.pack();
		    frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			
		}
	}

