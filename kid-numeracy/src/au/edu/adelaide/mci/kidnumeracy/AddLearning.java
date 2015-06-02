package au.edu.adelaide.mci.kidnumeracy;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import au.edu.adelaide.mci.kidnumeracy.model.Equation;
import au.edu.adelaide.mci.kidnumeracy.model.EquationGenerator;
import au.edu.adelaide.mci.kidnumeracy.model.EquationSeqGenerator;

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
	
	
	private EquationGenerator equationGenerator = new EquationSeqGenerator();
	
	
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
		Equation equation = equationGenerator.nextSequence();
		setOperand1(equation.getOperand1());
		//operand2 =  %(19 - operand1)
		setOperand2(equation.getOperand2());
		result = equation.getSum();
		fireAddEvent();
		return result;
	}

	public void setOperand2(int operand2) {
		this.operand2 = operand2;
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
