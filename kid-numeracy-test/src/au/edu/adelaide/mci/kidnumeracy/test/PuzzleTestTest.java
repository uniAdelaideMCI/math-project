package au.edu.adelaide.mci.kidnumeracy.test;

import android.test.AndroidTestCase;
import au.edu.adelaide.mci.kidnumeracy.PuzzleTest;

public class PuzzleTestTest extends AndroidTestCase {
	PuzzleTest puzzleTest = new PuzzleTest();
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
	}
	
	public void testGetNumCount(){
		int numCount = puzzleTest.getNumCount();
		assertEquals(9, numCount);
	}
	
}
