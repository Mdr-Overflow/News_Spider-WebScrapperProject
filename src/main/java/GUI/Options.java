package GUI;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.DataOutput;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

//import sun.nio.ch.Net;


import javax.swing.plaf.metal.MetalCheckBoxIcon;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class Options extends JFrame {


	private static double SCALE_FACTOR = 1.5;

	private static int[] ColorBitmaskQueue = new int[2];
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	private Color buttonColor ;

	public static void centreWindow(Window frame) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
	}


	private void FixedWindow(int i,JFrame frame) {

		frame.setResizable(i == 0);


	}


	private Image changeImageColor(Image image, Color foregroundColor, Color backgroundColor) {
		// Create a BufferedImage with the specified colors
		BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = bufferedImage.createGraphics();

		// Set the background color
		g2d.setColor(backgroundColor);
		g2d.fillRect(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());

		// Set the foreground color
		g2d.setColor(foregroundColor);

		// Draw the original image on top of the colored background
		g2d.drawImage(image, 0, 0, null);
		g2d.dispose();

		return bufferedImage;
	}

	private Image getScaledImage(Image srcImg, int w, int h){
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
		centreWindow(frame);



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
					  float fontScale = (float) ((newWidth ) / component.getWidth());
					  Font resizedFont = originalFont.deriveFont(originalSize * fontScale);
					  component.setFont(resizedFont);
				  }
			  }
