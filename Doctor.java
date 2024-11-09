import java.sql.*;
public class Doctor{
    static String url = "jdbc:mysql://localhost:3306/hospitalms";
    static String username = "root";
    static String password = "pass123";

    //Adding new doctor details
    public void adddetails(String user_id, String user_name, String user_work, int user_age, int user_exp){
        String query = "INSERT INTO doctor (doctor_id, doctor_name, doctor_specialization, doctor_age, doctor_experience) value (?,?,?,?,?)";
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            preparedStatement.setString(1, user_id);
            preparedStatement.setString(2, user_name);
            preparedStatement.setString(3, user_work);
            preparedStatement.setInt(4, user_age);
            preparedStatement.setInt(5, user_exp);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Fetching doctor details
}
