package com.stima;

/**
 * Created by Aidin Ahmad on 06/04/2017.
 */

import javax.swing.JFrame;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import java.awt.*;
import java.util.ArrayList;


public class GraphDrawer extends JFrame
{
    public static final String REDUCED_COST = "reduceCostMatrix";
    public static final String FULL_TOUR_COST = "fullTourCost";
    private static final long serialVersionUID = -2707712944901661771L;
    private final int SIZE = 250;
    private final Point POS = new Point(300, 300);
    private Point[] pointsRoute;
    private ArrayList<Integer> routeResult;
    int[][] mtx;
    private String title;

    public int getNextNode(int node){
        int i = routeResult.indexOf(node);
        i++;
        i%=routeResult.size();
        return routeResult.get(i);
    }

    public GraphDrawer(String title, int[][] mtx){
        super(title);
        int size = mtx.length;
        this.title = title;
        this.mtx = new int[size][size];
        for(int i = 0; i<size; i++){
            for(int j = 0; j< size; j++) {
                this.mtx[i][j] = mtx[i][j];
            }
        }
    }

    public void draw(ArrayList<Integer> routeResult)
    {
        int size = mtx.length;

        pointsRoute = new Point[size];

        this.routeResult = new ArrayList<>(routeResult);

        for(int i =0; i<size; i++){
            int x = (int) (POS.x + SIZE * Math.cos(i * 2 * Math.PI / size));
            int y = (int) (POS.y + SIZE * Math.sin(i * 2 * Math.PI / size));
            Point p = new Point(x,y);
            pointsRoute[i] = p;
        }

        Object [] vertex= new Object[size];
        mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();

        graph.getModel().beginUpdate();
        try
        {   //mencetak semua vertex graph
            for(int i =0; i<size; i++) {
                int x = pointsRoute[i].x;
                int y = pointsRoute[i].y;
                String name = ""+(i+1);
                Object v = graph.insertVertex(parent, null, name, x, y, 30,
                        30,"rounded=true;strokeColor=red;fillColor=yellow");
                vertex[i]=v;
            }

            //mencetak semua edge graph
            if(title.equals(FULL_TOUR_COST)) {
                for (int i = 0; i < size; i++) {
                    for (int j = i + 1; j < size; j++) {
                        String style;
                        if (getNextNode(i) == j || getNextNode(j) == i) {
                            style = "endArrow=none;strokeColor=blue";
                        } else {
                            style = "endArrow=none;";
                        }
                        graph.insertEdge(parent, null, null, vertex[i], vertex[j], style);
                    }
                }
            } else {
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        String style;
                        if(i!=j) {
                            if (getNextNode(i) == j) {
                                style = "strokeColor=blue";
                            } else {
                                style = "";
                            }
                            graph.insertEdge(parent, null, null, vertex[i], vertex[j], style);
                        }
                    }
                }
            }

        }

        finally
        {
            graph.getModel().endUpdate();
        }

        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        getContentPane().add(graphComponent);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 320);
        setVisible(true);
    }

//    public static void main(String[] args)
//    {
//
//    }

}
