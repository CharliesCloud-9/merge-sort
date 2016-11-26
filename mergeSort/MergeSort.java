package mergeSort;
/**
* line: 7 Question: the method you used is void in type; then how you are returning the values? 
* line: 7 Answer: I don't return any values. The values are passed by reference into the mergeSort method, which reorganizes the values in place (ie. within the same array). Any call to mergeSort from outside this method already has a pointer to the array.﻿
* line: 7 Some notes: basically MergeSort() method does not need to return anything, because any call to mergeSort from outside this method already has a pointer to the array.

* False Statement: you don't need lowIndex, highIndex as params - they are easily derived from the list.
* True Correct Statement: you do need them if you're calling the mergeSort function on items 6 through 12 of a 24 item list. Yes, alternatively you could call mergeSort on a new list that is a copy of those items, but that's not how I coded it here.
*/


/* 1.************Recursively divides whole list array iteratively until the atomic values(each element in list is separated on their own, meaning that now each element is sorted.) are achieved. view minute 8:32. Is the next 13 lines of code.********* */
public class MergeSort {						
	public void mergeSort(int[] list, int lowIndex, int highIndex) { 		//accept unsorted list,lowIndex & highIndex… //…needed to track range of values that were going to sort.
			if (lowIndex == highIndex)										//Base Case: If only 1 item in the list, then we will return.
				return;														//returns list untouched if only 1 item in list.
			else { 															//else if there is more than 1 item in the list then... block of code. //Breaking each of the 2 sublists L/R even more further in order to get them sorted and ready for each of there unpicked indexes to be compared. Divide & Conquer: reducing a problem into sub-problems in a recursive or self-similar manner, and at any step once we get solutions of subproblems, we can construct the solution of the actual problem. If we've sorted two sub-lists, than we can sort the parent-list.
				int midIndex = (lowIndex + highIndex) / 2;    				//1st:calculate the middle Index by adding (lowIndex+highIndex)/2
				//System.out.println(highIndex);
				mergeSort(list, lowIndex, midIndex);         				//working with/breaking apart indexes: 0,1,2,3,4,5. This mergeSort() call occurs more than once, recursively sends lower/left half through mergeSort() again & again, every time with new midIndex ex.2,1,0 & new highIndex ex.5,2,1 & lowIndex ex. 0. Finding/separating the Left/lower sublists lowIndex & midIndex to get ready to be placed in the L[] array sublist later on. //Extra info: then call merge sort on bottom/left half of list, sending same list along with lowIndex & midIndex we just calculated. So this sends left half to mergeSort().
				mergeSort(list, midIndex + 1, highIndex);     				//working with/breaking apart indexes: 6,7,8,9,10. This mergeSort() call occurs more than once, recursively sends higher/right half through mergeSort() again & again, every time with new midIndex ex.8,9 & lowIndex ex. 6,9 (note: remember that lowIndex is calculated "lowIndex+1", so 5+1='6',8+1='9') & highIndex 10.  Finding/separating the higher/right sublists midIndex & highIndex to get ready to be placed in the R[] array sublist later on.  //Extra info: then call M.S. on top/right half, sending medIndex+1 using this at the lowIndex. highIndex as the upper index. So this sends right half to mergeSort().
				merge(list, lowIndex, midIndex, highIndex);    //mergeTogetherEachElementIntoSublistsThanMergeSublistBackToParentList_Merge()   …after those 2 lists(left & right) are sorted/"all elements are separated", we do the merge step. This is considered
			} 																//…this calls separate merge() function below, which is the helper function that does the merge. So this will call merge() and merge the 2 sorted lists(left & right) together.
	}
	
