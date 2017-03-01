#pragma once

class MergeSorter  {
	public:
	MergeSorter() {

	}

	MergeSorter(MergeSorter &m) {

	}

	static void sort(int *table, int i, int j) {

		int k;
		if (i<j) {
			k = (i + j) / 2;
			sort(table, i, k);
			sort(table, k + 1, j);
			merge(table,i,k,j);
		}
	}

	static void merge(int* table, int left, int mid, int right) {
		int tempTable[right-left+1];

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

		for (int j = left; j <= right; j++) {
			table[j] = tempTable[j-left];
			}//salin elemen tempTable ke table

	}
};
