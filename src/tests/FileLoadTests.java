package tests;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import experiment.BoardCell;
import experiment.DoorDirection;
import experiment.IntBoard;

public class FileLoadTests {

public IntBoard board;
public Map<Character, String> legend;
	
	@Before
	public void initIntBoard() {
		board = IntBoard.getInstance();
		board.calcAdjacencies();
		legend = board.getLegend();
	}
	@Test
	public void testNumberofRooms() {
		assertEquals("Concert Hall", legend.get('C'));
		assertEquals("Kitchen", legend.get('K'));
		assertEquals("Living room", legend.get('L'));
		assertEquals("Closet", legend.get('X'));
		assertEquals("Walkway", legend.get('W'));
		assertEquals(9, legend.size());
	}
	
	@Test
	public void DoorDirections() {
		BoardCell room = board.getCellAt(6, 1);
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.DOWN, room.getDoorDirection());
		room = board.getCellAt(2, 18);
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.LEFT, room.getDoorDirection());
		room = board.getCellAt(9,5);
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.RIGHT, room.getDoorDirection());
		room = board.getCellAt(16, 12);
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.UP, room.getDoorDirection());
		// Test that room pieces that aren't doors know it
		room = board.getCellAt(0, 0);
		assertFalse(room.isDoorway());	
		// Test that walkways are not doors
		BoardCell cell = board.getCellAt(5, 3);
		assertFalse(cell.isDoorway());		
	}

	
	@Test
	public void testCols_Rows() {
		assertEquals(23, board.getNumRows());
		assertEquals(23, board.getNumColumns());
	}
	
	@Test
	public void testNumDoors() {
		int count = 0;
		for(int i = 0; i < board.getNumRows(); i++){
			for(int j = 0; j < board.getNumColumns(); j++){
				if(board.getCellAt(i, j).isDoorway() == true){
					count ++;
				}
			}
			
		}
		assertEquals(24, count);
		
	}
	@Test
	public void testRoomInitials() {
		// Test first cell in room
		assertEquals('C', board.getCellAt(18, 9).getInitial());
		assertEquals('G', board.getCellAt(17, 19).getInitial());
		assertEquals('B', board.getCellAt(6, 20).getInitial());
		// Test last cell in room
		assertEquals('D', board.getCellAt(7, 1).getInitial());
		assertEquals('K', board.getCellAt(0, 0).getInitial());
		// Test a walkway
		assertEquals('W', board.getCellAt(0, 3).getInitial());
		// Test the closet
		assertEquals('T', board.getCellAt(16,0).getInitial());
	}
	
	
	
	
	
	

}
