package com.company;

/**
 * Created by Lam_2 on 4/20/2015.
 */

import java.util.ArrayList;
import java.util.Arrays;

/*
    Nodes class to build the nodes in the grid
 */
public class Nodes {
    private int id;
    public int[][] grid;
    ArrayList<Integer> neighbour = new ArrayList();
    ArrayList<Integer> cost = new ArrayList();

    //create the nodes
    public Nodes(int id) //constructor to create node
    {
        this.id = id;
    }

    //set the grid
    public void setGrid(int[][] grid) {

        this.grid = grid;
    }

    //retrieve the grid
    public int[][] getGrid() {
        return grid;//return the grid
    }

    //retrieve id
    public int getId() {
        return id;
    }

    //set id
    public void setId(int id) {
        this.id = id;
    }

    //retrieve the neighbor node list
    public ArrayList<Integer> getNeighbour() {
        return neighbour;
    }

    //set neighbor node list
    public void setNeighbour(int neighbour) {
        this.neighbour.add(neighbour);
    }

    //get id cost list
    public ArrayList<Integer> getCost() {
        return cost;
    }

    //set id cost list
    public void setCost(int cost) {
        this.cost.add(cost);
    }

    //print grid
    public void gridPrint() {
        for (int[] index : grid) {
            System.out.println(Arrays.toString(index));
        }
    }

}