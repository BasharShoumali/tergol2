package tergol22;

import java.util.ArrayList;

/**
 * the ThreadCheckArray class represents a thread that searches for a subset of
 * numbers in an array that sum to a given target value. It utilizes recursion
 * and shared data synchronization to ensure thread safety.
 *
 * this class implements the Runnable interface and works alongside the
 * SharedData class to store results.
 *
 * @author Bashar Shoumali
 */
public class ThreadCheckArray implements Runnable 
{
	/**
	 * flag indicating whether a valid subset sum has been found.
	 */
	private boolean flag;
	
	/** 
	 * boolean array tracking which elements contribute to the subset sum. 
	 */
	private boolean [] winArray;
	
	/**
	 * shared data object used for synchronization between threads.
	 */
	SharedData sd;
	
	/**
	 * the list of integers to search through.
	 */
	ArrayList<Integer> array;
	
	/** 
	 * the target sum to find within the array. 
	 */
	int b;
	
	
	/**
     * constructs a new ThreadCheckArray object.
     *
     * @param sd The shared data object containing the input array and target sum.
     */
	public ThreadCheckArray(SharedData sd) 
	{
		this.sd = sd;	
		synchronized (sd) 
		{
			array = sd.getArray();
			b = sd.getB();
		}		
		winArray = new boolean[array.size()];
	}
	
	
	/**
     * recursively checks if a subset of the array sums to the target value.
     *
     * @param n The current index in the array.
     * @param b The remaining sum to check for.
     */
	void rec(int n, int b)
	{
		synchronized (sd) 
		{
			if (sd.getFlag())
				return;
		}	
		if (n == 1)
		{
			if(b == 0 || b == array.get(n-1))
			{
				flag = true;
				synchronized (sd) 
				{
					sd.setFlag(true);
				}			
			}
			if (b == array.get(n-1))
				winArray[n-1] = true;
			return;
		}
		
		rec(n-1, b - array.get(n-1));
		if (flag)
			winArray[n-1] = true;
		synchronized (sd) 
		{
			if (sd.getFlag())
				return;
		}	
		rec(n-1, b);
	}

	
	/**
     * runs the thread, starting the recursive subset search.
     * If a valid subset is found, the results are stored in the shared data object.
     */
	public void run() {
		if (array.size() != 1)
			if (Thread.currentThread().getName().equals("thread1"))
				rec(array.size()-1, b - array.get(array.size()-1));
			else 
				rec(array.size()-1, b);
		if (array.size() == 1)
			if (b == array.get(0) && !flag)
			{
				winArray[0] = true;
				flag = true;
				synchronized (sd) 
				{
					sd.setFlag(true);
				}
			}
		if (flag)
		{
			if (Thread.currentThread().getName().equals("thread1"))
				winArray[array.size() - 1] = true;
			synchronized (sd) 
			{
				sd.setWinArray(winArray);
			}	
		}
	}
}
