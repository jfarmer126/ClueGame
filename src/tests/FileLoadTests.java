package tests;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import experiment.BoardCell;
import experiment.DoorDirection;
import experiment.IntBoard;

//These are the our written tests to test Initialization of the board
public class FileLoadTests {

	//Initializes board
	public IntBoard board;
	
	@Before
	public void initIntBoard() {
		board = IntBoard.getInstance();
		board.setConfigFiles("data/Clue.csv", "data/legend.txt");
		board.initialize();
	}
	//Tests location of rooms and total number of rooms
	@Test
	public void testNumberofRooms() {
		Map<Character, String> legend = board.getLegend();
		assertEquals("Concert Hall", legend.get('C'));
		assertEquals("Kitchen", legend.get('K'));
		assertEquals("Living room", legend.get('L'));
		assertEquals("Closet", legend.get('X'));
		assertEquals("Walkway", legend.get('W'));
		assertEquals(11, legend.size());
	}
	//Tests that doors open in the correct location
	@Test
	public void DoorDirections() {
		BoardCell room = board.getCellAt(5, 1);
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.DOWN, room.getDoorDirection());
		room = board.getCellAt(9, 15);
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

//Tests number of rows and columns are as expected
	@Test
	public void testCols_Rows() {
		assertEquals(24, board.getNumRows());
		assertEquals(24, board.getNumColumns());
	}
//tests correct number of doors
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
		assertEquals(21, count);

	}
	//test that rooms are correctly labeled
	@Test
	public void testRoomInitials() {
		// Test first cell in room
		assertEquals('C', board.getCellAt(16, 9).getInitial());
		assertEquals('G', board.getCellAt(15, 18).getInitial());
		assertEquals('B', board.getCellAt(9, 22).getInitial());
		// Test last cell in room
		assertEquals('D', board.getCellAt(11, 3).getInitial());
		assertEquals('K', board.getCellAt(0, 0).getInitial());
		// Test a walkway
		assertEquals('W', board.getCellAt(9, 14).getInitial());
		// Test the closet
		assertEquals('T', board.getCellAt(16,5).getInitial());
	}







}
