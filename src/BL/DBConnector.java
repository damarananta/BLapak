package BL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by damarananta on 12/27/16.
 * Note: reuse
 */

public class DBConnector {
    private static String dbusername = "root";
    private static String dbpassword = "root";
    //Pattern jdbc:mysql://host:port/database name
    private static String DB = "jdbc:mysql://localhost:22020/employee";
    
    public ArrayList<String> executeSQLQuery_List(String sqlQuery) {
        Connection connection;
        ArrayList<String> resultValue = new ArrayList<String>();
        ResultSet resultSet;

        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(DB,dbusername,dbpassword);
            if(connection!=null) {
                System.out.println("Connected to the database");
            }else {
                System.out.println("Failed to connect database");
            }
            Statement statement = connection.createStatement();
            resultSet=statement.executeQuery(sqlQuery);

            try {
                while(resultSet.next()){
                    int columnCount = resultSet.getMetaData().getColumnCount();
                    StringBuilder stringBuilder = new StringBuilder();
                    for(int iCounter=1;iCounter<=columnCount; iCounter++){
                        stringBuilder.append(resultSet.getString(iCounter).trim()+" ");
                    }
                    String reqValue = stringBuilder.substring(0, stringBuilder.length()-1);
                    resultValue.add(reqValue);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            catch (NullPointerException ex) {
                System.out.println("No Records found for this specific query" +ex.getStackTrace());
            }
            finally {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException ex) {
                        System.out.println( "SQL Exception:" +ex.getStackTrace());
                    }
                }
            }

        }catch(SQLException sqlEx) {
            System.out.println( "SQL Exception:" +sqlEx.getStackTrace());
        }
        return resultValue;
    }
    public String executeSQLQuery(String sqlQuery) {
        Connection connection;
        String resultValue = "";
        ResultSet rs;

        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(DB,dbusername,dbpassword);
            if(connection!=null) {
                System.out.println("Connected to the database...");
            }else {
                System.out.println("Database connection failed");
            }
            Statement stmt = connection.createStatement();
            rs=stmt.executeQuery(sqlQuery);

            try {
                while(rs.next()){
                    resultValue = rs.getString(1).toString();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            catch (NullPointerException err) {
                System.out.println("No Records obtained for this specific query");
                err.printStackTrace();
            }
            connection.close();

        }catch(SQLException sqlEx) {
            System.out.println( "SQL Exception:" +sqlEx.getStackTrace());
        }
        return resultValue;
    }


}