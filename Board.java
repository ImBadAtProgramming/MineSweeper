package Minesweeper;

	import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;

	import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

	public class Board implements GameEvent {

		private static final int height = 16;
		private static final int width = 16;
		
		static Cell cells[][] = new Cell[width][height];

		public void createFrame() throws IOException {
			// TODO Auto-generated method stub
			JPanel panel = new JPanel(new GridBagLayout());
	        panel.setBackground(Color.white);

			JFrame frame = new JFrame("cellTester");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			GridBagConstraints c = new GridBagConstraints();
			c.insets = new Insets(1,1,1,1);
			
			CellPic cellPic = new CellPic();
			c.gridx = 0;
			c.gridy = 0;
			JLabel flags = new JLabel("flags");
			panel.add(flags, c);
			
			for (int y = 0; y < 16; y++) {
				c.gridy++;

				for (int x = 0; x < 16; x++) {
					c.gridx = x;
					
					cells[x][y] = new Cell(cellPic, this);
					panel.add(cells[x][y], c);
				}
			}
			
			
			
			for (int h = 0; h < 45; h++) {
				
				int x = (int) (16 * Math.random());
				int y = (int) (16 * Math.random());
				cells[x][y].setMine();
				
			}
			
			frame.add(panel);
			frame.pack();
		    frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			
		}

		@Override
		public void hitMine() {
			// TODO Auto-generated method stub
			System.out.println("ur ded");
		}
	}

