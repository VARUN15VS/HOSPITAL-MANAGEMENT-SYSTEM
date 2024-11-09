import java.sql.*;
public class history {
    static String url = "jdbc:mysql://localhost:3306/hospitalms";
    static String username = "root";
    static String password = "pass123";

    //Add patient history
    public void addpatienthistory(String patient_id, String doctor_id, String date){
        String query = "INSERT INTO history (patient_id, doctor_id, date) value (?,?,?)";
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            preparedStatement.setString(1, patient_id);
            preparedStatement.setString(2, doctor_id);
            preparedStatement.setString(3, date);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Retrieving history using patient_id

    //Retrieving history using doctor_id
}
