package GUI.tablecells;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckboxEditor extends DefaultCellEditor    {

    protected JCheckBox checkBox;
    public CheckboxEditor(JCheckBox checkBox) {
        super(checkBox);
        this.checkBox = checkBox;

    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        boolean value1 = (value instanceof Boolean) ? (Boolean) value : false;
        checkBox.setSelected(value1);
        return checkBox;
    }

    @Override
    public Object getCellEditorValue() {
        return checkBox.isSelected();
    }



}
