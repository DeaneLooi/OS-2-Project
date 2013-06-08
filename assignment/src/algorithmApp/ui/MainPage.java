package algorithmApp.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import algorithmApp.*;

public class MainPage extends JPanel {

	/**
	 * Create the panel.
	 */
	
	JFrame f;
	private JTextField outPut;
	
	public MainPage(JFrame frame) {
		f = frame;
		
		setBounds(new Rectangle(0, 0, 500, 400));
		setLayout(null);
		
		JLabel header = new JLabel("Disk Optimization Algorithm Test");
		header.setFont(new Font("Franklin Gothic Demi Cond", Font.BOLD, 15));
		header.setToolTipText("");
		header.setBounds(125, 10, 230, 35);
		add(header);
		
		JButton btnFCFS = new JButton("FCFS");
		btnFCFS.setBounds(75, 80, 90, 25);
		add(btnFCFS);
		
		JButton btnLook = new JButton("Look");
		btnLook.setBounds(320, 80, 90, 25);
		add(btnLook);
		
		JButton btnScan = new JButton("Scan");
		btnScan.setBounds(75, 160, 90, 25);
		add(btnScan);
		
		JButton btnSSTF = new JButton("SSTF");
		btnSSTF.setBounds(320, 160, 90, 25);
		add(btnSSTF);
		
		outPut = new JTextField();
		outPut.setBounds(75, 245, 335, 130);
		add(outPut);
		outPut.setColumns(10);

	}
}
