package au.edu.adelaide.mci.kidnumeracy;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Yun
 *
 */
public class CountLearning implements Serializable {
	
	private static final long serialVersionUID = 422349641231512002L;
	
	private static final int DIRECTION_UP = 1;
	
	private static final int DIRECTION_DOWN = 2;
	
	private int currentValue = 1;
	
	private int mMaxValue = 20;
	
	private CountLearningProcess mCountLearningProcess;
	
	private CountLearningPhase currentPhase;
	
	private int direction = DIRECTION_UP;
	
	private int currentTimes = 1;
	
	public CountLearning(CountLearningProcess countLearningProcess){
		mCountLearningProcess= countLearningProcess;
		mMaxValue = countLearningProcess.getFirstPhase().getMaxValue();
		currentPhase = countLearningProcess.getFirstPhase();
	}
	
	public int getMaxValue() {
		return mMaxValue;
	}

	public void setMaxValue(int maxValue) {
		mMaxValue = maxValue;
	}

	private int step = 1;
	
	private Set<NumberListener> numberListeners = new HashSet<NumberListener>();
	
	public void addNumberListener(NumberListener numberListener){
		numberListeners.add(numberListener);
	}
	
	/**return next value
	 * @return
	 */
	public int nextValue(){
		int repeatTimes = mCountLearningProcess.getRepeatTimes();
		int nextValue = currentValue;
		if (currentTimes <= repeatTimes){
			switch (direction) {
			case DIRECTION_UP:
				if (currentValue < mMaxValue){
					currentValue += step;
					fireNumChangedEvent(currentValue - step);
					if (currentValue == mMaxValue){
						direction = DIRECTION_DOWN;
						fireDirectionChangedEvent();
					}
				}
				nextValue = currentValue;
				break;

			default:  //count down
				if (currentValue > 1){
					currentValue -= step;
					nextValue = currentValue;
					fireNumChangedEvent(currentValue + step);
					if (currentValue == 1){
						currentTimes++;
						direction = DIRECTION_UP;
						fireDirectionChangedEvent();
						if (currentTimes == repeatTimes + 1){
							nextPhase();
						}
					}
				}
				break;
			}
		}
		return nextValue;
	}
	
	private boolean nextPhase() {
		if (!mCountLearningProcess.isLastPhase(currentPhase)){
			currentPhase = mCountLearningProcess.nextPhase(currentPhase);
			currentValue = 1;
			mMaxValue = currentPhase.getMaxValue();
			currentTimes = 1;
			direction = DIRECTION_UP;
			firePhaseChangedEvent();
			return true;
		}else{
			fireNumFinishedEvent();
			return false;
		}
	}
	
	public boolean isLastPhase(){
		return mCountLearningProcess.isLastPhase(currentPhase);
	}

	private void fireNumChangedEvent(int oldValue) {
		for (NumberListener numberListener : numberListeners) {
			numberListener.afterNumChanged(oldValue);
		}		
	}
	
	private void fireDirectionChangedEvent() {
		for (NumberListener numberListener : numberListeners) {
			numberListener.ondDirectionChanged();
		}		
	}
	private void firePhaseChangedEvent() {
		for (NumberListener numberListener : numberListeners) {
			numberListener.onPhaseChanged();
		}		
	}	
	
	private void fireNumFinishedEvent() {
		for (NumberListener numberListener : numberListeners) {
			numberListener.onAllPhaseCounted();
		}		
	}	

	public int getCurrentVaue(){
		return currentValue;
	}

	public void reset() {
		currentValue = 1;		
	}
}
