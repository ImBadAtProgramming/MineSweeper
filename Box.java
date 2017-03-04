package Minesweeper;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JComponent;

class Box extends JComponent implements MouseListener {
	
	private static final long serialVersionUID = 1L;
	private Color calla;
	private boolean mine = false;
    
	public Box() { }
    public Box(int width, int height, Color calla)
    {
        super();
        this.setPreferredSize(new Dimension(width, height));
        this.calla = calla;
        addMouseListener(this);
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(calla);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
    
    @Override
    public void mouseClicked(MouseEvent arg0) {

    }

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
    	calla = Color.GREEN;
    	repaint();
    	
    	
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
    	calla = Color.yellow;
    	repaint();
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		 
	}
}
