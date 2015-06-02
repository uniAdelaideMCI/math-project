/***********************************************************************
 * Module:  Equation.java
 * Author:  Yun
 * Purpose: Defines the Class Equation
 ***********************************************************************/

package au.edu.adelaide.mci.kidnumeracy.model;

/** @pdOid 1ea040ae-2d3b-4820-b04d-5426ead24f84 */
public class Equation {
	/** @pdOid 2f585914-960f-4fa1-8232-1f9627c9dab7 */
	private int operand1;
	/** @pdOid 29aa2d31-7ad6-4c2c-ab6e-9fb89cee461f */
	private int operand2;
	/** @pdOid 83f3eaaf-9ada-4b6d-a279-c1daadf6de17 */
	private int sum;

	public Equation(int aOperand1, int aOperand2, int aSum) {
		operand1 = aOperand1;
		operand2 = aOperand2;
		sum = aSum;
	}

	/** @pdOid 4354dc81-4d66-42ce-9cca-bb82631f22ac */
	public int getOperand1() {
		return operand1;
	}

	/** @pdOid f1ef2933-962c-436b-bf34-bab6282d185a */
	public int getOperand2() {
		return operand2;
	}

	/** @pdOid 3785df1c-666c-410d-b667-f8324112713b */
	public int getSum() {
		return sum;
	}

}