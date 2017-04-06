package com.stima;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Aidin Ahmad on 04/04/2017.
 */

public class TSP {
    CostMatrix costMatrix;
    ArrayList<Integer> aliveNode;
    int currentNode;
    private static final int INFINITY = 999;
    int risenNodeTree;

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
        risenNodeTree = 0;
        costMatrix = new CostMatrix(mtxGraph,size,0);
    }

    public TSP(CostMatrix costMatrix, int size, ArrayList<Integer> aliveNode, int currentNode, int cost, int risenNodeTree){
        this.costMatrix = new CostMatrix(costMatrix);
        for (int i =0; i<aliveNode.size(); i++){
            this.aliveNode.add(aliveNode.get(i));
        }
        this.currentNode = currentNode;
        this.risenNodeTree = risenNodeTree;
    }

    public TSP( TSP tsp){
        this.costMatrix = new CostMatrix(tsp.costMatrix);
        aliveNode = new ArrayList<>();
        for (int i =0; i<tsp.aliveNode.size(); i++){
            this.aliveNode.add(tsp.aliveNode.get(i));
        }
        this.currentNode = tsp.currentNode;
        this.risenNodeTree= tsp.risenNodeTree;
    }

    public boolean isSolution(){
        boolean getSolution= true;
        int i=0;
        while(getSolution && i< costMatrix.getSize()) {
            if(!aliveNode.contains(i)){
                getSolution=false;
            }
            i++;
        }
        return getSolution;
    }

    public ArrayList<Integer> riseChild() {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < costMatrix.getSize(); i++) {
            if (!aliveNode.contains(i)) {
                result.add(i);
            }
        }
        return result;
    }

    public void initReducedCostMtx(){
        aliveNode.add(currentNode);
        costMatrix.reduceCost();
        risenNodeTree = 1;
    }

    public void reducedCostMatrix(){
        if(isSolution()){

        } else {
            ArrayList<Integer> children = riseChild();
            CostMatrix[] childrenMtx= new CostMatrix[children.size()];

            int size = costMatrix.getSize();

            for(int idx=0; idx<children.size(); idx++){
                CostMatrix newCostMatrix = new CostMatrix(costMatrix);
                int addition = costMatrix.getMtx()[currentNode][children.get(idx)];
                newCostMatrix.addCost(addition);
                for(int i=0; i<size; i++){
                    newCostMatrix.change(currentNode,i,INFINITY);
                }
                for(int i=0; i<size; i++){
                    newCostMatrix.change(i,children.get(idx),INFINITY);
                }
                newCostMatrix.change(children.get(idx),0, INFINITY);
                newCostMatrix.reduceCost();
                childrenMtx[idx] = newCostMatrix;
            }
            risenNodeTree+=children.size();
            if(childrenMtx.length!=0) {
                int minCost = childrenMtx[0].getCost();
                int idxMin = 0;
                for (int idx = 1; idx < children.size(); idx++) {
                    if(childrenMtx[idx].getCost()<minCost){
                        minCost = childrenMtx[idx].getCost();
                        idxMin = idx;
                    }
                }
                this.costMatrix = childrenMtx[idxMin];
                currentNode = children.get(idxMin);
                aliveNode.add(currentNode);
                reducedCostMatrix();
            }
        }
    }


    public void initFullTourCost(){
        costMatrix.findFullTourCost(aliveNode,currentNode);
        aliveNode.add(0);
        risenNodeTree = 1;
    }

    public void fullTourCost(){
        if(isSolution()){

        } else {
            ArrayList<Integer> children = riseChild();

            CostMatrix[] childrenMtx= new CostMatrix[children.size()];

            int size = costMatrix.getSize();

            for(int idx=0; idx<children.size(); idx++){
                CostMatrix newCostMatrix = new CostMatrix(costMatrix);
                newCostMatrix.findFullTourCost(aliveNode,children.get(idx));
                childrenMtx[idx] = newCostMatrix;
            }
            risenNodeTree+=children.size();
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
                        nextChild.costMatrix = childrenMtx[idx];
                        nextChild.currentNode = children.get(idx);
                        nextChild.aliveNode.add(nextChild.currentNode);
                        nextChild.fullTourCost();
                        risenNodeTree = nextChild.risenNodeTree;
                        nextOpt.add(nextChild);
                    }
                }

                idxMin = 0;
                minCost = nextOpt.get(0).costMatrix.getCost();
                for (int i = 1; i<nextOpt.size(); i++){
                    if(nextOpt.get(i).costMatrix.getCost()==minCost){
                        idxMin = i;
                    }
                }

                this.costMatrix = nextOpt.get(idxMin).costMatrix;
                this.currentNode = nextOpt.get(idxMin).currentNode;
                this.aliveNode = nextOpt.get(idxMin).aliveNode;
                this.risenNodeTree = nextOpt.get(idxMin).risenNodeTree;
            }
        }
    }

    public void printContent(){
        for(int i = 0; i< costMatrix.getSize(); i++){
            for(int j = 0; j< costMatrix.getSize(); j++){
                System.out.print(costMatrix.getMtx()[i][j]+" ");
            }
            System.out.println(" ");
        }
    }

    public void printPath(){
        for(int i = 0; i< aliveNode.size(); i++){
             System.out.print((aliveNode.get(i)+1)+" ");
        }
    }
}