//
//			if (component instanceof JCheckBox)
//			{
//
//				Image icon = new BufferedImage(((JCheckBox)component).getIcon().getIconWidth(), ((JCheckBox)component).getIcon().getIconHeight(),
//						BufferedImage.TYPE_INT_RGB);
//
//				Image icon_selected = new BufferedImage(((JCheckBox)component).getSelectedIcon().getIconWidth(), ((JCheckBox)component).getIcon().getIconHeight(),
//						BufferedImage.TYPE_INT_RGB);
//
//
//			   	icon = getScaledImage(icon,this.getWidth()/30,this.getHeight()/25);
//				icon_selected = getScaledImage(icon_selected,this.getWidth()/30,this.getHeight()/25);
//
//
//
//				//		scaleImage(icon_selected,this.getWidth(),this.getHeight());
//				((JCheckBox)component).setIcon(new ImageIcon(icon));
//				((JCheckBox)component).setSelectedIcon(new ImageIcon(icon_selected));
//			}


			// Set the new size for the component
			component.setSize(newWidth, newHeight);
		}
		SCALE_FACTOR = 1;
	}

	public static int getIntFromColor(int Red, int Green, int Blue){
		Red = (Red << 16) & 0x00FF0000; //Shift red 16-bits and mask out other stuff
		Green = (Green << 8) & 0x0000FF00; //Shift Green 8-bits and mask out other stuff
		Blue = Blue & 0x000000FF; //Mask out anything not blue.

		return 0xFF000000 | Red | Green | Blue; //0xFF000000 for 100% Alpha. Bitwise OR everything together.
	}

	public static void colorFrame(JFrame frame, int mode) {
		Container contentPane = frame.getContentPane();
		Color color = frame.getContentPane().getBackground();
		if (mode == 0) {
			int intColor = getContrastColor(getIntFromColor(color.getRed(),color.getGreen(), color.getBlue()));
			Color fromInt = new Color(intColor);
			frame.getContentPane().setBackground(fromInt);
			colorContainer(contentPane,fromInt,0);
		} else if (mode == 2) {
			int intColor = getRandomColor(getIntFromColor(color.getRed(),color.getGreen(), color.getBlue()));
			Color fromInt = new Color(intColor);
			frame.getContentPane().setBackground(fromInt);
			colorContainer(contentPane,fromInt,2);
		} else if (mode == 1) {
			int intColor = getDBlueColor(getIntFromColor(color.getRed(),color.getGreen(), color.getBlue()));
			Color fromInt = new Color(intColor);
			frame.getContentPane().setBackground(fromInt);
			colorContainer(contentPane,fromInt,1);
		} else if (mode == -1){

			int intColor = getResetColor(getIntFromColor(color.getRed(),color.getGreen(), color.getBlue()));   /// HERE WE RESET
			Color fromInt = new Color(intColor);
			frame.getContentPane().setBackground(fromInt);
			colorContainer(contentPane,fromInt,-1);
		}

	}

	private static void colorContainer(Container container, Color color, int mode) {
		Component[] components = container.getComponents();
		int intColor = 0;
		int intColorF = 0;
		for (Component component : components) {
			Color color2 = component.getBackground();
			Color color2F = component.getForeground();
			if (mode == 0) {
				intColor = getContrastColor(getIntFromColor(color2.getRed(), color2.getGreen(), color2.getBlue()));
				intColorF = getContrastColor(getIntFromColor(color2F.getRed(), color2F.getGreen(), color2F.getBlue()));
			} else if ( mode == 2) {
				intColor = getRandomColor(getIntFromColor(color2.getRed(), color2.getGreen(), color2.getBlue()));
				intColorF = getRandomColor(getIntFromColor(color2F.getRed(), color2F.getGreen(), color2F.getBlue()));

			} else if ( mode == 1) {
				intColor = getDBlueColor(getIntFromColor(color2.getRed(), color2.getGreen(), color2.getBlue()));
				intColorF = getDBlueColor(getIntFromColor(color2F.getRed(), color2F.getGreen(), color2F.getBlue()));

			} else if (mode == -1) {

				intColor = getResetColor(getIntFromColor(color2.getRed(), color2.getGreen(), color2.getBlue()));
				intColorF = getResetColor(getIntFromColor(color2F.getRed(), color2F.getGreen(), color2F.getBlue()));

			}

			Color fromInt = new Color(intColor);
			Color fromIntF = new Color(intColorF);
			if (component instanceof Container) {

				colorContainer((Container) component,
						fromInt,mode);
			}
			component.setBackground(fromInt);
			component.setForeground(fromIntF);
		}
	}

	public static int getContrastColor(int bgColor) {
		ColorBitmaskQueue[0] = 0x00fff0ff;
		return bgColor ^ 0x00fff0ff;
	}

	public static int getDBlueColor(int bgColor) {
		ColorBitmaskQueue[0] = 0x00fff0af;
		return bgColor ^ 0x00fff0af;
	}

	public static int getRandomColor(int bgColor) {
		Random rand = new Random();
		int n = rand.nextInt(0x01000000);
		ColorBitmaskQueue[0] = n;
		return bgColor ^ n;
	}

	public static int getResetColor(int bgColor){

		return bgColor ^ ColorBitmaskQueue[0];

	}


	private void ThemeWindow(String theme, JFrame frame) {
		if (Objects.equals(theme, "Dark"))
			colorFrame(frame,0);
		if(Objects.equals(theme,"DeepBlue"))
			colorFrame(frame,1);
		if(Objects.equals(theme,"Random"))
			colorFrame(frame,2);
		if(Objects.equals(theme,"Reset"))
			colorFrame(frame,-1);
		if(Objects.equals(theme,"Light"))
			ColorBitmaskQueue[0] = 0;
	}


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		// ZOOM IN OPTION





		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Options frame = new Options();
					//frame.setBackground(Color.yellow);
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 *
	 * MULTI-MONITOR SYSTEM
	 */
	public Options() {
		//setBackground(Color.YELLOW);

		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} catch (UnsupportedLookAndFeelException ex) {
			throw new RuntimeException(ex);
		}

		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int widthS = gd.getDisplayMode().getWidth();
		int heightS = gd.getDisplayMode().getHeight();

		System.out.println("SYSTEM MAX REZ : " + widthS +"x" + heightS);

		String[] RezBox  = {"2500x1440","1900x1440","1900x1200","1920x1080","1440x1200","1200x1000","1000x600","1000x800","900x500"};
		String[] ThemeBox = {"Light","Dark","DeepBlue","Random"};

		// CHECK REZBOX

		List<String> list = new ArrayList<String>(Arrays.asList(RezBox));


		for (String rez : RezBox) {
			if (Integer.parseInt(rez.split("x")[0]) > widthS || Integer.parseInt(rez.split("x")[1]) > heightS)
				list.remove(rez);

		}
		//RezBox= null;
		RezBox = list.toArray(new String[0]);


		JLabel DesignSeparator = new JLabel();
		JLabel label2 = new JLabel();
		JLabel themeLabel = new JLabel();
		JComboBox comboBox1 = new JComboBox(RezBox);
		JComboBox themeComboBox = new JComboBox(ThemeBox);
		JLabel FilesSeparator = new JLabel();
		JLabel AdvancedSeparator = new JLabel();


		String Resolution_ = "";
		String theme_ = "";

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 704, 459);

		setMinimumSize(new Dimension(900,500));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("",
				"[100px:100px:300px][100px:100px:300px,fill][300px:300px:500px,grow][1px:1px:300px,grow][100px:100px:300px,fill][10px:10px:300px,fill]" +
						"[100px:100px:300px,fill][][10px:100px:10px,grow][][][10px:10px:30px,grow][10px:10px:300px,grow][][1px:100px:300px,grow][1px:1px:300px,grow]",
				"[][15px:15px:50px,grow][15px:15px:50px,grow][10px:10px:30px, grow][15px:15px:50px,grow][45px:55px:70px,grow][15px:15px:50px,grow]" +
						"[35px:35px:50px,grow][15px:15px:50px,grow][15px:15px:50px,grow][35px:35px:50px,grow]" +
						"[35px:35px:50px,grow][15px:15px:50px,grow][15px:15px:50px,grow][35px:35px:50px,grow][][][][10px:10px:30px,grow][10px:10px:300px,grow][]" +
						"[1px:100px:300px,grow]" +
						"[15px:15px:600px,grow]"));
		contentPane.setBackground(Color.WHITE);

		/*    OPTIONS DESIGN LISTBOX 1 space FILES 1 label checkbox space label checkbox space*/
        /* 12 19  Button

		"[1px:1px:300px,grow][1px:1px:300px,grow][][][][][][][][][][][][1px:1px:300px,grow][1px:1px:300px,grow]",
				"[][15px:15px:600px,grow][][][][][][][][][][][][][][][][][][][15px:15px:600px,grow]"));

					MIN : MAX : CUR , grow
					10px:100px:10px,grow
		 */


		JLabel lblNewLabel = new JLabel("OPTIONS");
		lblNewLabel.setFont(new Font("Yu Gothic", Font.BOLD, 22));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		// panels

		JPanel OPTIONS_panel0 = new JPanel();
		OPTIONS_panel0.setBackground(Color.ORANGE);
		OPTIONS_panel0.setBorder(new EmptyBorder(5, 5, 5, 5));
		OPTIONS_panel0.setLayout(
				new MigLayout("hidemode 3",
						// columns
						"[fill]" +
								"[fill]",
						// rows
						"[fill]" +
								"[fill]" +
								"[fill]"));

		JPanel OPTIONS_panel = new JPanel();
		OPTIONS_panel.setBackground(Color.ORANGE);
		OPTIONS_panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		OPTIONS_panel.setLayout(
				new MigLayout("hidemode 3",
						// columns
						"[fill]" +
								"[fill]",
						// rows
						"[fill]" +
								"[fill]" +
								"[fill]"));

		JPanel OPTIONS_panel2 = new JPanel();
		OPTIONS_panel2.setBackground(Color.ORANGE);
		OPTIONS_panel2.setBorder(new EmptyBorder(5, 5, 5, 5));
		OPTIONS_panel2.setMaximumSize(new Dimension(1000,1000));
		OPTIONS_panel2.setLayout(
				new MigLayout("hidemode 3",
						// columns
						"[fill]" +
								"[fill]",
						// rows
						"[fill]" +
								"[fill]" +
								"[fill]"));
		JPanel OPTIONS_panel3 = new JPanel();
		OPTIONS_panel3.setBackground(Color.ORANGE);
		OPTIONS_panel3.setBorder(new EmptyBorder(5, 5, 5, 5));
		OPTIONS_panel3.setLayout(
				new MigLayout("hidemode 3",
						// columns
						"[fill]" +
								"[fill]",
						// rows
						"[fill]" +
								"[fill]" +
								"[fill]"));

		JPanel OPTIONS_panel23 = new JPanel();
		OPTIONS_panel23.setBackground(Color.ORANGE);
		OPTIONS_panel23.setBorder(new EmptyBorder(5, 5, 5, 5));
		OPTIONS_panel23.setLayout(
				new MigLayout("hidemode 3",
						// columns
						"[fill]" +
								"[fill]",
						// rows
						"[fill]" +
								"[fill]" +
								"[fill]"));
		JPanel OPTIONS_panely1 = new JPanel();
		OPTIONS_panely1.setBackground(Color.ORANGE);
		OPTIONS_panely1.setBorder(new EmptyBorder(5, 5, 5, 5));
		OPTIONS_panely1.setLayout(
				new MigLayout("hidemode 3","[fill]" + "[fill]", "[fill]" + "[fill]" + "[fill]"));
		JPanel OPTIONS_panely2 = new JPanel();
		OPTIONS_panely2.setBackground(Color.ORANGE);
		OPTIONS_panely2.setBorder(new EmptyBorder(5, 5, 5, 5));
		OPTIONS_panely2.setLayout(
				new MigLayout("hidemode 3","[fill]" + "[fill]", "[fill]" + "[fill]" + "[fill]"));
		JPanel OPTIONS_panely3 = new JPanel();
		OPTIONS_panely3.setBackground(Color.ORANGE);
		OPTIONS_panely3.setBorder(new EmptyBorder(5, 5, 5, 5));
		OPTIONS_panely3.setLayout(
				new MigLayout("hidemode 3","[fill]" + "[fill]", "[fill]" + "[fill]" + "[fill]"));
		JPanel OPTIONS_panely4 = new JPanel();
		OPTIONS_panely4.setBackground(Color.ORANGE);
		OPTIONS_panely4.setBorder(new EmptyBorder(5, 5, 5, 5));
		OPTIONS_panely4.setLayout(
				new MigLayout("hidemode 3","[fill]" + "[fill]", "[fill]" + "[fill]" + "[fill]"));

		contentPane.add(OPTIONS_panel0,"cell 0 1, grow");
		contentPane.add(OPTIONS_panel,"cell 1 0");
		contentPane.add(OPTIONS_panel2,"cell 2 0,grow 1 0");
		contentPane.add(OPTIONS_panel23,"cell 3 0,grow 1 0");
		contentPane.add(OPTIONS_panel3,"cell 4 0");
		contentPane.add(OPTIONS_panely1,"cell 0 2, grow 0 1");
		contentPane.add(OPTIONS_panely2,"cell 0 3, grow 0 1");
		contentPane.add(OPTIONS_panely3,"cell 0 4, grow 0 1");
		contentPane.add(OPTIONS_panely4,"cell 0 5, grow 0 1");

		// New stuff

		DesignSeparator.setText("DESIGN");
		DesignSeparator.setFont(new Font("Yu Gothic", Font.BOLD , 16));

		label2.setText("Resolution");
		label2.setFont(new Font("Yu Gothic", Font.BOLD, 16));
