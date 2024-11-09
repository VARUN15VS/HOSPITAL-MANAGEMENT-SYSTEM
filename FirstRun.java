import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class FirstRun {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/";
        String username = "root";
        String password = "pass123";

        try {
            // Connect to MySQL server
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stm = conn.createStatement();

            // Create database
            String createDatabase = "CREATE DATABASE IF NOT EXISTS hospitalMS";
            stm.executeUpdate(createDatabase);

            // Use the newly created database
            stm.executeUpdate("USE hospitalMS");

            // Create Patient table
            String createPatientTable = "CREATE TABLE IF NOT EXISTS Patient (" +
                    "patient_id VARCHAR(100) PRIMARY KEY, " +
                    "patient_fname VARCHAR(50), " +
                    "patient_lname VARCHAR(50), " +
                    "problem_description VARCHAR(255), " +
                    "doctor_name VARCHAR(50), " +
                    "doc_id VARCHAR(100), " +
                    "patient_age INT)";
            stm.executeUpdate(createPatientTable);

            // Create Doctor table
            String createDoctorTable = "CREATE TABLE IF NOT EXISTS Doctor (" +
                    "doctor_id VARCHAR(100) PRIMARY KEY, " +
                    "doctor_name VARCHAR(50), " +
                    "doctor_specialization VARCHAR(50), " +
                    "doctor_age INT, " +
                    "doctor_experience INT)";
            stm.executeUpdate(createDoctorTable);

            // Create Lab table
            String createLabTable = "CREATE TABLE IF NOT EXISTS Lab (" +
                    "report_id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "patient_id VARCHAR(100), " +
                    "doctor_id VARCHAR(100), " +
                    "lab_no VARCHAR(20), " +
                    "lab_description VARCHAR(255), " +
                    "date VARCHAR(50), " +
                    "report_upload BLOB, " +
                    "FOREIGN KEY (patient_id) REFERENCES Patient(patient_id), " +
                    "FOREIGN KEY (doctor_id) REFERENCES Doctor(doctor_id))";
            stm.executeUpdate(createLabTable);

            // Create History table
            String createHistoryTable = "CREATE TABLE IF NOT EXISTS History (" +
                    "history_id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "patient_id VARCHAR(100), " +
                    "doctor_id VARCHAR(100), " +
                    "date VARCHAR(50), " +
                    "FOREIGN KEY (patient_id) REFERENCES Patient(patient_id), " +
                    "FOREIGN KEY (doctor_id) REFERENCES Doctor(doctor_id))";
            stm.executeUpdate(createHistoryTable);

            // Create Holiday table
            String createHolidayTable = "CREATE TABLE IF NOT EXISTS Holiday (" +
                    "doctor_id VARCHAR(100), " +
                    "holiday_date VARCHAR(50)";
            stm.executeUpdate(createHolidayTable);

            // Create Appointment table
            String createAppointmentTable = "CREATE TABLE IF NOT EXISTS Appointment (" +
                    "patient_id VARCHAR(100), " +
                    "date VARCHAR(50), " +
                    "doctor_id VARCHAR(100), " +
                    "time_slot VARCHAR(50), " +
                    "PRIMARY KEY (patient_id, doctor_id), " +
                    "FOREIGN KEY (patient_id) REFERENCES Patient(patient_id), " +
                    "FOREIGN KEY (doctor_id) REFERENCES Doctor(doctor_id))";
            stm.executeUpdate(createAppointmentTable);

            // Create Login table
            String createLoginTable = "CREATE TABLE IF NOT EXISTS Login (" +
                    "id VARCHAR(10) PRIMARY KEY, " +
                    "username VARCHAR(50) UNIQUE, " +
                    "password VARCHAR(255), " +
                    "fname VARCHAR(50), " +
                    "lname VARCHAR(50), " +
                    "phone_number VARCHAR(15), " +
                    "person_type VARCHAR(50), " +
                    "address VARCHAR(255), " +
                    "email_address VARCHAR(50), " +
                    "doctor_number INT)";
            stm.executeUpdate(createLoginTable);

            // Create ID Table
            String IDTable = "CREATE TABLE IF NOT EXISTS id (" +
                    "type VARCHAR(50), " +
                    "idno VARCHAR(100), " +
                    "PRIMARY KEY (type, idno))"; // Added closing parentheses and PRIMARY KEY
            stm.executeUpdate(IDTable);

            System.out.println("Database and tables created successfully.");

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}