package au.edu.adelaide.mci.kidnumeracy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @author Yun
 *
 */
public class PuzzleTest {
	private Random random = new Random();
	private int columnCount = 3;
	//missing numbers per row
	private int missingCount = 1;
	//the current missing number index
	private int currentMissIndex = -1;
	private int rowCount = 3;
	private int[] nums;
	private int[] missingNums;
	private int[] answeredNums;
	
	public int getCurrentMissIndex() {
		return currentMissIndex;
	}


	
	public PuzzleTest(){
		int numCount = getNumCount();
		nums = new int[numCount];
		answeredNums = new int[3];
		Arrays.fill(answeredNums, -1);
		for (int i = 0; i < nums.length; i++) {
			nums[i] = i + 1;
		}
		
		missingNums = new int[rowCount];
		//for each row
		for (int i = 0; i < missingNums.length; i++) {
			Set<Integer> missingCols = new HashSet<Integer>();
			for(int j = 1 ; j <= missingCount ; j++){
				int colNo = random.nextInt(columnCount);
				if (!missingCols.contains(colNo)){
					missingNums[i] = getNum(i,colNo);
				}
			}
		}		
	}
	
	public int getNumCount() {
		return columnCount * rowCount;
	}
	
	/**
	 * get missing indices 
	 * @return
	 */
	public int[] getMissingNumByRow(){
		return missingNums;
	}
	
	public int getNum(int rowNo, int colNo) {
		return nums[rowNo * columnCount + colNo];
	}
	
	
	/**
	 * @param value
	 * @return
	 */
	public int getRowIndexByValue(int value){
		int index = value - 1;
		int rowNo = index / columnCount;
		return rowNo;
	}
	
	public int getColIndexByValue(int value){
		int index = value - 1;
		int colNo = index % columnCount;
		return colNo;
	}
	
	public int nextMissingNum(){
		return missingNums[++currentMissIndex];
	}

	/**
	 * answer the current missing number
	 * @param num
	 */
	public boolean answer(int num) {
		answeredNums[currentMissIndex] = num;
		if (num == missingNums[currentMissIndex]){
			return true;
		}else{
			return false;
		}
	}



	/**
	 * @param num
	 * @return
	 */
	public boolean isAnswered(int num) {
		for (int answer : answeredNums) {
			if (answer == num){
				return true;
			}
		}
		return false;
	}



	public int getMissingNum(int index) {
		return missingNums[index];
	}
	
}
