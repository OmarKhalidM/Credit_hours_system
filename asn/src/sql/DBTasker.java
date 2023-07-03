package sql;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.sql.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DBTasker {

    public static final String TABLE_PREFIX = "subject_";
    public static final ExecutorService service = Executors.newFixedThreadPool(2);
    public static final String HOURS_COUNT = "36";
    public static final String HOURS_NUMBER_TEXT = "عدد الساعات";
    public static final String IS_SUCCESS_TEXT = "ناجح";


    public static void addSheetDataToDB(Subjects subject, XSSFSheet data, ArrayList<String> columnNames) {

        String tableName = TABLE_PREFIX + subject.toString();
        Connection conn = DBConnect.getConnection();

        try {
            ArrayList<String> newColumnNames = new ArrayList<>();
            for (String column : columnNames) {
                column = "\"" + column + "\"";
//                column = (column.replace(" ", "_"));
                newColumnNames.add(column);
            }

            String sql = "INSERT INTO " + tableName + " (" + String.join(", ", newColumnNames) + ") VALUES (" +
                    String.join(", ", Collections.nCopies(columnNames.size(), "?")) + ")";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            System.out.println(sql);
            final int batchSize = 100;
            int count = 0;
            for (Row row : data) {
                for (int i = 0; i < columnNames.size(); i++) {
                    if (columnNames.get(i).equals(HOURS_NUMBER_TEXT)) {
                        pstmt.setString(i + 1, HOURS_COUNT);
                    } else if (columnNames.get(i).equals(IS_SUCCESS_TEXT)) {
                        pstmt.setString(i + 1, "false");
                    } else {
                        Cell cell = row.getCell(i);
                        if (cell != null)
                            pstmt.setString(i + 1, cell.toString());
                        else
                        pstmt.setString(i + 1, "---");

                    }
                }

                pstmt.addBatch();
                count++;
                if (count % batchSize == 0) {
                    pstmt.executeBatch();
                }
            }
            pstmt.executeBatch();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public static ArrayList<String> createSubjectTableFromSheet(Subjects subject, XSSFSheet sheet) {
        try {
            ArrayList<String> columns = new ArrayList<>();
            for (Row row : sheet) {
                for (Cell cell : row) {
                    columns.add(cell.toString());
                }
                break;
            }
            addMetaColumnsToTable(columns);

            createTableFromColumns(TABLE_PREFIX + subject.toString(), columns);
            return columns;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void addMetaColumnsToTable(ArrayList<String> columns) {
        columns.add(HOURS_NUMBER_TEXT);
        columns.add(IS_SUCCESS_TEXT);
    }

    private static void createTableFromColumns(String tableName, ArrayList<String> columns) throws SQLException {
        Connection conn = DBConnect.getConnection();

        try (Statement stmt = conn.createStatement()) {
            String dropSql = "DROP TABLE " + tableName;
            stmt.executeUpdate(dropSql);
            stmt.close();
            System.out.println("Table " + tableName + " dropped successfully.");
        } catch (SQLException e) {
            System.out.println("Table dosent exist");
        }

        // Create new table
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE ");
        sb.append(tableName);
        sb.append(" (id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), ");

        for (String column : columns) {
            column = "\"" + column + "\"";
//            sb.append(column.replace(" ", "_"));
            sb.append(column);
            sb.append(" VARCHAR(255), ");
        }

        sb.append("PRIMARY KEY (id))");

        try (Statement stmt = conn.createStatement()) {
            System.out.println(sb);
            stmt.executeUpdate(sb.toString());
            System.out.println("Table " + tableName + " created successfully.");
        }
    }

    public static Map<String, List<String>> getAllDataFromTable(Subjects subjects) {
        String tableName = TABLE_PREFIX + subjects;
        Map<String, List<String>> tableData = new HashMap<>();
        try {

            Connection conn = DBConnect.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName);
            ResultSetMetaData meta = rs.getMetaData();
            int columnCount = meta.getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                String columnName = meta.getColumnName(i);
                tableData.put(columnName, new ArrayList<>());
            }

            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = meta.getColumnName(i);
                    String value = rs.getString(i);
                    tableData.get(columnName).add(value);
                }
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }

        return tableData;
    }

    public static void updateTableValues(Map<String, String> data, Subjects subjectName) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBConnect.getConnection();
            pstmt = conn.prepareStatement("UPDATE " + TABLE_PREFIX + subjectName.toString() + " SET \"" + IS_SUCCESS_TEXT + "\" = ? WHERE id = ?");

            for (Map.Entry<String, String> entry : data.entrySet()) {
                String id = entry.getKey();
                String value = entry.getValue();
                pstmt.setString(1, value);
                pstmt.setString(2, id);
                pstmt.addBatch();
            }

            pstmt.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
