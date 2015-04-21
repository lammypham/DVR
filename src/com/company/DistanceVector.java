package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Lam_2 on 4/8/2015.
 */
public class DistanceVector implements ActionListener {
    private JTextField fileInput = new JTextField(15);//declare jtextfield
    private ArrayList<Nodes> nodeList = new ArrayList();//declare nodelist
    int match = 0;
    int iteration = 1;

    DistanceVector(ArrayList nl) {
        this.nodeList = nl;
        //System.out.println(nl.get(0));
    }

    public void actionPerformed(ActionEvent e)
    {
        System.out.println("running Distance Vector Algorithm");
        // Demonstrate "Open" dialog:
        String[] token;
        BufferedReader reader = null;
        String line = null;
        FileBrowser fb = new FileBrowser();


            System.out.println("******Initial tables");//formatting print
            Utils util = new Utils();
            for (int index = 0; index < nodeList.size(); index++)//loop to node list size
            {
                Nodes node = nodeList.get(index);
                util.buildTable(nodeList.size(), node);

                util.gridPrint(node);;//print the nodes for the build table
                System.out.print("\n");//print a new line
            }

            System.out.println("******Finsihed \n");//formatting print

            while (match != (nodeList.size() * nodeList.size()))//while not all tables match
            {
                match = 0;//match set to 0
                System.out.println("Iteration number : " + iteration + " \n");//formatting print
                for (int index = 0; index < nodeList.size(); index++)//loop to all the nodes
                {
                    for (int innerIndex = 0; innerIndex < nodeList.get(index).neighbour.size(); innerIndex++)//loop to neighbours of those nodes
                    {

                        Nodes node = nodeList.get(index);
                        int foundNei = (new Utils().nodeMatch(nodeList.get(index).neighbour.get(innerIndex), nodeList));//find the node neighbour

                        util.updateNodes(nodeList.get(foundNei).getGrid(), nodeList.size(), nodeList.get(foundNei).getId(), node);//pass to update with nighbour grid, size of list, ID of neighbour
                    }
                    System.out.println("Grid for node " + nodeList.get(index).getId());//formatting print
                    nodeList.get(index).gridPrint();//print grid for node
                    System.out.print("\n");
                }
                iteration++;//increment iteration number


                for (int index = 0; index < nodeList.size(); index++)//loop to node list size
                {
                    for (int innerIndex = 0; innerIndex < nodeList.size(); innerIndex++)//loop to node list size
                    {
                        if (new Utils().gridCheck(nodeList.get(index).getGrid(), nodeList.get(innerIndex).getGrid(), nodeList.size()))//if node matches it returns true
                        {
                            match++;//check if tables match
                        }
                    }
                }
            }

            System.out.println("******Final tables");//formatting print

            for (int index = 0; index < nodeList.size(); index++)//loop to all the nodes
            {
                System.out.println("Last grid for " + nodeList.get(index));//clean print
                nodeList.get(index).gridPrint();
                System.out.print("\n");
            }

    }

}

