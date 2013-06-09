package algorithmApp;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Rectangle;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private static MainPage mainPage;

	/**
	 * Launch the application.
	 */

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
					MainFrame frame = new MainFrame();

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
		super();
		initialize();
	}

	
	public void initialize(){
		setBounds(0,0,1350,750);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("OS Disk Optimization");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}
}
