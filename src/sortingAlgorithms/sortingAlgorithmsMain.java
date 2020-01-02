package sortingAlgorithms;

import java.util.Random;
import java.util.Scanner;

public class sortingAlgorithmsMain {
	
	static Random r = new Random();
	static int LAENGE;
	static long bogoCounter = 0;
	static long sortingTime;

	public static void main(String[] args) {
		int[] array = createArray();
		output("Original Array:", array);
		
		int[] bubbleS = bubbleSort(array);
		output("BubbleSort:", bubbleS, sortingTime);
		
		int[] iS = insertionSort(array);
		output("InsertionSort:", iS, sortingTime);
		
		int[] sS = selectionSort(array);
		output("SelectionSort:", sS, sortingTime);
		
		int[] bogoS = bogoSort(array);
		output("BogoSort: ("+bogoCounter+" Sortierschritte)", bogoS, sortingTime);
		
		int[] qS = quickSort(array);
		output("QuickSort:", qS, sortingTime);
	}
	
	//Creates a randomly filled Array with a length of LAENGE
	
	public static int[] createArray() {
		boolean ok = true;
		do {
			try {
				ok = true;
				Scanner sc = new Scanner(System.in);
				System.out.print("Geben Sie die Länge des zufällig erzeugten Arrays an : ");
				LAENGE = sc.nextInt();
				if (LAENGE < 5) {
					System.out.println("Die Länge des Arrays muss mindestes 5 entsprechen!");
					ok = false;
				}
			} catch (Exception e) {
				System.out.println("Geben Sie bitte einen gültigen Wert ein!");
				ok = false;
			}
		} while (ok == false);
		int[] a = new int[LAENGE];
		for (int i = 0; i < a.length; i++) {
			a[i] = r.nextInt(a.length);
		}
		return a;
	}
	
	public static int[] copyArray(int[] array) {
		int[] arrayCopy = new int[array.length];
		for (int i = 0; i < array.length; i++) {
			arrayCopy[i] = array[i];
		}
		return arrayCopy;
	}
	
	//BubbleSort
	
	public static int[] bubbleSort(int[] array) {
		int[] a = copyArray(array);
		long beginTime = getTime();
		int temp;
		for(int i = 1; i < a.length; i++) {
			for(int j = 0; j < a.length - i; j++) {
				if(a[j] > a[j + 1]) {
					temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
				}
				
			}
		}
		sortingTime = getTime() - beginTime;
		return a;
	}
	
	//InsertionSort
	
	public static int[] insertionSort(int[] array) {
		int[] a = copyArray(array);
		long beginTime = getTime();
		int temp;
		for (int i = 1; i < a.length; i++) {
			temp = a[i];
			int j = i;
			while (j > 0 && a[j - 1] > temp) {
				a[j] = a[j - 1];
				j--;
			}
			a[j] = temp;
		}
		sortingTime = getTime() - beginTime;
		return a;
	}
	
	//SelectionSort
	
	public static int[] selectionSort(int[] array) {
		int[] a = copyArray(array);
		long beginTime = getTime();
		for (int i = 0; i < a.length - 1; i++) {
			for (int j = i + 1; j < a.length; j++) {
				if (a[i] > a[j]) {
					int temp = a[i];
					a[i] = a[j];
					a[j] = temp;
				}
			}
		}
		sortingTime = getTime() - beginTime;
		return a;
	}
	
	//BogoSort according to german Wikipedia page
	
	public static int[] bogoSort(int[] array) {
		int[] a = copyArray(array);
		long beginTime = getTime();
		while(!isSorted(a)) { //check if the array is sorted, if not swap 2 random positions
			bogoCounter++;
			//Select 2 random positions of the array
			int index1 = r.nextInt(a.length);
			int index2 = r.nextInt(a.length);
			//swap the selected positions
			int temp = a[index1];
			a[index1] = a[index2];
			a[index2] = temp;
		}
		sortingTime = getTime() - beginTime;
		return a;
	}
	
	//Checks if an Array is sorted
	
	public static boolean isSorted(int[] a) {
		boolean sorted = true;
		for (int i = 0; i < a.length - 1; i++) {
			if (a[i] > a[i + 1]) {
				sorted = false;
				break;
			}
		}
		return sorted;
	}
	
	//QuickSort
	
	public static int[] quickSort(int[] array) {
		int[] a = copyArray(array);
		long beginTime = getTime();
		quickSortLoop(a, 0, LAENGE - 1);
		sortingTime = getTime() - beginTime;
		return a;
	}
	
	//Loops the Sorting-Algorithm of QuickSort
	
	public static void quickSortLoop(int[] a, int start, int end) {
		if (start >= end) {
			return;
		}
		int p = partition(a, start, end);
		quickSortLoop(a, start, p-1);
		quickSortLoop(a, p+1, end);
	}
	
	//Sorting of an partition of a quickSort-Array
	
	public static int partition(int[] a, int start, int end) {
		int pivot = a[start];
		int low = start + 1;
		int high = end;
		while (true) {
			while ((low <= high) && (a[high] >= pivot)) {
				high = high - 1;
			}
			while ((low <= high) && (a[low] <= pivot)) {
				low = low + 1;
			}
			if (low <= high) {
				int[] copyArray = copyArray(a);
				a[low] = copyArray[high];
				a[high] = copyArray[low];
			}
			else {
				break;
			}
		}
		int[] copyArray = copyArray(a);
		a[high] = copyArray[start];
		a[start] = copyArray[high];
		return high;
	}
	
	public static void output(String s, int[] a) {
		System.out.println(s);
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i]+" ");
		}
		System.out.println("\n");
	}
	public static void output(String s, int[] a, long time) {
		System.out.println(s+" ("+time+"ns)");
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i]+" ");
		}
		System.out.println("\n");
	}
	public static long getTime() {
		return System.nanoTime();
	}
}