//		/label2.set


		themeLabel.setText("Theme");
		themeLabel.setFont(new Font("Yu Gothic", Font.BOLD, 16));
//		themeLabel.setVerticalAlignment(SwingConstants.CENTER);
//		themeLabel.setHorizontalAlignment(SwingConstants.CENTER);


		FilesSeparator.setText("FILES");
		FilesSeparator.setFont(new Font("Yu Gothic", Font.BOLD , 16));


		JLabel fixedLabel = new JLabel("Fixed Window");
		fixedLabel.setFont(new Font("Yu Gothic", Font.BOLD, 16));


		JLabel lblNewLabel_1 = new JLabel("Run Headless");


		JCheckBox FixedCheckBox = new JCheckBox("");
		FixedCheckBox.setIcon (new MetalCheckBoxIcon() {
			protected int getControlSize() { return 20; }
		});

		JCheckBox chckbxNewCheckBox = new JCheckBox("");

		chckbxNewCheckBox.setIcon (new MetalCheckBoxIcon() {
			protected int getControlSize() { return 20; }
		});


		contentPane.add(FixedCheckBox, "cell 4 2, align center top ,grow 0 1");
		contentPane.add(chckbxNewCheckBox, "cell 2 5, align center top ,grow 0 1");

		contentPane.add(lblNewLabel, "cell 0 0,align center top ,grow 0 1 ");
		contentPane.add(DesignSeparator ,"cell 1 1 , align center top,grow 0 1");
		contentPane.add(label2, "cell 2 1,align center top,grow 1 1");
		contentPane.add(themeLabel, "cell 2 1,align center top,grow 1 1");
		contentPane.add(fixedLabel ,"cell 4 1,align center top,grow 1 1");
		contentPane.add(FilesSeparator ,"cell 1 5 , align center top,grow 0 1");
		contentPane.add(lblNewLabel_1, "cell 2 5,align center top,grow 1 1");

		//---- comboBox1 ----
		comboBox1.setFont(new Font("Bauhaus 93", Font.PLAIN, 16));
		contentPane.add(comboBox1, "cell 2 2, grow 1 1");


		themeComboBox.setFont(new Font("Bauhaus 93", Font.PLAIN, 16));
		contentPane.add(themeComboBox, "cell 2 2, grow 1 1");
		themeComboBox.setMinimumSize(new Dimension(90, 29));
		comboBox1.setMinimumSize(new Dimension(90, 29));


		JLabel lblNewLabel_2 = new JLabel("Only Run Safe URLs");
		contentPane.add(lblNewLabel_2, "cell 4 5,align center top,grow 1 1");

		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("");
		contentPane.add(chckbxNewCheckBox_1, "cell 4 5, align center,grow 0 1");


		chckbxNewCheckBox_1.setIcon (new MetalCheckBoxIcon() {
			protected int getControlSize() { return 20; }
		});


		JCheckBox chckbxNewCheckBox_2 = new JCheckBox("");
		contentPane.add(chckbxNewCheckBox_2, "cell 2 7, align center top,grow 0 1");

		JLabel lblNewLabel_3 = new JLabel("Save screencap of page");
		contentPane.add(lblNewLabel_3, "cell 2 7, align center top,grow 1 1");


		chckbxNewCheckBox_2.setIcon (new MetalCheckBoxIcon() {
			protected int getControlSize() { return 20; }
		});


		JLabel lblNewLabel_4 = new JLabel("Delete Temporary URL file");
		contentPane.add(lblNewLabel_4, "cell 4 7, align center top,grow 1 1");

		JCheckBox chckbxNewCheckBox_3 = new JCheckBox("");
		contentPane.add(chckbxNewCheckBox_3, "cell 4 7, align center top,grow 0 1");

		chckbxNewCheckBox_3.setIcon (new MetalCheckBoxIcon() {
			protected int getControlSize() { return 20; }
		});


		JCheckBox chckbxNewCheckBox_5 = new JCheckBox("");
		contentPane.add(chckbxNewCheckBox_5, "cell 2 10 , align center top,grow 0 1");


		JLabel lblNewLabel_6 = new JLabel("Delete Temporary RESULTS file");
		contentPane.add(lblNewLabel_6, "cell 2 10, align center top,grow 1 1");


		chckbxNewCheckBox_5.setIcon (new MetalCheckBoxIcon() {
			protected int getControlSize() { return 20; }
		});


		JLabel lblNewLabel_9 = new JLabel("Delete Temporary LOG file");
		contentPane.add(lblNewLabel_9, "cell 4 10, align center top,grow 1 1");


		JCheckBox chckbxNewCheckBox_4 = new JCheckBox("");
		contentPane.add(chckbxNewCheckBox_4, "cell 4 10, align center top,grow 0 1");

		chckbxNewCheckBox_4.setIcon (new MetalCheckBoxIcon() {
			protected int getControlSize() { return 20; }
		});


		AdvancedSeparator.setText("ADVANCED");
		AdvancedSeparator.setFont(new Font("Yu Gothic", Font.BOLD , 16));
		contentPane.add(AdvancedSeparator ,"cell 1 12 , align center top,grow 0 1");


		// ADVANCED OPTIONS

		JLabel lblNewLabel_7 = new JLabel("Maximum Number of Threads");
		contentPane.add(lblNewLabel_7, "cell 2 13");


		textField = new JTextField();
		contentPane.add(textField, "cell 2 14");
		textField.setColumns(10);


		JLabel lblNewLabel_8 = new JLabel("Request Rate in milliseconds");
		contentPane.add(lblNewLabel_8, "cell 4 13");


		textField_1 = new JTextField();
		contentPane.add(textField_1, "cell 4 14,center");
		textField_1.setColumns(10);


		JLabel lblNewLabel_5 = new JLabel("");
		contentPane.add(lblNewLabel_5, "flowx,cell 2 15");


		JButton btnNewButton = new JButton("Save Settings");
		contentPane.add(btnNewButton, "cell 11 18, grow 1 1");

		btnNewButton.setFont(new Font("Arial Black", Font.PLAIN, 12));
		btnNewButton.setForeground(new Color(255, 153, 0));


		// ********************** COLOR ON COMP FIX ( OPAQUE AND OTHER )

		try {
			UIManager.setLookAndFeel(
					UIManager.getSystemLookAndFeelClassName() );//.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
				 UnsupportedLookAndFeelException e) {
			throw new RuntimeException(e);
		}

		Component[] components = this.getContentPane().getComponents();
		for (Component component : components) {
			if (component instanceof JCheckBox ) {
				JCheckBox checkbox = (JCheckBox) component;
				checkbox.setOpaque(false);
				//	checkbox.setBackground(Color.WHITE);
				//	checkbox.setForeground(Color.WHITE);
				try {

					Image icon = ImageIO.read(new File("chk1_border.png"));
					Image icon_selected = ImageIO.read(new File("chk1.png"));

					icon = getScaledImage(icon,this.getWidth()/30,this.getHeight()/25);
					icon_selected = getScaledImage(icon_selected,this.getWidth()/30,this.getHeight()/25);

					// Get the foreground color of the frame
					Color foregroundColor = this.getForeground();
					Color backColor = this.getBackground();

					// Create a new image with the foreground color
					icon = changeImageColor(icon, foregroundColor,backColor);
					icon_selected = changeImageColor(icon_selected, foregroundColor,backColor);

					//		scaleImage(icon_selected,this.getWidth(),this.getHeight());
					checkbox.setIcon(new ImageIcon(icon));
					checkbox.setSelectedIcon(new ImageIcon(icon_selected));
				} catch (Exception e) {
					System.out.println(e);
				}
			}
			if (component instanceof JButton ) {
				JButton box = (JButton) component;
				box.setOpaque(false);
				//box.setBackground(Color.WHITE);
			}}


		lblNewLabel.setForeground(new Color(255, 153, 51));
		//lblNewLabel.setFont(new Font("Arial Narrow", Font.BOLD, 16));

		lblNewLabel_1.setForeground(new Color(255, 153, 51));
		lblNewLabel_1.setFont(new Font("Arial Narrow", Font.BOLD, 16));

		lblNewLabel_2.setForeground(new Color(255, 153, 51));
		lblNewLabel_2.setFont(new Font("Arial Narrow", Font.BOLD, 16));

		lblNewLabel_3.setForeground(new Color(255, 153, 51));
		lblNewLabel_3.setFont(new Font("Arial Narrow", Font.BOLD, 16));

		lblNewLabel_4.setForeground(new Color(255, 153, 51));
		lblNewLabel_4.setFont(new Font("Arial Narrow", Font.BOLD, 16));

		lblNewLabel_6.setForeground(new Color(255, 153, 51));
		lblNewLabel_6.setFont(new Font("Arial Narrow", Font.BOLD, 16));

		lblNewLabel_7.setForeground(new Color(255, 153, 51));
		lblNewLabel_7.setFont(new Font("Arial Narrow", Font.BOLD, 16));

		lblNewLabel_8.setForeground(new Color(255, 153, 51));
		lblNewLabel_8.setFont(new Font("Arial Narrow", Font.BOLD, 16));

		lblNewLabel_9.setForeground(new Color(255, 153, 51));
		lblNewLabel_9.setFont(new Font("Arial Narrow", Font.BOLD, 16));

		textField.setFont(new Font("Arial", Font.BOLD, 16));
		textField.setForeground(new Color(204, 51, 0));

		textField_1.setFont(new Font("Arial", Font.BOLD, 16));
		textField_1.setForeground(new Color(204, 51, 0));


		////////////////////////// GET OPTIONS


		Object[] op = {};
		op = Proj.Utils.ParseOptions();
		if ((int) op[0] != -1 ) {

			chckbxNewCheckBox.setSelected((int) op[0] == 1);  // true ==1 or false if == 0

			chckbxNewCheckBox_1.setSelected((int) op[1] == 1);

			chckbxNewCheckBox_2.setSelected((int) op[2] == 1);

			chckbxNewCheckBox_3.setSelected((int) op[3] == 1);

			chckbxNewCheckBox_4.setSelected((int) op[4] == 1);

			chckbxNewCheckBox_5.setSelected((int) op[5] == 1);

			textField.setText(Integer.toString((int)op[6]));
			textField_1.setText(Integer.toString((int)op[7]));


			// Resolution

			System.out.println("---> " + op[8] );
			comboBox1.setSelectedItem(op[8]);
			Resolution_ = (String) op[8];


			// Reset resolution ~! ~! ~! ~! ~! ~!~! ~! ~!~! ~! ~!~! ~! ~!~! ~! ~!~! ~! ~!~! ~! ~!~! ~! ~!~! ~! ~!~! ~! ~!~! ~! ~!
			int width = Integer.parseInt(Resolution_.split("x")[0]);
			int height = Integer.parseInt(Resolution_.split("x")[1]);
			this.setSize(width, height);
			// centreWindow(this);

			// theme

			System.out.println("---> " + op[9] );
			themeComboBox.setSelectedItem(op[9]);
			theme_ = (String) op[9];
			ThemeWindow(theme_,this);


			// fixed window
			System.out.println("---> " + op[10] );
			FixedCheckBox.setSelected((int) op[10] == 1);
			FixedWindow((int) op[10],this);

		} else System.out.println("FATAL ERROR");
		//////////////////////////GET OPTIONS


		//// EVENT LISTENERS

		comboBox1.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e){

				if(e.getStateChange()==ItemEvent.SELECTED){
					String selectedIN = comboBox1.getItemAt(comboBox1.getSelectedIndex()).toString();
					resizeWindowAndComponents(Options.this, Integer.parseInt(selectedIN.split("x")[0]),
							Integer.parseInt(selectedIN.split("x")[1]));
					System.out.println(selectedIN);


				}
			}
		});

		themeComboBox.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e){

				if(e.getStateChange()==ItemEvent.SELECTED){
					String selectedTheme = themeComboBox.getItemAt(themeComboBox.getSelectedIndex()).toString();
					System.out.println(selectedTheme);


					// FIRST WE NEED TO RESET THEME SO WE DON'T APPLY MASKS OVER OTHERS
					ThemeWindow("Reset",(JFrame) SwingUtilities.getWindowAncestor(themeLabel));


					//resetTheme((JFrame) SwingUtilities.getWindowAncestor(themeLabel));
					ThemeWindow(selectedTheme, (JFrame) SwingUtilities.getWindowAncestor(themeLabel));

				}
			}
		});

		FixedCheckBox.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e){

				if(e.getStateChange()==ItemEvent.SELECTED){

						FixedWindow(1, (JFrame) SwingUtilities.getWindowAncestor(themeLabel));
				}
				else {
					FixedWindow(0, (JFrame) SwingUtilities.getWindowAncestor(themeLabel));
				}
			}});



