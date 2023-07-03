package GUI;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import sql.DBTasker;
import sql.Subjects;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static sql.DBTasker.addSheetDataToDB;

public class App {

    private static StartFrame startFrame;
    private static MainFrame mainFrame;
    private static NewSubjectFrame newSubjectFrame;


    private static boolean isInit = false;

    public static void init() {
        if (!isInit)
            SwingUtilities.invokeLater(() -> startFrame = new StartFrame());
        isInit = true;
    }

    public static void start() {
        init();
        SwingUtilities.invokeLater(() -> startFrame.setVisible(true));
    }

    public static StartFrame getStartFrame() {
        return startFrame;
    }

    public static MainFrame getMainFrame() {
        return mainFrame;
    }

    public static void showMainFrame() {
        SwingUtilities.invokeLater(() -> {
            mainFrame = new MainFrame();
            startFrame.setVisible(false);
            mainFrame.setVisible(true);

        });

    }

    public static void showNewSubjectFrame() {
        if (newSubjectFrame == null || !newSubjectFrame.isVisible())
            SwingUtilities.invokeLater(() -> {
                newSubjectFrame = new NewSubjectFrame();
                newSubjectFrame.setVisible(true);

            });
    }



    public static void updateMainFrame(Map<String, List<String>> data) {
        mainFrame.addToTable(data);
    }



    public static void addNewSubject(Subjects subject, XSSFSheet sheet) {
        System.out.println("APP ADD NEW SUBJECT");

        ArrayList<String> columns = DBTasker.createSubjectTableFromSheet(subject, sheet);


        CompletableFuture<Void> firstTask = CompletableFuture.runAsync(() -> {
            addSheetDataToDB(subject, sheet,columns);
        });
        firstTask.thenRun(() -> {
            newSubjectFrame.setVisible(false);
            mainFrame.showTableData();
        });

    }

    public static void hideNewSubjectFrame() {
        if (newSubjectFrame != null && newSubjectFrame.isVisible())
            SwingUtilities.invokeLater(() -> {
                newSubjectFrame.setVisible(false);

            });
    }
}
