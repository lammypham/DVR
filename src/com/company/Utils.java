package com.company;

/**
 * Created by smpham on 4/20/2015.
 */
import java.util.ArrayList;
import java.util.Arrays;

public class Utils {

    //updateRow
    public void updateNodes(int[][] position, int size1, int row, Nodes node)
    {
        int[][]grid = node.getGrid();

        for (int index = 0; index < size1; index++) {
            for (int innerIndex = 0; innerIndex < size1; innerIndex++)
            {
                if (grid[index][innerIndex] > position[index][innerIndex])//if numb in grid is lower than numb in neighbour grid
                {
                    grid[index][innerIndex] = position[index][innerIndex];//replace numb
                }
            }
        }

        for (int index = 0; index < size1; index++) {
            if (grid[node.getId() - 1][index] > position[row - 1][index] + node.getCost().get(nodeMatch(row, node))) {

                grid[node.getId() - 1][index] = position[row - 1][index] + node.getCost().get(nodeMatch(row, node));
            }
        }

        node.setGrid(grid);
    }


    public void buildTable(int size, Nodes node)//build table method
    {
        int[][]grid = new int[0][0];

        grid = new int[size][size];//declare grid to be nodelistsizeXnodelistsize

        for (int index = 0; index < size; index++)//loop from 0 to size
        {
            for (int innerIndex = 0; innerIndex < size; innerIndex++)//loop from 0 to size
            {
                grid[index][innerIndex] = 16;//change all values to max 16 if unknown
            }
        }

        grid[node.getId() - 1][node.getId() - 1] = 0;

        for (int index = 0; index < node.getNeighbour().size(); index++)//loop from l to neighbour size
        {
            if (!node.getNeighbour().isEmpty())//if neighbour is not empty
            {
                grid[node.getId() - 1][node.getNeighbour().get(index) - 1] = node.getCost().get(index);//input the cost
            }
        }

        node.setGrid(grid);
    }

    public void gridPrint(Nodes node)//method to print gird
    {
        for (int[] index : node.getGrid()) {
            System.out.println(Arrays.toString(index));
        }
    }

    public boolean gridCheck(int[][] grid, int[][] grid2, int size) //gridCheck
    {
        for (int index = 0; index < size; index++)//loop for row indexes
        {
            for (int innerIndex = 0; innerIndex < size; innerIndex++)//loop for column indexes
            {
                if (!(grid[index][innerIndex] == grid2[index][innerIndex]))//if any entry in the node grid does not matche the neighbour grid
                {
                    return false;//return false if conition is true
                }
            }
        }
        return true;//else return true
    }

    public int nodeMatch(int match, ArrayList<Nodes> nodeList) //method to see if nodes match
    {
        for (int index = 0; index < nodeList.size(); index++)//loop to node list
        {
            if (match == nodeList.get(index).getId())//compare incoming node id to node ids in list
            {
                return index;//return if match
            }
        }
        return -99;//else return -99
    }


    public int nodeMatch(int match, Nodes node) {
        for (int index = 0; index < node.getNeighbour().size(); index++) {
            if (match == node.getNeighbour().get(index)) {
                return index;
            }

        }
        return -99;
    }
}