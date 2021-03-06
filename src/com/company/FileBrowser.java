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

/*
FileBrowser class that handles the front end file input and returns the file path to the distance vector class
 */
public class FileBrowser extends JFrame {
    private JTextField filename = new JTextField(), dir = new JTextField();
    private JButton open = new JButton("Open"), save = new JButton("Save");
    private JTextField fileInput = new JTextField(15);//declare jtextfield
    private ArrayList<Nodes> nodeList = new ArrayList();//declare nodelist

    //FileBrowser constructor constructs the panels and container to build the GUI for the fb
    public FileBrowser() {
        JPanel p = new JPanel();
        p.add(open);
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

    //retrieve the file path
    public String getFilePath() {
        return filename.getName();
    }

    //passes the nodeList
    public ArrayList getNodeList() {
        return nodeList;
    }

    //set the nodeList
    public void setNodeList(ArrayList nl) {
        this.nodeList = nl;
    }

    //selects file and passes
    public int selectFile() {
        JFileChooser c = new JFileChooser();
        // Demonstrate "Open" dialog:
        int rVal = c.showOpenDialog(FileBrowser.this);
        if (rVal == JFileChooser.APPROVE_OPTION) {
            filename.setName(c.getSelectedFile().getName());
            dir.setText(c.getCurrentDirectory().toString());
            System.out.println(filename.getName());
        }
        if (rVal == JFileChooser.CANCEL_OPTION) {
            filename.setName("");
            dir.setText("");
        }
        return rVal;
    }


}
