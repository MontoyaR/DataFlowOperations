package io.github.MontoyaR;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

  //Declare Database URL
  static final String DB_URL = "jdbc:sqlite:C:\\Users\\Montoya\\OneDrive - Florida Gulf Coast University\\IdeaProjects\\DataFlowOperations\\src\\Data\\SQLite";

  //Declare Connection
  static Connection conn = null;

  public static void dbConnect() {
    try {
      //Create a connection to the database.
      conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Montoya\\OneDrive - Florida Gulf Coast University\\IdeaProjects\\DataFlowOperations\\src\\Data\\SQLite");

      System.out.println("Connection to SQLite has been established.");
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    } finally {
      try {
        if (conn != null) {
          conn.close();
        }
      } catch (SQLException ex) {
        System.out.println(ex.getMessage());
      }
    }
  }
}
