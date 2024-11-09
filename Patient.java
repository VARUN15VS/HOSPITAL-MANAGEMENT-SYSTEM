import java.sql.*;
public class Patient{
    static String url = "jdbc:mysql://localhost:3306/hospitalms";
    static String username = "root";
    static String password = "pass123";

    //Adding new patient details
    public void addnewpatientdetails(String user_id, String user_fname, String user_lname, String user_problem, String doc_name, String did, int user_age) {
        String query = "INSERT INTO patient (patient_id, patient_fname, patient_lname, problem_description, doctor_name, doc_id, patient_age)value(?,?,?,?,?,?,?)";
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            preparedStatement.setString(1, user_id);
            preparedStatement.setString(2, user_fname);
            preparedStatement.setString(3, user_lname);
            preparedStatement.setString(4, user_problem);
            preparedStatement.setString(5, doc_name);
            preparedStatement.setString(6, did);
            preparedStatement.setInt(7, user_age);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Adding old patient details
    public void addoldpatientdetails(String user_id, String user_fname, String user_lname, String user_problem, String doc_name, String did, int user_age){
        String query = "UPDATE patient SET patient_fname = ?, patient_lname = ?, problem_description = ?, doctor_name = ?, doc_id = ?, patient_age = ? where patient_id = ?";
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            preparedStatement.setString(1, user_fname);
            preparedStatement.setString(2, user_lname);
            preparedStatement.setString(3, user_problem);
            preparedStatement.setString(4, doc_name);
            preparedStatement.setString(5, did);
            preparedStatement.setInt(6, user_age);
            preparedStatement.setString(7, user_id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
