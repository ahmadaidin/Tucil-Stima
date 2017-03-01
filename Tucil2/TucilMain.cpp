 #include "MergeSorter.hpp"
  #include "QuickSorter.hpp"

#include <stdlib.h>
#include <iostream>
#include <time.h>
#include <stdio.h>

using namespace std;

int main() {
  bool keluar = false;
  int size;
  while(!keluar){
    cout << "ukuran table yang diinginkan: ";
    cin >> size;
    srand(time(NULL));

    int oldTable[size];
    for (int i = 0; i < size; i++) {
      oldTable[i]=(rand()%(size*10)+1);
    }
    int opt=1;
    while(opt!=5){
      cout<<"Jenis Sort (default : Merge Sort): "<<endl;
      cout<<"1: Merge Sort"<<endl;
      cout<<"2: Quick Sort"<<endl;
      cout<<"3: Selection Sort"<<endl;
      cout<<"4: Insertion Sort"<<endl;
      cout<<"5: Pilih ukuran lain"<<endl;
      cout<<"0: Keluar"<<endl;
      cout<<"Pilihanmu: ";
      cin>>opt;

      if(opt==0){
        opt=5;
        keluar = true;
      } else if(opt!=5){
        int table[size];

        for (int i = 0; i < size; i++) {
          table[i]=oldTable[i];
        }

      	cout<<endl;
      	cout<<"Tabel awal: "<<endl;
      	for (int i = 0; i < size; i++) {
      		cout<<table[i]<<"|";
      	}

      	clock_t start = clock();

        switch (opt) {
          case 1: {
            MergeSorter::sort(table,0,size-1);
          }
          case 2: {
            QuickSorter::sort(table, 0,size-1);
          }
        }

      	clock_t end = clock();
      	float seconds = (float)(end - start)*1000 / CLOCKS_PER_SEC;

      	cout<<endl<<endl;
      	cout<<"Tabel hasil pengurutan: "<<endl;
      	for (int i = 0; i < size; i++) {
      		cout<<table[i]<<"|";
      	}

      	cout<<endl<<endl;
      	printf ("Waktu eksekusi: %.3lf ms.\n", seconds );
      	cout<<endl;
      }
      else {
        keluar = false;
      }
    }
  }
	return 0;
}
