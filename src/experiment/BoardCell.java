package experiment;

public class BoardCell{
	private int row;
	private int column;
	private char initial;
	private DoorDirection door;
	
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
	public DoorDirection getDirection(){
		return door;
	}
	public boolean isWalkway(){
		return false;
	}
	public boolean isRoom(){
		return false;
	}
	public boolean isDoorway(){
		return false;
	}
	
}

