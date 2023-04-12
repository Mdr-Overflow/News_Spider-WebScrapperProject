/*
 * Created by JFormDesigner on Mon Apr 10 11:34:45 EEST 2023
 */

package Design;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.jgoodies.forms.factories.*;
import net.miginfocom.swing.*;

/**
 * @author ADMIN
 */
public class OptionsNew extends JFrame {
    public OptionsNew() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - Madaras Andrei
        DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
        panel1 = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        comboBox1 = new JComboBox();
        formattedTextField1 = new JFormattedTextField();
        scrollBar1 = new JScrollBar();
        desktopPane1 = new JDesktopPane();
        hSpacer1 = new JPanel(null);
        vSpacer2 = new JPanel(null);
        separator1 = compFactory.createSeparator("sfdsdfssssssssssssssss");
        button1 = new JButton();
        checkBox1 = new JCheckBox();
        radioButton1 = new JRadioButton();
        toggleButton2 = new JToggleButton();
        toggleButton1 = new JToggleButton();
        vSpacer1 = new JPanel(null);
        vSpacer3 = new JPanel(null);

        //======== this ========
        setBackground(new Color(0xcccc00));
        Container contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[335,fill]" +
            "[188,fill]" +
            "[422,fill]",
            // rows
            "[58]" +
            "[48]" +
            "[64]" +
            "[]" +
            "[89]" +
            "[]" +
            "[388]"));

        //======== panel1 ========
        {
            panel1.setBackground(new Color(0x00cc33));
            panel1.setLayout(new MigLayout(
                "hidemode 3",
                // columns
                "[fill]" +
                "[fill]",
                // rows
                "[]" +
                "[]" +
                "[]"));
        }
        contentPane.add(panel1, "cell 0 0");

        //---- label1 ----
        label1.setText("OPTIONS");
        label1.setBackground(new Color(0xff9900));
        label1.setForeground(new Color(0xff6600));
        label1.setFont(new Font("Yu Gothic", Font.BOLD, 22));
        contentPane.add(label1, "cell 1 0,align center center,grow 0 0");

        //---- label2 ----
        label2.setText("Resoltuion");
        contentPane.add(label2, "cell 0 1,align center top,grow 0 0");

        //---- comboBox1 ----
        comboBox1.setFont(new Font("Bauhaus 93", Font.PLAIN, 16));
        comboBox1.setBorder(Borders.DLU2_BORDER);
        comboBox1.setMinimumSize(new Dimension(90, 29));
        contentPane.add(comboBox1, "cell 0 2");
        contentPane.add(formattedTextField1, "cell 0 4");
        contentPane.add(scrollBar1, "cell 0 4");
        contentPane.add(desktopPane1, "cell 0 4");
        contentPane.add(hSpacer1, "cell 1 4");
        contentPane.add(vSpacer2, "cell 2 5");
        contentPane.add(separator1, "cell 0 6");

        //---- button1 ----
        button1.setText("text");
        contentPane.add(button1, "cell 0 6");

        //---- checkBox1 ----
        checkBox1.setText("text");
        checkBox1.setFont(checkBox1.getFont().deriveFont(checkBox1.getFont().getStyle() | Font.BOLD, checkBox1.getFont().getSize() + 20f));
        checkBox1.setOpaque(true);
        checkBox1.setBorderPaintedFlat(true);
        checkBox1.setBackground(new Color(0x33ff33));
        contentPane.add(checkBox1, "cell 1 6");

        //---- radioButton1 ----
        radioButton1.setText("text");
        contentPane.add(radioButton1, "cell 1 6");

        //---- toggleButton2 ----
        toggleButton2.setText("text");
        contentPane.add(toggleButton2, "cell 1 6");

        //---- toggleButton1 ----
        toggleButton1.setText("text");
        contentPane.add(toggleButton1, "cell 1 6");
        contentPane.add(vSpacer1, "cell 1 6");
        contentPane.add(vSpacer3, "cell 2 6");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - Madaras Andrei
    private JPanel panel1;
    private JLabel label1;
    private JLabel label2;
    private JComboBox comboBox1;
    private JFormattedTextField formattedTextField1;
    private JScrollBar scrollBar1;
    private JDesktopPane desktopPane1;
    private JPanel hSpacer1;
    private JPanel vSpacer2;
    private JComponent separator1;
    private JButton button1;
    private JCheckBox checkBox1;
    private JRadioButton radioButton1;
    private JToggleButton toggleButton2;
    private JToggleButton toggleButton1;
    private JPanel vSpacer1;
    private JPanel vSpacer3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
