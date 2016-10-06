package experiment;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class IntBoard {
	private static final int MAX_BOARD_SIZE = 50; 
	private Map<BoardCell, Set<BoardCell>> myMap;
	private Set<BoardCell> visited;
	private Set<BoardCell> targets; 
	private BoardCell [][] grid = new BoardCell [MAX_BOARD_SIZE][MAX_BOARD_SIZE];
	private static int myRow = 4;
	private static int myCol = 4;
	private static IntBoard theBoard = new IntBoard();

	private String boardConfigFile = null;
	private String roomConfigFile= null;
	private Map<Character, String> rooms;
	
	private IntBoard() {
		for (int i = 0; i < MAX_BOARD_SIZE; i++) {
			for (int j = 0; j < MAX_BOARD_SIZE; j++) {
				grid[i][j] = new BoardCell(i,j);
			}
		}
		visited = new HashSet<BoardCell> ();
		targets = new HashSet<BoardCell> ();
	}

	public static IntBoard getInstance() {
		return theBoard;
	}
	
	public void calcAdjacencies() {
		myMap = new HashMap<BoardCell, Set<BoardCell>> ();
		for (int i = 0; i < myRow; i++) {
			for (int j = 0; j < myCol; j++) {
				Set<BoardCell> temp = new HashSet<BoardCell> ();
				if (i+1 < myRow) 
				temp.add(grid[i+1][j]);
				if (j+1 < myCol) {
				temp.add(grid[i][j+1]);
				}
				if (i-1 >= 0)
				temp.add(grid[i-1][j]);
				if (j-1 >= 0)
				temp.add(grid[i][j-1]);
				myMap.put(grid[i][j], temp);
			}
		}
	}
	
	public void calcTargets(BoardCell startCell, int pathLength) {
		visited.add(startCell);
		for (BoardCell b : myMap.get(startCell)) {
			if (!(visited.contains(b))) {
				visited.add(b);
				if (pathLength == 1) {
					targets.add(b);
				}
				else {
					calcTargets(b, pathLength-1);
				}
				visited.remove(b);
			}	
		}
	}
	
	public Map<Character, String> getRooms() {
		return rooms;
	}


	public Set<BoardCell> getTargets() {
		
		return targets;
	}
	
	public Set<BoardCell> getAdjList(BoardCell cell) {
		
		return myMap.get(cell);
	}
	
	public BoardCell getCellAt(int num1, int num2) {
		return grid[num1][num2];
	}
	public static int getMyRow() {
		return myRow;
	}
	public static int getMyCol() {
		return myCol;
	}
	public void setConfigFiles(String layout, String legend){
		boardConfigFile = layout;
		roomConfigFile = legend;
	}


	public void loadRoomconfig() throws FileNotFoundException{
		FileReader legend= new FileReader(roomConfigFile);
		Scanner in = new Scanner(legend).useDelimiter(",");
		rooms = new HashMap<Character, String>();
		while (in.hasNext()){
			String line = in.next();
			
		}
		
		
		//Character c = line.;
		
		
	}
	public void loadBoardconfig() throws FileNotFoundException{
		FileReader layout = new FileReader(boardConfigFile);
		Scanner in = new Scanner(layout);
		int rowcount = 0;
		while (in.hasNextLine()){
			String[] result = in.nextLine().split(",");
			for(int j=0; j< result.length; j++ ){
				char temp = result[j].charAt(0);
				grid[rowcount][j].setInitial(temp);
				System.out.println(temp);
			}
			rowcount++;
			
		}
	}
	public void initialize() throws BadConfigFormatException {
		try{
			loadRoomconfig();
			loadBoardconfig();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}
