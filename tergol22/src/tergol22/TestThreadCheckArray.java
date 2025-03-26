package tergol22;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * the TestThreadCheckArray class runs a multi-threaded program that searches for a number 
 * in an array using two threads.
 * 
 * the user inputs an array and a number to search for. The program initializes shared data,
 * spawns two threads to search, and displays results.
 * 
 * @author Bashar Shoumali
 */
public class TestThreadCheckArray {
	/**
     * the main method handles user input, initializes the shared data, and runs the threads.
     * 
     * @param args Command-line arguments (not used).
     */
	public static void main(String[] args) {
		try (Scanner input = new Scanner(System.in)) {
			Thread thread1, thread2;
			//get array size from user input
			System.out.println("Enter array size");
			int num  = input.nextInt();
			ArrayList<Integer> array = new ArrayList<Integer>();
			
			//populate the array with user-provided numbers
			System.out.println("Enter numbers for array");
			
			for (int index = 0; index < num; index++) 
				array.add(input.nextInt());
			
			//get the target number to search for
			System.out.println("Enter number");
			num = input.nextInt();
			
			//create shared data object
			SharedData sd = new SharedData(array, num);
			
			//create and start threads for searching
			thread1 = new Thread(new ThreadCheckArray(sd), "thread1");
			thread2 = new Thread(new ThreadCheckArray(sd), "thread2");
			thread1.start();
			thread2.start();
			
			//wait for both threads to complete
			try 
			{
				thread1.join();
				thread2.join();
			} 
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
			//if the number was not found, display a message and return
			if (!sd.getFlag())
			{
				System.out.println("Sorry");
				return;
			}
			
			//show search results
			System.out.println("Solution for b : " + sd.getB() + ",n = " + sd.getArray().size());
			System.out.print("I:    ");
			for(int index = 0; index < sd.getArray().size() ; index++)
				System.out.print(index + "    ");
			System.out.println();
			System.out.print("A:    ");
			for (int index : sd.getArray())
			{
				System.out.print(index);
				int counter = 5;
				while (true)
				{
					index = index / 10;
					counter--;
					if (index == 0)
						break;
				}
				for (int i = 0; i < counter; i++)
					System.out.print(" ");
			}

			System.out.println();
			System.out.print("C:    ");
			for (boolean index : sd.getWinArray())
			{
				if (index)
					System.out.print("1    ");
				else
					System.out.print("0    ");	
			}
		}
	}

}
