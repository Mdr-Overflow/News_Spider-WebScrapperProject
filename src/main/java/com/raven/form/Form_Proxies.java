package com.raven.form;

import javax.swing.border.*;
import javax.swing.table.*;

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
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Form_Proxies extends JPanel {
    private ObjectMapper objectMapper;
    private File jsonFile;
    public Form_Proxies() {
        initComponents();
        objectMapper = new ObjectMapper();
        jsonFile = new File("Proxy.json");


        setOpaque(false);
        initData();
        loadState();
    }

    private void initData() {
        radioButton1.addActionListener(e -> {
            boolean selected = radioButton1.isSelected();
            radioButton2.setEnabled(selected);
            radioButton3.setEnabled(selected);
            textField1.setEnabled(selected && radioButton3.isSelected());
        });

        radioButton2.addActionListener(e -> {
            if (radioButton2.isSelected()) {
                radioButton3.setSelected(false);
                textField1.setEnabled(false);
            }
        });

        radioButton3.addActionListener(e -> {
            if (radioButton3.isSelected()) {
                radioButton2.setSelected(false);
                textField1.setEnabled(true);
            }
        });
        // Set action listener for button1
        button1.addActionListener(e -> {
            saveState();
        });
        radioButton2.addActionListener(e -> {
            if (radioButton2.isSelected()) {
                // Download and save proxy data
                downloadAndSaveProxyData();
            }
        });
    }

    private void downloadAndSaveProxyData() {
        try {
            URL url = new URL("https://proxylist.geonode.com/api/proxy-list?limit=10&page=1&sort_by=lastChecked&sort_type=desc");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();

                // Parse JSON
                Map<String, Object> data = objectMapper.readValue(content.toString(), Map.class);

                // Save JSON to file
                objectMapper.writeValue(new File("ProxyList.json"), data);

            } else {
                System.out.println("GET request failed. Response Code: " + responseCode);
            }
            connection.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void saveState() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("Enabled", radioButton1.isSelected());
        data.put("Default", radioButton2.isSelected());
        data.put("Custom", radioButton3.isSelected());
        data.put("Source", textField1.getText());

        try {
            objectMapper.writeValue(jsonFile, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadState() {
        if (jsonFile.exists()) {
            try {
                HashMap<String, Object> data = objectMapper.readValue(jsonFile, new TypeReference<>() {});

                radioButton1.setSelected((boolean) data.get("Enabled"));
                radioButton2.setSelected((boolean) data.get("Default"));
                radioButton3.setSelected((boolean) data.get("Custom"));
                textField1.setText((String) data.get("Source"));

                radioButton2.setEnabled(radioButton1.isSelected());
                radioButton3.setEnabled(radioButton1.isSelected());
                textField1.setEnabled(radioButton1.isSelected() && radioButton3.isSelected());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
        radioButton1 = new JRadioButton();
        radioButton2 = new JRadioButton();
        radioButton3 = new JRadioButton();
        textField1 = new JTextField();
        label1 = new JLabel();
        button1 = new JButton();

        //======== this ========

        //---- jLabel1 ----
        jLabel1.setFont(new Font("sansserif", Font.BOLD, 20));
        jLabel1.setForeground(new Color(0x0448d2));
        jLabel1.setText("Proxies Page");
        jLabel1.setIcon(new ImageIcon("Resources\\GoogleProxie.png"));

        //---- radioButton1 ----
        radioButton1.setText("Use Proxies ?");
        radioButton1.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        radioButton1.setForeground(new Color(0x0448d2));

        //---- radioButton2 ----
        radioButton2.setText("Use Default");
        radioButton2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        radioButton2.setForeground(new Color(0x0448d2));

        //---- radioButton3 ----
        radioButton3.setText("Use Custom");
        radioButton3.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        radioButton3.setForeground(new Color(0x0448d2));

        //---- label1 ----
        label1.setText("URL To Proxie List");
        label1.setIcon(null);
        label1.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        label1.setForeground(new Color(0x0448d2));

        //---- button1 ----
        button1.setText("Save");
        button1.setForeground(new Color(0x0448d2));
        button1.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                            .addGap(153, 153, 153)
                            .addGroup(layout.createParallelGroup()
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(radioButton3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(28, 28, 28)
                                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
                                    .addGap(20, 20, 20))
                                .addComponent(radioButton2, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup()
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(21, 21, 21)
                                    .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(48, 48, 48)
                                    .addComponent(radioButton1, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)))
                            .addGap(57, 57, 57)))
                    .addComponent(textField1, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(38, Short.MAX_VALUE))
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 499, Short.MAX_VALUE)
                    .addComponent(button1, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
                    .addGap(78, 78, 78))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGap(26, 26, 26)
                    .addComponent(jLabel1)
                    .addGap(43, 43, 43)
                    .addComponent(radioButton1)
                    .addGap(18, 18, 18)
                    .addComponent(radioButton2)
                    .addGap(30, 30, 30)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(radioButton3)
                        .addComponent(textField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label1))
                    .addGap(57, 57, 57)
                    .addComponent(button1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(123, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // Generated using JFormDesigner Educational license - Madaras Andrei
    private JLabel jLabel1;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JRadioButton radioButton3;
    private JTextField textField1;
    private JLabel label1;
    private JButton button1;
    // End of variables declaration//GEN-END:variables
}
