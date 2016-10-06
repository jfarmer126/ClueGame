package experiment;

public class BoardCell{
	private int row;
	private int column;
	private char initial;
	private DoorDirection door = DoorDirection.NONE;

	public BoardCell(int row, int column) {
		this.row = row;
		this.column = column;
	}
	public void setInitial(char c){
		initial = c;
	}
	public char getInitial(){
		return initial;
	}
	public DoorDirection getDoorDirection(){
		return door;
	}
	public boolean isWalkway(){
		return false;
	}
	public boolean isRoom(){
		return false;
	}
	public boolean isDoorway(){
		if (door == DoorDirection.NONE) {
			return false;
		}
		return true;
	}
	public void setDoorDirection(char c) {
		if (c == 'R') {
			door = DoorDirection.RIGHT;
		}
		if (c == 'L') {
			door = DoorDirection.LEFT;
		}
		if (c == 'D') {
			door = DoorDirection.DOWN;
		}
		if (c == 'U') {
			door = DoorDirection.UP;
		}
	}
}

