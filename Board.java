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

		public static int height = 10;
		public static int width = 10;
		public static int difficulty = 1;
		public static int size = 1;
		private JLabel flagNumLabel;
		//number of mines on the board
		private static int mineSupply = (int)(width * height * .15);
		//number of flags left to place
		public static int flagNum;
		public static JLabel informativeLabel;
		public static JButton changeDifficulty;
		public static JButton changeSize;
		public static String informativeText;
		public static String diffText = "Easy";
		public static String sizeText = "Small";
		static JFrame frame;
		
		static Cell cells[][];

		public void createFrame() throws IOException {
			// TODO Auto-generated method stub
			if (size == 1) {
				height = 10;
				width = 10;					
			}
			if (size == 2) {
				height = 16;
				width = 16;					
			}
			if (size == 3) {
				height = 20;
				width = 20;					
			}
			
			if (difficulty == 1) {
				mineSupply = (int)(width * height * .12);
			}
			if (difficulty == 2) {
				mineSupply = (int)(width * height * .145);
			}
			if (difficulty == 3) {
				mineSupply = (int)(width * height * .2);
			}
			flagNum = mineSupply;
			
			JPanel mainPanel = new JPanel();
			mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
			
			JPanel topPanel = new JPanel();
			topPanel.setLayout(new FlowLayout());
			topPanel.setBackground(Color.GRAY);
			
			informativeLabel = new JLabel(informativeText);
			topPanel.add(informativeLabel);
			informativeLabel.setForeground(Color.BLACK);
			
			JButton newGame = new JButton("New Game");
			topPanel.add(newGame);
			newGame.addActionListener(this);
			
			JLabel flag = new JLabel("Flags");
			topPanel.add(flag);
			flag.setForeground(Color.BLACK);
			
			flagNumLabel = new JLabel();
			topPanel.add(flagNumLabel);
			flagNumLabel.setText(Integer.toString(flagNum));
			flagNumLabel.setForeground(Color.BLACK);
			
			JPanel mineField = new JPanel(new GridBagLayout());
	        mineField.setBackground(Color.black);
			
			JPanel bottomPanel = new JPanel(new FlowLayout());
			bottomPanel.setBackground(Color.GRAY);
			
			changeDifficulty = new JButton("Difficulty");
			bottomPanel.add(changeDifficulty);
			changeDifficulty.addActionListener(this);
			
			JLabel difficulty = new JLabel(diffText);
			difficulty.setForeground(Color.BLACK);
			bottomPanel.add(difficulty);
			
			changeSize = new JButton("Size");
			bottomPanel.add(changeSize);
			changeSize.addActionListener(this);

			JLabel size = new JLabel(sizeText);
			size.setForeground(Color.BLACK);
			bottomPanel.add(size);
			
			frame = new JFrame("cellTester");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			GridBagConstraints c = new GridBagConstraints();
			c.insets = new Insets(1,1,1,1);
			
			CellPic cellPic = new CellPic();
			c.gridx = 0;
			c.gridy = 0;
	
			cells = new Cell[width][height];
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
			mainPanel.add(topPanel);
			mainPanel.add(mineField);
			mainPanel.add(bottomPanel);

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
							informativeText = "Game Won!";
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
			informativeText = "Game Over!";
			for (int x = 0; x < width; x++) {
				
				for (int y = 0; y < height; y++) {
					
					if (!cells[x][y].hasMine() && cells[x][y].hasFlag()) {
						cells[x][y].wrongFlag();
					}
				}
					
			}
			frame.repaint();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String action = e.getActionCommand();
			
			if (action.equals("Difficulty")) {
				difficulty = difficulty + 1;
				if (difficulty > 3) {
					difficulty = 1;
				}
				if (difficulty == 1) {
					System.out.println("Difficulty changed to Easy");
					diffText = "Easy";
				}
				if (difficulty == 2) {
					System.out.println("Difficulty changed to Medium");
					diffText = "Medium";
				}
				if (difficulty == 3) {
					System.out.println("Difficulty changed to Hard");
					diffText = "Hard";
				}
				frame.dispose();
				try {
					createFrame();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			if(action.equals("Size")) {
				size = size + 1;
				if (size > 3) {
					size = 1;
				}
				if (size == 1) {
					System.out.println("Size changed to Small");
					sizeText = "Small";
				}
				if (size == 2) {
					System.out.println("Size changed to Medium");
					sizeText = "Medium";				
				}
				if (size == 3) {
					System.out.println("Size changed to Large");
					sizeText = "Large";				
				}
				frame.dispose();
				try {
					createFrame();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if (action.equals("New Game")) {
				System.out.println("New Game Initiated");
				try {
					flagNum = mineSupply;
					frame.dispose();
					informativeText = "New Game Initiated";
					createFrame();
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
			// if the cell has no adjacent mines, then all cells around it are checked and uncovered
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
				
				x = baseX - 1;
				y = baseY - 1;
				if (0 <= x && x < width && 0 <= y && y < height) {
					cells[x][y].uncoverCell();
				}
				x = baseX + 1;
				y = baseY - 1;
				if (0 <= x && x < width && 0 <= y && y < height) {
					cells[x][y].uncoverCell();
				}
				x = baseX - 1;
				y = baseY + 1;
				if (0 <= x && x < width && 0 <= y && y < height) {
					cells[x][y].uncoverCell();
				}
				x = baseX + 1;
				y = baseY + 1;
				if (0 <= x && x < width && 0 <= y && y < height) {
					cells[x][y].uncoverCell();
				}
				
			}
			frame.repaint();
			return numMines;
		}
	}
	
