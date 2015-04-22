package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Lam_2 on 4/8/2015.
 */
public class DVRGUI extends JFrame {

    public static JFrame myFrame = new JFrame("Distance Vector Routing");
    public static JPanel jPanel1,jPanel2;
    public JMenu File, Help;
    public JMenuItem loadFile,exit;

    FileBrowser fileBrowser = new FileBrowser();
    FileBrowser fb = new FileBrowser();
    DistanceVector dv = new DistanceVector(fb.getNodeList());
    public DVRGUI()
    {
        //System.out.println(fb.getNodeList().get(0));
        JMenuBar menuBar = new JMenuBar();
        myFrame.setJMenuBar(menuBar);
        File = new JMenu("File");
        loadFile = new JMenuItem("Load File");
        File.add(loadFile);
        JButton run = new JButton("Run Distance Vector Routing");
        run.addActionListener(dv);
        run.setEnabled(false);
        loadFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileBrowser.selectFile() == JFileChooser.APPROVE_OPTION)
                {
                    String filename = fileBrowser.getFilePath();
                    dv.setFileName(filename);
                    dv.loadFile();
                    run.setEnabled(true);
                }

            }
        });
        exit = new JMenuItem("Exit");
        File.add(exit);
        menuBar.add(File);
        jPanel1 = new JPanel();
        jPanel1.setPreferredSize(new Dimension(750, 600));
        jPanel1.setBackground(Color.white);
       // jPanel1.add(run, BorderLayout.PAGE_END);
        myFrame.add(jPanel1, BorderLayout.WEST);


        jPanel2 = new JPanel();
        jPanel2.setPreferredSize(new Dimension(250, 200));
        jPanel2.setBackground(Color.white);
        jPanel2.add(run);
        myFrame.add(jPanel2,BorderLayout.EAST);
        //myFrame.pack();
        myFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        myFrame.setSize(1000,600);
        myFrame.setVisible(true);
    }


}
