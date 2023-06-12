package dentist_reserve;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DataStorage {
    public static Boolean addPatient(String Name, String ID, String Address, String Mobile, String birthday) {
        String sql = "INSERT INTO patient_info (Name, ID, Address, Phone, Birthday) VALUES (?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;
        Boolean ret_value = false;

        try {
        	connection = DatabaseConnector.getConnection();
        	statement = connection.prepareStatement(sql);
        	
            statement.setString(1, Name);
            statement.setString(2, ID);
            statement.setString(3, Address);
            statement.setString(4, Mobile);
            statement.setString(5, birthday);
            statement.executeUpdate();

            System.out.println("Value stored successfully.");
            
            ret_value = true;
        } catch (SQLException e) {
            System.err.println("Error storing value: " + e.getMessage());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
            
            return ret_value;
        }
    }
    
    public static ArrayList<String> getPatientInfo(String ID) {
        String sql = "SELECT * from patient_info WHERE ID = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<String> patientInfo = new ArrayList<>();
        
        try {
        	connection = DatabaseConnector.getConnection();
        	statement = connection.prepareStatement(sql);
        	
            statement.setString(1, ID);
            resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
            	System.out.println("Value retrieved successfully.");
            	patientInfo.add(resultSet.getString("Number"));
            	patientInfo.add(resultSet.getString("Name"));
            	patientInfo.add(resultSet.getString("ID"));
            	patientInfo.add(resultSet.getString("Address"));
            	patientInfo.add(resultSet.getString("Phone"));
            } else {
            	System.out.println("No Value\n");
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving value: " + e.getMessage());
        } finally {
            try {
            	if (resultSet != null) {
            		resultSet.close();
            	}
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
            
            return patientInfo;
        }
    }
}

