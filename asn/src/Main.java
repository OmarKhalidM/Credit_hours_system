import GUI.App;
import sql.DBConnect;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {


    public static void main(String[] args) {
        DBConnect.initDB();
        App.start();

    }
}