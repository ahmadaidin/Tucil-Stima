#pragma once

class SelectionSorter  {
	public:
	SelectionSorter() {

	}

	SelectionSorter(SelectionSorter &m) {

	}

	static void sort(int *table, int size) {
		for(int i = 0; i<size-1; i++){
			for(int j=i+1; j<size; j++){
				if(table[i]>table[j]){
					swap(table[i],table[j]);
				}
			}
		}
	}

};
