package sortingAlgorithms;

import java.util.Random;

public class sortingAlgorithmsMain {
	
	static Random r = new Random();
	static final int LAENGE = 10;
	static int counter = 0;

	public static void main(String[] args) {
		int[] array = createArray(LAENGE);
		
		System.out.println("Original Array:");
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]+" ");
		}
		System.out.println("\n");
		
		int[] bubbleS = bubbleSort(array);
		
		System.out.println("BubbleSort:");
		for (int i = 0; i < bubbleS.length; i++) {
			System.out.print(bubbleS[i]+" ");
		}
		System.out.println("\n");
		
		int[] iS = insertionSort(array);
		
		System.out.println("InsertionSort:");
		for (int i = 0; i < iS.length; i++) {
			System.out.print(iS[i]+" ");
		}
		System.out.println("\n");
		
		int[] sS = selectionSort(array);
		
		System.out.println("SelectionSort:");
		for (int i = 0; i < sS.length; i++) {
			System.out.print(sS[i]+" ");
		}
		System.out.println("\n");
		
		int[] bogoS = bogoSort(array);
		
		System.out.println("BogoSort: ("+counter+" Sortierschritte)");
		for (int i = 0; i < bogoS.length; i++) {
			System.out.print(bogoS[i]+" ");
		}
	}
	public static int[] createArray(int LAENGE) {
		int[] a = new int[LAENGE];
		for (int i = 0; i < a.length; i++) {
			a[i] = r.nextInt(a.length);
		}
		return a;
	}
	public static int[] bubbleSort(int[] array) {
		int[] a = new int[LAENGE];
		for (int i = 0; i < array.length; i++) {
			a[i] = array[i];
		}
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
		return a;
	}
	public static int[] insertionSort(int[] array) {
		int[] a = new int[LAENGE];
		for (int i = 0; i < array.length; i++) {
			a[i] = array[i];
		}
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
		return a;
	}
	public static int[] selectionSort(int[] array) {
		int[] a = new int[LAENGE];
		for (int i = 0; i < array.length; i++) {
			a[i] = array[i];
		}
		for (int i = 0; i < a.length - 1; i++) {
			for (int j = i + 1; j < a.length; j++) {
				if (a[i] > a[j]) {
					int temp = a[i];
					a[i] = a[j];
					a[j] = temp;
				}
			}
		}
		return a;
	}
	public static int[] bogoSort(int[] array) {
		int[] a = new int[LAENGE];
		for (int i = 0; i < array.length; i++) {
			a[i] = array[i];
		}
		while(!isSorted(a)) { //check if the array is sorted, if not swap 2 random positions
			counter++;
			//Select 2 random positions of the array
			int index1 = r.nextInt(a.length);
			int index2 = r.nextInt(a.length);
			//swap the selected positions
			int temp = a[index1];
			a[index1] = a[index2];
			a[index2] = temp;
		}
		return a;
	}
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
}
