/*
 * Created by JFormDesigner on Sat Apr 01 05:24:32 IDT 2023
 */

package GUI;

import GUI.tablecells.CheckBoxRenderer;
import GUI.tablecells.CheckboxEditor;
import sql.DBTasker;
import sql.Subjects;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static sql.DBTasker.IS_SUCCESS_TEXT;

/**
 * @author NedalNnee
 */
public class MainFrame extends JFrame {
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel main;
    private JPanel pane;
    private JButton newSubject;
    private JPanel panel2;
    private JLabel label1;
    private JComboBox subjectsSelect;
    private JLabel status;
    private JPanel panel3;
    private JButton button1;
    private JScrollPane scrollPane1;
    private JTable dataTable;
    private NewSubjectShow newSubjectShow;
    private selected selected;
    private saveData saveData;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    public MainFrame() {
        initComponents();
        for (Subjects subject : Subjects.values()) {
            subjectsSelect.addItem(subject.getName());
        }
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        main = new JPanel();
        pane = new JPanel();
        newSubject = new JButton();
        panel2 = new JPanel();
        label1 = new JLabel();
        subjectsSelect = new JComboBox();
        status = new JLabel();
        panel3 = new JPanel();
        button1 = new JButton();
        scrollPane1 = new JScrollPane();
        dataTable = new JTable();
        newSubjectShow = new NewSubjectShow();
        selected = new selected();
        saveData = new saveData();

        //======== this ========
        setVisible(true);
        setMaximumSize(new Dimension(1920, 1080));
        setMinimumSize(new Dimension(900, 600));
        setTitle("Welcome");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(100, 30));
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== main ========
        {
            main.setLayout(new BorderLayout());

            //======== pane ========
            {
                pane.setPreferredSize(new Dimension(250, 80));
                pane.setMinimumSize(new Dimension(150, 100));
                pane.setAlignmentX(0.0F);
                pane.setAlignmentY(0.0F);
                pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
                pane.setFont(new Font("Tajawal", Font.PLAIN, 12));
                pane.setBorder(new EmptyBorder(5, 5, 5, 5));
                pane.setLayout(new BorderLayout());

                //---- newSubject ----
                newSubject.setPreferredSize(new Dimension(200, 30));
                newSubject.setMinimumSize(new Dimension(200, 30));
                newSubject.setMaximumSize(new Dimension(150, 30));
                newSubject.setHorizontalTextPosition(SwingConstants.CENTER);
                newSubject.setFont(new Font("Tajawal", Font.BOLD, 16));
                newSubject.setAction(newSubjectShow);
                newSubject.setText("Import Excel File");
                newSubject.setBackground(new Color(0x1985a1));
                newSubject.setForeground(new Color(0xdcdcdd));
                pane.add(newSubject, BorderLayout.WEST);

                //======== panel2 ========
                {
                    panel2.setMinimumSize(new Dimension(350, 100));
                    panel2.setPreferredSize(new Dimension(200, 100));
                    panel2.setLayout(new BorderLayout());

                    //---- label1 ----
                    label1.setText("Subject");
                    label1.setMaximumSize(new Dimension(100, 30));
                    label1.setMinimumSize(new Dimension(100, 30));
                    label1.setFont(new Font("Tajawal", Font.PLAIN, 16));
                    label1.setHorizontalAlignment(SwingConstants.RIGHT);
                    label1.setBorder(null);
                    panel2.add(label1, BorderLayout.EAST);

                    //---- subjectsSelect ----
                    subjectsSelect.setFont(new Font("Tajawal", Font.PLAIN, 16));
                    subjectsSelect.setBorder(new EmptyBorder(0, 10, 0, 0));
                    subjectsSelect.setBackground(new Color(0x1985a1));
                    subjectsSelect.setForeground(new Color(0xdcdcdd));
                    subjectsSelect.setAction(selected);
                    panel2.add(subjectsSelect, BorderLayout.CENTER);
                }
                pane.add(panel2, BorderLayout.EAST);

                //---- status ----
                status.setForeground(Color.black);
                status.setHorizontalAlignment(SwingConstants.CENTER);
                pane.add(status, BorderLayout.CENTER);

                //======== panel3 ========
                {
                    panel3.setPreferredSize(new Dimension(46, 40));
                    panel3.setMinimumSize(new Dimension(46, 40));
                    panel3.setLayout(new BorderLayout());

                    //---- button1 ----
                    button1.setBorder(new EmptyBorder(10, 10, 10, 10));
                    button1.setAction(saveData);
                    button1.setBackground(new Color(0x1985a1));
                    button1.setFont(new Font("sansserif", Font.PLAIN, 16));
                    button1.setForeground(new Color(0xdcdcdd));
                    button1.setText("submit");
                    panel3.add(button1, BorderLayout.EAST);
                }
                pane.add(panel3, BorderLayout.SOUTH);
            }
            main.add(pane, BorderLayout.PAGE_START);

            //======== scrollPane1 ========
            {
                scrollPane1.setPreferredSize(new Dimension(452, 200));
                scrollPane1.setBorder(new EmptyBorder(10, 10, 10, 10));

                //---- dataTable ----
                dataTable.setFont(new Font("Tajawal", Font.PLAIN, 18));
                dataTable.setRowHeight(24);
                dataTable.setBorder(new EmptyBorder(5, 5, 5, 5));
                scrollPane1.setViewportView(dataTable);
            }
            main.add(scrollPane1, BorderLayout.CENTER);
        }
        contentPane.add(main, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }


    //    public void addToTable(Map<String, List<String>> data) {
//        ArrayList<String> columns = new ArrayList<>(data.keySet());
//        DefaultTableModel model = new DefaultTableModel(columns.toArray(), 0);
//        dataTable.setModel(model);
//
//        int numRows = 0;
//        for (List<String> rowValues : data.values()) {
//            if (rowValues.size() > numRows) {
//                numRows = rowValues.size();
//            }
//        }
//
//        for (int i = 0; i < numRows; i++) {
//            String[] row = new String[columns.size()];
//            for (int j = 0; j < columns.size(); j++) {
//                List<String> columnValues = data.get(columns.get(j));
//
//                if (i < columnValues.size()) {
//                    row[j] = columnValues.get(i);
//                } else {
//                    row[j] = "";
//                }
//            }
//            model.addRow(row);
//        }
//    }
    public void addToTable(Map<String, List<String>> data) {
        ArrayList<String> columns = new ArrayList<>(data.keySet());
        DefaultTableModel model = new DefaultTableModel(columns.toArray(), 0);
        dataTable.setModel(model);
        if (data.size() == 0) {
            return;
        }

        int numRows = 0;
        for (List<String> rowValues : data.values()) {
            if (rowValues.size() > numRows) {
                numRows = rowValues.size();
            }
        }
        for (int i = 0; i < numRows; i++) {
            String[] row = new String[columns.size()];
            for (int j = 0; j < columns.size(); j++) {
                List<String> columnValues = data.get(columns.get(j));

                if (i < columnValues.size()) {

                    row[j] = columnValues.get(i);

                } else {
                    row[j] = "";
                }
            }
            model.addRow(row);
        }

        int isSuccessColumnNumber = getColumnNumberByTitle(model, IS_SUCCESS_TEXT);

        TableColumnModel columnModel = dataTable.getColumnModel();
        TableColumn tableColumn = columnModel.getColumn(isSuccessColumnNumber);
        tableColumn.setCellRenderer(new CheckBoxRenderer());

        CheckboxEditor checkboxEditor = new CheckboxEditor(new JCheckBox());
        tableColumn.setCellEditor(checkboxEditor);


        for (int i = 0; i < numRows; i++) {
            for (String column : columns) {
                List<String> columnValues = data.get(column);
                String value = columnValues.get(i);
                if (column.equals(IS_SUCCESS_TEXT) && value.equals("true")) {
                    dataTable.setValueAt(true, i, isSuccessColumnNumber);
                }
            }
        }


    }

    public int getColumnNumberByTitle(DefaultTableModel model, String title) {
        for (int i = 0; i < model.getColumnCount(); i++) {
            if (model.getColumnName(i).equalsIgnoreCase(title.replace(" ", "_"))) {
                return i;
            }
        }
        return -1;
    }

    public void showTableData() {
        String s = subjectsSelect.getSelectedItem().toString();
        Subjects subject = Subjects.getByName(s);
        SwingUtilities.invokeLater(() -> {
            subjectsSelect.setEnabled(false);
            newSubject.setEnabled(false);
            status.setText("جاري التحميل...");
        });
        Map<String, List<String>> tableData = DBTasker.getAllDataFromTable(subject);
        addToTable(tableData);


        SwingUtilities.invokeLater(() -> {
            subjectsSelect.setEnabled(true);
            newSubject.setEnabled(true);
            status.setText(subject.getName());
        });

    }


    private class NewSubjectShow extends AbstractAction {
        private NewSubjectShow() {
            // JFormDesigner - Action initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
            putValue(NAME, "newSubjectShow");
            putValue(ACTION_COMMAND_KEY, "newSubjectShow");
            // JFormDesigner - End of action initialization  //GEN-END:initComponents  @formatter:on
        }

        public void actionPerformed(ActionEvent e) {
            App.showNewSubjectFrame();
        }
    }

    private class subjectSelected extends AbstractAction {
        private subjectSelected() {
            // JFormDesigner - Action initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
            // JFormDesigner - End of action initialization  //GEN-END:initComponents  @formatter:on
        }

        public void actionPerformed(ActionEvent e) {


        }
    }


    private class selected extends AbstractAction {
        private selected() {
            // JFormDesigner - Action initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
            putValue(NAME, "selected");
            // JFormDesigner - End of action initialization  //GEN-END:initComponents  @formatter:on
        }

        public void actionPerformed(ActionEvent e) {
            DBTasker.service.execute(MainFrame.this::showTableData);
        }
    }

    private class saveData extends AbstractAction {
        private saveData() {
            // JFormDesigner - Action initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
            putValue(NAME, "saveData");
            // JFormDesigner - End of action initialization  //GEN-END:initComponents  @formatter:on
        }

        public void actionPerformed(ActionEvent e) {
            DBTasker.service.execute(() -> {
                DefaultTableModel model = (DefaultTableModel) dataTable.getModel();
                Map<String, String> idSuccessMap = new HashMap<>();
                int idCoumnNumber = getColumnNumberByTitle(model, "id");
                int isSuccessColumnNumber = getColumnNumberByTitle(model, IS_SUCCESS_TEXT);
                for (int i = 0; i < dataTable.getRowCount(); i++) {
                    try {


                        String id = dataTable.getValueAt(i, idCoumnNumber).toString();
                        Object value = dataTable.getValueAt(i, isSuccessColumnNumber);
                        System.out.println(value);
                        Boolean success = (Boolean) value;
                        idSuccessMap.put(id, success.toString());
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }

                }
                String s = subjectsSelect.getSelectedItem().toString();
                Subjects subject = Subjects.getByName(s);
                DBTasker.updateTableValues(idSuccessMap, subject);

                showTableData();
            });

        }
    }
}