	public void merge(int[] list, int lowIndex, int midIndex, int highIndex) {  //mergeTogetherEachElementIntoSublistsThanMergeSublistBackToParentList_Merge()
																//list Partition/inserting elements into there respective sublists, L[] or R[], only 2 sublists: 
		/* 2.**************Inputting lower half of unsorted list & putting them in L[] array sublist, and then sorting this L[] array sublist.********************************/
																//Left: Getting all the unsorted lower/left indexes from lowIndex through midIndex and putting them into L[] array sublists.
		int[] L = new int[midIndex - lowIndex + 2]; 			//First take a variable that will store the number of elements in L. So think L=length(L)		
		//System.out.println("mid" +midIndex);
		for (int i = lowIndex; i <= midIndex; i++) {			//sorting L[]... incrementing through i = 0,1,2,3,4,5
			L[i - lowIndex] = list[i];							//sorting L[] sublist values...ex:2,4,5,8,12,20
		}
		L[midIndex - lowIndex + 1] = Integer.MAX_VALUE;   		//Highest possible value an integer can have is 9999999999 , so any value inside the L[] array cannot be larger than 9999999999. //MAX_VALUE notes: That's a Java constant that is the highest possible value an integer can have. So anything you compare to it will always be less than MAX_VALUE. Think of it as 9999999999.﻿
		
		/* 3.**************Inputting higher half of unsorted list & putting them in R[] array sublist, and then sorting this R[] array sublist.********************************/
																//Right: Getting all the unsorted higher/right indexes from midIndex through highIndex and putting them into R[] array sublists.
		int[] R = new int[highIndex - midIndex + 1];			//Another variable that will store the number of elements in R. So think R=length(R)
		for (int i = midIndex + 1; i <= highIndex; i++) {		//sorting R[]... incrementing through i = 6,7,8,9,10
			R[i - midIndex - 1] = list[i];						//sorting R[] sublist values...ex:1,3,6,10,23
		}
		R[highIndex - midIndex] = Integer.MAX_VALUE;			//Highest possible value an integer can have is 9999999999 , so any value inside the R[] array cannot be larger than 9999999999. //MAX_VALUE notes: That's a Java constant that is the highest possible value an integer can have. So anything you compare to it will always be less than MAX_VALUE. Think of it as 9999999999.﻿
		/* 4.*********************Compare L[] & R[] sublist & Merge back into parent list in sorted order********************************/
		int i = 0; 											//i will mark the index of the smallest unpicked(Unpicked meaning:next index to be picked/next in line) in L ...left/lower part of list.
		int j = 0; 											//j will mark the index of the smallest unpicked(Unpicked meaning:next index to be picked/next in line) in R ...Right/Higher part of list.
		for (int k = lowIndex; k <= highIndex; k++) { 		//K will mark the index of the position that needs to be filled in list.
			if (L[i] <= R[j]) { 							//if true if smallest unpicked in L/Left is less than/equal to smallest unpicked in R/Right, than do block of code.//We are comparing the smallest unpicked in L with the smallest unpicked in R. So comparing smallest unpicked index/value of L/Left to smallest unpicked index/value of R/Right. 
				list[k] = L[i];								//at Kth position in list, we will write L[i], we are overwriting list //and that no problem! =)
				i++;										//And now I need to increment k and go to the next position. This will be done by the k-for loop above. Also i is incremented here. i is incremented so we can mark the next unpicked index in L /Left part.
			}
			else { 											//if condition not true. if smallest unpicked in L/Left is not less than/equal to smallest unpicked in R/Right, than do this block of code instead.
				list[k] = R[j]; 							//than list[k] will be R[j]
				j++; 										//Once again we increment k and go to the next position. This will be done by using the k-for loop above. Also j is incremented here. j is incremented so we can mark the next unpicked index in R /Right part.
			} 												//Small Note: Notice that k is used in both true and not true conditions, that is why a k-for loop was used, because regardless, k index is used in both if & else, because k represents the list index.
		} 
	}
	
	public static void main(String arg[]) {
		int[] list = {5,4,2,20,12,8,3,10,23,1,6};  //9999999999 if you use this number as a value inside list it will give you an error because of our value Max rule on line 35 and 43!!Remember this!
		int lowIndex = 0, highIndex = 10; 
		MergeSort msObj = new MergeSort();
		
		long startTime = System.nanoTime();   				//part of method time keeping
		
		msObj.mergeSort(list, lowIndex, highIndex);  		//mergeSort method call
		
		long endTime = System.nanoTime();	  				//part of method time keeping
		long duration = (endTime - startTime); 				//part of method time keeping
		System.out.println("mergeSort finish time is: " + duration);      //print part of method time keeping
		
		for (int i = 0; i < list.length; i++) {
			System.out.println(list[i]);  					//using for loop to print each newly sorted element in the list.
		}	
	}
}
