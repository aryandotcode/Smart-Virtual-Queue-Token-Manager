import com.smartqueue.db.DatabaseConnection;
import com.smartqueue.ui.LoginForm;
import java.sql.Connection;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Test database connection
        try (Connection conn = DatabaseConnection.getConnection()) {
            System.out.println("✅ Database connection successful!");
        } catch (Exception e) {
            System.out.println("❌ Database connection failed: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        // Start the application
        SwingUtilities.invokeLater(() -> new LoginForm());
    }
}