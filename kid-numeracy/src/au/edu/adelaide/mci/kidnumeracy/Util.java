/**
 * 
 */
package au.edu.adelaide.mci.kidnumeracy;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Yun
 *
 */
public class Util {
	private static Random random = new Random(System.currentTimeMillis());

	/**
	 * return an array that is ordered randomly
	 * @param nums
	 * @return
	 */
	public static int[] randomiseArray(int[] nums) {
		int[] result = new int[nums.length];
		ArrayList<Integer> indices = new ArrayList<Integer>();
		for (int i= 0 ; i < nums.length ; i++ ) {
			indices.add(i);
		}
		//randomly choose one index from old array
		for (int i = 0; i < result.length; i++) {
			int index = random.nextInt(indices.size());//index in the array list
			int oldIndex = indices.remove(index); //index in the original array
			result[i] = nums[oldIndex];
		}
		return result;
	}

}
