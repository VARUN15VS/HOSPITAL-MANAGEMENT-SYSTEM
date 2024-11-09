import java.sql.*;
public class Holiday {
    static String url = "jdbc:mysql://localhost:3306/hospitalms";
    static String username = "root";
    static String password = "pass123";

    //Adding holidays
    public void addholiday(String user_id, String date){
        String query = "INSERT INTO holiday (doctor_id, holiday_date) value (?,?)";
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            preparedStatement.setString(1, user_id);
            preparedStatement.setString(2, date);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Deleting holidays
    public void deleteholiday(String user_id, String date){
        String query = "DELETE FROM holiday where doctor_id = ? and holiday_date = ?";
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            preparedStatement.setString(1, user_id);
            preparedStatement.setString(2, date);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Reteriving holidays
}