//				.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//
//				super.mouseClicked(e);
//
//				int index = comboBox1.getSelectedIndex();
//				if (index >= 0) {
//
//				}
//
//
//			}
//
//			@Override
//			public void mouseEntered(MouseEvent e) {
//				super.mouseEntered(e);
//			}
//
//			@Override
//			public void mouseExited(MouseEvent e) {
//				super.mouseExited(e);
//			}
//		}


		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				buttonColor = btnNewButton.getForeground();
				btnNewButton.setForeground(Color.GREEN);

			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton.setForeground(buttonColor);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				int good = 1;
				String options ="Options: ";

				if (chckbxNewCheckBox.isSelected())     //COOKIE JAR
					options += "COOKIE JAR: YES,";
				else
					options += "COOKIE JAR: NO,";

				if (chckbxNewCheckBox_1.isSelected())     //SAFE URLS
					options += "SAFE URLS: YES,";
				else
					options += "SAFE URLS: NO,";

				if (chckbxNewCheckBox_2.isSelected())     //SCREENCAP
					options += "SCREENCAP: YES,";
				else
					options += "SCREENCAP: NO,";

				if (chckbxNewCheckBox_3.isSelected())     //DEL URL FILE
					options += "DEL URL FILE: YES,";
				else
					options += "DEL URL FILE: NO,";

				if (chckbxNewCheckBox_4.isSelected())     //DEL LOG FILE
					options += "DEL LOG FILE: YES,";
				else
					options += "DEL LOG FILE: NO,";

				if (chckbxNewCheckBox_5.isSelected())     //DEL RESULTS FILE
					options += "DEL RESULTS FILE: YES,";
				else
					options += "DEL RESULTS FILE: NO,";

				try {
					if (textField.getText().length() != 0 || textField_1.getText().length() != 0) {
						int res1 = Integer.parseInt(textField.getText());
						int res2 = Integer.parseInt(textField_1.getText()) ;

						if (res1 < 0 || res2 < 0) {
							good =0;
							JOptionPane.showMessageDialog(contentPane,
									"Write a real positive number in the boxes");

						}
					}

				} catch (Exception ex2) {
					good = 0;
					ex2.printStackTrace();
					JOptionPane.showMessageDialog(contentPane,
							"Write a real positive number in the boxes");

				}

				if (good == 1) {

					if (textField.getText().length() != 0)
						options += textField.getText() + ",";
					else
						options += Proj.Utils.DEFAULTmaxthreads + ",";


					if (textField_1.getText().length() != 0)
						options += textField_1.getText() +",";
					else
						options += Proj.Utils.DEFAULTrequestrate +",";

					// GET RES

					options += (String) comboBox1.getSelectedItem() + ",";
					options += (String) themeComboBox.getSelectedItem() + ',';

					if (FixedCheckBox.isSelected())     //fixed window size
						options += "1";
					else
						options += "0";

					options+="\n";

					System.out.println(options);


					Proj.Utils.WriteToFile("Options.txt", options);

				}

			}
		});


	}




}
