package com.company;

/**
 * Created by Lam_2 on 4/20/2015.
 */
import java.util.ArrayList;
import java.util.Arrays;

public class Nodes //Nodes class
{
    private int id;//node id declared
    public int[][] grid;//declare arry grid
    ArrayList<Integer> neighbour = new ArrayList();//neighbour array list declared
    ArrayList<Integer> cost = new ArrayList();//cost array list declared

    public void setGrid(int[][] grid) {

        this.grid = grid;
    }

    public int[][] getGrid()// method for getting the grid
    {
        return grid;//return the grid
    }

    public Nodes(int id) //constructor to create node
    {
        this.id = id;
    }

    public int getId() //getter for id
    {
        return id;
    }

    public void setId(int id) //setter for id
    {
        this.id = id;
    }

    public ArrayList<Integer> getNeighbour() //getter for neighbour array list
    {
        return neighbour;
    }

    public void setNeighbour(int neighbour) //setter for neighbour
    {
        this.neighbour.add(neighbour);
    }

    public ArrayList<Integer> getCost() //getter for cost array list
    {
        return cost;
    }

    public void setCost(int cost) //setter for cost
    {
        this.cost.add(cost);
    }

    public void gridPrint()//method to print gird
    {
        for (int[] index : grid) {
            System.out.println(Arrays.toString(index));
        }
    }

    @Override
    public String toString() {
        return "Nodes [id = " + id + ", neighbour = " + neighbour + ", cost = " + cost + "]";
    }
}