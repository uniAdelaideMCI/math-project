package au.edu.adelaide.mci.kidnumeracy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * JSON Objects for a puzzle test
 * @author Group 5
 *
 */
public class PuzzleTest {
	/**
	 * @author Yun
	 *
	 */
	class PuzzleValue {
		private int rowNo;
		private int colNo;
		private int value;
		private boolean orginalMissing;
		private boolean answered = false;
		private int answeredValue;
		
		public boolean isAnsweredCorrect(){
			return value == answeredValue;
		}

		public boolean answer(int answer) {
			answeredValue = answer;
			answered = true;
			return isAnsweredCorrect();
		}

		public boolean isAnswered() {
			return answered;
		}

		public boolean isOriginalMissing() {
			return orginalMissing;
		}

		private boolean samePosition(PuzzleValue puzzleValue) {
			return puzzleValue.rowNo == rowNo && puzzleValue.colNo == colNo;
		}

		public int getValue() {
			return value;
		}

		public int getRowNo() {
			return rowNo;
		}
	}



	private Random random = new Random();
	private int rowCount = 3;
	private int columnCount = 3;
	//missing numbers per row
	private int missingCountPerRow = 1;
	private int totalMissingCount = 3;
	//the current missing number index
	private int currentMissIndex = 0;
	private PuzzleValue[] nums;
	private List<PuzzleValue> missNums = new ArrayList<PuzzleValue>();	
	
	public int getCurrentMissIndex() {
		return currentMissIndex;
	}
	
	public boolean hasMissing(){
		return currentMissIndex < missNums.size();
	}
	
	
	
	public PuzzleTest(){
		totalMissingCount = missingCountPerRow * rowCount;
		int numCount = getNumCount();
		nums = new PuzzleValue[numCount];
		for (int i = 0; i < rowCount ; i++){
			for(int j = 0 ; j < columnCount ; j++){
				int index = i*rowCount + j;
				nums[index] = new PuzzleValue();
				nums[index].rowNo = i;
				nums[index].colNo = j;
				nums[index].value = index + 1;
			}
		}
		
		//for each row
		for (int i = 0; i < rowCount ; i++) {
			Set<Integer> missingCols = new HashSet<Integer>();
			for(int j = 0 ; j < missingCountPerRow ; j++){
				int colNo = -1;
				do {
					colNo = random.nextInt(columnCount);
					missingCols.add(colNo);
				} while (!missingCols.contains(colNo));
								
				int index = i * rowCount + colNo;
				nums[index].orginalMissing = true;
				missNums.add(nums[index]);

			}
		}
		
	}
	
	public int getTotalOriginMissCount() {
		return rowCount * missingCountPerRow;
	}



	public int getNumCount() {
		return columnCount * rowCount;
	}
	
	/**
	 * get missing indices 
	 * @return
	 */
	public PuzzleValue[] getOriginalMissNums(){
		PuzzleValue[] values = new PuzzleValue[missNums.size()];
		return missNums.toArray(values);
	}
	
	public PuzzleValue getNum(int rowNo, int colNo) {
		return getNum(rowNo * columnCount + colNo);
	}
	
	public PuzzleValue getNum(int position) {
		return nums[position];
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

	public PuzzleValue nextMissingNum(){
		return missNums.get(currentMissIndex++);
	}
	
	public PuzzleValue currentMissingNum(){
		if (currentMissIndex >= missNums.size()){
			return null;
		}else{
			return missNums.get(currentMissIndex);			
		}
	}
	

	/**
	 * answer the current missing number
	 * @param num
	 */
	public boolean answer(int answer) {
		PuzzleValue puzzleValue = getCurrentMissNum();
		return puzzleValue.answer(answer);
	}
	
	public boolean answer(int position, int answer) {
		PuzzleValue puzzleValue = getNum(position);	
		return puzzleValue.answer(answer);
	}	



	private PuzzleValue getCurrentMissNum() {
		if (currentMissIndex > missNums.size())
			return null;
		return missNums.get(currentMissIndex);
	}



	/**
	 * @param puzzleValue
	 * @return
	 */
	public boolean isCurrentMissing(PuzzleValue puzzleValue) {
		if (currentMissingNum() != null && currentMissingNum().samePosition(puzzleValue)){
			return true;
		}
		return false;
	}



	public int[] getMissNumsRandom() {
		int [] result = new int[getTotalOriginMissCount()];
		int i = 0;
		for (PuzzleValue puzzleValue : missNums) {
			result[i++] = puzzleValue.getValue(); 
		}
		result = Util.randomiseArray(result);
		return result;
	}

}
