package com.company;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.MouseInputListener;

/**
 * Created by Lam_2 on 4/8/2015.
 */
public class FileBrowser extends JFrame implements ActionListener {
    private JTextField filename = new JTextField(), dir = new JTextField();
    private JButton open = new JButton("Open"), save = new JButton("Save");
    private JTextField fileInput = new JTextField(15);//declare jtextfield
    private ArrayList<Nodes> nodeList = new ArrayList();//declare nodelist
    int match = 0;
    int iteration = 1;
    int clicked = 1;

    public FileBrowser() {
        JPanel p = new JPanel();
        open.addActionListener(new OpenL());
        p.add(open);
        save.addActionListener(new SaveL());
        p.add(save);
        Container cp = getContentPane();
        cp.add(p, BorderLayout.SOUTH);
        dir.setEditable(false);
        filename.setEditable(false);
        p = new JPanel();
        p.setLayout(new GridLayout(2, 1));
        p.add(filename);
        p.add(dir);
        cp.add(p, BorderLayout.NORTH);
    }

    public String getFilePath() {
        return filename.getName();
    }

    public ArrayList getNodeList() {
        return nodeList;
    }

    public void setNodeList(ArrayList nl) {
        this.nodeList = nl;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser c = new JFileChooser();
        // Demonstrate "Open" dialog:
        int rVal = c.showOpenDialog(FileBrowser.this);
        if (rVal == JFileChooser.APPROVE_OPTION) {
            filename.setName(c.getSelectedFile().getName());
            dir.setText(c.getCurrentDirectory().toString());
            System.out.println(filename.getName());
        }

        if (rVal == JFileChooser.CANCEL_OPTION) {
            filename.setText("You pressed cancel");
            dir.setText("");
        }

        // Demonstrate "Open" dialog:
        String[] token;
        BufferedReader reader = null;
        String line = null;

        try {
            reader = new BufferedReader(new FileReader(filename.getName()));
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

                //setNodeList(nodeList);


            }
            System.out.println("----------------Start tables----------------");//formatting print
            Utils util = new Utils();
            for (int index = 0; index < nodeList.size(); index++)//loop to node list size
            {
                Nodes node = nodeList.get(index);
                util.buildTable(nodeList.size(), node);

                util.gridPrint(node);
                ;//print the nodes for the build table
                System.out.print("\n");//print a new line
            }

            System.out.println("******Finsihed \n");//formatting print
            JFrame remote = new JFrame("Run Iteration");
            JPanel remotePanel = new JPanel();
<<<<<<< HEAD


            JButton clicker = new JButton("Next Iteration");
            clicker.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("pressed");
                    clicked++;
=======


            JButton clicker = new JButton("Next Iteration");
            clicker.addMouseListener(new MouseInputListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }

                @Override
                public void mouseDragged(MouseEvent e) {

>>>>>>> origin/master
                }
            });

<<<<<<< HEAD

=======
                @Override
                public void mouseMoved(MouseEvent e) {

                }
                public boolean clicker(MouseEvent e) {
                    return true;
                }
            });

            remotePanel.add(clicker);
            remote.add(remotePanel);
            remote.setSize(400,200);
            remote.setVisible(true);
