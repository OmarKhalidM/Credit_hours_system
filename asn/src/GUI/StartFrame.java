package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
/*
 * Created by JFormDesigner on Sat Apr 01 04:48:41 IDT 2023
 */


/**
 * @author NedalNnee
 */
public class StartFrame extends JFrame {
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel label1;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    private exit exit;
    private EnterAPp Enter;
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-END:variables  @formatter:off

    public StartFrame() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label1 = new JLabel();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();
        exit = new exit();
        Enter = new EnterAPp();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new BorderLayout(24, 24));

                //---- label1 ----
                label1.setText("\u0645\u0631\u062d\u0628\u0627 \u0628\u0643 ");
                label1.setFont(new Font("Tajawal", Font.PLAIN, 16));
                label1.setHorizontalAlignment(SwingConstants.CENTER);
                contentPanel.add(label1, BorderLayout.CENTER);
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

                //---- okButton ----
                okButton.setBackground(new Color(0x1985a1));
                okButton.setForeground(new Color(0xdcdcdd));
                okButton.setFont(new Font("Tajawal", Font.PLAIN, 16));
                okButton.setAction(Enter);
                buttonBar.add(okButton, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- cancelButton ----
                cancelButton.setBackground(new Color(0xb20d30));
                cancelButton.setForeground(new Color(0xdcdcdd));
                cancelButton.setFont(new Font("Tajawal", Font.PLAIN, 16));
                cancelButton.setAction(exit);
                buttonBar.add(cancelButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        setSize(400, 300);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    private class exit extends AbstractAction {
        private exit() {
            // JFormDesigner - Action initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
            putValue(NAME, "\u062e\u0631\u0648\u062c");
            // JFormDesigner - End of action initialization  //GEN-END:initComponents  @formatter:on
        }

        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    private class EnterAPp extends AbstractAction {
        private EnterAPp() {
            // JFormDesigner - Action initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
            putValue(NAME, "\u062f\u062e\u0648\u0644");
            // JFormDesigner - End of action initialization  //GEN-END:initComponents  @formatter:on
        }

        public void actionPerformed(ActionEvent e) {
            App.showMainFrame();
        }
    }
}
