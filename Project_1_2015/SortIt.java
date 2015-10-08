////////// CS360 Fall 2015 Project 1 //////////
////////// Written by Andrew Nelson //////////
/////////  This program sorts a .csv file that holds the name, SSN, and Date of Birth for a person on each line.

import java.io.*;

public class SortIt {							//class declaration
	public static int length = 0;
	public static int[] assign;
	public static int[] compare;				//public variables used to track various items
	public static int passNum;
	
	//assignments were counted in the sorting algorithms when a new variable was created or changed relative to the sort
	//this does not include trivial assignments such as integers used only for loop increments
	
	//comparisons were counted only when one item was compared to another using < > or ==
	//this does not include comparisons used to increment loops
	
	public static Person[] read(boolean ssn) {		//reads in the file line by line
		Person[] array = null;
		try{
			BufferedReader file = new BufferedReader(new FileReader("personnel.csv"));
			int number = Integer.parseInt(file.readLine());
			length = number;
			array = new Person[number];				//uses first line to make array of that length
			String line = file.readLine();
			int i = 0;
			while (line!= null){					//while loop to create a person for every line
				String[] ray = line.split(",");		//and place each person in the array
				ray[2] = ray[2].replace("-", "");
				array[i] = new Person(ray[0], Integer.parseInt(ray[1]), Integer.parseInt(ray[2]), ssn);
				line = file.readLine();
				i++;
			}

			file.close();							//always close the file
			
		}
			
			catch (IOException er) {
				System.out.println("Cannot find file personnel.csv");
			}
		return array;}						//returns the new, unsorted, array; end read
	
	static void radixSort(Person[] array, boolean ssn, int len){		//performs radix sort on the given array

		Person[] sorted = new Person[len];				//creates a new array to hold the sorted product
		assign[passNum]++;									// not an in-place sort
		if(ssn){											//2 options: sort by ssn or not(sort by birthday)
			int i, m = array[0].getSocial(), exp = 1;
			assign[passNum]+=2;
			for(i=1; i<len; i++){
				if(array[i].getSocial() > m){ 
					m = array[i].getSocial();				//finds the maximum number
					assign[passNum]++;
				}
				compare[passNum]++;
		}
			while(m/exp>0){
				int[] bucket = new int[10];				//creates the bucket to count the number of each digit
				compare[passNum]++;
				assign[passNum]++;
				
			for(i=0; i<len; i++){
				bucket[(array[i].getSocial()/exp)%10]++;	//counts the number of each digit
				assign[passNum]++;}
			
			for(i=1; i<10; i++){							
				bucket[i] += bucket[i-1];					//adds each element of the bucket together
				assign[passNum]++;}
			
			for(i=len-1; i>=0; i--){
				sorted[--bucket[(array[i].getSocial()/exp)%10]] = array[i];		//places the elements in their sorted order
				assign[passNum]++;}
				
			for(i = 0; i < len; i++){
				array[i] = sorted[i];						//returns the elements to the array in sorted order
				assign[passNum]++;}
			exp *= 10;
			assign[passNum]++;
			}
	}
	else{													// same thing but by DOB
		int i, m = array[0].getDOB(), exp = 1;
		for(i=1; i<len; i++){
			if(array[i].getDOB() > m){ m = array[i].getDOB();}
	}
		while(m/exp>0){
			int[] bucket = new int[10];
			
		for(i=0; i<len; i++)
			bucket[(array[i].getDOB()/exp)%10]++;
		for(i=1; i<10; i++)
			bucket[i] += bucket[i-1];
		for(i=len-1; i>=0; i--)
			sorted[--bucket[(array[i].getDOB()/exp)%10]] = array[i];
		for(i = 0; i < len; i++)
			array[i] = sorted[i];
		exp *= 10;
		}
	}
		
	}		//end radixSort

