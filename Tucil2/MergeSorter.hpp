#pragma once

#include <vector>
#include <iostream>
using namespace std;

class MergeSorter  {
	public:
	MergeSorter() {

	}

	MergeSorter(MergeSorter &m) {

	}

	static void sort(int *table, int i, int j) {
		table[0]=99;
		// int k;
		// if (i<j) {
		// 	k = (i + j) / 2;
		// 	sort(table, i, k);
		// 	sort(table, k + 1, j);
		// 	merge(table,i,k,j);
		// }
	}

	static void merge(int* table, int left, int mid, int right) {
		vector<int> tempTable;
		
		int iteratorL, iteratorR;
		
		iteratorL = left;
		iteratorR = mid + 1;

		while ((iteratorL <= mid) && (iteratorR <= right)) {
			if (table[iteratorL] <= table[iteratorR]) {
				tempTable.push_back(table[iteratorL]);
				iteratorL++;
			}
			else {
				tempTable.push_back(table[iteratorR]);
				iteratorR++;
			}
		}

		while (iteratorL <= mid) {
			tempTable.push_back(table[iteratorL]);
			iteratorL++;
		}//salin sisa table bagian kiri ke tempTable jika ada

		while (iteratorR <= right) {
			tempTable.push_back(table[iteratorR]);
			iteratorR++;
		}//salin sisa table bagian kanan ke tempTable jika ada

		cout << endl;
		for (int i = 0; i < tempTable.size(); i++) {
			cout << tempTable[i]<<"| ";
		}
		cout << endl;

		//for (int i = left; i <= right; i++) {
			//table[i] = tempTable[i-left];
		//	}//salin elemen tempTable ke table
	}

};