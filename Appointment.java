import java.sql.*;
public class Appointment {
    static String url = "jdbc:mysql://localhost:3306/hospitalms";
    static String username = "root";
    static String password = "pass123";

    //Adding history
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

    //Add appointment
    public int addappointment(String patient_id, String date, String doctor_id, String time){
        int i = 0;
        String query = "INSERT INTO appointment (patient_id, date, doctor_id, time_slot) value (?,?,?,?)";
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            preparedStatement.setString(1, patient_id);
            preparedStatement.setString(2, date);
            preparedStatement.setString(3, doctor_id);
            preparedStatement.setString(4, time);

            preparedStatement.executeUpdate();
            addpatienthistory(patient_id,doctor_id,date);
            i = 1;
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    //Delete appointment
    public void deleteappointment(String patient_id, String doctor_id){
        String query = "DELETE FROM appointment where patient_id = ? and doctor_id = ?";
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            preparedStatement.setString(1, patient_id);
            preparedStatement.setString(2, doctor_id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Modify appointment
    public int modifyappointment(String patient_id, String doctor_id, String time){
        int i = 0;
        String query = "UPDATE appointment SET time_slot = ? where patient_id = ? and doctor_id = ?";
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            preparedStatement.setString(1, time);
            preparedStatement.setString(2, patient_id);
            preparedStatement.setString(3, doctor_id);

            preparedStatement.executeUpdate();
            i = 1;
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    //Retrieving appointment by doctor_id
}
