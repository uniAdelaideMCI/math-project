package au.edu.adelaide.mci.kidnumeracy.model;

import java.io.Serializable;

/***********************************************************************
 * Module:  EquationSeqGenerator.java
 * Author:  Yun
 * Purpose: Defines the Class EquationSeqGenerator
 ***********************************************************************/


/** Generate the equations in a sequent order
 * 
 * @pdOid 0e948de7-7f63-4933-912f-2838991902cf */
public class EquationSeqGenerator implements EquationGenerator,Serializable {
	private static final long serialVersionUID = -4172881849472213554L;
	private int maxSum = 10;
	private int operand1 = -1;
	private int operand2 = -1;
	private int sum = -1;
	
	public int getSum() {
		return sum;
	}
	private boolean cycleable = true;
   /** operand1 = 1.. maxSum
    * operand2 = 1..(maxSum-operand1)
    * 
    * @pdOid 4a79c8ae-5bf1-42cd-9eb0-b4f01498aea3 */
   public Equation nextSequence(){
	   if (sum == -1){
		   operand1 = 1; 
		   operand2 = 1;
	   }else{
		   if (operand2 < maxSum - operand1){
			   operand2++;
		   }else if (operand1 < maxSum - 1){
			   operand1++;
			   operand2 = 1;
		   }else{
			   //restart from 1 if the sequence reach the end
			   if (cycleable){
				   operand1 = 1; 
				   operand2 = 1;
			   }
		   }
	   }
	   sum = operand1 + operand2;
	   return new Equation(operand1,operand2, sum);
   }

}
