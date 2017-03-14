package Minesweeper;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JComponent;

public class Cell extends JComponent implements MouseListener {

	private static final long serialVersionUID = 1L;
	
	static final int height = 20;
	static final int width = 20;

	private boolean allowClick;
	private boolean hasMine;
	private boolean hasFlag;
	private boolean covered;
	private boolean isEmpty;
	private int x = 0;
	private int y = 0;
	private int numMines;
	private CellPic cellPic;
	private GameEvent gameEvent;
	
	public Cell(int x, int y, CellPic cellPic, GameEvent gameEvent) throws IOException {

		super();
		this.x = x;
		this.y = y;
		this.setPreferredSize(new Dimension(width, height));
		allowClick = true;
		this.hasMine = false;
		this.cellPic = cellPic;		
		this.gameEvent = gameEvent;
		hasFlag = false;
		covered = true;
		numMines = 0;
		addMouseListener(this);
	}
	
	public Image getImage() {
		
		Image img = null;
		
		if (covered) {
			img = cellPic.getImage(CellPic.ImgType.COVER);
			
			if (hasFlag == true) {
				img = cellPic.getImage(CellPic.ImgType.FLAG);
			}
		} else {
			
			if (hasMine == true) {
				img = cellPic.getImage(CellPic.ImgType.MINE);
				return img;
			}
			if (numMines == 0) {
				img = cellPic.getImage(CellPic.ImgType.EMPTY);
			}
			if (numMines == 1) {
				img = cellPic.getImage(CellPic.ImgType.ONE);
			}
			if (numMines == 2) {
				img = cellPic.getImage(CellPic.ImgType.TWO);
			}
			if (numMines == 3) {
				img = cellPic.getImage(CellPic.ImgType.THREE);
			}
			if (numMines == 4) {
				img = cellPic.getImage(CellPic.ImgType.FOUR);
			}
			if (numMines == 5) {
				img = cellPic.getImage(CellPic.ImgType.FIVE);
			}
		}
		return img;
	}

	public boolean isCovered() {
		return !covered;
	}
	
	public boolean hasFlag() {
		return hasFlag;
	}
	
	public boolean hasMine() {
		return hasMine;
	}
	
	public void setMine() {
		hasMine = true;
	}
	
	public void paintComponent(Graphics g)
    {		
        super.paintComponent(g);
        g.drawImage(getImage(), 0, 0, null);
    }
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int button = e.getButton();
		if (allowClick == true) {
			
			if (button == MouseEvent.BUTTON3) {
				
				boolean allowed = true;
				if (Board.flagNum == 0 && !hasFlag) {
					allowed = false;
				}
				if (allowed) {
					hasFlag = !hasFlag;
					gameEvent.toggleMineFlag(hasFlag);
				}
			}
			if (button == MouseEvent.BUTTON1) {
				
				if (hasFlag) {
					//do nothing
				}
				else if (hasMine) {
					gameEvent.hitMine();
					covered = false;
					for (int x = 0; x < Board.width; x++) {
						for (int y = 0; y < Board.height; y++) {
							Board.cells[x][y].allowClick();
							if (Board.cells[x][y].checkIfMine()) {
								Board.cells[x][y].uncover();
							}
						}
					}	
				} else if (covered) {
					covered = !covered;
					numMines = gameEvent.uncoverCell(this);
				}
			}

		}
		repaint();
		gameEvent.testIfWon();
	}

	private void allowClick() {
		// TODO Auto-generated method stub
		allowClick = false;
	}

	public void uncover() {
		// TODO Auto-generated method stub
		covered = false;
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public boolean checkIfMine() {
		// TODO Auto-generated method stub
		return hasMine;
	}

	public int inputX() {
		// TODO Auto-generated method stub
		return x;
	}

	public int inputY() {
		// TODO Auto-generated method stub
		return y;
	}

	public void uncoverCell() {
		// TODO Auto-generated method stub
		if (covered) {
			this.uncover();
			numMines = gameEvent.uncoverCell(this);
			repaint();
		}
	}
}
