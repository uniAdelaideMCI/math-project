package au.edu.adelaide.mci.kidnumeracy;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @author Yun
 *
 */
public class AddLearning {
	
	private int operand1;
	
	private int operand2;
	
	private int result;
	
	private Random random;
	
	public AddLearning(){
		random = new Random(System.currentTimeMillis());
	}
	
	private Set<AddListener> addListeners = new HashSet<AddListener>();
	
	public void addAddListener(AddListener addListener){
		addListeners.add(addListener);
	}
	
	private void fireAddEvent(){
		for (AddListener addListener : addListeners) {
			addListener.add();
		}
	}
	
	public int add(){
		operand1 = random.nextInt(19) + 1 ;
		//operand2 =  %(19 - operand1)
		operand2 = random.nextInt(20 - operand1) + 1;
		result = operand1 + operand2;
		fireAddEvent();
		return result;
	}
}
