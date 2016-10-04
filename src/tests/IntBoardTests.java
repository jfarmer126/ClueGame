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
		board = new IntBoard();
		board.calcAdjacencies();
	}
	
	//tests adjacency top corner
	@Test
	public void testAdjacency0() {
		BoardCell Cell = board.getCell(0,0);
		Set<BoardCell> testList = board.getAdjList(Cell);
		assertTrue(testList.contains(board.getCell(1, 0)));
		assertTrue(testList.contains(board.getCell(0, 1)));
		assertEquals(2, testList.size());
	}
	
	//tests adjacency bottom corner
	@Test
	public void testAdjacency3() {
		BoardCell Cell = board.getCell(3,3);
		Set<BoardCell> testList = board.getAdjList(Cell);
		assertTrue(testList.contains(board.getCell(2, 3)));
		assertTrue(testList.contains(board.getCell(3, 2)));
		assertEquals(2, testList.size());
	}
	
	//tests adjacency right edge
	@Test
	public void testAdjacency1_3() {
		BoardCell Cell = board.getCell(1,3);
		Set<BoardCell> testList = board.getAdjList(Cell);
		assertTrue(testList.contains(board.getCell(0, 3)));
		assertTrue(testList.contains(board.getCell(2, 3)));
		assertTrue(testList.contains(board.getCell(1, 2)));
		assertEquals(3, testList.size());
	}
	//tests adjacency left edge
	@Test
	public void testAdjacency2_0() {
		BoardCell Cell = board.getCell(2,0);
		Set<BoardCell> testList = board.getAdjList(Cell);
		assertTrue(testList.contains(board.getCell(1, 0)));
		assertTrue(testList.contains(board.getCell(2, 1)));
		assertTrue(testList.contains(board.getCell(3, 0)));
		assertEquals(3, testList.size());
	}
	
	//tests adjacency middle 1st column
	@Test
	public void testAdjacency1_1() {
		BoardCell Cell = board.getCell(1,1);
		Set<BoardCell> testList = board.getAdjList(Cell);
		assertTrue(testList.contains(board.getCell(0, 1)));
		assertTrue(testList.contains(board.getCell(1, 0)));
		assertTrue(testList.contains(board.getCell(2, 1)));
		assertTrue(testList.contains(board.getCell(1, 2)));
		assertEquals(4, testList.size());
	}
	
	//tests adjacency middle second column
	@Test
	public void testAdjacency2_2() {
		BoardCell Cell = board.getCell(2,2);
		Set<BoardCell> testList = board.getAdjList(Cell);
		assertTrue(testList.contains(board.getCell(2, 3)));
		assertTrue(testList.contains(board.getCell(3, 2)));
		assertTrue(testList.contains(board.getCell(2, 1)));
		assertTrue(testList.contains(board.getCell(1, 2)));
		assertEquals(4, testList.size());
	}
	
	//Tests 3 steps starting from top left corner
	@Test
	public void testTargets0_3()
	{
		BoardCell cell = board.getCell(0, 0);
		board.calcTargets(cell, 3);
		Set targets = board.getTargets();
		assertEquals(6, targets.size());
		assertTrue(targets.contains(board.getCell(3, 0)));
		assertTrue(targets.contains(board.getCell(2, 1)));
		assertTrue(targets.contains(board.getCell(0, 1)));
		assertTrue(targets.contains(board.getCell(1, 2)));
		assertTrue(targets.contains(board.getCell(0, 3)));
		assertTrue(targets.contains(board.getCell(1, 0)));
	}
	
	//Tests 5 steps starting from middle left column
	@Test
	public void testTargets1_1()
	{
		BoardCell cell = board.getCell(1, 1);
		board.calcTargets(cell, 5);
		Set targets = board.getTargets();
		assertEquals(8, targets.size());
		assertTrue(targets.contains(board.getCell(0, 1)));
		assertTrue(targets.contains(board.getCell(0, 3)));
		assertTrue(targets.contains(board.getCell(1, 0)));
		assertTrue(targets.contains(board.getCell(1, 2)));
		assertTrue(targets.contains(board.getCell(2, 1)));
		assertTrue(targets.contains(board.getCell(2, 3)));
		assertTrue(targets.contains(board.getCell(3, 0)));
		assertTrue(targets.contains(board.getCell(3, 2)));
		
	}
	
	//Tests 6 steps starting from bottom left column
	@Test
	public void testTargets3_3()
	{
		BoardCell cell = board.getCell(3, 3);
		board.calcTargets(cell, 6);
		Set targets = board.getTargets();
		//assertEquals(7, targets.size());
		assertTrue(targets.contains(board.getCell(0, 0)));
		assertTrue(targets.contains(board.getCell(0, 2)));
		assertTrue(targets.contains(board.getCell(1, 1)));
		assertTrue(targets.contains(board.getCell(1, 3)));
		assertTrue(targets.contains(board.getCell(2, 0)));
		assertTrue(targets.contains(board.getCell(2, 2)));
		assertTrue(targets.contains(board.getCell(3, 1)));
		for (BoardCell t : board.getTargets())
			System.out.println(t);
	}
	
	//Tests 2 steps starting from top left corner
	@Test
	public void testTargets_Step2()
	{
		BoardCell cell = board.getCell(0, 0);
		board.calcTargets(cell, 2);
		Set targets = board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCell(0, 2)));
		assertTrue(targets.contains(board.getCell(1, 1)));
		assertTrue(targets.contains(board.getCell(2, 0)));
		
	}
	
	//Tests 4 steps starting from top left corner
	@Test
	public void testTargets_Step4()
	{
		BoardCell cell = board.getCell(0, 0);
		board.calcTargets(cell, 4);
		Set targets = board.getTargets();
		assertEquals(6, targets.size());
		assertTrue(targets.contains(board.getCell(0, 2)));
		assertTrue(targets.contains(board.getCell(1, 1)));
		assertTrue(targets.contains(board.getCell(1, 3)));
		assertTrue(targets.contains(board.getCell(2, 0)));
		assertTrue(targets.contains(board.getCell(2, 2)));
		assertTrue(targets.contains(board.getCell(3, 1)));
		
	}
	
	//Tests 1 step starting from top left corner
	@Test
	public void testTargets_Step1()
	{
		BoardCell cell = board.getCell(0, 0);
		board.calcTargets(cell, 1);
		Set targets = board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCell(0, 1)));
		assertTrue(targets.contains(board.getCell(1, 0)));
	
		
	}

}
