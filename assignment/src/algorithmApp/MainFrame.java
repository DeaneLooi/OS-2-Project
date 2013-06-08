package algorithmApp;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import algorithmApp.ui.*;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private FCFS Fcfs;
	private Look Look;
	private Scan Scan;
	private SSTF Sstf;
	private static MainPage mainPage;

	/**
	 * Launch the application.
	 */

	public FCFS getFcfs() {
		return Fcfs;
	}

	public void setFcfs(FCFS fcfs) {
		Fcfs = fcfs;
	}

	public Look getLook() {
		return Look;
	}

	public void setLook(Look look) {
		Look = look;
	}

	public Scan getScan() {
		return Scan;
	}

	public void setScan(Scan scan) {
		Scan = scan;
	}

	public SSTF getSstf() {
		return Sstf;
	}

	public void setSstf(SSTF sstf) {
		Sstf = sstf;
	}

	public MainPage getMainPage() {
		return mainPage;
	}

	public void set(MainPage mainPage) {
		this.mainPage = mainPage;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame = new JFrame();

					mainPage = new MainPage(frame);

					frame.getContentPane().add(mainPage);
					mainPage.setVisible(true);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setResizable(true);
		setSize(new Dimension(1366, 768));
		setPreferredSize(new Dimension(500, 400));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1366, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
