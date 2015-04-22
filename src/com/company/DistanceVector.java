package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Lam_2 on 4/8/2015.
 */
public class DistanceVector implements ActionListener {
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

    DistanceVector(ArrayList nl) {
        this.nodeList = nl;
        //System.out.println(nl.get(0));


    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("running Distance Vector Algorithm");


        if (clicked <= nodeList.size()) {

            runItr(clicked);
            clicked++;
        }


    }


    public void loadFile() {

        try {
            reader = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        try {
            while ((line = reader.readLine()) != null) {
                token = line.split(" ");
                int n1 = Integer.parseInt(token[0]);
                int n2 = Integer.parseInt(token[1]);
                int val = Integer.parseInt(token[2]);
                boolean flag1 = false;
                boolean flag2 = false;

                for (int i = 0; i < nodeList.size(); i++) {
                    flag1 = (nodeList.get(i).getId() == n1) ? true : flag1;
                    //get second node to see if it exits in array
                    flag2 = (nodeList.get(i).getId() == n2) ? true : flag2;
                }

                if (!flag1) {
                    Nodes newNode = new Nodes(n1);//create the node
                    nodeList.add(newNode);
                    flag1 = false;//set flag back to false
                }

                if (!flag2)//if it doesn't exit
                {
                    Nodes newNode = new Nodes(n2);//create the node
                    nodeList.add(newNode);
                    flag2 = false;//set flag back to false
                }

                for (int index = 0; index < nodeList.size(); index++)//loop to node list size
                {
                    if (nodeList.get(index).getId() == n1)//if id of node matches input
                    {
                        nodeList.get(index).setNeighbour(n2);//set neighbour
                        nodeList.get(index).setCost(val);//set cost
                    }
                    if (nodeList.get(index).getId() == n2)//if id of node matches input
                    {
                        nodeList.get(index).setNeighbour(n1);//set neighbour
                        nodeList.get(index).setCost(val);//set cost
                    }
                    System.out.println(nodeList.get(index));
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("----------------Start tables----------------");//formatting print

        for (int index = 0; index < nodeList.size(); index++)//loop to node list size
        {
            Nodes node = nodeList.get(index);
            util.buildTable(nodeList.size(), node);

            util.gridPrint(node);
            ;//print the nodes for the build table
            System.out.print("\n");//print a new line
        }

        System.out.println("******Finsihed \n");//formatting print

    }

    public void setFileName(String fn) {
        this.filename = fn;
    }

    public void runItr(int click) {
        if (match != (nodeList.size() * nodeList.size()))//while not all tables match
        {
            match = 0;//match set to 0

            if(taLst.isEmpty()) {
                for(int index=0; index < nodeList.size(); index++) {
                    JTextArea txt = new JTextArea();
                    JLabel lbl = new JLabel();
                    taLst.add(txt);
                    lbLst.add(lbl);
                }
            }

            //System.out.println("Iteration number : " + iteration + " \n");//formatting print
            for (int index = 0; index < nodeList.size(); index++)//loop to all the nodes
            {
                JTextArea txt = taLst.get(index);
                JLabel lbl = lbLst.get(index);

                for (int innerIndex = 0; innerIndex < nodeList.get(index).neighbour.size(); innerIndex++)//loop to neighbours of those nodes
                {

                    Nodes node = nodeList.get(index);
                    int foundNei = (new Utils().nodeMatch(nodeList.get(index).neighbour.get(innerIndex), nodeList));//find the node neighbour

                    util.updateNodes(nodeList.get(foundNei).getGrid(), nodeList.size(), nodeList.get(foundNei).getId(), node);//pass to update with nighbour grid, size of list, ID of neighbour
                }


                //some how provide a counter to run through the iteration
                ArrayList<String> nodeArray = new ArrayList<String>();
                for (int[] i : nodeList.get(index).getGrid()) {
                    lbl.setText("Grid for " + nodeList.get(index).getId());
                    nodeArray.add(Arrays.toString(i) + "\n");
                }

                // nodeArray.add(Arrays.toString(nodeList.get(index)));
                txt.setText(nodeArray.toString());
                DVRGUI.jPanel1.add(lbl);
                DVRGUI.jPanel1.add(txt);


                //jp.add(lbl, BorderLayout.NORTH);
                //jp.add(txt);
                //jf.add(jp);


//                System.out.println("Grid for node " + nodeList.get(index).getId());//formatting print
//                nodeList.get(index).gridPrint();//print grid for node
//                System.out.print("\n");
            }
            DVRGUI.jPanel2.add(itr = new JLabel("Iteration: " + iteration));

            iteration++;//increment iteration number

            DVRGUI.jPanel1.revalidate();
            DVRGUI.jPanel1.repaint();
            DVRGUI.jPanel2.revalidate();
            DVRGUI.jPanel2.repaint();

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


    }


}

