package Minesweeper;

import java.awt.Image;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CellPic {
	
	private Image cover;
	private Image flag;
	private Image empty;
	private Image one;
	private Image two;
	private Image three;
	private Image four;
	private Image five;
	private Image mine;
	
	enum ImgType {
		COVER,
		FLAG,
		EMPTY,
		ONE,
		TWO,
		THREE,
		FOUR,
		FIVE,
		MINE
	}
	
	public CellPic() throws IOException {
		
		this.cover = ImageIO.read(new FileInputStream("Images/cover.png"));
		this.flag = ImageIO.read(new FileInputStream("Images/flag.png"));
		this.empty = ImageIO.read(new FileInputStream("Images/empty.png"));
		this.one = ImageIO.read(new FileInputStream("Images/one.png"));
		this.two = ImageIO.read(new FileInputStream("Images/two.png"));
		this.three = ImageIO.read(new FileInputStream("Images/three.png"));
		this.four = ImageIO.read(new FileInputStream("Images/four.png"));
		this.five = ImageIO.read(new FileInputStream("Images/five.png"));
		this.mine = ImageIO.read(new FileInputStream("Images/mine.png"));
	}
	
	public Image getImage(ImgType imgType) {
		switch (imgType) {
		case COVER: 
			return cover;
		case FLAG:
			return flag;
		case EMPTY:
			return empty;
		case MINE:
			return mine;
		case FIVE:
			return five;
		case FOUR:
			return four;
		case ONE:
			return one;
		case THREE:
			return three;
		case TWO:
			return two;
		}
		return null;
	}
}

