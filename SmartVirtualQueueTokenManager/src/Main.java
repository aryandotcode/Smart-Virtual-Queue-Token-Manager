import com.smartqueue.db.DatabaseConnection;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection conn = DatabaseConnection.getConnection();
        if (conn != null) {
            System.out.println("Connected to Database!");
        } else {
            System.out.println("Failed to connect.");
        }
    }
}
