package GUI;

import net.miginfocom.swing.MigLayout;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PageCard extends JDialog {
    private JTextArea summaryTextArea;
    private String URL;



    public PageCard(JFrame parent, String url) {
        super(parent, "Card View", true);

        URL = url;
        String hostname;
        try {
            URI uri = new URI(URL);
            hostname = uri.getHost();
        } catch (URISyntaxException e) {
            hostname = "Invalid URL";
        }



        setSize(500, 600);
        setLocationRelativeTo(parent);
        setLayout(new MigLayout("wrap 2, insets 10", "[150!][grow]"));

        Font labelFont = new Font("SansSerif", Font.BOLD, 18);
        Color fontColor = new Color(0x0448d2);

        // Title Pane
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel(hostname == null ? "" : hostname);
        titleLabel.setFont(labelFont);
        titleLabel.setForeground(fontColor);
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
        Font textFieldFont = new Font("SansSerif", Font.BOLD, 16);
        int index = 0;
        String[] labels = {"URL:", "Domain:", "Page Type:", "Risk:"};
        String[] riskValues = {"None", "Safe", "Average"};
        String[] pageTypeValues = {"Simple", "Dynamic", "Infinite Scroll"};

        for (String label : labels) {
            JLabel attributeLabel = new JLabel(label);
            attributeLabel.setFont(labelFont);
            attributeLabel.setForeground(fontColor);
            dataPanel.add(attributeLabel);

            JTextField textField = new JTextField(45); // increased size to 25
            textField.setFont(textFieldFont);
            textField.setEditable(false);
            textField.setForeground(fontColor);
            index ++ ;


            if ("URL:".equals(label)) {
                textField.setText(URL);
            } else if ("Domain:".equals(label)) {
                textField.setText(hostname == null ? "" : hostname);
            }

              else if ("Page Type:".equals(label)) {
                Random random = new Random();
                 index = random.nextInt(pageTypeValues.length);
                textField.setText(pageTypeValues[index]);
            } else if ("Risk:".equals(label)) {
                Random random = new Random();
                index = random.nextInt(riskValues.length);
                textField.setText(riskValues[index]);
            }


        dataPanel.add(textField, "wrap");

        }

        // Result Label
        JLabel resultLabel = new JLabel("Success");
        resultLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        resultLabel.setForeground(Color.GREEN);
        dataPanel.add(resultLabel, "wrap");

        // Error Textbox
        JTextArea errorTextArea = new JTextArea(4, 20);
        errorTextArea.setEditable(false);
        errorTextArea.setBackground(getBackground());
     //   dataPanel.add(errorTextArea, "");

        // Summary TextArea
        summaryTextArea = new JTextArea(15, 150); // increased size
        summaryTextArea.setEditable(false);
        JScrollPane summaryScrollPane = new JScrollPane(summaryTextArea);
        dataPanel.add(summaryScrollPane, "span 11, center , growx , growy , gaptop 20");

        add(dataPanel, "span 11,center , grow , gaptop 10");

        // Buttons Panel
        JPanel buttonsPanel = new JPanel(new MigLayout("gap 10"));
        JButton saveAsButton = new JButton("Save As");
        saveAsButton.setFont(textFieldFont);
        saveAsButton.setForeground(fontColor);
        JButton ExitButton = new JButton("Back");
        ExitButton.setFont(textFieldFont);
        ExitButton.setForeground(fontColor);

        buttonsPanel.add(saveAsButton);
        buttonsPanel.add(ExitButton);

        add(buttonsPanel, "span 2, center, gaptop 20");

        saveAsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveAsDocx(summaryTextArea.getText());
            }
        });

        ExitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        parseStringAndFillTextArea();
    }

    private void saveAsDocx(String content) {
        XWPFDocument document = new XWPFDocument();
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText(content);

        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("DOCX files", "docx");
        fileChooser.setFileFilter(filter);

        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String filePath = fileToSave.getAbsolutePath();

            // Check if the file has the correct extension, add it if not
            if (!filePath.toLowerCase().endsWith(".docx")) {
                filePath = filePath + ".docx";
            }

            try (FileOutputStream out = new FileOutputStream(filePath)) {
                document.write(out);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void parseStringAndFillTextArea() {
        try {
            String content = new String(Files.readAllBytes(Paths.get("Results.json")));
            content = content.substring(1, content.length() - 1);  // Remove surrounding quotes

            Pattern keywordDatePattern = Pattern.compile("KeyWordAndDate:(\\w+) (\\d+/\\d+/\\d+ \\d+:\\d+:\\d+)");
            Matcher keywordDateMatcher = keywordDatePattern.matcher(content);
            String keyword = "";
            if (keywordDateMatcher.find()) {
                keyword = keywordDateMatcher.group(1);
            }

            String[] urlEntries = content.split("\\], \\{");
            StringBuilder textAreaContent = new StringBuilder();

            for (String urlEntry : urlEntries) {
                String[] split = urlEntry.split("=\\[");
                if (split.length == 2) {
                    String currentURL = split[0].replaceAll("\\{", "");
                    if (currentURL.equals(URL)) {
                        String[] urlTexts = split[1].split(", ");
                        for (String urlText : urlTexts) {
                            textAreaContent.append(urlText);
                            textAreaContent.append("\n");
                        }
                        break;
                    }
                }
            }

            if (textAreaContent.length() == 0) {
                textAreaContent.append("This is some text about ").append(keyword);
            }

            summaryTextArea.setText(textAreaContent.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }





    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame parent = new JFrame();
                parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                parent.setSize(800, 600);
                parent.setLocationRelativeTo(null);
                parent.setVisible(true);

                PageCard popup = new PageCard(parent,"https://goggle.com");
                popup.setVisible(true);
            }
        });
    }
}