>>>>>>> origin/master
            while (match != (nodeList.size() * nodeList.size()))//while not all tables match
            {
                match = 0;//match set to 0
                //System.out.println("Iteration number : " + iteration + " \n");//formatting print
                for (int index = 0; index < nodeList.size(); index++)//loop to all the nodes
                {
                    JFrame jf = new JFrame("Iteration " + iteration);
                    JTextArea txt = new JTextArea();
                    JLabel lbl = new JLabel();
                    JPanel jp = new JPanel();

                    for (int innerIndex = 0; innerIndex < nodeList.get(index).neighbour.size(); innerIndex++)//loop to neighbours of those nodes
                    {

                        Nodes node = nodeList.get(index);
                        int foundNei = (new Utils().nodeMatch(nodeList.get(index).neighbour.get(innerIndex), nodeList));//find the node neighbour

                        util.updateNodes(nodeList.get(foundNei).getGrid(), nodeList.size(), nodeList.get(foundNei).getId(), node);//pass to update with nighbour grid, size of list, ID of neighbour
                    }
<<<<<<< HEAD

                    //some how provide a counter to run through the iteration
                    ArrayList<String> nodeArray = new ArrayList<String>();
                    for (int[] i : nodeList.get(index).getGrid()) {
                        lbl.setText("Grid for " + nodeList.get(index).getId());
                        nodeArray.add(Arrays.toString(i) + "\n");
                    }

                    // nodeArray.add(Arrays.toString(nodeList.get(index)));
                    txt.setText(nodeArray.toString());
                    jp.add(lbl, BorderLayout.NORTH);
                    jp.add(txt);
                    jf.add(jp);
                    remotePanel.add(clicker);
                    remote.add(remotePanel);
                    remote.setSize(400,200);
                    remote.setVisible(true);
//                    while(clicked <= nodeList.size()) {

                        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                        jf.setSize(300, 300);
                        jf.setVisible(true);

//                    }

                    //System.out.println("Grid for node " + nodeList.get(index).getId());//formatting print
                    //nodeList.get(index).gridPrint();//print grid for node
                    //System.out.print("\n");
                }
                iteration++;//increment iteration number
=======
>>>>>>> origin/master

                    //some how provide a counter to run through the iteration

<<<<<<< HEAD
                for (int index = 0; index < nodeList.size(); index++)//loop to node list size
                {
                    for (int innerIndex = 0; innerIndex < nodeList.size(); innerIndex++)//loop to node list size
                    {
                        if (new Utils().gridCheck(nodeList.get(index).getGrid(), nodeList.get(innerIndex).getGrid(), nodeList.size()))//if node matches it returns true
                        {
                            match++;//check if tables match
                        }
=======
                    ArrayList<String> nodeArray = new ArrayList<String>();
                    for (int[] i : nodeList.get(index).getGrid()) {
                        lbl.setText("Grid for " + nodeList.get(index).getId());
                        nodeArray.add(Arrays.toString(i) + "\n");
>>>>>>> origin/master
                    }


                    // nodeArray.add(Arrays.toString(nodeList.get(index)));
                    txt.setText(nodeArray.toString());
                    jp.add(lbl, BorderLayout.NORTH);
                    jp.add(txt);
                    jf.add(jp);
                    jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                    jf.setSize(300, 300);
                    jf.setVisible(true);
                    //System.out.println("Grid for node " + nodeList.get(index).getId());//formatting print
                    //nodeList.get(index).gridPrint();//print grid for node
                    //System.out.print("\n");
                }
<<<<<<< HEAD
            }

            System.out.println("******Final tables");//formatting print

=======
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

>>>>>>> origin/master
            for (int index = 0; index < nodeList.size(); index++)//loop to all the nodes
            {
                System.out.println("Last grid for " + nodeList.get(index));//clean print
                nodeList.get(index).gridPrint();
                System.out.print("\n");
            }


        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }


    class OpenL implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser c = new JFileChooser();
            // Demonstrate "Open" dialog:
            int rVal = c.showOpenDialog(FileBrowser.this);
            if (rVal == JFileChooser.APPROVE_OPTION) {
                filename.setText(c.getSelectedFile().getName());
                dir.setText(c.getCurrentDirectory().toString());
            }
            if (rVal == JFileChooser.CANCEL_OPTION) {
                filename.setText("You pressed cancel");
                dir.setText("");
            }
        }
    }

    class SaveL implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser c = new JFileChooser();
            // Demonstrate "Save" dialog:
            int rVal = c.showSaveDialog(FileBrowser.this);
            if (rVal == JFileChooser.APPROVE_OPTION) {
                filename.setText(c.getSelectedFile().getName());
                dir.setText(c.getCurrentDirectory().toString());
            }
            if (rVal == JFileChooser.CANCEL_OPTION) {
                filename.setText("You pressed cancel");
                dir.setText("");
            }
        }
    }
}
