/*
 * Created by JFormDesigner on Sat Apr 01 12:36:11 IDT 2023
 */

package GUI;

import excel.ExcelHandler;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import sql.Subjects;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

import static GUI.App.addNewSubject;

/**
 * @author NedalNnee
 */
public class NewSubjectFrame extends JFrame {
    private XSSFSheet sheet;

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel panel1;
    private JButton button1;
    private JButton addNewSubject;
    private JLabel fileName;
    private JLabel label1;
    private JComboBox subjects;
    private openFilePicker action1;
    private AddNewSubject AddNewSubject;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    public NewSubjectFrame() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        panel1 = new JPanel();
        button1 = new JButton();
        addNewSubject = new JButton();
        fileName = new JLabel();
        label1 = new JLabel();
        subjects = new JComboBox();
        action1 = new openFilePicker();
        AddNewSubject = new AddNewSubject();

        //======== this ========
        setMinimumSize(new Dimension(350, 200));
        setPreferredSize(new Dimension(350, 200));
        setModalExclusionType(Dialog.ModalExclusionType.TOOLKIT_EXCLUDE);
        setAlwaysOnTop(true);
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== panel1 ========
        {
            panel1.setPreferredSize(new Dimension(350, 50));
            panel1.setMinimumSize(new Dimension(350, 100));
            panel1.setBorder(new EmptyBorder(10, 10, 10, 10));
            panel1.setLayout(new BorderLayout());

            //---- button1 ----
            button1.setAction(action1);
            button1.setPreferredSize(new Dimension(150, 50));
            button1.setMaximumSize(new Dimension(100, 100));
            button1.setMinimumSize(new Dimension(150, 30));
            button1.setText("\u0625\u062e\u062a\u064a\u0627\u0631 \u0645\u0644\u0641 excel");
            button1.setFont(new Font("Tajawal", button1.getFont().getStyle(), button1.getFont().getSize() + 4));
            button1.setBackground(new Color(0xccffff));
            panel1.add(button1, BorderLayout.EAST);

            //---- addNewSubject ----
            addNewSubject.setPreferredSize(new Dimension(100, 50));
            addNewSubject.setMaximumSize(new Dimension(100, 100));
            addNewSubject.setMinimumSize(new Dimension(100, 30));
            addNewSubject.setFont(new Font("Tajawal", addNewSubject.getFont().getStyle(), addNewSubject.getFont().getSize() + 4));
            addNewSubject.setAction(AddNewSubject);
            addNewSubject.setBackground(new Color(0xccffff));
            addNewSubject.setToolTipText("\u0625\u0636\u0627\u0641\u0629 ");
            addNewSubject.setText("\u0625\u0636\u0627\u0641\u0629");
            addNewSubject.setEnabled(false);
            panel1.add(addNewSubject, BorderLayout.WEST);

            //---- fileName ----
            fileName.setHorizontalAlignment(SwingConstants.CENTER);
            panel1.add(fileName, BorderLayout.CENTER);
        }
        contentPane.add(panel1, BorderLayout.SOUTH);

        //---- label1 ----
        label1.setText("\u0627\u062e\u062a\u0631 \u0627\u0644\u0645\u0627\u062f\u0629");
        label1.setMinimumSize(new Dimension(150, 50));
        label1.setMaximumSize(new Dimension(200, 50));
        label1.setPreferredSize(new Dimension(150, 50));
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setFont(new Font("Tajawal", label1.getFont().getStyle(), label1.getFont().getSize() + 4));
        contentPane.add(label1, BorderLayout.EAST);
        contentPane.add(subjects, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on

        for (Subjects subject : Subjects.values()) {
            subjects.addItem(subject.getName());
        }

    }

    private class openFilePicker extends AbstractAction {
        private openFilePicker() {
            // JFormDesigner - Action initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
            putValue(NAME, "openFilePicker");
            // JFormDesigner - End of action initialization  //GEN-END:initComponents  @formatter:on
        }

        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(NewSubjectFrame.this);
            if (result == JFileChooser.APPROVE_OPTION) {
                this.setEnabled(false);
                JButton jButton  = (JButton) e.getSource();;
                SwingUtilities.invokeLater(()->{

                    jButton.setText("جاري التحميل...");
                });

                File selectedFile = fileChooser.getSelectedFile();

                sheet = ExcelHandler.readFile(selectedFile.getPath());
                jButton.setText("تم تحميل الملف");

                addNewSubject.setEnabled(true);
            }
        }
    }


    private class AddNewSubject extends AbstractAction {
        private AddNewSubject() {
            // JFormDesigner - Action initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
            putValue(NAME, "AddNewSubject");
            // JFormDesigner - End of action initialization  //GEN-END:initComponents  @formatter:on
        }

        public void actionPerformed(ActionEvent e) {
            if (sheet != null) {
                System.out.println("ADDING TO DB......................................");

                this.setEnabled(false);
                JButton jButton = (JButton) e.getSource();
                jButton.setText("جاري الحفظ...");

                addNewSubject(Subjects.getByName(subjects.getSelectedItem().toString()), sheet);
                App.hideNewSubjectFrame();

            }
        }
    }
}

