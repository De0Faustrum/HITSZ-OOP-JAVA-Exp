package edu.hitsz.application.GUI;
import edu.hitsz.application.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * A message box, pops when removing a record for you to confirm thr operation.
 * @author Kosmischer
 * */

public class AscertainBox extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;

    public AscertainBox(int idToDelete) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK(idToDelete);
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // 点击 X 时调用 onCancel()
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // 遇到 ESCAPE 时调用 onCancel()
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    /**
     * After confirm, delete the record
     * @param idToDelete The id of the record to remove
     * */
    private void onOK(int idToDelete) {
        if(Game.RECORD_MANAGER.deleteScoreRecord(idToDelete)){
            System.out.println("Record " + idToDelete + " has already been removed.");
            Main.END_PAGE.refreshTable();
            Game.RECORD_MANAGER.writeObjectToFile();
        }
        dispose();
    }

    /**
     * Do nothing
     * */
    private void onCancel() {
        dispose();
    }

}
