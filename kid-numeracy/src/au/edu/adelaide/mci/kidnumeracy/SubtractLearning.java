package au.edu.adelaide.mci.kidnumeracy;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class SubtractLearning implements Serializable {

	private static final long serialVersionUID = 6248898134992698449L;

	private int operand1;
	
	private int operand2;
	
	private int result;
	
	private Random random;
	
	private Set<SubtractListener> subtractListeners = new HashSet<SubtractListener>();
	
	public SubtractLearning() {
		random = new Random(System.currentTimeMillis());
	}

	public void addSubtractListener(SubtractListener subtractListener){
		subtractListeners.add(subtractListener);
	}
	
	private void fireSubtractEvent(){
		for (SubtractListener subtractListener : subtractListeners) {
			subtractListener.subtract();
		}
	}
	
	public int subtract(){
		operand2 = random.nextInt(19) + 1 ;
		//operand2 =  %(19 - operand1)
		result = random.nextInt(20 - operand2) + 1;
		operand1 = operand2 + result;
		fireSubtractEvent();
		return result;		
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
