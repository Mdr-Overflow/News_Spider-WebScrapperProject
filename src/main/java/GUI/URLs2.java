/*
 * Created by JFormDesigner on Sun May 28 22:59:05 EEST 2023
 */

package GUI;

import java.awt.*;
import javax.swing.*;
import net.miginfocom.swing.*;

/**
 * @author ADMIN
 */
public class URLs2 extends JFrame {
    public URLs2() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - Madaras Andrei
        comboBox1 = new JComboBox();
        spinner1 = new JSpinner();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        button1 = new JButton();
        textField1 = new JTextField();
        label1 = new JLabel();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "fill,hidemode 3,align left center",
            // columns
            "[11,fill]" +
            "[41,fill]" +
            "[467,fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]",
            // rows
            "[10:10:100,top]" +
            "[224]" +
            "[10:10:20,center]" +
            "[20:20:40,center]"));
        contentPane.add(comboBox1, "cell 2 1");
        contentPane.add(spinner1, "cell 2 1");

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1, "cell 2 1,aligny bottom,growy 0");

        //---- button1 ----
        button1.setText("text");
        contentPane.add(button1, "cell 3 1");
        contentPane.add(textField1, "cell 4 1");

        //---- label1 ----
        label1.setText("Add new entry");
        contentPane.add(label1, "cell 4 1,alignx center,growx 0");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - Madaras Andrei
    private JComboBox comboBox1;
    private JSpinner spinner1;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JButton button1;
    private JTextField textField1;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
