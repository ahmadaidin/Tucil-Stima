#pragma once
#include <algorithm>
#include <map>
#include <iostream>

using namespace std;
class QuickSorter  {
	public:
	QuickSorter() {

	}

	QuickSorter(QuickSorter &m) {

	}

	static void sort(int *table, int left, int right) {

		int i = left;
		int j = right;

		//create partition
		int pivot = table[(left + right) / 2];// getMean(table, left,right);//pivot by mean of data

		while (i<=j){
			while(table[i]<pivot)
				i++;

			while(table[j]>pivot)
				j--;

			if(i<=j){
				swap(table[i],table[j]);
				i++;
				j--;
			}
		}

		//recursive
		if (left<j) {
			sort(table, left, j);
		}

		if(i<right){
			sort(table,i,right);
		}
	}

};
