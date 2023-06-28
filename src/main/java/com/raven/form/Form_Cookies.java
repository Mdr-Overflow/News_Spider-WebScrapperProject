package com.raven.form;

import javax.swing.border.*;
import javax.swing.table.*;
import javax.swing.tree.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.raven.component.*;
import com.raven.dialog.Message;
import com.raven.main.Main;
import com.raven.model.ModelStudent;
import com.raven.swing.noticeboard.*;
import com.raven.swing.table.*;
import com.raven.swing.table.EventAction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;

public class Form_Cookies extends JPanel {

    public Form_Cookies() {
        initComponents();

        setOpaque(false);
        initData();



    }

    private void initData() {
        loadRadioButtonState();
        initTableData();
    }

    private void initTableData() {
        EventAction eventAction = new EventAction() {
            @Override
            public void delete(ModelStudent student) {
                if (showMessage("Delete Student : " + student.getName())) {
                    System.out.println("User click OK");
                } else {
                    System.out.println("User click Cancel");
                }
            }

            @Override
            public void update(ModelStudent student) {
                if (showMessage("Update Student : " + student.getName())) {
                    System.out.println("User click OK");
                } else {
                    System.out.println("User click Cancel");
                }
            }
        };

    }

    private void loadTreeData() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Cookies");

        File folder = new File("Cookies");
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    root.add(new DefaultMutableTreeNode(file.getName()));
                }
            }
        }

        DefaultTreeModel model = new DefaultTreeModel(root);
        tree1.setModel(model);
    }


    ObjectMapper objectMapper = new ObjectMapper();
    File jsonFile = new File("cookies.json");

    private void saveRadioButtonState() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("Enabled", radioButton1.isSelected());

        try {
            objectMapper.writeValue(jsonFile, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadRadioButtonState() {
        if (jsonFile.exists()) {
            try {
                HashMap<String, Boolean> data = objectMapper.readValue(jsonFile, new TypeReference<HashMap<String, Boolean>>(){});
                Boolean enabled = data.get("Enabled");
                radioButton1.setSelected(enabled != null ? enabled : false);
                button1.setEnabled(radioButton1.isSelected());
                button2.setEnabled(radioButton1.isSelected());
                tree1.setEnabled(radioButton1.isSelected());

             
                    loadTreeData();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private boolean showMessage(String message) {
        Message obj = new Message(Frame.getFrames()[0], true);
        obj.showMessage(message);
        return obj.isOk();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    // Generated using JFormDesigner Educational license - Madaras Andrei
    private void initComponents() {
        jLabel1 = new JLabel();
        label3 = new JLabel();
        button2 = new JButton();
        radioButton1 = new JRadioButton();
        scrollPane1 = new JScrollPane();
        tree1 = new JTree();
        button1 = new JButton();

        //======== this ========


        // Initialization part...
            button1.setEnabled(false);
            button2.setEnabled(false);
            tree1.setEnabled(false);


        radioButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button1.setEnabled(radioButton1.isSelected());
                button2.setEnabled(radioButton1.isSelected());
                tree1.setEnabled(radioButton1.isSelected());

                if(radioButton1.isSelected()){
                    loadTreeData();
                }
                saveRadioButtonState();
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        Files.copy(selectedFile.toPath(), Paths.get("Cookies", selectedFile.getName()), StandardCopyOption.REPLACE_EXISTING);
                        loadTreeData();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        });


        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree1.getLastSelectedPathComponent();
                if (node == null) {
                    // Nothing is selected, return
                    System.out.println("NONE");
                    return;
                }

                String fileName = node.getUserObject().toString();
                File fileToDelete = new File("Cookies", fileName);

                if (fileToDelete.exists() && fileToDelete.isFile()) {
                    if (fileToDelete.delete()) {
                        System.out.println("File deleted successfully");
                    } else {
                        System.out.println("Failed to delete file");
                    }
                }

                // refresh the JTree1
                loadTreeData();
            }
        });



        //---- jLabel1 ----
        jLabel1.setFont(new Font("sansserif", Font.BOLD, 20));
        jLabel1.setForeground(new Color(0x0448d2));
        jLabel1.setText("Cookie Page");
        jLabel1.setIcon(new ImageIcon("C:\\Users\\ADMIN\\Documents\\GitHub\\News_Spider-WebScrapperProject\\Resources\\GoogleCookie.png"));

        //---- label3 ----
        label3.setText("Cookie Files");
        label3.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        label3.setForeground(new Color(0x0448d2));
        label3.setHorizontalAlignment(SwingConstants.CENTER);

        //---- button2 ----
        button2.setText("Choose File");
        button2.setForeground(new Color(0x0448d2));
        button2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));

        //---- radioButton1 ----
        radioButton1.setText("Use Cookies ?");
        radioButton1.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        radioButton1.setForeground(new Color(0x0448d2));

        //======== scrollPane1 ========
        {

            //---- tree1 ----
            tree1.setModel(new DefaultTreeModel(
                new DefaultMutableTreeNode("Cookies\t") {
                    {
                        add(new DefaultMutableTreeNode(" Test Cookie"));
                        add(new DefaultMutableTreeNode(" Test Cookie 2"));
                    }
                }));
            scrollPane1.setViewportView(tree1);
        }

        //---- button1 ----
        button1.setText("Delete Selected");
        button1.setForeground(new Color(0x0448d2));
        button1.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(77, 77, 77)
                            .addComponent(radioButton1, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(91, 91, 91)
                            .addComponent(label3, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
                            .addGap(53, 53, 53)
                            .addComponent(button2)))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
                    .addGap(65, 65, 65))
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 438, Short.MAX_VALUE)
                    .addComponent(button1)
                    .addGap(112, 112, 112))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGap(22, 22, 22)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(46, 46, 46)
                            .addComponent(radioButton1)
                            .addGap(40, 40, 40)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label3, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                                .addComponent(button2))
                            .addGap(62, 62, 62)))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(button1)
                    .addContainerGap(39, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // Generated using JFormDesigner Educational license - Madaras Andrei
    private JLabel jLabel1;
    private JLabel label3;
    private JButton button2;
    private JRadioButton radioButton1;
    private JScrollPane scrollPane1;
    private JTree tree1;
    private JButton button1;
    // End of variables declaration//GEN-END:variables
}
