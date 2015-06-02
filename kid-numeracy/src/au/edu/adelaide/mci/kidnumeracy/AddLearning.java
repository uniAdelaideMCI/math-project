package au.edu.adelaide.mci.kidnumeracy;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @author Yun
 *
 */
public class AddLearning implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4163033232945938323L;

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
		setOperand1(random.nextInt(9) + 1);
		//operand2 =  %(19 - operand1)
		operand2 = random.nextInt(10 - operand1) + 1;
		result = operand1 + operand2;
		fireAddEvent();
		return result;
	}

	private void setOperand1(int operand1) {
		this.operand1 = operand1;
	}

	public int getOperand1() {
		return operand1;
	}

	public int getOperand2() {
		return operand2;
	}

	public int getResult() {
		return result;
	}
}
