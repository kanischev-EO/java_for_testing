package ru.my.pack.addressbook.test;

import org.testng.annotations.Test;
import ru.my.pack.addressbook.model.GroupData;
import ru.my.pack.addressbook.model.Groups;

import java.sql.*;

public class DbConnectionTest {
  @Test
  public void testDbConnection(){
    Connection conn = null;
    try {
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?" +
                      "user=root&password=");
      Statement statement = conn.createStatement();
      Groups groups = new Groups();
      ResultSet resultSet = statement.executeQuery("select group_id,group_name,group_header,group_footer from group_list");
      while (resultSet.next()){
        groups.add(new GroupData().withId(resultSet.getInt("group_id")).withName(resultSet.getString("group_name"))
                .withHeader(resultSet.getString("group_header")).withFooter(resultSet.getString("group_footer")));
      }
      conn.close();
      statement.close();
      resultSet.close();
      System.out.println(groups);

      // Do something with the Connection

    } catch (SQLException ex) {
      // handle any errors
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
  }
}}
