package experiment;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class IntBoard {
	private Map<BoardCell, Set<BoardCell>> myMap;
	private Set<BoardCell> visited;
	private Set<BoardCell> targets; 
	private BoardCell [][] grid = new BoardCell [4][4];
	public final static int myRow = 4;
	public final static int myCol = 4;
	
	public IntBoard() {
		for (int i = 0; i < myRow; i++) {
			for (int j = 0; j < myCol; j++) {
				grid[i][j] = new BoardCell(i,j);
			}
		}
		visited = new HashSet<BoardCell> ();
		targets = new HashSet<BoardCell> ();
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
	
	public Set<BoardCell> getTargets() {
		
		return targets;
	}
	
	public Set<BoardCell> getAdjList(BoardCell cell) {
		
		return myMap.get(cell);
	}
	
	public BoardCell getCell(int num1, int num2) {
		return grid[num1][num2];
	}
	
}
