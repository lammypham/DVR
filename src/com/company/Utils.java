package com.company;

/**
 * Created by smpham on 4/20/2015.
 */

import java.util.ArrayList;
import java.util.Arrays;

public class Utils {

    //intialize table for first time
    public void buildTable(int size, Nodes node) {
        int[][] grid = new int[0][0];

        grid = new int[size][size];//declare grid to be size of nodes by size of nodes

        for (int idx = 0; idx < size; idx++) {
            for (int innerIdx = 0; innerIdx < size; innerIdx++) {
                grid[idx][innerIdx] = 16;//change all values to max 16 if unknown (infinity)
            }
        }

        grid[node.getId() - 1][node.getId() - 1] = 0;

        for (int idx = 0; idx < node.getNeighbour().size(); idx++) //loop from l to neighbour size
        {
            if (!node.getNeighbour().isEmpty()) //if neighbour is not empty
            {
                grid[node.getId() - 1][node.getNeighbour().get(idx) - 1] = node.getCost().get(idx); //input the cost
            }
        }

        node.setGrid(grid); //set the grid after building table
    }

    //update row for the smallest route value
    public void updateNodes(int[][] position, int size, int row, Nodes node) {
        int[][] grid = node.getGrid(); //intialize grid

        for (int idx = 0; idx < size; idx++) {
            for (int innerIdx = 0; innerIdx < size; innerIdx++) {
                if (grid[idx][innerIdx] > position[idx][innerIdx]) //if node value in grid is lower than node value in next grid
                {
                    grid[idx][innerIdx] = position[idx][innerIdx]; //replace the value in the node with the smaller value
                }
            }
        }

        for (int idx = 0; idx < size; idx++) {
            if (grid[node.getId() - 1][idx] > position[row - 1][idx] + node.getCost().get(nodeMatch(row, node))) {

                grid[node.getId() - 1][idx] = position[row - 1][idx] + node.getCost().get(nodeMatch(row, node));
            }
        }

        node.setGrid(grid); //reset the grid after update.
    }


    //grid check for valid
    public boolean gridCheck(int[][] grid, int[][] grid2, int size) {
        for (int idx = 0; idx < size; idx++)//loop for row indexes
        {
            for (int innerIdx = 0; innerIdx < size; innerIdx++)//loop for column indexes
            {
                if (!(grid[idx][innerIdx] == grid2[idx][innerIdx])) // check entry for valid match
                {
                    return false;
                }
            }
        }
        return true; // valid
    }

    //method to see if nodes match
    public int nodeMatch(int match, ArrayList<Nodes> nodeList) {
        for (int idx = 0; idx < nodeList.size(); idx++)//loop to node list
        {
            if (match == nodeList.get(idx).getId())//compare incoming node id to node ids in list
            {
                return idx;//return if match
            }
        }
        return -999;//else return -99
    }

    //check for stable state
    public int nodeMatch(int match, Nodes node) {
        for (int idx = 0; idx < node.getNeighbour().size(); idx++) {
            if (match == node.getNeighbour().get(idx)) {
                return idx;
            }

        }
        return -999;
    }

    //method to print grid
    public void gridPrint(Nodes node) {
        for (int[] idx : node.getGrid()) {
            System.out.println(Arrays.toString(idx));
        }
    }
}