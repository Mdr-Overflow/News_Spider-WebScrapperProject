package com.raven.form;

import javax.swing.border.*;
import javax.swing.table.*;
//import com.intellij.uiDesigner.core.*;
import com.raven.component.*;
import com.raven.dialog.Message;
import com.raven.main.Main;
import com.raven.model.ModelStudent;
import com.raven.swing.noticeboard.*;
import com.raven.swing.table.*;
import com.raven.swing.table.EventAction;

import javax.swing.*;
import java.awt.*;
import net.miginfocom.swing.*;
//import org.jdesktop.swingx.*;

public class Form_AI extends JPanel {

    public Form_AI() {
        initComponents();

        setOpaque(false);
        initData();
    }

    private void initData() {

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


    private boolean showMessage(String message) {
        Message obj = new Message(Main.getFrames()[0], true);
        obj.showMessage(message);
        return obj.isOk();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    // Generated using JFormDesigner Educational license - Madaras Andrei
    private void initComponents() {
        jLabel1 = new JLabel();
        label1 = new JLabel();
        panel1 = new JPanel();
        scrollPane1 = new JScrollPane();
        textPane1 = new JTextPane();
        panel2 = new JPanel();
        label2 = new JLabel();
        button3 = new JButton();
        label3 = new JLabel();
        comboBox1 = new JComboBox<>();
        button4 = new JButton();
        button2 = new JButton();
        button1 = new JButton();

        //======== this ========
        setLayout(new MigLayout(
            "insets 0,hidemode 3,gap 5 5",
            // columns
            "[fill]" +
            "[:50:250,growprio 50,grow 50,fill]" +
            "[361,fill]" +
            "[59,fill]" +
            "[fill]" +
            "[:475:800,grow 10,fill]" +
            "[fill]" +
            "[20:20:100,growprio 10,grow 10,fill]",
            // rows
            "[]" +
            "[fill]" +
            "[5:5:300,growprio 9,grow 5,shrinkprio 10,shrink 10,fill]" +
            "[]" +
            "[:281:500,growprio 9,grow 1,fill]" +
            "[growprio 9,grow 10]"));

        //---- jLabel1 ----
        jLabel1.setFont(new Font("sansserif", Font.BOLD, 20));
        jLabel1.setForeground(new Color(0x0448d2));
        jLabel1.setText("AI Page");
        jLabel1.setIcon(new ImageIcon("C:\\Users\\ADMIN\\Documents\\GitHub\\News_Spider-WebScrapperProject\\Resources\\GoogleBrain.png"));
        add(jLabel1, "cell 1 1 2 1");

        //---- label1 ----
        label1.setText("Results");
        label1.setForeground(new Color(0x0448d2));
        label1.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        add(label1, "cell 5 3,align center center,grow 0 0");

        //======== panel1 ========
        {
            panel1.setLayout(new BorderLayout());

            //======== scrollPane1 ========
            {

                //---- textPane1 ----
                textPane1.setEditable(false);
                scrollPane1.setViewportView(textPane1);
            }
            panel1.add(scrollPane1, BorderLayout.CENTER);
        }
        add(panel1, "cell 5 4");

        //======== panel2 ========
        {
            panel2.setLayout(null);

            //---- label2 ----
            label2.setText("Models");
            label2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
            label2.setForeground(new Color(0x0448d2));
            label2.setHorizontalAlignment(SwingConstants.CENTER);
            panel2.add(label2);
            label2.setBounds(new Rectangle(new Point(30, 20), label2.getPreferredSize()));

            //---- button3 ----
            button3.setText("Apply Model");
            button3.setForeground(new Color(0x0448d2));
            button3.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
            panel2.add(button3);
            button3.setBounds(new Rectangle(new Point(125, 115), button3.getPreferredSize()));

            //---- label3 ----
            label3.setText("Data");
            label3.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
            label3.setForeground(new Color(0x0448d2));
            label3.setHorizontalAlignment(SwingConstants.CENTER);
            panel2.add(label3);
            label3.setBounds(10, 70, 90, 27);

            //---- comboBox1 ----
            comboBox1.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
            comboBox1.setMaximumRowCount(3);
            comboBox1.setModel(new DefaultComboBoxModel<>(new String[] {
                "Summeriser",
                "Sentiment Analysis"
            }));
            panel2.add(comboBox1);
            comboBox1.setBounds(125, 15, 195, 29);

            //---- button4 ----
            button4.setText("Set Data");
            button4.setForeground(new Color(0x0448d2));
            button4.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
            panel2.add(button4);
            button4.setBounds(125, 70, 115, 27);

            //---- button2 ----
            button2.setText("Exit");
            button2.setForeground(new Color(0x0448d2));
            button2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
            panel2.add(button2);
            button2.setBounds(20, 215, 140, 35);

            //---- button1 ----
            button1.setText("Save As");
            button1.setForeground(new Color(0x0448d2));
            button1.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
            panel2.add(button1);
            button1.setBounds(190, 215, 135, 34);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel2.getComponentCount(); i++) {
                    Rectangle bounds = panel2.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel2.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel2.setMinimumSize(preferredSize);
                panel2.setPreferredSize(preferredSize);
            }
        }
        add(panel2, "cell 2 4");
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // Generated using JFormDesigner Educational license - Madaras Andrei
    private JLabel jLabel1;
    private JLabel label1;
    private JPanel panel1;
    private JScrollPane scrollPane1;
    private JTextPane textPane1;
    private JPanel panel2;
    private JLabel label2;
    private JButton button3;
    private JLabel label3;
    private JComboBox<String> comboBox1;
    private JButton button4;
    private JButton button2;
    private JButton button1;
    // End of variables declaration//GEN-END:variables
}
