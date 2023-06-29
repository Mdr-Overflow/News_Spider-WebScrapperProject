package GUI;


import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PageCard extends JDialog {

    public PageCard(JFrame parent) {
        super(parent, "Card View", true);
        setSize(500, 600);
        setLocationRelativeTo(parent);
        setLayout(new MigLayout("wrap 2, insets 10", "[150!][grow]"));

        // Title Pane
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Website Name");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(titleLabel);
        add(titlePanel, "span 2, center");

        // Image Pane
        JPanel imagePanel = new JPanel();
        ImageIcon icon = new ImageIcon("Resources\\GoogleIconPage.png"); // Replace with your image file path
        JLabel imageLabel = new JLabel(icon);
        imagePanel.add(imageLabel);
        add(imagePanel, "span 2, center, gapbottom 20");

        // Data Panel
        JPanel dataPanel = new JPanel(new MigLayout("insets 5"));
        String[] labels = {"URL:", "Domain:", "Page Type:", "Risk:", "Account:"};
        for (String label : labels) {
            JLabel attributeLabel = new JLabel(label);
            dataPanel.add(attributeLabel);

            JTextField textField = new JTextField(20);
            textField.setEditable(false);
            dataPanel.add(textField, "wrap");
        }

        // Result Label
        JLabel resultLabel = new JLabel("Success");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 18));
        resultLabel.setForeground(Color.GREEN);
        dataPanel.add(resultLabel, "span 2, center, gaptop 10");

        // Error Textbox
        JTextArea errorTextArea = new JTextArea(3, 20);
        errorTextArea.setEditable(false);
        errorTextArea.setBackground(getBackground());
        dataPanel.add(errorTextArea, "span 2, growx");

        // Summary TextArea
        JTextArea summaryTextArea = new JTextArea(10, 40);
        summaryTextArea.setEditable(false);
        JScrollPane summaryScrollPane = new JScrollPane(summaryTextArea);
        dataPanel.add(summaryScrollPane, "span 2, grow");

        add(dataPanel, "span 2, grow");

        // Close Button
        JPanel buttonsPanel = new JPanel(new MigLayout("gap 10"));
        JButton viewDbButton = new JButton("View in DataBase");
        JButton useAiButton = new JButton("Use on AI model");
        JButton ExitButton = new JButton("Back");

        buttonsPanel.add(viewDbButton);
        buttonsPanel.add(useAiButton);
        buttonsPanel.add(ExitButton);


        add(buttonsPanel, "span 2, center, gaptop 20");
        ExitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame parent = new JFrame();
                parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                parent.setSize(800, 600);
                parent.setLocationRelativeTo(null);
                parent.setVisible(true);

                PageCard popup = new PageCard(parent);
                popup.setVisible(true);
            }
        });
    }
}