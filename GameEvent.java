package Minesweeper;

// this class allows the Cell class to call methods in the Board class
public interface GameEvent {
	
	// when a mine is clicked on, this method runs, whole board uncovers, incorrect flags shown
	public void hitMine();
	// call toggleMineFlag to indicate a change in the number of flags, true = ++, false = --
	public void toggleMineFlag(boolean addingFlag);
	// this simply uncovers the given cell, and checks for mines to get the correct image
	public int uncoverCell(Cell cell);
	// a method that is run after every click of the player to determine if the game is won, by checking each cell
	public void testIfWon();
}
