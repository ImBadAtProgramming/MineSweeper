package Minesweeper;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

class Cell extends JComponent implements MouseListener {
	
	private static final long serialVersionUID = 1L;
	
	static final int height = 20;
	static final int width = 20;

	private boolean hasMine;
	private boolean hasFlag;
	private boolean covered;
	private int numMines;
	private CellPic cellPic;
	private GameEvent gameEvent;
	
	public Cell(CellPic cellPic, GameEvent gameEvent) throws IOException {

		super();
		this.setPreferredSize(new Dimension(width, height));
		this.hasMine = false;
		this.cellPic = cellPic;		
		this.gameEvent = gameEvent;
		hasFlag = false;
		covered = false;
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
		
		if (button == MouseEvent.BUTTON3) {
			hasFlag = !hasFlag;
		}
		 if (button == MouseEvent.BUTTON1) {
			 covered = !covered;
		 }
		 if (button == MouseEvent.BUTTON2) {
			 numMines = numMines + 1;
		 }
		 
		 if (hasMine) {
			 gameEvent.hitMine();
		 }
		 
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
}
