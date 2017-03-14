package Minesweeper;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

	public class Board implements GameEvent, ActionListener {

		static final int height = 4;
		static final int width = 4;
		private JLabel flagNumLabel;
		//number of mines on the board
		//private static int mineSupply = (int)(width * height * .2);
		private static int mineSupply = 1;
		//number of flags left to place
		public static int flagNum = mineSupply;
		public static JLabel informativeLabel;
		static JFrame frame;
		
		static Cell cells[][] = new Cell[width][height];

		public void createFrame() throws IOException {
			// TODO Auto-generated method stub
			
			JPanel mainPanel = new JPanel();
			mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
			
			JPanel topPanel = new JPanel();
			topPanel.setLayout(new FlowLayout());
			mainPanel.add(topPanel);
			
			informativeLabel = new JLabel();
			topPanel.add(informativeLabel);
			
			JButton newGame = new JButton("New Game");
			topPanel.add(newGame);
			newGame.addActionListener(this);
			
			JLabel flag = new JLabel("Flags");
			topPanel.add(flag);
			
			flagNumLabel = new JLabel();
			topPanel.add(flagNumLabel);
			flagNumLabel.setText(Integer.toString(flagNum));
			
			JPanel mineField = new JPanel(new GridBagLayout());
	        mineField.setBackground(Color.black);
			mainPanel.add(mineField);

			frame = new JFrame("cellTester");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			GridBagConstraints c = new GridBagConstraints();
			c.insets = new Insets(1,1,1,1);
			
			CellPic cellPic = new CellPic();
			c.gridx = 0;
			c.gridy = 0;
	
			
			for (int y = 0; y < height; y++) {
				c.gridy++;

				for (int x = 0; x < width; x++) {
					c.gridx = x;
					
					cells[x][y] = new Cell(x, y, cellPic, this);
					mineField.add(cells[x][y], c);
				}
			}
			
			
			
			for (int h = 0; h < mineSupply;) {
				
				int x = (int) (width * Math.random());
				int y = (int) (height * Math.random());
				
				if (!cells[x][y].hasMine()) {
					cells[x][y].setMine();
					h++;
				}
				
			}
			
			frame.add(mainPanel);
			frame.pack();
		    frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			
		}
		
		public void testIfWon() {
			
			int cell = 0;
			
			for (int x = 0; x < width; x++) {
				
				for (int y = 0; y < height; y++) {
					
					if (cells[x][y].hasFlag() && cells[x][y].hasMine() ||
							!cells[x][y].hasMine() && cells[x][y].isCovered()) {
						
						cell = cell + 1;
						
						if (cell == width * height) {
							
							System.out.println("Game Won!");
							informativeLabel.setText("Game Won!");
							frame.repaint();
						}
					}
				}
			}
		}

		@Override
		public void hitMine() {
			// TODO Auto-generated method stub
			System.out.println("GameOver");
			informativeLabel.setText("Game Over!");
			frame.repaint();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String action = e.getActionCommand();
			
				if (action.equals("New Game")) {
					System.out.println("New Game Initiated");
					try {
						flagNum = mineSupply;
						frame.dispose();
						createFrame();
						
						informativeLabel.setText("New Game Initiated");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		}        
	}

		@Override
		public void toggleMineFlag(boolean addingFlag) {
			// TODO Auto-generated method stub
			if (addingFlag) {
				flagNum = flagNum - 1;
			}
			else {
				flagNum = flagNum + 1;
			}
			flagNumLabel.setText(Integer.toString(flagNum));
		}

		@Override
		public int uncoverCell(Cell cell) {
			// TODO Auto-generated method stub
			int baseX = cell.inputX();
			int baseY = cell.inputY();
			int numMines = 0;
			// checks number of mines around the cell to tell it what number to be
			for (int j = -1; j < 2; j++) {
				int x = baseX + j;
				for (int h = -1; h < 2; h++) {
					int y = baseY + h;
					if (0 <= x && x < width && 0 <= y && y < height) {
						if (cells[x][y].checkIfMine()) {
							numMines = numMines + 1;
						}
					}
				}
			}	
			if (numMines == 0) {
				int x = baseX;
				int y = baseY;

				x = baseX + 1;
				if (0 <= x && x < width && 0 <= y && y < height) {
					cells[x][y].uncoverCell();
				}
				x = baseX - 1;
				if (0 <= x && x < width && 0 <= y && y < height) {
					cells[x][y].uncoverCell();
				}
				x = baseX;
				y = baseY + 1;
				if (0 <= x && x < width && 0 <= y && y < height) {
					cells[x][y].uncoverCell();
				}
				y = baseY - 1;
				if (0 <= x && x < width && 0 <= y && y < height) {
					cells[x][y].uncoverCell();
				}
			}
			frame.repaint();
			return numMines;
		}
	}
	
