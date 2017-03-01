#pragma once

class InsertionSorter  {
	public:
	InsertionSorter() {

	}

	InsertionSorter(InsertionSorter &m) {

	}

	static void sort(int *table, int size) {
		for(int i = 1; i<size; i++){
			int j=i;
			while (j>0 && table[j]<table[j-1]) {
				swap(table[j],table[j-1]);
				j--;
			}
		}
	}

};
