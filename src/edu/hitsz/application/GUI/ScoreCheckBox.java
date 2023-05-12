package edu.hitsz.application.GUI;

import edu.hitsz.application.Game;
import edu.hitsz.application.Main;
import edu.hitsz.data.ScoreRecord;

import javax.swing.*;
import java.awt.event.*;
import java.util.Date;

public class ScoreCheckBox extends JDialog {
    private JPanel mainCheckBoxPanel;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JLabel noticeLine1;
    private JLabel noticeLine2;

    public ScoreCheckBox() {

        setSize(Main.BOX_WIDTH, Main.BOX_HEIGHT);
        noticeLine1.setText("您的本局得分为: " + Game.SCORE);
        noticeLine2.setText("请输入玩家昵称以记录得分:");
        setContentPane(mainCheckBoxPanel);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        mainCheckBoxPanel.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // 在此处添加您的代码
        ScoreRecord record = new ScoreRecord(Game.RECORD_MANAGER.getNextId(), textField1.getText(), Game.SCORE, new Date());
        if(Game.RECORD_MANAGER.insertScoreRecord(record)){
            System.out.println("Record Insertion Success");
        }
        Main.END_PAGE.refreshTable();
        dispose();
    }

    private void onCancel() {
        // 必要时在此处添加您的代码
        dispose();
    }

}
