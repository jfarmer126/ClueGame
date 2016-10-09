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

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
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
		if (initial == 'W') {
			return true;
		}
		return false;
	}
	public boolean isRoom(){
		if (initial != 'W' && isDoorway() == false) {
			return true;
		}
		else {
			return false;
		}
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

	@Override
	public String toString() {
		return row +" " + column;
	}

}

