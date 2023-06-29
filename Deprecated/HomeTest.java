/*
 * Created by JFormDesigner on Thu Jun 08 03:40:23 EEST 2023
 */

package GUI;

import java.awt.*;
import javax.swing.*;
import net.miginfocom.swing.*;

/**
 * @author ADMIN
 */
public class HomeTest extends JFrame {
    public HomeTest() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - Madaras Andrei
        separator2 = new JSeparator();
        label1 = new JLabel();
        separator1 = new JSeparator();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
        contentPane.add(separator2);

        //---- label1 ----
        label1.setIcon(new ImageIcon("C:\\Users\\ADMIN\\Documents\\GitHub\\News_Spider-WebScrapperProject\\Resources\\nodes.gif"));
        contentPane.add(label1);
        contentPane.add(separator1);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - Madaras Andrei
    private JSeparator separator2;
    private JLabel label1;
    private JSeparator separator1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
