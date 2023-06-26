package com.raven.form;

//import com.intellij.uiDesigner.core.*;

import javax.swing.border.*;
import javax.swing.table.*;
import com.raven.component.*;
import com.raven.dialog.Message;
import com.raven.main.Main;
import com.raven.model.ModelStudent;
import com.raven.swing.noticeboard.*;
import com.raven.swing.table.*;
import com.raven.swing.table.EventAction;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
//import org.jdesktop.swingx.*;

public class Form_MainMenu extends JPanel {

    public Form_MainMenu() {
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
        panel1 = new JPanel();

        //======== this ========
        setLayout(new MigLayout(
            "insets 0,hidemode 3",
            // columns
            "[651,fill]" +
            "[338,fill]",
            // rows
            "[426,fill]"));

        //======== panel1 ========
        {
            panel1.setLayout(new FlowLayout());
        }
        add(panel1, "cell 0 0");
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // Generated using JFormDesigner Educational license - Madaras Andrei
    private JPanel panel1;
    // End of variables declaration//GEN-END:variables
}
