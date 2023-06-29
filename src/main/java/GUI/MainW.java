package GUI;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet.ColorAttribute;

import DB.Create_Tables;
import DB.DB_HEADERS;
import DB.DB_UA;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import com.formdev.flatlaf.FlatLightLaf;

import net.miginfocom.swing.MigLayout;

import GUI.MainMenu.*;
import Proj.Headers;

import static GUI.MENU_OPTIONS.resolution_;

public class MainW implements ActionListener {

	private JFrame frmNewsSpiderTest;
	private JTextField textField;
	private final JPanel PANELMANAGER = new JPanel();
	private CardLayout cardl = new CardLayout(0, 0);
	private final JPanel panel_3 = new JPanel();

	private JPanel current_panel;

	/**
	 * @wbp.nonvisual location=20,589
	 */
	static int rate = -1;
	static int threads = -1;
	static String[] Real_URLS;
	private final JPanel panel_OPTIONS = new JPanel();
	private final JPanel panel_Urls = new JPanel();
	public static JProgressBar bar = null;

	private JLabel gifLabel;
	static int progress = 0;

	public static String[] options = {" ", " ", " ", " ", " ", " "};
	public static ArrayList<HashMap<String, String>> Agents;


	public String[] done = {"?"};

	private static double SCALE_FACTOR = 1.5;

