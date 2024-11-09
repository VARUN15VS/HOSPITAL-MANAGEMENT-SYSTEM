import java.sql.*;

public class Login {
    static String url = "jdbc:mysql://localhost:3306/hospitalms";
    static String username = "root";
    static String password = "pass123";

    // Function for verifying login credentials
    public boolean verifyLogin(String userUsername, String userPassword) {
        String query = "SELECT password FROM login WHERE username = ?";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            preparedStatement.setString(1, userUsername);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    String dbPassword = rs.getString("password");
                    return dbPassword.equals(userPassword);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method to increment and update the user ID
    public void updateNewId(String type, String userId) {
        int uid = Integer.parseInt(userId);
        int newUid = uid + 1;
        userId = String.valueOf(newUid);  // Convert incremented id back to string

        String query = "UPDATE id SET idno = ? WHERE type = ?";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            preparedStatement.setString(1, userId); // Updated ID
            preparedStatement.setString(2, type);    // Type to match in WHERE clause

            preparedStatement.executeUpdate();  // Use executeUpdate for UPDATE statements
            System.out.println("ID updated successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get a new unique user ID based on the identifier type
    public String getNewId(int i) {
        String userId = "";
        String type = (i == 0) ? "P" : (i == 1) ? "D" : "A";  // Using a conditional to assign type

        String getIdQuery = "SELECT idno FROM id WHERE user_type = ?";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = conn.prepareStatement(getIdQuery)) {

            preparedStatement.setString(1, type);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    userId = rs.getString("idno");
                    updateNewId(type, userId);  // Update ID after fetching it
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userId;
    }

    // Method for creating a new user sign-up entry
    public int newSignup(int identifier, String userUsername, String userFname, String userLname,
                         String userPhone, String userPassword, String userType, String userAddress) {

        String userId = getNewId(identifier);  // Generate and get new user ID
        String query = "INSERT INTO login (id, username, password, fname, lname, phone_number, person_type, address) VALUES (?,?,?,?,?,?,?,?)";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            preparedStatement.setString(1, userId);         // Set generated user ID
            preparedStatement.setString(2, userUsername);
            preparedStatement.setString(3, userPassword);
            preparedStatement.setString(4, userFname);
            preparedStatement.setString(5, userLname);
            preparedStatement.setString(6, userPhone);
            preparedStatement.setString(7, userType);
            preparedStatement.setString(8, userAddress);

            preparedStatement.executeUpdate();
            return 0;  // Return 0 to indicate success
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1;  // Return 1 to indicate failure
    }
}
