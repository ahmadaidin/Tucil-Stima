package com.stima;

import java.util.ArrayList;

/**
 * Created by Aidin Ahmad on 05/04/2017.
 */
public class CostMatrix {
    private int[][] mtx;
    private int cost;
    private int size;
    private static final int INFINITY = 999;

    public CostMatrix(int size){
        this.size = size;
        cost = 0;
        mtx = new int [size][size];
    }

    public CostMatrix(CostMatrix costMatrix){
        this.size = costMatrix.size;
        cost = costMatrix.cost;
        this.mtx = new int [size][size];
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                this.mtx[i][j] = costMatrix.getMtx()[i][j];
            }
        }
    }

    public CostMatrix(int[][] mtx, int size, int cost){
        this.size = size;
        this.cost = cost;
        this.mtx = new int [size][size];
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                this.mtx[i][j] = mtx[i][j];
            }
        }
    }

    public void addCost(int val){
        cost+=val;
    }

    public int[][] getMtx(){
        return mtx;
    }

    public int getSize(){
        return size;
    }

    public int getCost(){
        return cost;
    }

    public void change(int i, int j, int val){
        mtx[i][j] = val;
    }

    public void reduceCost(){
        //melakukan reduksi bobot pada matrix
        //mengupdate nilai cost
        for(int i=0; i<size; i++){
            int min =0;
            boolean found = false;
            int idxMin = 0;
            while(!found && idxMin <size){
                if(mtx[i][idxMin]!= INFINITY){
                    found = true;
                    min = mtx[i][idxMin];
                } else{
                    idxMin++;
                }
            }

            for(int j= idxMin; j<size; j++){
                if(mtx[i][j]<min){
                    min = mtx[i][j];
                }
            }

            cost+=min;
            if(min!=0) {
                for (int j = 0; j < size; j++) {
                    if (mtx[i][j] != INFINITY) {
                        mtx[i][j] -= min;
                    }
                }
            }
        }

        for(int i=0; i<size; i++) {
            int min =0;
            boolean found = false;
            int idxMin = 0;
            while(!found && idxMin <size){
                if(mtx[idxMin][i]!= INFINITY){
                    found = true;
                    min = mtx[idxMin][i];
                } else{
                    idxMin++;
                }
            }

            for(int j= idxMin; j<size; j++){
                if(mtx[j][i]<min){
                    min = mtx[j][i];
                }
            }

            cost += min;
            if(min!=0) {
                for (int j = 0; j < size; j++) {
                    if (mtx[j][i] != INFINITY) {
                        mtx[j][i] -= min;
                    }
                }
            }
        }
    }

    public void findFullTourCost(ArrayList<Integer> aliveNode, int nextNode){
        //mendapatkan bobot tur lengkap dari suatu matrix jika sudah melalui
        //beberapa simpul aliveNode dan akan menuju nextNode
        //mengupdate nilai cost
        ArrayList<Integer> mandatoryNode = new ArrayList<>(aliveNode);
        mandatoryNode.add(nextNode);
        cost = 0;

        for(int i=0; i<size; i++){
            int min1=0;
            int min2=0;
            if(mandatoryNode.size()<=1 || !mandatoryNode.contains(i)){
                boolean found = false;
                int idxMin1 = 0;

                if(mtx[i][0]==INFINITY){
                    idxMin1++;
                }

                min1 = mtx[i][idxMin1];

                for(int j= idxMin1+1; j<size; j++){
                    if(mtx[i][j]<min1){
                        min1 = mtx[i][j];
                        idxMin1=j;
                    }
                }

                int idxMin2 = 0;
                while(!found && idxMin2 <size){
                    if(mtx[i][idxMin2]!= INFINITY && idxMin2!=idxMin1){
                        found = true;
                        min2 = mtx[i][idxMin2];
                    } else{
                        idxMin2++;
                    }
                }

                for(int j= idxMin2+1; j<size; j++){
                    if(mtx[i][j]<min2 && j!=idxMin1){
                        min2 = mtx[i][j];
                    }
                }

            } else {
                int idx = mandatoryNode.lastIndexOf(i);
                if (idx == 0) {
                    min1 = mtx[i][mandatoryNode.get(idx+1)];

                    int idxMin = 0;
                    while(mtx[i][idxMin]==INFINITY || idxMin==mandatoryNode.get(idx+1)){
                        idxMin++;
                    }

                    min2 = mtx[i][idxMin];

                    for(int j= idxMin+1; j<size; j++){
                        if(mtx[i][j]<min1 && idxMin!=mandatoryNode.get(idx+1)){
                            min2 = mtx[i][j];
                        }
                    }

                } else if (mandatoryNode.lastIndexOf(i) == mandatoryNode.size() - 1) {
                    min1 = mtx[i][mandatoryNode.get(idx-1)];

                    int idxMin = 0;

                    while(mtx[i][idxMin]==INFINITY || idxMin==mandatoryNode.get(idx-1)){
                        idxMin++;
                    }

                    min2 = mtx[i][idxMin];

                    for(int j= idxMin+1; j<size; j++){
                        if(mtx[i][j]<min1 && idxMin!=mandatoryNode.get(idx-1)){
                            min2 = mtx[i][j];
                        }
                    }
                } else {
                    min1 = mtx[i][mandatoryNode.get(idx+1)];
                    min2 = mtx[i][mandatoryNode.get(idx-1)];
                }
            }
            cost+=min1;
            cost+=min2;
        }
        cost/=2;
    }

    public void printContent(){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                System.out.print(mtx[i][j]+" ");
            }
            System.out.println(" ");
        }
    }

}
