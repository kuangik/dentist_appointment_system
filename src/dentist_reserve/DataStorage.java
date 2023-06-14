package dentist_reserve;

import java.sql.Connection;
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
        }
        return ret_value;
    }
    
    public static Boolean addAppointment(String ID, String date, String Doctor, String Treatment, String Comment) {
        String sql = "INSERT INTO appointment_info (ID, Date, Doctor, Treatment, Comment) VALUES (?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;
        Boolean ret_value = false;

        try {
        	connection = DatabaseConnector.getConnection();
        	statement = connection.prepareStatement(sql);
        	
            statement.setString(1, ID);
            statement.setString(2, date);
            statement.setString(3, Doctor);
            statement.setString(4, Treatment);
            statement.setString(5, Comment);
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
        }
        
        return ret_value;
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
        }
        
        return patientInfo;
    }
    
    public static ArrayList<String> getAppointmentInfo(String ID, String date) {
        String sql = "SELECT * from appointment_info WHERE ID = ? and Date = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<String> apptInfo = new ArrayList<>();
        
        try {
        	connection = DatabaseConnector.getConnection();
        	statement = connection.prepareStatement(sql);
        	
            statement.setString(1, ID);
            statement.setString(2, date);
            resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
            	System.out.println("Appointment Info is retrieved successfully.");
            	apptInfo.add(resultSet.getString("Date"));
            	apptInfo.add(resultSet.getString("ID"));
            	apptInfo.add(resultSet.getString("Treatment"));
            	apptInfo.add(resultSet.getString("Comment"));
            	apptInfo.add(resultSet.getString("Doctor"));
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
        }
        
        return apptInfo;
    }
    
    public static int delAppointmentInfo(String ID, String date) {
        String sql = "DELETE from appointment_info WHERE ID = ? and Date = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        int rowsAffected = 0;
        
        try {
        	connection = DatabaseConnector.getConnection();
        	statement = connection.prepareStatement(sql);
        	
            statement.setString(1, ID);
            statement.setString(2, date);
            rowsAffected = statement.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("Error retrieving value: " + e.getMessage());
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
        }
        
        return rowsAffected;
    }
    
    public static ArrayList<QueryAppointmentData> getAppointmentInfoByID_Date(String ID, String date) {
    	String sql = "";
    	Boolean query_by_id = true;
    	
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<QueryAppointmentData> apptInfo = new ArrayList<>();
        
        if (ID.isEmpty()) {
    		sql = "SELECT * from appointment_info WHERE Date = ?";
    		query_by_id = false;
    	} else if (date.isEmpty()) {
    		sql = "SELECT * from appointment_info WHERE ID = ?";
    	} else {
    		System.out.println("Invalid query\n");
    		return null;
    	}
        
        try {
        	connection = DatabaseConnector.getConnection();
        	statement = connection.prepareStatement(sql);
        	
        	if (query_by_id) {
        		statement.setString(1, ID);
        	} else {
        		statement.setString(1, date);
        	}
            resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
            	QueryAppointmentData appointmentData = new QueryAppointmentData();
            	
            	appointmentData.id 					= resultSet.getString("ID").toString();
            	appointmentData.reservation_date 	= resultSet.getString("Date").toString();
            	appointmentData.doctor 				= resultSet.getString("Doctor").toString();
            	appointmentData.comment				= resultSet.getString("Comment").toString();
            	appointmentData.treatment			= resultSet.getString("Treatment").toString();
            	
            	apptInfo.add(appointmentData);
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
        }
        
        return apptInfo;
    }
    
    public static ArrayList<String> getOperatorInfo(String username) {
        String sql = "SELECT * from operator_info WHERE username = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<String> operatorInfo = new ArrayList<>();
        
        try {
        	connection = DatabaseConnector.getConnection();
        	statement = connection.prepareStatement(sql);
        	
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
            	System.out.println("Operator Info is retrieved successfully.");
            	operatorInfo.add(resultSet.getString("username"));
            	operatorInfo.add(resultSet.getString("password"));
            	operatorInfo.add(resultSet.getString("Name"));
            	operatorInfo.add(resultSet.getString("Employee_ID"));
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
        }
        
        return operatorInfo;
    }
    
    public static Boolean addOperatorInfo(String Username, String Name, String EmployeeId, String Password) {
        String sql = "INSERT INTO operator_info (username, password, Name, Employee_ID) VALUES (?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;
        Boolean ret_value = false;

        try {
        	connection = DatabaseConnector.getConnection();
        	statement = connection.prepareStatement(sql);
        	
            statement.setString(1, Username);
            statement.setString(2, Password);
            statement.setString(3, Name);
            statement.setString(4, EmployeeId);
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
        }
        
        return ret_value;
    }
}

