 #include "MergeSorter.hpp"
 #include "QuickSorter.hpp"
 #include "SelectionSorter.hpp"
 #include "InsertionSorter.hpp"



#include <stdlib.h>
#include <iostream>
#include <time.h>
#include <stdio.h>

using namespace std;

int main() {
  bool keluar = false;
  int size;
  // while(!keluar){
    cout << "ukuran table yang diinginkan: ";
    cin >> size;
    srand(time(NULL));

    int table1[size];
    for (int i = 0; i < size; i++) {
      table1[i]=(rand()%(size )+1);
    }
    // int opt=1;
    // while(opt!=5){
      // cout<<"Jenis Sort (default : Merge Sort): "<<endl;
      // cout<<"1: Merge Sort"<<endl;
      // cout<<"2: Quick Sort"<<endl;
      // cout<<"3: Selection Sort"<<endl;
      // cout<<"4: Insertion Sort"<<endl;
      // cout<<"5: Pilih ukuran lain"<<endl;
      // cout<<"0: Keluar"<<endl;
      // cout<<"Pilihanmu: ";
      // cin>>opt;

      // if(opt==0){
      //   opt=5;
      //   keluar = true;
      // } else if(opt!=5){
        // int table1[size];
        // int table2[size];
        // int table3[size];
        // int table4[size];

        // for (int i = 0; i < size; i++) {
        //   table1[i]=oldTable[i];
          // table2[i]=oldTable[i];
          // table3[i]=oldTable[i];
          // table4[i]=oldTable[i];
//        }

      	// cout<<endl;
      	// cout<<"Tabel awal: "<<endl;
      	// for (int i = 0; i < size; i++) {
      	// 	cout<<table[i]<<"|";
      	// }

        clock_t start;
        clock_t end;

        // start = clock();
        // MergeSorter::sort(table1,0,size-1);
        // end = clock();
        // float seconds1 = (float)(end - start)*1000 / CLOCKS_PER_SEC;
        // cout<<endl<<endl;
        // printf ("Waktu eksekusi Merge Sort: %.3lf ms.\n", seconds1 );
        // cout<<endl;

        // start = clock();
        // QuickSorter::sort(table1, 0,size-1);
        // end = clock();
        // float seconds2 = (float)(end - start)*1000 / CLOCKS_PER_SEC;
        // cout<<endl<<endl;
        // printf ("Waktu eksekusi Quick Sort: %.3lf ms.\n", seconds2 );
        // cout<<endl;
        //
        //
        // start = clock();
        // SelectionSorter::sort(table1, size);
        // end = clock();
        // float seconds3 = (float)(end - start)*1000 / CLOCKS_PER_SEC;
        // cout<<endl<<endl;
        // printf ("Waktu eksekusi Selection Sort: %.3lf ms.\n", seconds3 );
        // cout<<endl;
        //
        start = clock();
        InsertionSorter::sort(table1, size);
        end = clock();
        float seconds4 = (float)(end - start)*1000 / CLOCKS_PER_SEC;
        cout<<endl<<endl;
        printf ("Waktu eksekusi Insertion Sort: %.3lf ms.\n", seconds4 );
        cout<<endl;

      	// cout<<endl<<endl;
      	// cout<<"Tabel hasil pengurutan: "<<endl;
      	// for (int i = 0; i < size; i++) {
      	// 	cout<<table[i]<<"|";
      	// }





    //   }
    //   else {
    //     keluar = false;
    //   }
    // }
  // }
	return 0;
}
