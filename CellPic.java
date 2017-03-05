package Minesweeper;

import java.awt.Image;
import java.io.File;
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
		
		cover = ImageIO.read(new File("Images/cover.png"));
		flag = ImageIO.read(new File("Images/flag.png"));
		empty = ImageIO.read(new File("Images/empty.png"));
		one = ImageIO.read(new File("Images/one.png"));
		two = ImageIO.read(new File("Images/two.png"));
		three = ImageIO.read(new File("Images/three.png"));
		four = ImageIO.read(new File("Images/four.png"));
		five = ImageIO.read(new File("Images/five.png"));
		mine = ImageIO.read(new File("Images/mine.png"));
		
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

