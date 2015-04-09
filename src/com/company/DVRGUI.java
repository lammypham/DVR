package com.company;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Lam_2 on 4/8/2015.
 */
public class DVRGUI extends JFrame {

    public static JFrame myFrame = new JFrame("Distance Vector Routing");
    public static JPanel jPanel1,jPanel2;
    public JMenu File, Help;
    public JMenuItem loadFile,exit;
    DistanceVector dv = new DistanceVector();
    FileBrowser fileBrowser = new FileBrowser();

    public DVRGUI()
    {
        JMenuBar menuBar = new JMenuBar();
        myFrame.setJMenuBar(menuBar);
        File = new JMenu("File");
        loadFile = new JMenuItem("Load File");
        File.add(loadFile);
        loadFile.addActionListener(fileBrowser);
        exit = new JMenuItem("Exit");
        File.add(exit);
        menuBar.add(File);
        jPanel1 = new JPanel();
        jPanel1.setPreferredSize(new Dimension(600, 600));
        jPanel1.setBackground(Color.white);
       // jPanel1.add(run, BorderLayout.PAGE_END);
        myFrame.add(jPanel1, BorderLayout.WEST);

        JButton run = new JButton("Run Distance Vector Routing");
        run.addActionListener(dv);
        jPanel2 = new JPanel();
        jPanel2.setPreferredSize(new Dimension(200, 200));
        jPanel2.setBackground(Color.white);
        jPanel2.add(run);
        myFrame.add(jPanel2,BorderLayout.EAST);
        myFrame.pack();
        myFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        myFrame.setSize(800,800);
        myFrame.setVisible(true);
    }


}
