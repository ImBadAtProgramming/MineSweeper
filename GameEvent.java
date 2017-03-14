package Minesweeper;

public interface GameEvent {
	
	public void hitMine();
	//call toggleMineFlag to indicate a change in the number of flags, true=+1, false=-1
	public void toggleMineFlag(boolean addingFlag);
	public int uncoverCell(Cell cell);
	public void testIfWon();
}
