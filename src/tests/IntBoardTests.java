package tests;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import experiment.BoardCell;
import experiment.IntBoard;

public class IntBoardTests {

	private IntBoard board;
	@Before
	public void initIntBoard() {
		board = board.getInstance();
		board.initialize();
		board.setConfigFiles("data/Clue.csv", "data/legend.txt");
		board.calcAdjacencies();
	}

	//tests adjacency top corner

	// Ensure that player does not move around within room
	// These cells are ORANGE on the planning spreadsheet
	@Test
	public void testAdjacenciesInsideRooms()
	{
		// Test a corner
		Set<BoardCell> testList = board.getAdjList(23, 0);
		assertEquals(0, testList.size());
		// Test one that has walkway underneath
		testList = board.getAdjList(5, 17);
		assertEquals(0, testList.size());
		// Test one that has walkway above
		testList = board.getAdjList(15, 21);
		assertEquals(0, testList.size());
		// Test one that is in middle of room
		testList = board.getAdjList(2, 12);
		assertEquals(0, testList.size());
		// Test one beside a door
		testList = board.getAdjList(16, 13);
		assertEquals(0, testList.size());
		// Test one in a corner of room
		testList = board.getAdjList(1, 0);
		assertEquals(0, testList.size());
	}

	// Ensure that the adjacency list from a doorway is only the
	// walkway. NOTE: This test could be merged with door 
	// direction test. 
	// These tests are PURPLE on the planning spreadsheet
	@Test
	public void testAdjacencyRoomExit()
	{
		// TEST DOORWAY RIGHT 
		Set<BoardCell> testList = board.getAdjList(5, 15);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(5, 16)));
		// TEST DOORWAY LEFT 
		testList = board.getAdjList(12, 20);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(12, 19)));
		//TEST DOORWAY DOWN
		testList = board.getAdjList(5, 19);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(6, 19)));
		//TEST DOORWAY UP
		testList = board.getAdjList(16, 11);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(15, 11)));

	}

	// Test adjacency at entrance to rooms
	// These tests are GREEN in planning spreadsheet
	@Test
	public void testAdjacencyDoorways()
	{
		// Test beside a door direction RIGHT
		Set<BoardCell> testList = board.getAdjList(5, 16);
		assertTrue(testList.contains(board.getCellAt(5, 15)));
		assertTrue(testList.contains(board.getCellAt(4, 16)));
		assertTrue(testList.contains(board.getCellAt(6, 16)));
		assertEquals(3, testList.size());
		// Test beside a door direction DOWN
		testList = board.getAdjList(15, 1);
		assertTrue(testList.contains(board.getCellAt(14, 1)));
		assertTrue(testList.contains(board.getCellAt(15, 0)));
		assertTrue(testList.contains(board.getCellAt(15, 2)));
		assertEquals(3, testList.size());
		// Test beside a door direction LEFT
		testList = board.getAdjList(15, 16);
		assertTrue(testList.contains(board.getCellAt(14, 16)));
		assertTrue(testList.contains(board.getCellAt(16, 16)));
		assertTrue(testList.contains(board.getCellAt(15, 17)));
		assertTrue(testList.contains(board.getCellAt(15, 15)));
		assertEquals(4, testList.size());
		// Test beside a door direction UP
		testList = board.getAdjList(15, 12);
		assertTrue(testList.contains(board.getCellAt(14, 12)));
		assertTrue(testList.contains(board.getCellAt(16, 12)));
		assertTrue(testList.contains(board.getCellAt(15, 13)));
		assertTrue(testList.contains(board.getCellAt(15, 11)));
		assertEquals(4, testList.size());
	}

	// Test a variety of walkway scenarios
	// These tests are BROWN on the planning spreadsheet
	@Test
	public void testAdjacencyWalkways()
	{
		// Test on top edge of board, just one walkway piece
		Set<BoardCell> testList = board.getAdjList(0, 3);
		assertTrue(testList.contains(board.getCellAt(0, 4)));
		assertEquals(1, testList.size());

		// Test on left edge of board, three walkway pieces
		testList = board.getAdjList(8, 0);
		assertTrue(testList.contains(board.getCellAt(7, 0)));
		assertTrue(testList.contains(board.getCellAt(9, 0)));
		assertEquals(2, testList.size());

		// Test between two rooms, walkways right and left
		testList = board.getAdjList(23, 15);
		assertTrue(testList.contains(board.getCellAt(23, 16)));
		assertTrue(testList.contains(board.getCellAt(22, 15)));
		assertEquals(2, testList.size());

		// Test surrounded by 4 walkways
		testList = board.getAdjList(14,14);
		assertTrue(testList.contains(board.getCellAt(13, 14)));
		assertTrue(testList.contains(board.getCellAt(15, 14)));
		assertTrue(testList.contains(board.getCellAt(14, 15)));
		assertTrue(testList.contains(board.getCellAt(14, 13)));
		assertEquals(4, testList.size());

		// Test on bottom edge of board, next to 1 room piece
		testList = board.getAdjList(23, 7);
		assertTrue(testList.contains(board.getCellAt(22, 7)));
		
		assertEquals(1, testList.size());

		// Test on right edge of board, next to 1 room piece
		testList = board.getAdjList(14, 23);
		assertTrue(testList.contains(board.getCellAt(14, 22)));
		assertEquals(1, testList.size());

		// Test on walkway next to  door that is not in the needed
		// direction to enter
		testList = board.getAdjList(15, 6);
		assertTrue(testList.contains(board.getCellAt(15, 5)));
		assertTrue(testList.contains(board.getCellAt(15, 7)));
		assertTrue(testList.contains(board.getCellAt(14, 6)));
		assertEquals(3, testList.size());
	}


	// Tests of just walkways, 1 step, includes on edge of board
	// and beside room
	// Have already tested adjacency lists on all four edges, will
	// only test two edges here
	// These are LIGHT BLUE on the planning spreadsheet
	@Test
	public void testTargetsOneStep() {
		board.calcTargets(6, 23, 1);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(1, targets.size());
		assertTrue(targets.contains(board.getCellAt(6, 22)));	

		board.calcTargets(15, 0, 1);
		targets= board.getTargets();
		assertEquals(1, targets.size());
		assertTrue(targets.contains(board.getCellAt(15, 1)));			
	}

	// Tests of just walkways, 2 steps
	// These are LIGHT BLUE on the planning spreadsheet
	@Test
	public void testTargetsTwoSteps() {
		board.calcTargets(6, 23, 2);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCellAt(6, 21)));
		assertTrue(targets.contains(board.getCellAt(7, 22)));

		board.calcTargets(15, 0, 2);
		targets= board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCellAt(14, 1)));	
		assertTrue(targets.contains(board.getCellAt(15, 2)));			
	}

	// Tests of just walkways, 4 steps
	// These are LIGHT BLUE on the planning spreadsheet
	@Test
	public void testTargetsFourSteps() {
		board.calcTargets(6, 23, 4);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCellAt(7, 22)));
		assertTrue(targets.contains(board.getCellAt(6, 19)));
		

		// Includes a path that doesn't have enough length
		board.calcTargets(0, 6, 4);
		targets= board.getTargets();
		assertEquals(1, targets.size());
		assertTrue(targets.contains(board.getCellAt(2, 8)));
	}	

	// Tests of just walkways plus one door, 6 steps
	// These are LIGHT BLUE on the planning spreadsheet

	@Test
	public void testTargetsSixSteps() {
		board.calcTargets(10, 6, 6);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(8, targets.size());
		assertTrue(targets.contains(board.getCellAt(5, 7)));
		assertTrue(targets.contains(board.getCellAt(5, 5)));	
		assertTrue(targets.contains(board.getCellAt(9, 5)));	
		assertTrue(targets.contains(board.getCellAt(6, 8)));	
		assertTrue(targets.contains(board.getCellAt(6, 4)));	
		assertTrue(targets.contains(board.getCellAt(14, 8)));	
		assertTrue(targets.contains(board.getCellAt(15, 7)));	
		assertTrue(targets.contains(board.getCellAt(15, 5)));	
	}	
	

	// Test getting into a room
	// These are LIGHT BLUE on the planning spreadsheet

	@Test 
	public void testTargetsIntoRoom()
	{
		// One room is exactly 2 away
		board.calcTargets(2, 16, 2);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(3, targets.size());
		// directly left (can't go right 2 steps)
		assertTrue(targets.contains(board.getCellAt(2, 18)));
		// directly up and down
		assertTrue(targets.contains(board.getCellAt(0, 16)));
		assertTrue(targets.contains(board.getCellAt(4, 16)));
	}

	// Test getting into room, doesn't require all steps
	// These are LIGHT BLUE on the planning spreadsheet
	@Test
	public void testTargetsIntoRoomShortcut() 
	{
		board.calcTargets(16, 8, 3);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(8, targets.size());
		// directly up and down
		assertTrue(targets.contains(board.getCellAt(15, 8)));
		assertTrue(targets.contains(board.getCellAt(17, 8)));
		// directly left (can't go right)
		assertTrue(targets.contains(board.getCellAt(16, 7)));
		// left then down
		assertTrue(targets.contains(board.getCellAt(18, 7)));
		assertTrue(targets.contains(board.getCellAt(15, 10)));
		// up then left/right
		assertTrue(targets.contains(board.getCellAt(14, 7)));
		assertTrue(targets.contains(board.getCellAt(14, 9)));
		// into the rooms
		assertTrue(targets.contains(board.getCellAt(16, 6)));	
	}

	// Test getting out of a room
	// These are LIGHT BLUE on the planning spreadsheet
	@Test
	public void testRoomExit()
	{
		// Take one step, essentially just the adj list
		board.calcTargets(19, 17, 1);
		Set<BoardCell> targets= board.getTargets();
		// Ensure doesn't exit through the wall
		assertEquals(1, targets.size());
		assertTrue(targets.contains(board.getCellAt(19, 16)));
		// Take two steps
		board.calcTargets(19, 17, 2);
		targets= board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCellAt(18, 16)));
		assertTrue(targets.contains(board.getCellAt(19, 15)));
		assertTrue(targets.contains(board.getCellAt(20, 16)));
	}

}
