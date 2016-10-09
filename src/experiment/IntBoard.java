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
	private static int myRow;
	private static int myCol;
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

	public void calcTargets(int row, int col, int pathLength) {
		visited.add(grid[row][col]);
		for (BoardCell b : myMap.get(grid[row][col])) {
			if (!(visited.contains(b))) {
				visited.add(b);
				if (pathLength == 1) {
					targets.add(b);
				}
				else {
					calcTargets(b.getRow(), b.getColumn(), pathLength-1);
				}
				visited.remove(b);
			}	
		}
	}

	public Map<Character, String> getLegend() {
		return rooms;
	}


	public Set<BoardCell> getTargets() {

		return targets;
	}

	public Set<BoardCell> getAdjList(int row, int col) {

		return myMap.get(grid[row][col]);
	}

	public BoardCell getCellAt(int num1, int num2) {
		return grid[num1][num2];
	}

	public static int getNumRows() {
		return myRow;
	}
	public static int getNumColumns() {
		return myCol;
	}

	public void setConfigFiles(String layout, String legend){
		boardConfigFile = layout;
		roomConfigFile = legend;
	}


	public void loadRoomConfig() throws FileNotFoundException, BadConfigFormatException{
		FileReader legend = new FileReader(roomConfigFile);
		Scanner in = new Scanner(legend);
		rooms = new HashMap<Character, String>();
		while (in.hasNextLine()) {
			String line = in.nextLine();
			String result[] = line.split(", ");
			String part1 = result[0];
			char temp = part1.charAt(0);
			String part2 = result[1];
			String part3 = result[2];
			if ((!part3.equals("Card")) && (!part3.equals("Other"))){
				throw new BadConfigFormatException();
			}
			rooms.put(temp, part2);
		}
	}
	public void loadBoardConfig() throws FileNotFoundException, BadConfigFormatException {
		FileReader layout = new FileReader(boardConfigFile);
		Scanner in = new Scanner(layout);
		int rowCount = 0;
		int count1 = 0;
		int count2 = 0;
		while (in.hasNextLine()){
			String[] result = in.nextLine().split(",");
			count2 = result.length;
			if(rowCount == 0){
				count1 = count2;
			}
			if(count1 != count2){
				throw new BadConfigFormatException();
				
			}
			for(int j=0; j< result.length; j++){
				if (result[j].length() > 1) {
					if (result[j].charAt(1) != 'N') {
						grid[rowCount][j].setDoorDirection(result[j].charAt(1));
					}
				}
				char temp = result[j].charAt(0);
				if (rooms.containsKey(temp) == false) {
					throw new BadConfigFormatException(String.valueOf(temp));
				}
				grid[rowCount][j].setInitial(temp);
				myCol = result.length;
			}
			rowCount++;
		}
		myRow = rowCount;
	}
	public void initialize() {
		try {
			loadRoomConfig();
			loadBoardConfig();
		}catch (Exception b) {
			
		}

	}
}
