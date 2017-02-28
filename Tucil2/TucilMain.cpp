#include "MergeSorter.hpp"

#include <stdlib.h>
#include <iostream>
#include <time.h>

using namespace std;


int main() {
	int size;
	cout << "ukuran table yang diinginkan: ";
	cin >> size;
	srand(time(NULL));

	int *table = new int(size);

	for (int i = 0; i < size; i++) {
		table[i]=(rand()%size+1);
	}
	
	for (int i = 0; i < size; i++) {
		cout<<table[i]<<",";
	}

	// MergeSorter::sort(table, 0, size - 1);

	// cout << endl;
	// for (int i = 0; i < size; i++) {
	// 	cout<<table[i]<<",";
	// }
	
	return 0;
}
