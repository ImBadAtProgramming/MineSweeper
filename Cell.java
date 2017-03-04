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
	private Image cover;
	private Image flag;
	public Cell(boolean hasMine) throws IOException {

		super();
		this.setPreferredSize(new Dimension(width, height));
		this.hasMine = hasMine;
		hasFlag = false;
		covered = true;
		numMines = 0;
		addMouseListener(this);
		
		cover = ImageIO.read(new File("Images/cover.png"));
		flag = ImageIO.read(new File("Images/flag.png"));
	}
	
	public Image getImage() {
		
		Image img = null;
		
		if (covered == true) {
			img = cover;
			
			if (hasFlag == true) {
				img = flag;
			}
		}
		
		return img;
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
