package com.stima;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Aidin Ahmad on 04/04/2017.
 */

public class TSP {
    CostMtx costMtx;
    ArrayList<Integer> aliveNode;
    int currentNode;
    private static final int INFINITY = 999;

    public TSP(String filename) {
        ArrayList<Integer> content = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File(filename));
            while (scanner.hasNextInt()) {
                content.add(scanner.nextInt());
            }
        } catch (IOException e){

        }

        int size = (int) Math.sqrt(content.size());
        int[][] mtxGraph = new int[size][size];

        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                int elmt = content.get(i*size+j);
                mtxGraph[i][j]=elmt;
            }
        }
        aliveNode = new ArrayList<>();
        currentNode = 0;
        costMtx = new CostMtx(mtxGraph,size,0);
    }

    public TSP(CostMtx costMtx, int size, ArrayList<Integer> aliveNode, int currentNode, int cost){
        this.costMtx = new CostMtx(costMtx);
        for (int i =0; i<aliveNode.size(); i++){
            this.aliveNode.add(aliveNode.get(i));
        }
        this.currentNode = currentNode;
    }

    public TSP( TSP tsp){
        this.costMtx = new CostMtx(tsp.costMtx);
        aliveNode = new ArrayList<>();
        for (int i =0; i<tsp.aliveNode.size(); i++){
            this.aliveNode.add(tsp.aliveNode.get(i));
        }
        this.currentNode = tsp.currentNode;
    }

    public boolean isSolution(){
        boolean getSolution= true;
        int i=0;
        while(getSolution && i< costMtx.getSize()) {
            if(!aliveNode.contains(i)){
                getSolution=false;
            }
            i++;
        }
        return getSolution;
    }

    public ArrayList<Integer> riseChild() {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < costMtx.getSize(); i++) {
            if (!aliveNode.contains(i)) {
                result.add(i);
            }
        }
        return result;
    }

    public void initBnB(){
        aliveNode.add(currentNode);
        costMtx.reduceCost();
    }

    public void BnB(){
        if(isSolution()){

        } else {
            ArrayList<Integer> children = riseChild();
            CostMtx[] childrenMtx= new CostMtx[children.size()];

            int size = costMtx.getSize();

            for(int idx=0; idx<children.size(); idx++){
                CostMtx newCostMtx = new CostMtx(costMtx);
                int addition = costMtx.getMtx()[currentNode][children.get(idx)];
                newCostMtx.addCost(addition);
                for(int i=0; i<size; i++){
                    newCostMtx.change(currentNode,i,INFINITY);
                }
                for(int i=0; i<size; i++){
                    newCostMtx.change(i,children.get(idx),INFINITY);
                }
                newCostMtx.change(children.get(idx),0, INFINITY);
                newCostMtx.reduceCost();
                childrenMtx[idx] = newCostMtx;

            }

            if(childrenMtx.length!=0) {
                int minCost = childrenMtx[0].getCost();
                int idxMin = 0;
                for (int idx = 1; idx < children.size(); idx++) {
                    if(childrenMtx[idx].getCost()<minCost){
                        minCost = childrenMtx[idx].getCost();
                        idxMin = idx;
                    }
                }
                this.costMtx = childrenMtx[idxMin];
                currentNode = children.get(idxMin);
                aliveNode.add(currentNode);
                BnB();
            }
        }
    }


    public void initFullTourCost(){
        costMtx.findFullTourCost(aliveNode,currentNode);
        aliveNode.add(0);
    }

    public void fullTourCost(){
        if(isSolution()){

        } else {
            ArrayList<Integer> children = riseChild();

            CostMtx[] childrenMtx= new CostMtx[children.size()];

            int size = costMtx.getSize();

            for(int idx=0; idx<children.size(); idx++){
                CostMtx newCostMtx = new CostMtx(costMtx);
                newCostMtx.findFullTourCost(aliveNode,children.get(idx));
                childrenMtx[idx] = newCostMtx;
            }

            if(childrenMtx.length!=0) {
                ArrayList<TSP> nextOpt = new ArrayList<>();

                int minCost = childrenMtx[0].getCost();
                int idxMin = 0;
                for (int idx = 1; idx < children.size(); idx++) {
                    if (childrenMtx[idx].getCost() < minCost) {
                        minCost = childrenMtx[idx].getCost();
                        idxMin = idx;
                    }
                }

                for (int idx = 0; idx < children.size(); idx++) {
                    if(childrenMtx[idx].getCost()==minCost) {
                        TSP nextChild = new TSP(this);
                        nextChild.costMtx = childrenMtx[idx];
                        nextChild.currentNode = children.get(idx);
                        nextChild.aliveNode.add(nextChild.currentNode);
                        nextChild.fullTourCost();
                        nextOpt.add(nextChild);
                    }
                }

                idxMin = 0;
                minCost = nextOpt.get(0).costMtx.getCost();
                for (int i = 1; i<nextOpt.size(); i++){
                    if(nextOpt.get(i).costMtx.getCost()==minCost){
                        idxMin = i;
                    }
                }

                this.costMtx = nextOpt.get(idxMin).costMtx;
                this.currentNode = nextOpt.get(idxMin).currentNode;
                this.aliveNode = nextOpt.get(idxMin).aliveNode;
            }
        }
    }

    public void printContent(){
        for(int i = 0; i< costMtx.getSize(); i++){
            for(int j = 0; j< costMtx.getSize(); j++){
                System.out.print(costMtx.getMtx()[i][j]+" ");
            }
            System.out.println(" ");
        }
    }

    public void printPath(){
        for(int i = 0; i< aliveNode.size(); i++){
             System.out.print(aliveNode.get(i)+" ");
        }
    }
}
