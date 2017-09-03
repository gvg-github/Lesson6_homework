import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by GVG on 02.09.2017.
 */
public class WorkWithBD {

    private static Connection connection;
    private static Statement stmt;
    private static final Logger logger = Logger.getLogger(WorkWithBD.class.getName());

    public static void main(String[] args) throws SQLException {

        connect();
        createBase();
        work();
        disconnect();

    }

    public static void work() throws SQLException {
        addLine(1, "Bob", 100);
        addLine(2, "Max", 90);
        addLine(3, "John", 80);
        addLine(4, "Sam", 80);
        addLine(5, "Max", 95);
        changeLine(4, "Sam", 70);
        deleteLine(2);
        deleteLine(6);
//        testTable();
    }

    public static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:MainDB.db");
            stmt = connection.createStatement();
            logger.log(Level.INFO, "Connection established");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void disconnect() throws SQLException {
        connection.close();
        logger.log(Level.INFO, "Connection closed");
    }

    public static void createBase() throws SQLException {
        stmt.execute("CREATE TABLE IF NOT EXISTS Students(\n" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,\n" +
                " Name TEXT NOT NULL,\n" +
                " Ball INTEGER);");

        stmt.execute("DELETE FROM Students");
        logger.log(Level.INFO, "BD cleared");
    }

    public static void addLine(int id, String Name, int Ball) throws SQLException {
        stmt.execute("INSERT INTO Students (id, Name, Ball) VALUES (" + id + ", '" + Name + "', " + Ball + ")");
        logger.log(Level.INFO, "Add complete");
    }

    public static void changeLine(int id, String Name, int Ball) throws SQLException {
        stmt.execute("UPDATE Students set Name = '" + Name + "', Ball = " + Ball + " WHERE id = " + id);
        logger.log(Level.INFO, "Change complete");
    }

    public static void deleteLine(int id) throws SQLException {
        stmt.execute("DELETE FROM Students WHERE id = " + id);
        logger.log(Level.INFO, "Delete complete");
    }

    public static void testTable() throws SQLException {
        ResultSet res = stmt.executeQuery("SELECT * FROM Students");
        while (res.next()) {
            System.out.print("id: " + res.getInt("id") + ", Name: " + res.getString("Name") + ", Ball: " + res.getInt("Ball"));
            System.out.println();
        }
    }

    public static Savepoint createSave() throws SQLException {
        Savepoint point = connection.setSavepoint();
        logger.log(Level.INFO, "Create savepoint");
        return point;
    }

    public static void restoreFromSave(Savepoint point) throws SQLException {
        connection.rollback(point);
        logger.log(Level.WARNING, "BD restored from last savepoint!");
    }

    public static boolean retunRS(String text) throws SQLException {
        ResultSet res = stmt.executeQuery(text);
        if (res.next()) return true;
        return false;
    }
}
