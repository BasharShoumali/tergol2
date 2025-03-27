package tergol22;

import java.util.ArrayList;

/**
 * The SharedData class represents a shared resource used by multiple threads.
 * It contains an array of integers, a win state boolean array, a shared flag,
 * and a constant value.
 * 
 * This class provides methods to access and modify these shared variables in a
 * thread-safe manner.amjd
 * 
 * @author Bashar Shoumali
 */
public class SharedData {
	/**
	 * The array of integers to be used in both threads
	 **/
	private ArrayList<Integer> array;

	/**
	 * a boolean array that represents the win state for each index
	 **/
	private boolean[] winArray;

	/**
	 * a flag indicating whether a specific condition has been met.
	 */
	private boolean flag;

	/**
	 * a constant integer value used for calculations.
	 */
	private final int b;

	/**
	 * constructs a SharedData object with the given array and constant value.
	 * 
	 * @param array The list of integers shared between threads.
	 * @param b     A constant integer used in the calculations.
	 */
	public SharedData(ArrayList<Integer> array, int b) {

		this.array = array;
		this.b = b;
	}

	/**
	 * retrieves the win state array.
	 * 
	 * @return A boolean array representing the win state.
	 */
	public boolean[] getWinArray() {
		return winArray;
	}

	/**
	 * sets the win state array.
	 * 
	 * @param winArray A boolean array representing the new win state.
	 */
	public void setWinArray(boolean[] winArray) {
		this.winArray = winArray;
	}

	/**
	 * retrieves the array of integers.
	 * 
	 * @return The list of integers shared between threads.
	 */
	public ArrayList<Integer> getArray() {
		return array;
	}

	/**
	 * retrieves the constant integer value.
	 * 
	 * @return The constant integer value used in calculations.
	 */
	public int getB() {
		return b;
	}

	/**
	 * retrieves the flag indicating whether the condition has been met.
	 * 
	 * @return True if the condition has been met, false otherwise.
	 */
	public boolean getFlag() {
		return flag;
	}

	/**
	 * sets the flag indicating whether the condition has been met.
	 * 
	 * @param flag True if the condition is met, false otherwise.
	 */
	public void setFlag(boolean flag) {
		this.flag = flag;
	}

}
