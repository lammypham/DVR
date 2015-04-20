package com.company;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * Created by Lam_2 on 4/8/2015.
 */
public class FileBrowser extends JFrame implements ActionListener {
    private JTextField filename = new JTextField(), dir = new JTextField();

    private JButton open = new JButton("Open"), save = new JButton("Save");
    private JTextField fileInput = new JTextField(15);//declare jtextfield
    private ArrayList<Nodes> nodeList = new ArrayList();//declare nodelist

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
