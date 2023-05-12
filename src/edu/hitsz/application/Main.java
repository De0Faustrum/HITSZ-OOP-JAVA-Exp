package edu.hitsz.application;
import edu.hitsz.application.GUI.AscertainBox;
import edu.hitsz.application.GUI.EndPage;
import edu.hitsz.application.GUI.ScoreCheckBox;
import edu.hitsz.application.GUI.StartMenu;
import edu.hitsz.application.GameMode.CommonGame;
import edu.hitsz.application.GameMode.EasyGame;
import edu.hitsz.application.GameMode.HardGame;
import edu.hitsz.application.GameMode.StaticConfiguration;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * 程序入口
 * @author hitsz
 */
public class Main {

    public static final int WINDOW_WIDTH = 512;
    public static final int WINDOW_HEIGHT = 768;
    public static final int BOX_WIDTH = 250;
    public static final int BOX_HEIGHT = 300;
    public static final CardLayout CARD_LAYOUT = new CardLayout(0, 0);
    public static final JPanel CARD_PANEL = new JPanel(CARD_LAYOUT);
    public static final JFrame FRAME = new JFrame("Aircraft War");
    public static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    public static EndPage END_PAGE;

    /**
     * The main entrance of the game
     * Create the FRAME and add a start menu page into it
     * */
    public static void main(String[] args) {

        System.out.println("Hello Aircraft War");

        FRAME.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        FRAME.setResizable(false);
        //设置窗口的大小和位置,居中放置
        FRAME.setBounds(((int) SCREEN_SIZE.getWidth() - WINDOW_WIDTH) / 2, ((int) SCREEN_SIZE.getHeight() - WINDOW_HEIGHT)/2,
                WINDOW_WIDTH, WINDOW_HEIGHT);
        FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        StartMenu startMenu = new StartMenu();
        CARD_PANEL.add(startMenu.getMainPanel());
        CARD_LAYOUT.next(CARD_PANEL);
        FRAME.add(CARD_PANEL);
        FRAME.setVisible(true);

    }

    /**
     * Start game, activated after selecting difficulty in the start menu
     * */
    public static void startGame(){
        Game game;
        HashMap<Integer,String> hashMap = new HashMap<>();
        hashMap.put(1,"Easy");
        hashMap.put(2,"Common");
        hashMap.put(3,"Hard");
        String difficulty = hashMap.get(StaticConfiguration.difficultyMode);
        System.out.println("Game Difficulty: " + difficulty);
        game = switch (difficulty) {
            case "Easy" -> new EasyGame();
            case "Common" -> new CommonGame();
            case "Hard" -> new HardGame();
            default -> new CommonGame();
        };
        CARD_PANEL.add(game);
        CARD_LAYOUT.next(CARD_PANEL);
        game.action();
    }

    /**
     * Display record ranking, activated after game over
     * */
    public static void displayRanking(){
        END_PAGE = new EndPage();
        Main.CARD_PANEL.add(END_PAGE.getMainPanel());
        Main.CARD_LAYOUT.next(Main.CARD_PANEL);
        Main.FRAME.add(Main.CARD_PANEL);
        Main.FRAME.setVisible(true);
    }

    /**
     * A message box to check whether to store your records or not
     * */
    public static void scoreCheck(){
        ScoreCheckBox scoreCheckBox = new ScoreCheckBox();
        scoreCheckBox.setTitle("Score Check");
        scoreCheckBox.setBounds(((int) SCREEN_SIZE.getWidth() - BOX_WIDTH) / 2, ((int) SCREEN_SIZE.getHeight() - BOX_HEIGHT)/2,
                BOX_WIDTH, BOX_HEIGHT);
        scoreCheckBox.setResizable(false);
        scoreCheckBox.pack();
        scoreCheckBox.setVisible(true);
    }

    /**
     * A message box to confirm removal of a record
     * */
    public static void removeAscertain(int idToDelete){
        AscertainBox ascertainBox = new AscertainBox(idToDelete);
        ascertainBox.setTitle("Ascertain");
        ascertainBox.setBounds(((int) SCREEN_SIZE.getWidth() - BOX_WIDTH) / 2, ((int) SCREEN_SIZE.getHeight() - BOX_HEIGHT)/2,
                BOX_WIDTH, BOX_HEIGHT);
        ascertainBox.setResizable(false);
        ascertainBox.pack();
        ascertainBox.setVisible(true);
    }
}
