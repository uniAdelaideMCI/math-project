package au.edu.adelaide.mci.kidnumeracy.test;

import junit.framework.TestCase;
import au.edu.adelaide.mci.kidnumeracy.PuzzleTest;

public class PuzzleTestTest extends TestCase {
	PuzzleTest puzzleTest = new PuzzleTest();
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	public void testGetNumCount(){
		int numCount = puzzleTest.getNumCount();
		assertEquals(9, numCount);
	}
	
	public void testGetMissingNumByRow(){
		int missingNums[] = puzzleTest.getMissingNumByRow();
		assertEquals(3,missingNums.length);
		
	}
}
