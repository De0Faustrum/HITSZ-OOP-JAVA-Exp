package edu.hitsz.application.GUI;
import edu.hitsz.application.Game;
import edu.hitsz.application.Main;
import edu.hitsz.data.ScoreRecord;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;
import static java.lang.Integer.parseInt;

/**
 * End Page, used to display score ranking
 * @author Kosmischer
 * */

public class EndPage {
    private JTable rankingTable;
    private JButton closeWindow;
    private JButton removeRecord;
    private JPanel mainEndPagePanel;
    private JPanel buttonPanel;
    private JPanel tablePanel;
    private JScrollPane scrollPanel;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd");

    public EndPage() {

        String[] columnName = {"Rank", "Record ID", "Player Name", "Score", "Record Date"};
        List<ScoreRecord> rankingList = Game.RECORD_MANAGER.getAllScore();
        String[][] tableData = new String[rankingList.size()][5];
        for (int i = 0; i < rankingList.size(); i++) {
            tableData[i][0] = "" + (i+1);
            tableData[i][1] = "" + rankingList.get(i).getId();
            tableData[i][2] = rankingList.get(i).getPlayer();
            tableData[i][3] = "" + rankingList.get(i).getScore();
            tableData[i][4] = dateFormat.format(rankingList.get(i).getDate());
        }

        DefaultTableModel tableModel = new DefaultTableModel(tableData, columnName) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        rankingTable.setModel(tableModel);
        scrollPanel.setViewportView(rankingTable);

        removeRecord.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = rankingTable.getSelectedRow();
                if(selectedRow != -1){
                    int idToDelete = Game.RECORD_MANAGER.getAllScore().get(selectedRow).getId();
                    Main.removeAscertain(idToDelete);

                }
                else {
                    System.out.println("Index Not Valid");
                }
            }
        });
        closeWindow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.RECORD_MANAGER.putAllRecords();
            }
        });
    }

    /**
     * Used in Card Panel Module
     * @return Main JPanel
     * */
    public JPanel getMainPanel(){
        return mainEndPagePanel;
    }

    /**
     * After insertion or removal of a record, refresh the end page
     * */
    public void refreshTable(){
        String[] columnName = {"Rank", "Record ID", "Player Name", "Score", "Record Date"};
        List<ScoreRecord> rankingList = Game.RECORD_MANAGER.getAllScore();
        String[][] tableData = new String[rankingList.size()][5];
        for (int i = 0; i < rankingList.size(); i++) {
            tableData[i][0] = "" + (i+1);
            tableData[i][1] = "" + rankingList.get(i).getId();
            tableData[i][2] = rankingList.get(i).getPlayer();
            tableData[i][3] = "" + rankingList.get(i).getScore();
            tableData[i][4] = dateFormat.format(rankingList.get(i).getDate());
        }

        DefaultTableModel tableModel = new DefaultTableModel(tableData, columnName) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        rankingTable.setModel(tableModel);
        scrollPanel.setViewportView(rankingTable);
    }
}