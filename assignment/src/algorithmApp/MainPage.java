package algorithmApp;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import algorithm.DiskOptimization;
import algorithm.DiskParameter;
import javax.swing.JTextPane;

public class MainPage extends JPanel {

	/**
	 * Create the panel.
	 */

	JFrame f;
	JTextPane outPut = new JTextPane();
	String fileName = "";
	JFileChooser jFileChooser = null;
	private JTextField jTextFieldFile;

	/**
	 * @wbp.parser.constructor
	 */
	public MainPage(JFrame frame) {
		f = frame;

		setBounds(new Rectangle(0, 0, 1350, 730));
		setLayout(null);

		JLabel header = new JLabel("Disk Optimization Algorithm Test");
		header.setFont(new Font("Franklin Gothic Demi Cond", Font.BOLD, 15));
		header.setToolTipText("");
		header.setBounds(580, 10, 230, 35);
		add(header);

		JButton btnFCFS = new JButton("FCFS");
		btnFCFS.setFont(new Font("Franklin Gothic Heavy", Font.BOLD, 15));
		btnFCFS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DiskOptimization DO = new DiskOptimization(fileName);
				int[] sequence = DO.dp.getSequence();
				String text = DO.printSequence("FCFS", sequence);
				outPut.setText(text);
			}
		});
		btnFCFS.setBounds(500, 100, 100, 50);
		add(btnFCFS);

		JButton btnLook = new JButton("Look");
		btnLook.setFont(new Font("Franklin Gothic Heavy", Font.BOLD, 15));
		btnLook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DiskOptimization DO = new DiskOptimization(fileName);
				int[] sequence = DO.arrangeByLook(DO.dp.getPrevious(),
						DO.dp.getCurrent(), DO.dp.getSequence());
				String text = DO.printSequence("Look", sequence);
				outPut.setText(text);
			}
		});
		btnLook.setBounds(800, 100, 100, 50);
		add(btnLook);

		JButton btnScan = new JButton("Scan");
		btnScan.setFont(new Font("Franklin Gothic Heavy", Font.BOLD, 15));
		btnScan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DiskOptimization DO = new DiskOptimization(fileName);
				int[] sequence = DO.arrangeByScan(DO.dp.getPrevious(),
						DO.dp.getCurrent(), DO.dp.getSequence());
				String text = DO.printSequence("Scan", sequence);

				outPut.setText(text);
			}
		});
		btnScan.setBounds(500, 200, 100, 50);
		add(btnScan);

		JButton btnSSTF = new JButton("SSTF");
		btnSSTF.setFont(new Font("Franklin Gothic Heavy", Font.BOLD, 15));
		btnSSTF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DiskOptimization DO = new DiskOptimization(fileName);
				int[] sequence = DO.arrangeBySSTF(DO.dp.getCurrent(),
						DO.dp.getSequence());
				String text = DO.printSequence("SSTF", sequence);
				outPut.setText(text);
			}
		});
		btnSSTF.setBounds(800, 200, 100, 50);
		add(btnSSTF);
		outPut.setEditable(false);

		outPut.setBounds(75, 304, 1200, 400);
		add(outPut);

		JButton btnBrowse = new JButton("Browse");
		btnBrowse.setBounds(289, 100, 105, 35);
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonBrowseActionPerformed();
			}
		});
		add(btnBrowse);

		jTextFieldFile = new JTextField();
		jTextFieldFile.setBounds(35, 100, 216, 35);
		add(jTextFieldFile);
		jTextFieldFile.setColumns(10);

		JButton btnCscan = new JButton("C-Scan");
		btnCscan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DiskOptimization DO = new DiskOptimization(fileName);
				int[] sequence = DO.arrangeByCScan(DO.dp.getPrevious(),
						DO.dp.getCurrent(), DO.dp.getSequence());
				String text = DO.printSequence("C-Scan", sequence);
				outPut.setText(text);
			}
		});
		btnCscan.setFont(new Font("Franklin Gothic Heavy", Font.BOLD, 15));
		btnCscan.setBounds(1100, 100, 100, 50);
		add(btnCscan);

		JButton btnClook = new JButton("C-Look");
		btnClook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DiskOptimization DO = new DiskOptimization(fileName);
				int[] sequence = DO.arrangeByCLook(DO.dp.getPrevious(),
						DO.dp.getCurrent(), DO.dp.getSequence());
				String text = DO.printSequence("C-Look", sequence);
				outPut.setText(text);
			}
		});
		btnClook.setFont(new Font("Franklin Gothic Heavy", Font.BOLD, 15));
		btnClook.setBounds(1100, 200, 100, 50);
		add(btnClook);

	}

	private void jButtonBrowseActionPerformed() {
		String fileName = getFile();
		jTextFieldFile.setText(fileName);
		jButtonLoadActionPerformed();
	}

	private void jButtonLoadActionPerformed() {
		String fileName = jTextFieldFile.getText();
		this.fileName = fileName;
		JOptionPane.showMessageDialog(null, "Your file has been loaded!");
	}

	private String getFile() {
		String fileName = null;
		jFileChooser = new JFileChooser("D:\\OS-2-Project\\OS-2-Project\\assignment");
		int retval = jFileChooser.showOpenDialog(null);
		if (retval == JFileChooser.APPROVE_OPTION) {
			File file = jFileChooser.getSelectedFile();
			fileName = file.getPath();
		} else
			System.out.println("retval =" + fileName + "--"
					+ JFileChooser.APPROVE_OPTION);
		return fileName;
	}
}