	public static void centreWindow(Window frame) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
	}

	private Image getScaledImage(Image srcImg, int w, int h) {
		BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resizedImg.createGraphics();

		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(srcImg, 0, 0, w, h, null);
		g2.dispose();

		return resizedImg;
	}

	public void resizeWindowAndComponents(JFrame frame, int width, int height) {
		// Set the new size of the frame
		frame.setSize(width, height);
//		centreWindow(frame);


		// Get an array of all the components in the frame
		Component[] components = frame.getContentPane().getComponents();

		// Iterate over each component and resize it
		for (Component component : components) {
			// Calculate the new size for the component
			int newWidth = (int) ((component.getWidth() * ((double) width / (frame.getWidth()))));
			int newHeight = (int) (component.getHeight() * ((double) height / frame.getHeight()));
			newWidth = (int) Math.round(newWidth * SCALE_FACTOR);


			if (component instanceof JComponent) {
				Font originalFont = component.getFont();
				float originalSize = originalFont.getSize2D();
				//	System.out.println(newWidth + " ||||||||| " + width);
				if (component.getWidth() != 0) {
					float fontScale = (float) ((newWidth) / component.getWidth());
					Font resizedFont = originalFont.deriveFont(originalSize * fontScale);
					component.setFont(resizedFont);
				}
			}
//
//


			// Set the new size for the component
			component.setSize(newWidth, newHeight);
		}
		SCALE_FACTOR = 1;
	}


	public static void main(String[] args) {
		FlatLightLaf.setup();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainW window = new MainW();
					window.frmNewsSpiderTest.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	// STUFF

	//  Panel3 is MainMenu


	public MainW() {

		initialize();
	}


	private void initialize() {
		frmNewsSpiderTest = new JFrame();

		try {
			UIManager.setLookAndFeel(new FlatLightLaf());
		} catch (UnsupportedLookAndFeelException ex) {
			throw new RuntimeException(ex);
		}

		frmNewsSpiderTest.setTitle("GUI Test");
		frmNewsSpiderTest.setBounds(100, 100, 931, 640);
		frmNewsSpiderTest.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		frmNewsSpiderTest.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				gifLabel.setVisible(true);


				int newSizeW = frmNewsSpiderTest.getWidth();
				int newSizeH = frmNewsSpiderTest.getHeight();
				resizeWindowAndComponents(frmNewsSpiderTest, newSizeW, newSizeH);

				if (current_panel == panel_3) {


					File f = new File("Resources/nodes.gif");
					URL image = null;
					try {
						image = f.toURL();
					} catch (MalformedURLException ex) {
						throw new RuntimeException(ex);
					}


					ImageIcon gifIcon = new ImageIcon(image);  // Set the path to the GIF
					gifIcon.setImage(gifIcon.getImage().getScaledInstance((int) (frmNewsSpiderTest.getWidth() / 1.5), (int) (frmNewsSpiderTest.getHeight() / 1.2), Image.SCALE_DEFAULT));
					gifLabel.setIcon(gifIcon);

					//	resizeWindowAndComponents(frmNewsSpiderTest,newSizeW,newSizeH);
				} else {
					gifLabel.setVisible(false);

				}

			}

		});


		//https://iconarchive.com/show/flat-halloween-icons-by-uiconstock/Halloween-Spider-2-icon.html
		String pathToFileOnDisk = Paths.get("IconDoodle.png").toString();
		ImageIcon img = new ImageIcon(pathToFileOnDisk);
		frmNewsSpiderTest.setIconImage(img.getImage());

		JMenuBar menuBar = new JMenuBar();
		frmNewsSpiderTest.setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu(" Meniu ");
		menuBar.add(mnNewMenu);
		frmNewsSpiderTest.getContentPane().setLayout(new BoxLayout(frmNewsSpiderTest.getContentPane(), BoxLayout.X_AXIS));

		// Create a label with a GIF
		gifLabel = new JLabel();
		JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
		try {


			File f = new File("Resources/nodes.gif");
			URL image = f.toURL();


			ImageIcon gifIcon = new ImageIcon(image);  // Set the path to the GIF
			gifIcon.setImage(gifIcon.getImage().getScaledInstance((int) (frmNewsSpiderTest.getWidth() / 1.5), (int) (frmNewsSpiderTest.getHeight() / 1.2), Image.SCALE_DEFAULT));
			gifLabel.setIcon(gifIcon);


			// Add the label to the content pane with GridBagConstraints
			frmNewsSpiderTest.getContentPane().add(separator);
			frmNewsSpiderTest.getContentPane().add(gifLabel);

		} catch (Exception e) {
			System.out.println(e.toString());
		}


		frmNewsSpiderTest.addWindowListener(new java.awt.event.WindowAdapter() {
												@Override
												public void windowClosing(java.awt.event.WindowEvent windowEvent) {
													if (JOptionPane.showConfirmDialog(frmNewsSpiderTest,
															"Are you sure you want to close this window?", "Close Window?",
															JOptionPane.YES_NO_OPTION,
															JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {

														if (options[3] == "yes")  // 3-URL , 4-LOG , 5-RES
														{
															Proj.Utils.Delete_Temp_File("Urls.txt");
														}
														if (options[4] == "yes")  // 3-URL , 4-LOG , 5-RES
														{
															Proj.Utils.Delete_Temp_File("log.txt");
														}
														if (options[5] == "yes")  // 3-URL , 4-LOG , 5-RES
														{
															Proj.Utils.Delete_Temp_File("Results.json");
														}


														frmNewsSpiderTest.setDefaultCloseOperation(javax.swing.
																WindowConstants.EXIT_ON_CLOSE);
														frmNewsSpiderTest.dispose();
														//frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
													} else {
													}
												}
											}
		);


		JPanel panel = new JPanel();
		frmNewsSpiderTest.getContentPane().add(PANELMANAGER);
		PANELMANAGER.setLayout(cardl);
		PANELMANAGER.add(panel, "name_528503221187900");
		PANELMANAGER.add(panel_3);
		PANELMANAGER.add(panel_OPTIONS);
		PANELMANAGER.add(panel_Urls);
		//PANELMANAGER.add(panel_1);
		ArrayList<JPanel> PANELS = new ArrayList<JPanel>();


		current_panel = panel;


		panel.setLocation(new Point(1, 1));
		panel.setBounds(new Rectangle(2, 2, 2, 2));
		panel.setBackground(Color.ORANGE);
		panel.setLayout(new MigLayout("", "[80px:80px:300px,grow 50,left][100px:457px:1400px,grow,center][80px:80px:300px,grow 50,right]", "[30px:30px:30px,top][100px:264px:800px,grow,center][30px:n:50px,grow 10][100px:80px:200px,grow 50,fill][]"));


		JPanel panel_1 = new JPanel();
		panel_1.setMinimumSize(new Dimension(100, 100));
		panel_1.setPreferredSize(new Dimension(1, 1));
		panel_1.setMaximumSize(new Dimension(32000, 32000));


		panel_1.addComponentListener(resizeListener);                     //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@   IMPORTANT


		panel_1.setBounds(new Rectangle(2, 2, 2, 2));
		panel_1.setBackground(new Color(255, 255, 255));
		panel.add(panel_1, "cell 1 1,grow");
		panel_1.setLayout(null);


		Panel panel_2 = new Panel();
		panel_2.setMinimumSize(new Dimension(100, 100));
		panel_2.setMaximumSize(new Dimension(4000, 2000));
		panel_2.setBackground(new Color(255, 255, 255));
		panel.add(panel_2, "cell 1 3,alignx center,aligny center");
		panel_2.setLayout(new MigLayout("", "[100px:100px:100px,grow 10,left][100px:250px:250px,grow 50,fill][100px:100px:100px,grow 10,right]", "[22px:22px:66px,grow][30px:50px:50px,grow 10][30px:50px:50px,grow]"));

		JLabel label = new JLabel("Enter Words to Search");
		//Dimension rv;
		System.out.println(label.getWidth());
		label.setFont(new Font("Arial Black", Font.PLAIN, 16));

		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setVerticalAlignment(Label.CENTER);
		panel_2.add(label, "cell 1 0,growx,aligny center");
		label.setForeground(new Color(255, 153, 0));
		label.setName("SLabIGN");
		//textField = new JTextField();
		JTextField KeyWord_TextBox = new JTextField();
		KeyWord_TextBox.setMinimumSize(new Dimension(20, 20));
		KeyWord_TextBox.setFont(new Font("Arial", Font.BOLD, 16));
		KeyWord_TextBox.setForeground(new Color(204, 51, 0));
		panel_2.add(KeyWord_TextBox, "cell 1 1,grow");
		KeyWord_TextBox.setBounds(new Rectangle(1, 0, 0, 0));


		JButton button = new JButton("START");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				button.setForeground(Color.GREEN);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				button.setForeground(new Color(255, 153, 51));
			}
		});
		button.setFont(new Font("Arial Black", Font.PLAIN, 12));
		button.setForeground(new Color(255, 153, 0));
		panel_2.add(button, "cell 1 2,grow");
		PANELMANAGER.add(panel_3, "name_528644898002799");

		//////// RUN 															@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@


		///  ESTABLISH CONN TO THE DATABASE AND CREATE TABLES IN MEM IF THEY DON'T EXIST ALREADY
		try {
			Create_Tables ct = new Create_Tables();


			// CREATE DEFAULTS FOR USERAGENTS AND HEADERS
			DB_UA ua_db = new DB_UA();
			DB.DB_HEADERS hd_db = new DB.DB_HEADERS();

			ua_db.Populate_UA_DEFAULT();
			hd_db.Populate_HEADERS_DEFAULT();


			hd_db.Populate_HEADERS_DEFAULT();
			// CREATE AGENTS DB

			ua_db.END_CONNECTION();
			hd_db.END_CONNECTION();

			Headers h = new Headers();

			DB.DB_AGENTS ag_db = new DB.DB_AGENTS();
			ag_db.del_AGENTS();
			ag_db.Create_AGENTS(10);
			Agents = new ArrayList<HashMap<String, String>>(h.Generate_Agents(ag_db));


		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			JOptionPane.showMessageDialog(panel,
					"COULD NOT ESTABLISH CONNECTION TO DATABASE OR NOT ENOGTH MEMORY FOR TABLES");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			System.exit(-1);
		}

		//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		button.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
				//////// DEFAULTS 															@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				String[] Url_list_Test = {"https://www.theverge.com",
						"https://www.theguardian.com/",
						"https://www.dailymail.co.uk/", "https://www.reuters.com/", "https://time.com/",
						"https://www.bloomberg.com/europe", "https://www.heraldscotland.com/", "https://example.com"};

				button.setEnabled(false);
				ArrayList<Future<Runnable>> futures = new ArrayList<Future<Runnable>>();
				String params = "All";
				String KeyWord = KeyWord_TextBox.getText();
				rate = -1;
				threads = -1;
				//String keyword = "tornado";

				////////DEFAULTS 															@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@


				/////// GET URLS
				ArrayList<String> URLS = new ArrayList<String>();

				Real_URLS = Url_list_Test;
				int i = 0;
				if (Proj.Utils.ReadFromURLs() != null) {


					URLS.addAll(Proj.Utils.ReadFromURLs());
					Real_URLS = new String[URLS.size()];
					System.out.println("ENTERED URL GETTER");
					System.out.println(URLS.size());
					for (String url : URLS) {
						System.out.println("url of URLS");
						System.out.println(url);
						Real_URLS[i] = url;
						i++;
						//textArea.append(url+ "\n");
					}
				}

				////// GET OPTIONS

				Object[] op = {};
				op = Proj.Utils.ParseOptions();
				if ((int) op[0] != -1) {
					if ((int) op[0] == 1) {
						// Headless
						options[0] = "yes";

					} else options[0] = "no";

					if ((int) op[1] == 1) {
						// Safe

						options[1] = "yes";

					} else options[1] = "no";//

					if ((int) op[2] == 1) {

						options[2] = "yes";

					} else options[2] = "no";

					if ((int) op[3] == 1) {

						options[3] = "yes";

					} else options[3] = "no";

					if ((int) op[4] == 1) {

						options[4] = "yes";

					} else options[4] = "no";

					if ((int) op[5] == 1) {

						options[5] = "yes";

					} else options[5] = "no";

					rate = (int) op[7];
					threads = (int) op[6];
					resolution_ = (String) op[8];

				}
				if (rate == -1 || threads == -1) {
					System.out.println("FAILED GETTING OPTIONS");
					rate = 200;
					threads = 12;
				}
				//////
				System.out.println(rate);
				System.out.println(threads);
				clearpanel(panel_1);
				init_progressbar(panel_1);


				SwingWorker<Boolean, Integer> bar_worker = new SwingWorker<Boolean, Integer>() {
					@Override
					protected Boolean doInBackground() throws Exception {
						futures.add((Future<Runnable>) fill());
						{
							return true;
						}
					}


					protected void done() {

						boolean status;

						try {

							status = get();

							progress = 0;

						} catch (InterruptedException e) {

							// This is thrown if the thread's interrupted.
						} catch (ExecutionException e) {

						}
					}


				};

				SwingWorker<Boolean, Integer> worker = new SwingWorker<Boolean, Integer>() {
					@Override
					protected Boolean doInBackground() throws Exception {
						futures.add((Future<Runnable>) Proj.driver2.RunInGui(params, KeyWord, Real_URLS, rate, threads, Agents, options));
						{
							return true;
						}
					}

					protected void done() {

						boolean status;

						try {

							status = get();
							button.setEnabled(true);
							progress = 0;
							//kill_progressbar(b);

							clearpanel(panel_1);
							paintComponents1(panel_1, Real_URLS, KeyWord);
							futures.clear();
							if (!bar_worker.isDone()) {
								bar_worker.cancel(status);
							}
						} catch (InterruptedException e) {


						} catch (ExecutionException e) {

						}
					}

				};
				bar_worker.execute();
				worker.execute();
			}


		});

		mnNewMenu.setForeground(new Color(255, 153, 51));
		mnNewMenu.setFont(new Font("Arial Black", Font.BOLD, 14));

		JMenuItem APP = new JMenuItem("APP");
		APP.setActionCommand("App");
		APP.setForeground(new Color(255, 153, 51));
		APP.setFont(new Font("Arial Black", Font.BOLD, 14));
		mnNewMenu.add(APP);

		JMenuItem mntmNewMenuItem = new JMenuItem("Main Meniu");
		//mntmNewMenuItem = new JMenuItem("Main Meniu");

		mntmNewMenuItem.setForeground(new Color(255, 153, 51));
		mntmNewMenuItem.setFont(new Font("Arial Black", Font.BOLD, 14));
		mnNewMenu.add(mntmNewMenuItem);


		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Options");
		mntmNewMenuItem_1.setForeground(new Color(255, 153, 51));
		mntmNewMenuItem_1.setFont(new Font("Arial Black", Font.BOLD, 14));
		mnNewMenu.add(mntmNewMenuItem_1);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Urls");
		mntmNewMenuItem_2.setForeground(new Color(255, 153, 51));
		mntmNewMenuItem_2.setFont(new Font("Arial Black", Font.BOLD, 14));
		mnNewMenu.add(mntmNewMenuItem_2);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("DB");
		mntmNewMenuItem_3.setForeground(new Color(255, 153, 51));
		mntmNewMenuItem_3.setFont(new Font("Arial Black", Font.BOLD, 14));
		mnNewMenu.add(mntmNewMenuItem_3);

		// MAIN MENU                                                                                          @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				String comStr = ae.getActionCommand();
				System.out.println(comStr);

				if (current_panel != panel_3) {

					current_panel.removeAll();
					current_panel.revalidate();
					current_panel.repaint();

					if (comStr.equals("Main Meniu")) {
						menuBar.setVisible(false);
						current_panel.setVisible(false);
						panel_3.setVisible(true);
						gifLabel.setVisible(true);
						current_panel = panel_3;
					}


					panel_3.setLayout(new MigLayout("", "[1px:1px:100px,grow 7][1px:1px:100px,grow 7][1px:1px:100px,grow 7][1px:1px:100px,grow 7][1px:1px:100px,grow 7]"
							+ "[1px:1px:100px,grow 7][100px:100px:600px,grow,center][1px:1px:100px,grow 7][1px:1px:100px,grow 7][1px:1px:100px,grow 7]"
							+ "[1px:1px:100px,grow 7][1px:1px:100px,grow 7][1px:1px:100px,grow 7]", "[grow 20][30px:30px:60px,grow,center][:50px:100px]"
							+ "[20px:20px:300px,fill][30px:30px:60px,grow,center][20px:20px:30px,grow,center][30px:30px:60px,grow,center][20px:20p"
							+ "x:30px,grow,center][30px:30px:60px,grow,center][20px:20px:30px,grow,center][30px:30px:60px,grow,center][20px:20px:30"
							+ "px,grow,center][30px:30px:60px,grow,center][20px:20px:300px,grow 10,fill]"));

					JLabel lblNewLabel = new JLabel("");

					String imageFilePath = Paths.get("SpiderDoodle.jpeg").toString();

					JLabel lblNewLabel_1 = new JLabel("News Spider");

					panel_3.add(lblNewLabel_1, "flowx,cell 6 1,alignx center,aligny center");
					panel_3.add(lblNewLabel, "cell 6 2");

					lblNewLabel_1.setForeground(new Color(255, 153, 51));
					lblNewLabel_1.setFont(new Font("Arial Narrow", Font.BOLD, 32));
					//lblNewLabel.setIcon(new ImageIcon(imageFilePath));


					JButton btnNewButton = new JButton("START");
					btnNewButton.setForeground(new Color(255, 153, 51));
					btnNewButton.setFont(new Font("Arial Narrow", Font.BOLD, 16));

					btnNewButton.setIcon(new ImageIcon("webDoodle.png"));


					// MAIN MENIU APP
					btnNewButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent ae) {
							//


							String comStr = ae.getActionCommand();
							System.out.println(comStr);


							if (current_panel != panel) {
								current_panel.setVisible(false);
								current_panel.removeAll();
								current_panel.revalidate();
								current_panel.repaint();
								current_panel = panel;
								gifLabel.setVisible(false);
								//


								menuBar.setVisible(true);
								MENU_OPTIONS.Remake_MainWindow(current_panel);

								panel.setVisible(true);

							}

						}
					});


					panel_3.add(btnNewButton, "cell 6 4,grow");

					// MAIN MENIU OPTIONS															@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
					JButton btnNewButton_1 = new JButton("OPTIONS");
					panel_3.add(btnNewButton_1, "cell 6 6,grow");
					btnNewButton_1.setForeground(new Color(255, 153, 51));
					btnNewButton_1.setFont(new Font("Arial Narrow", Font.BOLD, 16));
					btnNewButton_1.setIcon(new ImageIcon("webDoodle.png"));
					btnNewButton_1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent ae) {

							String comStr = ae.getActionCommand();
							System.out.println(comStr);

							if (current_panel != panel_OPTIONS) {

								current_panel.removeAll();
								current_panel.revalidate();
								current_panel.repaint();

								if (comStr.equals("OPTIONS")) {
									current_panel.setVisible(false);
									panel_OPTIONS.setVisible(true);
									gifLabel.setVisible(false);
									current_panel = panel_OPTIONS;
								}

								menuBar.setVisible(true);
								MENU_OPTIONS.Create_Options_Meniu(current_panel);
								current_panel = panel_OPTIONS;

							}


						}
					});

					// MAIN MENIU URLS															@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
					JButton btnNewButton_2 = new JButton("URLS");
					panel_3.add(btnNewButton_2, "cell 6 8,grow");
					btnNewButton_2.setForeground(new Color(255, 153, 51));
					btnNewButton_2.setFont(new Font("Arial Narrow", Font.BOLD, 16));
					btnNewButton_2.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent ae) {


							String comStr = ae.getActionCommand();
							System.out.println(comStr);

							if (current_panel != panel_Urls) {

								current_panel.removeAll();
								current_panel.revalidate();
								current_panel.repaint();

								if (comStr.equals("URLS")) {
									current_panel.setVisible(false);
									panel_Urls.setVisible(true);
									current_panel = panel_Urls;
									gifLabel.setVisible(false);
								}

								menuBar.setVisible(true);
								MENU_OPTIONS.Create_Urls_Meniu(current_panel);


							}


						}
					});

					// MAIN MENU DB
					JButton btnNewButton_3 = new JButton("DB");
					panel_3.add(btnNewButton_3, "cell 6 10,grow");
					btnNewButton_3.setForeground(new Color(255, 153, 51));
					btnNewButton_3.setFont(new Font("Arial Narrow", Font.BOLD, 16));
					btnNewButton_3.addActionListener(
							new ActionListener() {
								//@SuppressWarnings("static-access")
								public void actionPerformed(ActionEvent ae) {

									String comStr = ae.getActionCommand();
									System.out.println(comStr);
									System.out.println(done[0] + "<---------------------------------------------------------");
									if (done[0] == "?") {

										DB_WINDOW dbw = new DB_WINDOW();
										done[0] = "??";
										dbw.main(done);
									} else if (done[0] == "Yes") {
										DB_WINDOW dbw = new DB_WINDOW();
										done[0] = "??";
										dbw.main(done);

									} else {
										JOptionPane.showMessageDialog(panel,
												"Only one instance of dbmanager allowed");
									}
									//frmNewsSpiderTest.

								}
							});


					JButton btnNewButton_4 = new JButton("EXIT");
					panel_3.add(btnNewButton_4, "cell 6 12,grow");
					btnNewButton_4.setForeground(new Color(255, 153, 51));
					btnNewButton_4.setFont(new Font("Arial Narrow", Font.BOLD, 16));
					btnNewButton_4.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							System.exit(1);
						}
					});


				}
			}


		});

		//OPTIONS															@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				String comStr = ae.getActionCommand();
				System.out.println(comStr);

				if (current_panel != panel_OPTIONS) {

					current_panel.removeAll();
					current_panel.revalidate();
					current_panel.repaint();

					if (comStr.equals("Options")) {
						current_panel.setVisible(false);
						panel_OPTIONS.setVisible(true);
						current_panel = panel_OPTIONS;
					}


					MENU_OPTIONS.Create_Options_Meniu(current_panel);
					current_panel = panel_OPTIONS;

				}


			}
		});

		// URLs 															@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {


				String comStr = ae.getActionCommand();
				System.out.println(comStr);

				if (current_panel != panel_Urls) {

					current_panel.removeAll();
					current_panel.revalidate();
					current_panel.repaint();

					if (comStr.equals("Urls")) {
						current_panel.setVisible(false);
						panel_Urls.setVisible(true);
						current_panel = panel_Urls;
					}


					MENU_OPTIONS.Create_Urls_Meniu(current_panel);


				}


			}
		});


		// DB															@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		///
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent ae) {

				String comStr = ae.getActionCommand();
				System.out.println(comStr);
				System.out.println(done[0] + "<---------------------------------------------------------");
				if (done[0] == "?") {

					DB_WINDOW dbw = new DB_WINDOW();
					done[0] = "??";
					dbw.main(done);
				} else if (done[0] == "Yes") {
					DB_WINDOW dbw = new DB_WINDOW();
					done[0] = "??";
					dbw.main(done);

				} else {
					JOptionPane.showMessageDialog(panel,
							"Only one instance of dbmanager allowed");
				}
				//frmNewsSpiderTest.

			}
		});


		// APP															@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		APP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				//


				String comStr = ae.getActionCommand();
				System.out.println(comStr);


				if (current_panel != panel) {
					current_panel.setVisible(false);
					current_panel.removeAll();
					current_panel.revalidate();
					current_panel.repaint();
					current_panel = panel;
					//

					MENU_OPTIONS.Remake_MainWindow(current_panel);

					panel.setVisible(true);

				}

			}
		});


		mnNewMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				mnNewMenu.setForeground(Color.GREEN);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				mnNewMenu.setForeground(new Color(255, 153, 51));
			}
		});
		mnNewMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 2;

		//mntmNewMenuItem // START ON MAIN MENU
		for (ActionListener a : mntmNewMenuItem.getActionListeners()) {
			a.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Main Meniu"));
		}

	}


	public void actionPerformed(ActionEvent ae) {
		// Get the action command from the menu selection.
		String comStr = ae.getActionCommand();
		System.out.println(comStr);
		if (comStr.equals("Main Meniu")) {
			System.out.println("asdasdsa");
		}

	}


	public static void clearpanel(JPanel p) {

		p.removeAll();
		p.revalidate();

		Graphics g = p.getGraphics();
		int W = p.getWidth();
		int H = p.getHeight();

		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaintMode();

		g2d.setStroke(new BasicStroke(1));
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, W, H);
	}


	public static void paintComponents1(JPanel p, String[] Url_list, String KeyWord) {


		MakeVertices.generateNodesEX(p, Url_list, KeyWord);
	}


	public static ComponentListener resizeListener = new ComponentListener() {

		@Override
		public void componentResized(ComponentEvent e) {

			for (Component jc : ((Container) e.getSource()).getComponents()) {
				//System.out.println(jc);
				if (jc instanceof JLabel) {
					if (((JLabel) jc).getText().matches("[0-9]|[0-9][0-9]|[0-9][0-9][0-9]")) {
						//System.out.println("FOUND");
						((JPanel) e.getSource()).remove(jc);
					}

				}
			}
		}

		@Override
		public void componentMoved(ComponentEvent e) {
		}

		@Override
		public void componentShown(ComponentEvent e) {
		}

		@Override
		public void componentHidden(ComponentEvent e) {
		}


	};
	// recalculate value


	public static Runnable fill() {
		progress = 0;
		try {
			while (progress <= 100) {
				System.out.println("..................");
				if (progress > 30 && progress < 70)
					bar.setString("Waiting for pages to load");
				else if (progress > 70)
					bar.setString("Parsing...");
				else
					bar.setString("Loading Started");

				// fill the menu bar
				bar.setValue(progress + 10);

				// delay the thread
				try {
					Thread.sleep(4000);
				} catch (Exception e) {
				}

				progress += 30;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	public static void init_progressbar(JPanel p) {

		bar = new JProgressBar();

		bar.setStringPainted(true);

		bar.setValue(0);

		p.add(bar);

		bar.setBounds(p.getWidth() / 2 - 100, p.getHeight() / 2, 200, 20);
		p.add(bar);

		bar.setVisible(true);

	}


	public static void kill_progressbar(JProgressBar bar) {
		bar.setVisible(false);

	}


	public void Make_INIT_MENIU() {

	}

}
	


