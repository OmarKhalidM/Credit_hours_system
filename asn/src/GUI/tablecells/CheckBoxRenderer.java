package GUI.tablecells;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class CheckBoxRenderer extends JCheckBox implements TableCellRenderer {


    public CheckBoxRenderer() {
        setHorizontalAlignment(JCheckBox.CENTER);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        boolean valueB = false;
        try {
            valueB = (Boolean) value;
        }catch (Exception ignored){

        }

        setSelected((value != null && valueB));
        return this;
    }
}