	static void quicksort(Person[] array, int left, int right){ //performs quicksort on the given array
		int pivot;
		if(left < right){
			compare[passNum]++;
			pivot = partition(array, left, right);		//pivot is the result of the first partition
			assign[passNum]++;
			quicksort(array,left,pivot-1);
			quicksort(array,pivot+1,right);				//recurse from both sides
		}}
	
	static int partition(Person[] array, int left, int right){ //used with quicksort method to perform the sort
		Person pivot = array[right];		//uses the last element in the array as pivot
		int i = left-1;
		Person temp;
		assign[passNum]+=2;
		for(int j = left; j <= right-1; j++){
			if (array[j].lessThan(pivot)){		//swaps all items less than the pivot to its left
				i++;
				temp = array[i];
				array[i] = array[j];
				array[j] = temp;}
				assign[passNum]+= 3;}
			compare[passNum]++;
		
		temp = array[i+1];
		array[i+1] = array[right];				//places the pivot item between the items of lesser and greater value
		array[right] = temp;
		assign[passNum]+=3;
		return i+1;
		}
	
	public static void sort(Person[] array, int pass, boolean ssn){			//used to organize the sorts and output
		if(ssn){System.out.println("Sorting by Social Security Number");}
		else{System.out.println("Sorting by Date of Birth");}
		System.out.println();
		
		System.out.println("* Quicksort");
		
		if(pass == 1){
			quicksort(array,0,length-1);
		}
		else{
			int passLen = length/pass;
			for(int i =0; i< pass-1; i++){
				Person[] passRay = new Person[passLen*(i+1)];
				for(int k=0; k< passLen*(i+1); k++){
					passRay[k] = array[k];}
				quicksort(passRay,0,passLen*(i+1)-1);
				passNum++;
			}
			quicksort(array,0,length-1);
		}
		
		System.out.println("- Sorted array");
		for(Person p: array){
			System.out.println(p);
		}
		System.out.println();
		System.out.println("- Assignments");
		for(int i: assign){System.out.print(i + " ");}
		System.out.println();
		System.out.println("- Comparisons");
		for(int i: compare){System.out.print(i+ " ");}
		System.out.println();
		System.out.println();
		
		
		
		System.out.println("* Radix Sort");
		passNum = 0;
		if(pass == 1){
			radixSort(array,ssn,length);
		}
		else{
			int passLen = length/pass;
			for(int i =0; i< pass-1; i++){
				Person[] passRay = new Person[passLen*(i+1)];
				for(int k=0; k< passLen*(i+1); k++){
					passRay[k] = array[k];}
				radixSort(passRay,ssn,passLen*(i+1));
				passNum++;
			}
			 radixSort(array,ssn,length);
		}
		
		System.out.println("- Sorted array");
		for(Person p: array){System.out.println(p);}
		System.out.println();
		System.out.println("- Assignments");
		for(int i: assign){System.out.print(i + " ");}
		System.out.println();
		System.out.println("- Comparisons");
		for(int i: compare){System.out.print(i+ " ");}
		System.out.println();
	}
	
	public static void main(String[] args){				// main function that starts each method and handles command line arguments
		boolean ssn = false;		//if no -S or -B, it will sort by DOB
		int pass = 1;
		
		if(args.length > 3){System.out.println("Too many arguments. Try again."); System.exit(1);} //I expect no more than 3 arguments
		if(args.length < 1){System.out.println("Too few arguments. Try again."); System.exit(1);}	//and no less than one
																				//for example: -S -n 10 or -B but NOT -B -n or -B -S -n 10
		
		for(int i = 0; i < args.length; i++){
			if(args[i].equals("-S")){
				ssn = true;}
			else if(args[i].equals("-B")){
				ssn = false;}
			else if(args[i].equals("-n")){pass = Integer.parseInt(args[i+1]);
			i++;}
			else {System.out.println("Error, incorrect argument"); System.exit(1);}
		}
		assign = new int[pass];
		compare = new int[pass];
		
		Person[] array;
		array = read(ssn);
		sort(array, pass, ssn);
		
		}}
