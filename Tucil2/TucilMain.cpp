// #include "MergeSorter.hpp"

#include <stdlib.h>
#include <vector>
#include <iostream>
#include <time.h>

using namespace std;

void sort(int *table, int i, int j);
void merge(int* table, int left, int mid, int right);

int main() {
	int size;
	cout << "ukuran table yang diinginkan: ";
	cin >> size;
	srand(time(NULL));

	int table[size];// = new int(size);

	for (int i = 0; i < size; i++) {
		table[i]=(rand()%size+1);
	}
	
	for (int i = 0; i < size; i++) {
		cout<<table[i]<<",";
	}

	sort(table, 0, size - 1);

	cout << endl;
	for (int i = 0; i < size; i++) {
		cout<<table[i]<<",";
	}
	
	return 0;
}


	void sort(int *table, int i, int j) {
		// table[0]=99;
		int k;
		if (i<j) {
			k = (i + j) / 2;
			sort(table, i, k);
			sort(table, k + 1, j);
			merge(table,i,k,j);
		}
	}

	void merge(int* table, int left, int mid, int right) {
		int tempTable[right-left+1];// = new int();
		
		int iteratorL, iteratorR;
		
		iteratorL = left;
		iteratorR = mid + 1;
		int i=0;

		while ((iteratorL <= mid) && (iteratorR <= right)) {
			if (table[iteratorL] <= table[iteratorR]) {
				tempTable[i]=table[iteratorL];
				iteratorL++;
			}
			else {
				tempTable[i]=table[iteratorR];
				iteratorR++;
			}
			i++;
		}

		while (iteratorL <= mid) {
			tempTable[i]=table[iteratorL];
			iteratorL++;
			i++;
		}//salin sisa table bagian kiri ke tempTable jika ada

		while (iteratorR <= right) {
			tempTable[i]=table[iteratorR];
			iteratorR++;
			i++;
		}//salin sisa table bagian kanan ke tempTable jika ada

		// cout << endl;
		// for (int i = 0; i < sizeof(tempTable)/sizeof(tempTable[0]); i++) {
		// 	cout << tempTable[i]<<"| ";
		// }
		// cout << endl;

		for (int j = left; j <= right; j++) {
			table[j] = tempTable[j-left];
			}//salin elemen tempTable ke table

		free(tempTable);
	}