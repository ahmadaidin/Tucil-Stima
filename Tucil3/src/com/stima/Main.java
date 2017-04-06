package com.stima;

public class Main {
    public static final String REDUCED_COST = "reduceCostMatrix";
    public static final String FULL_TOUR_COST = "fullTourCost";

    public static void main(String[] args) {
        TSP tspReducedCostMtx1 = new TSP("input1.txt");
        TSP tspReducedCostMtx2 = new TSP("input2.txt");
        TSP tspFullTour1= new TSP("input3.txt");
        TSP tspFullTour2 = new TSP("input4.txt");
        long startTime;
        long endTime;
        long duration;

        //Reduced Cost Matrix 1
        GraphDrawer drawer1 = new GraphDrawer(REDUCED_COST,tspReducedCostMtx1.costMatrix.getMtx());
        startTime = System.nanoTime();
        tspReducedCostMtx1.initReducedCostMtx();
        tspReducedCostMtx1.reducedCostMatrix();
        endTime = System.nanoTime();
        duration = (endTime - startTime)/1000000;

        System.out.print("rute terpendek:");
        tspReducedCostMtx1.printPath();
        System.out.println();
        System.out.println("bobot: " + tspReducedCostMtx1.costMatrix.getCost());
        System.out.println("jumlah simpul yang dibangkintkan: " + tspReducedCostMtx1.risenNodeTree);
        System.out.println("Excecution time: " + duration +" ms");
        drawer1.draw(tspReducedCostMtx1.aliveNode);

        System.out.println();
        System.out.println();

        //Reduced Cost Matrix 2
        GraphDrawer drawer2 = new GraphDrawer(REDUCED_COST,tspReducedCostMtx2.costMatrix.getMtx());
        startTime = System.nanoTime();
        tspReducedCostMtx2.initReducedCostMtx();
        tspReducedCostMtx2.reducedCostMatrix();
        endTime = System.nanoTime();
        duration = (endTime - startTime)/1000000;

        System.out.print("rute terpendek :");
        tspReducedCostMtx2.printPath();
        System.out.println();
        System.out.println("bobot: " + tspReducedCostMtx2.costMatrix.getCost());
        System.out.println("jumlah simpul yang dibangkintkan: " + tspReducedCostMtx2.risenNodeTree);
        System.out.println("Excecution time: " + duration +" ms");
        drawer2.draw(tspReducedCostMtx2.aliveNode);

        System.out.println();
        System.out.println();

        //Full Tour Cost 1
        GraphDrawer drawer3 = new GraphDrawer(FULL_TOUR_COST,tspFullTour1.costMatrix.getMtx());
        startTime = System.nanoTime();
        tspFullTour1.initFullTourCost();
        tspFullTour1.fullTourCost();
        endTime = System.nanoTime();
        duration = (endTime - startTime)/1000000;

        System.out.print("rute terpendek :");
        tspFullTour1.printPath();
        System.out.println();
        System.out.println("bobot: " + tspFullTour1.costMatrix.getCost());
        System.out.println("jumlah simpul yang dibangkintkan: " + tspFullTour1.risenNodeTree);
        System.out.println("Excecution time: " + duration +" ms");
        drawer3.draw(tspFullTour1.aliveNode);

        System.out.println();
        System.out.println();

        //Full Tour Cost 2
        GraphDrawer drawer4 = new GraphDrawer(FULL_TOUR_COST,tspFullTour2.costMatrix.getMtx());
        startTime = System.nanoTime();
        tspFullTour2.initFullTourCost();
        tspFullTour2.fullTourCost();
        endTime = System.nanoTime();
        duration = (endTime - startTime)/1000000;

        System.out.print("rute terpendek :");
        tspFullTour2.printPath();
        System.out.println();
        System.out.println("bobot: " + tspFullTour2.costMatrix.getCost());
        System.out.println("jumlah simpul yang dibangkintkan: " + tspFullTour2.risenNodeTree);
        System.out.println("Excecution time: " + duration +" ms");
        drawer4.draw(tspFullTour2.aliveNode);
    }
}
