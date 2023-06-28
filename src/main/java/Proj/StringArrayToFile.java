package Proj;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class StringArrayToFile {

    private String[] stringArray;

    public StringArrayToFile(String[] stringArray) {
        this.stringArray = stringArray;
    }

    public void saveFileUsingJFileChooser() throws Exception {
        JFrame frame = new JFrame("Save As");
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Specify a file to save");

                int userSelection = fileChooser.showSaveDialog(frame);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
                    String filePath = fileToSave.getAbsolutePath();
                    try {
                        if(filePath.endsWith(".docx")){
                            writeDocxFile(filePath);
                        } else if(filePath.endsWith(".txt")){
                            writeTxtFile(filePath);
                        } else {
                            throw new Exception("Invalid file type. Only .docx and .txt are supported");
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new java.awt.FlowLayout());
        frame.add(saveButton);
        frame.setVisible(true);
    }

    private void writeDocxFile(String filePath) throws IOException {
        XWPFDocument document = new XWPFDocument();
        for (String line : stringArray) {
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setText(line);
        }
        FileOutputStream out = new FileOutputStream(filePath);
        document.write(out);
        out.close();
        document.close();
    }

    private void writeTxtFile(String filePath) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        for (String line : stringArray) {
            writer.write(line + System.lineSeparator());
        }
        writer.close();
    }

    public static void main(String[] args) {
        String[] strings = {"Hello", "This is a test", "Saving to file"};
        StringArrayToFile app = new StringArrayToFile(strings);
        try {
            app.saveFileUsingJFileChooser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}