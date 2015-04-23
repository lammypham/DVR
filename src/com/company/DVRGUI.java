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
    public static JPanel jPanel1, jPanel2;
    public JMenu File, Help;
    public JMenuItem loadFile, exit;
    FileBrowser fileBrowser = new FileBrowser();
    FileBrowser fb = new FileBrowser();
    DistanceVector dv = new DistanceVector();
    DistanceVectorCompleteRun dvcr = new DistanceVectorCompleteRun();

    //DVR GUI interface that displays all the grids for the DVR and interation count on the right
    //button runs the iteration
    public DVRGUI() {
        JMenuBar menuBar = new JMenuBar();
        myFrame.setJMenuBar(menuBar);
        File = new JMenu("File");
        loadFile = new JMenuItem("Load File");
        File.add(loadFile);
        JButton runItr = new JButton("Run Distance Vector Routing");
        runItr.addActionListener(dv);
        runItr.setEnabled(false);
        JButton runComp = new JButton("Run Completely");
        runComp.addActionListener(dvcr);
        runComp.setEnabled(false);

        //loads the file the enalbes the button
        //sends file to dv and intializes the table
        loadFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileBrowser.selectFile() == JFileChooser.APPROVE_OPTION) {
                    String filename = fileBrowser.getFilePath();
                    dv.setFileName(filename);
                    dv.loadFile();
                    dvcr.setFileName(filename);
                    dvcr.loadFile();
                    runItr.setEnabled(true);
                    runComp.setEnabled(true);
                }

            }
        });
        exit = new JMenuItem("Exit");
        File.add(exit);
        menuBar.add(File);
        //grid panels
        jPanel1 = new JPanel();
<<<<<<< HEAD
        jPanel1.setLayout(new GridLayout(0, 2));
=======
        jPanel1.setLayout(new GridLayout(0,2));
>>>>>>> origin/master
        jPanel1.setPreferredSize(new Dimension(750, 600));
        jPanel1.setBackground(Color.white);
        myFrame.add(jPanel1, BorderLayout.WEST);

        //iteration panel and button
        jPanel2 = new JPanel();
        jPanel2.setPreferredSize(new Dimension(250, 200));
        jPanel2.setBackground(Color.white);
        jPanel2.add(runItr);
        jPanel2.add(runComp);
        myFrame.add(jPanel2, BorderLayout.EAST);
        myFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        myFrame.setSize(1000, 600);
        myFrame.setVisible(true);
    }


}
