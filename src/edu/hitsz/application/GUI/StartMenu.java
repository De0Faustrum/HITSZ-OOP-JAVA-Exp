package edu.hitsz.application.GUI;

import edu.hitsz.application.Game;
import edu.hitsz.application.GameMode.StaticConfiguration;
import edu.hitsz.application.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartMenu {
    private JPanel MainStartMenuPanel;
    private JButton simpleMode;
    private JButton commonMode;
    private JButton hardMode;
    private JComboBox comboBox1;

    public StartMenu() {
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(comboBox1.getSelectedIndex() == 0){
                    System.out.println("音效开");
                }
                else {
                    System.out.println("音效关");
                }
            }
        });
        simpleMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StaticConfiguration.difficultyMode = 1;
                startWithMusicConfig();
            }
        });
        commonMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StaticConfiguration.difficultyMode = 2;
                startWithMusicConfig();
            }
        });
        hardMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StaticConfiguration.difficultyMode = 3;
                startWithMusicConfig();
            }
        });
    }

    /**
     * Start the game and set the on/off of the bgm
     * */
    public void startWithMusicConfig(){
        if(comboBox1.getSelectedIndex() == 0){
            Game.musicSwitch = true;
        }
        else {
            Game.musicSwitch =false;
        }
        Main.startGame();
    }

    /**
     * Used in Card Panel Module
     * @return Main JPanel
     * */
    public JPanel getMainPanel(){
        return MainStartMenuPanel;
    }
}
