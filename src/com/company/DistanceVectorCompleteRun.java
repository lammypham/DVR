package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Lam_2 on 4/22/2015.
 */
public class DistanceVectorCompleteRun implements ActionListener {
    private JTextField fileInput = new JTextField(15);//declare jtextfield
    private ArrayList<Nodes> nodeList = new ArrayList();//declare nodelist
    int match = 0;
    int iteration = 1;
    String[] token;
    BufferedReader reader = null;
    String line = null;
    String filename;
    int clicked = 1;
    Utils util = new Utils();
    JLabel itr;
    List<JTextArea> taLst = new ArrayList<>();
    List<JLabel> lbLst = new ArrayList<>();

    public void actionPerformed(ActionEvent e) {
        System.out.println("running Distance Vector Algorithm");

        //runs only when the user has clicked less than the size of nodes in the list

        if (clicked <= nodeList.size()) {

            runComplete(clicked);
            clicked++;
        }
    }

    //loads the file given and intializes the tables
    public void loadFile() {

        try {
            reader = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        try {
            while ((line = reader.readLine()) != null) {
                token = line.split(" ");
                int n1 = Integer.parseInt(token[0]); // node
                int n2 = Integer.parseInt(token[1]); // next
                int val = Integer.parseInt(token[2]); // cost

                //flags to check if values are not null
                boolean flag1 = false;
                boolean flag2 = false;

                for (int i = 0; i < nodeList.size(); i++) {
                    flag1 = (nodeList.get(i).getId() == n1) ? true : flag1;
                    //get second node to see if it exits in array
                    flag2 = (nodeList.get(i).getId() == n2) ? true : flag2;
                }
                //create the nodes if they do not exist
                if (!flag1) {
                    Nodes newNode = new Nodes(n1);
                    nodeList.add(newNode);
                    flag1 = false; //reset
                }

                if (!flag2)//if it doesn't exit
                {
                    Nodes newNode = new Nodes(n2);
                    nodeList.add(newNode);
                    flag2 = false; // reset
                }
                //intialize the grid and set values
                for (int idx = 0; idx < nodeList.size(); idx++)//loop to node list size
                {
                    if (nodeList.get(idx).getId() == n1)//if id of node matches input
                    {
                        nodeList.get(idx).setNeighbour(n2);//set neighbour
                        nodeList.get(idx).setCost(val);//set cost
                    }
                    if (nodeList.get(idx).getId() == n2)//if id of node matches input
                    {
                        nodeList.get(idx).setNeighbour(n1);//set neighbour
                        nodeList.get(idx).setCost(val);//set cost
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //loop to node list size to build the grid
        for (int idx = 0; idx < nodeList.size(); idx++) {
            Nodes node = nodeList.get(idx);
            util.buildTable(nodeList.size(), node);
        }
    }

    public void setFileName(String fn) {
        this.filename = fn;
    }

    public void runItr(int click) {
        if (match != (nodeList.size() * nodeList.size()))//while not all tables match
        {
            match = 0;//match set to 0

            if (taLst.isEmpty()) {
                for (int idx = 0; idx < nodeList.size(); idx++) {
                    JTextArea txt = new JTextArea(6, 6);
                    JLabel lbl = new JLabel();
                    taLst.add(txt);
                    lbLst.add(lbl);
                }
            }

            for (int idx = 0; idx < nodeList.size(); idx++)//loop to all the nodes
            {
                //build text and label area for every node and their associative grid
                JTextArea txt = taLst.get(idx);
                JLabel lbl = lbLst.get(idx);

                for (int innerIdx = 0; innerIdx < nodeList.get(idx).neighbour.size(); innerIdx++)//loop to neighbours of those nodes
                {
                    //grab nodes
                    Nodes node = nodeList.get(idx);
                    int foundNxt = (new Utils().nodeMatch(nodeList.get(idx).neighbour.get(innerIdx), nodeList));//find the node neighbour

                    util.updateNodes(nodeList.get(foundNxt).getGrid(), nodeList.size(), nodeList.get(foundNxt).getId(), node);//pass to update with nighbour grid, size of list, ID of neighbour
                }

                //run through the iterations and create the list to add to the text field
                ArrayList<String> nodeArray = new ArrayList<String>();
                for (int[] i : nodeList.get(idx).getGrid()) {
                    lbl.setText("Table: " + nodeList.get(idx).getId());
                    //remove brackets and commas for asthetics
                    nodeArray.add(Arrays.toString(i).replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\,", "").replaceAll(" ", "  ") + "\n");
                }

                txt.setText(nodeArray.toString().replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\,", ""));
                //add the fields to the panels
                DVRGUI.jPanel1.add(lbl);
                DVRGUI.jPanel1.add(txt);


            }
            DVRGUI.jPanel2.add(itr = new JLabel("Iteration: " + iteration));
            //increment iteration
            iteration++;//increment iteration number
            //refresh the panels
            DVRGUI.jPanel1.revalidate();
            DVRGUI.jPanel1.repaint();
            DVRGUI.jPanel2.revalidate();
            DVRGUI.jPanel2.repaint();
            //validation check
            for (int idx = 0; idx < nodeList.size(); idx++)//loop to node list size
            {
                for (int innerIdx = 0; innerIdx < nodeList.size(); innerIdx++)//loop to node list size
                {
                    if (new Utils().gridCheck(nodeList.get(idx).getGrid(), nodeList.get(innerIdx).getGrid(), nodeList.size()))//if node matches it returns true
                    {
                        match++;//check if tables match
                    }
                }
            }
        }
    }

    public void runComplete(int click) {
        JTextField jtxt = new JTextField();
        long startTime = System.nanoTime();
        while (match != (nodeList.size() * nodeList.size()))//while not all tables match
        {
            match = 0;//match set to 0

            if (taLst.isEmpty()) {
                for (int idx = 0; idx < nodeList.size(); idx++) {
                    JTextArea txt = new JTextArea(6, 6);
                    JLabel lbl = new JLabel();
                    taLst.add(txt);
                    lbLst.add(lbl);
                }
            }

            for (int idx = 0; idx < nodeList.size(); idx++)//loop to all the nodes
            {
                //build text and label area for every node and their associative grid
                JTextArea txt = taLst.get(idx);
                JLabel lbl = lbLst.get(idx);

                for (int innerIdx = 0; innerIdx < nodeList.get(idx).neighbour.size(); innerIdx++)//loop to neighbours of those nodes
                {
                    //grab nodes
                    Nodes node = nodeList.get(idx);
                    int foundNxt = (new Utils().nodeMatch(nodeList.get(idx).neighbour.get(innerIdx), nodeList));//find the node neighbour

                    util.updateNodes(nodeList.get(foundNxt).getGrid(), nodeList.size(), nodeList.get(foundNxt).getId(), node);//pass to update with nighbour grid, size of list, ID of neighbour
                }

                //run through the iterations and create the list to add to the text field
                ArrayList<String> nodeArray = new ArrayList<String>();
                for (int[] i : nodeList.get(idx).getGrid()) {
                    lbl.setText("Table: " + nodeList.get(idx).getId());
                    //remove brackets and commas for asthetics
                    nodeArray.add(Arrays.toString(i).replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\,", "").replaceAll(" ", "  ") + "\n");
                }

                txt.setText(nodeArray.toString().replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\,", ""));
                //add the fields to the panels
                DVRGUI.jPanel1.add(lbl);
                DVRGUI.jPanel1.add(txt);


            }
            DVRGUI.jPanel2.add(itr = new JLabel("Iteration: " + iteration));
            //increment iteration
            iteration++;//increment iteration number
            //refresh the panels
            DVRGUI.jPanel1.revalidate();
            DVRGUI.jPanel1.repaint();
            DVRGUI.jPanel2.revalidate();
            DVRGUI.jPanel2.repaint();
            //validation check
            for (int idx = 0; idx < nodeList.size(); idx++)//loop to node list size
            {
                for (int innerIdx = 0; innerIdx < nodeList.size(); innerIdx++)//loop to node list size
                {
                    if (new Utils().gridCheck(nodeList.get(idx).getGrid(), nodeList.get(innerIdx).getGrid(), nodeList.size()))//if node matches it returns true
                    {
                        match++;//check if tables match
                    }
                }
            }
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;
        jtxt.setText("Time to run: " + duration + " ms");
        DVRGUI.jPanel2.add(jtxt);
        DVRGUI.jPanel2.revalidate();
        DVRGUI.jPanel2.repaint();
    }
}
