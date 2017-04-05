package com.stima;

public class Main {

    public static void main(String[] args) {
        TSP tspBnb1 = new TSP("input1.txt");
        TSP tspBnb2 = new TSP("input2.txt");
        TSP tspFullTour1= new TSP("input3.txt");
        TSP tspFullTour2 = new TSP("input4.txt");

        //B&B 1
        long startTime = System.nanoTime();
        tspBnb1.initBnB();
        tspBnb1.BnB();
        long endTime = System.nanoTime();
        long duration = (endTime - startTime)/1000000;

        System.out.println("cost: " + tspBnb1.costMtx.getCost());
        System.out.print("path :");
        tspBnb1.printPath();
        System.out.println("Excecution time: " + duration +" ms");


        //B&B 2
        startTime = System.nanoTime();
        tspBnb2.initBnB();
        tspBnb2.BnB();
        endTime = System.nanoTime();
        duration = (endTime - startTime)/1000000;

        System.out.println("cost: " + tspBnb2.costMtx.getCost());
        System.out.print("path :");
        tspBnb2.printPath();
        System.out.println("Excecution time: " + duration +" ms");

        //FullTourCost 1
        startTime = System.nanoTime();
        tspFullTour1.initFullTourCost();
        tspFullTour1.fullTourCost();
        endTime = System.nanoTime();
        duration = (endTime - startTime)/1000000;

        System.out.println("cost: " + tspFullTour1.costMtx.getCost());
        System.out.print("path :");
        tspFullTour1.printPath();
        System.out.println("Excecution time: " + duration +" ms");

        //FullTourCost 2
        startTime = System.nanoTime();
        tspFullTour2.initFullTourCost();
        tspFullTour2.fullTourCost();
        endTime = System.nanoTime();
        duration = (endTime - startTime)/1000000;

        System.out.println("cost: " + tspFullTour2.costMtx.getCost());
        System.out.print("path :");
        tspFullTour2.printPath();
        System.out.println("Excecution time: " + duration +" ms");
    }
}
