package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JLabel;

public class MainMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
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
	public MainMenu() {
		setTitle("Main Meniu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 856, 644);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[1px:1px:100px,grow 7][1px:1px:100px,grow 7][1px:1px:100px,grow 7][1px:1px:100px,grow 7][1px:1px:100px,grow 7][1px:1px:100px,grow 7][100px:100px:600px,grow,center][1px:1px:100px,grow 7][1px:1px:100px,grow 7][1px:1px:100px,grow 7][1px:1px:100px,grow 7][1px:1px:100px,grow 7][1px:1px:100px,grow 7]", "[grow 20][30px:30px:60px,grow,center][:50px:100px][20px:20px:300px,fill][30px:30px:60px,grow,center][20px:20px:30px,grow,center][30px:30px:60px,grow,center][20px:20px:30px,grow,center][30px:30px:60px,grow,center][20px:20px:30px,grow,center][30px:30px:60px,grow,center][20px:20px:30px,grow,center][30px:30px:60px,grow,center][20px:20px:300px,grow 10,fill]"));
		
		JButton btnNewButton = new JButton("START");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnNewButton.setForeground(new Color(255, 153, 51));
		btnNewButton.setFont(new Font("Arial Narrow", Font.BOLD, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JLabel lblNewLabel = new JLabel("");
		
		String imageFilePath = Paths.get("SpiderDoodle.jpeg").toString();
		
		JLabel lblNewLabel_1 = new JLabel("NEWS SPIDER");
		
		contentPane.add(lblNewLabel_1, "flowx,cell 6 1,alignx center,aligny center");
		
		lblNewLabel_1.setForeground(new Color(255, 153, 51));
		lblNewLabel_1.setFont(new Font("Arial Narrow", Font.BOLD, 32));
		lblNewLabel.setIcon(new ImageIcon(imageFilePath));
		
		
		contentPane.add(lblNewLabel, "cell 6 2");
		contentPane.add(btnNewButton, "cell 6 4,grow");
		
		JButton btnNewButton_1 = new JButton("OPTIONS");
		contentPane.add(btnNewButton_1, "cell 6 6,grow");
		btnNewButton_1.setForeground(new Color(255, 153, 51));
		btnNewButton_1.setFont(new Font("Arial Narrow", Font.BOLD, 16));
		
		
		JButton btnNewButton_2 = new JButton("URLS");
		contentPane.add(btnNewButton_2, "cell 6 8,grow");
		btnNewButton_2.setForeground(new Color(255, 153, 51));
		btnNewButton_2.setFont(new Font("Arial Narrow", Font.BOLD, 16));
		
		JButton btnNewButton_3 = new JButton("DB CONFIG");
		contentPane.add(btnNewButton_3, "cell 6 10,grow");
		btnNewButton_3.setForeground(new Color(255, 153, 51));
		btnNewButton_3.setFont(new Font("Arial Narrow", Font.BOLD, 16));
		
		JButton btnNewButton_4 = new JButton("EXIT");
		btnNewButton_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 System.exit(1);
			}
		});
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.add(btnNewButton_4, "cell 6 12,grow");
		btnNewButton_4.setForeground(new Color(255, 153, 51));
		btnNewButton_4.setFont(new Font("Arial Narrow", Font.BOLD, 16));
	}

}
